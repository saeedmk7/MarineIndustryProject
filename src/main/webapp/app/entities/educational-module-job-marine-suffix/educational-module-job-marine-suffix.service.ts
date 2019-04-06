import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalModuleJobMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalModuleJobMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalModuleJobMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/educational-module-jobs';

    constructor(private http: HttpClient) {}

    create(educationalModuleJob: IEducationalModuleJobMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalModuleJob);
        return this.http
            .post<IEducationalModuleJobMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    createBulk(educationalModuleJobs: IEducationalModuleJobMarineSuffix[]): Observable<EntityResponseType> {
        return this.http
            .post<IEducationalModuleJobMarineSuffix>(this.resourceUrl, educationalModuleJobs, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalModuleJob: IEducationalModuleJobMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalModuleJob);
        return this.http
            .put<IEducationalModuleJobMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalModuleJobMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalModuleJobMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(educationalModuleJob: IEducationalModuleJobMarineSuffix): IEducationalModuleJobMarineSuffix {
        const copy: IEducationalModuleJobMarineSuffix = Object.assign({}, educationalModuleJob, {
            createDate:
                educationalModuleJob.createDate != null && educationalModuleJob.createDate.isValid()
                    ? educationalModuleJob.createDate.toJSON()
                    : null,
            modifyDate:
                educationalModuleJob.modifyDate != null && educationalModuleJob.modifyDate.isValid()
                    ? educationalModuleJob.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.first3JobCode = res.body.jobCode != null ? res.body.jobCode.substring(0,3) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((educationalModuleJob: IEducationalModuleJobMarineSuffix) => {
            educationalModuleJob.createDate = educationalModuleJob.createDate != null ? moment(educationalModuleJob.createDate) : null;
            educationalModuleJob.modifyDate = educationalModuleJob.modifyDate != null ? moment(educationalModuleJob.modifyDate) : null;
            educationalModuleJob.first3JobCode = educationalModuleJob.jobCode != null ? educationalModuleJob.jobCode.substring(0,3) : null;
        });
        return res;
    }
}
