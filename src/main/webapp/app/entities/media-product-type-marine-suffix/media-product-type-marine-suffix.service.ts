import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';

type EntityResponseType = HttpResponse<IMediaProductTypeMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IMediaProductTypeMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class MediaProductTypeMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/media-product-types';

    constructor(protected http: HttpClient) {}

    create(mediaProductType: IMediaProductTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mediaProductType);
        return this.http
            .post<IMediaProductTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mediaProductType: IMediaProductTypeMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mediaProductType);
        return this.http
            .put<IMediaProductTypeMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMediaProductTypeMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMediaProductTypeMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(mediaProductType: IMediaProductTypeMarineSuffix): IMediaProductTypeMarineSuffix {
        const copy: IMediaProductTypeMarineSuffix = Object.assign({}, mediaProductType, {
            createDate:
                mediaProductType.createDate != null && mediaProductType.createDate.isValid() ? mediaProductType.createDate.toJSON() : null,
            modifyDate:
                mediaProductType.modifyDate != null && mediaProductType.modifyDate.isValid() ? mediaProductType.modifyDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
            res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((mediaProductType: IMediaProductTypeMarineSuffix) => {
                mediaProductType.createDate = mediaProductType.createDate != null ? moment(mediaProductType.createDate) : null;
                mediaProductType.modifyDate = mediaProductType.modifyDate != null ? moment(mediaProductType.modifyDate) : null;
            });
        }
        return res;
    }
}
