import { Component, OnInit, OnDestroy, ViewChild, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { FinalNiazsanjiReportMarineSuffixService } from './final-niazsanji-report-marine-suffix.service';
import { TranslateService } from '@ngx-translate/core';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IFinalNiazsanjiReportFardiMarineSuffix } from 'app/entities/final-niazsanji-report-marine-suffix/final-niazsanji-report-fardi-marine-suffix.model';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { DataResult, GroupDescriptor, process } from '@progress/kendo-data-query';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import * as $ from 'jquery';
import { IFinalNiazsanjiReportOrganizationMarineSuffix } from 'app/entities/final-niazsanji-report-marine-suffix/final-niazsanji-report-organization-marine-suffix.model';
import { GridComponent, GridDataResult, RowClassArgs } from '@progress/kendo-angular-grid';
import { Workbook } from '@progress/kendo-angular-excel-export';
import { saveAs } from '@progress/kendo-file-saver';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { FINALNIAZSANJISTATUSMEANING } from 'app/shared/constants/final-niazsanji-report-status-meaning.constants';
import { IPlanningAndRunMonthReport } from 'app/shared/model/custom/planning-month-report';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { ICountListModel } from 'app/shared/model/custom/count-list-model';
import { FINALNIAZSANJISTATUSMEANINGFORSEARCH } from 'app/shared/constants/final-niazsanji-report-status-meaning-for-search.constants';

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix',
    encapsulation: ViewEncapsulation.None,
    styleUrls: ['./final-niazsanji-report-marine-suffix.scss'],
    templateUrl: './final-niazsanji-report-marine-suffix.component.html'
})
export class FinalNiazsanjiReportMarineSuffixComponent implements OnInit, OnDestroy, AfterViewInit {
    currentAccount: any;
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix = {};
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
    coursetypes: ICourseTypeMarineSuffix[];
    personId: number;

    finalNiazsanjiReportsFardis: IFinalNiazsanjiReportFardiMarineSuffix[] = [];
    finalNiazsanjiReportsOrganizations: IFinalNiazsanjiReportOrganizationMarineSuffix[] = [];
    educationalModules: IEducationalModuleMarineSuffix[];

    planningAndRunMonthReports: IPlanningAndRunMonthReport[] = [];

    @ViewChild(GridComponent) grid: GridComponent;

    people: IPersonMarineSuffix[];
    recommendedPeople: IPersonMarineSuffix[];
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

    searchbarModel: SearchPanelModel[] = [];
    criteriaSubscriber: Subscription;
    criteria: any;

    countList: ICountListModel[] = [];
    selectedFinalNiazsanjis: IFinalNiazsanjiReportMarineSuffix[] = [];
    selectedYear: number = 0;
    years: any[];
    isSaving: boolean = false;

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
        private courseTypeService: CourseTypeMarineSuffixService,
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
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;

