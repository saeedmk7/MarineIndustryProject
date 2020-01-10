import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import {IJobNiazsanjiMarineSuffix} from "app/shared/model/job-niazsanji-marine-suffix.model";

type EntityResponseType = HttpResponse<IJobNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJobNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/job-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(jobNiazsanji: IJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobNiazsanji);
        return this.http
            .post<IJobNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jobNiazsanji: IJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobNiazsanji);
        return this.http
            .put<IJobNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJobNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJobNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    finalize(jobNiazsanji: IJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobNiazsanji);
        let url = SERVER_API_URL + 'api/finalize-job-niazsanji';
        return this.http
            .post<IJobNiazsanjiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IJobNiazsanjiMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    protected convertDateFromClient(jobNiazsanji: IJobNiazsanjiMarineSuffix): IJobNiazsanjiMarineSuffix {
        const copy: IJobNiazsanjiMarineSuffix = Object.assign({}, jobNiazsanji, {
            createDate: jobNiazsanji.createDate != null && jobNiazsanji.createDate.isValid() ? jobNiazsanji.createDate.toJSON() : null,
            modifyDate: jobNiazsanji.modifyDate != null && jobNiazsanji.modifyDate.isValid() ? jobNiazsanji.modifyDate.toJSON() : null,
            archivedDate:
                jobNiazsanji.archivedDate != null && jobNiazsanji.archivedDate.isValid() ? jobNiazsanji.archivedDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.personFullName = (res.body.personName != null ? res.body.personName : '') + " " + (res.body.personFamily != null ? res.body.personFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((jobNiazsanji: IJobNiazsanjiMarineSuffix) => {
                jobNiazsanji.createDate = jobNiazsanji.createDate != null ? moment(jobNiazsanji.createDate) : null;
                jobNiazsanji.modifyDate = jobNiazsanji.modifyDate != null ? moment(jobNiazsanji.modifyDate) : null;
                jobNiazsanji.archivedDate = jobNiazsanji.archivedDate != null ? moment(jobNiazsanji.archivedDate) : null;
                jobNiazsanji.personFullName = (jobNiazsanji.personName != null ? jobNiazsanji.personName : '') + " " + (jobNiazsanji.personFamily != null ? jobNiazsanji.personFamily : '');
            });
        }
        return res;
    }
}
