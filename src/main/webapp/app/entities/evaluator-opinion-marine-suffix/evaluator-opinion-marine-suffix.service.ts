import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';

type EntityResponseType = HttpResponse<IEvaluatorOpinionMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEvaluatorOpinionMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EvaluatorOpinionMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/evaluator-opinions';

    constructor(protected http: HttpClient) {}

    create(evaluatorOpinion: IEvaluatorOpinionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluatorOpinion);
        return this.http
            .post<IEvaluatorOpinionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(evaluatorOpinion: IEvaluatorOpinionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evaluatorOpinion);
        return this.http
            .put<IEvaluatorOpinionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEvaluatorOpinionMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEvaluatorOpinionMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(evaluatorOpinion: IEvaluatorOpinionMarineSuffix): IEvaluatorOpinionMarineSuffix {
        const copy: IEvaluatorOpinionMarineSuffix = Object.assign({}, evaluatorOpinion, {
            createDate:
                evaluatorOpinion.createDate != null && evaluatorOpinion.createDate.isValid() ? evaluatorOpinion.createDate.toJSON() : null,
            modifyDate:
                evaluatorOpinion.modifyDate != null && evaluatorOpinion.modifyDate.isValid() ? evaluatorOpinion.modifyDate.toJSON() : null
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
            res.body.forEach((evaluatorOpinion: IEvaluatorOpinionMarineSuffix) => {
                evaluatorOpinion.createDate = evaluatorOpinion.createDate != null ? moment(evaluatorOpinion.createDate) : null;
                evaluatorOpinion.modifyDate = evaluatorOpinion.modifyDate != null ? moment(evaluatorOpinion.modifyDate) : null;
            });
        }
        return res;
    }
}
