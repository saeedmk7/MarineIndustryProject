import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';

type EntityResponseType = HttpResponse<IScientificWorkGroupMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IScientificWorkGroupMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ScientificWorkGroupMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/scientific-work-groups';

    constructor(private http: HttpClient) {}

    create(scientificWorkGroup: IScientificWorkGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(scientificWorkGroup);
        return this.http
            .post<IScientificWorkGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(scientificWorkGroup: IScientificWorkGroupMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(scientificWorkGroup);
        return this.http
            .put<IScientificWorkGroupMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IScientificWorkGroupMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IScientificWorkGroupMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(scientificWorkGroup: IScientificWorkGroupMarineSuffix): IScientificWorkGroupMarineSuffix {
        const copy: IScientificWorkGroupMarineSuffix = Object.assign({}, scientificWorkGroup, {
            createDate:
                scientificWorkGroup.createDate != null && scientificWorkGroup.createDate.isValid()
                    ? scientificWorkGroup.createDate.toJSON()
                    : null,
            modifyDate:
                scientificWorkGroup.modifyDate != null && scientificWorkGroup.modifyDate.isValid()
                    ? scientificWorkGroup.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((scientificWorkGroup: IScientificWorkGroupMarineSuffix) => {
            scientificWorkGroup.createDate = scientificWorkGroup.createDate != null ? moment(scientificWorkGroup.createDate) : null;
            scientificWorkGroup.modifyDate = scientificWorkGroup.modifyDate != null ? moment(scientificWorkGroup.modifyDate) : null;
        });
        return res;
    }
}
