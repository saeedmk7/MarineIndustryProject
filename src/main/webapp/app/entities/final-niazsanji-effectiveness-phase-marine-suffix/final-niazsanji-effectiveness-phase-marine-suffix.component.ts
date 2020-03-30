import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { FinalNiazsanjiEffectivenessPhaseMarineSuffixService } from './final-niazsanji-effectiveness-phase-marine-suffix.service';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {MONTHS} from "app/shared/constants/months.constants";
import {FINALNIAZSANJISTATUSMEANING} from "app/shared/constants/final-niazsanji-report-status-meaning.constants";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {TranslateService} from '@ngx-translate/core';
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IRequestOtherNiazsanjiMarineSuffix} from "app/shared/model/request-other-niazsanji-marine-suffix.model";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {NIAZSANJI_SOURCE_FILTERS} from "app/shared/constants/NiazsanjiSourceFilters";
import {EFFECTIVENESSPHASELEVELS} from 'app/shared/constants/effectiveness-phase-levels.constants';

@Component({
    selector: 'mi-final-niazsanji-effectiveness-phase-marine-suffix',
    templateUrl: './final-niazsanji-effectiveness-phase-marine-suffix.component.html'
})
export class FinalNiazsanjiEffectivenessPhaseMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
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

    coursetypes: ICourseTypeMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
    recommendedPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgsRoot: IOrganizationChartMarineSuffix[];

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    dates: any[];
    months: any[] = MONTHS;
    statusMeaning: any[] = FINALNIAZSANJISTATUSMEANING;
    effectivenessPhaseLevels: any[] = EFFECTIVENESSPHASELEVELS;

    done: boolean = false;
    yearsCollections: any[];

    niazSanjiSource: boolean = true;

    searchbarModel: SearchPanelModel[] = [];
    criteriaSubscriber: Subscription;
    criteria: any;

    constructor(
        protected finalNiazsanjiReportService: FinalNiazsanjiEffectivenessPhaseMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private commonSearchCheckerService: CommonSearchCheckerService,
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;

            this.done = true;
            this.makeCriteria(criteria.content);
        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
    }

    makeCriteria(criteria?,excelExport: boolean = false){

        if (criteria) {
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
                this.setRoles(account);

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
    setEffectivenessLevel(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix, item: any){
        if(confirm("آیا از انتخاب سطح: '" + item.title + "' برای دوره مورد نظر مطمئنید؟ در صورت انتخاب این سطح دیگر امکان تغییر آن وجود ندارد.")){
            this.finalNiazsanjiReportService.setEffectivenessPhaseLevel(finalNiazsanjiReport.id, item.id)
                .subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => this.onSuccess(),
                       (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    handleAfterChart(wantOrgIds: number[],criteria,excelExport: boolean = false){
        //criteria = this.commonSearchCheckerService.checkRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
        if(this.isSuperUsers) {
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
        if(!this.isSuperUsers){
            criteria.push({
                key: 'status.greaterOrEqualThan',
                value: 20
            });
        }
        if(excelExport) {
            this.finalNiazsanjiReportService
                .query({
                    page: this.page - 1,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => this.paginateFinalNiazsanjiReports(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.finalNiazsanjiReportService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => this.paginateFinalNiazsanjiReports(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    export() {
        this.makeCriteria(this.criteria,true);
    }
    prepareForExportExcel(res : IRequestOtherNiazsanjiMarineSuffix[]){
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

            let person = this.people.find(w => w.id == a.personId);

            let educationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);

            let obj: Object;
            obj = {'index': index,
                'organizationChart': a.organizationChartTitle,
                'person': person.fullName,
                'jobTitle': person.jobTitle,
                'educationalModuleTitle': educationalModule.title,
                'educationalModuleId': educationalModule.code,
                'skillLevelOfSkillTitle': educationalModule.skillableLevelOfSkillTitle,
                'totalLearningTime': educationalModule.totalLearningTime,
                'costEducationalModule': a.costEducationalModule,
                'niazsanjiInput': a.niazsanjiInputTitle,
                'courseType': a.courseTypeTitle,
                'restriction': a.restrictions.map(w => w.title).join(' - '),
                'restrictionDescription': a.restrictionDescription,
                'teachingApproach': a.teachingApproachTitle,
                'goalsText': a.goalsText,
                'prerequisite': a.prerequisite,
                'description': a.description,
                'createDate': a.createDate,
                'status': this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus)
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'requestOtherNiazsanjis', 'marineindustryprojApp.requestOtherNiazsanji');
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/final-niazsanji-report-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.makeCriteria(this.criteria);*/
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/final-niazsanji-report-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.makeCriteria(this.criteria);
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.setRoles(account);

            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','niazSanjiSource','select', 'equals', NIAZSANJI_SOURCE_FILTERS));
            this.prepareDate();
            this.prepareSearchOrgChart();
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleCode','text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleTitle','text', 'contains'));
            if(this.isSuperUsers)
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','status','select', 'equals', this.statusMeaning, 'mean'));
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','id','text', 'equals'));
            this.prepareSearchCourseType();
            //this.prepareSearchEducationalModule();
            this.preparePeople();

        });
        //this.registerChangeInFinalNiazsanjiReports();
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
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'niazsanjiYear', 'select', 'equals', dates, 'title'));

    }
    prepareSearchCourseType(){
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','courseTypeId','select', 'equals', this.coursetypes));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchEducationalModule() {
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules
            //this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleId','select', 'equals', this.educationalModules, 'fullTitle'));
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    //this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleId','select', 'equals', this.educationalModules, 'fullTitle'));
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    prepareSearchPerson(orgs: IOrganizationChartMarineSuffix[]) {
        if(this.isSuperUsers)
        {
            if(!this.people) {
                if (this.personService.people) {
                    this.people = this.convertObjectDatesService.goClone(this.personService.people);
                    this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                    this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.recommendedPeople, 'fullName', 'half'));
                }
                else {
                    this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {

                            this.people = res.body;
                            this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.recommendedPeople, 'fullName', 'half'));
                        },
                        (res: HttpErrorResponse) => this.onError(res.message));
                }
            }
            else{
                this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.recommendedPeople, 'fullName', 'half'));
            }
            return;
        }
        const orgIds = orgs.map(a => a.id);
        let criteria = [{
            key:'organizationChartId.in',
            value: orgIds
        }];
        this.personService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id","asc"]
        }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                this.recommendedPeople = resp.body;
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.recommendedPeople, 'fullName', 'half'));
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    preparePeople() {
        if (this.personService.people) {
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.people, 'fullName'));
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','personId','select', 'equals', this.people, 'fullName'));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            //this.showPlanningReport();
            const orgs = this.handleOrgChartView();
            if(this.isSuperUsers) {
                this.prepareRootsSearch(orgs);
            }
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','organizationChartId','select', 'equals', orgs, 'fullTitle', 'half'));
            this.prepareSearchPerson(orgs);

        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    //this.showPlanningReport();
                    const orgs = this.handleOrgChartView();
                    if(this.isSuperUsers) {
                        this.prepareRootsSearch(orgs);
                    }
                    this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','organizationChartId','select', 'equals', orgs, 'fullTitle', 'half'));
                    this.prepareSearchPerson(orgs);

                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    handleOrgChartView(): IOrganizationChartMarineSuffix[]{
        if(this.isSuperUsers) {
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

    prepareRootsSearch(orgs: IOrganizationChartMarineSuffix[]){

        this.orgsRoot = orgs.filter(a => a.parentId == null);
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','organizationChartId','select', 'equals', this.orgsRoot, 'fullTitle'));
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }

    registerChangeInFinalNiazsanjiReports() {
        this.eventSubscriber = this.eventManager.subscribe('finalNiazsanjiReportListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateFinalNiazsanjiReports(data: IFinalNiazsanjiReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules
            this.loadData(data);
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    this.loadData(data);
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }

    }

    loadData(data: IFinalNiazsanjiReportMarineSuffix[]){
        this.finalNiazsanjiReports = this.convertObjectDatesService.fillFinalNiazsanjiDataArray(data,
            this.educationalModules, this.organizationcharts);
        this.finalNiazsanjiReports = this.convertObjectDatesService.changeArrayDate(this.finalNiazsanjiReports);
    }
    protected onSuccess() {
        this.makeCriteria(this.criteria);
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
