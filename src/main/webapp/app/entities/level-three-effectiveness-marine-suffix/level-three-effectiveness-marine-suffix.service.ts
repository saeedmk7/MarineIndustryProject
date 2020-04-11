import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';
import {IEffectivenessPhasePerCriteriaData} from "app/shared/model/custom/effectiveness-phase-per-criteria-data";

type EntityResponseType = HttpResponse<ILevelThreeEffectivenessMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelThreeEffectivenessMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelThreeEffectivenessMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-three-effectivenesses';

    constructor(protected http: HttpClient) {}

    create(levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeEffectiveness);
        return this.http
            .post<ILevelThreeEffectivenessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeEffectiveness);
        return this.http
            .put<ILevelThreeEffectivenessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
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
            .get<ILevelThreeEffectivenessMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelThreeEffectivenessMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix): ILevelThreeEffectivenessMarineSuffix {
        const copy: ILevelThreeEffectivenessMarineSuffix = Object.assign({}, levelThreeEffectiveness, {
            createDate:
                levelThreeEffectiveness.createDate != null && levelThreeEffectiveness.createDate.isValid()
                    ? levelThreeEffectiveness.createDate.toJSON()
                    : null,
            modifyDate:
                levelThreeEffectiveness.modifyDate != null && levelThreeEffectiveness.modifyDate.isValid()
                    ? levelThreeEffectiveness.modifyDate.toJSON()
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
            res.body.forEach((levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix) => {
                levelThreeEffectiveness.createDate =
                    levelThreeEffectiveness.createDate != null ? moment(levelThreeEffectiveness.createDate) : null;
                levelThreeEffectiveness.modifyDate =
                    levelThreeEffectiveness.modifyDate != null ? moment(levelThreeEffectiveness.modifyDate) : null;
            });
        }
        return res;
    }
}
