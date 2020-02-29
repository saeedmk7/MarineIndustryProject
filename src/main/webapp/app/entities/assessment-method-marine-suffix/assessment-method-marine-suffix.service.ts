import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';

type EntityResponseType = HttpResponse<IAssessmentMethodMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IAssessmentMethodMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class AssessmentMethodMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/assessment-methods';

    constructor(protected http: HttpClient) {}

    create(assessmentMethod: IAssessmentMethodMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(assessmentMethod);
        return this.http
            .post<IAssessmentMethodMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(assessmentMethod: IAssessmentMethodMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(assessmentMethod);
        return this.http
            .put<IAssessmentMethodMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAssessmentMethodMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAssessmentMethodMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(assessmentMethod: IAssessmentMethodMarineSuffix): IAssessmentMethodMarineSuffix {
        const copy: IAssessmentMethodMarineSuffix = Object.assign({}, assessmentMethod, {
            createDate:
                assessmentMethod.createDate != null && assessmentMethod.createDate.isValid() ? assessmentMethod.createDate.toJSON() : null,
            modifyDate:
                assessmentMethod.modifyDate != null && assessmentMethod.modifyDate.isValid() ? assessmentMethod.modifyDate.toJSON() : null
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
            res.body.forEach((assessmentMethod: IAssessmentMethodMarineSuffix) => {
                assessmentMethod.createDate = assessmentMethod.createDate != null ? moment(assessmentMethod.createDate) : null;
                assessmentMethod.modifyDate = assessmentMethod.modifyDate != null ? moment(assessmentMethod.modifyDate) : null;
            });
        }
        return res;
    }
}
