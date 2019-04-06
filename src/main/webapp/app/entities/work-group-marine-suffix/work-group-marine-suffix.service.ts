import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';

type EntityResponseType = HttpResponse<IWorkGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IWorkGroupMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class WorkGroupMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/work-groups';

    constructor(private http: HttpClient) {}

    create(workGroup: IWorkGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workGroup);
        return this.http
            .post<IWorkGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(workGroup: IWorkGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(workGroup);
        return this.http
            .put<IWorkGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IWorkGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IWorkGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(workGroup: IWorkGroupMarineSuffix): IWorkGroupMarineSuffix {
        const copy: IWorkGroupMarineSuffix = Object.assign({}, workGroup, {
            createDate: workGroup.createDate != null && workGroup.createDate.isValid() ? workGroup.createDate.toJSON() : null,
            modifyDate: workGroup.modifyDate != null && workGroup.modifyDate.isValid() ? workGroup.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((workGroup: IWorkGroupMarineSuffix) => {
            workGroup.createDate = workGroup.createDate != null ? moment(workGroup.createDate) : null;
            workGroup.modifyDate = workGroup.modifyDate != null ? moment(workGroup.modifyDate) : null;
        });
        return res;
    }
}
