import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService} from 'ng-jhipster';

import {
    IRequestOrganizationNiazsanjiMarineSuffix
} from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {RequestOrganizationNiazsanjiMarineSuffixService} from './request-organization-niazsanji-marine-suffix.service';
import {IPersonMarineSuffix, PersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import {PersonMarineSuffixService} from 'app/entities/person-marine-suffix';
import {IEducationalModuleMarineSuffix} from 'app/shared/model/educational-module-marine-suffix.model';
import {EducationalModuleMarineSuffixService} from 'app/entities/educational-module-marine-suffix';
import {ITeachApproachMarineSuffix} from 'app/shared/model/teach-approach-marine-suffix.model';
import {TeachApproachMarineSuffixService} from 'app/entities/teach-approach-marine-suffix';
import {Principal} from "app/core";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {ITeacherMarineSuffix} from "app/shared/model/teacher-marine-suffix.model";
import {TeacherMarineSuffixService} from "app/entities/teacher-marine-suffix/teacher-marine-suffix.service";
import {IJobMarineSuffix} from "app/shared/model/job-marine-suffix.model";
import {JobMarineSuffixService} from "app/entities/job-marine-suffix";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import * as $ from 'jquery';
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-update',
    templateUrl: './request-organization-niazsanji-marine-suffix-update.component.html'
})
export class RequestOrganizationNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    private _requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix;
    isSaving: boolean;
    message: string;

    currentPerson: IPersonMarineSuffix;
    coursetypes: ICourseTypeMarineSuffix[];
    people: IPersonMarineSuffix[];
    fullPeople: IPersonMarineSuffix[];
    jobs: IJobMarineSuffix[];
    fullJobs: IJobMarineSuffix[];
    selectedJob: IJobMarineSuffix;
    firstThreeJobCode: string;
    educationalmodules: IEducationalModuleMarineSuffix[];
    organizationCharts: IOrganizationChartMarineSuffix[];

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    targetPeople: IPersonMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgChartDisabled: boolean;

    teachers: ITeacherMarineSuffix[];
    selectionType: boolean = false;
    disable: boolean = false;
    currentAccount: any;
    constructor(
        private jhiAlertService: JhiAlertService,
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private teachApproachService: TeachApproachMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private principal : Principal,
        private teacherService: TeacherMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities
    ) {}
    cleanUp(){

        if(!this.selectionType){
            this.firstThreeJobCode = "";
            this.selectedJob = {};
            this.onChange(undefined);
        }
    }
    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ requestOrganizationNiazsanji }) => {
            this.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
            if(this.requestOrganizationNiazsanji.people)
                this.requestOrganizationNiazsanji.people.forEach(a => a.fullName = a.name + " " + a.family);
        });
        this.principal.identity().then(account => {

            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                 this.currentPerson = resp.body;
                 this.currentUserFullName = this.currentPerson.fullName;
                 this.loadOrgCharts();
            },
                (res: HttpErrorResponse) => this.onError(res.message));
        });


        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
                this.fullJobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.teacherService.teachers){
            this.teachers = this.teacherService.teachers;
        }
        else {
            this.teacherService.query().subscribe(
                (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                    this.teachers = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }
    setRoles(account: any){

        if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
            this.isModirAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
            this.isModirKolAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationCharts = this.organizationChartService.organizationchartsAll;
            this.handleOrgChartView();
            this.handlePeopleList();
            this.findTargetPeople();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationCharts = res.body;
                    this.handleOrgChartView();
                    this.handlePeopleList();
                    this.findTargetPeople();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    handleOrgChartView(){
        if(this.treeUtilities.hasChild(this.organizationCharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationCharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationCharts.filter(a => orgIds.includes(a.id));
            this.orgChartDisabled = false;
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId));
            this.orgChartDisabled = true;
        }
    }
    handlePeopleList(){
        const rootId = this.treeUtilities.getRootId(this.organizationCharts ,this.currentPerson.organizationChartId);
        const orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationCharts ,rootId).filter(this.treeUtilities.onlyUnique);

        let criteria = [{
            key: 'organizationChartId.in',
            value: orgIds
        }];

        this.personService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {

                this.people = res.body;
                this.fullPeople = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    addAllPeopleOfThisUser(){
        let orgIds = [];

        if(this.requestOrganizationNiazsanji.organizationChartId)
        {
            orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationCharts , this.requestOrganizationNiazsanji.organizationChartId).filter(this.treeUtilities.onlyUnique);
        }
        else
        {
            orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationCharts , this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
        }
        this.requestOrganizationNiazsanji.people = this.people.filter(a => orgIds.includes(a.organizationChartId)).filter(this.treeUtilities.onlyUnique);
    }
    addAllPeople(){
        if(this.personService.people){
            this.people = this.personService.people;
            //this.requestOrganizationNiazsanji.people = this.people;
        }
        else{
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    //this.requestOrganizationNiazsanji.people = this.people;
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    findTargetPeople(){
        let organization = this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId);
        if(organization.parentId > 0) {
            let neededOrgId: number = organization.parentId;

            let criteria = [{
                key: 'organizationChartId.equals',
                value: neededOrgId
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    let orgPeople = resp.body;
                    if (orgPeople.length > 0) {
                        this.targetPeople = orgPeople;
                    }
                    else {
                        this.targetPeople = [];
                        this.targetPeople = [new PersonMarineSuffix(0, 'خطا', 'خطا', 'خطا: نفر دریافت کننده ای در چارت سازمانی برای شما تعریف نشده است. لطفا با مدیریت سامانه تماس بگیرید. ')]
                    }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
        else{
            this.targetPeople = [];
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست شما پس از ثبت تایید نهایی می شود و برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
        }
    }
    showRelatedJobs(input){
        this.disable = true;
        let jobCode = input.firstThreeJobCode;
        if(!isNaN(jobCode)) {
            if (jobCode) {
                if (jobCode.length === 3) {
                    this.jobs = this.fullJobs.filter(entity => entity.jobCode.startsWith(jobCode));

                    $("#jobHighlight").trigger('click');
                }
            }
            else {
                this.jobs = this.fullJobs;
            }
        }
        else{
            input.firstThreeJobCode = "";
        }
    }
    onChange(event){

        if(event === undefined) {
            this.people = this.fullPeople;
            $("#personHighlight").trigger('click');
        }
        else {
            this.people = this.fullPeople.filter(a => a.jobId == event.id);
            $("#personHighlight").trigger('click');
        }
    }
    previousState() {
        window.history.back();
    }

    change(i){
        this.router.navigateByUrl(i);
    }
    currentUserFullName: string = "";

    save() {

        this.isSaving = true;
        this.message = "";

        if (this.requestOrganizationNiazsanji.organizationChartId == undefined) {
            this.message = "لطفا قسمت پیشنهاد دهنده را انتخاب نمائید.";
            this.isSaving = false;
            return;
        }
        if (!this.requestOrganizationNiazsanji.people) {
            this.message = "لطفا فرد مورد نظر را انتخاب نمائید.";
            this.isSaving = false;
            return;
        }
        if(this.requestOrganizationNiazsanji.priceCost && isNaN(this.requestOrganizationNiazsanji.priceCost))
        {
            this.message = "لطفا در باکس سرمایه گذاری فقط عدد وارد نمائید.";
            this.isSaving = false;
            return;
        }
        if(this.requestOrganizationNiazsanji.organizationChartId)
        {
            this.requestOrganizationNiazsanji.recommendedByOrgchart = this.organizationCharts.find(a => a.id == this.requestOrganizationNiazsanji.organizationChartId).fullTitle;
        }
        this.currentUserFullName = this.currentPerson.fullName;


        if (this.requestOrganizationNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.requestOrganizationNiazsanjiService.update(this.requestOrganizationNiazsanji),true);

        } else {
            let org = this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId);
            if(org.parentId > 0)
                this.requestOrganizationNiazsanji.status = org.parentId;
            else
                this.requestOrganizationNiazsanji.status = 0;
            this.requestOrganizationNiazsanji.requestStatus = RequestStatus.NEW;
            this.requestOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
            this.requestOrganizationNiazsanji.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
            if (this.requestOrganizationNiazsanji.description) {
                this.requestOrganizationNiazsanji.conversation += "\n";
                this.requestOrganizationNiazsanji.conversation += this.currentUserFullName + ": " + this.requestOrganizationNiazsanji.description;
            }
            this.subscribeToSaveResponse(this.requestOrganizationNiazsanjiService.create(this.requestOrganizationNiazsanji));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>>, isEdit:boolean = false) {
        result.subscribe(
            (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(res.body, isEdit),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess(res: IRequestOrganizationNiazsanjiMarineSuffix, isEdit:boolean = false) {

        if(!isEdit) {
            if(res.status == 0){
                res.conversation += "\n ------------------------------------- \n";
                res.conversation += " تایید درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.requestOrganizationNiazsanjiService.finalize(res).subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => res,
                    (res: HttpErrorResponse) => res
                );
            }
        }
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {

        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackTeachApproachById(index: number, item: ITeachApproachMarineSuffix) {
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
    get requestOrganizationNiazsanji() {
        return this._requestOrganizationNiazsanji;
    }

    set requestOrganizationNiazsanji(requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix) {
        this._requestOrganizationNiazsanji = requestOrganizationNiazsanji;
    }
}
