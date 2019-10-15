import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';

type EntityResponseType = HttpResponse<IInvestToGroupTransactionMarineSuffix>;
type EntityArrayResponseType = HttpResponse<IInvestToGroupTransactionMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class InvestToGroupTransactionMarineSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/invest-to-group-transactions';

    constructor(protected http: HttpClient) {}

    create(investToGroupTransaction: IInvestToGroupTransactionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(investToGroupTransaction);
        return this.http
            .post<IInvestToGroupTransactionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(investToGroupTransaction: IInvestToGroupTransactionMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(investToGroupTransaction);
        return this.http
            .put<IInvestToGroupTransactionMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInvestToGroupTransactionMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInvestToGroupTransactionMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(
        investToGroupTransaction: IInvestToGroupTransactionMarineSuffix
    ): IInvestToGroupTransactionMarineSuffix {
        const copy: IInvestToGroupTransactionMarineSuffix = Object.assign({}, investToGroupTransaction, {
            createDate:
                investToGroupTransaction.createDate != null && investToGroupTransaction.createDate.isValid()
                    ? investToGroupTransaction.createDate.toJSON()
                    : null,
            modifyDate:
                investToGroupTransaction.modifyDate != null && investToGroupTransaction.modifyDate.isValid()
                    ? investToGroupTransaction.modifyDate.toJSON()
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
            res.body.forEach((investToGroupTransaction: IInvestToGroupTransactionMarineSuffix) => {
                investToGroupTransaction.createDate =
                    investToGroupTransaction.createDate != null ? moment(investToGroupTransaction.createDate) : null;
                investToGroupTransaction.modifyDate =
                    investToGroupTransaction.modifyDate != null ? moment(investToGroupTransaction.modifyDate) : null;
            });
        }
        return res;
    }
}
