import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';

type EntityResponseType = HttpResponse<IWorkIndustryMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IWorkIndustryMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class WorkIndustryMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/work-industries';

    constructor(private http: HttpClient) {}

    create(workIndustry: IWorkIndustryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workIndustry);
        return this.http
            .post<IWorkIndustryMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(workIndustry: IWorkIndustryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workIndustry);
        return this.http
            .put<IWorkIndustryMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IWorkIndustryMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IWorkIndustryMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(workIndustry: IWorkIndustryMarineSuffix): IWorkIndustryMarineSuffix {
        const copy: IWorkIndustryMarineSuffix = Object.assign({}, workIndustry, {
            createDate: workIndustry.createDate != null && workIndustry.createDate.isValid() ? workIndustry.createDate.toJSON() : null,
            modifyDate: workIndustry.modifyDate != null && workIndustry.modifyDate.isValid() ? workIndustry.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((workIndustry: IWorkIndustryMarineSuffix) => {
            workIndustry.createDate = workIndustry.createDate != null ? moment(workIndustry.createDate) : null;
            workIndustry.modifyDate = workIndustry.modifyDate != null ? moment(workIndustry.modifyDate) : null;
        });
        return res;
    }
}
