import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterGradeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterGradeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterGradeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-center-grades';

    constructor(protected http: HttpClient) {}

    create(educationalCenterGrade: IEducationalCenterGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGrade);
        return this.http
            .post<IEducationalCenterGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenterGrade: IEducationalCenterGradeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGrade);
        return this.http
            .put<IEducationalCenterGradeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterGradeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterGradeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(educationalCenterGrade: IEducationalCenterGradeMarineSuffix): IEducationalCenterGradeMarineSuffix {
        const copy: IEducationalCenterGradeMarineSuffix = Object.assign({}, educationalCenterGrade, {
            createDate:
                educationalCenterGrade.createDate != null && educationalCenterGrade.createDate.isValid()
                    ? educationalCenterGrade.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenterGrade.modifyDate != null && educationalCenterGrade.modifyDate.isValid()
                    ? educationalCenterGrade.modifyDate.toJSON()
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
            res.body.forEach((educationalCenterGrade: IEducationalCenterGradeMarineSuffix) => {
                educationalCenterGrade.createDate =
                    educationalCenterGrade.createDate != null ? moment(educationalCenterGrade.createDate) : null;
                educationalCenterGrade.modifyDate =
                    educationalCenterGrade.modifyDate != null ? moment(educationalCenterGrade.modifyDate) : null;
            });
        }
        return res;
    }
}
