import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';

type EntityResponseType = HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PreJobNiazsanjiCompetencyMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/pre-job-niazsanji-competencies';

    constructor(protected http: HttpClient) {}

    create(preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preJobNiazsanjiCompetency);
        return this.http
            .post<IPreJobNiazsanjiCompetencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preJobNiazsanjiCompetency);
        return this.http
            .put<IPreJobNiazsanjiCompetencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    bulkUpdate(preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[]): Observable<EntityResponseType> {
        const copy = preJobNiazsanjiCompetencies;
        const url = this.resourceUrl + "/bulk"
        return this.http
            .put<IPreJobNiazsanjiCompetencyMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPreJobNiazsanjiCompetencyMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPreJobNiazsanjiCompetencyMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix
    ): IPreJobNiazsanjiCompetencyMarineSuffix {
        const copy: IPreJobNiazsanjiCompetencyMarineSuffix = Object.assign({}, preJobNiazsanjiCompetency, {
            createDate:
                preJobNiazsanjiCompetency.createDate != null && preJobNiazsanjiCompetency.createDate.isValid()
                    ? preJobNiazsanjiCompetency.createDate.toJSON()
                    : null,
            modifyDate:
                preJobNiazsanjiCompetency.modifyDate != null && preJobNiazsanjiCompetency.modifyDate.isValid()
                    ? preJobNiazsanjiCompetency.modifyDate.toJSON()
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
            res.body.forEach((preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix) => {
                preJobNiazsanjiCompetency.createDate =
                    preJobNiazsanjiCompetency.createDate != null ? moment(preJobNiazsanjiCompetency.createDate) : null;
                preJobNiazsanjiCompetency.modifyDate =
                    preJobNiazsanjiCompetency.modifyDate != null ? moment(preJobNiazsanjiCompetency.modifyDate) : null;
            });
        }
        return res;
    }
}
