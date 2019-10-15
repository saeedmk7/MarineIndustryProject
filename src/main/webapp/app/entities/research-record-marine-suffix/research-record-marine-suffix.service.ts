import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';

type EntityResponseType = HttpResponse<IResearchRecordMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IResearchRecordMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ResearchRecordMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/research-records';

    constructor(protected http: HttpClient) {}

    create(researchRecord: IResearchRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(researchRecord);
        return this.http
            .post<IResearchRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(researchRecord: IResearchRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(researchRecord);
        return this.http
            .put<IResearchRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IResearchRecordMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IResearchRecordMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(researchRecord: IResearchRecordMarineSuffix): IResearchRecordMarineSuffix {
        const copy: IResearchRecordMarineSuffix = Object.assign({}, researchRecord, {
            createDate:
                researchRecord.createDate != null && researchRecord.createDate.isValid() ? researchRecord.createDate.toJSON() : null,
            modifyDate: researchRecord.modifyDate != null && researchRecord.modifyDate.isValid() ? researchRecord.modifyDate.toJSON() : null
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
            res.body.forEach((researchRecord: IResearchRecordMarineSuffix) => {
                researchRecord.createDate = researchRecord.createDate != null ? moment(researchRecord.createDate) : null;
                researchRecord.modifyDate = researchRecord.modifyDate != null ? moment(researchRecord.modifyDate) : null;
            });
        }
        return res;
    }
}
