import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';

type EntityResponseType = HttpResponse<ICriterionMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ICriterionMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class CriterionMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/criteria';

    constructor(private http: HttpClient) {}

    create(criterion: ICriterionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(criterion);
        return this.http
            .post<ICriterionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(criterion: ICriterionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(criterion);
        return this.http
            .put<ICriterionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICriterionMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICriterionMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(criterion: ICriterionMarineSuffix): ICriterionMarineSuffix {
        const copy: ICriterionMarineSuffix = Object.assign({}, criterion, {
            createDate: criterion.createDate != null && criterion.createDate.isValid() ? criterion.createDate.toJSON() : null,
            modifyDate: criterion.modifyDate != null && criterion.modifyDate.isValid() ? criterion.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((criterion: ICriterionMarineSuffix) => {
            criterion.createDate = criterion.createDate != null ? moment(criterion.createDate) : null;
            criterion.modifyDate = criterion.modifyDate != null ? moment(criterion.modifyDate) : null;
        });
        return res;
    }
}
