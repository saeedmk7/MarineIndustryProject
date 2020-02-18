import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';

type EntityResponseType = HttpResponse<ISoldierMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISoldierMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SoldierMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/soldiers';

    constructor(protected http: HttpClient) {}

    create(soldier: ISoldierMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldier);
        return this.http
            .post<ISoldierMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(soldier: ISoldierMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldier);
        return this.http
            .put<ISoldierMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISoldierMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISoldierMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(soldier: ISoldierMarineSuffix): ISoldierMarineSuffix {
        const copy: ISoldierMarineSuffix = Object.assign({}, soldier, {
            birthDate: soldier.birthDate != null && soldier.birthDate.isValid() ? soldier.birthDate.toJSON() : null,
            releaseDate: soldier.releaseDate != null && soldier.releaseDate.isValid() ? soldier.releaseDate.toJSON() : null,
            employmentDate: soldier.employmentDate != null && soldier.employmentDate.isValid() ? soldier.employmentDate.toJSON() : null,
            createDate: soldier.createDate != null && soldier.createDate.isValid() ? soldier.createDate.toJSON() : null,
            modifyDate: soldier.modifyDate != null && soldier.modifyDate.isValid() ? soldier.modifyDate.toJSON() : null,
            archivedDate: soldier.archivedDate != null && soldier.archivedDate.isValid() ? soldier.archivedDate.toJSON() : null

        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.birthDate = res.body.birthDate != null ? moment(res.body.birthDate) : null;
            res.body.releaseDate = res.body.releaseDate != null ? moment(res.body.releaseDate) : null;
            res.body.employmentDate = res.body.employmentDate != null ? moment(res.body.employmentDate) : null;
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.fullName = (res.body.name ? res.body.name : '') + ' ' + (res.body.family ? res.body.family : '');
            res.body.fullNameAndNationalId = (res.body.name ? res.body.name : '') + ' ' + (res.body.family ? res.body.family : '') + ' - ' + (res.body.nationalId ? res.body.nationalId : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((soldier: ISoldierMarineSuffix) => {
                soldier.birthDate = soldier.birthDate != null ? moment(soldier.birthDate) : null;
                soldier.releaseDate = soldier.releaseDate != null ? moment(soldier.releaseDate) : null;
                soldier.employmentDate = soldier.employmentDate != null ? moment(soldier.employmentDate) : null;
                soldier.createDate = soldier.createDate != null ? moment(soldier.createDate) : null;
                soldier.modifyDate = soldier.modifyDate != null ? moment(soldier.modifyDate) : null;
                soldier.archivedDate = soldier.archivedDate != null ? moment(soldier.archivedDate) : null;
                soldier.fullName = (soldier.name ? soldier.name : '') + ' ' + (soldier.family ? soldier.family : '');
                soldier.fullNameAndNationalId = (soldier.name ? soldier.name : '') + ' ' + (soldier.family ? soldier.family : '') + ' - ' + (soldier.nationalId ? soldier.nationalId : '');
            });
        }
        return res;
    }
}
