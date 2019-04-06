import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';

type EntityResponseType = HttpResponse<IEmploymentTypeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEmploymentTypeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EmploymentTypeMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/employment-types';

    constructor(private http: HttpClient) {}

    create(employmentType: IEmploymentTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(employmentType);
        return this.http
            .post<IEmploymentTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(employmentType: IEmploymentTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(employmentType);
        return this.http
            .put<IEmploymentTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEmploymentTypeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEmploymentTypeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(employmentType: IEmploymentTypeMarineSuffix): IEmploymentTypeMarineSuffix {
        const copy: IEmploymentTypeMarineSuffix = Object.assign({}, employmentType, {
            createDate:
                employmentType.createDate != null && employmentType.createDate.isValid() ? employmentType.createDate.toJSON() : null,
            modifyDate: employmentType.modifyDate != null && employmentType.modifyDate.isValid() ? employmentType.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((employmentType: IEmploymentTypeMarineSuffix) => {
            employmentType.createDate = employmentType.createDate != null ? moment(employmentType.createDate) : null;
            employmentType.modifyDate = employmentType.modifyDate != null ? moment(employmentType.modifyDate) : null;
        });
        return res;
    }
}
