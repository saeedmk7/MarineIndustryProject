import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';

type EntityResponseType = HttpResponse<IWorkUnitMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IWorkUnitMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class WorkUnitMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/work-units';

    constructor(private http: HttpClient) {}

    create(workUnit: IWorkUnitMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workUnit);
        return this.http
            .post<IWorkUnitMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(workUnit: IWorkUnitMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workUnit);
        return this.http
            .put<IWorkUnitMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IWorkUnitMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IWorkUnitMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(workUnit: IWorkUnitMarineSuffix): IWorkUnitMarineSuffix {
        const copy: IWorkUnitMarineSuffix = Object.assign({}, workUnit, {
            createDate: workUnit.createDate != null && workUnit.createDate.isValid() ? workUnit.createDate.toJSON() : null,
            modifyDate: workUnit.modifyDate != null && workUnit.modifyDate.isValid() ? workUnit.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((workUnit: IWorkUnitMarineSuffix) => {
            workUnit.createDate = workUnit.createDate != null ? moment(workUnit.createDate) : null;
            workUnit.modifyDate = workUnit.modifyDate != null ? moment(workUnit.modifyDate) : null;
        });
        return res;
    }
}
