import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';

type EntityResponseType = HttpResponse<INavBarItemMarineSuffix>;
type EntityArrayResponseType = HttpResponse<INavBarItemMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class NavBarItemMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/nav-bar-items';

    constructor(private http: HttpClient) {}

    create(navBarItem: INavBarItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(navBarItem);
        return this.http
            .post<INavBarItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(navBarItem: INavBarItemMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(navBarItem);
        return this.http
            .put<INavBarItemMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INavBarItemMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INavBarItemMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(navBarItem: INavBarItemMarineSuffix): INavBarItemMarineSuffix {
        const copy: INavBarItemMarineSuffix = Object.assign({}, navBarItem, {
            createDate: navBarItem.createDate != null && navBarItem.createDate.isValid() ? navBarItem.createDate.toJSON() : null,
            modifyDate: navBarItem.modifyDate != null && navBarItem.modifyDate.isValid() ? navBarItem.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((navBarItem: INavBarItemMarineSuffix) => {
            navBarItem.createDate = navBarItem.createDate != null ? moment(navBarItem.createDate) : null;
            navBarItem.modifyDate = navBarItem.modifyDate != null ? moment(navBarItem.modifyDate) : null;
        });
        return res;
    }
}
