import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';

type EntityResponseType = HttpResponse<IMainTaskMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMainTaskMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MainTaskMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/main-tasks';

    constructor(private http: HttpClient) {}

    create(mainTask: IMainTaskMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mainTask);
        return this.http
            .post<IMainTaskMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mainTask: IMainTaskMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mainTask);
        return this.http
            .put<IMainTaskMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMainTaskMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMainTaskMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(mainTask: IMainTaskMarineSuffix): IMainTaskMarineSuffix {
        const copy: IMainTaskMarineSuffix = Object.assign({}, mainTask, {
            createDate: mainTask.createDate != null && mainTask.createDate.isValid() ? mainTask.createDate.toJSON() : null,
            modifyDate: mainTask.modifyDate != null && mainTask.modifyDate.isValid() ? mainTask.modifyDate.toJSON() : null,
            archivedDate: mainTask.archivedDate != null && mainTask.archivedDate.isValid() ? mainTask.archivedDate.toJSON() : null
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
        res.body.forEach((mainTask: IMainTaskMarineSuffix) => {
            mainTask.createDate = mainTask.createDate != null ? moment(mainTask.createDate) : null;
            mainTask.modifyDate = mainTask.modifyDate != null ? moment(mainTask.modifyDate) : null;
            mainTask.archivedDate = mainTask.archivedDate != null ? moment(mainTask.archivedDate) : null;
        });
        return res;
    }
}
