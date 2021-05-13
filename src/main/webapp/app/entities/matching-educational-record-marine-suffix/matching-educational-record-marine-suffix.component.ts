import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { IUser, Principal, UserService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { MatchingEducationalRecordMarineSuffixService } from './matching-educational-record-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { TranslateService } from '@ngx-translate/core';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { REQUEST_STATUS_FILTERS } from 'app/shared/constants/RequestStatusFilters';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from 'app/entities/employment-type-marine-suffix';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from 'app/entities/skillable-level-of-skill-marine-suffix';

@Component({
    selector: 'mi-matching-educational-record-marine-suffix',
    templateUrl: './matching-educational-record-marine-suffix.component.html'
})
export class MatchingEducationalRecordMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    matchingEducationalRecords: IMatchingEducationalRecordMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    recommenedPeople: IPersonMarineSuffix[];
    users: IUser[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    skillablelevelofskills: ISkillableLevelOfSkillMarineSuffix[];

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

    constructor(
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        protected skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected treeUtilities: TreeUtilities,
        protected userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService,
        protected jhiTranslate: TranslateService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;
            this.done = true;
            this.makeCriteria(criteria.content);
        });

        this.yearsCollections = GREGORIAN_START_END_DATE;
    }
    complete(id: number) {
        if (confirm('آیا برای تایید نهایی مطمئنید؟')) {
            this.matchingEducationalRecordService.find(id).subscribe((resp: HttpResponse<IMatchingEducationalRecordMarineSuffix>) => {
                let matchingEducationalRecord = resp.body;
                matchingEducationalRecord.bossStatus = 40;
                matchingEducationalRecord.requestStatus = RequestStatus.ACCEPT;
                matchingEducationalRecord.conversation =
                    ' درخواست توسط ' +
                    this.currentPerson.fullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' تایید نهایی شد. ';
                matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                this.matchingEducationalRecordService
                    .update(matchingEducationalRecord)
                    .subscribe((resp: HttpResponse<IMatchingEducationalRecordMarineSuffix>) => {
                        this.makeCriteria(this.criteria);
                    });
            });
        }
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
        criteria = this.checkMatchingEducationalRecordRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
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
    public checkMatchingEducationalRecordRequestStatusFilters(criteria, currentPersonOrgChartId: number) {
        const requestStatusFilters = criteria.find(a => a.key == 'requestStatusFilters.equals');
        if (requestStatusFilters) {
            const val = requestStatusFilters.value;
            criteria = criteria.filter(a => a.key != 'requestStatusFilters.equals');
            if (val) {
                if (val == RequestStatus.ACCEPT || val == RequestStatus.IGNORE) {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: val
                    });
                } else if (val == RequestStatus.RETURNED) {
                    criteria.push({
                        key: 'hasImportantMessage.equals',
                        value: true
                    });
                } else {
                    if (this.isKarshenasArshadAmozeshSazman) {
                        criteria.push(
                            {
                                key: 'bossStatus.in',
                                value: [10, 30]
                            },
                            {
                                key: 'requestStatus.equals',
                                value: 'NEW'
                            }
                        );
                    } else if (this.isModirKolAmozesh) {
                        criteria.push(
                            {
                                key: 'bossStatus.in',
                                value: [20, 30]
                            },
                            {
                                key: 'requestStatus.equals',
                                value: 'NEW'
                            }
                        );
                    } else {
                        criteria.push(
                            {
                                key: 'chartStatus.equals',
                                value: currentPersonOrgChartId
                            },
                            {
                                key: 'requestStatus.equals',
                                value: RequestStatus.NEW
                            }
                        );
                    }
                }
            }
        }
        return criteria;
    }
    loadAll(criteria?, excelExport: boolean = false) {
        if (!this.isSuperUsers) {
            let orgs = this.treeUtilities
                .getParentIds(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            if (orgs.length > 0) {
                orgs.push(0);
                criteria.push({
                    key: 'chartStatus.in',
                    value: orgs
                });
            } else {
                orgs = [0];
                criteria.push({
                    key: 'chartStatus.equals',
                    value: orgs
                });
            }
        }
        if (excelExport) {
            this.matchingEducationalRecordService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IMatchingEducationalRecordMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.matchingEducationalRecordService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IMatchingEducationalRecordMarineSuffix[]>) =>
                        this.paginateMatchingEducationalRecords(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    export() {
        this.makeCriteria(this.criteria, true);
    }
    prepareForExportExcel(res: IMatchingEducationalRecordMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);
        res = this.convertObjectDatesService.changeArrayDate(res);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            index++;

            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.chartStatus, a.requestStatus);
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) a.organizationChartTitle = org.fullTitle;
            let obj: Object;
            obj = {
                index: index,
                organizationChart: a.organizationChartTitle,
                person: a.personFullName,
                jobTitle: a.personJobTitle,
                description: a.description,
                createDate: a.createDate,
                status: this.treeUtilities.getStatusMeaning(this.organizationcharts, a.chartStatus, a.requestStatus)
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
        /*this.router.navigate(['/matching-educational-record-marine-suffix'], {
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
            '/matching-educational-record-marine-suffix',
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
                this.prepareEmploymentType();
                this.prepareSkill();
            });
        });
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }
    prepareSkill() {
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                this.skillablelevelofskills = res.body;
                this.searchbarModel.push(
                    new SearchPanelModel('matchingEducationalRecord', 'skillableLevelOfSkillId', 'select', 'equals', res.body)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareEmploymentType() {
        this.employmentTypeService.query().subscribe(
            (res: HttpResponse<IEmploymentTypeMarineSuffix[]>) => {
                this.searchbarModel.push(
                    new SearchPanelModel('applicationProcess', 'personEmploymentTypeId', 'select', 'equals', res.body)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareDate() {
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));
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
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IMatchingEducationalRecordMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInMatchingEducationalRecords() {
        this.eventSubscriber = this.eventManager.subscribe('matchingEducationalRecordListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateMatchingEducationalRecords(data: IMatchingEducationalRecordMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        this.matchingEducationalRecords = this.convertObjectDatesService.changeArrayDate(data, true);
        this.matchingEducationalRecords.forEach((a: IMatchingEducationalRecordMarineSuffix) => {
            a.statusMeaning = this.getStatusMeaning(this.organizationcharts, a.chartStatus, a.bossStatus, a.requestStatus);
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if (org) a.organizationChartTitle = org.fullTitle;
        });
    }
    getStatusMeaning(
        organizationcharts: IOrganizationChartMarineSuffix[],
        chartStatus: number,
        bossStatus,
        requestStatus: RequestStatus
    ): string {
        let organizationchart = organizationcharts.find(a => a.id == chartStatus);
        if (organizationchart) {
            if (requestStatus == RequestStatus.NEW) {
                return 'منتظر تایید ' + organizationchart.title;
            }
            if (requestStatus == RequestStatus.IGNORE) {
                return 'رد شده توسط ' + organizationchart.title;
            }
        } else {
            if (chartStatus == 0 && requestStatus == RequestStatus.NEW) return 'منتظر تکمیل و بررسی اطلاعات توسط آموزش سازمان';
            if (chartStatus == 0 && requestStatus == RequestStatus.IGNORE) return 'رد شده توسط آموزش سازمان';
            if (chartStatus == 0 && requestStatus == RequestStatus.ACCEPT) return 'تایید نهایی آموزش سازمان (تایید نهایی)';
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    toggleImportantMessage(id: number, type: boolean) {
        this.matchingEducationalRecordService
            .toggleImportantMessage(id, type)
            .subscribe(
                (res: HttpResponse<IMatchingEducationalRecordMarineSuffix>) => this.makeCriteria(this.criteria),
                (res: HttpErrorResponse) => this.onSaveError()
            );
    }
    private onSaveError() {}
}
