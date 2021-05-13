import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class FinalOrganizationNiazsanjiMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/final-organization-niazsanjis';

    constructor(private http: HttpClient) {}

    create(finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalOrganizationNiazsanji);
        return this.http
            .post<IFinalOrganizationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    finalize(finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalOrganizationNiazsanji);
        let finalizeUrl = SERVER_API_URL + 'api/finalize-organization-niazsanjis';
        return this.http
            .post<IFinalOrganizationNiazsanjiMarineSuffix>(finalizeUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(finalOrganizationNiazsanji);
        return this.http
            .put<IFinalOrganizationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IFinalOrganizationNiazsanjiMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinalOrganizationNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinalOrganizationNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    count(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(
        finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix
    ): IFinalOrganizationNiazsanjiMarineSuffix {
        const copy: IFinalOrganizationNiazsanjiMarineSuffix = Object.assign({}, finalOrganizationNiazsanji, {
            createDate:
                finalOrganizationNiazsanji.createDate != null && finalOrganizationNiazsanji.createDate.isValid()
                    ? finalOrganizationNiazsanji.createDate.toJSON()
                    : null,
            modifyDate:
                finalOrganizationNiazsanji.modifyDate != null && finalOrganizationNiazsanji.modifyDate.isValid()
                    ? finalOrganizationNiazsanji.modifyDate.toJSON()
                    : null,
            archivedDate:
                finalOrganizationNiazsanji.archivedDate != null && finalOrganizationNiazsanji.archivedDate.isValid()
                    ? finalOrganizationNiazsanji.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.totalLearningTime =
            (res.body.learningTimeTheorical ? res.body.learningTimeTheorical : 0) +
            (res.body.learningTimePractical ? res.body.learningTimePractical : 0);
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix) => {
            finalOrganizationNiazsanji.createDate =
                finalOrganizationNiazsanji.createDate != null ? moment(finalOrganizationNiazsanji.createDate) : null;
            finalOrganizationNiazsanji.modifyDate =
                finalOrganizationNiazsanji.modifyDate != null ? moment(finalOrganizationNiazsanji.modifyDate) : null;
            finalOrganizationNiazsanji.archivedDate =
                finalOrganizationNiazsanji.archivedDate != null ? moment(finalOrganizationNiazsanji.archivedDate) : null;
            finalOrganizationNiazsanji.totalLearningTime =
                (finalOrganizationNiazsanji.learningTimePractical ? finalOrganizationNiazsanji.learningTimePractical : 0) +
                (finalOrganizationNiazsanji.learningTimeTheorical ? finalOrganizationNiazsanji.learningTimeTheorical : 0);
        });
        return res;
    }
}
