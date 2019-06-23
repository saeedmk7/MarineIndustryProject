import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import {
    FinalOrganizationNiazsanjiMarineSuffix,
    IFinalOrganizationNiazsanjiMarineSuffix
} from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { FinalOrganizationNiazsanjiMarineSuffixService } from './final-organization-niazsanji-marine-suffix.service';
import {TranslateService} from '@ngx-translate/core';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestOrganizationNiazsanjiMarineSuffixService} from "app/entities/request-organization-niazsanji-marine-suffix/request-organization-niazsanji-marine-suffix.service";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix/organization-chart-marine-suffix.service";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix/person-marine-suffix.service";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import * as $ from 'jquery';
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";

@Component({
    selector: 'mi-final-organization-niazsanji-marine-suffix',
    templateUrl: './final-organization-niazsanji-marine-suffix.component.html'
})
export class FinalOrganizationNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalOrganizationNiazsanjis: IFinalOrganizationNiazsanjiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
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
    isAdmin:boolean = false;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done:boolean = false;
    criteria: any;

    selectedNiazSanji: number[] = [];
    selectedYear: number;
    yearsCollections: any[];
    years: any[];
    counter: number = 0;
    coursetypes: ICourseTypeMarineSuffix[];
    constructor(
        private finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
        private requestOrganizationNiazsanjiMarineSuffixService: RequestOrganizationNiazsanjiMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private treeUtilities: TreeUtilities,
        private courseTypeService: CourseTypeMarineSuffixService,
        private convertObjectDatesService : ConvertObjectDatesService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
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
                    this.finalOrganizationNiazsanjiService.find(a).subscribe((resp: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => {

                            let finalOrganizationNiazsanji = resp.body;
                            finalOrganizationNiazsanji.niazsanjiYear = this.selectedYear;
                            this.finalOrganizationNiazsanjiService.update(finalOrganizationNiazsanji).subscribe(
                                (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.completeSuccess(),
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
    createCriteria(criteria?): any{

        if(criteria)
        {
            const year = criteria.find(a => a.key == 'yearId.equals');
            if(year) {
                const val = +year.value;
                criteria = criteria.filter(a => a.key != 'yearId.equals');
                if (val) {
                    const yearDetail = this.yearsCollections.find(a => a.year == val);
                    const beginDate = new Date(yearDetail.beginDate).toISOString();
                    const endDate = new Date(yearDetail.endDate).toISOString();

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
        if(exportExcel)
        {
            this.finalOrganizationNiazsanjiService
                .query({
                    page: 0,
                    size: 200000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix[]>) =>
                        this.finalExportToExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.finalOrganizationNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix[]>) =>
                        this.paginateFinalOrganizationNiazsanjis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    export() {
        this.loadAll(this.criteria, true);
        /*let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.finalOrganizationNiazsanjis, 'finalOrganizationNiazsanjis', 'marineindustryprojApp.finalOrganizationNiazsanji');*/
    }
    finalExportToExcel(excelFinalOrganizationNiazsanjis: IFinalOrganizationNiazsanjiMarineSuffix[]){
        let a = new ExcelService(this.jhiTranslate);
        excelFinalOrganizationNiazsanjis = this.convertObjectDatesService.changeArrayDate(excelFinalOrganizationNiazsanjis);
        let report = [];
        let index: number = 0;
        excelFinalOrganizationNiazsanjis.forEach(a => {
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId).fullTitle;
            let personIds = a.people.map(a => a.id);
            let filteredPeople = this.people.filter(w => personIds.includes(w.id));
            let peopleNames: string[] = filteredPeople.map(a => a.fullName);
            index++;
            let educationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);
            let obj: Object;
            obj = {'index': index,
                'organizationChart': org,
                'personName': peopleNames.join('،'),
                /*'jobTitle': person.jobTitle,*/
                'educationalModule': a.educationalModuleTitle,
                'timeEducationalModule': (educationalModule.learningTimePractical ? educationalModule.learningTimePractical : 0) + (educationalModule.learningTimeTheorical ? educationalModule.learningTimeTheorical : 0),
                'levelEducationalModule': educationalModule.skillableLevelOfSkillTitle,
                'costEducationalModule': a.priceCost,
                'createDate': a.createDate,
                'niazsanjiYear': a.niazsanjiYear
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'finalOrganizationNiazsanjis', 'marineindustryprojApp.niazsanjiFardi');
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/final-organization-niazsanji-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        this.loadAll(this.criteria);
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
    clear() {
        this.page = 0;
        this.router.navigate([
            '/final-organization-niazsanji-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }
    complete(mymodel){

        this.finalOrganizationNiazsanjiService.find(mymodel.id).subscribe((resp) => {
            let model = resp.body;

            if (confirm("آیا از اجرا کردن نیازسنجی پودمان سازمانی (متمرکز و گروهی) مورد نظر مطمئنید.")) {

                this.finalOrganizationNiazsanjiService.finalize(model).subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSuccess("نیازسنجی پودمان سازمانی (متمرکز و گروهی) مورد نظر شما به مرحله اجرا وارد شد."),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        });
    }
    ngOnInit() {
        //this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }
            this.prepareSearchOrgChart();
            this.prepareSearchPerson();
            this.prepareSearchEducationalModule();
            this.prepareSearchDate();

            this.prepareSearchCourseType();


        });
        //this.registerChangeInFinalOrganizationNiazsanjis();
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
        this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleTitle', 'text', 'contains'));
        if(this.educationalModuleService.educationalModules){
            this.educationalModules = this.educationalModuleService.educationalModules;
            /*this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, "fullTitle",'half'));*/
            /*if(!this.done){
                this.loadAll();
            }*/
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', res.body, "fullTitle",'half'));*/
                    /*if(!this.done){
                        this.loadAll();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    prepareSearchPerson(){
        if(this.personService.people){
            this.people = this.personService.people;
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

        const dates = this.convertObjectDatesService.getYearsArray();
        const thisYear = this.convertObjectDatesService.getNowShamsiYear();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates, 'title','',thisYear+''));
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

    trackId(index: number, item: IFinalOrganizationNiazsanjiMarineSuffix) {
        return item.id;
    }

    registerChangeInFinalOrganizationNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('finalOrganizationNiazsanjiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateFinalOrganizationNiazsanjis(data: IFinalOrganizationNiazsanjiMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.finalOrganizationNiazsanjis = this.convertObjectDatesService.changeArrayDate(data,true);
        this.finalOrganizationNiazsanjis.forEach(a => {
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartRootTitle = org.fullTitle.split('>')[0];
            const education: IEducationalModuleMarineSuffix = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if(education){
                a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
                a.totalLearningTime = (education.learningTimePractical ? education.learningTimePractical : 0) + (education.learningTimeTheorical ? education.learningTimeTheorical : 0)
            }
        });
    }
    private onSuccess(errorMessage: string) {
        this.jhiAlertService.success(errorMessage, null, null);
        this.loadAll(this.criteria);
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
        this.loadAll(this.criteria);
    }
}
