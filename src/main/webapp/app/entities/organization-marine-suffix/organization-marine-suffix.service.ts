import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';

type EntityResponseType = HttpResponse<IOrganizationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IOrganizationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/organizations';

    constructor(private http: HttpClient) {}

    create(organization: IOrganizationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(organization);
        return this.http
            .post<IOrganizationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(organization: IOrganizationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(organization);
        return this.http
            .put<IOrganizationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOrganizationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOrganizationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(organization: IOrganizationMarineSuffix): IOrganizationMarineSuffix {
        const copy: IOrganizationMarineSuffix = Object.assign({}, organization, {
            createDate: organization.createDate != null && organization.createDate.isValid() ? organization.createDate.toJSON() : null,
            modifyDate: organization.modifyDate != null && organization.modifyDate.isValid() ? organization.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((organization: IOrganizationMarineSuffix) => {
            organization.createDate = organization.createDate != null ? moment(organization.createDate) : null;
            organization.modifyDate = organization.modifyDate != null ? moment(organization.modifyDate) : null;
        });
        return res;
    }
}
