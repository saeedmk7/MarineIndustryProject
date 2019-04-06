import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';

type EntityResponseType = HttpResponse<IDesignAndPlanningMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IDesignAndPlanningMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class DesignAndPlanningMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/design-and-plannings';

    constructor(private http: HttpClient) {}

    create(designAndPlanning: IDesignAndPlanningMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(designAndPlanning);
        return this.http
            .post<IDesignAndPlanningMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(designAndPlanning: IDesignAndPlanningMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(designAndPlanning);
        return this.http
            .put<IDesignAndPlanningMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDesignAndPlanningMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDesignAndPlanningMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(designAndPlanning: IDesignAndPlanningMarineSuffix): IDesignAndPlanningMarineSuffix {
        const copy: IDesignAndPlanningMarineSuffix = Object.assign({}, designAndPlanning, {
            finishedDate:
                designAndPlanning.finishedDate != null && designAndPlanning.finishedDate.isValid()
                    ? designAndPlanning.finishedDate.toJSON()
                    : null,
            createDate:
                designAndPlanning.createDate != null && designAndPlanning.createDate.isValid()
                    ? designAndPlanning.createDate.toJSON()
                    : null,
            modifyDate:
                designAndPlanning.modifyDate != null && designAndPlanning.modifyDate.isValid()
                    ? designAndPlanning.modifyDate.toJSON()
                    : null,
            archivedDate:
                designAndPlanning.archivedDate != null && designAndPlanning.archivedDate.isValid()
                    ? designAndPlanning.archivedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.finishedDate = res.body.finishedDate != null ? moment(res.body.finishedDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((designAndPlanning: IDesignAndPlanningMarineSuffix) => {
            designAndPlanning.finishedDate = designAndPlanning.finishedDate != null ? moment(designAndPlanning.finishedDate) : null;
            designAndPlanning.createDate = designAndPlanning.createDate != null ? moment(designAndPlanning.createDate) : null;
            designAndPlanning.modifyDate = designAndPlanning.modifyDate != null ? moment(designAndPlanning.modifyDate) : null;
            designAndPlanning.archivedDate = designAndPlanning.archivedDate != null ? moment(designAndPlanning.archivedDate) : null;
        });
        return res;
    }
}
