import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeacherGradeScoreMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeacherGradeScoreMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeacherGradeScoreMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/teacher-grade-scores';

    constructor(protected http: HttpClient) {}

    create(teacherGradeScore: ITeacherGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherGradeScore);
        return this.http
            .post<ITeacherGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teacherGradeScore: ITeacherGradeScoreMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherGradeScore);
        return this.http
            .put<ITeacherGradeScoreMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeacherGradeScoreMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeacherGradeScoreMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(teacherGradeScore: ITeacherGradeScoreMarineSuffix): ITeacherGradeScoreMarineSuffix {
        const copy: ITeacherGradeScoreMarineSuffix = Object.assign({}, teacherGradeScore, {
            createDate:
                teacherGradeScore.createDate != null && teacherGradeScore.createDate.isValid()
                    ? teacherGradeScore.createDate.toJSON()
                    : null,
            modifyDate:
                teacherGradeScore.modifyDate != null && teacherGradeScore.modifyDate.isValid()
                    ? teacherGradeScore.modifyDate.toJSON()
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
            res.body.forEach((teacherGradeScore: ITeacherGradeScoreMarineSuffix) => {
                teacherGradeScore.createDate = teacherGradeScore.createDate != null ? moment(teacherGradeScore.createDate) : null;
                teacherGradeScore.modifyDate = teacherGradeScore.modifyDate != null ? moment(teacherGradeScore.modifyDate) : null;
            });
        }
        return res;
    }
}
