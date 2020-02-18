import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';

type EntityResponseType = HttpResponse<IHeadlineMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IHeadlineMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class HeadlineMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/headlines';

    constructor(protected http: HttpClient) {}

    create(headline: IHeadlineMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(headline);
        return this.http
            .post<IHeadlineMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(headline: IHeadlineMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(headline);
        return this.http
            .put<IHeadlineMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHeadlineMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHeadlineMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(headline: IHeadlineMarineSuffix): IHeadlineMarineSuffix {
        const copy: IHeadlineMarineSuffix = Object.assign({}, headline, {
            createDate: headline.createDate != null && headline.createDate.isValid() ? headline.createDate.toJSON() : null,
            modifyDate: headline.modifyDate != null && headline.modifyDate.isValid() ? headline.modifyDate.toJSON() : null
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
            res.body.forEach((headline: IHeadlineMarineSuffix) => {
                headline.createDate = headline.createDate != null ? moment(headline.createDate) : null;
                headline.modifyDate = headline.modifyDate != null ? moment(headline.modifyDate) : null;
            });
        }
        return res;
    }
}
