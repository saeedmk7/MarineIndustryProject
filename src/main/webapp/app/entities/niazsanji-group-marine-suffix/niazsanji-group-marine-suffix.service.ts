import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import {IReportMarineSuffix} from "app/shared/model/report-marine-suffix.model";

type EntityResponseType = HttpResponse<INiazsanjiGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiGroupMarineSuffix[]>;
type EntityResponseTypeReport = HttpResponse<IReportMarineSuffix>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiGroupMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/niazsanji-groups';

    constructor(private http: HttpClient) {}

    report(id: number): Observable<EntityResponseTypeReport> {
        return this.http
            .get<IReportMarineSuffix>(`${this.resourceUrl}/Report/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseTypeReport) => res));
    }

    create(niazsanjiGroup: INiazsanjiGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiGroup);
        return this.http
            .post<INiazsanjiGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiGroup: INiazsanjiGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiGroup);
        return this.http
            .put<INiazsanjiGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(niazsanjiGroup: INiazsanjiGroupMarineSuffix): INiazsanjiGroupMarineSuffix {
        const copy: INiazsanjiGroupMarineSuffix = Object.assign({}, niazsanjiGroup, {
            reviewDate:
                niazsanjiGroup.reviewDate != null && niazsanjiGroup.reviewDate.isValid() ? niazsanjiGroup.reviewDate.toJSON() : null,
            scheduleDate:
                niazsanjiGroup.scheduleDate != null && niazsanjiGroup.scheduleDate.isValid() ? niazsanjiGroup.scheduleDate.toJSON() : null,
            createDate:
                niazsanjiGroup.createDate != null && niazsanjiGroup.createDate.isValid() ? niazsanjiGroup.createDate.toJSON() : null,
            modifyDate:
                niazsanjiGroup.modifyDate != null && niazsanjiGroup.modifyDate.isValid() ? niazsanjiGroup.modifyDate.toJSON() : null,
            archivedDate:
                niazsanjiGroup.archivedDate != null && niazsanjiGroup.archivedDate.isValid() ? niazsanjiGroup.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.reviewDate = res.body.reviewDate != null ? moment(res.body.reviewDate) : null;
        res.body.scheduleDate = res.body.scheduleDate != null ? moment(res.body.scheduleDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((niazsanjiGroup: INiazsanjiGroupMarineSuffix) => {
            niazsanjiGroup.reviewDate = niazsanjiGroup.reviewDate != null ? moment(niazsanjiGroup.reviewDate) : null;
            niazsanjiGroup.scheduleDate = niazsanjiGroup.scheduleDate != null ? moment(niazsanjiGroup.scheduleDate) : null;
            niazsanjiGroup.createDate = niazsanjiGroup.createDate != null ? moment(niazsanjiGroup.createDate) : null;
            niazsanjiGroup.modifyDate = niazsanjiGroup.modifyDate != null ? moment(niazsanjiGroup.modifyDate) : null;
            niazsanjiGroup.archivedDate = niazsanjiGroup.archivedDate != null ? moment(niazsanjiGroup.archivedDate) : null;
        });
        return res;
    }
}
