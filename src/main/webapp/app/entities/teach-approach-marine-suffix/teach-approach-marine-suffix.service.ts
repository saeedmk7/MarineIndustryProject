import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeachApproachMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeachApproachMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeachApproachMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/teach-approaches';

    constructor(private http: HttpClient) {}

    create(teachApproach: ITeachApproachMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachApproach);
        return this.http
            .post<ITeachApproachMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teachApproach: ITeachApproachMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachApproach);
        return this.http
            .put<ITeachApproachMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeachApproachMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeachApproachMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(teachApproach: ITeachApproachMarineSuffix): ITeachApproachMarineSuffix {
        const copy: ITeachApproachMarineSuffix = Object.assign({}, teachApproach, {
            createDate: teachApproach.createDate != null && teachApproach.createDate.isValid() ? teachApproach.createDate.toJSON() : null,
            modifyDate: teachApproach.modifyDate != null && teachApproach.modifyDate.isValid() ? teachApproach.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((teachApproach: ITeachApproachMarineSuffix) => {
            teachApproach.createDate = teachApproach.createDate != null ? moment(teachApproach.createDate) : null;
            teachApproach.modifyDate = teachApproach.modifyDate != null ? moment(teachApproach.modifyDate) : null;
        });
        return res;
    }
}
