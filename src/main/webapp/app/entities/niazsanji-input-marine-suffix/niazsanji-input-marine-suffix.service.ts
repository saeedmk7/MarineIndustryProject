import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';

type EntityResponseType = HttpResponse<INiazsanjiInputMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiInputMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiInputMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-inputs';

    constructor(protected http: HttpClient) {}

    create(niazsanjiInput: INiazsanjiInputMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiInput);
        return this.http
            .post<INiazsanjiInputMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiInput: INiazsanjiInputMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiInput);
        return this.http
            .put<INiazsanjiInputMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiInputMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiInputMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(niazsanjiInput: INiazsanjiInputMarineSuffix): INiazsanjiInputMarineSuffix {
        const copy: INiazsanjiInputMarineSuffix = Object.assign({}, niazsanjiInput, {
            createDate:
                niazsanjiInput.createDate != null && niazsanjiInput.createDate.isValid() ? niazsanjiInput.createDate.toJSON() : null,
            modifyDate: niazsanjiInput.modifyDate != null && niazsanjiInput.modifyDate.isValid() ? niazsanjiInput.modifyDate.toJSON() : null
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
            res.body.forEach((niazsanjiInput: INiazsanjiInputMarineSuffix) => {
                niazsanjiInput.createDate = niazsanjiInput.createDate != null ? moment(niazsanjiInput.createDate) : null;
                niazsanjiInput.modifyDate = niazsanjiInput.modifyDate != null ? moment(niazsanjiInput.modifyDate) : null;
            });
        }
        return res;
    }
}
