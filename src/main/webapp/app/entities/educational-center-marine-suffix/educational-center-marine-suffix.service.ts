import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalCenterMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalCenterMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalCenterMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/educational-centers';

    constructor(private http: HttpClient) {}

    create(educationalCenter: IEducationalCenterMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenter);
        return this.http
            .post<IEducationalCenterMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalCenter: IEducationalCenterMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalCenter);
        return this.http
            .put<IEducationalCenterMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalCenterMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalCenterMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(educationalCenter: IEducationalCenterMarineSuffix): IEducationalCenterMarineSuffix {
        const copy: IEducationalCenterMarineSuffix = Object.assign({}, educationalCenter, {
            createDate:
                educationalCenter.createDate != null && educationalCenter.createDate.isValid()
                    ? educationalCenter.createDate.toJSON()
                    : null,
            modifyDate:
                educationalCenter.modifyDate != null && educationalCenter.modifyDate.isValid()
                    ? educationalCenter.modifyDate.toJSON()
                    : null,
            archivedDate:
                educationalCenter.archivedDate != null && educationalCenter.archivedDate.isValid()
                    ? educationalCenter.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((educationalCenter: IEducationalCenterMarineSuffix) => {
            educationalCenter.createDate = educationalCenter.createDate != null ? moment(educationalCenter.createDate) : null;
            educationalCenter.modifyDate = educationalCenter.modifyDate != null ? moment(educationalCenter.modifyDate) : null;
            educationalCenter.archivedDate = educationalCenter.archivedDate != null ? moment(educationalCenter.archivedDate) : null;
        });
        return res;
    }
}
