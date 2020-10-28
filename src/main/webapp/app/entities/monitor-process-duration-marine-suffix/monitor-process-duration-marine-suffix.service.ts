import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';

type EntityResponseType = HttpResponse<IMonitorProcessDurationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMonitorProcessDurationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MonitorProcessDurationMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/monitor-process-durations';

    constructor(protected http: HttpClient) {}

    create(monitorProcessDuration: IMonitorProcessDurationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorProcessDuration);
        return this.http
            .post<IMonitorProcessDurationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(monitorProcessDuration: IMonitorProcessDurationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorProcessDuration);
        return this.http
            .put<IMonitorProcessDurationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMonitorProcessDurationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMonitorProcessDurationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(monitorProcessDuration: IMonitorProcessDurationMarineSuffix): IMonitorProcessDurationMarineSuffix {
        const copy: IMonitorProcessDurationMarineSuffix = Object.assign({}, monitorProcessDuration, {
            createDate:
                monitorProcessDuration.createDate != null && monitorProcessDuration.createDate.isValid()
                    ? monitorProcessDuration.createDate.toJSON()
                    : null,
            modifyDate:
                monitorProcessDuration.modifyDate != null && monitorProcessDuration.modifyDate.isValid()
                    ? monitorProcessDuration.modifyDate.toJSON()
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
            res.body.forEach((monitorProcessDuration: IMonitorProcessDurationMarineSuffix) => {
                monitorProcessDuration.createDate =
                    monitorProcessDuration.createDate != null ? moment(monitorProcessDuration.createDate) : null;
                monitorProcessDuration.modifyDate =
                    monitorProcessDuration.modifyDate != null ? moment(monitorProcessDuration.modifyDate) : null;
            });
        }
        return res;
    }
}
