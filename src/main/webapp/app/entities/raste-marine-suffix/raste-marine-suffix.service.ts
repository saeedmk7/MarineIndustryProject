import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';

type EntityResponseType = HttpResponse<IRasteMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRasteMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RasteMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/rastes';

    constructor(private http: HttpClient) {}

    create(raste: IRasteMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(raste);
        return this.http
            .post<IRasteMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(raste: IRasteMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(raste);
        return this.http
            .put<IRasteMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRasteMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRasteMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(raste: IRasteMarineSuffix): IRasteMarineSuffix {
        const copy: IRasteMarineSuffix = Object.assign({}, raste, {
            createDate: raste.createDate != null && raste.createDate.isValid() ? raste.createDate.toJSON() : null,
            modifyDate: raste.modifyDate != null && raste.modifyDate.isValid() ? raste.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((raste: IRasteMarineSuffix) => {
            raste.createDate = raste.createDate != null ? moment(raste.createDate) : null;
            raste.modifyDate = raste.modifyDate != null ? moment(raste.modifyDate) : null;
        });
        return res;
    }
}
