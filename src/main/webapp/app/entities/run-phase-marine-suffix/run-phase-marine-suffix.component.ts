import { Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild, AfterViewInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IRunPhaseMarineSuffix, RunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { RunPhaseMarineSuffixService } from './run-phase-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { GridComponent, RowClassArgs } from '@progress/kendo-angular-grid';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { FINALNIAZSANJISTATUSMEANING } from 'app/shared/constants/final-niazsanji-report-status-meaning.constants';
import { DataResult, GroupDescriptor, process } from '@progress/kendo-data-query';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import * as $ from 'jquery';
import { IRunPhaseFardiMarineSuffix } from 'app/entities/run-phase-marine-suffix/run-phase-fardi-marine-suffix.model';
import { IRunPhaseOrganizationMarineSuffix } from 'app/entities/run-phase-marine-suffix/run-phase-organization-marine-suffix.model';
import { Workbook } from '@progress/kendo-angular-excel-export';
import { saveAs } from '@progress/kendo-file-saver';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { IPlanningAndRunMonthReport } from 'app/shared/model/custom/planning-month-report';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { ICountListModel } from 'app/shared/model/custom/count-list-model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { IFinalNiazsanjiPeopleListModel } from 'app/shared/model/custom/final-niazsanji-report-people-list-model';

@Component({
    selector: 'mi-run-phase-marine-suffix',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./run-phase-marine-suffix.component.scss'],
    templateUrl: './run-phase-marine-suffix.component.html'
})
export class RunPhaseMarineSuffixComponent implements OnInit, OnDestroy, AfterViewInit {
    currentAccount: any;
    runPhases: IRunPhaseMarineSuffix[] = [];
    public runPhase: IRunPhaseMarineSuffix = {};
    educationalModules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
    recommendedPeople: IPersonMarineSuffix[];
    personId: number;
    runPhaseFardis: IRunPhaseFardiMarineSuffix[] = [];
    runPhaseOrganizations: IRunPhaseOrganizationMarineSuffix[] = [];
    coursetypes: ICourseTypeMarineSuffix[];

    @ViewChild(GridComponent) grid: GridComponent;

    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgsRoot: IOrganizationChartMarineSuffix[];
    planningAndRunMonthReports: IPlanningAndRunMonthReport[] = [];

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
    public groups: GroupDescriptor[] = [{ field: 'organizationChartTitle' }];
    public groupsOrg: GroupDescriptor[] = [{ field: 'organizationChartTitle' }];

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

    countList: ICountListModel[] = [];
    loading: boolean = false;
    constructor(
        private runPhaseService: RunPhaseMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private treeUtilities: TreeUtilities
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
    }
    showPlanningReport() {
        let niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        let orgRootId = this.treeUtilities.getRootId(this.organizationcharts, this.currentPerson.organizationChartId);
        this.finalNiazsanjiReportService.getPlanningAndRunMonthReport(niazsanjiYear, 2, orgRootId).subscribe(
            (res: HttpResponse<IPlanningAndRunMonthReport[]>) => {
                this.planningAndRunMonthReports = res.body;
                this.planningAndRunMonthReports.forEach(a => {
                    a.persianMonth = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.month);
                });
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    onSubmit(f: any) {
        this.message = '';
        this.runPhaseFardis = [];
        this.runPhaseOrganizations = [];
        if (!this.isSuperUsers && f.value['niazsanjiYear'] == null) return;
        let niazSanjiSource = f.value['niazSanjiSource'];
        let criteria = [];
        criteria = this.createCriteria(criteria, f);

        if (niazSanjiSource) {
            const array = ['FARDI', 'OTHER', 'JOB'];
            criteria.push({
                key: 'niazSanjiSource.in',
                value: array
            });
        } else {
            criteria.push({
                key: 'niazSanjiSource.equals',
                value: 'ORGANIZATION'
            });
        }

        this.runPhaseService
            .query({
                page: 0,
                size: 2000,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IRunPhaseMarineSuffix[]>) => this.prepareForFinal(res.body),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    createCriteria(criteria, f): any {
        if (f.value['status'] != undefined) {
            let val = f.value['status'];
            if (val != undefined) {
                /*let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();*/
                criteria.push({
                    key: 'status.equals',
                    value: val
                });
            }
        }
        if (f.value['runMonth']) {
            let val = f.value['runMonth'];
            if (val) {
                /*let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();*/
                criteria.push({
                    key: 'runMonth.equals',
                    value: val
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
                    key: 'niazsanjiYear.equals',
                    value: val
                });
            }
        }

        if (f.value['organizationChartId']) {
            let val = +f.value['organizationChartId'];
            let childIds = this.treeUtilities
                .getAllOfChilderenIdsOfThisId(this.organizationcharts, val)
                .filter(this.treeUtilities.onlyUnique);
            criteria.push({
                key: 'organizationChartId.in',
                value: childIds
            });
        } else {
            if (this.isModirAmozesh) {
                let org = this.recommenedOrgCharts.find(a => a.parentId == null);
                let childIds = this.treeUtilities
                    .getAllOfChilderenIdsOfThisId(this.organizationcharts, org.id)
                    .filter(this.treeUtilities.onlyUnique);
                criteria.push({
                    key: 'organizationChartId.in',
                    value: childIds
                });
            }
        }

        if (f.value['personId']) {
            let val = +f.value['personId'];
            criteria.push({
                key: 'personId.in',
                value: val
            });
        }

        if (f.value['finalNiazsanjiReportId']) {
            let val = +f.value['finalNiazsanjiReportId'];
            criteria.push({
                key: 'finalNiazsanjiReportId.equals',
                value: val
            });
        }
        if (f.value['educationalModuleId']) {
            let val = +f.value['educationalModuleId'];
            criteria.push({
                key: 'educationalModuleId.equals',
                value: val
            });
        }
        if (f.value['educationalModuleCode']) {
            let val = +f.value['educationalModuleCode'];
            criteria.push({
                key: 'educationalModuleCode.contains',
                value: val
            });
        }
        if (f.value['educationalModuleTitle']) {
            let val = f.value['educationalModuleTitle'];
            criteria.push({
                key: 'educationalModuleTitle.contains',
                value: val
            });
        }
        if (f.value['courseTypeId']) {
            let val = +f.value['courseTypeId'];
            criteria.push({
                key: 'courseTypeId.in',
                value: val
            });
        }
        return criteria;
    }

    private prepareForFinal(data: IRunPhaseMarineSuffix[]) {
        if (data.length > 0) {
            if (this.niazSanjiSource) {
                this.prepareForFardiFinal(data);
                $('#collapseExample').addClass('collapse');
                $('#collapseExample').removeClass('show');
            } else {
                let reportIds = data.map(w => w.finalNiazsanjiReportId);
                this.finalNiazsanjiReportPersonService.countList(reportIds).subscribe(
                    (res: HttpResponse<ICountListModel[]>) => {
                        this.countList = res.body;
                        //this.prepareForOrganizationFinal(this.countList, data);
                        this.prepareForOrganizationFinal(data);

                        $('#collapseExample').addClass('collapse');
                        $('#collapseExample').removeClass('show');
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        } else {
            this.message = 'موردی یافت نشد.';
        }
        //this.finalNiazsanjiReports = this.convertObjectDatesService.changeArrayDate(data);
    }

    prepareForFardiFinal(data: IRunPhaseMarineSuffix[]) {
        data.forEach((a: IRunPhaseMarineSuffix) => {
            let runPhaseFardi: IRunPhaseFardiMarineSuffix = {};
            runPhaseFardi.id = a.id;
            runPhaseFardi.finalNiazsanjiReportId = a.finalNiazsanjiReportId;
            runPhaseFardi.status = a.status;
            runPhaseFardi.educationalModuleId = a.educationalModuleId;
            runPhaseFardi.educationalModuleCode = a.educationalModuleCode;
            runPhaseFardi.educationalModuleTitle = a.educationalModuleTitle;
            runPhaseFardi.educationalModuleLevel = a.skillLevelOfSkillTitle;
            runPhaseFardi.educationalModuleTotalLearningTime = a.educationalModuleTotalTime;

            runPhaseFardi.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) {
                runPhaseFardi.organizationChartTitle = org.fullTitle;
            } //a.organizationChartTitle;
            //runPhaseFardi.priceCost = a.finalizeCost;

            runPhaseFardi.finalizeCost = a.finalizeCost;
            runPhaseFardi.priceCost = a.priceCost;
            runPhaseFardi.finishDate = a.finishDate;
            runPhaseFardi.courseTypeTitle = a.courseTypeTitle;
            runPhaseFardi.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            if (a.people.length > 0) {
                let person = this.people.find(q => q.id == a.people[0].id);
                if (person) {
                    runPhaseFardi.personFullName = person.fullName;
                    runPhaseFardi.personJobTitle = person.jobTitle;
                }
            }
            this.runPhaseFardis.push(runPhaseFardi);
        });

        this.loadFardis();
    }

    prepareForOrganizationFinal(data: IRunPhaseMarineSuffix[]) {
        data.forEach((a: IRunPhaseMarineSuffix) => {
            let runPhaseOrganization: IRunPhaseOrganizationMarineSuffix = {};
            runPhaseOrganization.id = a.id;
            runPhaseOrganization.finalNiazsanjiReportId = a.finalNiazsanjiReportId;
            runPhaseOrganization.status = a.status;

            runPhaseOrganization.educationalModuleId = a.educationalModuleId;
            runPhaseOrganization.educationalModuleCode = a.educationalModuleCode;

            runPhaseOrganization.educationalModuleTitle = a.educationalModuleTitle;
            runPhaseOrganization.educationalModuleLevel = a.skillLevelOfSkillTitle;
            runPhaseOrganization.educationalModuleTotalLearningTime = a.educationalModuleTotalTime;

            runPhaseOrganization.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) {
                runPhaseOrganization.organizationChartTitle = org.fullTitle;
            } //a.organizationChartTitle;
            //runPhaseOrganization.organizationChartTitle = a.organizationChartTitle;
            runPhaseOrganization.priceCost = a.priceCost;
            runPhaseOrganization.finalizeCost = a.finalizeCost;
            runPhaseOrganization.finishDate = a.finishDate;
            runPhaseOrganization.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            runPhaseOrganization.courseTypeTitle = a.courseTypeTitle;
            let peopleCount = this.countList.find(w => w.entityId == a.finalNiazsanjiReportId);

            if (peopleCount) runPhaseOrganization.peopleCount = peopleCount.count;
            this.runPhaseOrganizations.push(runPhaseOrganization);
        });
        this.loadOrgs();
    }

    public groupChangeFardis(groups: GroupDescriptor[]): void {
        this.groups = groups;
        this.loadFardis();
    }

    private loadFardis(): void {
        this.gridView = process(this.runPhaseFardis, { group: this.groups });
        /*this.total = aggregateBy(this.runPhaseFardis, this.aggregates);
        this.totalCost = aggregateBy(this.runPhaseFardis, this.aggregatesCost);*/
        this.total = this.runPhaseFardis.map(a => a.educationalModuleTotalLearningTime).reduce((a, b) => a + b, 0);
        this.totalCost = this.runPhaseFardis.map(a => a.priceCost).reduce((a, b) => a + b, 0);
        this.totalFinalizeCost = this.runPhaseFardis.map(a => a.finalizeCost).reduce((a, b) => a + b, 0);
    }

    public groupChangeOrgs(groups: GroupDescriptor[]): void {
        this.groupsOrg = groups;
        this.loadOrgs();
    }

    private loadOrgs(): void {
        this.gridViewOrg = process(this.runPhaseOrganizations, { group: this.groupsOrg });
        /*this.total = aggregateBy(this.runPhaseOrganizations, this.aggregates);
        this.totalCost = aggregateBy(this.runPhaseOrganizations, this.aggregatesCost);*/
        this.total = 0; //this.runPhaseOrganizations.map(a => a.educationalModuleTotalLearningTime).reduce((a, b) => a + b, 0);
        this.totalCost = 0;
        this.totalFinalizeCost = 0;
        this.runPhaseOrganizations.forEach(a => {
            this.total += a.educationalModuleTotalLearningTime * a.peopleCount;
            this.totalCost += a.priceCost;
            this.totalFinalizeCost += a.finalizeCost;
        });
        //this.total = this.runPhaseOrganizations.map(a => a.people.length);
        //this.totalCost = 0;//this.runPhaseOrganizations.map(a => a.priceCost).reduce((a, b) => a + b, 0);
    }

    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) {
            this.isAdmin = true;
        }
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) {
            this.isModirAmozesh = true;
        }
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) {
            this.isModirKolAmozesh = true;
        }
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined) {
            this.isKarshenasArshadAmozeshSazman = true;
        }

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) {
            this.isSuperUsers = true;
        }
    }
    prepareSearchCourseType() {
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchEducationalModule() {
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
        } else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    prepareSearchPerson(orgs: IOrganizationChartMarineSuffix[]) {
        if (this.isSuperUsers) {
            if (!this.people) {
                if (this.personService.people) {
                    this.people = this.personService.people;
                    this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                } else {
                    this.personService.query().subscribe(
                        (res: HttpResponse<IPersonMarineSuffix[]>) => {
                            this.people = res.body;
                            this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
                }
            } else {
                this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
            }
            return;
        }
        const orgIds = orgs.map(a => a.id);
        let criteria = [
            {
                key: 'organizationChartId.in',
                value: orgIds
            }
        ];
        this.personService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.recommendedPeople = resp.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    preparePeople() {
        if (this.personService.people) {
            this.people = this.personService.people;
        } else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    prepareSearchDate() {
        //this.runPhase.niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        this.dates = this.convertObjectDatesService.getYearsArray();
    }

    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.showPlanningReport();
            const orgs = this.handleOrgChartView();
            this.prepareSearchPerson(orgs);
            this.prepareRootsSearch(orgs);
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.showPlanningReport();
                    const orgs = this.handleOrgChartView();
                    this.prepareSearchPerson(orgs);
                    this.prepareRootsSearch(orgs);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    handleOrgChartView(): IOrganizationChartMarineSuffix[] {
        if (this.isSuperUsers) {
            this.recommenedOrgCharts = this.organizationcharts;
            return this.recommenedOrgCharts;
        }
        if (this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
            let orgIds = this.treeUtilities
                .getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        } else {
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }

        return this.recommenedOrgCharts;
    }

    prepareRootsSearch(orgs: IOrganizationChartMarineSuffix[]) {
        this.orgsRoot = orgs.filter(a => a.parentId == null);
    }

    onSearchClick() {
        $('#searchButton').trigger('click');
    }

    public rowCallback(context: RowClassArgs) {
        return {
            success: context.dataItem.status == 10,
            pink: context.dataItem.status == 5,
            danger: context.dataItem.status == 7,
            warning: context.dataItem.status == 0
        };
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
            /*if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined) {
                this.isAdmin = true;
            }*/

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;

                this.prepareSearchOrgChart();
                this.prepareSearchEducationalModule();
                this.preparePeople();
                this.prepareSearchDate();
                this.prepareSearchCourseType();
            });
        });
        this.registerChangeInRunPhases();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRunPhaseMarineSuffix) {
        return item.id;
    }

    registerChangeInRunPhases() {
        this.eventSubscriber = this.eventManager.subscribe('runPhaseListModification', response => this.onSearchClick());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    ngAfterViewInit(): void {}

    public exportToExcel(grid: GridComponent): void {
        this.loading = true;
        const finalNiazsanjiReportIds = this.runPhaseOrganizations.map(w => w.finalNiazsanjiReportId);
        this.finalNiazsanjiReportPersonService.finalNiazsanjiReportPeopleList(finalNiazsanjiReportIds).subscribe(
            (resp: HttpResponse<IFinalNiazsanjiPeopleListModel[]>) => {
                const finalNiazsanjiPeople = resp.body;
                this.runPhaseOrganizations.forEach(w => {
                    const entity = finalNiazsanjiPeople.find(e => e.entityId == w.finalNiazsanjiReportId);
                    if (entity) w.peopleFullNames = entity.peopleFullNames.join(' , ');
                });

                this.loadOrgs();
                grid.saveAsExcel();
                this.loading = false;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
}
