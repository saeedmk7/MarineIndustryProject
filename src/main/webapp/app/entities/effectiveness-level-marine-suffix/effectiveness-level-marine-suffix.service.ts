import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';

type EntityResponseType = HttpResponse<IEffectivenessLevelMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEffectivenessLevelMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EffectivenessLevelMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/effectiveness-levels';

    constructor(private http: HttpClient) {}

    create(effectivenessLevel: IEffectivenessLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessLevel);
        return this.http
            .post<IEffectivenessLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(effectivenessLevel: IEffectivenessLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessLevel);
        return this.http
            .put<IEffectivenessLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEffectivenessLevelMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEffectivenessLevelMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(effectivenessLevel: IEffectivenessLevelMarineSuffix): IEffectivenessLevelMarineSuffix {
        const copy: IEffectivenessLevelMarineSuffix = Object.assign({}, effectivenessLevel, {
            createDate:
                effectivenessLevel.createDate != null && effectivenessLevel.createDate.isValid()
                    ? effectivenessLevel.createDate.toJSON()
                    : null,
            modifyDate:
                effectivenessLevel.modifyDate != null && effectivenessLevel.modifyDate.isValid()
                    ? effectivenessLevel.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((effectivenessLevel: IEffectivenessLevelMarineSuffix) => {
            effectivenessLevel.createDate = effectivenessLevel.createDate != null ? moment(effectivenessLevel.createDate) : null;
            effectivenessLevel.modifyDate = effectivenessLevel.modifyDate != null ? moment(effectivenessLevel.modifyDate) : null;
        });
        return res;
    }
}
