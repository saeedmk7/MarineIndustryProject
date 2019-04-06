import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalHistoryMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalHistoryMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalHistoryMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-histories';

    constructor(protected http: HttpClient) {}

    /*create(educationalHistory: IEducationalHistoryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalHistory);
        return this.http
            .post<IEducationalHistoryMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }*/
    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {

        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + "/upload-file";
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }
    create(educationalHistory: IEducationalHistoryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalHistory);
        return this.http
            .post<IEducationalHistoryMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    finalize(educationalHistory: IEducationalHistoryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalHistory);
        let url = SERVER_API_URL + 'api/finalize-educational-histories';
        return this.http
            .post<IEducationalHistoryMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    update(educationalHistory: IEducationalHistoryMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalHistory);
        return this.http
            .put<IEducationalHistoryMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalHistoryMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalHistoryMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(educationalHistory: IEducationalHistoryMarineSuffix): IEducationalHistoryMarineSuffix {
        const copy: IEducationalHistoryMarineSuffix = Object.assign({}, educationalHistory, {
            createDate:
                educationalHistory.createDate != null && educationalHistory.createDate.isValid()
                    ? educationalHistory.createDate.toJSON()
                    : null,
            modifyDate:
                educationalHistory.modifyDate != null && educationalHistory.modifyDate.isValid()
                    ? educationalHistory.modifyDate.toJSON()
                    : null,
            archivedDate:
                educationalHistory.archivedDate != null && educationalHistory.archivedDate.isValid()
                    ? educationalHistory.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.personFullName = (res.body.personName != null ? res.body.personName : '') + " " + (res.body.personFamily != null ? res.body.personFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((educationalHistory: IEducationalHistoryMarineSuffix) => {
                educationalHistory.createDate = educationalHistory.createDate != null ? moment(educationalHistory.createDate) : null;
                educationalHistory.modifyDate = educationalHistory.modifyDate != null ? moment(educationalHistory.modifyDate) : null;
                educationalHistory.archivedDate = educationalHistory.archivedDate != null ? moment(educationalHistory.archivedDate) : null;
                educationalHistory.personFullName = (educationalHistory.personName != null ? educationalHistory.personName : '') + " " + (educationalHistory.personFamily != null ? educationalHistory.personFamily : '');
            });
        }
        return res;
    }
}
