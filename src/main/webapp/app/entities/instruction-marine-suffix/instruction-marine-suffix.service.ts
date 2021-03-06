import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';

type EntityResponseType = HttpResponse<IInstructionMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IInstructionMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class InstructionMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/instructions';

    constructor(protected http: HttpClient) {}

    create(instruction: IInstructionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(instruction);
        return this.http
            .post<IInstructionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(instruction: IInstructionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(instruction);
        return this.http
            .put<IInstructionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInstructionMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInstructionMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(instruction: IInstructionMarineSuffix): IInstructionMarineSuffix {
        const copy: IInstructionMarineSuffix = Object.assign({}, instruction, {
            createDate: instruction.createDate != null && instruction.createDate.isValid() ? instruction.createDate.toJSON() : null,
            modifyDate: instruction.modifyDate != null && instruction.modifyDate.isValid() ? instruction.modifyDate.toJSON() : null
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
            res.body.forEach((instruction: IInstructionMarineSuffix) => {
                instruction.createDate = instruction.createDate != null ? moment(instruction.createDate) : null;
                instruction.modifyDate = instruction.modifyDate != null ? moment(instruction.modifyDate) : null;
            });
        }
        return res;
    }
}
