import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeacherCriteriaGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeacherCriteriaGroupMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeacherCriteriaGroupMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/teacher-criteria-groups';

    constructor(protected http: HttpClient) {}

    create(teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherCriteriaGroup);
        return this.http
            .post<ITeacherCriteriaGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacherCriteriaGroup);
        return this.http
            .put<ITeacherCriteriaGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeacherCriteriaGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeacherCriteriaGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix): ITeacherCriteriaGroupMarineSuffix {
        const copy: ITeacherCriteriaGroupMarineSuffix = Object.assign({}, teacherCriteriaGroup, {
            createDate:
                teacherCriteriaGroup.createDate != null && teacherCriteriaGroup.createDate.isValid()
                    ? teacherCriteriaGroup.createDate.toJSON()
                    : null,
            modifyDate:
                teacherCriteriaGroup.modifyDate != null && teacherCriteriaGroup.modifyDate.isValid()
                    ? teacherCriteriaGroup.modifyDate.toJSON()
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
            res.body.forEach((teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix) => {
                teacherCriteriaGroup.createDate = teacherCriteriaGroup.createDate != null ? moment(teacherCriteriaGroup.createDate) : null;
                teacherCriteriaGroup.modifyDate = teacherCriteriaGroup.modifyDate != null ? moment(teacherCriteriaGroup.modifyDate) : null;
            });
        }
        return res;
    }
}
