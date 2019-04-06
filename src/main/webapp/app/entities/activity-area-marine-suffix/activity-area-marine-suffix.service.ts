import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';

type EntityResponseType = HttpResponse<IActivityAreaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IActivityAreaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ActivityAreaMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/activity-areas';

    constructor(private http: HttpClient) {}

    create(activityArea: IActivityAreaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(activityArea);
        return this.http
            .post<IActivityAreaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(activityArea: IActivityAreaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(activityArea);
        return this.http
            .put<IActivityAreaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IActivityAreaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IActivityAreaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(activityArea: IActivityAreaMarineSuffix): IActivityAreaMarineSuffix {
        const copy: IActivityAreaMarineSuffix = Object.assign({}, activityArea, {
            createDate: activityArea.createDate != null && activityArea.createDate.isValid() ? activityArea.createDate.toJSON() : null,
            modifyDate: activityArea.modifyDate != null && activityArea.modifyDate.isValid() ? activityArea.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((activityArea: IActivityAreaMarineSuffix) => {
            activityArea.createDate = activityArea.createDate != null ? moment(activityArea.createDate) : null;
            activityArea.modifyDate = activityArea.modifyDate != null ? moment(activityArea.modifyDate) : null;
        });
        return res;
    }
}