            if (this.done) {
                this.loadAll(criteria.content);
            }
            this.done = true;
        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
        this.years = this.yearsCollections.map(a => a.year);
    }

    change(val, finalNiazsanji: IFinalNiazsanjiReportMarineSuffix) {
        if (val.target.checked) {
            this.selectedFinalNiazsanjis.push(finalNiazsanji);
        } else {
            this.selectedFinalNiazsanjis = this.selectedFinalNiazsanjis.filter(w => w.id != finalNiazsanji.id);
        }
    }

    save() {
        if (this.selectedFinalNiazsanjis.length > 0 && this.selectedYear) {
            this.isSaving = true;
            this.selectedFinalNiazsanjis.forEach(finalNiaz => {
                finalNiaz.niazsanjiYear = this.selectedYear;

                this.finalNiazsanjiReportService
                    .find(finalNiaz.id)
                    .subscribe((finalNiazResp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {
                        let newFinalNiaz = finalNiazResp.body;
                        newFinalNiaz.niazsanjiYear = this.selectedYear;
                        this.subscribeToSaveResponse(this.finalNiazsanjiReportService.update(newFinalNiaz));
                    });
            });
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalNiazsanjiReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    counter: number = 0;

    private onSaveSuccess(res: IFinalNiazsanjiReportMarineSuffix) {
        this.counter++;
        if (this.counter == this.selectedFinalNiazsanjis.length) {
            this.counter = 0;
            this.selectedFinalNiazsanjis = [];
            this.loadAll(this.criteria);
        }
    }

    private onSaveError() {
        this.isSaving = false;
    }

    public ngAfterViewInit(): void {
        // Expand the first row initially
        //this.grid.expandRow(0);
    }

    loadAll(criteria?) {
        this.message = '';
        this.finalNiazsanjiReportsFardis = [];
        this.finalNiazsanjiReportsOrganizations = [];
        if (!criteria) return;

        if (!this.isSuperUsers) {
            const niazsanjiYear = criteria.find(a => a.key == 'niazsanjiYear.equals');
            if (niazsanjiYear) {
                if (!niazsanjiYear.value) {
                    this.message = 'لطفا سال نیازسنجی را انتخاب نمائید.';
                    return;
                }
            } else {
                this.message = 'لطفا سال نیازسنجی را انتخاب نمائید.';
                return;
            }
        }
        criteria = this.createCriteria(criteria);

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

    createCriteria(criteria): any {
        const niazsanjiSource = criteria.find(a => a.key == 'niazSanjiSource.equals');
        if (niazsanjiSource) {
            if (niazsanjiSource.value == 'ORGANIZATION') this.niazSanjiSource = false;
            else {
                this.niazSanjiSource = true;
                criteria = criteria.filter(a => a.key != 'niazSanjiSource.equals');
                const array = ['FARDI', 'OTHER', 'JOB'];
                criteria.push({
                    key: 'niazSanjiSource.in',
                    value: array
                });
            }
        }

        const org = criteria.find(a => a.key == 'organizationChartId.equals');
        if (org) {
            const orgId = +org.value;
            criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
            if (orgId) {
                const childIds = this.treeUtilities
                    .getAllOfChilderenIdsOfThisId(this.organizationcharts, orgId)
                    .filter(this.treeUtilities.onlyUnique);
                criteria.push({
                    key: 'organizationChartId.in',
                    value: childIds
                });
            } else if (this.isModirAmozesh) {
                let org = this.recommenedOrgCharts.find(a => a.parentId == null);
                let childIds = this.treeUtilities
                    .getAllOfChilderenIdsOfThisId(this.organizationcharts, org.id)
                    .filter(this.treeUtilities.onlyUnique);
                criteria.push({
                    key: 'organizationChartId.in',
                    value: childIds
                });
            }
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

        return criteria;
    }

    private prepareForFinal(data: IFinalNiazsanjiReportMarineSuffix[]) {
        if (data.length > 0) {
            let reportIds = data.map(a => a.id);
            if (this.niazSanjiSource) {
                let criteria = [
                    {
                        key: 'finalNiazsanjiReportId.in',
                        value: reportIds
                    }
                ];
                this.finalNiazsanjiReportPersonService
                    .query({
                        page: 0,
                        size: 2000,
                        criteria,
                        sort: this.sort()
                    })
                    .subscribe(
                        (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                            this.prepareForFardiFinal(res.body, data);

                            $('#collapseExample').addClass('collapse');
                            $('#collapseExample').removeClass('show');
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            } else {
                this.finalNiazsanjiReportPersonService.countList(reportIds).subscribe(
                    (res: HttpResponse<ICountListModel[]>) => {
                        this.countList = res.body;
                        this.prepareForOrganizationFinal(this.countList, data);

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

    prepareForFardiFinal(res: IFinalNiazsanjiReportPersonMarineSuffix[], data: IFinalNiazsanjiReportMarineSuffix[]) {
        data.forEach((a: IFinalNiazsanjiReportMarineSuffix) => {
            let finalNiazsanjiReportsFardi: IFinalNiazsanjiReportFardiMarineSuffix = {};
            finalNiazsanjiReportsFardi.id = a.id;
            finalNiazsanjiReportsFardi.status = a.status;

            finalNiazsanjiReportsFardi.educationalModuleId = a.educationalModuleId;
            finalNiazsanjiReportsFardi.educationalModuleCode = a.educationalModuleCode;
            finalNiazsanjiReportsFardi.educationalModuleTitle = a.educationalModuleTitle;
            finalNiazsanjiReportsFardi.educationalModuleLevel = a.skillLevelOfSkillTitle;
            finalNiazsanjiReportsFardi.educationalModuleTotalLearningTime = a.educationalModuleTotalTime;

            finalNiazsanjiReportsFardi.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) finalNiazsanjiReportsFardi.organizationChartTitle = org.fullTitle; //a.organizationChartTitle;
            finalNiazsanjiReportsFardi.priceCost = a.priceCost;
            finalNiazsanjiReportsFardi.finalizeCost = a.finalizeCost;
            finalNiazsanjiReportsFardi.courseTypeTitle = a.courseTypeTitle;
            finalNiazsanjiReportsFardi.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            finalNiazsanjiReportsFardi.planningRunMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(
                a.planningRunMonth
            );

            let personRep = res.find(w => w.finalNiazsanjiReportId == a.id);
            if (personRep) {
                let person = this.people.find(q => q.id == personRep.personId);
                if (person) {
                    finalNiazsanjiReportsFardi.personFullName = person.fullName;
                    finalNiazsanjiReportsFardi.personJobTitle = person.jobTitle;
                }
            }
            const modifyPerson: IPersonMarineSuffix = this.people.find(w => w.nationalId == a.modifyUserLogin);
            if (modifyPerson) finalNiazsanjiReportsFardi.modifyPerson = modifyPerson.fullName;
            if (a.modifyDate) finalNiazsanjiReportsFardi.modifyDate = this.convertObjectDatesService.miladi2Shamsi(a.modifyDate.toDate());

            this.finalNiazsanjiReportsFardis.push(finalNiazsanjiReportsFardi);
        });

        this.loadFardis();
    }

    prepareForOrganizationFinal(res: ICountListModel[], data: IFinalNiazsanjiReportMarineSuffix[]) {
        data.forEach((a: IFinalNiazsanjiReportMarineSuffix) => {
            let finalNiazsanjiReportsOrganization: IFinalNiazsanjiReportOrganizationMarineSuffix = {};
            finalNiazsanjiReportsOrganization.id = a.id;
            finalNiazsanjiReportsOrganization.status = a.status;

            finalNiazsanjiReportsOrganization.educationalModuleId = a.educationalModuleId;
            finalNiazsanjiReportsOrganization.educationalModuleCode = a.educationalModuleCode;
            finalNiazsanjiReportsOrganization.educationalModuleTitle = a.educationalModuleTitle;
            finalNiazsanjiReportsOrganization.educationalModuleLevel = a.educationalModuleLevelTitle;
            finalNiazsanjiReportsOrganization.educationalModuleTotalLearningTime = a.educationalModuleTotalTime;

            finalNiazsanjiReportsOrganization.niazsanjiYear = a.niazsanjiYear;
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) finalNiazsanjiReportsOrganization.organizationChartTitle = org.fullTitle; //a.organizationChartTitle;
            //finalNiazsanjiReportsOrganization.organizationChartTitle = a.organizationChartTitle;
            finalNiazsanjiReportsOrganization.priceCost = a.priceCost;
            finalNiazsanjiReportsOrganization.finalizeCost = a.finalizeCost;
            finalNiazsanjiReportsOrganization.runMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            finalNiazsanjiReportsOrganization.planningRunMonthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(
                a.planningRunMonth
            );
            finalNiazsanjiReportsOrganization.courseTypeTitle = a.courseTypeTitle;

            const modifyPerson: IPersonMarineSuffix = this.people.find(w => w.nationalId == a.modifyUserLogin);
            if (modifyPerson) finalNiazsanjiReportsOrganization.modifyPerson = modifyPerson.fullName;
            if (a.modifyDate)
                finalNiazsanjiReportsOrganization.modifyDate = this.convertObjectDatesService.miladi2Shamsi(a.modifyDate.toDate());

            let personReps = res.find(w => w.entityId == a.id);
            if (personReps) {
                finalNiazsanjiReportsOrganization.peopleCount = personReps.count;
                this.finalNiazsanjiReportsOrganizations.push(finalNiazsanjiReportsOrganization);
            }
        });

        this.loadOrgs();
    }

    public groupChangeFardis(groups: GroupDescriptor[]): void {
        this.groups = groups;
        this.loadFardis();
    }

    private loadFardis(): void {
        this.gridView = process(this.finalNiazsanjiReportsFardis, { group: this.groups });
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
        this.gridViewOrg = process(this.finalNiazsanjiReportsOrganizations, { group: this.groupsOrg });
        /*this.total = aggregateBy(this.finalNiazsanjiReportsOrganizations, this.aggregates);
        this.totalCost = aggregateBy(this.finalNiazsanjiReportsOrganizations, this.aggregatesCost);*/
        this.total = 0; //this.finalNiazsanjiReportsOrganizations.map(a => a.educationalModuleTotalLearningTime).reduce((a, b) => a + b, 0);
        this.totalCost = 0;
        this.totalFinalizeCost = 0;
        this.finalNiazsanjiReportsOrganizations.forEach(a => {
            this.total += a.educationalModuleTotalLearningTime * a.peopleCount;
            this.totalCost += a.priceCost;
            this.totalFinalizeCost += a.finalizeCost;
        });
        //this.total = this.finalNiazsanjiReportsOrganizations.map(a => a.people.length);
        //this.totalCost = 0;//this.finalNiazsanjiReportsOrganizations.map(a => a.priceCost).reduce((a, b) => a + b, 0);
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;

                const values = [
                    {
                        value: 'FARDI',
                        text: 'نیازسنجی پودمان فردی و شغلی و سایر',
                        id: 'niazSanjiSource_FARDI',
                        checked: true
                    },
                    {
                        value: 'ORGANIZATION',
                        text: 'نیازسنجی پودمان سازمانی ',
                        id: 'niazSanjiSource_ORGANIZATION',
                        checked: false
                    }
                ];
                let yearArray = [];
                this.yearsCollections.forEach(a => {
                    let year = {
                        id: a.year,
                        year: a.year.toString()
                    };
                    yearArray.push(year);
                });
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'niazSanjiSource', 'radio', 'equals', values));
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'finalNiazsanjiReport',
                        'niazsanjiYear',
                        'select',
                        'equals',
                        yearArray,
                        'year',
                        '',
                        this.convertObjectDatesService.getNowShamsiYear().toString()
                    )
                );
                this.searchbarModel.push(
                    new SearchPanelModel('finalNiazsanjiReport', 'planningRunMonth', 'select', 'equals', this.months, 'persianMonth')
                );

                this.prepareSearchOrgChart();
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleCode', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleTitle', 'text', 'contains'));
                this.searchbarModel.push(
                    new SearchPanelModel('finalNiazsanjiReport', 'status', 'select', 'equals', FINALNIAZSANJISTATUSMEANINGFORSEARCH, 'mean')
                );
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'id', 'text', 'equals'));
                this.prepareSearchCourseType();
                this.prepareSearchEducationalModule();
                this.preparePeople();

                this.done = true;
                //this.prepareSearchDate();
            });
        });
        //this.registerChangeInFinalNiazsanjiReports();
    }

    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }

    prepareSearchCourseType() {
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
                this.searchbarModel.push(
                    new SearchPanelModel('finalNiazsanjiReport', 'courseTypeId', 'select', 'equals', this.coursetypes)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    prepareSearchEducationalModule() {
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
            //this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleId','select', 'equals', this.educationalModules, 'fullTitle'));
        } else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    //this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport','educationalModuleId','select', 'equals', this.educationalModules, 'fullTitle'));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    prepareSearchPerson(orgs: IOrganizationChartMarineSuffix[]) {
        if (this.isSuperUsers) {
            if (!this.people) {
                if (this.personService.people) {
                    this.people = this.convertObjectDatesService.goClone(this.personService.people);
                    this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'finalNiazsanjiReport',
                            'personId',
                            'select',
                            'equals',
                            this.recommendedPeople,
                            'fullName',
                            'half'
                        )
                    );
                } else {
                    this.personService.query().subscribe(
                        (res: HttpResponse<IPersonMarineSuffix[]>) => {
                            this.people = res.body;
                            this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                            this.searchbarModel.push(
                                new SearchPanelModel(
                                    'finalNiazsanjiReport',
                                    'personId',
                                    'select',
                                    'equals',
                                    this.recommendedPeople,
                                    'fullName',
                                    'half'
                                )
                            );
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
                }
            } else {
                this.recommendedPeople = this.convertObjectDatesService.goClone(this.people);
                this.searchbarModel.push(
                    new SearchPanelModel('finalNiazsanjiReport', 'personId', 'select', 'equals', this.recommendedPeople, 'fullName', 'half')
                );
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
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'finalNiazsanjiReport',
                            'personId',
                            'select',
                            'equals',
                            this.recommendedPeople,
                            'fullName',
                            'half'
                        )
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    preparePeople() {
        if (this.personService.people) {
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
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
        this.finalNiazsanjiReport.niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        this.dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(
            new SearchPanelModel(
                'finalNiazsanjiReport',
                'personId',
                'select',
                'equals',
                this.dates,
                'title',
                '',
                this.finalNiazsanjiReport.niazsanjiYear.toString()
            )
        );
    }

    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            //this.showPlanningReport();
            const orgs = this.handleOrgChartView();
            if (this.isSuperUsers) {
                this.prepareRootsSearch(orgs);
            }
            this.searchbarModel.push(
                new SearchPanelModel('finalNiazsanjiReport', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half')
            );
            this.prepareSearchPerson(orgs);
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    //this.showPlanningReport();
                    const orgs = this.handleOrgChartView();
                    if (this.isSuperUsers) {
                        this.prepareRootsSearch(orgs);
                    }
                    this.searchbarModel.push(
                        new SearchPanelModel('finalNiazsanjiReport', 'organizationChartId', 'select', 'equals', orgs, 'fullTitle', 'half')
                    );
                    this.prepareSearchPerson(orgs);
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
        this.searchbarModel.push(
            new SearchPanelModel('finalNiazsanjiReport', 'organizationChartId', 'select', 'equals', this.orgsRoot, 'fullTitle')
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }

    registerChangeInFinalNiazsanjiReports() {
        this.eventSubscriber = this.eventManager.subscribe('finalNiazsanjiReportListModification', response => this.onSearchClick());
        //this.eventSubscriber = this.eventManager.subscribe('finalNiazsanjiReportListModification', response => this.onSearchClick());
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
            info: context.dataItem.modifyPerson != null,
            light: context.dataItem.modifyPerson == null
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

    /*public onExcelExport(args: any): void {
        // Prevent automatically saving the file. We will save it manually after we fetch and add the details

        args.preventDefault();

        const workbook = args.workbook;
        const rows = workbook.sheets[0].rows;

        // Get the default header styles.
        // Aternatively set custom styles for the details
        // https://www.telerik.com/kendo-angular-ui/components/excelexport/api/WorkbookSheetRowCell/
        const headerOptions = rows[0].cells[0];

        const data: IFinalNiazsanjiReportOrganizationMarineSuffix[] = this.finalNiazsanjiReportsOrganizations;
        /!*for (let idx = 0; idx < data.length; idx++) {
            rows. data[idx];
        }*!/
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
            saveAs(dataUrl, 'final-niazsanji-report.xlsx');
            //saveAs(dataUrl, 'Categories.xlsx');
            //this.loading =  false;
        });

    }*/
}
