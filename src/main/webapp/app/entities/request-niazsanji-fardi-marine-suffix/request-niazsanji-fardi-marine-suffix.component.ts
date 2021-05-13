import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { IUser, Principal, UserService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { RequestNiazsanjiFardiMarineSuffixService } from './request-niazsanji-fardi-marine-suffix.service';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { REQUEST_STATUS_FILTERS } from 'app/shared/constants/RequestStatusFilters';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix',
    templateUrl: './request-niazsanji-fardi-marine-suffix.component.html',
    styleUrls: ['./request-niazsanji-fardi-marine-suffix.scss']
})
export class RequestNiazsanjiFardiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    requestNiazsanjiFardis: IRequestNiazsanjiFardiMarineSuffix[];
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
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;

    yearsCollections: any[];
    coursetypes: ICourseTypeMarineSuffix[];

    totalHour: number;
    totalPriceCost: number;

    constructor(
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
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
        protected jhiTranslate: TranslateService,
        protected treeUtilities: TreeUtilities,
        protected userService: UserService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        /*this.itemsPerPage = ITEMS_PER_PAGE;*/
        this.routeData = this.activatedRoute.data.subscribe(data => {
            /*this.itemsPerPage = data.pagingParams.size ? data.pagingParams.size : ITEMS_PER_PAGE;*/
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        //this.setPermission();
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;
            this.done = true;
            this.makeCriteria(criteria.content);
        });

        this.yearsCollections = GREGORIAN_START_END_DATE;
    }

    makeCriteria(criteria?, excelExport: boolean = false) {
        if (criteria) {
            criteria = this.commonSearchCheckerService.checkYear(criteria);
        } else {
            criteria = [];
        }
        if (this.currentPerson) {
            if (this.organizationChartService.organizationchartsAll) {
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                let wantOrgIds = this.treeUtilities
                    .getAllOfChilderenIdsOfThisIdWithoutItself(
                        this.organizationChartService.organizationchartsAll,
                        this.currentPerson.organizationChartId
                    )
                    .filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds, criteria, excelExport);
            } else {
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = resp.body;
                    criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                    let wantOrgIds = this.treeUtilities
                        .getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId)
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
                        criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                        let wantOrgIds = this.treeUtilities
                            .getAllOfChilderenIdsOfThisIdWithoutItself(
                                this.organizationChartService.organizationchartsAll,
                                this.currentPerson.organizationChartId
                            )
                            .filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds, criteria, excelExport);
                    } else {
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                            this.organizationcharts = resp.body;
                            criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteria(criteria, this.organizationcharts);
                            let wantOrgIds = this.treeUtilities
                                .getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId)
                                .filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds, criteria, excelExport);
                        });
                    }
                });
            });
        }
    }

    handleAfterChart(wantOrgIds: number[], criteria, excelExport: boolean = false) {
        criteria = this.commonSearchCheckerService.checkRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
        if (this.isSuperUsers) {
            this.loadAll(criteria, excelExport);
            return;
        }
        if (wantOrgIds.length > 0) {
            let criteria1 = [
                {
                    key: 'organizationChartId.in',
                    value: wantOrgIds
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria: criteria1,
                    sort: ['id', 'asc']
                })
                .subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    let selectedPeople = resp.body;
                    if (selectedPeople.length > 0) {
                        let logins: string[] = selectedPeople.map(a => a.nationalId);
                        logins.push(this.currentPerson.nationalId);
                        criteria.push({
                            key: 'createUserLogin.in',
                            value: logins
                        });
                    } else {
                        let logins = [this.currentPerson.nationalId];
                        criteria.push({
                            key: 'createUserLogin.in',
                            value: logins
                        });
                    }
                    this.loadAll(criteria, excelExport);
                });
        } else {
            criteria.push({
                key: 'createUserLogin.in',
                value: [this.currentPerson.nationalId]
            });
            this.loadAll(criteria, excelExport);
        }
    }

    loadAll(criteria?, excelExport: boolean = false) {
        if (!this.isAdmin) {
            let orgs = this.treeUtilities
                .getParentIds(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            if (orgs.length > 0) {
                orgs.push(0);
                criteria.push({
                    key: 'status.in',
                    value: orgs
                });
            } else {
                orgs = [0];
                criteria.push({
                    key: 'status.equals',
                    value: orgs
                });
            }
        }
        if (excelExport) {
            this.requestNiazsanjiFardiService
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
        } else {
            this.requestNiazsanjiFardiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix[]>) => this.paginateRequestNiazsanjiFardis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
        if (this.isModirAmozesh || this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isTopUsers = true;
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    export() {
        this.makeCriteria(this.criteria, true);
    }
    prepareForExportExcel(res: IRequestNiazsanjiFardiMarineSuffix[]) {
        res = this.convertObjectDatesService.changeArrayDate(res);
        this.exportRequestsFinal(res);
    }
    exportRequestsFinal(res: IRequestNiazsanjiFardiMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId).fullTitle;

            if (a.allEducationalModuleId) {
                index++;
                //let allEducationalModule = this.educationalModules.find(w => w.id == a.allEducationalModuleId);
                let obj: Object;
                obj = {
                    index: index,
                    organizationChartId: org,
                    personName: a.personFullName,
                    jobTitle: a.personJobTitle,
                    educationalModule: a.allEducationalModuleTitle,
                    timeEducationalModule:
                        (a.allLearningTimePractical ? a.allLearningTimePractical : 0) +
                        (a.allLearningTimeTheorical ? a.allLearningTimeTheorical : 0),
                    levelEducationalModule: a.allSkillLevelOfSkillTitle,
                    costEducationalModule: a.costAllEducationalModule,
                    courseType: a.courseTypeTitle,
                    createDate: a.createDate,
                    status: this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus)
                };
                report.push(obj);
            }
            if (a.approvedEducationalModuleId) {
                index++;
                //let approvedEducationalModule = this.educationalModules.find(w => w.id == a.approvedEducationalModuleId);
                let obj: Object;
                obj = {
                    index: index,
                    organizationChartId: org,
                    personName: a.personFullName,
                    jobTitle: a.personJobTitle,
                    educationalModule: a.approvedEducationalModuleTitle,
                    timeEducationalModule:
                        (a.approvedLearningTimePractical ? a.approvedLearningTimePractical : 0) +
                        (a.approvedLearningTimeTheorical ? a.approvedLearningTimeTheorical : 0),
                    levelEducationalModule: a.approvedSkillLevelOfSkillTitle,
                    costEducationalModule: a.costApprovedEducationalModule,
                    courseType: a.courseTypeTitle,
                    createDate: a.createDate,
                    status: this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus)
                };
                report.push(obj);
            }
        });
        a.exportAsExcelFile(report, 'requestNiazsanjiFardis', 'marineindustryprojApp.requestNiazsanjiFardi');
    }
    transition() {
        /*this.router.navigate(['/request-niazsanji-fardi-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });

        this.criteria.push({
            page: this.page,
            size: this.itemsPerPage,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        });*/
        //this.makeCriteria(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/request-niazsanji-fardi-marine-suffix',
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
                this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleCode', 'text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'requestNiazsanjiFardi',
                        'requestStatusFilters',
                        'selectWithStringId',
                        'equals',
                        REQUEST_STATUS_FILTERS
                    )
                );
                this.prepareSearchOrgChart();
                this.prepareDate();
                this.prepareSearchEducationalModule();
                this.prepareSearchCourseType();
            });
        });

        //this.registerChangeInRequestNiazsanjiFardis();
    }
    prepareSearchCourseType() {
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
                this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'courseTypeId', 'select', 'equals', this.coursetypes));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareDate() {
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
        } else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                    /*if (!this.done) {
                        this.makeCriteria();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    prepareSearchPerson() {
        if (this.personService.people) {
            this.people = this.personService.people;
            this.handlePeople();
        } else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    this.handlePeople();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    handlePeople() {
        if (this.recommenedOrgCharts.length == this.organizationcharts.length) {
            this.recommenedPeople = this.people;
            this.searchbarModel.push(
                new SearchPanelModel('requestNiazsanjiFardi', 'personId', 'select', 'equals', this.recommenedPeople, 'fullName', 'half')
            );
        } else {
            const orgIds = this.recommenedOrgCharts.map(a => a.id);
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
                        let orgPeople = resp.body;
                        if (orgPeople.length > 0) {
                            this.recommenedPeople = orgPeople;
                        } else {
                            this.recommenedPeople = [];
                        }
                        this.searchbarModel.push(
                            new SearchPanelModel(
                                'requestNiazsanjiFardi',
                                'personId',
                                'select',
                                'equals',
                                this.recommenedPeople,
                                'fullName',
                                'half'
                            )
                        );
                    },
                    error => this.onError('فردی یافت نشد.')
                );
        }
    }
    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            if (this.isSuperUsers) {
                this.searchbarModel.push(
                    new SearchPanelModel(
                        'requestNiazsanjiFardi',
                        'organizationChartId',
                        'select',
                        'equals',
                        this.organizationcharts.filter(w => w.parentId == null)
                    )
                );
            }
            this.searchbarModel.push(
                new SearchPanelModel(
                    'requestNiazsanjiFardi',
                    'organizationChartId',
                    'select',
                    'equals',
                    this.handleOrgChartView(),
                    'fullTitle',
                    'half'
                )
            );
            this.prepareSearchPerson();
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    if (this.isSuperUsers) {
                        this.searchbarModel.push(
                            new SearchPanelModel(
                                'requestNiazsanjiFardi',
                                'organizationChartId',
                                'select',
                                'equals',
                                this.organizationcharts.filter(w => w.parentId == null)
                            )
                        );
                    }
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'requestNiazsanjiFardi',
                            'organizationChartId',
                            'select',
                            'equals',
                            this.handleOrgChartView(),
                            'fullTitle',
                            'half'
                        )
                    );
                    this.prepareSearchPerson();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    handleOrgChartView(): IOrganizationChartMarineSuffix[] {
        if (this.isAdmin) {
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

    private onSaveSuccess() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success('marineindustryprojApp.requestNiazsanjiFardi.completed');
    }

    private onSaveSuccessIgnore() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success('marineindustryprojApp.requestNiazsanjiFardi.rejected');
    }

    private onSaveError() {}
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
        /*this.eventManager.destroy(this.subOrg);
        this.eventManager.destroy(this.subEducation);*/
    }

    trackId(index: number, item: IRequestNiazsanjiFardiMarineSuffix) {
        return item.id;
    }

    registerChangeInRequestNiazsanjiFardis() {
        this.eventSubscriber = this.eventManager.subscribe('requestNiazsanjiFardiListModification', response => this.makeCriteria());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateRequestNiazsanjiFardis(data: IRequestNiazsanjiFardiMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.requestNiazsanjiFardis = this.convertObjectDatesService.changeArrayDate(data, true);
        if (!this.educationalModules) {
            if (this.educationalModuleService.educationalModules) {
                this.educationalModules = this.educationalModuleService.educationalModules;
                this.prepareResult();
                /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                /*if (!this.done) {
                    this.makeCriteria();
                }*/
            } else {
                this.educationalModuleService.query().subscribe(
                    (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                        this.educationalModules = res.body;
                        this.prepareResult();
                        /*this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
                        /*if (!this.done) {
                            this.makeCriteria();
                        }*/
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        } else {
            this.prepareResult();
        }
    }
    prepareResult() {
        this.requestNiazsanjiFardis.forEach((a: IRequestNiazsanjiFardiMarineSuffix) => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) a.organizationChartTitle = org.fullTitle;

            let education: IEducationalModuleMarineSuffix;
            if (a.allEducationalModuleId) {
                //education = this.educationalModules.find(w => w.id == a.allEducationalModuleId);
                a.skillLevelOfSkillTitle = a.allSkillLevelOfSkillTitle;
                a.totalLearningTime =
                    (a.allLearningTimePractical ? a.allLearningTimePractical : 0) +
                    (a.allLearningTimeTheorical ? a.allLearningTimeTheorical : 0);
            }
            if (a.approvedEducationalModuleId) {
                //education = this.educationalModules.find(w => w.id == a.approvedEducationalModuleId);
                a.skillLevelOfSkillTitle = a.approvedSkillLevelOfSkillTitle;
                a.totalLearningTime =
                    (a.approvedLearningTimePractical ? a.approvedLearningTimePractical : 0) +
                    (a.approvedLearningTimeTheorical ? a.approvedLearningTimeTheorical : 0);
            }
        });
        /*this.totalHour = this.requestNiazsanjiFardis
            .filter(a => a.totalLearningTime)
            .map(a => a.totalLearningTime)
            .reduce((sum, current) => sum + current);*/
        this.totalPriceCost = this.requestNiazsanjiFardis
            .filter(a => a.costApprovedEducationalModule || a.costAllEducationalModule)
            .map(
                a =>
                    (a.costApprovedEducationalModule ? a.costApprovedEducationalModule : 0) +
                    (a.costAllEducationalModule ? a.costAllEducationalModule : 0)
            )
            .reduce((sum, current) => sum + current);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    toggleImportantMessage(id: number, type: boolean) {
        this.requestNiazsanjiFardiService
            .toggleImportantMessage(id, type)
            .subscribe(
                (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.makeCriteria(this.criteria),
                (res: HttpErrorResponse) => this.onSaveError()
            );
    }
}
