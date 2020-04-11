import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterCriteriaMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterCriteriaMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterCriteriaMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-center-criteria';

    constructor(protected http: HttpClient) {}

    create(educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterCriteria);
        return this.http
            .post<IEducationalCenterCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterCriteria);
        return this.http
            .put<IEducationalCenterCriteriaMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterCriteriaMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterCriteriaMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix
    ): IEducationalCenterCriteriaMarineSuffix {
        const copy: IEducationalCenterCriteriaMarineSuffix = Object.assign({}, educationalCenterCriteria, {
            createDate:
                educationalCenterCriteria.createDate != null && educationalCenterCriteria.createDate.isValid()
                    ? educationalCenterCriteria.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenterCriteria.modifyDate != null && educationalCenterCriteria.modifyDate.isValid()
                    ? educationalCenterCriteria.modifyDate.toJSON()
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
            res.body.forEach((educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix) => {
                educationalCenterCriteria.createDate =
                    educationalCenterCriteria.createDate != null ? moment(educationalCenterCriteria.createDate) : null;
                educationalCenterCriteria.modifyDate =
                    educationalCenterCriteria.modifyDate != null ? moment(educationalCenterCriteria.modifyDate) : null;
            });
        }
        return res;
    }
}
