import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { IApplicationProcessSaveDataModel } from 'app/entities/application-process-marine-suffix/application-process-marine-suffix-save-data.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IApplicationProcessMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IApplicationProcessMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationProcessMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/application-processes';

    constructor(protected http: HttpClient) {}

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {
        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + '/upload-file';
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    saveApplicationProcessModel(applicationProcessSaveModel?: IApplicationProcessSaveDataModel): Observable<EntityResponseType> {
        const url = this.resourceUrl + '/save-data-model';
        return this.http
            .post<IApplicationProcessMarineSuffix>(url, applicationProcessSaveModel, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(applicationProcess: IApplicationProcessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcess);
        return this.http
            .post<IApplicationProcessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(applicationProcess: IApplicationProcessMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(applicationProcess);
        return this.http
            .put<IApplicationProcessMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IApplicationProcessMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IApplicationProcessMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IApplicationProcessMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    protected convertDateFromClient(applicationProcess: IApplicationProcessMarineSuffix): IApplicationProcessMarineSuffix {
        const copy: IApplicationProcessMarineSuffix = Object.assign({}, applicationProcess, {
            createDate:
                applicationProcess.createDate != null && applicationProcess.createDate.isValid()
                    ? applicationProcess.createDate.toJSON()
                    : null,
            modifyDate:
                applicationProcess.modifyDate != null && applicationProcess.modifyDate.isValid()
                    ? applicationProcess.modifyDate.toJSON()
                    : null,
            archivedDate:
                applicationProcess.archivedDate != null && applicationProcess.archivedDate.isValid()
                    ? applicationProcess.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            res.body.personFullName =
                (res.body.personName != null ? res.body.personName : '') +
                ' ' +
                (res.body.personFamily != null ? res.body.personFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((applicationProcess: IApplicationProcessMarineSuffix) => {
                applicationProcess.createDate = applicationProcess.createDate != null ? moment(applicationProcess.createDate) : null;
                applicationProcess.modifyDate = applicationProcess.modifyDate != null ? moment(applicationProcess.modifyDate) : null;
                applicationProcess.archivedDate = applicationProcess.archivedDate != null ? moment(applicationProcess.archivedDate) : null;
                applicationProcess.personFullName =
                    (applicationProcess.personName != null ? applicationProcess.personName : '') +
                    ' ' +
                    (applicationProcess.personFamily != null ? applicationProcess.personFamily : '');
            });
        }
        return res;
    }
}
