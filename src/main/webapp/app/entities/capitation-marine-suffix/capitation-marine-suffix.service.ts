import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';
import { CapitationReport, ICapitationReport } from 'app/shared/model/custom/capitationReportModels/capitation-report';

type EntityResponseType = HttpResponse<ICapitationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ICapitationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class CapitationMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/capitations';

    constructor(protected http: HttpClient) {}

    create(capitation: ICapitationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(capitation);
        return this.http
            .post<ICapitationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(capitation: ICapitationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(capitation);
        return this.http
            .put<ICapitationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICapitationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICapitationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    getCapitationReport(niazsanjiYear: number, organizationChartId: number): Observable<HttpResponse<ICapitationReport>> {
        const url = `${this.resourceUrl}/getCapitationReport/${niazsanjiYear}/${organizationChartId}`;
        return this.http.get<ICapitationReport>(url, { observe: 'response' }).pipe(map((res: HttpResponse<ICapitationReport>) => res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(capitation: ICapitationMarineSuffix): ICapitationMarineSuffix {
        const copy: ICapitationMarineSuffix = Object.assign({}, capitation, {
            createDate: capitation.createDate != null && capitation.createDate.isValid() ? capitation.createDate.toJSON() : null,
            modifyDate: capitation.modifyDate != null && capitation.modifyDate.isValid() ? capitation.modifyDate.toJSON() : null
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
            res.body.forEach((capitation: ICapitationMarineSuffix) => {
                capitation.createDate = capitation.createDate != null ? moment(capitation.createDate) : null;
                capitation.modifyDate = capitation.modifyDate != null ? moment(capitation.modifyDate) : null;
            });
        }
        return res;
    }
}
