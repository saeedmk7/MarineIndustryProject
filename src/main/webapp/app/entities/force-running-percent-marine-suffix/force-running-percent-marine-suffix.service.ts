import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';

type EntityResponseType = HttpResponse<IForceRunningPercentMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IForceRunningPercentMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ForceRunningPercentMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/force-running-percents';

    constructor(protected http: HttpClient) {}

    create(forceRunningPercent: IForceRunningPercentMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(forceRunningPercent);
        return this.http
            .post<IForceRunningPercentMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(forceRunningPercent: IForceRunningPercentMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(forceRunningPercent);
        return this.http
            .put<IForceRunningPercentMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IForceRunningPercentMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IForceRunningPercentMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(forceRunningPercent: IForceRunningPercentMarineSuffix): IForceRunningPercentMarineSuffix {
        const copy: IForceRunningPercentMarineSuffix = Object.assign({}, forceRunningPercent, {
            createDate:
                forceRunningPercent.createDate != null && forceRunningPercent.createDate.isValid()
                    ? forceRunningPercent.createDate.toJSON()
                    : null,
            modifyDate:
                forceRunningPercent.modifyDate != null && forceRunningPercent.modifyDate.isValid()
                    ? forceRunningPercent.modifyDate.toJSON()
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
            res.body.forEach((forceRunningPercent: IForceRunningPercentMarineSuffix) => {
                forceRunningPercent.createDate = forceRunningPercent.createDate != null ? moment(forceRunningPercent.createDate) : null;
                forceRunningPercent.modifyDate = forceRunningPercent.modifyDate != null ? moment(forceRunningPercent.modifyDate) : null;
            });
        }
        return res;
    }
}
