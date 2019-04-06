import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';

type EntityResponseType = HttpResponse<IResourceMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IResourceMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ResourceMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/resources';

    constructor(private http: HttpClient) {}

    create(resource: IResourceMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(resource);
        return this.http
            .post<IResourceMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(resource: IResourceMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(resource);
        return this.http
            .put<IResourceMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IResourceMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IResourceMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(resource: IResourceMarineSuffix): IResourceMarineSuffix {
        const copy: IResourceMarineSuffix = Object.assign({}, resource, {
            createDate: resource.createDate != null && resource.createDate.isValid() ? resource.createDate.toJSON() : null,
            modifyDate: resource.modifyDate != null && resource.modifyDate.isValid() ? resource.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((resource: IResourceMarineSuffix) => {
            resource.createDate = resource.createDate != null ? moment(resource.createDate) : null;
            resource.modifyDate = resource.modifyDate != null ? moment(resource.modifyDate) : null;
        });
        return res;
    }
}
