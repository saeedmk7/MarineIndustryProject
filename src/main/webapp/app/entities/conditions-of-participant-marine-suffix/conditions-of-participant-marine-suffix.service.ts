import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';

type EntityResponseType = HttpResponse<IConditionsOfParticipantMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IConditionsOfParticipantMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ConditionsOfParticipantMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/conditions-of-participants';

    constructor(private http: HttpClient) {}

    create(conditionsOfParticipant: IConditionsOfParticipantMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(conditionsOfParticipant);
        return this.http
            .post<IConditionsOfParticipantMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(conditionsOfParticipant: IConditionsOfParticipantMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(conditionsOfParticipant);
        return this.http
            .put<IConditionsOfParticipantMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IConditionsOfParticipantMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IConditionsOfParticipantMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(conditionsOfParticipant: IConditionsOfParticipantMarineSuffix): IConditionsOfParticipantMarineSuffix {
        const copy: IConditionsOfParticipantMarineSuffix = Object.assign({}, conditionsOfParticipant, {
            createDate:
                conditionsOfParticipant.createDate != null && conditionsOfParticipant.createDate.isValid()
                    ? conditionsOfParticipant.createDate.toJSON()
                    : null,
            modifyDate:
                conditionsOfParticipant.modifyDate != null && conditionsOfParticipant.modifyDate.isValid()
                    ? conditionsOfParticipant.modifyDate.toJSON()
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
        res.body.forEach((conditionsOfParticipant: IConditionsOfParticipantMarineSuffix) => {
            conditionsOfParticipant.createDate =
                conditionsOfParticipant.createDate != null ? moment(conditionsOfParticipant.createDate) : null;
            conditionsOfParticipant.modifyDate =
                conditionsOfParticipant.modifyDate != null ? moment(conditionsOfParticipant.modifyDate) : null;
        });
        return res;
    }
}
