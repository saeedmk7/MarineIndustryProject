import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';

type EntityResponseType = HttpResponse<ISubTaskMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISubTaskMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SubTaskMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/sub-tasks';

    constructor(private http: HttpClient) {}

    create(subTask: ISubTaskMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(subTask);
        return this.http
            .post<ISubTaskMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(subTask: ISubTaskMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(subTask);
        return this.http
            .put<ISubTaskMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISubTaskMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISubTaskMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(subTask: ISubTaskMarineSuffix): ISubTaskMarineSuffix {
        const copy: ISubTaskMarineSuffix = Object.assign({}, subTask, {
            createDate: subTask.createDate != null && subTask.createDate.isValid() ? subTask.createDate.toJSON() : null,
            modifyDate: subTask.modifyDate != null && subTask.modifyDate.isValid() ? subTask.modifyDate.toJSON() : null,
            archivedDate: subTask.archivedDate != null && subTask.archivedDate.isValid() ? subTask.archivedDate.toJSON() : null
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
        res.body.forEach((subTask: ISubTaskMarineSuffix) => {
            subTask.createDate = subTask.createDate != null ? moment(subTask.createDate) : null;
            subTask.modifyDate = subTask.modifyDate != null ? moment(subTask.modifyDate) : null;
            subTask.archivedDate = subTask.archivedDate != null ? moment(subTask.archivedDate) : null;
        });
        return res;
    }
}
