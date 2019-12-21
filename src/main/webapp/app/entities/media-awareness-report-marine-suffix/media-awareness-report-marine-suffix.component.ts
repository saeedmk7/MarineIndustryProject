import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { MediaAwarenessReportMarineSuffixService } from './media-awareness-report-marine-suffix.service';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IMediaProductTypeMarineSuffix} from "app/shared/model/media-product-type-marine-suffix.model";
import {MediaProductTypeMarineSuffixService} from "app/entities/media-product-type-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";

@Component({
    selector: 'mi-media-awareness-report-marine-suffix',
    templateUrl: './media-awareness-report-marine-suffix.component.html'
})
export class MediaAwarenessReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    mediaAwarenessReports: IMediaAwarenessReportMarineSuffix[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    criteriaSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    mediaproducttypes: IMediaProductTypeMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];

    currentPerson: IPersonMarineSuffix;

    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    constructor(
        protected mediaAwarenessReportService: MediaAwarenessReportMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        private personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected mediaProductTypeService: MediaProductTypeMarineSuffixService,
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

    loadAll(criteria?) {
        debugger;
        if(!criteria)
            criteria = [];
        if(!this.isSuperUsers){
            criteria = [{
                key: 'organizationChartId.in',
                value: this.recommenedOrgCharts.map(a => a.id)
            }];
        }
        this.mediaAwarenessReportService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IMediaAwarenessReportMarineSuffix[]>) => this.paginateMediaAwarenessReports(res.body, res.headers),
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
        this.router.navigate(['/media-awareness-report-marine-suffix'], {
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
            '/media-awareness-report-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.searchbarModel = new Array<SearchPanelModel>();
        this.accountService.identity().then(account => {
            this.currentAccount = account;

            this.setRoles(account);

            this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','subject','text', 'contains'));
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
            this.mediaProductTypeService.query().subscribe(
                (res: HttpResponse<IMediaProductTypeMarineSuffix[]>) => {
                    this.mediaproducttypes = res.body;
                    this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','mediaProductTypeId','select', 'equals', this.mediaproducttypes));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        /*this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','jobKey','text','contains'));*/
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
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }

    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IMediaAwarenessReportMarineSuffix) {
        return item.id;
    }

    registerChangeInMediaAwarenessReports() {
        //this.eventSubscriber = this.eventManager.subscribe('mediaAwarenessReportListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateMediaAwarenessReports(data: IMediaAwarenessReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.mediaAwarenessReports = data;
        this.mediaAwarenessReports.forEach(a => {
            debugger;
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartTitle = org.fullTitle.split('>')[0];
        });
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
