import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<IJamHelpAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJamHelpAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JamHelpAuthorityMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/jam-help-authorities';

    constructor(protected http: HttpClient) {}

    create(jamHelpAuthority: IJamHelpAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jamHelpAuthority);
        return this.http
            .post<IJamHelpAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jamHelpAuthority: IJamHelpAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jamHelpAuthority);
        return this.http
            .put<IJamHelpAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJamHelpAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJamHelpAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(jamHelpAuthority: IJamHelpAuthorityMarineSuffix): IJamHelpAuthorityMarineSuffix {
        const copy: IJamHelpAuthorityMarineSuffix = Object.assign({}, jamHelpAuthority, {
            createDate:
                jamHelpAuthority.createDate != null && jamHelpAuthority.createDate.isValid() ? jamHelpAuthority.createDate.toJSON() : null,
            modifyDate:
                jamHelpAuthority.modifyDate != null && jamHelpAuthority.modifyDate.isValid() ? jamHelpAuthority.modifyDate.toJSON() : null
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
            res.body.forEach((jamHelpAuthority: IJamHelpAuthorityMarineSuffix) => {
                jamHelpAuthority.createDate = jamHelpAuthority.createDate != null ? moment(jamHelpAuthority.createDate) : null;
                jamHelpAuthority.modifyDate = jamHelpAuthority.modifyDate != null ? moment(jamHelpAuthority.modifyDate) : null;
            });
        }
        return res;
    }
}
