import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeacherMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeacherMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeacherMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/teachers';
    teachers: ITeacherMarineSuffix[];
    constructor(private http: HttpClient) {}

    create(teacher: ITeacherMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacher);
        return this.http
            .post<ITeacherMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teacher: ITeacherMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teacher);
        return this.http
            .put<ITeacherMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeacherMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        if(req) {
            const options = createRequestOption(req);
            return this.http
                .get<ITeacherMarineSuffix[]>(this.resourceUrl, {params: options, observe: 'response'})
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
        }
        else{
            let url = this.resourceUrl + '/all';
            return this.http
                .get<ITeacherMarineSuffix[]>(url, {observe: 'response'})
                .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res, true)));
        }
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(teacher: ITeacherMarineSuffix): ITeacherMarineSuffix {
        const copy: ITeacherMarineSuffix = Object.assign({}, teacher, {
            issueDate: teacher.issueDate != null && teacher.issueDate.isValid() ? teacher.issueDate.toJSON() : null,
            expirationDate: teacher.expirationDate != null && teacher.expirationDate.isValid() ? teacher.expirationDate.toJSON() : null,
            licenseRenewalDate:
                teacher.licenseRenewalDate != null && teacher.licenseRenewalDate.isValid() ? teacher.licenseRenewalDate.toJSON() : null,
            createDate: teacher.createDate != null && teacher.createDate.isValid() ? teacher.createDate.toJSON() : null,
            modifyDate: teacher.modifyDate != null && teacher.modifyDate.isValid() ? teacher.modifyDate.toJSON() : null,
            archivedDate: teacher.archivedDate != null && teacher.archivedDate.isValid() ? teacher.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.issueDate = res.body.issueDate != null ? moment(res.body.issueDate) : null;
        res.body.expirationDate = res.body.expirationDate != null ? moment(res.body.expirationDate) : null;
        res.body.licenseRenewalDate = res.body.licenseRenewalDate != null ? moment(res.body.licenseRenewalDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.fullName = (res.body.name ? res.body.name : "") + " " + (res.body.family ? res.body.family : "");
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType, isAll:boolean = false): EntityArrayResponseType {
        res.body.forEach((teacher: ITeacherMarineSuffix) => {
            teacher.issueDate = teacher.issueDate != null ? moment(teacher.issueDate) : null;
            teacher.expirationDate = teacher.expirationDate != null ? moment(teacher.expirationDate) : null;
            teacher.licenseRenewalDate = teacher.licenseRenewalDate != null ? moment(teacher.licenseRenewalDate) : null;
            teacher.createDate = teacher.createDate != null ? moment(teacher.createDate) : null;
            teacher.modifyDate = teacher.modifyDate != null ? moment(teacher.modifyDate) : null;
            teacher.archivedDate = teacher.archivedDate != null ? moment(teacher.archivedDate) : null;
            teacher.fullName = (teacher.name ? teacher.name : "") + " " + (teacher.family ? teacher.family : "");
        });
        if(isAll){
            this.teachers = res.body;
        }
        return res;
    }
}
