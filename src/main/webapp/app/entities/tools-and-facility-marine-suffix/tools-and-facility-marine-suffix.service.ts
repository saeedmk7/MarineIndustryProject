import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';

type EntityResponseType = HttpResponse<IToolsAndFacilityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IToolsAndFacilityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ToolsAndFacilityMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/tools-and-facilities';

    constructor(private http: HttpClient) {}

    create(toolsAndFacility: IToolsAndFacilityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(toolsAndFacility);
        return this.http
            .post<IToolsAndFacilityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(toolsAndFacility: IToolsAndFacilityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(toolsAndFacility);
        return this.http
            .put<IToolsAndFacilityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IToolsAndFacilityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IToolsAndFacilityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(toolsAndFacility: IToolsAndFacilityMarineSuffix): IToolsAndFacilityMarineSuffix {
        const copy: IToolsAndFacilityMarineSuffix = Object.assign({}, toolsAndFacility, {
            createDate:
                toolsAndFacility.createDate != null && toolsAndFacility.createDate.isValid() ? toolsAndFacility.createDate.toJSON() : null,
            modifyDate:
                toolsAndFacility.modifyDate != null && toolsAndFacility.modifyDate.isValid() ? toolsAndFacility.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((toolsAndFacility: IToolsAndFacilityMarineSuffix) => {
            toolsAndFacility.createDate = toolsAndFacility.createDate != null ? moment(toolsAndFacility.createDate) : null;
            toolsAndFacility.modifyDate = toolsAndFacility.modifyDate != null ? moment(toolsAndFacility.modifyDate) : null;
        });
        return res;
    }
}
