import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';

type EntityResponseType = HttpResponse<IMediaAwarenessReportMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMediaAwarenessReportMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MediaAwarenessReportMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/media-awareness-reports';

    constructor(protected http: HttpClient) {}

    create(mediaAwarenessReport: IMediaAwarenessReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mediaAwarenessReport);
        return this.http
            .post<IMediaAwarenessReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mediaAwarenessReport: IMediaAwarenessReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mediaAwarenessReport);
        return this.http
            .put<IMediaAwarenessReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMediaAwarenessReportMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMediaAwarenessReportMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(mediaAwarenessReport: IMediaAwarenessReportMarineSuffix): IMediaAwarenessReportMarineSuffix {
        const copy: IMediaAwarenessReportMarineSuffix = Object.assign({}, mediaAwarenessReport, {
            createDate:
                mediaAwarenessReport.createDate != null && mediaAwarenessReport.createDate.isValid()
                    ? mediaAwarenessReport.createDate.toJSON()
                    : null,
            modifyDate:
                mediaAwarenessReport.modifyDate != null && mediaAwarenessReport.modifyDate.isValid()
                    ? mediaAwarenessReport.modifyDate.toJSON()
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
            res.body.forEach((mediaAwarenessReport: IMediaAwarenessReportMarineSuffix) => {
                mediaAwarenessReport.createDate = mediaAwarenessReport.createDate != null ? moment(mediaAwarenessReport.createDate) : null;
                mediaAwarenessReport.modifyDate = mediaAwarenessReport.modifyDate != null ? moment(mediaAwarenessReport.modifyDate) : null;
            });
        }
        return res;
    }
}
