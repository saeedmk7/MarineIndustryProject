import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';

type EntityResponseType = HttpResponse<IPollScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPollScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PollScoreMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/poll-scores';

    constructor(private http: HttpClient) {}

    create(pollScore: IPollScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pollScore);
        return this.http
            .post<IPollScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pollScore: IPollScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pollScore);
        return this.http
            .put<IPollScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPollScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPollScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(pollScore: IPollScoreMarineSuffix): IPollScoreMarineSuffix {
        const copy: IPollScoreMarineSuffix = Object.assign({}, pollScore, {
            createDate: pollScore.createDate != null && pollScore.createDate.isValid() ? pollScore.createDate.toJSON() : null,
            modifyDate: pollScore.modifyDate != null && pollScore.modifyDate.isValid() ? pollScore.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((pollScore: IPollScoreMarineSuffix) => {
            pollScore.createDate = pollScore.createDate != null ? moment(pollScore.createDate) : null;
            pollScore.modifyDate = pollScore.modifyDate != null ? moment(pollScore.modifyDate) : null;
        });
        return res;
    }
}
