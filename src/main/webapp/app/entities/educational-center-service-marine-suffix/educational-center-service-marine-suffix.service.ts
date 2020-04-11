import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterServiceMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterServiceMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterServiceMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-center-services';

    constructor(protected http: HttpClient) {}

    create(educationalCenterService: IEducationalCenterServiceMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterService);
        return this.http
            .post<IEducationalCenterServiceMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenterService: IEducationalCenterServiceMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenterService);
        return this.http
            .put<IEducationalCenterServiceMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterServiceMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterServiceMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        educationalCenterService: IEducationalCenterServiceMarineSuffix
    ): IEducationalCenterServiceMarineSuffix {
        const copy: IEducationalCenterServiceMarineSuffix = Object.assign({}, educationalCenterService, {
            createDate:
                educationalCenterService.createDate != null && educationalCenterService.createDate.isValid()
                    ? educationalCenterService.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenterService.modifyDate != null && educationalCenterService.modifyDate.isValid()
                    ? educationalCenterService.modifyDate.toJSON()
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
            res.body.forEach((educationalCenterService: IEducationalCenterServiceMarineSuffix) => {
                educationalCenterService.createDate =
                    educationalCenterService.createDate != null ? moment(educationalCenterService.createDate) : null;
                educationalCenterService.modifyDate =
                    educationalCenterService.modifyDate != null ? moment(educationalCenterService.modifyDate) : null;
            });
        }
        return res;
    }
}
