import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeachTypeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeachTypeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeachTypeMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/teach-types';

    constructor(private http: HttpClient) {}

    create(teachType: ITeachTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachType);
        return this.http
            .post<ITeachTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teachType: ITeachTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachType);
        return this.http
            .put<ITeachTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeachTypeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeachTypeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(teachType: ITeachTypeMarineSuffix): ITeachTypeMarineSuffix {
        const copy: ITeachTypeMarineSuffix = Object.assign({}, teachType, {
            createDate: teachType.createDate != null && teachType.createDate.isValid() ? teachType.createDate.toJSON() : null,
            modifyDate: teachType.modifyDate != null && teachType.modifyDate.isValid() ? teachType.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((teachType: ITeachTypeMarineSuffix) => {
            teachType.createDate = teachType.createDate != null ? moment(teachType.createDate) : null;
            teachType.modifyDate = teachType.modifyDate != null ? moment(teachType.modifyDate) : null;
        });
        return res;
    }
}
