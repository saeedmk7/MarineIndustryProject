import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';

type EntityResponseType = HttpResponse<ITeachTechniqueMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ITeachTechniqueMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class TeachTechniqueMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/teach-techniques';

    constructor(private http: HttpClient) {}

    create(teachTechnique: ITeachTechniqueMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachTechnique);
        return this.http
            .post<ITeachTechniqueMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(teachTechnique: ITeachTechniqueMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(teachTechnique);
        return this.http
            .put<ITeachTechniqueMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITeachTechniqueMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITeachTechniqueMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(teachTechnique: ITeachTechniqueMarineSuffix): ITeachTechniqueMarineSuffix {
        const copy: ITeachTechniqueMarineSuffix = Object.assign({}, teachTechnique, {
            createDate:
                teachTechnique.createDate != null && teachTechnique.createDate.isValid() ? teachTechnique.createDate.toJSON() : null,
            modifyDate: teachTechnique.modifyDate != null && teachTechnique.modifyDate.isValid() ? teachTechnique.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((teachTechnique: ITeachTechniqueMarineSuffix) => {
            teachTechnique.createDate = teachTechnique.createDate != null ? moment(teachTechnique.createDate) : null;
            teachTechnique.modifyDate = teachTechnique.modifyDate != null ? moment(teachTechnique.modifyDate) : null;
        });
        return res;
    }
}
