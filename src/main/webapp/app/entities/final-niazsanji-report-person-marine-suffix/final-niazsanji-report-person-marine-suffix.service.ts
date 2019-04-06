import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';

type EntityResponseType = HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FinalNiazsanjiReportPersonMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/final-niazsanji-report-people';

    constructor(private http: HttpClient) {}

    create(finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalNiazsanjiReportPerson);
        return this.http
            .post<IFinalNiazsanjiReportPersonMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalNiazsanjiReportPerson);
        return this.http
            .put<IFinalNiazsanjiReportPersonMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinalNiazsanjiReportPersonMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinalNiazsanjiReportPersonMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(
        finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix
    ): IFinalNiazsanjiReportPersonMarineSuffix {
        const copy: IFinalNiazsanjiReportPersonMarineSuffix = Object.assign({}, finalNiazsanjiReportPerson, {
            createDate:
                finalNiazsanjiReportPerson.createDate != null && finalNiazsanjiReportPerson.createDate.isValid()
                    ? finalNiazsanjiReportPerson.createDate.toJSON()
                    : null,
            modifyDate:
                finalNiazsanjiReportPerson.modifyDate != null && finalNiazsanjiReportPerson.modifyDate.isValid()
                    ? finalNiazsanjiReportPerson.modifyDate.toJSON()
                    : null,
            archivedDate:
                finalNiazsanjiReportPerson.archivedDate != null && finalNiazsanjiReportPerson.archivedDate.isValid()
                    ? finalNiazsanjiReportPerson.archivedDate.toJSON()
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
        res.body.forEach((finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix) => {
            finalNiazsanjiReportPerson.createDate =
                finalNiazsanjiReportPerson.createDate != null ? moment(finalNiazsanjiReportPerson.createDate) : null;
            finalNiazsanjiReportPerson.modifyDate =
                finalNiazsanjiReportPerson.modifyDate != null ? moment(finalNiazsanjiReportPerson.modifyDate) : null;
            finalNiazsanjiReportPerson.archivedDate =
                finalNiazsanjiReportPerson.archivedDate != null ? moment(finalNiazsanjiReportPerson.archivedDate) : null;
        });
        return res;
    }
}
