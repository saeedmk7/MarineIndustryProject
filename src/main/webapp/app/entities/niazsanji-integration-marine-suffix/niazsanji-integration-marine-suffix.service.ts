import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';

type EntityResponseType = HttpResponse<INiazsanjiIntegrationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INiazsanjiIntegrationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NiazsanjiIntegrationMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/niazsanji-integrations';

    constructor(protected http: HttpClient) {}

    create(niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiIntegration);
        return this.http
            .post<INiazsanjiIntegrationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(niazsanjiIntegration);
        return this.http
            .put<INiazsanjiIntegrationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INiazsanjiIntegrationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INiazsanjiIntegrationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix): INiazsanjiIntegrationMarineSuffix {
        const copy: INiazsanjiIntegrationMarineSuffix = Object.assign({}, niazsanjiIntegration, {
            createDate:
                niazsanjiIntegration.createDate != null && niazsanjiIntegration.createDate.isValid()
                    ? niazsanjiIntegration.createDate.toJSON()
                    : null,
            modifyDate:
                niazsanjiIntegration.modifyDate != null && niazsanjiIntegration.modifyDate.isValid()
                    ? niazsanjiIntegration.modifyDate.toJSON()
                    : null,
            archivedDate:
                niazsanjiIntegration.archivedDate != null && niazsanjiIntegration.archivedDate.isValid()
                    ? niazsanjiIntegration.archivedDate.toJSON()
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
            res.body.forEach((niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix) => {
                niazsanjiIntegration.createDate = niazsanjiIntegration.createDate != null ? moment(niazsanjiIntegration.createDate) : null;
                niazsanjiIntegration.modifyDate = niazsanjiIntegration.modifyDate != null ? moment(niazsanjiIntegration.modifyDate) : null;
                niazsanjiIntegration.archivedDate =
                    niazsanjiIntegration.archivedDate != null ? moment(niazsanjiIntegration.archivedDate) : null;
            });
        }
        return res;
    }
}
