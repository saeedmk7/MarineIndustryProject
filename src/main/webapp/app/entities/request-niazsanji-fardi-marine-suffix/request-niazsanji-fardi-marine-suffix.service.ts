import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

type EntityResponseType = HttpResponse<IRequestNiazsanjiFardiMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRequestNiazsanjiFardiMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RequestNiazsanjiFardiMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/request-niazsanji-fardis';

    constructor(protected http: HttpClient, private convertObjectDatesService: ConvertObjectDatesService) {}

    create(requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestNiazsanjiFardi);
        return this.http
            .post<IRequestNiazsanjiFardiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    finalize(requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestNiazsanjiFardi);
        let url = SERVER_API_URL + 'api/finalize-request-niazsanji-fardi';
        return this.http
            .post<IRequestNiazsanjiFardiMarineSuffix>(url, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(requestNiazsanjiFardi);
        return this.http
            .put<IRequestNiazsanjiFardiMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRequestNiazsanjiFardiMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRequestNiazsanjiFardiMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    protected convertDateFromClient(requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix): IRequestNiazsanjiFardiMarineSuffix {
        const copy: IRequestNiazsanjiFardiMarineSuffix = Object.assign({}, requestNiazsanjiFardi, {
            createDate:
                requestNiazsanjiFardi.createDate != null && requestNiazsanjiFardi.createDate.isValid()
                    ? requestNiazsanjiFardi.createDate.toJSON()
                    : null,
            modifyDate:
                requestNiazsanjiFardi.modifyDate != null && requestNiazsanjiFardi.modifyDate.isValid()
                    ? requestNiazsanjiFardi.modifyDate.toJSON()
                    : null,
            archivedDate:
                requestNiazsanjiFardi.archivedDate != null && requestNiazsanjiFardi.archivedDate.isValid()
                    ? requestNiazsanjiFardi.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
            //res.body.statusMeaning = res.body.status != null? this.convertObjectDatesService.convertStatus2EqualString(res.body.status) : null;
            res.body.personFullName = (res.body.personName != null ? res.body.personName : '') + " " + (res.body.personFamily != null ? res.body.personFamily : '');
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix) => {
                requestNiazsanjiFardi.createDate =
                    requestNiazsanjiFardi.createDate != null ? moment(requestNiazsanjiFardi.createDate) : null;
                requestNiazsanjiFardi.modifyDate =
                    requestNiazsanjiFardi.modifyDate != null ? moment(requestNiazsanjiFardi.modifyDate) : null;
                requestNiazsanjiFardi.archivedDate =
                    requestNiazsanjiFardi.archivedDate != null ? moment(requestNiazsanjiFardi.archivedDate) : null;
                /*requestNiazsanjiFardi.statusMeaning =
                    requestNiazsanjiFardi.status != null ? this.convertObjectDatesService.convertStatus2EqualString(requestNiazsanjiFardi.status) : null;*/
                requestNiazsanjiFardi.personFullName = (requestNiazsanjiFardi.personName != null ? requestNiazsanjiFardi.personName : '') + " " + (requestNiazsanjiFardi.personFamily != null ? requestNiazsanjiFardi.personFamily : '');
            });
        }
        return res;
    }
}
