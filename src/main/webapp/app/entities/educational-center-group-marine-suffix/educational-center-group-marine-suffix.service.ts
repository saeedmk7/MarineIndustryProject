import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterGroupMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterGroupMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-center-groups';

    constructor(protected http: HttpClient) {}

    create(educationalCenterGroup: IEducationalCenterGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGroup);
        return this.http
            .post<IEducationalCenterGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenterGroup: IEducationalCenterGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterGroup);
        return this.http
            .put<IEducationalCenterGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(educationalCenterGroup: IEducationalCenterGroupMarineSuffix): IEducationalCenterGroupMarineSuffix {
        const copy: IEducationalCenterGroupMarineSuffix = Object.assign({}, educationalCenterGroup, {
            createDate:
                educationalCenterGroup.createDate != null && educationalCenterGroup.createDate.isValid()
                    ? educationalCenterGroup.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenterGroup.modifyDate != null && educationalCenterGroup.modifyDate.isValid()
                    ? educationalCenterGroup.modifyDate.toJSON()
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
            res.body.forEach((educationalCenterGroup: IEducationalCenterGroupMarineSuffix) => {
                educationalCenterGroup.createDate =
                    educationalCenterGroup.createDate != null ? moment(educationalCenterGroup.createDate) : null;
                educationalCenterGroup.modifyDate =
                    educationalCenterGroup.modifyDate != null ? moment(educationalCenterGroup.modifyDate) : null;
            });
        }
        return res;
    }
}
