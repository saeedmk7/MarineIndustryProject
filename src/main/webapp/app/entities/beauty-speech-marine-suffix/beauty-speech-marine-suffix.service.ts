import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';

type EntityResponseType = HttpResponse<IBeautySpeechMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IBeautySpeechMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class BeautySpeechMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/beauty-speeches';

    constructor(private http: HttpClient) {}

    create(beautySpeech: IBeautySpeechMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(beautySpeech);
        return this.http
            .post<IBeautySpeechMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(beautySpeech: IBeautySpeechMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(beautySpeech);
        return this.http
            .put<IBeautySpeechMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBeautySpeechMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBeautySpeechMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(beautySpeech: IBeautySpeechMarineSuffix): IBeautySpeechMarineSuffix {
        const copy: IBeautySpeechMarineSuffix = Object.assign({}, beautySpeech, {
            showDate: beautySpeech.showDate != null && beautySpeech.showDate.isValid() ? beautySpeech.showDate.toJSON() : null,
            createDate: beautySpeech.createDate != null && beautySpeech.createDate.isValid() ? beautySpeech.createDate.toJSON() : null,
            modifyDate: beautySpeech.modifyDate != null && beautySpeech.modifyDate.isValid() ? beautySpeech.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.showDate = res.body.showDate != null ? moment(res.body.showDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((beautySpeech: IBeautySpeechMarineSuffix) => {
            beautySpeech.showDate = beautySpeech.showDate != null ? moment(beautySpeech.showDate) : null;
            beautySpeech.createDate = beautySpeech.createDate != null ? moment(beautySpeech.createDate) : null;
            beautySpeech.modifyDate = beautySpeech.modifyDate != null ? moment(beautySpeech.modifyDate) : null;
        });
        return res;
    }
}
