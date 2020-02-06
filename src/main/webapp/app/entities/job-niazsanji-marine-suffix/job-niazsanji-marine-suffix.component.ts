import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {AccountService, Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {IFinalOrganizationNiazsanjiMarineSuffix} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import * as $ from 'jquery';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {EducationalModuleType} from "app/shared/model/enums/EducationalModuleType";
import {SkillableLevelOfSkillMarineSuffixService} from "app/entities/skillable-level-of-skill-marine-suffix";
import {ISkillableLevelOfSkillMarineSuffix} from "app/shared/model/skillable-level-of-skill-marine-suffix.model";
import {JobNiazsanjiMarineSuffixService} from "app/entities/job-niazsanji-marine-suffix/job-niazsanji-marine-suffix.service";
import {PreJobNiazsanjiMarineSuffixService} from "app/entities/pre-job-niazsanji-marine-suffix";
import {IJobNiazsanjiMarineSuffix} from "app/shared/model/job-niazsanji-marine-suffix.model";
import {IPreJobNiazsanjiMarineSuffix} from "app/shared/model/pre-job-niazsanji-marine-suffix.model";

@Component({
    selector: 'mi-job-niazsanji-marine-suffix',
    templateUrl: './job-niazsanji-marine-suffix.component.html'
})
export class JobNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    jobNiazsanjis: IJobNiazsanjiMarineSuffix[];
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
        protected jobNiazsanjiService: JobNiazsanjiMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
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
                    this.jobNiazsanjiService.find(a).subscribe((resp: HttpResponse<IJobNiazsanjiMarineSuffix>) => {
                            let niazSanjiFardi = resp.body;
                            niazSanjiFardi.niazsanjiYear = this.selectedYear;
                            this.jobNiazsanjiService.update(niazSanjiFardi).subscribe(
                                (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => this.completeSuccess(),
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
            this.jobNiazsanjiService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IJobNiazsanjiMarineSuffix[]>) => this.finalExportToExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.jobNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IJobNiazsanjiMarineSuffix[]>) => this.paginateJobNiazsanjis(res.body, res.headers),
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
        /*this.router.navigate(['/job-niazsanji-marine-suffix'], {
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
            '/job-niazsanji-marine-suffix',
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
    finalExportToExcel(excelNiazSanjiFardis: IJobNiazsanjiMarineSuffix[]){
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
        a.exportAsExcelFile(report, 'jobNiazsanjis', 'marineindustryprojApp.jobNiazsanji');
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
            this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'educationalModuleId', 'number', 'equals'));
            this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'educationalModuleTitle', 'text', 'contains'));
            let educationalModuleType = [{
                id: EducationalModuleType.ALL,
                title: 'کل پودمان'
            },{
                id: EducationalModuleType.APPROVED,
                title: 'نیازسنجی از شناسنامه شغلی'
            }];
            this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'educationalModuleType', 'select', 'equals', educationalModuleType));
            this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'priceCost', 'number', 'equals'));
            this.prepareSearchOrgChart();
            this.prepareSearchPerson();
            this.prepareSearchEducationalModule();
            this.prepareSearchDate();
            this.prepareSearchCourseType();
            this.prepareSkillLevelOfSkill();

        });
        //this.registerChangeInJobNiazsanjis();
    }
    prepareSkillLevelOfSkill(){
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {

                this.skillableLevelOfSkills = res.body;
                this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'skillableLevelOfSkillId', 'select', 'equals', this.skillableLevelOfSkills));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchCourseType(){
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {

                this.coursetypes = res.body;
                this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'courseTypeId', 'select', 'equals', this.coursetypes));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchEducationalModule(){

        if(this.educationalModuleService.educationalModules){
            this.educationalModules = this.educationalModuleService.educationalModules
            /*this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'educationalModuleId', 'select', 'equals', this.educationalModules, "fullTitle",'half'));*/
            /*if (!this.done) {
                this.loadAll();
            }*/
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    /*      this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'educationalModuleId', 'select', 'equals', res.body, "fullTitle",'half'));*/
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
            this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'personId', 'select', 'equals', this.people, "fullName"));
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    //this.people.forEach(a => a["title"] = a.fullName);
                    this.searchbarModel.push(new SearchPanelModel('jobNiazsanji', 'personId', 'select', 'equals', this.people, "fullName"));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    prepareSearchDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
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
    complete(mymodel: IJobNiazsanjiMarineSuffix) {
        if(mymodel.niazsanjiYear) {
            this.jobNiazsanjiService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;

                if (confirm("آیا از اجرا کردن این دوره شغلی مورد نظر مطمئنید.")) {

                    this.jobNiazsanjiService.finalize(model).subscribe(
                        (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSuccess("نیازسنجی پودمان سازمانی مورد نظر شما به مرحله اجرا وارد شد."),
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
                }
            });
        }
        else{
            alert("انتخاب کردن سال نیازسنجی اجباریست.");
        }
    }
    accept(jobNiazsanji: IJobNiazsanjiMarineSuffix){
        this.jobNiazsanjiService.find(jobNiazsanji.id).subscribe((resp) => {
            let model = resp.body;

            if (confirm("آیا از اجرا کردن این دوره شغلی مورد نظر مطمئنید.")) {

                this.jobNiazsanjiService.finalize(model).subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSuccess("نیازسنجی پودمان سازمانی (متمرکز و گروهی) مورد نظر شما به مرحله اجرا وارد شد."),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        });
    }
    reject(model: IJobNiazsanjiMarineSuffix){
        let message = prompt("لطفا علت عدم تایید را ثبت نمائید.");
        if(message)
        {

            let currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;
            this.jobNiazsanjiService.find(model.id).subscribe((resp: HttpResponse<IJobNiazsanjiMarineSuffix>) =>{

                let niazSanjiFardi = resp.body;
                this.preJobNiazsanjiService.find(niazSanjiFardi.preJobNiazsanjiId).subscribe((res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => {

                    let preJobNiazsanji = res.body;
                    preJobNiazsanji.status = 21;
                    preJobNiazsanji.conversation += "\n ------------------------------------- \n";
                    preJobNiazsanji.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                    preJobNiazsanji.requestStatus = RequestStatus.IGNORE;
                    preJobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                    preJobNiazsanji.conversation += "\n";
                    preJobNiazsanji.conversation += currentUserFullName + ": " + message;

                    this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                        (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => res,
                        (error: HttpErrorResponse) => this.onError(error.message));

                    niazSanjiFardi.status = 5;
                    niazSanjiFardi.changeStatusUserLogin = this.currentAccount.login;
                    niazSanjiFardi.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                    this.jobNiazsanjiService.update(niazSanjiFardi).subscribe(
                        (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => res,
                        (error: HttpErrorResponse) => this.onError(error.message));
                    //this.jobNiazsanjiService.update()

                });
            });

        }
        else{
            alert("علت عدم تایید اجباریست. ");
        }

    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInJobNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('jobNiazsanjiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateJobNiazsanjis(data: IJobNiazsanjiMarineSuffix[], headers: HttpHeaders) {

        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        debugger;
        this.jobNiazsanjis = this.convertObjectDatesService.changeArrayDate(data, true);
        this.jobNiazsanjis.forEach(a => {
            debugger;
            const org = this.organizationcharts.find(w => w.id == a.organizationChartId);
            if(org)
                a.organizationChartRootTitle = org.fullTitle.split('>')[0];
            const education: IEducationalModuleMarineSuffix = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if(education){
                a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
                a.totalLearningTime = (education.learningTimePractical ? education.learningTimePractical : 0) + (education.learningTimeTheorical ? education.learningTimeTheorical : 0)
            }
        });
        if(this.jobNiazsanjis) {
            debugger;
            const totalLearningTimes = this.jobNiazsanjis.filter(a => a.totalLearningTime).map(a => a.totalLearningTime);
            if(totalLearningTimes)
                this.totalHour = totalLearningTimes.reduce((sum, current) => sum + current);

            const priceCosts = this.jobNiazsanjis.filter(a => a.priceCost).map(a => a.priceCost);
            if(priceCosts)
                this.totalPriceCost = priceCosts.reduce((sum, current) => sum + current);
        }
    }

    private onSuccess(successMessage: string) {
        this.jhiAlertService.success(successMessage, null, null);
        this.loadAll(this.criteria);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
        this.loadAll(this.criteria);
    }
    toggleImportantMessage(id: number, type: boolean){

        this.jobNiazsanjiService.toggleImportantMessage(id, type).subscribe(
            (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => this.loadAll(this.criteria),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveError(){

    }
}
