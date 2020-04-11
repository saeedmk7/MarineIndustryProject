import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import {IEffectivenessPhasePerCriteriaData} from "app/shared/model/custom/effectiveness-phase-per-criteria-data";

type EntityResponseType = HttpResponse<ILevelFourEffectivenessMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelFourEffectivenessMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelFourEffectivenessMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-four-effectivenesses';

    constructor(protected http: HttpClient) {}

    create(levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourEffectiveness);
        return this.http
            .post<ILevelFourEffectivenessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourEffectiveness);
        return this.http
            .put<ILevelFourEffectivenessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
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
            .get<ILevelFourEffectivenessMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelFourEffectivenessMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix): ILevelFourEffectivenessMarineSuffix {
        const copy: ILevelFourEffectivenessMarineSuffix = Object.assign({}, levelFourEffectiveness, {
            createDate:
                levelFourEffectiveness.createDate != null && levelFourEffectiveness.createDate.isValid()
                    ? levelFourEffectiveness.createDate.toJSON()
                    : null,
            modifyDate:
                levelFourEffectiveness.modifyDate != null && levelFourEffectiveness.modifyDate.isValid()
                    ? levelFourEffectiveness.modifyDate.toJSON()
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
            res.body.forEach((levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix) => {
                levelFourEffectiveness.createDate =
                    levelFourEffectiveness.createDate != null ? moment(levelFourEffectiveness.createDate) : null;
                levelFourEffectiveness.modifyDate =
                    levelFourEffectiveness.modifyDate != null ? moment(levelFourEffectiveness.modifyDate) : null;
            });
        }
        return res;
    }
}
