import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';

type EntityResponseType = HttpResponse<ISecurityLevelMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISecurityLevelMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SecurityLevelMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/security-levels';

    constructor(private http: HttpClient) {}

    create(securityLevel: ISecurityLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(securityLevel);
        return this.http
            .post<ISecurityLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(securityLevel: ISecurityLevelMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(securityLevel);
        return this.http
            .put<ISecurityLevelMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISecurityLevelMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISecurityLevelMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(securityLevel: ISecurityLevelMarineSuffix): ISecurityLevelMarineSuffix {
        const copy: ISecurityLevelMarineSuffix = Object.assign({}, securityLevel, {
            createDate: securityLevel.createDate != null && securityLevel.createDate.isValid() ? securityLevel.createDate.toJSON() : null,
            modifyDate: securityLevel.modifyDate != null && securityLevel.modifyDate.isValid() ? securityLevel.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((securityLevel: ISecurityLevelMarineSuffix) => {
            securityLevel.createDate = securityLevel.createDate != null ? moment(securityLevel.createDate) : null;
            securityLevel.modifyDate = securityLevel.modifyDate != null ? moment(securityLevel.modifyDate) : null;
        });
        return res;
    }
}
