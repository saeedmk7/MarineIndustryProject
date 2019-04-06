import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';

type EntityResponseType = HttpResponse<INiazsanjiFardiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiFardiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiFardiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-fardis';

    constructor(protected http: HttpClient) {}

    create(niazsanjiFardi: INiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiFardi);
        return this.http
            .post<INiazsanjiFardiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    finalize(niazsanjiFardi: INiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiFardi);
        let url = SERVER_API_URL + 'api/finalize-niazsanji-fardi';
        return this.http
            .post<INiazsanjiFardiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiFardi: INiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiFardi);
        return this.http
            .put<INiazsanjiFardiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiFardiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiFardiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(niazsanjiFardi: INiazsanjiFardiMarineSuffix): INiazsanjiFardiMarineSuffix {
        const copy: INiazsanjiFardiMarineSuffix = Object.assign({}, niazsanjiFardi, {
            createDate:
                niazsanjiFardi.createDate != null && niazsanjiFardi.createDate.isValid() ? niazsanjiFardi.createDate.toJSON() : null,
            modifyDate:
                niazsanjiFardi.modifyDate != null && niazsanjiFardi.modifyDate.isValid() ? niazsanjiFardi.modifyDate.toJSON() : null,
            archivedDate:
                niazsanjiFardi.archivedDate != null && niazsanjiFardi.archivedDate.isValid() ? niazsanjiFardi.archivedDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.personFullName = (res.body.personName != null ? res.body.personName : '') + " " + (res.body.personFamily != null ? res.body.personFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((niazsanjiFardi: INiazsanjiFardiMarineSuffix) => {
                niazsanjiFardi.createDate = niazsanjiFardi.createDate != null ? moment(niazsanjiFardi.createDate) : null;
                niazsanjiFardi.modifyDate = niazsanjiFardi.modifyDate != null ? moment(niazsanjiFardi.modifyDate) : null;
                niazsanjiFardi.archivedDate = niazsanjiFardi.archivedDate != null ? moment(niazsanjiFardi.archivedDate) : null;
                niazsanjiFardi.personFullName = (niazsanjiFardi.personName != null ? niazsanjiFardi.personName : '') + " " + (niazsanjiFardi.personFamily != null ? niazsanjiFardi.personFamily : '');
            });
        }
        return res;
    }
}
