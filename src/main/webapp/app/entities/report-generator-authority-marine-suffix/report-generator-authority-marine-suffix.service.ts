import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<IReportGeneratorAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IReportGeneratorAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ReportGeneratorAuthorityMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/report-generator-authorities';

    constructor(protected http: HttpClient) {}

    create(reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reportGeneratorAuthority);
        return this.http
            .post<IReportGeneratorAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reportGeneratorAuthority);
        return this.http
            .put<IReportGeneratorAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReportGeneratorAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReportGeneratorAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix
    ): IReportGeneratorAuthorityMarineSuffix {
        const copy: IReportGeneratorAuthorityMarineSuffix = Object.assign({}, reportGeneratorAuthority, {
            createDate:
                reportGeneratorAuthority.createDate != null && reportGeneratorAuthority.createDate.isValid()
                    ? reportGeneratorAuthority.createDate.toJSON()
                    : null,
            modifyDate:
                reportGeneratorAuthority.modifyDate != null && reportGeneratorAuthority.modifyDate.isValid()
                    ? reportGeneratorAuthority.modifyDate.toJSON()
                    : null
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
            res.body.forEach((reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix) => {
                reportGeneratorAuthority.createDate =
                    reportGeneratorAuthority.createDate != null ? moment(reportGeneratorAuthority.createDate) : null;
                reportGeneratorAuthority.modifyDate =
                    reportGeneratorAuthority.modifyDate != null ? moment(reportGeneratorAuthority.modifyDate) : null;
            });
        }
        return res;
    }
}
