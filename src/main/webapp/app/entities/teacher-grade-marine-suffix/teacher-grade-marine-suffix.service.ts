import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeacherGradeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeacherGradeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeacherGradeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/teacher-grades';

    constructor(protected http: HttpClient) {}

    create(teacherGrade: ITeacherGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherGrade);
        return this.http
            .post<ITeacherGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teacherGrade: ITeacherGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherGrade);
        return this.http
            .put<ITeacherGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeacherGradeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeacherGradeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(teacherGrade: ITeacherGradeMarineSuffix): ITeacherGradeMarineSuffix {
        const copy: ITeacherGradeMarineSuffix = Object.assign({}, teacherGrade, {
            createDate: teacherGrade.createDate != null && teacherGrade.createDate.isValid() ? teacherGrade.createDate.toJSON() : null,
            modifyDate: teacherGrade.modifyDate != null && teacherGrade.modifyDate.isValid() ? teacherGrade.modifyDate.toJSON() : null
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
            res.body.forEach((teacherGrade: ITeacherGradeMarineSuffix) => {
                teacherGrade.createDate = teacherGrade.createDate != null ? moment(teacherGrade.createDate) : null;
                teacherGrade.modifyDate = teacherGrade.modifyDate != null ? moment(teacherGrade.modifyDate) : null;
            });
        }
        return res;
    }
}
