import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import {
    IRequestEducationalModuleMarineSuffix
} from 'app/shared/model/request-educational-module-marine-suffix.model';
import { Principal } from 'app/core';

import {DATE_TIME_FORMAT, ITEMS_PER_PAGE} from 'app/shared';
import { RequestEducationalModuleMarineSuffixService } from './request-educational-module-marine-suffix.service';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import * as moment from "moment";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {el} from "@angular/platform-browser/testing/src/browser_util";
import {IEducationalHistoryMarineSuffix} from "app/shared/model/educational-history-marine-suffix.model";
import {IScientificWorkGroupMarineSuffix} from "app/shared/model/scientific-work-group-marine-suffix.model";
import {ISkillableLevelOfSkillMarineSuffix} from "app/shared/model/skillable-level-of-skill-marine-suffix.model";
import {ScientificWorkGroupMarineSuffixService} from "app/entities/scientific-work-group-marine-suffix";
import {SkillableLevelOfSkillMarineSuffixService} from "app/entities/skillable-level-of-skill-marine-suffix";
import {OrganizationMarineSuffixService} from "app/entities/organization-marine-suffix";
import {IOrganizationMarineSuffix} from "app/shared/model/organization-marine-suffix.model";

@Component({
    selector: 'mi-request-educational-module-marine-suffix',
    templateUrl: './request-educational-module-marine-suffix.component.html',
    styleUrls: ['./request-educational-module-marine-suffix.scss']
})
export class RequestEducationalModuleMarineSuffixComponent implements OnInit, OnDestroy {
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    currentAccount: any;
    requestEducationalModules: IRequestEducationalModuleMarineSuffix[];
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
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;

    yearsCollections: any[];

    searchScientificWorkGroupIds: number[];
    scientificWorkGroups: IScientificWorkGroupMarineSuffix[];
    searchSkillableLevelOfSkillIds: number[];
    skillableLevelOfSkills: ISkillableLevelOfSkillMarineSuffix[];
    organizations: IOrganizationMarineSuffix[];

