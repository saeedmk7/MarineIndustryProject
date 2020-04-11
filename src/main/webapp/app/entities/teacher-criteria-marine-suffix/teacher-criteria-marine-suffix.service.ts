import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeacherCriteriaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeacherCriteriaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeacherCriteriaMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/teacher-criteria';

    constructor(protected http: HttpClient) {}

    create(teacherCriteria: ITeacherCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherCriteria);
        return this.http
            .post<ITeacherCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teacherCriteria: ITeacherCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherCriteria);
        return this.http
            .put<ITeacherCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeacherCriteriaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeacherCriteriaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(teacherCriteria: ITeacherCriteriaMarineSuffix): ITeacherCriteriaMarineSuffix {
        const copy: ITeacherCriteriaMarineSuffix = Object.assign({}, teacherCriteria, {
            createDate:
                teacherCriteria.createDate != null && teacherCriteria.createDate.isValid() ? teacherCriteria.createDate.toJSON() : null,
            modifyDate:
                teacherCriteria.modifyDate != null && teacherCriteria.modifyDate.isValid() ? teacherCriteria.modifyDate.toJSON() : null
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
            res.body.forEach((teacherCriteria: ITeacherCriteriaMarineSuffix) => {
                teacherCriteria.createDate = teacherCriteria.createDate != null ? moment(teacherCriteria.createDate) : null;
                teacherCriteria.modifyDate = teacherCriteria.modifyDate != null ? moment(teacherCriteria.modifyDate) : null;
            });
        }
        return res;
    }
}
