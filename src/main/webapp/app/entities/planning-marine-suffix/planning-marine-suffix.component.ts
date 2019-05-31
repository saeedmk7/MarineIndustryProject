import {Component, OnInit, OnDestroy, ViewChild, AfterViewInit, ViewEncapsulation} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {TranslateService} from '@ngx-translate/core';
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {DataResult, GroupDescriptor, process} from "@progress/kendo-data-query";
import * as $ from 'jquery';
import {GridComponent, GridDataResult, RowClassArgs} from "@progress/kendo-angular-grid";
import {Workbook} from '@progress/kendo-angular-excel-export';
import { saveAs } from '@progress/kendo-file-saver';
import {MONTHS} from "app/shared/constants/months.constants";
import {IPlanningAndRunMonthReport} from "app/shared/model/custom/planning-month-report";
import {IFinalNiazsanjiReportOrganizationMarineSuffix} from "app/entities/final-niazsanji-report-marine-suffix/final-niazsanji-report-organization-marine-suffix.model";
import {FINALNIAZSANJISTATUSMEANING} from "app/shared/constants/final-niazsanji-report-status-meaning.constants";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {
    FinalNiazsanjiReportMarineSuffix,
    IFinalNiazsanjiReportMarineSuffix
} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {IFinalNiazsanjiReportFardiMarineSuffix} from "app/entities/final-niazsanji-report-marine-suffix/final-niazsanji-report-fardi-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";

