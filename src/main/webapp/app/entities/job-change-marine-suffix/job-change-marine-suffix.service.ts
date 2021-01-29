import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';

type EntityResponseType = HttpResponse<IJobChangeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJobChangeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobChangeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/job-changes';

    constructor(protected http: HttpClient) {}

    create(jobChange: IJobChangeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobChange);
        return this.http
            .post<IJobChangeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jobChange: IJobChangeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobChange);
        return this.http
            .put<IJobChangeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJobChangeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJobChangeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(jobChange: IJobChangeMarineSuffix): IJobChangeMarineSuffix {
        const copy: IJobChangeMarineSuffix = Object.assign({}, jobChange, {
            createDate: jobChange.createDate != null && jobChange.createDate.isValid() ? jobChange.createDate.toJSON() : null,
            modifyDate: jobChange.modifyDate != null && jobChange.modifyDate.isValid() ? jobChange.modifyDate.toJSON() : null
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
            res.body.forEach((jobChange: IJobChangeMarineSuffix) => {
                jobChange.createDate = jobChange.createDate != null ? moment(jobChange.createDate) : null;
                jobChange.modifyDate = jobChange.modifyDate != null ? moment(jobChange.modifyDate) : null;
            });
        }
        return res;
    }
}
