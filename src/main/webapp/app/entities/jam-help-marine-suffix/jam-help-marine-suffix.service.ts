import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';

type EntityResponseType = HttpResponse<IJamHelpMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJamHelpMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JamHelpMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/jam-helps';

    constructor(protected http: HttpClient) {}

    create(jamHelp: IJamHelpMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jamHelp);
        return this.http
            .post<IJamHelpMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jamHelp: IJamHelpMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jamHelp);
        return this.http
            .put<IJamHelpMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJamHelpMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJamHelpMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(jamHelp: IJamHelpMarineSuffix): IJamHelpMarineSuffix {
        const copy: IJamHelpMarineSuffix = Object.assign({}, jamHelp, {
            createDate: jamHelp.createDate != null && jamHelp.createDate.isValid() ? jamHelp.createDate.toJSON() : null,
            modifyDate: jamHelp.modifyDate != null && jamHelp.modifyDate.isValid() ? jamHelp.modifyDate.toJSON() : null
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
            res.body.forEach((jamHelp: IJamHelpMarineSuffix) => {
                jamHelp.createDate = jamHelp.createDate != null ? moment(jamHelp.createDate) : null;
                jamHelp.modifyDate = jamHelp.modifyDate != null ? moment(jamHelp.modifyDate) : null;
            });
        }
        return res;
    }
}
