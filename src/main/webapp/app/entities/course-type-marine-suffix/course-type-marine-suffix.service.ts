import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';

type EntityResponseType = HttpResponse<ICourseTypeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ICourseTypeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class CourseTypeMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/course-types';

    constructor(private http: HttpClient) {}

    create(courseType: ICourseTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(courseType);
        return this.http
            .post<ICourseTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(courseType: ICourseTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(courseType);
        return this.http
            .put<ICourseTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICourseTypeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICourseTypeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(courseType: ICourseTypeMarineSuffix): ICourseTypeMarineSuffix {
        const copy: ICourseTypeMarineSuffix = Object.assign({}, courseType, {
            createDate: courseType.createDate != null && courseType.createDate.isValid() ? courseType.createDate.toJSON() : null,
            modifyDate: courseType.modifyDate != null && courseType.modifyDate.isValid() ? courseType.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((courseType: ICourseTypeMarineSuffix) => {
            courseType.createDate = courseType.createDate != null ? moment(courseType.createDate) : null;
            courseType.modifyDate = courseType.modifyDate != null ? moment(courseType.modifyDate) : null;
        });
        return res;
    }
}
