import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { RequestOrganizationNiazsanjiMarineSuffixService } from './request-organization-niazsanji-marine-suffix.service';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';
import { FinalOrganizationNiazsanjiMarineSuffixService } from 'app/entities/final-organization-niazsanji-marine-suffix/final-organization-niazsanji-marine-suffix.service';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from 'app/entities/skillable-level-of-skill-marine-suffix';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { REQUEST_STATUS_FILTERS } from 'app/shared/constants/RequestStatusFilters';

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix',
    templateUrl: './request-organization-niazsanji-marine-suffix.component.html',
    styleUrls: ['./request-organization-niazsanji-marine-suffix.scss']
})
export class RequestOrganizationNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    requestOrganizationNiazsanjis: IRequestOrganizationNiazsanjiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
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

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;

    yearsCollections: any[];
    coursetypes: ICourseTypeMarineSuffix[];
    skillableLevelOfSkills: ISkillableLevelOfSkillMarineSuffix[];

    totalHour: number;
    totalPriceCost: number;

    constructor(
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        private finalOrganizationNiazsanjiMarineSuffixService: FinalOrganizationNiazsanjiMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
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

    toggleImportantMessage(id: number, type: boolean) {
        this.requestOrganizationNiazsanjiService
            .toggleImportantMessage(id, type)
            .subscribe(
                (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.makeCriteria(this.criteria),
                (res: HttpErrorResponse) => this.onSaveError()
            );
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
                if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;

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
        if (this.isAdmin) {
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
            this.requestOrganizationNiazsanjiService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.requestOrganizationNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix[]>) =>
                        this.paginateRequestOrganizationNiazsanjis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    export() {
        this.makeCriteria(this.criteria, true);
        /*let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.requestOrganizationNiazsanjis, 'requestOrganizationNiazsanjis', 'marineindustryprojApp.requestOrganizationNiazsanji');*/
    }

    prepareForExportExcel(res: IRequestOrganizationNiazsanjiMarineSuffix[]) {
        res = this.convertObjectDatesService.changeArrayDate(res);
        this.personService.query().subscribe(
            (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = resp.body;
                if (this.educationalModuleService.educationalModules) {
                    this.educationalModules = this.educationalModuleService.educationalModules;
                    this.exportRequestsFinal(res);
                } else {
                    this.educationalModuleService.query().subscribe(
                        (resp: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                            this.educationalModules = resp.body;
                            this.exportRequestsFinal(res);
                        },
                        error => this.onError('پودمانی یافت نشد.')
                    );
                }
            },
            error => this.onError('فردی یافت نشد.')
        );
    }

    exportRequestsFinal(res: IRequestOrganizationNiazsanjiMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            this.prepareRequestOrganizationNiazsanji(a);
            let personIds = a.people.map(a => a.id);
            let filteredPeople = this.people.filter(w => personIds.includes(w.id));
            let peopleNames: string[] = filteredPeople.map(a => a.fullName);

            index++;
            //let educationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);
            let obj: Object;
            obj = {
                index: index,
                organizationChartRoot: a.organizationChartRoot,
                recommendedByOrgchart: a.organizationChartTitle,
                educationalModule: a.educationalModuleTitle,
                educationalModuleCode: a.educationalModuleCode,
                educationalModuleSkillLevelOfSkillTitle: a.skillLevelOfSkillTitle,
                peopleCount: a.peopleCount,
                educationalModuleTotalLearningTime: a.totalLearningTime,
                fullPeopleTime: a.totalLearningTime * a.peopleCount,
                priceCost: a.priceCost,
                courseType: a.courseTypeTitle,
                person: peopleNames.join('،'),
                createDate: a.createDate,
                status: this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus),
                trainingGoals: a.trainingGoals,
                neededSoftwares: a.neededSoftwares,
                neededHardware: a.neededHardware,
                teacher: a.teacherFamily,
                teacherNotFound: a.teacherNotFound,
                teacherName: a.teacherName,
                teacherMobile: a.teacherMobile,
                restriction: a.restrictions && a.restrictions.length > 0 ? a.restrictions.map(w => w.title).join('-') : '',
                restrictionDescription: a.restrictionDescription,
                teachingApproach: a.teachingApproachTitle,
                goalsText: a.goalsText,
                prerequisite: a.prerequisite,
                studentsType: a.studentsType
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'requestOrganizationNiazsanjis', 'marineindustryprojApp.requestOrganizationNiazsanji');
    }

    /*reject(mymodel: IRequestOrganizationNiazsanjiMarineSuffix)
    {
        if(confirm("آیا برای رد کردن کامل درخواست موافقید؟")) {
            this.requestOrganizationNiazsanjiService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;
                model.requestStatus = RequestStatus.IGNORE;
                model.changeStatusUserLogin = this.currentAccount.login;
                this.requestOrganizationNiazsanjiService.update(model).subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccessIgnore(),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
        });
        }
    }
    complete(mymodel: IRequestOrganizationNiazsanjiMarineSuffix){
        this.requestOrganizationNiazsanjiService.find(mymodel.id).subscribe((resp) => {
            let model = resp.body;
            if (model.teacherNotFound) {
                this.onError("marineindustryprojApp.requestOrganizationNiazsanji.teacherNotFoundError");
                return;
            }
            if (confirm("آیا از تایید و نهایی کردن این درخواست مطمئنید.")) {

                let finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix = new FinalOrganizationNiazsanjiMarineSuffix();
                finalOrganizationNiazsanji.organizationChartId = model.organizationChartId;
                finalOrganizationNiazsanji.requestOrganizationNiazsanjiId = model.id;
                finalOrganizationNiazsanji.code = model.code;
                finalOrganizationNiazsanji.requestStatus = RequestStatus.ACCEPT;
                finalOrganizationNiazsanji.archived = false;
                finalOrganizationNiazsanji.description = model.description;
                finalOrganizationNiazsanji.documents = model.documents;
                finalOrganizationNiazsanji.educationalModuleId = model.educationalModuleId;
                finalOrganizationNiazsanji.neededHardware = model.neededHardware;
                finalOrganizationNiazsanji.neededSoftwares = model.neededSoftwares;
                finalOrganizationNiazsanji.people = model.people;
                finalOrganizationNiazsanji.priceCost = model.priceCost;
                finalOrganizationNiazsanji.recommendedByOrgchart = model.recommendedByOrgchart;
                finalOrganizationNiazsanji.studentsType = model.studentsType;
                finalOrganizationNiazsanji.teachApproachId = model.teachApproachId;
                finalOrganizationNiazsanji.teacherMobile = model.teacherMobile;
                finalOrganizationNiazsanji.trainingGoals = model.trainingGoals;
                finalOrganizationNiazsanji.status = 0;
                finalOrganizationNiazsanji.teacherId = model.teacherId;
                this.finalOrganizationNiazsanjiMarineSuffixService.create(finalOrganizationNiazsanji).subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.updateRequest(model),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
            }
        });
    }*/

    /*private updateRequest(model: IRequestOrganizationNiazsanjiMarineSuffix){
        //let ss: RequestStatus = RequestStatus.ACCEPT;
        model.requestStatus = RequestStatus.ACCEPT;
        model.changeStatusUserLogin = this.currentAccount.login;
        this.requestOrganizationNiazsanjiService.update(model).subscribe(
            (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }*/
    private onSaveSuccess() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success('marineindustryprojApp.requestOrganizationNiazsanji.completed');
    }

    private onSaveSuccessIgnore() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success('marineindustryprojApp.requestOrganizationNiazsanji.rejected');
    }

    private onSaveError() {}

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/request-organization-niazsanji-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        //this.makeCriteria(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/request-organization-niazsanji-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.makeCriteria();
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleCode', 'text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'priceCost', 'number', 'equals'));
            this.searchbarModel.push(
                new SearchPanelModel(
                    'requestNiazsanjiFardi',
                    'requestStatusFilters',
                    'selectWithStringId',
                    'equals',
                    REQUEST_STATUS_FILTERS
                )
            );
            this.prepareSearchDate();
            this.prepareSearchEducationalModule();
            this.prepareSearchOrgChart();
            this.prepareSearchCourseType();
            this.prepareSkillLevelOfSkill();
        });
    }

    prepareSkillLevelOfSkill() {
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                this.skillableLevelOfSkills = res.body;
                this.searchbarModel.push(
                    new SearchPanelModel('niazsanjiFardi', 'skillableLevelOfSkillId', 'select', 'equals', this.skillableLevelOfSkills)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    prepareSearchEducationalModule() {
        //this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
            /*this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle', 'half'));*/
            /*if(!this.done){
                this.makeCriteria();
            }*/
        } else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', res.body, 'fullTitle', 'half'));*/
                    /*if(!this.done){
                        this.makeCriteria();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    prepareSearchDate() {
        const dates = this.convertObjectDatesService.getYearsArray();
        const thisYear = this.convertObjectDatesService.getNowShamsiYear();
        this.searchbarModel.push(
            new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates, 'title', '', thisYear + '')
        );
    }

    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            const groups = this.organizationcharts.filter(w => w.parentId == null);
            this.searchbarModel.push(
                new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', groups, 'fullTitle')
            );
            this.searchbarModel.push(
                new SearchPanelModel(
                    'requestNiazsanjiFardi',
                    'organizationChartId',
                    'select',
                    'equals',
                    this.organizationcharts,
                    'fullTitle',
                    'half'
                )
            );
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    const groups = this.organizationcharts.filter(w => w.parentId == null);
                    this.searchbarModel.push(
                        new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', groups, 'fullTitle')
                    );
                    this.searchbarModel.push(
                        new SearchPanelModel(
                            'requestNiazsanjiFardi',
                            'organizationChartId',
                            'select',
                            'equals',
                            this.organizationcharts,
                            'fullTitle',
                            'half'
                        )
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IRequestOrganizationNiazsanjiMarineSuffix) {
        return item.id;
    }

    registerChangeInRequestOrganizationNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('requestOrganizationNiazsanjiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateRequestOrganizationNiazsanjis(data: IRequestOrganizationNiazsanjiMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.requestOrganizationNiazsanjis = this.convertObjectDatesService.changeArrayDate(data, true);
        this.requestOrganizationNiazsanjis.forEach((a: IRequestOrganizationNiazsanjiMarineSuffix) => {
            this.prepareRequestOrganizationNiazsanji(a);
        });
    }

    prepareRequestOrganizationNiazsanji(a: IRequestOrganizationNiazsanjiMarineSuffix) {
        a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
        const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
        if (org) {
            a.organizationChartTitle = org.fullTitle;
            a.organizationChartRoot = org.rootTitle;
        }
        a.peopleCount = a.people.length;
        a.fullLearningTime = a.totalLearningTime * a.peopleCount;
        /*let education: IEducationalModuleMarineSuffix = this.educationalModules.find(w => w.id == a.educationalModuleId);
        if (education) {
            a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
            a.totalLearningTime =
                a.peopleCount * (education.learningTimePractical ? education.learningTimePractical : 0) +
                (education.learningTimeTheorical ? education.learningTimeTheorical : 0);
        }*/
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.warning(errorMessage);
    }

    /*createCriteria(criteria?): any{

        if(criteria)
        {
            let val = +criteria.find(a => a.key == 'yearId.equals').value;
            criteria.pop('yearId');
            if(val){
                let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key:'createDate.lessOrEqualThan', value: endDate
                });
                criteria.push({
                    key:'createDate.greaterOrEqualThan', value: beginDate
                });
            }
        }
        if(!this.isAdmin) {
            if (criteria) {
                criteria.push({key: 'createUserLogin.equals', value: this.currentAccount.login})
            }
            else {
                criteria = [
                    {key: 'createUserLogin.equals', value: this.currentAccount.login}
                ];
            }
        }
        return criteria;
    }*/
}
