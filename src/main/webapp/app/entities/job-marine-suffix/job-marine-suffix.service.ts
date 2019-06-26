import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';

type EntityResponseType = HttpResponse<IJobMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IJobMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/jobs';

    constructor(private http: HttpClient) {}

    create(job: IJobMarineSuffix): Observable<EntityResponseType> {

        const copy = this.convertDateFromClient(job);
        return this.http
            .post<IJobMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    aggregateJob(job: IJobMarineSuffix): Observable<EntityResponseType> {

        //const copy = this.convertDateFromClient(job);
        const requestUrl = this.resourceUrl + "/jobAggregation";
        return this.http
            .post<IJobMarineSuffix>(requestUrl, job, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(job: IJobMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(job);
        return this.http
            .put<IJobMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJobMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {

        if(req) {
            const options = createRequestOption(req);
            return this.http
                .get<IJobMarineSuffix[]>(this.resourceUrl, {params: options, observe: 'response'})
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
        }
        else{
            let url = this.resourceUrl + "/all";
            return this.http
                .get<IJobMarineSuffix[]>(url, {observe: 'response'})
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
        }
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(job: IJobMarineSuffix): IJobMarineSuffix {
        const copy: IJobMarineSuffix = Object.assign({}, job, {
            createDate: job.createDate != null && job.createDate.isValid() ? job.createDate.toJSON() : null,
            modifyDate: job.modifyDate != null && job.modifyDate.isValid() ? job.modifyDate.toJSON() : null,
            archivedDate: job.archivedDate != null && job.archivedDate.isValid() ? job.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.fullTitle = (res.body.jobCode ? res.body.jobCode : "") + " - " + (res.body.title ? res.body.title : "");
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((job: IJobMarineSuffix) => {
            job.createDate = job.createDate != null ? moment(job.createDate) : null;
            job.modifyDate = job.modifyDate != null ? moment(job.modifyDate) : null;
            job.archivedDate = job.archivedDate != null ? moment(job.archivedDate) : null;
            job.fullTitle = (job.title ? job.title : "") + " - " + (job.jobCode ? job.jobCode : "");
        });
        return res;
    }
}
