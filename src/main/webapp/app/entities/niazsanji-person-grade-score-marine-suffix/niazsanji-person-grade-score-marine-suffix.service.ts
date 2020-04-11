import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';

type EntityResponseType = HttpResponse<INiazsanjiPersonGradeScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiPersonGradeScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiPersonGradeScoreMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-person-grade-scores';

    constructor(protected http: HttpClient) {}

    create(niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiPersonGradeScore);
        return this.http
            .post<INiazsanjiPersonGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiPersonGradeScore);
        return this.http
            .put<INiazsanjiPersonGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiPersonGradeScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiPersonGradeScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix
    ): INiazsanjiPersonGradeScoreMarineSuffix {
        const copy: INiazsanjiPersonGradeScoreMarineSuffix = Object.assign({}, niazsanjiPersonGradeScore, {
            createDate:
                niazsanjiPersonGradeScore.createDate != null && niazsanjiPersonGradeScore.createDate.isValid()
                    ? niazsanjiPersonGradeScore.createDate.toJSON()
                    : null,
            modifyDate:
                niazsanjiPersonGradeScore.modifyDate != null && niazsanjiPersonGradeScore.modifyDate.isValid()
                    ? niazsanjiPersonGradeScore.modifyDate.toJSON()
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
            res.body.forEach((niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix) => {
                niazsanjiPersonGradeScore.createDate =
                    niazsanjiPersonGradeScore.createDate != null ? moment(niazsanjiPersonGradeScore.createDate) : null;
                niazsanjiPersonGradeScore.modifyDate =
                    niazsanjiPersonGradeScore.modifyDate != null ? moment(niazsanjiPersonGradeScore.modifyDate) : null;
            });
        }
        return res;
    }
}
