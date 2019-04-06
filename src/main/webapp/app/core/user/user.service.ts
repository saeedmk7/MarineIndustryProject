import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import {IUser, User} from './user.model';
import {map} from "rxjs/operators";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

type EntityArrayResponseType = HttpResponse<IUser[]>;
@Injectable({ providedIn: 'root' })
export class UserService {
    private resourceUrl = SERVER_API_URL + 'api/users';
    users: IUser[];
    constructor(private http: HttpClient) {}

    create(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.post<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    resetPassword(user: IUser): Observable<HttpResponse<IUser>> {
        let url = this.resourceUrl + "/reset-password";
        return this.http.post<IUser>(url, user, { observe: 'response' });
    }

    update(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.put<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<IUser>> {
        return this.http.get<IUser>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }
    findByPersonId(personId: number): Observable<HttpResponse<IUser>> {

        let url: string = `${this.resourceUrl}/person/${personId}`;
        return this.http.get<IUser>(url, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<IUser[]>> {

        const options = createRequestOption(req);
        return this.http.get<IUser[]>(this.resourceUrl, {params: options, observe: 'response'});
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    private convertDateFromServer(res: HttpResponse<IUser[]>): HttpResponse<IUser[]> {
        this.users = res.body;
        return res;
    }
}
