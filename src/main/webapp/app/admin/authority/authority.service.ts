import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import {IAuthority} from 'app/shared/model/authority.model';
import {IAcademicRankMarineSuffix} from "app/shared/model/academic-rank-marine-suffix.model";

type EntityResponseType = HttpResponse<IAuthority>;

@Injectable({ providedIn: 'root' })
export class AuthorityService {
    private resourceUrl = SERVER_API_URL + 'api';

    constructor(private http: HttpClient) {}

    create(authority: IAuthority): Observable<EntityResponseType> {
        return this.http
            .post<IAuthority>(this.resourceUrl + '/authority', authority, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => res));
    }
    authorities(): Observable<HttpResponse<IAuthority[]>> {
        //return this.http.get<HttpResponse<IAuthority[]>>(this.resourceUrl + '/authorities');
        return this.http
            .get<IAuthority[]>(this.resourceUrl + '/authorities', { observe: 'response' })
            .pipe(map((res: HttpResponse<IAuthority[]>) => res));
    }

    delete(name: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/authority/${name}`, { observe: 'response' });
    }
}
