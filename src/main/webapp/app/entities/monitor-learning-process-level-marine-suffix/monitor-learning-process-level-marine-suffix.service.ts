import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';

type EntityResponseType = HttpResponse<IMonitorLearningProcessLevelMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMonitorLearningProcessLevelMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MonitorLearningProcessLevelMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/monitor-learning-process-levels';

    constructor(protected http: HttpClient) {}

    create(monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorLearningProcessLevel);
        return this.http
            .post<IMonitorLearningProcessLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorLearningProcessLevel);
        return this.http
            .put<IMonitorLearningProcessLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMonitorLearningProcessLevelMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMonitorLearningProcessLevelMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix
    ): IMonitorLearningProcessLevelMarineSuffix {
        const copy: IMonitorLearningProcessLevelMarineSuffix = Object.assign({}, monitorLearningProcessLevel, {
            createDate:
                monitorLearningProcessLevel.createDate != null && monitorLearningProcessLevel.createDate.isValid()
                    ? monitorLearningProcessLevel.createDate.toJSON()
                    : null,
            modifyDate:
                monitorLearningProcessLevel.modifyDate != null && monitorLearningProcessLevel.modifyDate.isValid()
                    ? monitorLearningProcessLevel.modifyDate.toJSON()
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
            res.body.forEach((monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix) => {
                monitorLearningProcessLevel.createDate =
                    monitorLearningProcessLevel.createDate != null ? moment(monitorLearningProcessLevel.createDate) : null;
                monitorLearningProcessLevel.modifyDate =
                    monitorLearningProcessLevel.modifyDate != null ? moment(monitorLearningProcessLevel.modifyDate) : null;
            });
        }
        return res;
    }
}
