import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';

type EntityResponseType = HttpResponse<IPersonMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPersonMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PersonMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/people';
    people: IPersonMarineSuffix[];
    constructor(private http: HttpClient) {}

    create(person: IPersonMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(person);
        return this.http
            .post<IPersonMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(person: IPersonMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(person);
        return this.http
            .put<IPersonMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPersonMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        if (req) {
            const options = createRequestOption(req);
            return this.http
                .get<IPersonMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
        } else {
            let url = this.resourceUrl + '/all';
            return this.http
                .get<IPersonMarineSuffix[]>(url, { observe: 'response' })
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res, true)));
        }
    }
    getUnChartPeople(): Observable<EntityArrayResponseType> {
        let url = this.resourceUrl + '/allUnChart';
        return this.http
            .get<IPersonMarineSuffix[]>(url, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res, true)));
    }
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(person: IPersonMarineSuffix): IPersonMarineSuffix {
        const copy: IPersonMarineSuffix = Object.assign({}, person, {
            birthDate: person.birthDate != null && person.birthDate.isValid() ? person.birthDate.toJSON() : null,
            employmentDate: person.employmentDate != null && person.employmentDate.isValid() ? person.employmentDate.toJSON() : null,
            createDate: person.createDate != null && person.createDate.isValid() ? person.createDate.toJSON() : null,
            modifyDate: person.modifyDate != null && person.modifyDate.isValid() ? person.modifyDate.toJSON() : null,
            archivedDate: person.archivedDate != null && person.archivedDate.isValid() ? person.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.birthDate = res.body.birthDate != null ? moment(res.body.birthDate) : null;
        res.body.employmentDate = res.body.employmentDate != null ? moment(res.body.employmentDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.fullName =
            (res.body.name ? res.body.name : '') +
            ' ' +
            (res.body.family ? res.body.family : '') +
            (res.body.nationalId ? ' ( ' + res.body.nationalId + ' ) ' : '') +
            (res.body.jobTitle ? ' ( ' + res.body.jobTitle + ' ) ' : '');
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType, isAll: boolean = false): EntityArrayResponseType {
        res.body.forEach((person: IPersonMarineSuffix) => {
            person.birthDate = person.birthDate != null ? moment(person.birthDate) : null;
            person.employmentDate = person.employmentDate != null ? moment(person.employmentDate) : null;
            person.createDate = person.createDate != null ? moment(person.createDate) : null;
            person.modifyDate = person.modifyDate != null ? moment(person.modifyDate) : null;
            person.archivedDate = person.archivedDate != null ? moment(person.archivedDate) : null;
            person.fullName =
                (person.name ? person.name : '') +
                ' ' +
                (person.family ? person.family : '') +
                (person.nationalId ? ' ( ' + person.nationalId + ' ) ' : '') +
                (person.jobTitle ? ' ( ' + person.jobTitle + ' ) ' : '');
        });
        if (isAll) this.people = res.body;
        return res;
    }
}
