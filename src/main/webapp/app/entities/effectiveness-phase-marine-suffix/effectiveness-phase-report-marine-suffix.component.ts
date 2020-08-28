import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { AccountService, Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EffectivenessPhaseMarineSuffixService } from './effectiveness-phase-marine-suffix.service';
import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from 'app/entities/effectiveness-phase-level-marine-suffix';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { MONTHS } from 'app/shared/constants/months.constants';
import { FINALNIAZSANJISTATUSMEANING } from 'app/shared/constants/final-niazsanji-report-status-meaning.constants';
import { EFFECTIVENESSPHASELEVELS } from 'app/shared/constants/effectiveness-phase-levels.constants';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { TranslateService } from '@ngx-translate/core';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { NIAZSANJI_SOURCE_FILTERS } from 'app/shared/constants/NiazsanjiSourceFilters';

@Component({
    selector: 'mi-effectiveness-phase-report-marine-suffix',
    templateUrl: './effectiveness-phase-report-marine-suffix.component.html',
    styleUrls: ['./effectiveness-phase-marine-suffix.scss']
})
export class EffectivenessPhaseReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    effectivenessPhases: IEffectivenessPhaseMarineSuffix[];
    effectivenessPhaseLevels: IEffectivenessPhaseLevelMarineSuffix[];
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
    effectivenessPhaseLevelsStatus: any[] = EFFECTIVENESSPHASELEVELS;

    done: boolean = false;
    yearsCollections: any[];

    searchbarModel: SearchPanelModel[] = [];
    criteriaSubscriber: Subscription;
    criteria: any;

    averageFinalScore: number = 0;
    averageWeightedPoints: number = 0;

    constructor(
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        protected effectivenessPhaseLevelService: EffectivenessPhaseLevelMarineSuffixService,
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
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;

            this.done = true;
            this.makeCriteria(criteria.content);
        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
    }

    makeCriteria(criteria?, excelExport: boolean = false) {
        if (criteria) {
        } else {
            criteria = [];
        }
        if (this.currentPerson) {
            if (this.organizationChartService.organizationchartsAll) {
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                let wantOrgIds = this.treeUtilities
                    .getAllOfThisTreeIds(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId)
                    .filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds, criteria, excelExport);
            } else {
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = resp.body;
                    let wantOrgIds = this.treeUtilities
                        .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                        .filter(this.treeUtilities.onlyUnique);
                    this.handleAfterChart(wantOrgIds, criteria, excelExport);
                });
            }
        } else {
            this.principal.identity().then(account => {
                this.currentAccount = account;
                this.setRoles(account);

                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;

                    if (this.organizationChartService.organizationchartsAll) {
                        this.organizationcharts = this.organizationChartService.organizationchartsAll;
                        let wantOrgIds = this.treeUtilities
                            .getAllOfThisTreeIds(
                                this.organizationChartService.organizationchartsAll,
                                this.currentPerson.organizationChartId
                            )
                            .filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds, criteria, excelExport);
                    } else {
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                            this.organizationcharts = resp.body;

                            let wantOrgIds = this.treeUtilities
                                .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                                .filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds, criteria, excelExport);
                        });
                    }
                });
            });
        }
    }
    handleAfterChart(wantOrgIds: number[], criteria, excelExport: boolean = false) {
        //criteria = this.commonSearchCheckerService.checkRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
        if (this.isSuperUsers) {
            this.loadAll(criteria, excelExport);
            return;
        }
        if (wantOrgIds.length > 0) {
            criteria.push({
                key: 'organizationChartId.in',
                value: wantOrgIds
            });
        } else {
            criteria.push({
                key: 'createUserLogin.in',
                value: [this.currentPerson.nationalId]
            });
        }
        this.loadAll(criteria, excelExport);
    }

    loadAll(criteria?, excelExport: boolean = false) {
        if (excelExport) {
            this.effectivenessPhaseService
                .query({
                    page: this.page - 1,
                    size: 2000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.effectivenessPhaseService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => this.paginateEffectivenessPhases(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    export() {
        this.makeCriteria(this.criteria, true);
    }
    prepareForExportExcel(res: IEffectivenessPhaseMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);

        let effectivenessPhases: IEffectivenessPhaseMarineSuffix[] = this.convertObjectDatesService.changeArrayDate(res);
        effectivenessPhases.forEach(a => {
            switch (a.status) {
                case 0:
                    a.statusMeaning = 'آماده اثربخشی';
                    break;
                case 10:
                    a.statusMeaning = 'شروع به اثربخشی';
                    break;
                case 20:
                    a.statusMeaning = 'تکمیل اثربخشی';
                    break;
            }
            if (a.finalNiazsanjiReport.organizationChartId) {
                a.finalNiazsanjiReport.organizationChartRootTitle = this.organizationcharts.find(
                    w => w.id == a.finalNiazsanjiReport.organizationChartId
                ).rootTitle;
                a.finalNiazsanjiReport.organizationChartFullTitle = this.organizationcharts.find(
                    w => w.id == a.finalNiazsanjiReport.organizationChartId
                ).fullTitle;
            }
        });

        let report = [];
        let index: number = 0;
        effectivenessPhases.forEach(a => {
            index++;

            let unitOfMeasurement = '';
            this.jhiTranslate.get('marineindustryprojApp.UnitOfMeasurement.' + a.effectivenessPhaseLevel.unitOfMeasurement).subscribe(w => {
                unitOfMeasurement = w;
            });

            let obj: Object;
            obj = {
                index: index,
                organizationChartGroup: a.finalNiazsanjiReport.organizationChartRootTitle,
                organizationChart: a.finalNiazsanjiReport.organizationChartFullTitle,
                courseTitle: a.finalNiazsanjiReport.educationalModuleTitle,
                courseCode: a.finalNiazsanjiReport.educationalModuleCode,
                finishDate:
                    a.finalNiazsanjiReport.runPhases && a.finalNiazsanjiReport.runPhases.length > 0
                        ? a.finalNiazsanjiReport.runPhases[0].finishDate
                        : '',
                criteria: a.effectivenessPhaseLevelTitle,
                unitOfMeasurement: unitOfMeasurement,
                goal: a.effectivenessPhaseLevel.goal,
                firstScore: a.firstScore,
                secondScore: a.secondScore,
                finalScore: a.finalScore,
                weightedPoints: a.weightedPoints,
                weight: a.effectivenessPhaseLevel.weight,
                startPhaseDate: a.startPhaseDate,
                finishPhaseDate: a.finishPhaseDate,
                niazsanjiYear: a.finalNiazsanjiReport.niazsanjiYear,
                teacher: a.finalNiazsanjiReport.teacherName
                    ? a.finalNiazsanjiReport.teacherName
                    : '' + ' ' + a.finalNiazsanjiReport.teacherFamily ? a.finalNiazsanjiReport.teacherFamily : '',
                status: a.statusMeaning,
                selectedEffectivenessPhaseLevel: a.finalNiazsanjiReport.selectedEffectivenessPhaseLevelTitle,
                description: a.description,
                createDate: a.createDate,
                modifyDate: a.modifyDate
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'effectivenessPhases', 'marineindustryprojApp.effectivenessPhase');
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/effectiveness-phase-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();*/
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/effectiveness-phase-marine-suffix',
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
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;
                this.prepareDate();
                this.prepareSearchOrgChart();
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleCode', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleTitle', 'text', 'contains'));
                /*if (this.isSuperUsers)
                    this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'status', 'select', 'equals', this.statusMeaning, 'mean'));*/
                this.searchbarModel.push(new SearchPanelModel('effectivenessPhase', 'finalNiazsanjiReportId', 'text', 'equals'));
                this.prepareSearchCourseType();
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'effectivenessPhase',
                        'selectedEffectivenessPhaseLevel',
                        'select',
                        'equals',
                        EFFECTIVENESSPHASELEVELS
                    )
                );
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'effectivenessPhase',
                        'currentEffectivenessPhaseLevel',
                        'select',
                        'lessOrEqualThan',
                        EFFECTIVENESSPHASELEVELS
                    )
                );
                //this.searchbarModel.push(new SearchPanelModel('fieldOfStudy', 'currentEffectivenessPhaseLevel', 'select', 'lessOrEqualThan', EFFECTIVENESSPHASELEVELS));
                //this.prepareSearchEffectivenessPhaseLevel();
                //this.prepareSearchEducationalModule();
                //this.preparePeople();
            });
        });
        /*this.loadAll();
        this.registerChangeInEffectivenessPhases();*/
    }
    prepareSearchEffectivenessPhaseLevel() {
        this.effectivenessPhaseLevelService.query().subscribe(
            (res: HttpResponse<IEffectivenessPhaseLevelMarineSuffix[]>) => {
                this.effectivenessPhaseLevels = res.body;
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'effectivenessPhase',
                        'effectivenessPhaseLevelId',
                        'select',
                        'equals',
                        this.effectivenessPhaseLevels
                    )
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }
    prepareDate() {
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'niazsanjiYear', 'select', 'equals', dates, 'title'));
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
            this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'personId', 'select', 'equals', this.people, 'fullName'));
        } else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    this.searchbarModel.push(
                        new SearchPanelModel('finalNiazsanjiReport', 'personId', 'select', 'equals', this.people, 'fullName')
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
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

    protected onSaveError(res) {
        console.error(res);
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEffectivenessPhaseMarineSuffix) {
        return item.id;
    }

    registerChangeInEffectivenessPhases() {
        this.eventSubscriber = this.eventManager.subscribe('effectivenessPhaseListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateEffectivenessPhases(data: IEffectivenessPhaseMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        this.effectivenessPhases = this.convertObjectDatesService.changeArrayDate(data);
        this.effectivenessPhases.forEach(a => {
            switch (a.status) {
                case 0:
                    a.statusMeaning = 'آماده اثربخشی';
                    break;
                case 10:
                    a.statusMeaning = 'شروع به اثربخشی';
                    break;
                case 20:
                    a.statusMeaning = 'تکمیل اثربخشی';
                    break;
            }
            if (a.finalNiazsanjiReport.organizationChartId) {
                a.finalNiazsanjiReport.organizationChartRootTitle = this.organizationcharts.find(
                    w => w.id == a.finalNiazsanjiReport.organizationChartId
                ).rootTitle;
            }
        });
        this.averageFinalScore =
            this.effectivenessPhases.map(a => a.finalScore).reduce((sum, current) => sum + current) / this.effectivenessPhases.length;
        this.averageWeightedPoints =
            this.effectivenessPhases.map(a => a.weightedPoints).reduce((sum, current) => sum + current) / this.effectivenessPhases.length;

        /*const effectivenessPhaseLevelIds = this.effectivenessPhases.map(a => a.effectivenessPhaseLevelId);
        const criteria = [{
            key: 'effectivenessPhaseLevelId.in',
            value: effectivenessPhaseLevelIds
        }];
        this.effectivenessPhaseLevelService.query(criteria).subscribe((resp: HttpResponse<IEffectivenessPhaseLevelMarineSuffix[]>) => {
                this.effectivenessPhaseLevels = resp.body;
                this.effectivenessPhases.forEach(a => {
                    a.effectivenessPhaseLevel = this.effectivenessPhaseLevels.find(e => e.id == a.effectivenessPhaseLevelId);
                });
            },
            (res: HttpErrorResponse) => this.onError(res.message))*/
    }

    previousState() {
        window.history.back();
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
