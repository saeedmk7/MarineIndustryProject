import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';

type EntityResponseType = HttpResponse<IUsersRequestMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IUsersRequestMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class UsersRequestMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/users-requests';

    constructor(private http: HttpClient) {}

    create(usersRequest: IUsersRequestMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(usersRequest);
        return this.http
            .post<IUsersRequestMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(usersRequest: IUsersRequestMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(usersRequest);
        return this.http
            .put<IUsersRequestMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUsersRequestMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUsersRequestMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    count(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { params: options, observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }


    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(usersRequest: IUsersRequestMarineSuffix): IUsersRequestMarineSuffix {
        const copy: IUsersRequestMarineSuffix = Object.assign({}, usersRequest, {
            createDate: usersRequest.createDate != null && usersRequest.createDate.isValid() ? usersRequest.createDate.toJSON() : null,
            modifyDate: usersRequest.modifyDate != null && usersRequest.modifyDate.isValid() ? usersRequest.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((usersRequest: IUsersRequestMarineSuffix) => {
            usersRequest.createDate = usersRequest.createDate != null ? moment(usersRequest.createDate) : null;
            usersRequest.modifyDate = usersRequest.modifyDate != null ? moment(usersRequest.modifyDate) : null;
        });
        return res;
    }
}
