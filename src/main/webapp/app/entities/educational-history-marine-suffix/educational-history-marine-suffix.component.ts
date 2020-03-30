import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EducationalHistoryMarineSuffixService } from './educational-history-marine-suffix.service';
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix/person-marine-suffix.service";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix/organization-chart-marine-suffix.service";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {Principal} from "app/core/auth/principal.service";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {IRequestOrganizationNiazsanjiMarineSuffix} from "app/shared/model/request-organization-niazsanji-marine-suffix.model";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'mi-educational-history-marine-suffix',
    templateUrl: './educational-history-marine-suffix.component.html'
})
export class EducationalHistoryMarineSuffixComponent implements OnInit, OnDestroy {
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    groups: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    courseTypes: ICourseTypeMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    currentAccount: any;
    educationalHistories: IEducationalHistoryMarineSuffix[];
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
    unchangedCriteria: any;

    yearsCollections: any[];

    sumPractical: number;
    sumTheorical: number;
    sumTotal: number;

    constructor(
        protected educationalHistoryService: EducationalHistoryMarineSuffixService,
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
        private courseTypeService: CourseTypeMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        protected jhiTranslate: TranslateService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) => {

            this.criteria = criteria.content;
            this.unchangedCriteria = criteria.content;
            this.done = true;
            this.makeCriteria(criteria.content);

        });

        this.yearsCollections = GREGORIAN_START_END_DATE;
    }

    makeCriteria(criteria?,excelExport: boolean = false){

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

        if(this.isSuperUsers) {

            const organizationChartIdFilter = criteria.find(a => a.key == 'organizationChartId.equals');
            if(organizationChartIdFilter){
                let val = +organizationChartIdFilter.value;
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

                criteria = this.criteria.filter(a => a.key != "createUserLogin.in");
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

        if(!this.isSuperUsers)
        {
            if(!criteria.find(a => a.key == "status.in")) {
                let orgs = this.treeUtilities.getParentIds(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                if (orgs.length > 0) {
                    orgs.push(0);
                    criteria.push({
                        key: 'status.in',
                        value: orgs
                    });
                }
                else {
                    orgs = [0];
                    criteria.push({
                        key: 'status.equals',
                        value: orgs
                    });
                }
            }
        }
        if(excelExport) {
            this.educationalHistoryService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IEducationalHistoryMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.educationalHistoryService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IEducationalHistoryMarineSuffix[]>) => this.paginateEducationalHistories(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    export() {
        this.makeCriteria(this.unchangedCriteria,true);
    }
    prepareForExportExcel(res : IEducationalHistoryMarineSuffix[]){
        let a = new ExcelService(this.jhiTranslate);
        res = this.convertObjectDatesService.changeArrayDate(res);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            index++;
            debugger;
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartTitle = org.fullTitle;

            //let person = this.people.find(w => w.id == a.personId);

            //let educationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);

            let obj: Object;
            obj = {'index': index,
                'organizationChart': a.organizationChartTitle,
                'person': a.personFullName,
                'educationalModuleName': a.educationalModuleName,
                'educationalModuleId': a.educationalModuleCode,
                'totalLearningTime': a.totalTime,
                'courseType': a.courseTypeTitle,
                'educationalCenter': a.educationalCenter,
                'dateOfStart': a.dateOfStart,
                'description': a.description,
                'createDate': a.createDate,
                'status': this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus)
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'educationalHistories', 'marineindustryprojApp.educationalHistory');
    }
    loadPage(page: number) {

        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    toggleImportantMessage(id: number, type: boolean){

        this.educationalHistoryService.toggleImportantMessage(id, type).subscribe(
            (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.makeCriteria(this.criteria),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveError() {

    }
    transition() {
        /*this.router.navigate(['/educational-history-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/

        //this.makeCriteria(this.unchangedCriteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/educational-history-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.makeCriteria(this.unchangedCriteria);
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);


            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                this.currentPerson = resp.body;
                //this.prepareSearchOrgChart();
                //this.prepareDate();
                this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'educationalModuleName', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'educationalCenter', 'text', 'contains'));
                this.prepareSearchOrgChart();
                this.courseTypeService.query().subscribe(
                    (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                        this.courseTypes = res.body;
                        this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'courseTypeId', 'select', 'equals', this.courseTypes));
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                )
                if(this.isSuperUsers){

                    let status = [{id: 'ACCEPT', title: 'تایید شده'},{id: 'IGNORE', title: 'رد شده'},{id:'NEW',title: 'جدید'}];
                    this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'requestStatus', 'select', 'equals', status));

                }

            })
        });

        /*if (!this.done) {
            this.makeCriteria();
        }*/
    }
    prepareSearchPerson(orgs: IOrganizationChartMarineSuffix[]) {

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
                this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'personId', 'select', 'equals', resp.body, 'fullName', ''));
            },
            (error) => this.onError("فردی یافت نشد."));
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
            if(this.isSuperUsers) {
                this.groups = this.organizationcharts.filter(a => a.parentId == null).sort((a, b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0);
                this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', this.groups, 'fullTitle', 'half'));
            }
            const orgs = this.handleOrgChartView();
            this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half'));
            this.prepareSearchPerson(orgs);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    if(this.isSuperUsers) {
                        this.groups = this.organizationcharts.filter(a => a.parentId == null).sort((a, b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0);
                        this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', this.groups, 'fullTitle', 'half'));
                    }
                    const orgs = this.handleOrgChartView();
                    this.searchbarModel.push(new SearchPanelModel('educationalHistory', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half'));
                    this.prepareSearchPerson(orgs);
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
    ngOnDestroy() {
        this.eventManager.destroy(this.criteriaSubscriber);
        //this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEducationalHistoryMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInEducationalHistories() {
        this.eventSubscriber = this.eventManager.subscribe('educationalHistoryListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateEducationalHistories(data: IEducationalHistoryMarineSuffix[], headers: HttpHeaders) {

        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.educationalHistories = this.convertObjectDatesService.changeArrayDate(data, true);

        this.educationalHistories.forEach((a: IEducationalHistoryMarineSuffix) => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
        });
        if(this.educationalHistories.length) {
            this.sumPractical = this.educationalHistories.map(a => a.learningTimePractical).reduce((partial_sum, a) => partial_sum + a);
            this.sumTheorical = this.educationalHistories.map(a => a.learningTimeTheorical).reduce((partial_sum, a) => partial_sum + a);
            this.sumTotal = this.educationalHistories.map(a => a.totalTime).reduce((partial_sum, a) => partial_sum + a);
        }
        else{
            this.sumPractical = this.sumTheorical = this.sumTotal = 0;
        }

    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
