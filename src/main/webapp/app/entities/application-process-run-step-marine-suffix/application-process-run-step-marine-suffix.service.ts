import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IApplicationProcessRunStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IApplicationProcessRunStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationProcessRunStepMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/application-process-run-steps';

    constructor(protected http: HttpClient) {}

    create(applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcessRunStep);
        return this.http
            .post<IApplicationProcessRunStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcessRunStep);
        return this.http
            .put<IApplicationProcessRunStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IApplicationProcessRunStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IApplicationProcessRunStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix
    ): IApplicationProcessRunStepMarineSuffix {
        const copy: IApplicationProcessRunStepMarineSuffix = Object.assign({}, applicationProcessRunStep, {
            doneDate:
                applicationProcessRunStep.doneDate != null && applicationProcessRunStep.doneDate.isValid()
                    ? applicationProcessRunStep.doneDate.toJSON()
                    : null,
            createDate:
                applicationProcessRunStep.createDate != null && applicationProcessRunStep.createDate.isValid()
                    ? applicationProcessRunStep.createDate.toJSON()
                    : null,
            modifyDate:
                applicationProcessRunStep.modifyDate != null && applicationProcessRunStep.modifyDate.isValid()
                    ? applicationProcessRunStep.modifyDate.toJSON()
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
            res.body.forEach((applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix) => {
                applicationProcessRunStep.doneDate =
                    applicationProcessRunStep.doneDate != null ? moment(applicationProcessRunStep.doneDate) : null;
                applicationProcessRunStep.createDate =
                    applicationProcessRunStep.createDate != null ? moment(applicationProcessRunStep.createDate) : null;
                applicationProcessRunStep.modifyDate =
                    applicationProcessRunStep.modifyDate != null ? moment(applicationProcessRunStep.modifyDate) : null;
            });
        }
        return res;
    }
}
