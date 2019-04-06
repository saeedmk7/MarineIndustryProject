import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';

type EntityResponseType = HttpResponse<IEvaluationMethodMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEvaluationMethodMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationMethodMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/evaluation-methods';

    constructor(private http: HttpClient) {}

    create(evaluationMethod: IEvaluationMethodMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluationMethod);
        return this.http
            .post<IEvaluationMethodMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(evaluationMethod: IEvaluationMethodMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluationMethod);
        return this.http
            .put<IEvaluationMethodMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEvaluationMethodMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEvaluationMethodMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(evaluationMethod: IEvaluationMethodMarineSuffix): IEvaluationMethodMarineSuffix {
        const copy: IEvaluationMethodMarineSuffix = Object.assign({}, evaluationMethod, {
            createDate:
                evaluationMethod.createDate != null && evaluationMethod.createDate.isValid() ? evaluationMethod.createDate.toJSON() : null,
            modifyDate:
                evaluationMethod.modifyDate != null && evaluationMethod.modifyDate.isValid() ? evaluationMethod.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((evaluationMethod: IEvaluationMethodMarineSuffix) => {
            evaluationMethod.createDate = evaluationMethod.createDate != null ? moment(evaluationMethod.createDate) : null;
            evaluationMethod.modifyDate = evaluationMethod.modifyDate != null ? moment(evaluationMethod.modifyDate) : null;
        });
        return res;
    }
}
