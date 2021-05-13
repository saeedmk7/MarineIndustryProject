import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';

type EntityResponseType = HttpResponse<IApplicationProcessStepMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IApplicationProcessStepMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationProcessStepMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/application-process-steps';

    constructor(protected http: HttpClient) {}

    create(applicationProcessStep: IApplicationProcessStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcessStep);
        return this.http
            .post<IApplicationProcessStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(applicationProcessStep: IApplicationProcessStepMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcessStep);
        return this.http
            .put<IApplicationProcessStepMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IApplicationProcessStepMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IApplicationProcessStepMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(applicationProcessStep: IApplicationProcessStepMarineSuffix): IApplicationProcessStepMarineSuffix {
        const copy: IApplicationProcessStepMarineSuffix = Object.assign({}, applicationProcessStep, {
            createDate:
                applicationProcessStep.createDate != null && applicationProcessStep.createDate.isValid()
                    ? applicationProcessStep.createDate.toJSON()
                    : null,
            modifyDate:
                applicationProcessStep.modifyDate != null && applicationProcessStep.modifyDate.isValid()
                    ? applicationProcessStep.modifyDate.toJSON()
                    : null,
            archivedDate:
                applicationProcessStep.archivedDate != null && applicationProcessStep.archivedDate.isValid()
                    ? applicationProcessStep.archivedDate.toJSON()
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
            res.body.forEach((applicationProcessStep: IApplicationProcessStepMarineSuffix) => {
                applicationProcessStep.createDate =
                    applicationProcessStep.createDate != null ? moment(applicationProcessStep.createDate) : null;
                applicationProcessStep.modifyDate =
                    applicationProcessStep.modifyDate != null ? moment(applicationProcessStep.modifyDate) : null;
                applicationProcessStep.archivedDate =
                    applicationProcessStep.archivedDate != null ? moment(applicationProcessStep.archivedDate) : null;
            });
        }
        return res;
    }
}
