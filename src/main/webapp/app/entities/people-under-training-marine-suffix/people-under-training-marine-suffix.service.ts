import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';

type EntityResponseType = HttpResponse<IPeopleUnderTrainingMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IPeopleUnderTrainingMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PeopleUnderTrainingMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/people-under-trainings';

    constructor(protected http: HttpClient) {}

    create(peopleUnderTraining: IPeopleUnderTrainingMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(peopleUnderTraining);
        return this.http
            .post<IPeopleUnderTrainingMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(peopleUnderTraining: IPeopleUnderTrainingMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(peopleUnderTraining);
        return this.http
            .put<IPeopleUnderTrainingMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPeopleUnderTrainingMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPeopleUnderTrainingMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(peopleUnderTraining: IPeopleUnderTrainingMarineSuffix): IPeopleUnderTrainingMarineSuffix {
        const copy: IPeopleUnderTrainingMarineSuffix = Object.assign({}, peopleUnderTraining, {
            createDate:
                peopleUnderTraining.createDate != null && peopleUnderTraining.createDate.isValid()
                    ? peopleUnderTraining.createDate.toJSON()
                    : null,
            modifyDate:
                peopleUnderTraining.modifyDate != null && peopleUnderTraining.modifyDate.isValid()
                    ? peopleUnderTraining.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
            res.body.fullTitle = (res.body.title ? res.body.title : "") + " " + (res.body.description ? "(" + res.body.description + ")" : "");
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((peopleUnderTraining: IPeopleUnderTrainingMarineSuffix) => {
                peopleUnderTraining.createDate = peopleUnderTraining.createDate != null ? moment(peopleUnderTraining.createDate) : null;
                peopleUnderTraining.modifyDate = peopleUnderTraining.modifyDate != null ? moment(peopleUnderTraining.modifyDate) : null;
                peopleUnderTraining.fullTitle = (peopleUnderTraining.title ? peopleUnderTraining.title : "") + " " +
                    (peopleUnderTraining.description ? "(" + peopleUnderTraining.description + ")" : "");
            });
        }
        return res;
    }
}
