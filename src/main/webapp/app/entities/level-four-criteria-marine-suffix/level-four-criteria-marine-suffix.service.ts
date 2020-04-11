import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';

type EntityResponseType = HttpResponse<ILevelFourCriteriaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelFourCriteriaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelFourCriteriaMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-four-criteria';

    constructor(protected http: HttpClient) {}

    create(levelFourCriteria: ILevelFourCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourCriteria);
        return this.http
            .post<ILevelFourCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelFourCriteria: ILevelFourCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelFourCriteria);
        return this.http
            .put<ILevelFourCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILevelFourCriteriaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelFourCriteriaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelFourCriteria: ILevelFourCriteriaMarineSuffix): ILevelFourCriteriaMarineSuffix {
        const copy: ILevelFourCriteriaMarineSuffix = Object.assign({}, levelFourCriteria, {
            createDate:
                levelFourCriteria.createDate != null && levelFourCriteria.createDate.isValid()
                    ? levelFourCriteria.createDate.toJSON()
                    : null,
            modifyDate:
                levelFourCriteria.modifyDate != null && levelFourCriteria.modifyDate.isValid()
                    ? levelFourCriteria.modifyDate.toJSON()
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
            res.body.forEach((levelFourCriteria: ILevelFourCriteriaMarineSuffix) => {
                levelFourCriteria.createDate = levelFourCriteria.createDate != null ? moment(levelFourCriteria.createDate) : null;
                levelFourCriteria.modifyDate = levelFourCriteria.modifyDate != null ? moment(levelFourCriteria.modifyDate) : null;
            });
        }
        return res;
    }
}
