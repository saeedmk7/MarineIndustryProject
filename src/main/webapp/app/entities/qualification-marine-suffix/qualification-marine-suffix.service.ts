import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';

type EntityResponseType = HttpResponse<IQualificationMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IQualificationMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class QualificationMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/qualifications';

    constructor(private http: HttpClient) {}

    create(qualification: IQualificationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(qualification);
        return this.http
            .post<IQualificationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(qualification: IQualificationMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(qualification);
        return this.http
            .put<IQualificationMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IQualificationMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IQualificationMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(qualification: IQualificationMarineSuffix): IQualificationMarineSuffix {
        const copy: IQualificationMarineSuffix = Object.assign({}, qualification, {
            createDate: qualification.createDate != null && qualification.createDate.isValid() ? qualification.createDate.toJSON() : null,
            modifyDate: qualification.modifyDate != null && qualification.modifyDate.isValid() ? qualification.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((qualification: IQualificationMarineSuffix) => {
            qualification.createDate = qualification.createDate != null ? moment(qualification.createDate) : null;
            qualification.modifyDate = qualification.modifyDate != null ? moment(qualification.modifyDate) : null;
        });
        return res;
    }
}
