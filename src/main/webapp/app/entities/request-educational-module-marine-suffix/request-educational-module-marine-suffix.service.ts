import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';

type EntityResponseType = HttpResponse<IRequestEducationalModuleMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRequestEducationalModuleMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RequestEducationalModuleMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/request-educational-modules';

    constructor(private http: HttpClient) {}

    create(requestEducationalModule: IRequestEducationalModuleMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestEducationalModule);
        return this.http
            .post<IRequestEducationalModuleMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(requestEducationalModule: IRequestEducationalModuleMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestEducationalModule);
        return this.http
            .put<IRequestEducationalModuleMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRequestEducationalModuleMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRequestEducationalModuleMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(requestEducationalModule: IRequestEducationalModuleMarineSuffix): IRequestEducationalModuleMarineSuffix {
        const copy: IRequestEducationalModuleMarineSuffix = Object.assign({}, requestEducationalModule, {
            timePassed:
                requestEducationalModule.timePassed != null && requestEducationalModule.timePassed.isValid()
                    ? requestEducationalModule.timePassed.toJSON()
                    : null,
            credit:
                requestEducationalModule.credit != null && requestEducationalModule.credit.isValid()
                    ? requestEducationalModule.credit.toJSON()
                    : null,
            createDate:
                requestEducationalModule.createDate != null && requestEducationalModule.createDate.isValid()
                    ? requestEducationalModule.createDate.toJSON()
                    : null,
            modifyDate:
                requestEducationalModule.modifyDate != null && requestEducationalModule.modifyDate.isValid()
                    ? requestEducationalModule.modifyDate.toJSON()
                    : null,
            archivedDate:
                requestEducationalModule.archivedDate != null && requestEducationalModule.archivedDate.isValid()
                    ? requestEducationalModule.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timePassed = res.body.timePassed != null ? moment(res.body.timePassed) : null;
        res.body.credit = res.body.credit != null ? moment(res.body.credit) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.fullTitle = (res.body.id ? res.body.id : "") + " - " + (res.body.title ? res.body.title : "");
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((requestEducationalModule: IRequestEducationalModuleMarineSuffix) => {
            requestEducationalModule.timePassed =
                requestEducationalModule.timePassed != null ? moment(requestEducationalModule.timePassed) : null;
            requestEducationalModule.credit = requestEducationalModule.credit != null ? moment(requestEducationalModule.credit) : null;
            requestEducationalModule.createDate =
                requestEducationalModule.createDate != null ? moment(requestEducationalModule.createDate) : null;
            requestEducationalModule.modifyDate =
                requestEducationalModule.modifyDate != null ? moment(requestEducationalModule.modifyDate) : null;
            requestEducationalModule.archivedDate =
                requestEducationalModule.archivedDate != null ? moment(requestEducationalModule.archivedDate) : null;
            requestEducationalModule.fullTitle =
                requestEducationalModule.id + " - " + requestEducationalModule.title ? requestEducationalModule.title : "";
        });
        return res;
    }
}
