import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RequestOrganizationNiazsanjiMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/request-organization-niazsanjis';

    constructor(private http: HttpClient) {}

    create(requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOrganizationNiazsanji);
        return this.http
            .post<IRequestOrganizationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    finalize(requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOrganizationNiazsanji);
        let url = SERVER_API_URL + 'api/finalize-request-organization-niazsanji';
        return this.http
            .post<IRequestOrganizationNiazsanjiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestOrganizationNiazsanji);
        return this.http
            .put<IRequestOrganizationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    toggleImportantMessage(id: number, type: boolean): Observable<EntityResponseType> {
        const url: string = this.resourceUrl + '/toggleImportantMessage/' + id + '/' + type;
        return this.http
            .put<IRequestOrganizationNiazsanjiMarineSuffix>(url, null, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRequestOrganizationNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRequestOrganizationNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    private convertDateFromClient(
        requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix
    ): IRequestOrganizationNiazsanjiMarineSuffix {
        const copy: IRequestOrganizationNiazsanjiMarineSuffix = Object.assign({}, requestOrganizationNiazsanji, {
            createDate:
                requestOrganizationNiazsanji.createDate != null && requestOrganizationNiazsanji.createDate.isValid()
                    ? requestOrganizationNiazsanji.createDate.toJSON()
                    : null,
            modifyDate:
                requestOrganizationNiazsanji.modifyDate != null && requestOrganizationNiazsanji.modifyDate.isValid()
                    ? requestOrganizationNiazsanji.modifyDate.toJSON()
                    : null,
            archivedDate:
                requestOrganizationNiazsanji.archivedDate != null && requestOrganizationNiazsanji.archivedDate.isValid()
                    ? requestOrganizationNiazsanji.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.fullTitle =
            (res.body.id ? res.body.id : '') +
            '-' +
            (res.body.code ? res.body.code : '') +
            '-' +
            (res.body.recommendedByOrgchart ? res.body.recommendedByOrgchart : '');
        res.body.totalLearningTime =
            (res.body.learningTimePractical ? res.body.learningTimePractical : 0) +
            (res.body.learningTimeTheorical ? res.body.learningTimeTheorical : 0);
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix) => {
            requestOrganizationNiazsanji.createDate =
                requestOrganizationNiazsanji.createDate != null ? moment(requestOrganizationNiazsanji.createDate) : null;
            requestOrganizationNiazsanji.modifyDate =
                requestOrganizationNiazsanji.modifyDate != null ? moment(requestOrganizationNiazsanji.modifyDate) : null;
            requestOrganizationNiazsanji.archivedDate =
                requestOrganizationNiazsanji.archivedDate != null ? moment(requestOrganizationNiazsanji.archivedDate) : null;
            requestOrganizationNiazsanji.fullTitle =
                (requestOrganizationNiazsanji.id ? requestOrganizationNiazsanji.id : '') +
                ' - ' +
                (requestOrganizationNiazsanji.code ? requestOrganizationNiazsanji.code : '') +
                ' - ' +
                (requestOrganizationNiazsanji.recommendedByOrgchart ? requestOrganizationNiazsanji.recommendedByOrgchart : '');
            requestOrganizationNiazsanji.totalLearningTime =
                (requestOrganizationNiazsanji.learningTimeTheorical ? requestOrganizationNiazsanji.learningTimeTheorical : 0) +
                (requestOrganizationNiazsanji.learningTimePractical ? requestOrganizationNiazsanji.learningTimePractical : 0);
        });
        return res;
    }
}
