import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';

type EntityResponseType = HttpResponse<IPriorityCriteriaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPriorityCriteriaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PriorityCriteriaMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/priority-criteria';

    constructor(protected http: HttpClient) {}

    create(priorityCriteria: IPriorityCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(priorityCriteria);
        return this.http
            .post<IPriorityCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(priorityCriteria: IPriorityCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(priorityCriteria);
        return this.http
            .put<IPriorityCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPriorityCriteriaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPriorityCriteriaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(priorityCriteria: IPriorityCriteriaMarineSuffix): IPriorityCriteriaMarineSuffix {
        const copy: IPriorityCriteriaMarineSuffix = Object.assign({}, priorityCriteria, {
            createDate:
                priorityCriteria.createDate != null && priorityCriteria.createDate.isValid() ? priorityCriteria.createDate.toJSON() : null,
            modifyDate:
                priorityCriteria.modifyDate != null && priorityCriteria.modifyDate.isValid() ? priorityCriteria.modifyDate.toJSON() : null
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
            res.body.forEach((priorityCriteria: IPriorityCriteriaMarineSuffix) => {
                priorityCriteria.createDate = priorityCriteria.createDate != null ? moment(priorityCriteria.createDate) : null;
                priorityCriteria.modifyDate = priorityCriteria.modifyDate != null ? moment(priorityCriteria.modifyDate) : null;
            });
        }
        return res;
    }
}
