import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';

type EntityResponseType = HttpResponse<ISoldierMediaAwarenessReportMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISoldierMediaAwarenessReportMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SoldierMediaAwarenessReportMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/soldier-media-awareness-reports';

    constructor(protected http: HttpClient) {}

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {
        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + '/upload-file';
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    create(soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldierMediaAwarenessReport);
        return this.http
            .post<ISoldierMediaAwarenessReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldierMediaAwarenessReport);
        return this.http
            .put<ISoldierMediaAwarenessReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISoldierMediaAwarenessReportMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISoldierMediaAwarenessReportMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix
    ): ISoldierMediaAwarenessReportMarineSuffix {
        const copy: ISoldierMediaAwarenessReportMarineSuffix = Object.assign({}, soldierMediaAwarenessReport, {
            createDate:
                soldierMediaAwarenessReport.createDate != null && soldierMediaAwarenessReport.createDate.isValid()
                    ? soldierMediaAwarenessReport.createDate.toJSON()
                    : null,
            modifyDate:
                soldierMediaAwarenessReport.modifyDate != null && soldierMediaAwarenessReport.modifyDate.isValid()
                    ? soldierMediaAwarenessReport.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.soldierFullName =
                (res.body.soldierName != null ? res.body.soldierName : '') +
                ' ' +
                (res.body.soldierFamily != null ? res.body.soldierFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix) => {
                soldierMediaAwarenessReport.createDate =
                    soldierMediaAwarenessReport.createDate != null ? moment(soldierMediaAwarenessReport.createDate) : null;
                soldierMediaAwarenessReport.modifyDate =
                    soldierMediaAwarenessReport.modifyDate != null ? moment(soldierMediaAwarenessReport.modifyDate) : null;
                soldierMediaAwarenessReport.soldierFullName =
                    (soldierMediaAwarenessReport.soldierName != null ? soldierMediaAwarenessReport.soldierName : '') +
                    ' ' +
                    (soldierMediaAwarenessReport.soldierFamily != null ? soldierMediaAwarenessReport.soldierFamily : '');
            });
        }
        return res;
    }
}
