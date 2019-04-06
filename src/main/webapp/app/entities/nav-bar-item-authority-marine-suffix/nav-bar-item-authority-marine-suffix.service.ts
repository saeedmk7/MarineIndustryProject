import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INavBarItemAuthorityMarineSuffix } from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<INavBarItemAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INavBarItemAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NavBarItemAuthorityMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/nav-bar-item-authorities';

    constructor(private http: HttpClient) {}

    create(navBarItemAuthority: INavBarItemAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(navBarItemAuthority);
        return this.http
            .post<INavBarItemAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    createBulk(navBarItemAuthorities: INavBarItemAuthorityMarineSuffix[]): Observable<EntityResponseType> {

        //const copy = this.convertDateFromClient(navBarItemAuthority);
        return this.http
            .post<INavBarItemAuthorityMarineSuffix>(this.resourceUrl, navBarItemAuthorities, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
    update(navBarItemAuthority: INavBarItemAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(navBarItemAuthority);
        return this.http
            .put<INavBarItemAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INavBarItemAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INavBarItemAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    deleteAuthority(authority: string): Observable<HttpResponse<any>> {

        return this.http.delete<any>(`${this.resourceUrl}/${authority}`, { observe: 'response' });
    }
    private convertDateFromClient(navBarItemAuthority: INavBarItemAuthorityMarineSuffix): INavBarItemAuthorityMarineSuffix {
        const copy: INavBarItemAuthorityMarineSuffix = Object.assign({}, navBarItemAuthority, {
            createDate:
                navBarItemAuthority.createDate != null && navBarItemAuthority.createDate.isValid()
                    ? navBarItemAuthority.createDate.toJSON()
                    : null,
            modifyDate:
                navBarItemAuthority.modifyDate != null && navBarItemAuthority.modifyDate.isValid()
                    ? navBarItemAuthority.modifyDate.toJSON()
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
        res.body.forEach((navBarItemAuthority: INavBarItemAuthorityMarineSuffix) => {
            navBarItemAuthority.createDate = navBarItemAuthority.createDate != null ? moment(navBarItemAuthority.createDate) : null;
            navBarItemAuthority.modifyDate = navBarItemAuthority.modifyDate != null ? moment(navBarItemAuthority.modifyDate) : null;
        });
        return res;
    }
}
