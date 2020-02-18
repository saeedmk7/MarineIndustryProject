import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { SoldierTrainingReportMarineSuffixService } from './soldier-training-report-marine-suffix.service';
import {IMediaProductTypeMarineSuffix} from "app/shared/model/media-product-type-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {ISoldierMarineSuffix} from "app/shared/model/soldier-marine-suffix.model";
import {SoldierMarineSuffixService} from "app/entities/soldier-marine-suffix";
import {MONTHS} from "app/shared/constants/months.constants";

@Component({
    selector: 'mi-soldier-training-report-marine-suffix',
    templateUrl: './soldier-training-report-marine-suffix.component.html'
})
export class SoldierTrainingReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    soldierTrainingReports: ISoldierTrainingReportMarineSuffix[];
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
    done:boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    criteriaSubscriber: Subscription;

    constructor(
        protected soldierTrainingReportService: SoldierTrainingReportMarineSuffixService,
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
        private soldierService: SoldierMarineSuffixService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }
    export() {

        /*let a = new ExcelService(this.jhiTranslate);
        let ee = this.mediaAwarenessReports.map(w => {
            return { id: w.id, organizationChart: w.organizationChartTitle, mediaProductType: w.mediaProductTypeTitle, designed: w.designed,
                subject: w.subject, publishDate: w.publishDate, numberOfViewers: w.numberOfViewers, durationOfOperation: w.durationOfOperation,
                reportTime: w.reportTime, personHour: w.personHour }
        });
        a.exportAsExcelFile(ee, 'mediaAwarenessReports', 'marineindustryprojApp.mediaAwarenessReport');*/
    }
    loadAll(criteria?) {

        if(!criteria)
            criteria = [];

        /*if(criteria){
            const org = criteria.find(a => a.key == 'organizationChartId.equals');
            if(org) {
                const orgId = +org.value;
                criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
                if (orgId) {
                    const childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, orgId);
                    criteria.push({
                        key: 'organizationChartId.in', value: childIds
                    });
                }
            }

            if(!criteria.find(a => a.key == 'organizationChartId.in'))
            {
                if(!this.isSuperUsers)
                {
                    criteria = [{
                        key: 'organizationChartId.in',
                        value: this.recommenedOrgCharts.map(a => a.id)
                    }];
                }
            }
        }*/

        this.soldierTrainingReportService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ISoldierTrainingReportMarineSuffix[]>) => this.paginateSoldierTrainingReports(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    setRoles(account: any){

        if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
            this.isModirAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
            this.isModirKolAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/soldier-training-report-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/soldier-training-report-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;

            this.setRoles(account);

            this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport','title','text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport','executiveTrainingCompany','text', 'contains'));
            this.prepareSearchDate();
            this.prepareSearchMonth();
            this.soldierService.query().subscribe(
                (res: HttpResponse<ISoldierMarineSuffix[]>) => {
                    //this.soldiers = res.body;
                    this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport','soldierId','select', 'equals', res.body, 'fullNameAndNationalId'));

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
        //this.registerChangeInSoldierTrainingReports();
    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            const groups = this.organizationcharts.filter(a => a.parentId == null);
            this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport', 'soldierOrganizationChartId', 'select', 'equals', groups, 'title'));
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    const groups = this.organizationcharts.filter(a => a.parentId == null);
                    this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport', 'soldierOrganizationChartId', 'select', 'equals', groups, 'title'));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    prepareSearchDate(){
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport', 'year', 'select', 'equals', dates, 'title'));
    }
    prepareSearchMonth(){
        this.searchbarModel.push(new SearchPanelModel('soldierTrainingReport', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: ISoldierTrainingReportMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSoldierTrainingReports() {
        this.eventSubscriber = this.eventManager.subscribe('soldierTrainingReportListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateSoldierTrainingReports(data: ISoldierTrainingReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.soldierTrainingReports = this.convertObjectDatesService.changeArrayDate(data);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
