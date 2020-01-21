import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';

type EntityResponseType = HttpResponse<INiazsanjiOtherMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiOtherMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiOtherMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-others';

    constructor(protected http: HttpClient) {}

    create(niazsanjiOther: INiazsanjiOtherMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiOther);
        return this.http
            .post<INiazsanjiOtherMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiOther: INiazsanjiOtherMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiOther);
        return this.http
            .put<INiazsanjiOtherMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiOtherMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiOtherMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(niazsanjiOther: INiazsanjiOtherMarineSuffix): INiazsanjiOtherMarineSuffix {
        const copy: INiazsanjiOtherMarineSuffix = Object.assign({}, niazsanjiOther, {
            createDate:
                niazsanjiOther.createDate != null && niazsanjiOther.createDate.isValid() ? niazsanjiOther.createDate.toJSON() : null,
            modifyDate:
                niazsanjiOther.modifyDate != null && niazsanjiOther.modifyDate.isValid() ? niazsanjiOther.modifyDate.toJSON() : null,
            archivedDate:
                niazsanjiOther.archivedDate != null && niazsanjiOther.archivedDate.isValid() ? niazsanjiOther.archivedDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((niazsanjiOther: INiazsanjiOtherMarineSuffix) => {
                niazsanjiOther.createDate = niazsanjiOther.createDate != null ? moment(niazsanjiOther.createDate) : null;
                niazsanjiOther.modifyDate = niazsanjiOther.modifyDate != null ? moment(niazsanjiOther.modifyDate) : null;
                niazsanjiOther.archivedDate = niazsanjiOther.archivedDate != null ? moment(niazsanjiOther.archivedDate) : null;
            });
        }
        return res;
    }
}
