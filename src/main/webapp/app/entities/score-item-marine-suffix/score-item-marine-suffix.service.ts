import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';

type EntityResponseType = HttpResponse<IScoreItemMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IScoreItemMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ScoreItemMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/score-items';

    constructor(private http: HttpClient) {}

    create(scoreItem: IScoreItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(scoreItem);
        return this.http
            .post<IScoreItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(scoreItem: IScoreItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(scoreItem);
        return this.http
            .put<IScoreItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IScoreItemMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IScoreItemMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(scoreItem: IScoreItemMarineSuffix): IScoreItemMarineSuffix {
        const copy: IScoreItemMarineSuffix = Object.assign({}, scoreItem, {
            createDate: scoreItem.createDate != null && scoreItem.createDate.isValid() ? scoreItem.createDate.toJSON() : null,
            modifyDate: scoreItem.modifyDate != null && scoreItem.modifyDate.isValid() ? scoreItem.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((scoreItem: IScoreItemMarineSuffix) => {
            scoreItem.createDate = scoreItem.createDate != null ? moment(scoreItem.createDate) : null;
            scoreItem.modifyDate = scoreItem.modifyDate != null ? moment(scoreItem.modifyDate) : null;
        });
        return res;
    }
}
