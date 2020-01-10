import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model/design-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IDesignNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IDesignNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class DesignNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/design-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(designNiazsanji: IDesignNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(designNiazsanji);
        return this.http
            .post<IDesignNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(designNiazsanji: IDesignNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(designNiazsanji);
        return this.http
            .put<IDesignNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDesignNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDesignNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(designNiazsanji: IDesignNiazsanjiMarineSuffix): IDesignNiazsanjiMarineSuffix {
        const copy: IDesignNiazsanjiMarineSuffix = Object.assign({}, designNiazsanji, {
            createDate:
                designNiazsanji.createDate != null && designNiazsanji.createDate.isValid() ? designNiazsanji.createDate.toJSON() : null,
            modifyDate:
                designNiazsanji.modifyDate != null && designNiazsanji.modifyDate.isValid() ? designNiazsanji.modifyDate.toJSON() : null,
            archivedDate:
                designNiazsanji.archivedDate != null && designNiazsanji.archivedDate.isValid()
                    ? designNiazsanji.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((designNiazsanji: IDesignNiazsanjiMarineSuffix) => {
                designNiazsanji.createDate = designNiazsanji.createDate != null ? moment(designNiazsanji.createDate) : null;
                designNiazsanji.modifyDate = designNiazsanji.modifyDate != null ? moment(designNiazsanji.modifyDate) : null;
                designNiazsanji.archivedDate = designNiazsanji.archivedDate != null ? moment(designNiazsanji.archivedDate) : null;
            });
        }
        return res;
    }
}
