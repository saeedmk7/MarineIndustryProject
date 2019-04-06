import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';

type EntityResponseType = HttpResponse<IRunPhaseMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRunPhaseMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RunPhaseMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/run-phases';

    constructor(private http: HttpClient) {}

    create(runPhase: IRunPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runPhase);
        return this.http
            .post<IRunPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(runPhase: IRunPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runPhase);
        return this.http
            .put<IRunPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRunPhaseMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRunPhaseMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(runPhase: IRunPhaseMarineSuffix): IRunPhaseMarineSuffix {
        const copy: IRunPhaseMarineSuffix = Object.assign({}, runPhase, {
            doneDate: runPhase.doneDate != null && runPhase.doneDate.isValid() ? runPhase.doneDate.toJSON() : null,
            createDate: runPhase.createDate != null && runPhase.createDate.isValid() ? runPhase.createDate.toJSON() : null,
            modifyDate: runPhase.modifyDate != null && runPhase.modifyDate.isValid() ? runPhase.modifyDate.toJSON() : null,
            archivedDate: runPhase.archivedDate != null && runPhase.archivedDate.isValid() ? runPhase.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.doneDate = res.body.doneDate != null ? moment(res.body.doneDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((runPhase: IRunPhaseMarineSuffix) => {
            runPhase.doneDate = runPhase.doneDate != null ? moment(runPhase.doneDate) : null;
            runPhase.createDate = runPhase.createDate != null ? moment(runPhase.createDate) : null;
            runPhase.modifyDate = runPhase.modifyDate != null ? moment(runPhase.modifyDate) : null;
            runPhase.archivedDate = runPhase.archivedDate != null ? moment(runPhase.archivedDate) : null;
        });
        return res;
    }
}