@Component({
    selector: 'mi-planning-marine-suffix',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['../final-niazsanji-report-marine-suffix/final-niazsanji-report-marine-suffix.scss'],
    templateUrl: './planning-marine-suffix.component.html'
})
export class PlanningMarineSuffixComponent implements OnInit, OnDestroy, AfterViewInit {
    currentAccount: any;
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix = {};
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];


    finalNiazsanjiReportsFardis: IFinalNiazsanjiReportFardiMarineSuffix[] = [];
    finalNiazsanjiReportsOrganizations: IFinalNiazsanjiReportOrganizationMarineSuffix[] = [];
    educationalModules: IEducationalModuleMarineSuffix[];

    planningAndRunMonthReports: IPlanningAndRunMonthReport[] = [];

    @ViewChild(GridComponent) grid: GridComponent;

    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgsRoot: IOrganizationChartMarineSuffix[];
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
    dates: any[];
    months: any[] = MONTHS;
    statusMeaning: any[] = FINALNIAZSANJISTATUSMEANING;
    public groups: GroupDescriptor[] = [{field: 'organizationChartTitle'}];
    public groupsOrg: GroupDescriptor[] = [{field: 'organizationChartTitle'}];

    public gridView: DataResult;
    public gridViewOrg: DataResult;

    done: boolean = false;
    yearsCollections: any[];

    niazSanjiSource: boolean = true;

    /*public aggregates: any[] = [{field: 'educationalModuleTotalLearningTime', aggregate: 'sum'}];
    public aggregatesCost: any[] = [{field: 'priceCost', aggregate: 'sum'}];*/
    public total: any;
    public totalCost: any;
    public totalFinalizeCost: any;
    public message: string;

    constructor(
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        /*this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });*/
        this.yearsCollections = GREGORIAN_START_END_DATE;
    }
    /*finalize(dataItem){

        this.finalNiazsanjiReportService.find(dataItem.id).subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) =>{
            resp.body.status = 10;
            this.finalNiazsanjiReportService.update(resp.body).subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => resp);
        });

    }*/

    showPlanningReport(){

        let niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        let orgRootId = this.treeUtilities.getRootId(this.organizationcharts, this.currentPerson.organizationChartId);
        this.finalNiazsanjiReportService.getPlanningAndRunMonthReport(niazsanjiYear,1, orgRootId)
            .subscribe(
                (res: HttpResponse<IPlanningAndRunMonthReport[]>) => {

                    this.planningAndRunMonthReports = res.body;
                    this.planningAndRunMonthReports.forEach(a => {
                        a.persianMonth = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.month);
                    });
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    public ngAfterViewInit(): void {
        // Expand the first row initially
        //this.grid.expandRow(0);
    }

    onSubmit(f: any) {

        this.message = "";
        this.finalNiazsanjiReportsFardis = [];
        this.finalNiazsanjiReportsOrganizations = [];
        if(f.value['niazsanjiYear'] == null)
            return;
        let niazSanjiSource = f.value['niazSanjiSource'];
        let criteria = [];
        criteria = this.createCriteria(criteria, f);

        if (niazSanjiSource) {
            criteria.push({
                key: 'niazSanjiSource.equals',
                value: 'FARDI'
            });
        }
        else {
            criteria.push({
                key: 'niazSanjiSource.equals',
                value: 'ORGANIZATION'
            });
        }
        this.finalNiazsanjiReportService
            .query({
                page: 0,
                size: 2000,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => this.prepareForFinal(res.body),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    createCriteria(criteria, f): any {

        if (f.value['status']) {
            let val = f.value['status'];
            if (val) {
                /*let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();*/
                criteria.push({
                    key: 'status.equals', value: val
                });
            }
        }
        if (f.value['planningRunMonth']) {
            let val = f.value['planningRunMonth'];
            if (val) {
                /*let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();*/
                criteria.push({
                    key: 'planningRunMonth.equals', value: val
                });
            }
        }
        if (f.value['niazsanjiYear']) {
            let val = f.value['niazsanjiYear'];
            if (val) {
                /*let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();*/
                criteria.push({
                    key: 'niazsanjiYear.equals', value: val
                });
            }
        }

        if (f.value['organizationChartId']) {
            let val = +f.value['organizationChartId'];
            let childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, val).filter(this.treeUtilities.onlyUnique);
            criteria.push({
                key: 'organizationChartId.in', value: childIds
            });
        }
        else{
            if(this.isModirAmozesh){
                let org = this.recommenedOrgCharts.find(a => a.parentId == null);
                let childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, org.id).filter(this.treeUtilities.onlyUnique);
                criteria.push({
                    key: 'organizationChartId.in', value: childIds
                });
            }
        }
        /*if (f.value['educationalModuleId']) {
            let val = +f.value['educationalModuleId'];
            criteria.push({
                key: 'educationalModuleId.equals', value: val
            });
        }*/
        if (f.value['educationalModuleTitle']) {
            let val = +f.value['educationalModuleTitle'];
            criteria.push({
                key: 'educationalModuleTitle.contains', value: val
            });
        }
        return criteria;
    }

    private prepareForFinal(data: IFinalNiazsanjiReportMarineSuffix[]) {
        if(data.length > 0)
        {
            let reportIds = data.map(a => a.id);
            let criteria = [{
                key: 'finalNiazsanjiReportId.in',
                value: reportIds
            }];
            this.finalNiazsanjiReportPersonService.query({
                page: 0,
                size: 2000,
                criteria,
                sort: this.sort()
            }).subscribe(
                (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {

                    if (this.niazSanjiSource) {
                        this.prepareForFardiFinal(res.body, data);
                    }
                    else {
                        this.prepareForOrganizationFinal(res.body, data);
                    }
                    $('#collapseExample').addClass('collapse');
                    $('#collapseExample').removeClass('show');
                }, (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        else{
            this.message = "موردی یافت نشد.";
        }
        //this.finalNiazsanjiReports = this.convertObjectDatesService.changeArrayDate(data);
    }

    prepareForFardiFinal(res: IFinalNiazsanjiReportPersonMarineSuffix[], data: IFinalNiazsanjiReportMarineSuffix[]) {
        data.forEach((a: IFinalNiazsanjiReportMarineSuffix) => {
            let finalNiazsanjiReportsFardi: IFinalNiazsanjiReportFardiMarineSuffix = {};
            finalNiazsanjiReportsFardi.id = a.id;
            finalNiazsanjiReportsFardi.status = a.status;


            finalNiazsanjiReportsFardi.educationalModuleId = a.educationalModuleId;

            let education = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if (education) {
                finalNiazsanjiReportsFardi.educationalModuleTitle = education.title;
                finalNiazsanjiReportsFardi.educationalModuleLevel = education.skillableLevelOfSkillTitle;
                finalNiazsanjiReportsFardi.educationalModuleTotalLearningTime = education.totalLearningTime;
            }
            finalNiazsanjiReportsFardi.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                finalNiazsanjiReportsFardi.organizationChartTitle = org.fullTitle; //a.organizationChartTitle;
            finalNiazsanjiReportsFardi.priceCost = a.priceCost;
            finalNiazsanjiReportsFardi.finalizeCost = a.finalizeCost;
            finalNiazsanjiReportsFardi.courseTypeTitle = a.courseTypeTitle;
            finalNiazsanjiReportsFardi.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            finalNiazsanjiReportsFardi.planningRunMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.planningRunMonth);


            let personRep = res.find(w => w.finalNiazsanjiReportId == a.id);
            if (personRep) {

                let person = this.people.find(q => q.id == personRep.personId);
                if (person) {
                    finalNiazsanjiReportsFardi.personFullName = person.fullName;
                    finalNiazsanjiReportsFardi.personJobTitle = person.jobTitle;
                }
            }

            this.finalNiazsanjiReportsFardis.push(finalNiazsanjiReportsFardi);
        });

        this.loadFardis();
    }

    prepareForOrganizationFinal(res: IFinalNiazsanjiReportPersonMarineSuffix[], data: IFinalNiazsanjiReportMarineSuffix[]) {

        data.forEach((a: IFinalNiazsanjiReportMarineSuffix) => {

            let finalNiazsanjiReportsOrganization: IFinalNiazsanjiReportOrganizationMarineSuffix = {};
            finalNiazsanjiReportsOrganization.id = a.id;
            finalNiazsanjiReportsOrganization.status = a.status;

            finalNiazsanjiReportsOrganization.educationalModuleId = a.educationalModuleId;

            let education = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if (education) {
                finalNiazsanjiReportsOrganization.educationalModuleTitle = education.title;
                finalNiazsanjiReportsOrganization.educationalModuleLevel = education.skillableLevelOfSkillTitle;
                finalNiazsanjiReportsOrganization.educationalModuleTotalLearningTime = education.totalLearningTime;
            }

            finalNiazsanjiReportsOrganization.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                finalNiazsanjiReportsOrganization.organizationChartTitle = org.fullTitle; //a.organizationChartTitle;
            //finalNiazsanjiReportsOrganization.organizationChartTitle = a.organizationChartTitle;
            finalNiazsanjiReportsOrganization.priceCost = a.priceCost;
            finalNiazsanjiReportsOrganization.finalizeCost = a.finalizeCost;
            finalNiazsanjiReportsOrganization.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            finalNiazsanjiReportsOrganization.planningRunMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.planningRunMonth);
            finalNiazsanjiReportsOrganization.courseTypeTitle = a.courseTypeTitle;


            let personReps = res.filter(w => w.finalNiazsanjiReportId == a.id);
            if (personReps) {
                let peopleIds = personReps.map(a => a.personId);
                let persons = this.people.filter(w => peopleIds.includes(w.id));
                finalNiazsanjiReportsOrganization.people = persons;
                this.finalNiazsanjiReportsOrganizations.push(finalNiazsanjiReportsOrganization);
                /*personReps.forEach((personRep: IFinalNiazsanjiReportPersonMarineSuffix) => {
                    let person = this.people.find(q => q.id == personRep.personId);
                    if(person)
                    {
                        finalNiazsanjiReportsFardi.personFullName = person.fullName;
                        finalNiazsanjiReportsFardi.personJobTitle = person.jobTitle;
                    }
                    this.finalNiazsanjiReportsFardis.push(finalNiazsanjiReportsFardi);
                });*/
            }
        });

        this.loadOrgs();
    }

    public groupChangeFardis(groups: GroupDescriptor[]): void {
        this.groups = groups;
        this.loadFardis();
    }

    private loadFardis(): void {

        this.gridView = process(this.finalNiazsanjiReportsFardis, {group: this.groups});
        /*this.total = aggregateBy(this.finalNiazsanjiReportsFardis, this.aggregates);
        this.totalCost = aggregateBy(this.finalNiazsanjiReportsFardis, this.aggregatesCost);*/
        this.total = this.finalNiazsanjiReportsFardis.map(a => a.educationalModuleTotalLearningTime).reduce((a, b) => a + b, 0);
        this.totalCost = this.finalNiazsanjiReportsFardis.map(a => a.priceCost).reduce((a, b) => a + b, 0);
        this.totalFinalizeCost = this.finalNiazsanjiReportsFardis.map(a => a.finalizeCost).reduce((a, b) => a + b, 0);
    }

    public groupChangeOrgs(groups: GroupDescriptor[]): void {
        this.groupsOrg = groups;
        this.loadOrgs();
    }

    private loadOrgs(): void {

        this.gridViewOrg = process(this.finalNiazsanjiReportsOrganizations, {group: this.groupsOrg});
        /*this.total = aggregateBy(this.finalNiazsanjiReportsOrganizations, this.aggregates);
        this.totalCost = aggregateBy(this.finalNiazsanjiReportsOrganizations, this.aggregatesCost);*/
        this.total = 0; //this.finalNiazsanjiReportsOrganizations.map(a => a.educationalModuleTotalLearningTime).reduce((a, b) => a + b, 0);
        this.totalCost = 0;
        this.totalFinalizeCost = 0;
        this.finalNiazsanjiReportsOrganizations.forEach(a => {
            this.total += a.educationalModuleTotalLearningTime * a.people.length;
            this.totalCost += a.priceCost * a.people.length;
            this.totalFinalizeCost += a.finalizeCost * a.people.length;
        });
        //this.total = this.finalNiazsanjiReportsOrganizations.map(a => a.people.length);
        //this.totalCost = 0;//this.finalNiazsanjiReportsOrganizations.map(a => a.priceCost).reduce((a, b) => a + b, 0);

    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
            /*if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined) {
                this.isAdmin = true;
            }*/

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                this.currentPerson = resp.body;

                this.prepareSearchOrgChart();
                this.prepareSearchEducationalModule();
                this.prepareSearchPerson();
                this.prepareSearchDate();
            });
        });
        this.registerChangeInFinalNiazsanjiReports();
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
    prepareSearchEducationalModule() {
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }

    prepareSearchPerson() {
        if (this.personService.people) {
            this.people = this.personService.people;
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    prepareSearchDate() {
        this.finalNiazsanjiReport.niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        this.dates = this.convertObjectDatesService.getYearsArray();
    }

    /*prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }*/
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.showPlanningReport();
            const orgs = this.handleOrgChartView();
            this.prepareRootsSearch(orgs);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    this.showPlanningReport();
                    const orgs = this.handleOrgChartView();
                    this.prepareRootsSearch(orgs);
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
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
        //this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }

    registerChangeInFinalNiazsanjiReports() {
        this.eventSubscriber = this.eventManager.subscribe('finalNiazsanjiReportListModification', response => this.onSearchClick());
    }

    onSearchClick() {
        $('#searchButton').trigger('click');
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    public rowCallback(context: RowClassArgs) {
        return {
            success: context.dataItem.status == 20,
            warning: context.dataItem.status == 10,
            danger: context.dataItem.status < 10
        };
    }

    /*private paginateFinalNiazsanjiReports(data: IFinalNiazsanjiReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.finalNiazsanjiReports = this.convertObjectDatesService.changeArrayDate(data);
    }*/

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    /*ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
        //this.eventManager.destroy(this.criteriaSubscriber);
    }*/

    public onExcelExport(args: any): void {
        // Prevent automatically saving the file. We will save it manually after we fetch and add the details

        args.preventDefault();

        const workbook = args.workbook;
        const rows = workbook.sheets[0].rows;

        // Get the default header styles.
        // Aternatively set custom styles for the details
        // https://www.telerik.com/kendo-angular-ui/components/excelexport/api/WorkbookSheetRowCell/
        const headerOptions = rows[0].cells[0];

        const data: IFinalNiazsanjiReportOrganizationMarineSuffix[] = this.finalNiazsanjiReportsOrganizations;
        /*for (let idx = 0; idx < data.length; idx++) {
            rows. data[idx];
        }*/
        // add the detail data to the generated master sheet rows
        // loop backwards in order to avoid changing the rows index
        for (let idx = data.length - 1; idx >= 0; idx--) {
            const people = data[idx].people; //(<GridDataResult>result[idx]).data;

            // add the detail data
            for (let productIdx = people.length - 1; productIdx >= 0; productIdx--) {
                const person = people[productIdx];
                rows.splice(idx + 2, 0, {cells: [{}, {}, {value: person.nationalId}, {value: person.fullName}, {value: person.jobTitle}]});
            }

            // add the detail header
            rows.splice(idx + 2, 0, {
                cells: [
                    {},
                    {},
                    Object.assign({}, headerOptions, {value: 'کدملی'}),
                    Object.assign({}, headerOptions, {value: 'نام و نام خانوادگی'}),
                    Object.assign({}, headerOptions, {value: 'شغل سازمانی'})
                ]
            });
        }

        // create a Workbook and save the generated data URL
        // https://www.telerik.com/kendo-angular-ui/components/excelexport/api/Workbook/
        new Workbook(workbook).toDataURL().then((dataUrl: string) => {
            // https://www.telerik.com/kendo-angular-ui/components/filesaver/
            saveAs(dataUrl, 'planning.xlsx');
            //saveAs(dataUrl, 'Categories.xlsx');
            //this.loading =  false;
        });

    }
}
