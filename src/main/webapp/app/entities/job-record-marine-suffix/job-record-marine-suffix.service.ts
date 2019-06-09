import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';

type EntityResponseType = HttpResponse<IJobRecordMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJobRecordMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobRecordMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/job-records';

    constructor(protected http: HttpClient) {}

    create(jobRecord: IJobRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobRecord);
        return this.http
            .post<IJobRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jobRecord: IJobRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobRecord);
        return this.http
            .put<IJobRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJobRecordMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJobRecordMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(jobRecord: IJobRecordMarineSuffix): IJobRecordMarineSuffix {
        const copy: IJobRecordMarineSuffix = Object.assign({}, jobRecord, {
            createDate: jobRecord.createDate != null && jobRecord.createDate.isValid() ? jobRecord.createDate.toJSON() : null,
            modifyDate: jobRecord.modifyDate != null && jobRecord.modifyDate.isValid() ? jobRecord.modifyDate.toJSON() : null
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
            res.body.forEach((jobRecord: IJobRecordMarineSuffix) => {
                jobRecord.createDate = jobRecord.createDate != null ? moment(jobRecord.createDate) : null;
                jobRecord.modifyDate = jobRecord.modifyDate != null ? moment(jobRecord.modifyDate) : null;
            });
        }
        return res;
    }
}
