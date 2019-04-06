import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';

type EntityResponseType = HttpResponse<IEffectivenessIndexMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEffectivenessIndexMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EffectivenessIndexMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/effectiveness-indices';

    constructor(private http: HttpClient) {}

    create(effectivenessIndex: IEffectivenessIndexMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessIndex);
        return this.http
            .post<IEffectivenessIndexMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(effectivenessIndex: IEffectivenessIndexMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(effectivenessIndex);
        return this.http
            .put<IEffectivenessIndexMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEffectivenessIndexMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEffectivenessIndexMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(effectivenessIndex: IEffectivenessIndexMarineSuffix): IEffectivenessIndexMarineSuffix {
        const copy: IEffectivenessIndexMarineSuffix = Object.assign({}, effectivenessIndex, {
            createDate:
                effectivenessIndex.createDate != null && effectivenessIndex.createDate.isValid()
                    ? effectivenessIndex.createDate.toJSON()
                    : null,
            modifyDate:
                effectivenessIndex.modifyDate != null && effectivenessIndex.modifyDate.isValid()
                    ? effectivenessIndex.modifyDate.toJSON()
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
        res.body.forEach((effectivenessIndex: IEffectivenessIndexMarineSuffix) => {
            effectivenessIndex.createDate = effectivenessIndex.createDate != null ? moment(effectivenessIndex.createDate) : null;
            effectivenessIndex.modifyDate = effectivenessIndex.modifyDate != null ? moment(effectivenessIndex.modifyDate) : null;
        });
        return res;
    }
}
