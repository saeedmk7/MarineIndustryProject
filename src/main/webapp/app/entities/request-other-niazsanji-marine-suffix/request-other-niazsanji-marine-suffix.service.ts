import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import {IRequestNiazsanjiFardiMarineSuffix} from "app/shared/model/request-niazsanji-fardi-marine-suffix.model";

type EntityResponseType = HttpResponse<IRequestOtherNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRequestOtherNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RequestOtherNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/request-other-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOtherNiazsanji);
        return this.http
            .post<IRequestOtherNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    finalize(requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOtherNiazsanji);
        let url = SERVER_API_URL + 'api/finalize-request-other-niazsanjis';
        return this.http
            .post<IRequestOtherNiazsanjiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOtherNiazsanji);
        return this.http
            .put<IRequestOtherNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IRequestOtherNiazsanjiMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRequestOtherNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRequestOtherNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }



    protected convertDateFromClient(requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix): IRequestOtherNiazsanjiMarineSuffix {
        const copy: IRequestOtherNiazsanjiMarineSuffix = Object.assign({}, requestOtherNiazsanji, {
            createDate:
                requestOtherNiazsanji.createDate != null && requestOtherNiazsanji.createDate.isValid()
                    ? requestOtherNiazsanji.createDate.toJSON()
                    : null,
            modifyDate:
                requestOtherNiazsanji.modifyDate != null && requestOtherNiazsanji.modifyDate.isValid()
                    ? requestOtherNiazsanji.modifyDate.toJSON()
                    : null,
            archivedDate:
                requestOtherNiazsanji.archivedDate != null && requestOtherNiazsanji.archivedDate.isValid()
                    ? requestOtherNiazsanji.archivedDate.toJSON()
                    : null
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
            res.body.forEach((requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix) => {
                requestOtherNiazsanji.createDate =
                    requestOtherNiazsanji.createDate != null ? moment(requestOtherNiazsanji.createDate) : null;
                requestOtherNiazsanji.modifyDate =
                    requestOtherNiazsanji.modifyDate != null ? moment(requestOtherNiazsanji.modifyDate) : null;
                requestOtherNiazsanji.archivedDate =
                    requestOtherNiazsanji.archivedDate != null ? moment(requestOtherNiazsanji.archivedDate) : null;
                requestOtherNiazsanji.personFullName = (requestOtherNiazsanji.personName != null ? requestOtherNiazsanji.personName : '') + " " + (requestOtherNiazsanji.personFamily != null ? requestOtherNiazsanji.personFamily : '');
            });
        }
        return res;
    }
}
