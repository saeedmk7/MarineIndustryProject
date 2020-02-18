import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import {AccountService, IUser, Principal, UserService} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PreJobNiazsanjiMarineSuffixService } from './pre-job-niazsanji-marine-suffix.service';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IRequestOrganizationNiazsanjiMarineSuffix} from "app/shared/model/request-organization-niazsanji-marine-suffix.model";
import {IRequestNiazsanjiFardiMarineSuffix} from "app/shared/model/request-niazsanji-fardi-marine-suffix.model";
import {REQUEST_STATUS_FILTERS} from "app/shared/constants/RequestStatusFilters";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";

@Component({
    selector: 'mi-pre-job-niazsanji-marine-suffix',
    templateUrl: './pre-job-niazsanji-marine-suffix.component.html',
    styleUrls: ['./pre-job-niazsanji-marine-suffix.scss']
})
export class PreJobNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    preJobNiazsanjis: IPreJobNiazsanjiMarineSuffix[];
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
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;

    yearsCollections: any[];

    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    recommenedPeople: IPersonMarineSuffix[];
    users: IUser[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isKarshenasAmozesh: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    constructor(
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected convertObjectDatesService: ConvertObjectDatesService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected treeUtilities: TreeUtilities,
        protected userService: UserService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });

        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) => {


            this.criteria = criteria.content;
            this.done = true;
            this.makeCriteria(criteria.content);

        });

        this.yearsCollections = GREGORIAN_START_END_DATE;
    }
    makeCriteria(criteria?,excelExport: boolean = false){


        if (criteria) {
            criteria = this.commonSearchCheckerService.checkYear(criteria);
        }
        else{
            criteria = [];
        }
        if(this.currentPerson){
            if(this.organizationChartService.organizationchartsAll){
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds,criteria,excelExport);
            }
            else{
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                    this.organizationcharts = resp.body;
                    criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                    let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                    this.handleAfterChart(wantOrgIds,criteria,excelExport);
                });
            }
        }
        else{
            this.principal.identity().then(account => {

                this.currentAccount = account;
                this.setRoles(this.currentAccount);

                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;

                    if(this.organizationChartService.organizationchartsAll){
                        this.organizationcharts = this.organizationChartService.organizationchartsAll;
                        criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                        let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds,criteria,excelExport);
                    }
                    else{
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                            this.organizationcharts = resp.body;
                            criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                            let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds,criteria,excelExport);
                        });
                    }
                });
            });
        }

    }
    handleAfterChart(wantOrgIds: number[],criteria,excelExport: boolean = false){
        criteria = this.commonSearchCheckerService.checkRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
        if(this.isAdmin) {
            this.loadAll(criteria, excelExport);
            return;
        }
        if(wantOrgIds.length > 0) {
            let criteria1 = [{
                key: 'organizationChartId.in',
                value: wantOrgIds
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria: criteria1,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {

                let selectedPeople = resp.body;
                if (selectedPeople.length > 0) {
                    let logins: string[] = selectedPeople.map(a => a.nationalId);
                    logins.push(this.currentPerson.nationalId);
                    criteria.push({
                        key: 'createUserLogin.in',
                        value: logins
                    });
                }
                else {
                    let logins = [this.currentPerson.nationalId];
                    criteria.push({
                        key: 'createUserLogin.in',
                        value: logins
                    });
                }
                this.loadAll(criteria, excelExport);
            });
        }
        else{
            criteria.push({
                key: 'createUserLogin.in',
                value: [this.currentPerson.nationalId]
            });
            this.loadAll(criteria, excelExport);
        }
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
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_AMOZESH") !== undefined)
            this.isKarshenasAmozesh = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    loadAll(criteria?,excelExport: boolean = false) {

        if(!this.isSuperUsers)
        {
            let orgs = this.treeUtilities.getParentIds(this.organizationcharts,this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            if(orgs.length > 0){
                orgs.push(0);
                criteria.push({
                    key: 'status.in',
                    value: orgs
                });
            }
            else{
                orgs = [0];
                criteria.push({
                    key: 'status.equals',
                    value: orgs
                });
            }
        }
        if(excelExport){
            this.preJobNiazsanjiService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.preJobNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IPreJobNiazsanjiMarineSuffix[]>) => this.paginatePreJobNiazsanjis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    prepareForExportExcel(res : IRequestNiazsanjiFardiMarineSuffix[]) {
        res = this.convertObjectDatesService.changeArrayDate(res);
        //this.exportRequestsFinal(res);
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/pre-job-niazsanji-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.makeCriteria(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/pre-job-niazsanji-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.makeCriteria(this.criteria);
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                this.currentPerson = resp.body;
                /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'number', 'equals'));*/
                //this.searchbarModel.push(new SearchPanelModel('preJobNiazsanji', 'title', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel("requestNiazsanjiFardi", 'requestStatusFilters', 'select', 'equals', REQUEST_STATUS_FILTERS))
                this.prepareSearchOrgChart();
                this.prepareDate();
            })
        });
    }
    prepareDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));

    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.searchbarModel.push(new SearchPanelModel('preJobNiazsanji', 'organizationChartId', 'select', 'equals', this.handleOrgChartView(), 'fullTitle', 'half'));
            this.prepareSearchPerson();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    this.searchbarModel.push(new SearchPanelModel('preJobNiazsanji', 'organizationChartId', 'select', 'equals', this.handleOrgChartView(), 'fullTitle', 'half'));
                    this.prepareSearchPerson();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    handleOrgChartView(): IOrganizationChartMarineSuffix[]{
        if(this.isAdmin) {
            this.recommenedOrgCharts = this.organizationcharts;
            return this.recommenedOrgCharts;
        }
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }
        return this.recommenedOrgCharts;
    }
    prepareSearchPerson() {
        if (this.personService.people) {
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
            this.handlePeople();
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    this.handlePeople();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    handlePeople(){
        if(this.recommenedOrgCharts.length == this.organizationcharts.length) {
            this.recommenedPeople = this.people;
            this.searchbarModel.push(new SearchPanelModel('preJobNiazsanji', 'personId', 'select', 'equals', this.recommenedPeople, 'fullName', 'half'));
        }
        else {

            const orgIds = this.recommenedOrgCharts.map(a => a.id);
            let criteria = [{
                key: 'organizationChartId.in',
                value: orgIds
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {

                    let orgPeople = resp.body;
                    if (orgPeople.length > 0) {
                        this.recommenedPeople = orgPeople;
                    }
                    else {
                        this.recommenedPeople = [];
                    }
                    this.searchbarModel.push(new SearchPanelModel('preJobNiazsanji', 'personId', 'select', 'equals', this.recommenedPeople, 'fullName', 'half'));
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    toggleImportantMessage(id: number, type: boolean){

        this.preJobNiazsanjiService.toggleImportantMessage(id, type).subscribe(
            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.makeCriteria(this.criteria),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveError() {

    }
    trackId(index: number, item: IPreJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInPreJobNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('preJobNiazsanjiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginatePreJobNiazsanjis(data: IPreJobNiazsanjiMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        this.preJobNiazsanjis = this.convertObjectDatesService.changeArrayDate(data, true);
        this.preJobNiazsanjis.forEach(a => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartTitle = org.fullTitle;
        })
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
