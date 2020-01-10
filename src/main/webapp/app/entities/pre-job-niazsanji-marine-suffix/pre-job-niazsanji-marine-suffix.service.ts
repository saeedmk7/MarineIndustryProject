import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import {IRequestOrganizationNiazsanjiMarineSuffix} from "app/shared/model/request-organization-niazsanji-marine-suffix.model";

type EntityResponseType = HttpResponse<IPreJobNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPreJobNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PreJobNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/pre-job-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(preJobNiazsanji: IPreJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preJobNiazsanji);
        return this.http
            .post<IPreJobNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    finalize(preJobNiazsanji: IPreJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preJobNiazsanji);
        let url = this.resourceUrl + "/finalize";
        return this.http
            .post<IPreJobNiazsanjiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IPreJobNiazsanjiMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    update(preJobNiazsanji: IPreJobNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preJobNiazsanji);
        return this.http
            .put<IPreJobNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPreJobNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPreJobNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(preJobNiazsanji: IPreJobNiazsanjiMarineSuffix): IPreJobNiazsanjiMarineSuffix {
        const copy: IPreJobNiazsanjiMarineSuffix = Object.assign({}, preJobNiazsanji, {
            createDate:
                preJobNiazsanji.createDate != null && preJobNiazsanji.createDate.isValid() ? preJobNiazsanji.createDate.toJSON() : null,
            modifyDate:
                preJobNiazsanji.modifyDate != null && preJobNiazsanji.modifyDate.isValid() ? preJobNiazsanji.modifyDate.toJSON() : null,
            archivedDate:
                preJobNiazsanji.archivedDate != null && preJobNiazsanji.archivedDate.isValid()
                    ? preJobNiazsanji.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((preJobNiazsanji: IPreJobNiazsanjiMarineSuffix) => {
                preJobNiazsanji.createDate = preJobNiazsanji.createDate != null ? moment(preJobNiazsanji.createDate) : null;
                preJobNiazsanji.modifyDate = preJobNiazsanji.modifyDate != null ? moment(preJobNiazsanji.modifyDate) : null;
                preJobNiazsanji.archivedDate = preJobNiazsanji.archivedDate != null ? moment(preJobNiazsanji.archivedDate) : null;
            });
        }
        return res;
    }
}
