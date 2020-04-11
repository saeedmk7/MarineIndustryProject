import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';

type EntityResponseType = HttpResponse<ILevelThreeCriteriaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelThreeCriteriaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelThreeCriteriaMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-three-criteria';

    constructor(protected http: HttpClient) {}

    create(levelThreeCriteria: ILevelThreeCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeCriteria);
        return this.http
            .post<ILevelThreeCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelThreeCriteria: ILevelThreeCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeCriteria);
        return this.http
            .put<ILevelThreeCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILevelThreeCriteriaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelThreeCriteriaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelThreeCriteria: ILevelThreeCriteriaMarineSuffix): ILevelThreeCriteriaMarineSuffix {
        const copy: ILevelThreeCriteriaMarineSuffix = Object.assign({}, levelThreeCriteria, {
            createDate:
                levelThreeCriteria.createDate != null && levelThreeCriteria.createDate.isValid()
                    ? levelThreeCriteria.createDate.toJSON()
                    : null,
            modifyDate:
                levelThreeCriteria.modifyDate != null && levelThreeCriteria.modifyDate.isValid()
                    ? levelThreeCriteria.modifyDate.toJSON()
                    : null
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
            res.body.forEach((levelThreeCriteria: ILevelThreeCriteriaMarineSuffix) => {
                levelThreeCriteria.createDate = levelThreeCriteria.createDate != null ? moment(levelThreeCriteria.createDate) : null;
                levelThreeCriteria.modifyDate = levelThreeCriteria.modifyDate != null ? moment(levelThreeCriteria.modifyDate) : null;
            });
        }
        return res;
    }
}
