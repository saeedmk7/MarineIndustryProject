import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';

type EntityResponseType = HttpResponse<ILevelFourScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelFourScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelFourScoreMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-four-scores';

    constructor(protected http: HttpClient) {}

    create(levelFourScore: ILevelFourScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourScore);
        return this.http
            .post<ILevelFourScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelFourScore: ILevelFourScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourScore);
        return this.http
            .put<ILevelFourScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILevelFourScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelFourScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelFourScore: ILevelFourScoreMarineSuffix): ILevelFourScoreMarineSuffix {
        const copy: ILevelFourScoreMarineSuffix = Object.assign({}, levelFourScore, {
            createDate:
                levelFourScore.createDate != null && levelFourScore.createDate.isValid() ? levelFourScore.createDate.toJSON() : null,
            modifyDate: levelFourScore.modifyDate != null && levelFourScore.modifyDate.isValid() ? levelFourScore.modifyDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((levelFourScore: ILevelFourScoreMarineSuffix) => {
                levelFourScore.createDate = levelFourScore.createDate != null ? moment(levelFourScore.createDate) : null;
                levelFourScore.modifyDate = levelFourScore.modifyDate != null ? moment(levelFourScore.modifyDate) : null;
            });
        }
        return res;
    }
}
