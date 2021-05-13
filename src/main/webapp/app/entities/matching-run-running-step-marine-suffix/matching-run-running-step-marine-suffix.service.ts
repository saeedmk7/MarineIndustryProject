import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IMatchingRunRunningStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMatchingRunRunningStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MatchingRunRunningStepMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/matching-run-running-steps';

    constructor(protected http: HttpClient) {}

    create(matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingRunRunningStep);
        return this.http
            .post<IMatchingRunRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingRunRunningStep);
        return this.http
            .put<IMatchingRunRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMatchingRunRunningStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMatchingRunRunningStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix): IMatchingRunRunningStepMarineSuffix {
        const copy: IMatchingRunRunningStepMarineSuffix = Object.assign({}, matchingRunRunningStep, {
            doneDate:
                matchingRunRunningStep.doneDate != null && matchingRunRunningStep.doneDate.isValid()
                    ? matchingRunRunningStep.doneDate.toJSON()
                    : null,
            createDate:
                matchingRunRunningStep.createDate != null && matchingRunRunningStep.createDate.isValid()
                    ? matchingRunRunningStep.createDate.toJSON()
                    : null,
            modifyDate:
                matchingRunRunningStep.modifyDate != null && matchingRunRunningStep.modifyDate.isValid()
                    ? matchingRunRunningStep.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.doneDate = res.body.doneDate != null ? moment(res.body.doneDate) : null;
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix) => {
                matchingRunRunningStep.doneDate = matchingRunRunningStep.doneDate != null ? moment(matchingRunRunningStep.doneDate) : null;
                matchingRunRunningStep.createDate =
                    matchingRunRunningStep.createDate != null ? moment(matchingRunRunningStep.createDate) : null;
                matchingRunRunningStep.modifyDate =
                    matchingRunRunningStep.modifyDate != null ? moment(matchingRunRunningStep.modifyDate) : null;
            });
        }
        return res;
    }
}
