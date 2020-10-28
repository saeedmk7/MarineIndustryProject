import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';

type EntityResponseType = HttpResponse<IMonitorLearningProcessGradeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMonitorLearningProcessGradeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MonitorLearningProcessGradeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/monitor-learning-process-grades';

    constructor(protected http: HttpClient) {}

    create(monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorLearningProcessGrade);
        return this.http
            .post<IMonitorLearningProcessGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(monitorLearningProcessGrade);
        return this.http
            .put<IMonitorLearningProcessGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMonitorLearningProcessGradeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMonitorLearningProcessGradeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix
    ): IMonitorLearningProcessGradeMarineSuffix {
        const copy: IMonitorLearningProcessGradeMarineSuffix = Object.assign({}, monitorLearningProcessGrade, {
            createDate:
                monitorLearningProcessGrade.createDate != null && monitorLearningProcessGrade.createDate.isValid()
                    ? monitorLearningProcessGrade.createDate.toJSON()
                    : null,
            modifyDate:
                monitorLearningProcessGrade.modifyDate != null && monitorLearningProcessGrade.modifyDate.isValid()
                    ? monitorLearningProcessGrade.modifyDate.toJSON()
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
            res.body.forEach((monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix) => {
                monitorLearningProcessGrade.createDate =
                    monitorLearningProcessGrade.createDate != null ? moment(monitorLearningProcessGrade.createDate) : null;
                monitorLearningProcessGrade.modifyDate =
                    monitorLearningProcessGrade.modifyDate != null ? moment(monitorLearningProcessGrade.modifyDate) : null;
            });
        }
        return res;
    }
}
