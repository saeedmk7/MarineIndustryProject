import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';

type EntityResponseType = HttpResponse<IReportGeneratorMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IReportGeneratorMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ReportGeneratorMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/report-generators';

    constructor(protected http: HttpClient) {}

    create(reportGenerator: IReportGeneratorMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reportGenerator);
        return this.http
            .post<IReportGeneratorMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(reportGenerator: IReportGeneratorMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reportGenerator);
        return this.http
            .put<IReportGeneratorMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReportGeneratorMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    findByGuid(guid: string): Observable<EntityResponseType> {
        return this.http
            .get<IReportGeneratorMarineSuffix>(`${this.resourceUrl}/byGuid/${guid}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReportGeneratorMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(reportGenerator: IReportGeneratorMarineSuffix): IReportGeneratorMarineSuffix {
        const copy: IReportGeneratorMarineSuffix = Object.assign({}, reportGenerator, {
            createDate:
                reportGenerator.createDate != null && reportGenerator.createDate.isValid() ? reportGenerator.createDate.toJSON() : null,
            modifyDate:
                reportGenerator.modifyDate != null && reportGenerator.modifyDate.isValid() ? reportGenerator.modifyDate.toJSON() : null
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
            res.body.forEach((reportGenerator: IReportGeneratorMarineSuffix) => {
                reportGenerator.createDate = reportGenerator.createDate != null ? moment(reportGenerator.createDate) : null;
                reportGenerator.modifyDate = reportGenerator.modifyDate != null ? moment(reportGenerator.modifyDate) : null;
            });
        }
        return res;
    }
}
