import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {IReportMarineSuffix} from "app/shared/model/report-marine-suffix.model";
import {ChartResult, IChartResult} from "app/shared/model/custom/chart-result";

type EntityResponseType = HttpResponse<IFinalNiazsanjiReportMarineSuffix>;
type EntityResponseTypeReport = HttpResponse<IReportMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FinalNiazsanjiReportMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/final-niazsanji-reports';

    constructor(private http: HttpClient) {}

    create(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalNiazsanjiReport);
        return this.http
            .post<IFinalNiazsanjiReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalNiazsanjiReport);
        return this.http
            .put<IFinalNiazsanjiReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    /*report(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix): Observable<EntityResponseType> {
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }*/
    report(educationalModuleId: number): Observable<EntityResponseTypeReport> {
        return this.http
            .get<IReportMarineSuffix>(`${this.resourceUrl}/PreReport/${educationalModuleId}`, { observe: 'response' })
            .pipe(map((res: EntityResponseTypeReport) => res));
    }
    postReport(finalNiazsanjiReportId : number): Observable<EntityResponseTypeReport> {
        return this.http
            .get<IReportMarineSuffix>(`${this.resourceUrl}/PostReport/${finalNiazsanjiReportId}`, { observe: 'response' })
            .pipe(map((res: EntityResponseTypeReport) => res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    getChartResult(niazsanjiYear: number): Observable<HttpResponse<IChartResult[]>> {

        let url = this.resourceUrl + '/getChartData/' + niazsanjiYear;
        return this.http
            .get<IChartResult[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IChartResult[]>) => res));
    }
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix): IFinalNiazsanjiReportMarineSuffix {
        const copy: IFinalNiazsanjiReportMarineSuffix = Object.assign({}, finalNiazsanjiReport, {
            createDate:
                finalNiazsanjiReport.createDate != null && finalNiazsanjiReport.createDate.isValid()
                    ? finalNiazsanjiReport.createDate.toJSON()
                    : null,
            modifyDate:
                finalNiazsanjiReport.modifyDate != null && finalNiazsanjiReport.modifyDate.isValid()
                    ? finalNiazsanjiReport.modifyDate.toJSON()
                    : null,
            archivedDate:
                finalNiazsanjiReport.archivedDate != null && finalNiazsanjiReport.archivedDate.isValid()
                    ? finalNiazsanjiReport.archivedDate.toJSON()
                    : null
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
        res.body.forEach((finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) => {
            finalNiazsanjiReport.createDate = finalNiazsanjiReport.createDate != null ? moment(finalNiazsanjiReport.createDate) : null;
            finalNiazsanjiReport.modifyDate = finalNiazsanjiReport.modifyDate != null ? moment(finalNiazsanjiReport.modifyDate) : null;
            finalNiazsanjiReport.archivedDate =
                finalNiazsanjiReport.archivedDate != null ? moment(finalNiazsanjiReport.archivedDate) : null;
        });
        return res;
    }
}
