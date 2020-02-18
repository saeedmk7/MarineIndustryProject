import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';

type EntityResponseType = HttpResponse<IEvaluateCriteriaTrainingMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEvaluateCriteriaTrainingMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EvaluateCriteriaTrainingMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/evaluate-criteria-trainings';

    constructor(protected http: HttpClient) {}

    create(evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluateCriteriaTraining);
        return this.http
            .post<IEvaluateCriteriaTrainingMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluateCriteriaTraining);
        return this.http
            .put<IEvaluateCriteriaTrainingMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEvaluateCriteriaTrainingMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEvaluateCriteriaTrainingMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix
    ): IEvaluateCriteriaTrainingMarineSuffix {
        const copy: IEvaluateCriteriaTrainingMarineSuffix = Object.assign({}, evaluateCriteriaTraining, {
            createDate:
                evaluateCriteriaTraining.createDate != null && evaluateCriteriaTraining.createDate.isValid()
                    ? evaluateCriteriaTraining.createDate.toJSON()
                    : null,
            modifyDate:
                evaluateCriteriaTraining.modifyDate != null && evaluateCriteriaTraining.modifyDate.isValid()
                    ? evaluateCriteriaTraining.modifyDate.toJSON()
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
            res.body.forEach((evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix) => {
                evaluateCriteriaTraining.createDate =
                    evaluateCriteriaTraining.createDate != null ? moment(evaluateCriteriaTraining.createDate) : null;
                evaluateCriteriaTraining.modifyDate =
                    evaluateCriteriaTraining.modifyDate != null ? moment(evaluateCriteriaTraining.modifyDate) : null;
            });
        }
        return res;
    }
}
