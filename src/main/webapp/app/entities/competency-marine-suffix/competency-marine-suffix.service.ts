import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';

type EntityResponseType = HttpResponse<ICompetencyMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ICompetencyMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class CompetencyMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/competencies';

    constructor(protected http: HttpClient) {}

    create(competency: ICompetencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(competency);
        return this.http
            .post<ICompetencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(competency: ICompetencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(competency);
        return this.http
            .put<ICompetencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICompetencyMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICompetencyMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(competency: ICompetencyMarineSuffix): ICompetencyMarineSuffix {
        const copy: ICompetencyMarineSuffix = Object.assign({}, competency, {
            createDate: competency.createDate != null && competency.createDate.isValid() ? competency.createDate.toJSON() : null,
            modifyDate: competency.modifyDate != null && competency.modifyDate.isValid() ? competency.modifyDate.toJSON() : null
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
            res.body.forEach((competency: ICompetencyMarineSuffix) => {
                competency.createDate = competency.createDate != null ? moment(competency.createDate) : null;
                competency.modifyDate = competency.modifyDate != null ? moment(competency.modifyDate) : null;
            });
        }
        return res;
    }
}
