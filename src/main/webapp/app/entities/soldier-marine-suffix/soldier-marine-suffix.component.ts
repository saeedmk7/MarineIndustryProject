import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { SoldierMarineSuffixService } from './soldier-marine-suffix.service';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IEmploymentTypeMarineSuffix} from "app/shared/model/employment-type-marine-suffix.model";
import {IQualificationMarineSuffix} from "app/shared/model/qualification-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {EmploymentTypeMarineSuffixService} from "app/entities/employment-type-marine-suffix";
import {QualificationMarineSuffixService} from "app/entities/qualification-marine-suffix";
import {PlatformLocation} from "@angular/common";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {TranslateService} from '@ngx-translate/core';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

@Component({
    selector: 'mi-soldier-marine-suffix',
    templateUrl: './soldier-marine-suffix.component.html'
})
export class SoldierMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    soldiers: ISoldierMarineSuffix[];
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

    currentPerson: IPersonMarineSuffix;
    orgIds: number[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    allOrganizationcharts: IOrganizationChartMarineSuffix[];
    employmenttypes: IEmploymentTypeMarineSuffix[];
    qualifications: IQualificationMarineSuffix[];

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = [];
    done:boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isModirAmozesh2: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    constructor(
        protected soldierService: SoldierMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        private qualificationService: QualificationMarineSuffixService,
        private location: PlatformLocation,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService : ConvertObjectDatesService,
        private treeUtilities : TreeUtilities
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });
    }

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.soldiers, 'soldiers', 'marineindustryprojApp.soldier');
    }

    loadAll(criteria?) {
        criteria = this.makeCriteria(criteria);
        debugger;
        this.soldierService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ISoldierMarineSuffix[]>) => this.paginateSoldiers(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    makeCriteria(criteria?){

        if(!criteria)
        {
            criteria = [];

        }
        else{

            /*const retiredOption = criteria.find(a => a.key == 'retired.equals');
            if(retiredOption){
                let val = +retiredOption.value;
                //criteria.pop('yearId');
                criteria = criteria.filter(a => a.key != 'retired.equals');
                if (val == 1) {
                    criteria.push({
                        key: 'employmentDate.lessOrEqualThan',
                        value: this.convertObjectDatesService.get30YearsBeforeNow()
                    });
                }
                else if(val == 2){
                    criteria.push({
                        key: 'employmentDate.greaterOrEqualThan',
                        value: this.convertObjectDatesService.get30YearsBeforeNow()
                    });
                }
            }*/
            /*const archived = criteria.find(a => a.key == 'archived.equals');
            if(archived) {
                const archivedValue = +archived.value;
                criteria = criteria.filter(a => a.key != 'archived.equals');
                if (archivedValue) {
                    criteria.push({
                        key: 'archived.equals', value: true
                    });
                }
                else{
                    criteria.push({
                        key: 'archived.equals', value: false
                    });
                }
            }*/
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
        }
        const orgIn = criteria.find(a => a.key == 'organizationChartId.in');
        if(!orgIn && !this.isSuperUsers && this.isTopUsers) {
            criteria.push({
                key: 'organizationChartId.in',
                value: this.orgIds
            });
        }
        return criteria;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/soldier-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        //this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/soldier-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.searchbarModel.push(new SearchPanelModel('soldier','name','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('soldier','family','text','contains'));
        this.searchbarModel.push(new SearchPanelModel('soldier','nationalId','text','contains'));
        this.searchbarModel.push(new SearchPanelModel('soldier','personelCode','text','contains'));
        this.searchbarModel.push(new SearchPanelModel('soldier','phoneNumber','text','contains'));
        /*const retiredOptions = [{
            id: 0,
            title: 'همه'
        },{
            id: 1,
            title: 'بازنشست شده'
        },{
            id: 2,
            title: 'بازنشست نشده'
        }];*/
        //this.searchbarModel.push(new SearchPanelModel('person','retired','select','equals', retiredOptions));
        const archivedOptions = [{
            id: 0,
            title: 'خیر'
        },{
            id: 1,
            title: 'بلی'
        }];
        this.searchbarModel.push(new SearchPanelModel('soldier','archived','select','equals', archivedOptions));
        //this.prepareEmploymentType();
        this.prepareQualification();
        this.accountService.identity().then((account) => {
            this.setRoles(account);
            this.personService.find(account.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;
                this.prepareSearchOrgChart();
            }, (res: HttpErrorResponse) => this.onError(res.message));
        });
        //this.registerChangeInSoldiers();
    }

    private setRoles(account: any){
        if(account) {
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
                this.isModirAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_AMOZESH_2") !== undefined)
                this.isModirAmozesh2 = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
                this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
                this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh || this.isModirAmozesh2)
                this.isTopUsers = true;
        }
    }
    prepareQualification(){
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {

                this.employmenttypes = res.body;

                this.searchbarModel.push(new SearchPanelModel('soldier','lastQualificationId','select','equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.allOrganizationcharts = this.organizationChartService.organizationchartsAll;
            this.organizationcharts = this.convertObjectDatesService.goClone(this.allOrganizationcharts);
            this.orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId);
            this.organizationcharts = this.organizationcharts.filter(a => this.orgIds.includes(a.id));
            const groups = this.organizationcharts.filter(a => a.parentId == null);
            this.searchbarModel.push(new SearchPanelModel('soldier', 'organizationChartId', 'select', 'equals', groups, 'title'));
            //this.searchbarModel.push(new SearchPanelModel('person', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.allOrganizationcharts = res.body;
                    this.organizationcharts = this.convertObjectDatesService.goClone(this.allOrganizationcharts);
                    this.orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId);
                    this.organizationcharts = this.organizationcharts.filter(a => this.orgIds.includes(a.id));
                    const groups = this.organizationcharts.filter(a => a.parentId == null);
                    this.searchbarModel.push(new SearchPanelModel('person', 'organizationChartId', 'select', 'equals', groups, 'title'));
                    //this.searchbarModel.push(new SearchPanelModel('person', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: ISoldierMarineSuffix) {
        return item.id;
    }

    registerChangeInSoldiers() {
        this.eventSubscriber = this.eventManager.subscribe('soldierListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateSoldiers(data: ISoldierMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.soldiers = this.convertObjectDatesService.changeArrayDate(data);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
