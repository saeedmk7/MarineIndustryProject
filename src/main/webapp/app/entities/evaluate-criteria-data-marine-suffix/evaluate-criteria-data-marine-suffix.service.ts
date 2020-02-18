import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';

type EntityResponseType = HttpResponse<IEvaluateCriteriaDataMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEvaluateCriteriaDataMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EvaluateCriteriaDataMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/evaluate-criteria-data';

    constructor(protected http: HttpClient) {}

    create(evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluateCriteriaData);
        return this.http
            .post<IEvaluateCriteriaDataMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluateCriteriaData);
        return this.http
            .put<IEvaluateCriteriaDataMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {

        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + "/upload-file";
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEvaluateCriteriaDataMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEvaluateCriteriaDataMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix): IEvaluateCriteriaDataMarineSuffix {
        const copy: IEvaluateCriteriaDataMarineSuffix = Object.assign({}, evaluateCriteriaData, {
            createDate:
                evaluateCriteriaData.createDate != null && evaluateCriteriaData.createDate.isValid()
                    ? evaluateCriteriaData.createDate.toJSON()
                    : null,
            modifyDate:
                evaluateCriteriaData.modifyDate != null && evaluateCriteriaData.modifyDate.isValid()
                    ? evaluateCriteriaData.modifyDate.toJSON()
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
            res.body.forEach((evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix) => {
                evaluateCriteriaData.createDate = evaluateCriteriaData.createDate != null ? moment(evaluateCriteriaData.createDate) : null;
                evaluateCriteriaData.modifyDate = evaluateCriteriaData.modifyDate != null ? moment(evaluateCriteriaData.modifyDate) : null;
            });
        }
        return res;
    }
}
