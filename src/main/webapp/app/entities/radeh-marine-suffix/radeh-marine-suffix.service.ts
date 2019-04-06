import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';

type EntityResponseType = HttpResponse<IRadehMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRadehMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RadehMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/radehs';

    constructor(private http: HttpClient) {}

    create(radeh: IRadehMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(radeh);
        return this.http
            .post<IRadehMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(radeh: IRadehMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(radeh);
        return this.http
            .put<IRadehMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRadehMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRadehMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(radeh: IRadehMarineSuffix): IRadehMarineSuffix {
        const copy: IRadehMarineSuffix = Object.assign({}, radeh, {
            createDate: radeh.createDate != null && radeh.createDate.isValid() ? radeh.createDate.toJSON() : null,
            modifyDate: radeh.modifyDate != null && radeh.modifyDate.isValid() ? radeh.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((radeh: IRadehMarineSuffix) => {
            radeh.createDate = radeh.createDate != null ? moment(radeh.createDate) : null;
            radeh.modifyDate = radeh.modifyDate != null ? moment(radeh.modifyDate) : null;
        });
        return res;
    }
}
