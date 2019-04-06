import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';

type EntityResponseType = HttpResponse<IAcademicRankMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IAcademicRankMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class AcademicRankMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/academic-ranks';

    constructor(private http: HttpClient) {}

    create(academicRank: IAcademicRankMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(academicRank);
        return this.http
            .post<IAcademicRankMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(academicRank: IAcademicRankMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(academicRank);
        return this.http
            .put<IAcademicRankMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAcademicRankMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAcademicRankMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(academicRank: IAcademicRankMarineSuffix): IAcademicRankMarineSuffix {
        const copy: IAcademicRankMarineSuffix = Object.assign({}, academicRank, {
            createDate: academicRank.createDate != null && academicRank.createDate.isValid() ? academicRank.createDate.toJSON() : null,
            modifyDate: academicRank.modifyDate != null && academicRank.modifyDate.isValid() ? academicRank.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((academicRank: IAcademicRankMarineSuffix) => {
            academicRank.createDate = academicRank.createDate != null ? moment(academicRank.createDate) : null;
            academicRank.modifyDate = academicRank.modifyDate != null ? moment(academicRank.modifyDate) : null;
        });
        return res;
    }
}
