import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';

type EntityResponseType = HttpResponse<IGoalMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IGoalMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class GoalMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/goals';

    constructor(private http: HttpClient) {}

    create(goal: IGoalMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goal);
        return this.http
            .post<IGoalMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(goal: IGoalMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goal);
        return this.http
            .put<IGoalMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGoalMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoalMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(goal: IGoalMarineSuffix): IGoalMarineSuffix {
        const copy: IGoalMarineSuffix = Object.assign({}, goal, {
            createDate: goal.createDate != null && goal.createDate.isValid() ? goal.createDate.toJSON() : null,
            modifyDate: goal.modifyDate != null && goal.modifyDate.isValid() ? goal.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((goal: IGoalMarineSuffix) => {
            goal.createDate = goal.createDate != null ? moment(goal.createDate) : null;
            goal.modifyDate = goal.modifyDate != null ? moment(goal.modifyDate) : null;
        });
        return res;
    }
}
