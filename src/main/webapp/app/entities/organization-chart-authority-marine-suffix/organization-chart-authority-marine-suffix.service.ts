import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<IOrganizationChartAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IOrganizationChartAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationChartAuthorityMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/organization-chart-authorities';

    constructor(private http: HttpClient) {}

    create(organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(organizationChartAuthority);
        return this.http
            .post<IOrganizationChartAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(organizationChartAuthority);
        return this.http
            .put<IOrganizationChartAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOrganizationChartAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOrganizationChartAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(
        organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix
    ): IOrganizationChartAuthorityMarineSuffix {
        const copy: IOrganizationChartAuthorityMarineSuffix = Object.assign({}, organizationChartAuthority, {
            createDate:
                organizationChartAuthority.createDate != null && organizationChartAuthority.createDate.isValid()
                    ? organizationChartAuthority.createDate.toJSON()
                    : null,
            modifyDate:
                organizationChartAuthority.modifyDate != null && organizationChartAuthority.modifyDate.isValid()
                    ? organizationChartAuthority.modifyDate.toJSON()
                    : null,
            archivedDate:
                organizationChartAuthority.archivedDate != null && organizationChartAuthority.archivedDate.isValid()
                    ? organizationChartAuthority.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix) => {
            organizationChartAuthority.createDate =
                organizationChartAuthority.createDate != null ? moment(organizationChartAuthority.createDate) : null;
            organizationChartAuthority.modifyDate =
                organizationChartAuthority.modifyDate != null ? moment(organizationChartAuthority.modifyDate) : null;
            organizationChartAuthority.archivedDate =
                organizationChartAuthority.archivedDate != null ? moment(organizationChartAuthority.archivedDate) : null;
        });
        return res;
    }
}