    constructor(
        private requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        protected treeUtilities: TreeUtilities,
        private convertObjectDatesService : ConvertObjectDatesService,
        private educationalModuleMarineSuffixService: EducationalModuleMarineSuffixService,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        private organizationService: OrganizationMarineSuffixService,
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
        debugger;
        if (criteria) {
            /*let val = +criteria.find(a => a.key == 'yearId.equals').value;
            //criteria.pop('yearId');
            criteria = criteria.filter(a => a.key != 'yearId.equals');
            if (val) {
                let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key: 'createDate.lessOrEqualThan', value: endDate
                });
                criteria.push({
                    key: 'createDate.greaterOrEqualThan', value: beginDate
                });
            }*/
        }
        else{
            criteria = [];
        }
        if(this.currentPerson){
            if(this.organizationChartService.organizationchartsAll){
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds,criteria,excelExport);
            }
            else{
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                    this.organizationcharts = resp.body;
                    let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                    this.handleAfterChart(wantOrgIds,criteria,excelExport);
                });
            }
        }
        else{
            this.principal.identity().then(account => {

                this.currentAccount = account;
                this.setRoles(account);

                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;

                    if(this.organizationChartService.organizationchartsAll){
                        this.organizationcharts = this.organizationChartService.organizationchartsAll;
                        let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds,criteria,excelExport);
                    }
                    else{
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                            this.organizationcharts = resp.body;
                            let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds,criteria,excelExport);
                        });
                    }
                });
            });
        }

    }
    handleAfterChart(wantOrgIds: number[],criteria,excelExport: boolean = false){
        debugger;
        if(this.isSuperUsers) {

            if(criteria.find(a => a.key == 'organizationChartId.equals')){
                let val = +criteria.find(a => a.key == 'organizationChartId.equals').value;
                //criteria.pop('yearId');
                criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
                if (val) {
                    let orgs = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, val).filter(this.treeUtilities.onlyUnique);
                    criteria.push({
                        key: 'organizationChartId.in',
                        value: orgs
                    });
                }
            }

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
        debugger;
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

        if(excelExport) {
            this.requestEducationalModuleService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) =>
                        this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else{
            this.requestEducationalModuleService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) =>
                        this.paginateRequestEducationalModules(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    export() {
        this.makeCriteria(this.criteria,true);
    }
    prepareForExportExcel(res : IEducationalHistoryMarineSuffix[]){
        res = this.convertObjectDatesService.changeArrayDate(res);
        return;
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/request-educational-module-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();*/
        this.makeCriteria(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/request-educational-module-marine-suffix',
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


                //this.prepareSearchOrgChart();
                //this.prepareDate();
                this.searchbarModel.push(new SearchPanelModel('requestEducationalModule', 'title', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('requestEducationalModule', 'code', 'text', 'contains'));
                this.scientificWorkGroupService.query().subscribe(
                    (res: HttpResponse<IScientificWorkGroupMarineSuffix[]>) => {
                        this.scientificWorkGroups = res.body;
                        this.skillableLevelOfSkillService.query().subscribe(
                            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                                this.skillableLevelOfSkills = res.body;
                                this.organizationService.query().subscribe((res) => {
                                    this.organizations = res.body;
                                    this.searchbarModel.push(new SearchPanelModel('requestEducationalModule','scientificWorkGroupId','select','equals', this.scientificWorkGroups));
                                    this.searchbarModel.push(new SearchPanelModel('requestEducationalModule','skillableLevelOfSkillId','select','equals', this.skillableLevelOfSkills));
                                    this.searchbarModel.push(new SearchPanelModel('requestEducationalModule','organizationId','select','equals', this.organizations));
                                    /*this.searchbarModel.push(new SearchPanelModel('educationalModule','recommendedBy','text','contains'));*/
                                }),
                                    (res: HttpErrorResponse) => this.onError(res.message);
                            }),
                            (res: HttpErrorResponse) => this.onError(res.message);
                    }),
                    (res: HttpErrorResponse) => this.onError(res.message);

                //this.searchbarModel.push(new SearchPanelModel('requestEducationalModule', 'educationalCenter', 'text', 'contains'));
                this.prepareSearchOrgChart();
                this.prepareSearchPerson();
                if(this.isSuperUsers){

                    let status = [{id: 'ACCEPT', title: 'تایید شده'},{id: 'IGNORE', title: 'رد شده'},{id:'NEW',title: 'جدید'}];
                    this.searchbarModel.push(new SearchPanelModel('requestEducationalModule', 'requestStatus', 'select', 'equals', status));

                }

            })
        });


    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }
    /*prepareSearchPerson(orgs: IOrganizationChartMarineSuffix[]) {

        const ids = orgs.map(a => a.id);
        let criteria = [{
            key: 'organizationChartId.in',
            value: ids
        }];
        this.personService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                this.searchbarModel.push(new SearchPanelModel('requestEducationalModule', 'personId', 'select', 'equals', resp.body, 'fullName', ''));
            },
            (error) => this.onError("فردی یافت نشد."));
    }*/
    prepareSearchPerson(){
        if(this.personService.people){
            this.people = this.personService.people;
            /*if (!this.done) {
                this.makeCriteria();
            }*/
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    /*if (!this.done) {
                        this.makeCriteria();
                    }*/
                    //this.people.forEach(a => a["title"] = a.fullName);
                    //this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'personId', 'select', 'equals', this.people, "fullName"));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
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

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    prepareDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));

    }

    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            const orgs = this.handleOrgChartView();
            //this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half'));
            //this.prepareSearchPerson(orgs);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    const orgs = this.handleOrgChartView();
                    //this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half'));
                    //this.prepareSearchPerson(orgs);
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    handleOrgChartView(): IOrganizationChartMarineSuffix[]{
        if(this.isAdmin)
            return this.organizationcharts;
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
    trackId(index: number, item: IRequestEducationalModuleMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInRequestEducationalModules() {
        this.eventSubscriber = this.eventManager.subscribe('requestEducationalModuleListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateRequestEducationalModules(data: IRequestEducationalModuleMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.requestEducationalModules = this.convertObjectDatesService.changeArrayDate(data);
        this.requestEducationalModules.forEach(a => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
            const person = this.people.find(w => w.nationalId == a.createUserLogin);
            if(person)
                a.createUserLoginName = person.fullName;
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
