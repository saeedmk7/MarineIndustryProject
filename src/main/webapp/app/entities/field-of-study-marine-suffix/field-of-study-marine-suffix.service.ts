import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';

type EntityResponseType = HttpResponse<IFieldOfStudyMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFieldOfStudyMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FieldOfStudyMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/field-of-studies';

    constructor(private http: HttpClient) {}

    create(fieldOfStudy: IFieldOfStudyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fieldOfStudy);
        return this.http
            .post<IFieldOfStudyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(fieldOfStudy: IFieldOfStudyMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fieldOfStudy);
        return this.http
            .put<IFieldOfStudyMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFieldOfStudyMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFieldOfStudyMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(fieldOfStudy: IFieldOfStudyMarineSuffix): IFieldOfStudyMarineSuffix {
        const copy: IFieldOfStudyMarineSuffix = Object.assign({}, fieldOfStudy, {
            createDate: fieldOfStudy.createDate != null && fieldOfStudy.createDate.isValid() ? fieldOfStudy.createDate.toJSON() : null,
            modifyDate: fieldOfStudy.modifyDate != null && fieldOfStudy.modifyDate.isValid() ? fieldOfStudy.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((fieldOfStudy: IFieldOfStudyMarineSuffix) => {
            fieldOfStudy.createDate = fieldOfStudy.createDate != null ? moment(fieldOfStudy.createDate) : null;
            fieldOfStudy.modifyDate = fieldOfStudy.modifyDate != null ? moment(fieldOfStudy.modifyDate) : null;
        });
        return res;
    }
}
