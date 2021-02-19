import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalModuleMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalModuleMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalModuleMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/educational-modules';
    educationalModules: IEducationalModuleMarineSuffix[];
    constructor(private http: HttpClient) {}

    create(educationalModule: IEducationalModuleMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalModule);
        return this.http
            .post<IEducationalModuleMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalModule: IEducationalModuleMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalModule);
        return this.http
            .put<IEducationalModuleMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalModuleMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        if (req) {
            const options = createRequestOption(req);
            return this.http
                .get<IEducationalModuleMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
        } else {
            let url = this.resourceUrl + '/all';
            return this.http
                .get<IEducationalModuleMarineSuffix[]>(url, { observe: 'response' })
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res, true)));
        }
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(educationalModule: IEducationalModuleMarineSuffix): IEducationalModuleMarineSuffix {
        const copy: IEducationalModuleMarineSuffix = Object.assign({}, educationalModule, {
            timePassed:
                educationalModule.timePassed != null && educationalModule.timePassed.isValid()
                    ? educationalModule.timePassed.toJSON()
                    : null,
            credit: educationalModule.credit != null && educationalModule.credit.isValid() ? educationalModule.credit.toJSON() : null,
            createDate:
                educationalModule.createDate != null && educationalModule.createDate.isValid()
                    ? educationalModule.createDate.toJSON()
                    : null,
            modifyDate:
                educationalModule.modifyDate != null && educationalModule.modifyDate.isValid()
                    ? educationalModule.modifyDate.toJSON()
                    : null,
            archivedDate:
                educationalModule.archivedDate != null && educationalModule.archivedDate.isValid()
                    ? educationalModule.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timePassed = res.body.timePassed != null ? moment(res.body.timePassed) : null;
        res.body.credit = res.body.credit != null ? moment(res.body.credit) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.code = res.body.code ? res.body.code + '(' + res.body.code.length + ')' : '';
        res.body.fullTitle = (res.body.code ? res.body.code : '') + ' - ' + (res.body.title ? res.body.title : '');
        res.body.totalLearningTime =
            (res.body.learningTimeTheorical ? res.body.learningTimeTheorical : 0) +
            (res.body.learningTimePractical ? res.body.learningTimePractical : 0);
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType, isAll: boolean = false): EntityArrayResponseType {
        res.body.forEach((educationalModule: IEducationalModuleMarineSuffix) => {
            educationalModule.timePassed = educationalModule.timePassed != null ? moment(educationalModule.timePassed) : null;
            educationalModule.credit = educationalModule.credit != null ? moment(educationalModule.credit) : null;
            educationalModule.createDate = educationalModule.createDate != null ? moment(educationalModule.createDate) : null;
            educationalModule.modifyDate = educationalModule.modifyDate != null ? moment(educationalModule.modifyDate) : null;
            educationalModule.archivedDate = educationalModule.archivedDate != null ? moment(educationalModule.archivedDate) : null;
            educationalModule.code = educationalModule.code ? educationalModule.code + '(' + educationalModule.code.length + ')' : '';
            educationalModule.fullTitle =
                (educationalModule.code ? educationalModule.code : '') + ' - ' + (educationalModule.title ? educationalModule.title : '');
            educationalModule.totalLearningTime =
                (educationalModule.learningTimePractical ? educationalModule.learningTimePractical : 0) +
                (educationalModule.learningTimeTheorical ? educationalModule.learningTimeTheorical : 0);
        });

        if (isAll) this.educationalModules = res.body;
        return res;
    }
}
