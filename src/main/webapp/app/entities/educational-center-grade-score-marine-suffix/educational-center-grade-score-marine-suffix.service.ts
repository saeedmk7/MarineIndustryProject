import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterGradeScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterGradeScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterGradeScoreMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-center-grade-scores';

    constructor(protected http: HttpClient) {}

    create(educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGradeScore);
        return this.http
            .post<IEducationalCenterGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGradeScore);
        return this.http
            .put<IEducationalCenterGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterGradeScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterGradeScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix
    ): IEducationalCenterGradeScoreMarineSuffix {
        const copy: IEducationalCenterGradeScoreMarineSuffix = Object.assign({}, educationalCenterGradeScore, {
            createDate:
                educationalCenterGradeScore.createDate != null && educationalCenterGradeScore.createDate.isValid()
                    ? educationalCenterGradeScore.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenterGradeScore.modifyDate != null && educationalCenterGradeScore.modifyDate.isValid()
                    ? educationalCenterGradeScore.modifyDate.toJSON()
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
            res.body.forEach((educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix) => {
                educationalCenterGradeScore.createDate =
                    educationalCenterGradeScore.createDate != null ? moment(educationalCenterGradeScore.createDate) : null;
                educationalCenterGradeScore.modifyDate =
                    educationalCenterGradeScore.modifyDate != null ? moment(educationalCenterGradeScore.modifyDate) : null;
            });
        }
        return res;
    }
}
