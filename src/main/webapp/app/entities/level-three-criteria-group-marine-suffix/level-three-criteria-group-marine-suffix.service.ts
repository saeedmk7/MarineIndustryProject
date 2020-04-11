import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';

type EntityResponseType = HttpResponse<ILevelThreeCriteriaGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ILevelThreeCriteriaGroupMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class LevelThreeCriteriaGroupMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/level-three-criteria-groups';

    constructor(protected http: HttpClient) {}

    create(levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeCriteriaGroup);
        return this.http
            .post<ILevelThreeCriteriaGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(levelThreeCriteriaGroup);
        return this.http
            .put<ILevelThreeCriteriaGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILevelThreeCriteriaGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILevelThreeCriteriaGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix): ILevelThreeCriteriaGroupMarineSuffix {
        const copy: ILevelThreeCriteriaGroupMarineSuffix = Object.assign({}, levelThreeCriteriaGroup, {
            createDate:
                levelThreeCriteriaGroup.createDate != null && levelThreeCriteriaGroup.createDate.isValid()
                    ? levelThreeCriteriaGroup.createDate.toJSON()
                    : null,
            modifyDate:
                levelThreeCriteriaGroup.modifyDate != null && levelThreeCriteriaGroup.modifyDate.isValid()
                    ? levelThreeCriteriaGroup.modifyDate.toJSON()
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
            res.body.forEach((levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix) => {
                levelThreeCriteriaGroup.createDate =
                    levelThreeCriteriaGroup.createDate != null ? moment(levelThreeCriteriaGroup.createDate) : null;
                levelThreeCriteriaGroup.modifyDate =
                    levelThreeCriteriaGroup.modifyDate != null ? moment(levelThreeCriteriaGroup.modifyDate) : null;
            });
        }
        return res;
    }
}
