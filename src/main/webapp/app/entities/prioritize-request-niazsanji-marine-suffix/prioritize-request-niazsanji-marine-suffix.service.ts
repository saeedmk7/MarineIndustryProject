import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PrioritizeRequestNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/prioritize-request-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(prioritizeRequestNiazsanji);
        return this.http
            .post<IPrioritizeRequestNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(prioritizeRequestNiazsanji);
        return this.http
            .put<IPrioritizeRequestNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPrioritizeRequestNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPrioritizeRequestNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }

    finalize(prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(prioritizeRequestNiazsanji);
        let url = this.resourceUrl + '/finalize';
        return this.http
            .post<IPrioritizeRequestNiazsanjiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    protected convertDateFromClient(
        prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix
    ): IPrioritizeRequestNiazsanjiMarineSuffix {
        const copy: IPrioritizeRequestNiazsanjiMarineSuffix = Object.assign({}, prioritizeRequestNiazsanji, {
            createDate:
                prioritizeRequestNiazsanji.createDate != null && prioritizeRequestNiazsanji.createDate.isValid()
                    ? prioritizeRequestNiazsanji.createDate.toJSON()
                    : null,
            modifyDate:
                prioritizeRequestNiazsanji.modifyDate != null && prioritizeRequestNiazsanji.modifyDate.isValid()
                    ? prioritizeRequestNiazsanji.modifyDate.toJSON()
                    : null,
            archivedDate:
                prioritizeRequestNiazsanji.archivedDate != null && prioritizeRequestNiazsanji.archivedDate.isValid()
                    ? prioritizeRequestNiazsanji.archivedDate.toJSON()
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
            res.body.totalLearningTime =
                (res.body.learningTimePractical ? res.body.learningTimePractical : 0) +
                (res.body.learningTimeTheorical ? res.body.learningTimeTheorical : 0);
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix) => {
                prioritizeRequestNiazsanji.createDate =
                    prioritizeRequestNiazsanji.createDate != null ? moment(prioritizeRequestNiazsanji.createDate) : null;
                prioritizeRequestNiazsanji.modifyDate =
                    prioritizeRequestNiazsanji.modifyDate != null ? moment(prioritizeRequestNiazsanji.modifyDate) : null;
                prioritizeRequestNiazsanji.archivedDate =
                    prioritizeRequestNiazsanji.archivedDate != null ? moment(prioritizeRequestNiazsanji.archivedDate) : null;
                prioritizeRequestNiazsanji.personFullName =
                    (prioritizeRequestNiazsanji.personName != null ? prioritizeRequestNiazsanji.personName : '') +
                    ' ' +
                    (prioritizeRequestNiazsanji.personFamily != null ? prioritizeRequestNiazsanji.personFamily : '');
                prioritizeRequestNiazsanji.totalLearningTime =
                    (prioritizeRequestNiazsanji.learningTimePractical ? prioritizeRequestNiazsanji.learningTimePractical : 0) +
                    (prioritizeRequestNiazsanji.learningTimeTheorical ? prioritizeRequestNiazsanji.learningTimeTheorical : 0);
            });
        }
        return res;
    }
}
