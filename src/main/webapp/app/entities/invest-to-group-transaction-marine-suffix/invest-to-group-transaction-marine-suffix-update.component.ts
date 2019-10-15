import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import * as persianMoment from 'jalali-moment';
import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';
import { InvestToGroupTransactionMarineSuffixService } from './invest-to-group-transaction-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';

@Component({
    selector: 'mi-invest-to-group-transaction-marine-suffix-update',
    templateUrl: './invest-to-group-transaction-marine-suffix-update.component.html'
})
export class InvestToGroupTransactionMarineSuffixUpdateComponent implements OnInit {
    investToGroupTransaction: IInvestToGroupTransactionMarineSuffix;
    isSaving: boolean;
    organizationcharts: IOrganizationChartMarineSuffix[];
    groups: IOrganizationChartMarineSuffix[];
    dateValid: number;
    constructor(
        protected jhiAlertService: JhiAlertService,
        protected investToGroupTransactionService: InvestToGroupTransactionMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ investToGroupTransaction }) => {
            this.investToGroupTransaction = investToGroupTransaction;
        });
        this.loadOrgCharts();
    }
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.groups = this.organizationcharts.filter(a => a.parentId == null);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.groups = this.organizationcharts.filter(a => a.parentId == null);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        debugger;
        if(this.investToGroupTransaction.investDate)
        {
            this.investToGroupTransaction.investYear = +this.investToGroupTransaction.investDate.split('/')[0];
        }
        if (this.investToGroupTransaction.id !== undefined) {
            this.subscribeToSaveResponse(this.investToGroupTransactionService.update(this.investToGroupTransaction));
        } else {
            this.subscribeToSaveResponse(this.investToGroupTransactionService.create(this.investToGroupTransaction));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvestToGroupTransactionMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IInvestToGroupTransactionMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }
    checkDateValidation(event){

        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.dateValid = 1;
            }
            else {
                this.dateValid = 2;
            }
        }
        catch (e) {
            this.dateValid = 2;
        }
    }
}
