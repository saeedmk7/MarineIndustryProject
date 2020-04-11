import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';

type EntityResponseType = HttpResponse<ILevelThreeScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelThreeScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelThreeScoreMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-three-scores';

    constructor(protected http: HttpClient) {}

    create(levelThreeScore: ILevelThreeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeScore);
        return this.http
            .post<ILevelThreeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelThreeScore: ILevelThreeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeScore);
        return this.http
            .put<ILevelThreeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILevelThreeScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelThreeScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelThreeScore: ILevelThreeScoreMarineSuffix): ILevelThreeScoreMarineSuffix {
        const copy: ILevelThreeScoreMarineSuffix = Object.assign({}, levelThreeScore, {
            createDate:
                levelThreeScore.createDate != null && levelThreeScore.createDate.isValid() ? levelThreeScore.createDate.toJSON() : null,
            modifyDate:
                levelThreeScore.modifyDate != null && levelThreeScore.modifyDate.isValid() ? levelThreeScore.modifyDate.toJSON() : null
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
            res.body.forEach((levelThreeScore: ILevelThreeScoreMarineSuffix) => {
                levelThreeScore.createDate = levelThreeScore.createDate != null ? moment(levelThreeScore.createDate) : null;
                levelThreeScore.modifyDate = levelThreeScore.modifyDate != null ? moment(levelThreeScore.modifyDate) : null;
            });
        }
        return res;
    }
}
