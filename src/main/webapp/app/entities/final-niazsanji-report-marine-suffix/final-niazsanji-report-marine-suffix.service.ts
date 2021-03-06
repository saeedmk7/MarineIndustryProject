import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { IReportMarineSuffix } from 'app/shared/model/report-marine-suffix.model';
import { ChartResult, IChartResult } from 'app/shared/model/custom/chart-result';
import { HomePageNiazsanjiReport, IHomePageNiazsanjiReport } from 'app/shared/model/custom/niazsanji-chart-result';
import { IHomePagePersonHourChart } from 'app/shared/model/custom/home-page-person-hour-chart';
import { IHomePagePersonEducationalModule } from 'app/shared/model/custom/home-page-person-educational-module';
import { IPlanningAndRunMonthReport } from 'app/shared/model/custom/planning-month-report';
import { IHomePageReport } from 'app/shared/model/custom/home-page-report';
import { IFinalEffectivenessPhaseReportModel } from 'app/shared/model/custom/effectivenessPhaseModels/final-effectiveness-phase-report-model';
import { IChartResultDetail } from 'app/shared/model/custom/chart-result-detail';

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
    postReport(finalNiazsanjiReportId: number): Observable<EntityResponseTypeReport> {
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
        return this.http.get<IChartResult[]>(url, { observe: 'response' }).pipe(map((res: HttpResponse<IChartResult[]>) => res));
    }
    getChartResultDetail(niazsanjiYear: number, orgRootId: number): Observable<HttpResponse<IChartResultDetail[]>> {
        let url = this.resourceUrl + '/getChartResultDetail/' + niazsanjiYear + '/' + orgRootId;
        return this.http
            .get<IChartResultDetail[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IChartResultDetail[]>) => res));
    }
    getHomePageReport(niazsanjiYear: number, reportType: number): Observable<HttpResponse<IHomePageReport>> {
        let url = this.resourceUrl + '/getHomePageReport/' + niazsanjiYear + '/' + reportType;
        return this.http.get<IHomePageReport>(url, { observe: 'response' }).pipe(map((res: HttpResponse<IHomePageReport>) => res));
    }
    getHomePageNiazsanjiReport(personId: number): Observable<HttpResponse<IHomePageNiazsanjiReport>> {
        let url = this.resourceUrl + '/getHomePageNiazsanjiReport/' + personId;
        return this.http
            .get<IHomePageNiazsanjiReport>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IHomePageNiazsanjiReport>) => res));
    }
    getHomePagePersonHourChart(personId: number): Observable<HttpResponse<IHomePagePersonHourChart>> {
        let url = this.resourceUrl + '/getHomePagePersonHourChart/' + personId;
        return this.http
            .get<IHomePagePersonHourChart>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IHomePagePersonHourChart>) => res));
    }
    getHomePagePersonEducationalModule(personId: number): Observable<HttpResponse<IHomePagePersonEducationalModule[]>> {
        let url = this.resourceUrl + '/getHomePagePersonEducationalModule/' + personId;
        return this.http
            .get<IHomePagePersonEducationalModule[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IHomePagePersonEducationalModule[]>) => res));
    }
    getPlanningAndRunMonthReport(
        niazsanjiYear: number,
        reportType: number,
        orgRootId: number
    ): Observable<HttpResponse<IPlanningAndRunMonthReport[]>> {
        let url = this.resourceUrl + '/getPlanningAndRunMonthReport/' + niazsanjiYear + '/' + reportType + '/' + orgRootId;
        return this.http
            .get<IPlanningAndRunMonthReport[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IPlanningAndRunMonthReport[]>) => res));
    }
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    queryWithPeople(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        const url = this.resourceUrl + '/with-people';
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix[]>(url, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    setEffectivenessPhaseLevel(
        finalNiazsanjiReportId: number,
        selectedEffectivenessPhaseLevelNumber: number
    ): Observable<EntityResponseType> {
        let url = this.resourceUrl + '/setEffectivenessPhaseLevel/' + finalNiazsanjiReportId + '/' + selectedEffectivenessPhaseLevelNumber;
        return this.http
            .put<IFinalNiazsanjiReportMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    getFinalEffectivenessPhaseReport(
        reportYear: number,
        organizationChartIds: number[]
    ): Observable<HttpResponse<IFinalEffectivenessPhaseReportModel[]>> {
        let url = this.resourceUrl + '/get-final-effectiveness-phase-report/' + reportYear + '/' + organizationChartIds;
        return this.http
            .get<IFinalEffectivenessPhaseReportModel[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IFinalEffectivenessPhaseReportModel[]>) => this.correctNumbers(res)));
    }
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    correctNumbers(results: HttpResponse<IFinalEffectivenessPhaseReportModel[]>): HttpResponse<IFinalEffectivenessPhaseReportModel[]> {
        results.body.forEach(w => {
            w.averageEffectiveness = w.averageEffectiveness.toString() === 'NaN' ? 0 : w.averageEffectiveness;
            w.finishedCount = w.finishedCount.toString() === 'NaN' ? 0 : w.finishedCount;
        });
        return results;
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
        res.body.educationalModuleLevelTitle = res.body.skillLevelOfSkillTitle != null ? res.body.skillLevelOfSkillTitle : null;
        res.body.lastEffectivenessPhaseFinish =
            res.body.lastEffectivenessPhaseFinish != null ? moment(res.body.lastEffectivenessPhaseFinish) : null;
        res.body.teacherFullName =
            (res.body.teacherName != null ? res.body.teacherName : '') +
            ' ' +
            (res.body.teacherFamily != null ? res.body.teacherFamily : '');
        res.body.educationalModuleTotalTime =
            (res.body.educationalModuleLearningTimeTheorical ? res.body.educationalModuleLearningTimeTheorical : 0) +
            (res.body.educationalModuleLearningTimePractical ? res.body.educationalModuleLearningTimePractical : 0);

        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) => {
            finalNiazsanjiReport.createDate = finalNiazsanjiReport.createDate != null ? moment(finalNiazsanjiReport.createDate) : null;
            finalNiazsanjiReport.modifyDate = finalNiazsanjiReport.modifyDate != null ? moment(finalNiazsanjiReport.modifyDate) : null;
            finalNiazsanjiReport.archivedDate =
                finalNiazsanjiReport.archivedDate != null ? moment(finalNiazsanjiReport.archivedDate) : null;
            finalNiazsanjiReport.lastEffectivenessPhaseFinish =
                finalNiazsanjiReport.lastEffectivenessPhaseFinish != null
                    ? moment(finalNiazsanjiReport.lastEffectivenessPhaseFinish)
                    : null;
            finalNiazsanjiReport.teacherFullName =
                (finalNiazsanjiReport.teacherName != null ? finalNiazsanjiReport.teacherName : '') +
                ' ' +
                (finalNiazsanjiReport.teacherFamily != null ? finalNiazsanjiReport.teacherFamily : '');
            finalNiazsanjiReport.educationalModuleTotalTime =
                (finalNiazsanjiReport.educationalModuleLearningTimeTheorical
                    ? finalNiazsanjiReport.educationalModuleLearningTimeTheorical
                    : 0) +
                (finalNiazsanjiReport.educationalModuleLearningTimePractical
                    ? finalNiazsanjiReport.educationalModuleLearningTimePractical
                    : 0);
        });
        return res;
    }
}
