import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';

type EntityResponseType = HttpResponse<IRestrictionMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRestrictionMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RestrictionMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/restrictions';

    constructor(protected http: HttpClient) {}

    create(restriction: IRestrictionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(restriction);
        return this.http
            .post<IRestrictionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(restriction: IRestrictionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(restriction);
        return this.http
            .put<IRestrictionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRestrictionMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRestrictionMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(restriction: IRestrictionMarineSuffix): IRestrictionMarineSuffix {
        const copy: IRestrictionMarineSuffix = Object.assign({}, restriction, {
            createDate: restriction.createDate != null && restriction.createDate.isValid() ? restriction.createDate.toJSON() : null,
            modifyDate: restriction.modifyDate != null && restriction.modifyDate.isValid() ? restriction.modifyDate.toJSON() : null
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
            res.body.forEach((restriction: IRestrictionMarineSuffix) => {
                restriction.createDate = restriction.createDate != null ? moment(restriction.createDate) : null;
                restriction.modifyDate = restriction.modifyDate != null ? moment(restriction.modifyDate) : null;
            });
        }
        return res;
    }
}
