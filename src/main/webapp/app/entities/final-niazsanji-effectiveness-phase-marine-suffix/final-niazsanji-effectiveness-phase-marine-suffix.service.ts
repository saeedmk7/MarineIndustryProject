import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';

type EntityResponseType = HttpResponse<IFinalNiazsanjiReportMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FinalNiazsanjiEffectivenessPhaseMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/final-niazsanji-reports';

    constructor(protected http: HttpClient) {}

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
    setEffectivenessPhaseLevel(finalNiazsanjiReportId: number, selectedEffectivenessPhaseLevelNumber: number): Observable<EntityResponseType> {
        let url = this.resourceUrl + "/setEffectivenessPhaseLevel/" + finalNiazsanjiReportId + "/" + selectedEffectivenessPhaseLevelNumber;
        return this.http
            .put<IFinalNiazsanjiReportMarineSuffix>(url, null,{ observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinalNiazsanjiReportMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
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

    protected convertDateFromClient(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix): IFinalNiazsanjiReportMarineSuffix {
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
                    : null,
            lastEffectivenessPhaseFinish:
                finalNiazsanjiReport.lastEffectivenessPhaseFinish != null && finalNiazsanjiReport.lastEffectivenessPhaseFinish.isValid()
                    ? finalNiazsanjiReport.lastEffectivenessPhaseFinish.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.lastEffectivenessPhaseFinish =
                res.body.lastEffectivenessPhaseFinish != null ? moment(res.body.lastEffectivenessPhaseFinish) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) => {
                finalNiazsanjiReport.createDate = finalNiazsanjiReport.createDate != null ? moment(finalNiazsanjiReport.createDate) : null;
                finalNiazsanjiReport.modifyDate = finalNiazsanjiReport.modifyDate != null ? moment(finalNiazsanjiReport.modifyDate) : null;
                finalNiazsanjiReport.archivedDate =
                    finalNiazsanjiReport.archivedDate != null ? moment(finalNiazsanjiReport.archivedDate) : null;
                finalNiazsanjiReport.lastEffectivenessPhaseFinish =
                    finalNiazsanjiReport.lastEffectivenessPhaseFinish != null
                        ? moment(finalNiazsanjiReport.lastEffectivenessPhaseFinish)
                        : null;
            });
        }
        return res;
    }
}
