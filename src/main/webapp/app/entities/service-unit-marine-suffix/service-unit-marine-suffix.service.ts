import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';

type EntityResponseType = HttpResponse<IServiceUnitMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IServiceUnitMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ServiceUnitMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/service-units';

    constructor(private http: HttpClient) {}

    create(serviceUnit: IServiceUnitMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(serviceUnit);
        return this.http
            .post<IServiceUnitMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(serviceUnit: IServiceUnitMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(serviceUnit);
        return this.http
            .put<IServiceUnitMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IServiceUnitMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IServiceUnitMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(serviceUnit: IServiceUnitMarineSuffix): IServiceUnitMarineSuffix {
        const copy: IServiceUnitMarineSuffix = Object.assign({}, serviceUnit, {
            createDate: serviceUnit.createDate != null && serviceUnit.createDate.isValid() ? serviceUnit.createDate.toJSON() : null,
            modifyDate: serviceUnit.modifyDate != null && serviceUnit.modifyDate.isValid() ? serviceUnit.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((serviceUnit: IServiceUnitMarineSuffix) => {
            serviceUnit.createDate = serviceUnit.createDate != null ? moment(serviceUnit.createDate) : null;
            serviceUnit.modifyDate = serviceUnit.modifyDate != null ? moment(serviceUnit.modifyDate) : null;
        });
        return res;
    }
}
