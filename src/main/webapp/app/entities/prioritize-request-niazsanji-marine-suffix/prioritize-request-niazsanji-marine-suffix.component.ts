import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import {AccountService, IUser, Principal, UserService} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from './prioritize-request-niazsanji-marine-suffix.service';
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix',
    templateUrl: './prioritize-request-niazsanji-marine-suffix.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    prioritizeRequestNiazsanjis: IPrioritizeRequestNiazsanjiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    recommenedPeople: IPersonMarineSuffix[];
    users: IUser[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
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
    isAdmin: boolean;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;

    yearsCollections: any[];
    coursetypes: ICourseTypeMarineSuffix[];

    totalHour: number;
    totalPriceCost: number;

    constructor(
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
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
        private courseTypeService: CourseTypeMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
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
                if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                    this.isAdmin = true;

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

    loadAll(criteria?,excelExport: boolean = false) {
        if(!this.isAdmin)
        {
            criteria.push({
                key: 'status.equals',
                value: 0
            });
        }
        if(excelExport) {
        }
        else {
            this.prioritizeRequestNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix[]>) =>
                        this.paginatePrioritizeRequestNiazsanjis(res.body, res.headers),
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

    transition() {
        this.router.navigate(['/prioritize-request-niazsanji-marine-suffix'], {
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
            '/prioritize-request-niazsanji-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                this.currentPerson = resp.body;
                /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'number', 'equals'));*/
                this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleCode', 'text', 'contains'));
                this.prepareSearchOrgChart();
                this.prepareDate();
                this.prepareSearchEducationalModule();
                this.prepareSearchCourseType();
            })
        });
    }
    prepareSearchCourseType(){
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {

                this.coursetypes = res.body;
                this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'courseTypeId', 'select', 'equals', this.coursetypes));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));

    }
    prepareSearchEducationalModule() {

        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
            /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
            /*if (!this.done) {
                this.makeCriteria();
            }*/
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                    /*if (!this.done) {
                        this.makeCriteria();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    prepareSearchPerson() {
        if (this.personService.people) {
            this.people = this.personService.people;
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
            this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'personId', 'select', 'equals', this.recommenedPeople, 'fullName', 'half'));
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
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'personId', 'select', 'equals', this.recommenedPeople, 'fullName', 'half'));
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.handleOrgChartView(), 'fullTitle', 'half'));
            this.prepareSearchPerson();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.handleOrgChartView(), 'fullTitle', 'half'));
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
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IPrioritizeRequestNiazsanjiMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInPrioritizeRequestNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('prioritizeRequestNiazsanjiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginatePrioritizeRequestNiazsanjis(data: IPrioritizeRequestNiazsanjiMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.prioritizeRequestNiazsanjis = this.convertObjectDatesService.changeArrayDate(data, true);
        if(!this.educationalModules) {
            if (this.educationalModuleService.educationalModules) {
                this.educationalModules = this.educationalModuleService.educationalModules;
                this.prepareResult();
                /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                /*if (!this.done) {
                    this.makeCriteria();
                }*/
            }
            else {
                this.educationalModuleService.query().subscribe(
                    (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                        this.educationalModules = res.body;
                        this.prepareResult();
                        /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                        /*if (!this.done) {
                            this.makeCriteria();
                        }*/
                    },
                    (res: HttpErrorResponse) => this.onError(res.message))
            }
        }
        else{
            this.prepareResult();
        }
    }

    prepareResult(){
        this.prioritizeRequestNiazsanjis.forEach((a: IPrioritizeRequestNiazsanjiMarineSuffix) => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);

            let education: IEducationalModuleMarineSuffix;
            if (a.educationalModuleId) {
                education = this.educationalModules.find(w => w.id == a.educationalModuleId);
            }
            if (education) {
                a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
                a.totalLearningTime = (education.learningTimePractical ? education.learningTimePractical : 0) + (education.learningTimeTheorical ? education.learningTimeTheorical : 0)
            }
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartRootTitle = org.fullTitle.split('>')[0];
        });1
        this.totalHour = this.prioritizeRequestNiazsanjis.filter(a => a.totalLearningTime).map(a => a.totalLearningTime).reduce((sum, current) => sum + current);
        this.totalPriceCost = this.prioritizeRequestNiazsanjis.filter(a => a.costEducationalModule > 0)
            .map(a => a.costEducationalModule).reduce((sum, current) => sum + current);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
