import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EvaluateCriteriaDataMarineSuffixService } from './evaluate-criteria-data-marine-suffix.service';
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TranslateService} from '@ngx-translate/core';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {MONTHS} from "app/shared/constants/months.constants";

@Component({
    selector: 'mi-evaluate-criteria-data-marine-suffix',
    templateUrl: './evaluate-criteria-data-marine-suffix.component.html'
})
export class EvaluateCriteriaDataMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    evaluateCriteriaDatas: IEvaluateCriteriaDataMarineSuffix[];
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

    criteriaSubscriber: Subscription;

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

    constructor(
        protected evaluateCriteriaDataService: EvaluateCriteriaDataMarineSuffixService,
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
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {

        this.routeData = this.activatedRoute.data.subscribe(data => {
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

        let a = new ExcelService(this.jhiTranslate);
        /*let ee = this.mediaAwarenessReports.map(w => {
            return { id: w.id, organizationChart: w.organizationChartTitle, mediaProductType: w.mediaProductTypeTitle, designed: w.designed,
                subject: w.subject, publishDate: w.publishDate, numberOfViewers: w.numberOfViewers, durationOfOperation: w.durationOfOperation,
                reportTime: w.reportTime, personHour: w.personHour }
        });
        a.exportAsExcelFile(ee, 'mediaAwarenessReports', 'marineindustryprojApp.mediaAwarenessReport');*/
    }

    loadAll(criteria?) {
        if(!criteria)
            criteria = [];

        if(criteria){
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
        }
        this.evaluateCriteriaDataService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEvaluateCriteriaDataMarineSuffix[]>) => this.paginateEvaluateCriteriaData(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/evaluate-criteria-data-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();*/
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/evaluate-criteria-data-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        //this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);

            this.searchbarModel.push(new SearchPanelModel('evaluateCriteriaTraining','processTitle','text', 'contains'));
            /*this.searchbarModel.push(new SearchPanelModel('evaluateCriteriaTraining','reportTime','text', 'contains'));*/
            this.prepareSearchDate();
            this.prepareSearchMonth();
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        });
        //this.registerChangeInEvaluateCriteriaData();
    }

    prepareSearchDate(){
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('evaluateCriteriaTraining', 'year', 'select', 'equals', dates, 'title'));
    }
    prepareSearchMonth(){
        this.searchbarModel.push(new SearchPanelModel('evaluateCriteriaTraining', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
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

    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.handleOrgChartView();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.handleOrgChartView();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    handleOrgChartView(){
        if(this.isSuperUsers) {
            this.recommenedOrgCharts = this.organizationcharts;
        }
        else {
            if (this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
                let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
            }
            else {
                this.recommenedOrgCharts = [];
                this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
            }
        }
        this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','organizationChartId','select', 'equals', this.recommenedOrgCharts.filter(a => a.parentId == null)));
        /*this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','organizationChartId','select', 'equals', this.recommenedOrgCharts, 'fullTitle', 'bighalf'));*/
    }


    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEvaluateCriteriaDataMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInEvaluateCriteriaData() {
        this.eventSubscriber = this.eventManager.subscribe('evaluateCriteriaDataListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateEvaluateCriteriaData(data: IEvaluateCriteriaDataMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.evaluateCriteriaDatas = this.convertObjectDatesService.changeArrayDate(data);
        this.evaluateCriteriaDatas.forEach(a => {

            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartTitle = org.fullTitle.split('>')[0];
        });
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
