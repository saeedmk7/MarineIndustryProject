import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';

type EntityResponseType = HttpResponse<IFixCompetencyDeficiencyMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFixCompetencyDeficiencyMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FixCompetencyDeficiencyMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/fix-competency-deficiencies';

    constructor(protected http: HttpClient) {}

    create(fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fixCompetencyDeficiency);
        return this.http
            .post<IFixCompetencyDeficiencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fixCompetencyDeficiency);
        return this.http
            .put<IFixCompetencyDeficiencyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFixCompetencyDeficiencyMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFixCompetencyDeficiencyMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix): IFixCompetencyDeficiencyMarineSuffix {
        const copy: IFixCompetencyDeficiencyMarineSuffix = Object.assign({}, fixCompetencyDeficiency, {
            createDate:
                fixCompetencyDeficiency.createDate != null && fixCompetencyDeficiency.createDate.isValid()
                    ? fixCompetencyDeficiency.createDate.toJSON()
                    : null,
            modifyDate:
                fixCompetencyDeficiency.modifyDate != null && fixCompetencyDeficiency.modifyDate.isValid()
                    ? fixCompetencyDeficiency.modifyDate.toJSON()
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
            res.body.forEach((fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix) => {
                fixCompetencyDeficiency.createDate =
                    fixCompetencyDeficiency.createDate != null ? moment(fixCompetencyDeficiency.createDate) : null;
                fixCompetencyDeficiency.modifyDate =
                    fixCompetencyDeficiency.modifyDate != null ? moment(fixCompetencyDeficiency.modifyDate) : null;
            });
        }
        return res;
    }
}
