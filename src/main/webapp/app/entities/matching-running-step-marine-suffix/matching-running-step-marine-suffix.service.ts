import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IMatchingRunningStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMatchingRunningStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MatchingRunningStepMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/matching-running-steps';

    constructor(protected http: HttpClient) {}

    create(matchingRunningStep: IMatchingRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingRunningStep);
        return this.http
            .post<IMatchingRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(matchingRunningStep: IMatchingRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingRunningStep);
        return this.http
            .put<IMatchingRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMatchingRunningStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMatchingRunningStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(matchingRunningStep: IMatchingRunningStepMarineSuffix): IMatchingRunningStepMarineSuffix {
        const copy: IMatchingRunningStepMarineSuffix = Object.assign({}, matchingRunningStep, {
            createDate:
                matchingRunningStep.createDate != null && matchingRunningStep.createDate.isValid()
                    ? matchingRunningStep.createDate.toJSON()
                    : null,
            modifyDate:
                matchingRunningStep.modifyDate != null && matchingRunningStep.modifyDate.isValid()
                    ? matchingRunningStep.modifyDate.toJSON()
                    : null,
            archivedDate:
                matchingRunningStep.archivedDate != null && matchingRunningStep.archivedDate.isValid()
                    ? matchingRunningStep.archivedDate.toJSON()
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
            res.body.forEach((matchingRunningStep: IMatchingRunningStepMarineSuffix) => {
                matchingRunningStep.createDate = matchingRunningStep.createDate != null ? moment(matchingRunningStep.createDate) : null;
                matchingRunningStep.modifyDate = matchingRunningStep.modifyDate != null ? moment(matchingRunningStep.modifyDate) : null;
                matchingRunningStep.archivedDate =
                    matchingRunningStep.archivedDate != null ? moment(matchingRunningStep.archivedDate) : null;
            });
        }
        return res;
    }
}
