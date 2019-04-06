import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';

type EntityResponseType = HttpResponse<IJobTypeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJobTypeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobTypeMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/job-types';

    constructor(private http: HttpClient) {}

    create(jobType: IJobTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobType);
        return this.http
            .post<IJobTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jobType: IJobTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jobType);
        return this.http
            .put<IJobTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJobTypeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJobTypeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(jobType: IJobTypeMarineSuffix): IJobTypeMarineSuffix {
        const copy: IJobTypeMarineSuffix = Object.assign({}, jobType, {
            createDate: jobType.createDate != null && jobType.createDate.isValid() ? jobType.createDate.toJSON() : null,
            modifyDate: jobType.modifyDate != null && jobType.modifyDate.isValid() ? jobType.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((jobType: IJobTypeMarineSuffix) => {
            jobType.createDate = jobType.createDate != null ? moment(jobType.createDate) : null;
            jobType.modifyDate = jobType.modifyDate != null ? moment(jobType.modifyDate) : null;
        });
        return res;
    }
}
