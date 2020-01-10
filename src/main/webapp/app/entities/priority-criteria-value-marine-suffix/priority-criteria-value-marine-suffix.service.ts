import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';
import {IPreJobNiazsanjiCompetencyMarineSuffix} from "app/shared/model/pre-job-niazsanji-competency-marine-suffix.model";

type EntityResponseType = HttpResponse<IPriorityCriteriaValueMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPriorityCriteriaValueMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PriorityCriteriaValueMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/priority-criteria-values';

    constructor(protected http: HttpClient) {}

    create(priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(priorityCriteriaValue);
        return this.http
            .post<IPriorityCriteriaValueMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(priorityCriteriaValue);
        return this.http
            .put<IPriorityCriteriaValueMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    bulkUpdate(priorityCriteriaValues: IPriorityCriteriaValueMarineSuffix[]): Observable<EntityResponseType> {
        const copy = priorityCriteriaValues;
        const url = this.resourceUrl + "/bulk"
        return this.http
            .put<IPriorityCriteriaValueMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPriorityCriteriaValueMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPriorityCriteriaValueMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix): IPriorityCriteriaValueMarineSuffix {
        const copy: IPriorityCriteriaValueMarineSuffix = Object.assign({}, priorityCriteriaValue, {
            createDate:
                priorityCriteriaValue.createDate != null && priorityCriteriaValue.createDate.isValid()
                    ? priorityCriteriaValue.createDate.toJSON()
                    : null,
            modifyDate:
                priorityCriteriaValue.modifyDate != null && priorityCriteriaValue.modifyDate.isValid()
                    ? priorityCriteriaValue.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix) => {
                priorityCriteriaValue.createDate =
                    priorityCriteriaValue.createDate != null ? moment(priorityCriteriaValue.createDate) : null;
                priorityCriteriaValue.modifyDate =
                    priorityCriteriaValue.modifyDate != null ? moment(priorityCriteriaValue.modifyDate) : null;
            });
        }
        return res;
    }
}
