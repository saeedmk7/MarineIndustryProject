import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';
import { AccountService, Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { SoldierMediaAwarenessReportMarineSuffixService } from './soldier-media-awareness-report-marine-suffix.service';
import { SoldierMarineSuffixService } from 'app/entities/soldier-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { TranslateService } from '@ngx-translate/core';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { ExcelService } from 'app/plugin/export-excel/excel-service';

@Component({
    selector: 'mi-soldier-media-awareness-report-marine-suffix',
    templateUrl: './soldier-media-awareness-report-marine-suffix.component.html'
})
export class SoldierMediaAwarenessReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    soldierMediaAwarenessReports: ISoldierMediaAwarenessReportMarineSuffix[];
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

    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];

    currentPerson: IPersonMarineSuffix;

    searchbarModel: SearchPanelModel[] = [];
    done: boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    criteriaSubscriber: Subscription;

    constructor(
        protected soldierMediaAwarenessReportService: SoldierMediaAwarenessReportMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private convertObjectDatesService: ConvertObjectDatesService,
        private soldierService: SoldierMarineSuffixService,
        protected jhiTranslate: TranslateService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }

    loadAll(criteria?, excelExport: boolean = false) {
        if (!criteria) criteria = [];
        if (excelExport) {
            this.soldierMediaAwarenessReportService
                .query({
                    page: this.page - 1,
                    size: 2000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ISoldierMediaAwarenessReportMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.soldierMediaAwarenessReportService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ISoldierMediaAwarenessReportMarineSuffix[]>) =>
                        this.paginateSoldierMediaAwarenessReports(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {}

    clear() {
        this.page = 0;
        this.router.navigate([
            '/soldier-media-awareness-report-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll(this.criteria);
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;

            this.setRoles(account);

            this.searchbarModel.push(new SearchPanelModel('soldierMediaAwarenessReport', 'title', 'text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('soldierMediaAwarenessReport', 'executiveTrainingCompany', 'text', 'contains'));
            this.prepareSearchDate();
            this.prepareSearchMonth();
            this.soldierService.query().subscribe(
                (res: HttpResponse<ISoldierMarineSuffix[]>) => {
                    //this.soldiers = res.body;
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'soldierMediaAwarenessReport',
                            'soldierId',
                            'select',
                            'equals',
                            res.body,
                            'fullNameAndNationalId'
                        )
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.prepareSearchOrgChart();
            /*this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));*/
        });
        //this.registerChangeInSoldierMediaAwarenessReports();
    }

    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }

    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            const groups = this.organizationcharts.filter(a => a.parentId == null);
            this.searchbarModel.push(
                new SearchPanelModel('soldierMediaAwarenessReport', 'soldierOrganizationChartId', 'select', 'equals', groups, 'title')
            );
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    const groups = this.organizationcharts.filter(a => a.parentId == null);
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'soldierMediaAwarenessReport',
                            'soldierOrganizationChartId',
                            'select',
                            'equals',
                            groups,
                            'title'
                        )
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    prepareSearchDate() {
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('soldierMediaAwarenessReport', 'year', 'select', 'equals', dates, 'title'));
    }

    prepareSearchMonth() {
        this.searchbarModel.push(new SearchPanelModel('soldierMediaAwarenessReport', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: ISoldierMediaAwarenessReportMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSoldierMediaAwarenessReports() {
        this.eventSubscriber = this.eventManager.subscribe('soldierMediaAwarenessReportListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    export() {
        this.loadAll(this.criteria, true);
    }

    prepareForExportExcel(res: ISoldierMediaAwarenessReportMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);
        res = this.convertObjectDatesService.changeArrayDate(res);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            index++;

            let obj: Object;
            obj = {
                index: index,
                soldierOrganizationChartTitle: a.soldierOrganizationChartTitle,
                soldierFullName: a.soldierFullName,
                soldierNationalId: a.soldierNationalId,
                soldierReleaseDate: a.soldierEmploymentDate,
                title: a.title,
                personHour: a.personHour,
                executiveTrainingCompany: a.executiveTrainingCompany,
                certificateStatus: a.certificateStatus,
                certificateNumber: a.certificateNumber,
                year: a.year,
                month: a.month,
                description: a.description,
                createDate: a.createDate
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'soldierMediaAwarenessReports', 'marineindustryprojApp.soldierMediaAwarenessReport');
    }

    protected paginateSoldierMediaAwarenessReports(data: ISoldierMediaAwarenessReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.soldierMediaAwarenessReports = this.convertObjectDatesService.changeArrayDate(data);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
