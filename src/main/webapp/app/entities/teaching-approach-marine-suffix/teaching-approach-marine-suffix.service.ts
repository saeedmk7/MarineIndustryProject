import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeachingApproachMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeachingApproachMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeachingApproachMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/teaching-approaches';

    constructor(private http: HttpClient) {}

    create(teachingApproach: ITeachingApproachMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachingApproach);
        return this.http
            .post<ITeachingApproachMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teachingApproach: ITeachingApproachMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachingApproach);
        return this.http
            .put<ITeachingApproachMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeachingApproachMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeachingApproachMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(teachingApproach: ITeachingApproachMarineSuffix): ITeachingApproachMarineSuffix {
        const copy: ITeachingApproachMarineSuffix = Object.assign({}, teachingApproach, {
            createDate:
                teachingApproach.createDate != null && teachingApproach.createDate.isValid() ? teachingApproach.createDate.toJSON() : null,
            modifyDate:
                teachingApproach.modifyDate != null && teachingApproach.modifyDate.isValid() ? teachingApproach.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((teachingApproach: ITeachingApproachMarineSuffix) => {
            teachingApproach.createDate = teachingApproach.createDate != null ? moment(teachingApproach.createDate) : null;
            teachingApproach.modifyDate = teachingApproach.modifyDate != null ? moment(teachingApproach.modifyDate) : null;
        });
        return res;
    }
}
