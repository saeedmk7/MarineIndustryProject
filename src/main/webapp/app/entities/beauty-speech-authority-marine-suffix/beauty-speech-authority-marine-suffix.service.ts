import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<IBeautySpeechAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IBeautySpeechAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class BeautySpeechAuthorityMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/beauty-speech-authorities';

    constructor(protected http: HttpClient) {}

    create(beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(beautySpeechAuthority);
        return this.http
            .post<IBeautySpeechAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(beautySpeechAuthority);
        return this.http
            .put<IBeautySpeechAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBeautySpeechAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBeautySpeechAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix): IBeautySpeechAuthorityMarineSuffix {
        const copy: IBeautySpeechAuthorityMarineSuffix = Object.assign({}, beautySpeechAuthority, {
            createDate:
                beautySpeechAuthority.createDate != null && beautySpeechAuthority.createDate.isValid()
                    ? beautySpeechAuthority.createDate.toJSON()
                    : null,
            modifyDate:
                beautySpeechAuthority.modifyDate != null && beautySpeechAuthority.modifyDate.isValid()
                    ? beautySpeechAuthority.modifyDate.toJSON()
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
            res.body.forEach((beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix) => {
                beautySpeechAuthority.createDate =
                    beautySpeechAuthority.createDate != null ? moment(beautySpeechAuthority.createDate) : null;
                beautySpeechAuthority.modifyDate =
                    beautySpeechAuthority.modifyDate != null ? moment(beautySpeechAuthority.modifyDate) : null;
            });
        }
        return res;
    }
}
