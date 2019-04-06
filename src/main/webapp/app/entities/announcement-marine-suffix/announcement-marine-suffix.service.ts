import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';

type EntityResponseType = HttpResponse<IAnnouncementMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IAnnouncementMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class AnnouncementMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/announcements';

    constructor(private http: HttpClient) {}

    create(announcement: IAnnouncementMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(announcement);
        return this.http
            .post<IAnnouncementMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(announcement: IAnnouncementMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(announcement);
        return this.http
            .put<IAnnouncementMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAnnouncementMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAnnouncementMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(announcement: IAnnouncementMarineSuffix): IAnnouncementMarineSuffix {
        const copy: IAnnouncementMarineSuffix = Object.assign({}, announcement, {
            createDate: announcement.createDate != null && announcement.createDate.isValid() ? announcement.createDate.toJSON() : null,
            modifyDate: announcement.modifyDate != null && announcement.modifyDate.isValid() ? announcement.modifyDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((announcement: IAnnouncementMarineSuffix) => {
            announcement.createDate = announcement.createDate != null ? moment(announcement.createDate) : null;
            announcement.modifyDate = announcement.modifyDate != null ? moment(announcement.modifyDate) : null;
        });
        return res;
    }
}
