import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeachingRecordMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeachingRecordMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeachingRecordMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/teaching-records';

    constructor(protected http: HttpClient) {}

    create(teachingRecord: ITeachingRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachingRecord);
        return this.http
            .post<ITeachingRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teachingRecord: ITeachingRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachingRecord);
        return this.http
            .put<ITeachingRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeachingRecordMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeachingRecordMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(teachingRecord: ITeachingRecordMarineSuffix): ITeachingRecordMarineSuffix {
        const copy: ITeachingRecordMarineSuffix = Object.assign({}, teachingRecord, {
            createDate:
                teachingRecord.createDate != null && teachingRecord.createDate.isValid() ? teachingRecord.createDate.toJSON() : null,
            modifyDate: teachingRecord.modifyDate != null && teachingRecord.modifyDate.isValid() ? teachingRecord.modifyDate.toJSON() : null
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
            res.body.forEach((teachingRecord: ITeachingRecordMarineSuffix) => {
                teachingRecord.createDate = teachingRecord.createDate != null ? moment(teachingRecord.createDate) : null;
                teachingRecord.modifyDate = teachingRecord.modifyDate != null ? moment(teachingRecord.modifyDate) : null;
            });
        }
        return res;
    }
}
