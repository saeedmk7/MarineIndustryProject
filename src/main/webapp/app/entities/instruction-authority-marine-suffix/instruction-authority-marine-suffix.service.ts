import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';

type EntityResponseType = HttpResponse<IInstructionAuthorityMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IInstructionAuthorityMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class InstructionAuthorityMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/instruction-authorities';

    constructor(protected http: HttpClient) {}

    create(instructionAuthority: IInstructionAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(instructionAuthority);
        return this.http
            .post<IInstructionAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(instructionAuthority: IInstructionAuthorityMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(instructionAuthority);
        return this.http
            .put<IInstructionAuthorityMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInstructionAuthorityMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInstructionAuthorityMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(instructionAuthority: IInstructionAuthorityMarineSuffix): IInstructionAuthorityMarineSuffix {
        const copy: IInstructionAuthorityMarineSuffix = Object.assign({}, instructionAuthority, {
            createDate:
                instructionAuthority.createDate != null && instructionAuthority.createDate.isValid()
                    ? instructionAuthority.createDate.toJSON()
                    : null,
            modifyDate:
                instructionAuthority.modifyDate != null && instructionAuthority.modifyDate.isValid()
                    ? instructionAuthority.modifyDate.toJSON()
                    : null
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
            res.body.forEach((instructionAuthority: IInstructionAuthorityMarineSuffix) => {
                instructionAuthority.createDate = instructionAuthority.createDate != null ? moment(instructionAuthority.createDate) : null;
                instructionAuthority.modifyDate = instructionAuthority.modifyDate != null ? moment(instructionAuthority.modifyDate) : null;
            });
        }
        return res;
    }
}
