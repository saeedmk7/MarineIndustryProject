import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';

type EntityResponseType = HttpResponse<IDocumentMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IDocumentMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class DocumentMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/documents';

    constructor(private http: HttpClient) {}

    create(formdata: FormData): Observable<HttpEvent<{}>> {

        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const req = new HttpRequest('POST', this.resourceUrl, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }

    update(document: IDocumentMarineSuffix): Observable<EntityResponseType> {

        const copy = this.convertDateFromClient(document);
        return this.http
            .put<IDocumentMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDocumentMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDocumentMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number,entityName: any): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}/${entityName}`, { observe: 'response' });
    }

    private convertDateFromClient(document: IDocumentMarineSuffix): IDocumentMarineSuffix {
        const copy: IDocumentMarineSuffix = Object.assign({}, document, {
            createDate: document.createDate != null && document.createDate.isValid() ? document.createDate.toJSON() : null,
            modifyDate: document.modifyDate != null && document.modifyDate.isValid() ? document.modifyDate.toJSON() : null
        });

        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((document: IDocumentMarineSuffix) => {
            document.createDate = document.createDate != null ? moment(document.createDate) : null;
            document.modifyDate = document.modifyDate != null ? moment(document.modifyDate) : null;
        });
        return res;
    }
}
