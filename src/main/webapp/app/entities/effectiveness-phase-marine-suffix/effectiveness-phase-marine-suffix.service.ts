import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';

type EntityResponseType = HttpResponse<IEffectivenessPhaseMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEffectivenessPhaseMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EffectivenessPhaseMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/effectiveness-phases';

    constructor(protected http: HttpClient) {}


    create(effectivenessPhase: IEffectivenessPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessPhase);
        return this.http
            .post<IEffectivenessPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(effectivenessPhase: IEffectivenessPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessPhase);
        return this.http
            .put<IEffectivenessPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    startPhase(id: number): Observable<EntityResponseType> {
        const url = this.resourceUrl + "/startPhase/" + id;
        return this.http
            .put<IEffectivenessPhaseMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    completeLevelTwo(finalNiazsanjiReportId: number): Observable<HttpResponse<boolean>> {
        const url = this.resourceUrl + "/complete-level-two/" + finalNiazsanjiReportId;
        return this.http
            .put<boolean>(url, null, { observe: 'response' })
            .pipe(map((res: HttpResponse<boolean>) => res));
    }
    completeEffectivenessPhase(finalNiazsanjiReportId: number): Observable<HttpResponse<boolean>> {
        const url = this.resourceUrl + "/complete-effectiveness-phase/" + finalNiazsanjiReportId;
        return this.http
            .put<boolean>(url, null, { observe: 'response' })
            .pipe(map((res: HttpResponse<boolean>) => res));
    }
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEffectivenessPhaseMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEffectivenessPhaseMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    getByFinalNiazsanjiReportId(finalNiazsanjiReportId: number)
    {
        const url = this.resourceUrl + "/byFinalNiazsanjiId/" + finalNiazsanjiReportId;
        return this.http
            .get<IEffectivenessPhaseMarineSuffix[]>(url, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(effectivenessPhase: IEffectivenessPhaseMarineSuffix): IEffectivenessPhaseMarineSuffix {
        const copy: IEffectivenessPhaseMarineSuffix = Object.assign({}, effectivenessPhase, {
            finishPhaseDate:
                effectivenessPhase.finishPhaseDate != null && effectivenessPhase.finishPhaseDate.isValid()
                    ? effectivenessPhase.finishPhaseDate.toJSON()
                    : null,
            startPhaseDate:
                effectivenessPhase.startPhaseDate != null && effectivenessPhase.startPhaseDate.isValid()
                    ? effectivenessPhase.startPhaseDate.toJSON()
                    : null,
            createDate:
                effectivenessPhase.createDate != null && effectivenessPhase.createDate.isValid()
                    ? effectivenessPhase.createDate.toJSON()
                    : null,
            modifyDate:
                effectivenessPhase.modifyDate != null && effectivenessPhase.modifyDate.isValid()
                    ? effectivenessPhase.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.finishPhaseDate = res.body.finishPhaseDate != null ? moment(res.body.finishPhaseDate) : null;
            res.body.startPhaseDate = res.body.startPhaseDate != null ? moment(res.body.startPhaseDate) : null;
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.finalNiazsanjiReport.educationalModuleTotalTime = (res.body.finalNiazsanjiReport.educationalModuleLearningTimeTheorical
                ? res.body.finalNiazsanjiReport.educationalModuleLearningTimeTheorical : 0) +
                (res.body.finalNiazsanjiReport.educationalModuleLearningTimePractical
                ? res.body.finalNiazsanjiReport.educationalModuleLearningTimePractical : 0);
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((effectivenessPhase: IEffectivenessPhaseMarineSuffix) => {
                effectivenessPhase.finishPhaseDate =
                    effectivenessPhase.finishPhaseDate != null ? moment(effectivenessPhase.finishPhaseDate) : null;
                effectivenessPhase.startPhaseDate =
                    effectivenessPhase.startPhaseDate != null ? moment(effectivenessPhase.startPhaseDate) : null;
                effectivenessPhase.createDate = effectivenessPhase.createDate != null ? moment(effectivenessPhase.createDate) : null;
                effectivenessPhase.modifyDate = effectivenessPhase.modifyDate != null ? moment(effectivenessPhase.modifyDate) : null;
                effectivenessPhase.finalNiazsanjiReport.educationalModuleTotalTime =
                    (effectivenessPhase.finalNiazsanjiReport.educationalModuleLearningTimeTheorical
                        ? effectivenessPhase.finalNiazsanjiReport.educationalModuleLearningTimeTheorical : 0)
                    + (effectivenessPhase.finalNiazsanjiReport.educationalModuleLearningTimePractical ?
                    effectivenessPhase.finalNiazsanjiReport.educationalModuleLearningTimePractical : 0);
            });
        }
        return res;
    }
}
