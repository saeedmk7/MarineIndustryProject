import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';

type EntityResponseType = HttpResponse<IEducationalRecordMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IEducationalRecordMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class EducationalRecordMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/educational-records';

    constructor(protected http: HttpClient) {}

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {

        const url = this.resourceUrl + "/upload-file";
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    create(educationalRecord: IEducationalRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalRecord);
        return this.http
            .post<IEducationalRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(educationalRecord: IEducationalRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(educationalRecord);
        return this.http
            .put<IEducationalRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEducationalRecordMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEducationalRecordMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(educationalRecord: IEducationalRecordMarineSuffix): IEducationalRecordMarineSuffix {
        const copy: IEducationalRecordMarineSuffix = Object.assign({}, educationalRecord, {
            createDate:
                educationalRecord.createDate != null && educationalRecord.createDate.isValid()
                    ? educationalRecord.createDate.toJSON()
                    : null,
            modifyDate:
                educationalRecord.modifyDate != null && educationalRecord.modifyDate.isValid()
                    ? educationalRecord.modifyDate.toJSON()
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
            res.body.forEach((educationalRecord: IEducationalRecordMarineSuffix) => {
                educationalRecord.createDate = educationalRecord.createDate != null ? moment(educationalRecord.createDate) : null;
                educationalRecord.modifyDate = educationalRecord.modifyDate != null ? moment(educationalRecord.modifyDate) : null;
            });
        }
        return res;
    }
}
