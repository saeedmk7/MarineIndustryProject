import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';

type EntityResponseType = HttpResponse<ISoldierTrainingReportMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISoldierTrainingReportMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SoldierTrainingReportMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/soldier-training-reports';

    constructor(protected http: HttpClient) {}

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {

        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + "/upload-file";
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    create(soldierTrainingReport: ISoldierTrainingReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldierTrainingReport);
        return this.http
            .post<ISoldierTrainingReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(soldierTrainingReport: ISoldierTrainingReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(soldierTrainingReport);
        return this.http
            .put<ISoldierTrainingReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISoldierTrainingReportMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISoldierTrainingReportMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    protected convertDateFromClient(soldierTrainingReport: ISoldierTrainingReportMarineSuffix): ISoldierTrainingReportMarineSuffix {
        const copy: ISoldierTrainingReportMarineSuffix = Object.assign({}, soldierTrainingReport, {
            createDate:
                soldierTrainingReport.createDate != null && soldierTrainingReport.createDate.isValid()
                    ? soldierTrainingReport.createDate.toJSON()
                    : null,
            modifyDate:
                soldierTrainingReport.modifyDate != null && soldierTrainingReport.modifyDate.isValid()
                    ? soldierTrainingReport.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.soldierFullName = (res.body.soldierName != null ? res.body.soldierName : '') + " " + (res.body.soldierFamily != null ? res.body.soldierFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((soldierTrainingReport: ISoldierTrainingReportMarineSuffix) => {
                soldierTrainingReport.createDate =
                    soldierTrainingReport.createDate != null ? moment(soldierTrainingReport.createDate) : null;
                soldierTrainingReport.modifyDate =
                    soldierTrainingReport.modifyDate != null ? moment(soldierTrainingReport.modifyDate) : null;
                soldierTrainingReport.soldierFullName =
                    (soldierTrainingReport.soldierName != null ? soldierTrainingReport.soldierName : '') + " " + (soldierTrainingReport.soldierFamily != null ? soldierTrainingReport.soldierFamily : '');
            });
        }
        return res;
    }
}
