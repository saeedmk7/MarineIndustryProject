import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';

@Component({
    selector: 'mi-invest-to-group-transaction-marine-suffix-detail',
    templateUrl: './invest-to-group-transaction-marine-suffix-detail.component.html'
})
export class InvestToGroupTransactionMarineSuffixDetailComponent implements OnInit {
    investToGroupTransaction: IInvestToGroupTransactionMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ investToGroupTransaction }) => {
            this.investToGroupTransaction = investToGroupTransaction;
        });
    }

    previousState() {
        window.history.back();
    }
}
