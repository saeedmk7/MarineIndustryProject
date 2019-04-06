import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';

type EntityResponseType = HttpResponse<ICourseLocationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ICourseLocationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class CourseLocationMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/course-locations';

    constructor(private http: HttpClient) {}

    create(courseLocation: ICourseLocationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(courseLocation);
        return this.http
            .post<ICourseLocationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(courseLocation: ICourseLocationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(courseLocation);
        return this.http
            .put<ICourseLocationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICourseLocationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICourseLocationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(courseLocation: ICourseLocationMarineSuffix): ICourseLocationMarineSuffix {
        const copy: ICourseLocationMarineSuffix = Object.assign({}, courseLocation, {
            createDate:
                courseLocation.createDate != null && courseLocation.createDate.isValid() ? courseLocation.createDate.toJSON() : null,
            modifyDate: courseLocation.modifyDate != null && courseLocation.modifyDate.isValid() ? courseLocation.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((courseLocation: ICourseLocationMarineSuffix) => {
            courseLocation.createDate = courseLocation.createDate != null ? moment(courseLocation.createDate) : null;
            courseLocation.modifyDate = courseLocation.modifyDate != null ? moment(courseLocation.modifyDate) : null;
        });
        return res;
    }
}
