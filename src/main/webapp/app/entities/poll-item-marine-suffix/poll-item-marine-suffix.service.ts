import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';

type EntityResponseType = HttpResponse<IPollItemMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPollItemMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PollItemMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/poll-items';

    constructor(private http: HttpClient) {}

    create(pollItem: IPollItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pollItem);
        return this.http
            .post<IPollItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pollItem: IPollItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pollItem);
        return this.http
            .put<IPollItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPollItemMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPollItemMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(pollItem: IPollItemMarineSuffix): IPollItemMarineSuffix {
        const copy: IPollItemMarineSuffix = Object.assign({}, pollItem, {
            createDate: pollItem.createDate != null && pollItem.createDate.isValid() ? pollItem.createDate.toJSON() : null,
            modifyDate: pollItem.modifyDate != null && pollItem.modifyDate.isValid() ? pollItem.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((pollItem: IPollItemMarineSuffix) => {
            pollItem.createDate = pollItem.createDate != null ? moment(pollItem.createDate) : null;
            pollItem.modifyDate = pollItem.modifyDate != null ? moment(pollItem.modifyDate) : null;
        });
        return res;
    }
}
