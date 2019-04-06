import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IRunRunningStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRunRunningStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RunRunningStepMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/run-running-steps';

    constructor(private http: HttpClient) {}

    create(runRunningStep: IRunRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runRunningStep);
        return this.http
            .post<IRunRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(runRunningStep: IRunRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runRunningStep);
        return this.http
            .put<IRunRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRunRunningStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRunRunningStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(runRunningStep: IRunRunningStepMarineSuffix): IRunRunningStepMarineSuffix {
        const copy: IRunRunningStepMarineSuffix = Object.assign({}, runRunningStep, {
            doneDate: runRunningStep.doneDate != null && runRunningStep.doneDate.isValid() ? runRunningStep.doneDate.toJSON() : null,
            createDate:
                runRunningStep.createDate != null && runRunningStep.createDate.isValid() ? runRunningStep.createDate.toJSON() : null,
            modifyDate: runRunningStep.modifyDate != null && runRunningStep.modifyDate.isValid() ? runRunningStep.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.doneDate = res.body.doneDate != null ? moment(res.body.doneDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((runRunningStep: IRunRunningStepMarineSuffix) => {
            runRunningStep.doneDate = runRunningStep.doneDate != null ? moment(runRunningStep.doneDate) : null;
            runRunningStep.createDate = runRunningStep.createDate != null ? moment(runRunningStep.createDate) : null;
            runRunningStep.modifyDate = runRunningStep.modifyDate != null ? moment(runRunningStep.modifyDate) : null;
        });
        return res;
    }
}
