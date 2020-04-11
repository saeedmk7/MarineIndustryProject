import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import {IEffectivenessPhasePerCriteriaData} from "app/shared/model/custom/effectiveness-phase-per-criteria-data";

type EntityResponseType = HttpResponse<INiazsanjiPersonGradeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiPersonGradeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiPersonGradeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-person-grades';

    constructor(protected http: HttpClient) {}

    create(niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiPersonGrade);
        return this.http
            .post<INiazsanjiPersonGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiPersonGrade);
        return this.http
            .put<INiazsanjiPersonGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    completeLevel(finalNiazsanjiReportId: number): Observable<HttpResponse<boolean>> {
        const url = this.resourceUrl + "/complete-level/" + finalNiazsanjiReportId;
        return this.http
            .put<boolean>(url, null, { observe: 'response' })
            .pipe(map((res: HttpResponse<boolean>) => res));
    }
    criteriaChart(finalNiazsanjiReportId: number): Observable<HttpResponse<IEffectivenessPhasePerCriteriaData[]>> {
        const url = this.resourceUrl + "/criteria-chart/" + finalNiazsanjiReportId;
        return this.http
            .get<IEffectivenessPhasePerCriteriaData[]>(url, { observe: 'response' })
            .pipe(map((res: HttpResponse<IEffectivenessPhasePerCriteriaData[]>) => res));
    }
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiPersonGradeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiPersonGradeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix): INiazsanjiPersonGradeMarineSuffix {
        const copy: INiazsanjiPersonGradeMarineSuffix = Object.assign({}, niazsanjiPersonGrade, {
            createDate:
                niazsanjiPersonGrade.createDate != null && niazsanjiPersonGrade.createDate.isValid()
                    ? niazsanjiPersonGrade.createDate.toJSON()
                    : null,
            modifyDate:
                niazsanjiPersonGrade.modifyDate != null && niazsanjiPersonGrade.modifyDate.isValid()
                    ? niazsanjiPersonGrade.modifyDate.toJSON()
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
            res.body.forEach((niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix) => {
                niazsanjiPersonGrade.createDate = niazsanjiPersonGrade.createDate != null ? moment(niazsanjiPersonGrade.createDate) : null;
                niazsanjiPersonGrade.modifyDate = niazsanjiPersonGrade.modifyDate != null ? moment(niazsanjiPersonGrade.modifyDate) : null;
            });
        }
        return res;
    }
}
