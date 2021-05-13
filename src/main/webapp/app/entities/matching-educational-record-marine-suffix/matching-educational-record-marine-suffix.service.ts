import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { IRunPhaseSaveDataModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { IMatchingEducationalRecordSaveDataModel } from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix-save-data.model';

type EntityResponseType = HttpResponse<IMatchingEducationalRecordMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMatchingEducationalRecordMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MatchingEducationalRecordMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/matching-educational-records';

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

    saveMatchingEducationalRecordModel(
        matchingEducationalRecordSaveModel?: IMatchingEducationalRecordSaveDataModel
    ): Observable<EntityResponseType> {
        const url = this.resourceUrl + '/save-data-model';
        return this.http
            .post<IMatchingEducationalRecordMarineSuffix>(url, matchingEducationalRecordSaveModel, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingEducationalRecord);
        return this.http
            .post<IMatchingEducationalRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matchingEducationalRecord);
        return this.http
            .put<IMatchingEducationalRecordMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IMatchingEducationalRecordMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMatchingEducationalRecordMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMatchingEducationalRecordMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    protected convertDateFromClient(
        matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix
    ): IMatchingEducationalRecordMarineSuffix {
        const copy: IMatchingEducationalRecordMarineSuffix = Object.assign({}, matchingEducationalRecord, {
            createDate:
                matchingEducationalRecord.createDate != null && matchingEducationalRecord.createDate.isValid()
                    ? matchingEducationalRecord.createDate.toJSON()
                    : null,
            modifyDate:
                matchingEducationalRecord.modifyDate != null && matchingEducationalRecord.modifyDate.isValid()
                    ? matchingEducationalRecord.modifyDate.toJSON()
                    : null,
            archivedDate:
                matchingEducationalRecord.archivedDate != null && matchingEducationalRecord.archivedDate.isValid()
                    ? matchingEducationalRecord.archivedDate.toJSON()
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
            res.body.forEach((matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix) => {
                matchingEducationalRecord.createDate =
                    matchingEducationalRecord.createDate != null ? moment(matchingEducationalRecord.createDate) : null;
                matchingEducationalRecord.modifyDate =
                    matchingEducationalRecord.modifyDate != null ? moment(matchingEducationalRecord.modifyDate) : null;
                matchingEducationalRecord.archivedDate =
                    matchingEducationalRecord.archivedDate != null ? moment(matchingEducationalRecord.archivedDate) : null;
                matchingEducationalRecord.personFullName =
                    (matchingEducationalRecord.personName != null ? matchingEducationalRecord.personName : '') +
                    ' ' +
                    (matchingEducationalRecord.personFamily != null ? matchingEducationalRecord.personFamily : '');
            });
        }
        return res;
    }
}
