import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';

type EntityResponseType = HttpResponse<IMahiatCourseMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMahiatCourseMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MahiatCourseMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/mahiat-courses';

    constructor(private http: HttpClient) {}

    create(mahiatCourse: IMahiatCourseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mahiatCourse);
        return this.http
            .post<IMahiatCourseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mahiatCourse: IMahiatCourseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mahiatCourse);
        return this.http
            .put<IMahiatCourseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMahiatCourseMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMahiatCourseMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(mahiatCourse: IMahiatCourseMarineSuffix): IMahiatCourseMarineSuffix {
        const copy: IMahiatCourseMarineSuffix = Object.assign({}, mahiatCourse, {
            createDate: mahiatCourse.createDate != null && mahiatCourse.createDate.isValid() ? mahiatCourse.createDate.toJSON() : null,
            modifyDate: mahiatCourse.modifyDate != null && mahiatCourse.modifyDate.isValid() ? mahiatCourse.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((mahiatCourse: IMahiatCourseMarineSuffix) => {
            mahiatCourse.createDate = mahiatCourse.createDate != null ? moment(mahiatCourse.createDate) : null;
            mahiatCourse.modifyDate = mahiatCourse.modifyDate != null ? moment(mahiatCourse.modifyDate) : null;
        });
        return res;
    }
}
