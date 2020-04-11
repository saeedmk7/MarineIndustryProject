import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';

type EntityResponseType = HttpResponse<IActivationNiazsanjiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IActivationNiazsanjiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ActivationNiazsanjiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/activation-niazsanjis';

    constructor(protected http: HttpClient) {}

    create(activationNiazsanji: IActivationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(activationNiazsanji);
        return this.http
            .post<IActivationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(activationNiazsanji: IActivationNiazsanjiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(activationNiazsanji);
        return this.http
            .put<IActivationNiazsanjiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IActivationNiazsanjiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IActivationNiazsanjiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(activationNiazsanji: IActivationNiazsanjiMarineSuffix): IActivationNiazsanjiMarineSuffix {
        const copy: IActivationNiazsanjiMarineSuffix = Object.assign({}, activationNiazsanji, {
            startDate:
                activationNiazsanji.startDate != null && activationNiazsanji.startDate.isValid()
                    ? activationNiazsanji.startDate.toJSON()
                    : null,
            endDate:
                activationNiazsanji.endDate != null && activationNiazsanji.endDate.isValid() ? activationNiazsanji.endDate.toJSON() : null,
            createDate:
                activationNiazsanji.createDate != null && activationNiazsanji.createDate.isValid()
                    ? activationNiazsanji.createDate.toJSON()
                    : null,
            modifyDate:
                activationNiazsanji.modifyDate != null && activationNiazsanji.modifyDate.isValid()
                    ? activationNiazsanji.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
            res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((activationNiazsanji: IActivationNiazsanjiMarineSuffix) => {
                activationNiazsanji.startDate = activationNiazsanji.startDate != null ? moment(activationNiazsanji.startDate) : null;
                activationNiazsanji.endDate = activationNiazsanji.endDate != null ? moment(activationNiazsanji.endDate) : null;
                activationNiazsanji.createDate = activationNiazsanji.createDate != null ? moment(activationNiazsanji.createDate) : null;
                activationNiazsanji.modifyDate = activationNiazsanji.modifyDate != null ? moment(activationNiazsanji.modifyDate) : null;
            });
        }
        return res;
    }
}
