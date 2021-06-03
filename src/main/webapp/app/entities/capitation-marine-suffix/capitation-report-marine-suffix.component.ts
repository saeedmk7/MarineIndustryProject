import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { AccountService, Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { CapitationMarineSuffixService } from './capitation-marine-suffix.service';
import { ICapitationReport } from 'app/shared/model/custom/capitationReportModels/capitation-report';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';

@Component({
    selector: 'mi-capitation-report-marine-suffix',
    templateUrl: './capitation-report-marine-suffix.component.html'
})
export class CapitationReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    capitationReport: ICapitationReport;
    capitation: ICapitationMarineSuffix;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    groups: IOrganizationChartMarineSuffix[];
    selectedYear: number;
    selectedGroupId: number = 0;

    yearsCollections: any[];
    years: any[];

    errorMessage: string = '';

    constructor(
        protected capitationService: CapitationMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private organizationChartService: OrganizationChartMarineSuffixService
    ) {
        this.yearsCollections = GREGORIAN_START_END_DATE;
        this.years = this.yearsCollections.map(a => a.year);
    }

    loadAll(niazsanjiYear: number, organizationChartId: number) {
        this.capitationService
            .getCapitationReport(niazsanjiYear, organizationChartId)
            .subscribe(
                (res: HttpResponse<ICapitationReport>) => (this.capitationReport = res.body),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        if (this.organizationChartService.organizationcharts) {
            this.groups = this.organizationChartService.organizationcharts.filter(w => w.parentId == null);
        } else {
            this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.groups = resp.body.filter(w => w.parentId == null);
            });
        }
    }

    search() {
        this.errorMessage = '';
        if (!this.selectedYear) return this.onError('لطفا سال نیازسنجی را مشخص نمائید.');
        let criteria = [{ key: 'capitationYear.equals', value: this.selectedYear }];
        this.capitationService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe((resp: HttpResponse<ICapitationMarineSuffix[]>) => {
                this.capitation = resp.body[0];
            });
        this.loadAll(this.selectedYear, this.selectedGroupId);
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
    }

    protected onError(errorMessage: string) {
        this.errorMessage = errorMessage;
    }
}
