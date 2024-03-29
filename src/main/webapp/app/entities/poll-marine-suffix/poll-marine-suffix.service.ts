import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';

type EntityResponseType = HttpResponse<IPollMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPollMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PollMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/polls';

    constructor(private http: HttpClient) {}

    create(poll: IPollMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(poll);
        return this.http
            .post<IPollMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(poll: IPollMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(poll);
        return this.http
            .put<IPollMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPollMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPollMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(poll: IPollMarineSuffix): IPollMarineSuffix {
        const copy: IPollMarineSuffix = Object.assign({}, poll, {
            createDate: poll.createDate != null && poll.createDate.isValid() ? poll.createDate.toJSON() : null,
            modifyDate: poll.modifyDate != null && poll.modifyDate.isValid() ? poll.modifyDate.toJSON() : null,
            archivedDate: poll.archivedDate != null && poll.archivedDate.isValid() ? poll.archivedDate.toJSON() : null
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
        res.body.forEach((poll: IPollMarineSuffix) => {
            poll.createDate = poll.createDate != null ? moment(poll.createDate) : null;
            poll.modifyDate = poll.modifyDate != null ? moment(poll.modifyDate) : null;
            poll.archivedDate = poll.archivedDate != null ? moment(poll.archivedDate) : null;
        });
        return res;
    }
}
