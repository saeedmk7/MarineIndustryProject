import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { IRunPhaseSaveDataModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data.model';

type EntityResponseType = HttpResponse<IRunPhaseMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IRunPhaseMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RunPhaseMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/run-phases';

    constructor(private http: HttpClient) {}

    uploadFile(formdata: FormData): Observable<HttpEvent<{}>> {
        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const url = this.resourceUrl + '/upload-file';
        const req = new HttpRequest('POST', url, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }
    deleteFile(address: string): Observable<HttpResponse<any>> {
        let fileName = address.split('/')[address.split('/').length - 1];
        return this.http.delete<any>(`${this.resourceUrl}/delete/${fileName}`, { observe: 'response' });
    }
    create(runPhase: IRunPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runPhase);
        return this.http
            .post<IRunPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(runPhase: IRunPhaseMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(runPhase);
        return this.http
            .put<IRunPhaseMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRunPhaseMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRunPhaseMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    saveRunPhaseModel(runPhaseSaveModel?: IRunPhaseSaveDataModel): Observable<EntityResponseType> {
        const url = this.resourceUrl + '/save-data-model';
        return this.http
            .post<IRunPhaseMarineSuffix>(url, runPhaseSaveModel, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
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

    private convertDateFromClient(runPhase: IRunPhaseMarineSuffix): IRunPhaseMarineSuffix {
        const copy: IRunPhaseMarineSuffix = Object.assign({}, runPhase, {
            doneDate: runPhase.doneDate != null && runPhase.doneDate.isValid() ? runPhase.doneDate.toJSON() : null,
            createDate: runPhase.createDate != null && runPhase.createDate.isValid() ? runPhase.createDate.toJSON() : null,
            modifyDate: runPhase.modifyDate != null && runPhase.modifyDate.isValid() ? runPhase.modifyDate.toJSON() : null,
            archivedDate: runPhase.archivedDate != null && runPhase.archivedDate.isValid() ? runPhase.archivedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.doneDate = res.body.doneDate != null ? moment(res.body.doneDate) : null;
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        res.body.archivedDate = res.body.archivedDate != null ? moment(res.body.archivedDate) : null;
        res.body.educationalModuleTotalTime =
            (res.body.learningTimePractical ? res.body.learningTimePractical : 0) +
            (res.body.learningTimeTheorical ? res.body.learningTimeTheorical : 0);
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((runPhase: IRunPhaseMarineSuffix) => {
            runPhase.doneDate = runPhase.doneDate != null ? moment(runPhase.doneDate) : null;
            runPhase.createDate = runPhase.createDate != null ? moment(runPhase.createDate) : null;
            runPhase.modifyDate = runPhase.modifyDate != null ? moment(runPhase.modifyDate) : null;
            runPhase.archivedDate = runPhase.archivedDate != null ? moment(runPhase.archivedDate) : null;
            runPhase.educationalModuleTotalTime =
                (runPhase.learningTimeTheorical ? runPhase.learningTimeTheorical : 0) +
                (runPhase.learningTimePractical ? runPhase.learningTimePractical : 0);
        });
        return res;
    }
}
