import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';

type EntityResponseType = HttpResponse<IEffectivenessPhaseLevelMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEffectivenessPhaseLevelMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EffectivenessPhaseLevelMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/effectiveness-phase-levels';

    constructor(protected http: HttpClient) {}

    create(effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessPhaseLevel);
        return this.http
            .post<IEffectivenessPhaseLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessPhaseLevel);
        return this.http
            .put<IEffectivenessPhaseLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEffectivenessPhaseLevelMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEffectivenessPhaseLevelMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix): IEffectivenessPhaseLevelMarineSuffix {
        const copy: IEffectivenessPhaseLevelMarineSuffix = Object.assign({}, effectivenessPhaseLevel, {
            createDate:
                effectivenessPhaseLevel.createDate != null && effectivenessPhaseLevel.createDate.isValid()
                    ? effectivenessPhaseLevel.createDate.toJSON()
                    : null,
            modifyDate:
                effectivenessPhaseLevel.modifyDate != null && effectivenessPhaseLevel.modifyDate.isValid()
                    ? effectivenessPhaseLevel.modifyDate.toJSON()
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
            res.body.forEach((effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix) => {
                effectivenessPhaseLevel.createDate =
                    effectivenessPhaseLevel.createDate != null ? moment(effectivenessPhaseLevel.createDate) : null;
                effectivenessPhaseLevel.modifyDate =
                    effectivenessPhaseLevel.modifyDate != null ? moment(effectivenessPhaseLevel.modifyDate) : null;
            });
        }
        return res;
    }
}
