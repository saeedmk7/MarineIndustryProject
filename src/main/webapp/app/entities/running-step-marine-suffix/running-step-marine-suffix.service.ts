import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IRunningStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRunningStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RunningStepMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/running-steps';

    constructor(private http: HttpClient) {}

    create(runningStep: IRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runningStep);
        return this.http
            .post<IRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(runningStep: IRunningStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runningStep);
        return this.http
            .put<IRunningStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRunningStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRunningStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(runningStep: IRunningStepMarineSuffix): IRunningStepMarineSuffix {
        const copy: IRunningStepMarineSuffix = Object.assign({}, runningStep, {
            createDate: runningStep.createDate != null && runningStep.createDate.isValid() ? runningStep.createDate.toJSON() : null,
            modifyDate: runningStep.modifyDate != null && runningStep.modifyDate.isValid() ? runningStep.modifyDate.toJSON() : null,
            archivedDate: runningStep.archivedDate != null && runningStep.archivedDate.isValid() ? runningStep.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((runningStep: IRunningStepMarineSuffix) => {
            runningStep.createDate = runningStep.createDate != null ? moment(runningStep.createDate) : null;
            runningStep.modifyDate = runningStep.modifyDate != null ? moment(runningStep.modifyDate) : null;
            runningStep.archivedDate = runningStep.archivedDate != null ? moment(runningStep.archivedDate) : null;
        });
        return res;
    }
}
