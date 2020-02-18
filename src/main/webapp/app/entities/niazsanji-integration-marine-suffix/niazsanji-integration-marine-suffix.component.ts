import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {NiazsanjiIntegrationMarineSuffixService} from './niazsanji-integration-marine-suffix.service';
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {ISkillableLevelOfSkillMarineSuffix} from "app/shared/model/skillable-level-of-skill-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {SkillableLevelOfSkillMarineSuffixService} from "app/entities/skillable-level-of-skill-marine-suffix";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {TranslateService} from '@ngx-translate/core';
import * as $ from 'jquery';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {EducationalModuleType} from "app/shared/model/enums/EducationalModuleType";
import {INiazsanjiIntegrationMarineSuffix} from "app/shared/model/niazsanji-integration-marine-suffix.model";
import {INiazsanjiFardiMarineSuffix} from "app/shared/model/niazsanji-fardi-marine-suffix.model";

@Component({
    selector: 'mi-niazsanji-integration-marine-suffix',
    templateUrl: './niazsanji-integration-marine-suffix.component.html'
})
export class NiazsanjiIntegrationMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    niazsanjiIntegrations: INiazsanjiIntegrationMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    skillableLevelOfSkills: ISkillableLevelOfSkillMarineSuffix[];
    people: IPersonMarineSuffix[];
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
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;
    years: any[];
    yearsCollections: any[];
    selectedNiazSanji: number[] = [];
    selectedYear: number;
    counter: number = 0;
    coursetypes: ICourseTypeMarineSuffix[];

    totalHour: number;
    totalPriceCost: number;

    constructor(
        protected niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
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
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private courseTypeService: CourseTypeMarineSuffixService,
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        private jhiTranslate: TranslateService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) => {

            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
        this.years = this.yearsCollections.map(a => a.year);
    }

    selectYear(){
        if(this.selectedNiazSanji.length > 0){
            if(this.selectedYear) {
                this.selectedNiazSanji.forEach((a) => {
                    this.niazsanjiIntegrationService.find(a).subscribe((resp: HttpResponse<INiazsanjiIntegrationMarineSuffix>) => {
                            let niazSanjiIntegration = resp.body;
                            niazSanjiIntegration.niazsanjiYear = this.selectedYear;
                            this.niazsanjiIntegrationService.update(niazSanjiIntegration).subscribe(
                                (res: HttpResponse<INiazsanjiIntegrationMarineSuffix>) => this.completeSuccess(),
                                (error1) => this.onError(error1.message));
                        },
                        (error1) => this.onError(error1.message));
                });
            }
            else{
                alert('لطفا سال نیازسنجی را انتخاب نمائید.');
            }
        }
        else{
            alert('لطفا حداقل یک نیازسنجی را انتخاب نمائید.');
        }
    }

    completeSuccess(){
        this.counter++;
        if(this.selectedNiazSanji.length == this.counter){
            this.loadAll(this.criteria);
            this.counter = 0;
            this.selectedNiazSanji = [];
        }
    }
    createCriteria(criteria?): any {

        if (criteria) {
            const year = criteria.find(a => a.key == 'yearId.equals');
            if(year) {
                const val = +year.value;
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
                }
            }
            const org = criteria.find(a => a.key == 'organizationChartId.equals');
            if(org) {
                const orgId = +org.value;
                criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
                if (orgId) {
                    const childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, orgId);
                    criteria.push({
                        key: 'organizationChartId.in', value: childIds
                    });
                }
            }
        }
        else{
            criteria = [];
        }
        if(this.isModirKolAmozesh){
            criteria.push({
                key: 'status.greaterOrEqualThan', value: 10
            });
        }
        return criteria;
    }

    loadAll(criteria?, exportExcel: boolean = false) {
        criteria = this.createCriteria(criteria);
        if(exportExcel){

        }
        else{
            this.niazsanjiIntegrationService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<INiazsanjiIntegrationMarineSuffix[]>) => this.paginateNiazsanjiIntegrations(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }

    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/niazsanji-integration-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/niazsanji-integration-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }
    onChange(id:number, isChecked: boolean) {
        if(isChecked) {
            if(!this.selectedNiazSanji.includes(id))
                this.selectedNiazSanji.push(id);
        } else {
            this.selectedNiazSanji = this.selectedNiazSanji.filter(a => a != id);
        }
    }
    selectAll(isChecked: boolean){
        if(isChecked){
            $('.mycheckbox').each((a, item) => {
                $(item).prop('checked',true);
                this.onChange(item.id,true)
            });
        }
        else{
            $('.mycheckbox').each((a, item) => {
                $(item).prop('checked',false);
                this.onChange(item.id,false)
            });
        }
    }


    export() {
        this.loadAll(this.criteria, true);
    }
    finalExportToExcel(excelNiazSanjiFardis: INiazsanjiFardiMarineSuffix[]){
        let a = new ExcelService(this.jhiTranslate);
        excelNiazSanjiFardis = this.convertObjectDatesService.changeArrayDate(excelNiazSanjiFardis);
        let report = [];
        let index: number = 0;
        excelNiazSanjiFardis.forEach(a => {
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId).fullTitle;
            let person = this.people.find(w => w.id == a.personId);
            index++;
            let approvedEducationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);
            let obj: Object;
            obj = {'index': index,
                'organizationChart': org,
                'personName': person.fullName,
                'jobTitle': person.jobTitle,
                'educationalModule': a.educationalModuleTitle,
                'timeEducationalModule': (approvedEducationalModule.learningTimePractical ? approvedEducationalModule.learningTimePractical : 0) + (approvedEducationalModule.learningTimeTheorical ? approvedEducationalModule.learningTimeTheorical : 0),
                'levelEducationalModule': approvedEducationalModule.skillableLevelOfSkillTitle,
                'costEducationalModule': a.priceCost,
                'createDate': a.createDate,
                'niazsanjiYear': a.niazsanjiYear
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'niazsanjiFardis', 'marineindustryprojApp.niazsanjiFardi');
    }
    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined) {
                this.isAdmin = true;
            }

            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleCode', 'text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
            let educationalModuleType = [{
                id: EducationalModuleType.ALL,
                title: 'کل پودمان'
            },{
                id: EducationalModuleType.APPROVED,
                title: 'نیازسنجی از شناسنامه شغلی'
            }];
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleType', 'select', 'equals', educationalModuleType));
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'costEducationalModule', 'number', 'equals'));
            this.prepareSearchOrgChart();
            this.prepareSearchPerson();
            this.prepareSearchEducationalModule();
            this.prepareSearchDate();
            this.prepareSearchCourseType();
            this.prepareSkillLevelOfSkill();

        });
    }
    prepareSkillLevelOfSkill(){
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {

                this.skillableLevelOfSkills = res.body;
                this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'skillableLevelOfSkillId', 'select', 'equals', this.skillableLevelOfSkills));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchCourseType(){
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {

                this.coursetypes = res.body;
                this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'courseTypeId', 'select', 'equals', this.coursetypes));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchEducationalModule(){

        if(this.educationalModuleService.educationalModules){
            this.educationalModules = this.educationalModuleService.educationalModules
            /*this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, "fullTitle",'half'));*/
            /*if (!this.done) {
                this.loadAll();
            }*/
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*      this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', res.body, "fullTitle",'half'));*/
                    /*if (!this.done) {
                        this.loadAll();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    prepareSearchPerson(){
        if(this.personService.people){
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'personId', 'select', 'equals', this.people, "fullName"));
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    //this.people.forEach(a => a["title"] = a.fullName);
                    this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'personId', 'select', 'equals', this.people, "fullName"));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    prepareSearchDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        const thisYear = this.convertObjectDatesService.getNowShamsiYear();
        this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'niazsanjiYear', 'select', 'equals', dates, 'title'));
    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            const groups = this.organizationcharts.filter(a => a.parentId == null);
            this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', groups, 'title'));
            this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    const groups = this.organizationcharts.filter(a => a.parentId == null);
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', groups, 'title'));
                    //this.organizationcharts = this.tree
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: INiazsanjiIntegrationMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInNiazsanjiIntegrations() {
        this.eventSubscriber = this.eventManager.subscribe('niazsanjiIntegrationListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateNiazsanjiIntegrations(data: INiazsanjiIntegrationMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.niazsanjiIntegrations = this.convertObjectDatesService.changeArrayDate(data, true);
        this.niazsanjiIntegrations.forEach(a => {
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartRootTitle = org.fullTitle.split('>')[0];
            const education: IEducationalModuleMarineSuffix = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if(education){
                a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
                a.totalLearningTime = (education.learningTimePractical ? education.learningTimePractical : 0) + (education.learningTimeTheorical ? education.learningTimeTheorical : 0)
            }
        });
        /*if(this.niazsanjiIntegrations) {
            const totalLearningTimes = this.niazsanjiIntegrations.filter(a => a.totalLearningTime).map(a => a.totalLearningTime);
            if(totalLearningTimes)
                this.totalHour = totalLearningTimes.reduce((sum, current) => sum + current);

            const priceCosts = this.niazsanjiFardis.filter(a => a.priceCost).map(a => a.priceCost);
            if(priceCosts)
                this.totalPriceCost = priceCosts.reduce((sum, current) => sum + current);
        }*/
    }
    /*toggleImportantMessage(id: number, type: boolean){

        this.niazsanjiIntegrationService.toggleImportantMessage(id, type).subscribe(
            (res: HttpResponse<INiazsanjiFardiMarineSuffix>) => this.loadAll(this.criteria),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }*/
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
