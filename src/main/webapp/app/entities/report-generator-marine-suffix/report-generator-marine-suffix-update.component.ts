import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';
import { ReportGeneratorMarineSuffixService } from './report-generator-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IAuthority } from 'app/shared/model/authority.model';
import { AuthorityService } from 'app/admin/authority';
import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';
import { ReportGeneratorAuthorityMarineSuffixService } from 'app/entities/report-generator-authority-marine-suffix';
import { Principal } from 'app/core';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { NIAZSANJI_SOURCE_FILTERS } from 'app/shared/constants/NiazsanjiSourceFilters';
import { EFFECTIVENESSPHASELEVELS } from 'app/shared/constants/effectiveness-phase-levels.constants';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { FINALNIAZSANJISTATUSMEANING } from 'app/shared/constants/final-niazsanji-report-status-meaning.constants';
import { REPORTGENERATORFIELDS } from 'app/shared/constants/report-generator-fields';

@Component({
    selector: 'mi-report-generator-marine-suffix-update',
    templateUrl: './report-generator-marine-suffix-update.component.html'
})
export class ReportGeneratorMarineSuffixUpdateComponent implements OnInit {
    currentAccount: any;
    reportGenerator: IReportGeneratorMarineSuffix;
    isSaving: boolean;
    authorities: IAuthority[];
    selectedAuthorities: IAuthority[] = [];
    columnsList: any[] = [];
    selectedColumns: string[] = [];

    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    coursetypes: ICourseTypeMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
    recommendedPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    orgsRoot: IOrganizationChartMarineSuffix[];

    dates: any[];
    months: any[] = MONTHS;
    statusMeaning: any[] = FINALNIAZSANJISTATUSMEANING;
    effectivenessPhaseLevels: any[] = EFFECTIVENESSPHASELEVELS;

    done: boolean = false;
    yearsCollections: any[];

    niazSanjiSource: boolean = true;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    searchbarModel: SearchPanelModel[] = [];
    criteriaSubscriber: Subscription;
    criteria: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected reportGeneratorService: ReportGeneratorMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected authorityService: AuthorityService,
        protected reportGeneratorAuthorityService: ReportGeneratorAuthorityMarineSuffixService,
        protected accountService: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private treeUtilities: TreeUtilities,
        protected activatedRoute: ActivatedRoute
    ) {}

    searchCalled(v) {
        debugger;
        this.reportGenerator.searchParams = v;
    }

    ngOnInit() {
        this.isSaving = false;
        this.loadNeededData();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
            this.loadSearchOptions();
        });
    }
    loadSearchOptions() {
        this.searchbarModel.push(
            new SearchPanelModel('finalNiazsanjiReport', 'niazSanjiSource', 'selectWithStringId', 'equals', NIAZSANJI_SOURCE_FILTERS)
        );
        this.prepareDate();
        this.prepareSearchOrgChart();
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleCode', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'educationalModuleTitle', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'status', 'select', 'equals', this.statusMeaning, 'mean'));
        this.searchbarModel.push(new SearchPanelModel('finalNiazsanjiReport', 'id', 'text', 'equals'));
        this.prepareSearchCourseType();
        this.searchbarModel.push(
            new SearchPanelModel('finalNiazsanjiReport', 'selectedEffectivenessPhaseLevel', 'select', 'equals', EFFECTIVENESSPHASELEVELS)
        );
        this.searchbarModel.push(
            new SearchPanelModel('finalNiazsanjiReport', 'currentEffectivenessPhaseLevel', 'select', 'equals', EFFECTIVENESSPHASELEVELS)
        );
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
    prepareRootsSearch(orgs: IOrganizationChartMarineSuffix[]) {
        this.orgsRoot = orgs.filter(a => a.parentId == null);
        this.searchbarModel.push(
            new SearchPanelModel('finalNiazsanjiReport', 'organizationChartId', 'select', 'equals', this.orgsRoot, 'fullTitle')
        );
    }
    loadNeededData() {
        this.activatedRoute.data.subscribe(({ reportGenerator }) => {
            debugger;
            this.reportGenerator = reportGenerator;

            for (let fieldsKey in REPORTGENERATORFIELDS) {
                this.columnsList.push({
                    id: fieldsKey,
                    title: REPORTGENERATORFIELDS[fieldsKey]
                });
            }

            this.authorityService.authorities().subscribe(
                (res: HttpResponse<IAuthority[]>) => {
                    this.authorities = res.body;
                    let criteria = [
                        {
                            key: 'reportGeneratorId.equals',
                            value: this.reportGenerator.id
                        }
                    ];
                    this.reportGeneratorAuthorityService
                        .query({
                            page: 0,
                            size: 20000,
                            criteria,
                            sort: ['id', 'asc']
                        })
                        .subscribe(
                            (resp: HttpResponse<IJamHelpAuthorityMarineSuffix[]>) => {
                                const names = resp.body.map(a => a.authorityName);
                                this.selectedAuthorities = this.authorities.filter(a => names.includes(a.name));
                            },
                            error => this.onError('موردی یافت نشد.')
                        );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        debugger;
        this.reportGenerator.authorityNames = this.selectedAuthorities.join(',');
        let index = 0;
        const a = this.selectedColumns.map(w => {
            index++;
            return {
                columnName: w,
                displayOrder: index
            };
        });
        this.reportGenerator.columnNames = JSON.stringify(a);
        if (this.reportGenerator.id !== undefined) {
            this.subscribeToSaveResponse(this.reportGeneratorService.update(this.reportGenerator));
        } else {
            this.subscribeToSaveResponse(this.reportGeneratorService.create(this.reportGenerator));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportGeneratorMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IReportGeneratorMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }

    private setRoles(account: any) {
        if (account) {
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }
}
