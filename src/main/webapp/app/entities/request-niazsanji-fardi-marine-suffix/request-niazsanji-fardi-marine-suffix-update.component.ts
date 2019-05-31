import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {
    IRequestNiazsanjiFardiMarineSuffix,
    RequestNiazsanjiFardiMarineSuffix
} from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import {RequestNiazsanjiFardiMarineSuffixService} from './request-niazsanji-fardi-marine-suffix.service';
import {IDocumentMarineSuffix} from 'app/shared/model/document-marine-suffix.model';
import {DocumentMarineSuffixService} from 'app/entities/document-marine-suffix';
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from 'app/shared/model/educational-module-marine-suffix.model';
import {EducationalModuleMarineSuffixService} from 'app/entities/educational-module-marine-suffix';
import {IPersonMarineSuffix, PersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import {PersonMarineSuffixService} from 'app/entities/person-marine-suffix';
import {IOrganizationChartMarineSuffix} from 'app/shared/model/organization-chart-marine-suffix.model';
import {OrganizationChartMarineSuffixService} from 'app/entities/organization-chart-marine-suffix';
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {EducationalModuleJobMarineSuffixService} from "app/entities/educational-module-job-marine-suffix";
import {IEducationalModuleJobMarineSuffix} from "app/shared/model/educational-module-job-marine-suffix.model";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IUser, Principal, UserService} from "app/core";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IFinalOrganizationNiazsanjiMarineSuffix} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix-update',
    templateUrl: './request-niazsanji-fardi-marine-suffix-update.component.html',
    styleUrls:['./request-niazsanji-fardi-marine-suffix.scss']
})
export class RequestNiazsanjiFardiMarineSuffixUpdateComponent implements OnInit {
    requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix;
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgChartDisabled: boolean;
    isSaving: boolean;
    coursetypes: ICourseTypeMarineSuffix[];
    documents: IDocumentMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];
    approvedEducationalmodules: IEducationalModuleMarineSuffix[];
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
    people: IPersonMarineSuffix[];
    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    currentAccount: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    predicate: any;
    reverse: any;

    organizationcharts: IOrganizationChartMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;
    answer:string;

    approvedHour: number = 0;
    approvedLevel: string;

    allHour: number = 0;
    allLevel: string;

    message: string;
    targetPeople: IPersonMarineSuffix[];

    hasNoRow:boolean = false;
    currentUserFullName: string = "";
    constructor(
        protected dataUtils: JhiDataUtils,
        private courseTypeService: CourseTypeMarineSuffixService,
        protected jhiAlertService: JhiAlertService,
        protected educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        protected finalNiazsanjiReportPersonMarineSuffixService: FinalNiazsanjiReportPersonMarineSuffixService,
        protected finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        private principal : Principal,
        protected activatedRoute: ActivatedRoute,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {}
    ngOnInit() {

        this.isSaving = false;
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.activatedRoute.data.subscribe(({ requestNiazsanjiFardi }) => {
            this.requestNiazsanjiFardi = requestNiazsanjiFardi;

            if(this.requestNiazsanjiFardi.id == null) {
                this.requestNiazsanjiFardi.costApprovedEducationalModule = 0;
                this.requestNiazsanjiFardi.costAllEducationalModule = 0;
            }
            else{
                if(this.requestNiazsanjiFardi.allEducationalModuleId)
                {
                    this.requestNiazsanjiFardi.allCourseTypeId = this.requestNiazsanjiFardi.courseTypeId;
                }
                if(this.requestNiazsanjiFardi.approvedEducationalModuleId) {
                }
                    this.requestNiazsanjiFardi.approvedCourseTypeId = this.requestNiazsanjiFardi.courseTypeId;
            }
        });

        if(this.organizationChartService.organizationchartsAll){
            this.organizationcharts = this.convertObjectDatesService.goClone(this.organizationChartService.organizationchartsAll);

            if(this.requestNiazsanjiFardi.organizationChartId){
                let org = this.organizationcharts.find(a => a.id == this.requestNiazsanjiFardi.organizationChartId);
                this.onOrganizationChartChange(org);
            }

            this.setPermission();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    if (this.requestNiazsanjiFardi.organizationChartId) {
                        let org = this.organizationcharts.find(a => a.id == this.requestNiazsanjiFardi.organizationChartId);
                        this.onOrganizationChartChange(org);
                    }

                    this.setPermission();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    setPermission(){
        this.principal.identity().then(account => {

            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {

                this.currentPerson = resp.body;
                if(!this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
                    this.requestNiazsanjiFardi.organizationChartId = resp.body.organizationChartId;
                    this.requestNiazsanjiFardi.personId = resp.body.id;
                    this.people = [];
                    this.people.push(resp.body);

                    this.findTargetPeople(this.currentPerson);
                    this.handleOrgChartView();
                    this.onPersonChange(resp.body);
                    //this.findTargetPeople(resp.body);
                }
                else{
                    this.findTargetPeople(this.currentPerson);
                    this.handleOrgChartView();

                }


                /*    this.findTargetPeople(resp.body);
                else
                    this.handleOrgChartView();*/



            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    preparePeople() {
        if(this.isSuperUsers) {
            if (this.personService.people) {
                this.allPeople = this.personService.people;
            }
            else {
                this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.allPeople = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
            }
        }
        if(this.isModirAmozesh){
            let criteria = [{
                key:'organizationChartId.in',
                value: this.recommenedOrgCharts.map(a => a.id)
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                this.allPeople = resp.body;
            },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
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
    onPersonChange(event){

        if(event.id){
            let criteria = [{
                key:'personId.equals',
                value: event.id
            }];
            this.finalNiazsanjiReportPersonMarineSuffixService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => this.showEducations(resp.body),
                (error) => this.onError("موردی یافت نشد"));


            criteria = [{
                key:'jobId.equals',
                value: event.jobId
            }];
            this.educationalModuleJobService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp: HttpResponse<IEducationalModuleJobMarineSuffix[]>) => this.showEducationalModules(resp.body),
                (error) => this.onError("موردی یافت نشد"));
        }
    }
    onAllPersonChange(event: IPersonMarineSuffix){
        if(event.id){
            this.personService.find(event.id).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.people = [];
                this.people.push(resp.body);
                this.requestNiazsanjiFardi.personId = event.id;

                if(this.recommenedOrgCharts.find(a => a.id == resp.body.organizationChartId) == null) {
                    this.recommenedOrgCharts = [];
                    this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == resp.body.organizationChartId));
                }
                this.requestNiazsanjiFardi.organizationChartId = resp.body.organizationChartId;
                this.onPersonChange(resp.body);
            },
                (error) => this.onError("موردی یافت نشد"));
        }
    }
    onApprovedChange(event){
        this.approvedHour = (event.learningTimePractical ? event.learningTimePractical : 0) + (event.learningTimeTheorical ? event.learningTimeTheorical : 0);
        this.approvedLevel = event.skillableLevelOfSkillTitle;
    }
    onAllChange(event){
        this.allHour = (event.learningTimePractical ? event.learningTimePractical : 0) + (event.learningTimeTheorical ? event.learningTimeTheorical : 0);
        this.allLevel = event.skillableLevelOfSkillTitle;
    }

    findTargetPeople(person: IPersonMarineSuffix){
        let organization = this.organizationcharts.find(a => a.id == person.organizationChartId);
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

    handleOrgChartView(){
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
            this.orgChartDisabled = false;
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
            this.orgChartDisabled = true;
        }
        this.preparePeople();
    }
    onOrganizationChartChange(event){

        if(event.id){
            let criteria = [{
                key:'organizationChartId.equals',
                value: event.id
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp) => {
                this.people = resp.body;
                if(this.requestNiazsanjiFardi.personId){
                    this.onPersonChange(this.people.find(a => a.id == this.requestNiazsanjiFardi.personId));
                }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }

    showEducationalModules(resp: IEducationalModuleJobMarineSuffix[]){

        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
            this.loadApproved(resp);
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                    this.loadApproved(resp);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

    }

    loadApproved(resp: IEducationalModuleJobMarineSuffix[]){
        if(resp.length){

            let ids: number[] = resp.map(a => a.educationalModuleId);
            ids = ids.filter(this.treeUtilities.onlyUnique);
            this.approvedEducationalmodules = this.educationalmodules.filter(a => ids.includes(a.id));

            if(this.requestNiazsanjiFardi.approvedEducationalModuleId)
            {
                let approve = this.approvedEducationalmodules.find(a => a.id == this.requestNiazsanjiFardi.approvedEducationalModuleId);
                if(approve){
                    this.onApprovedChange(approve);
                }
            }
            if(this.requestNiazsanjiFardi.allEducationalModuleId)
            {
                let all = this.educationalmodules.find(a => a.id == this.requestNiazsanjiFardi.allEducationalModuleId);
                if(all){
                    this.onAllChange(all);
                }
            }
        }
        else{
            this.approvedEducationalmodules = [];
            this.approvedEducationalmodules.push(new EducationalModuleMarineSuffix(0,'0',"","پودمانی در شناسنامه شغلی شما یافت نشد."));
        }
    }
    showEducations(resp: IFinalNiazsanjiReportPersonMarineSuffix[]){

        if(resp.length > 0) {
            this.hasNoRow = false;
            let ids: number[] = [];
            resp.forEach(a => {
                ids.push(a.finalNiazsanjiReportId);
            });
            let criteria = [{
                key: 'id.in',
                value: ids
            }];
            this.finalNiazsanjiReportMarineSuffixService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {

                    this.finalNiazsanjiReports = resp.body
                },
                (error) => this.onError("موردی یافت نشد"));
        }
        else {
            //this.finalNiazsanjiReports.push("این فرد تا به الان هیچ دوره ای شرکت نکرده است.");
            this.finalNiazsanjiReports = [];
            this.hasNoRow = true;
        }
    }

    previousState() {
        window.history.back();

    }

    save() {

        this.isSaving = true;
        this.message = "";


        if (this.requestNiazsanjiFardi.allEducationalModuleId == undefined && this.requestNiazsanjiFardi.approvedEducationalModuleId == undefined) {
            this.message = "حداقل یکی از دو پودمان باید انتخاب شود.";
            this.isSaving = false;
            return;
        }
        if (this.requestNiazsanjiFardi.allEducationalModuleId == this.requestNiazsanjiFardi.approvedEducationalModuleId) {
            this.message = "پودمان های انتخابی هر دو یک پودمان هستند لطفا یکی از آنها را انتخاب نمائید.";
            this.isSaving = false;
            return;
        }

        if (this.requestNiazsanjiFardi.organizationChartId == undefined) {
            this.message = "لطفا قسمت پیشنهاد دهنده را انتخاب نمائید.";
            this.isSaving = false;
            return;
        }
        if (this.requestNiazsanjiFardi.personId == undefined) {
            this.message = "لطفا فرد مورد نظر را انتخاب نمائید.";
            this.isSaving = false;
            return;
        }




        if(this.requestNiazsanjiFardi.allEducationalModuleId == undefined)
            this.requestNiazsanjiFardi.costAllEducationalModule = 0;
        else {
            if(isNaN(this.requestNiazsanjiFardi.costAllEducationalModule))
            {
                this.message = "لطفا در باکس هزینه کلیه پودمان فقط عدد وارد نمائید.";
                this.isSaving = false;
                return;
            }
        }
        if(this.requestNiazsanjiFardi.approvedEducationalModuleId == undefined)
            this.requestNiazsanjiFardi.costApprovedEducationalModule = 0;
        else {
            if(isNaN(this.requestNiazsanjiFardi.costApprovedEducationalModule))
            {
                this.message = "لطفا در باکس هزینه از شناسنامه آموزشی فقط عدد وارد نمائید.";
                this.isSaving = false;
                return;
            }
        }

        if(this.requestNiazsanjiFardi.approvedEducationalModuleId)
        {
            if(!this.requestNiazsanjiFardi.approvedCourseTypeId)
            {
                this.message = "لطفا نوع دوره از شناسنامه آموزشی را انتخاب نمائید.";
                this.isSaving = false;
                return;
            }
        }

        if(this.requestNiazsanjiFardi.allEducationalModuleId)
        {
            if(!this.requestNiazsanjiFardi.allCourseTypeId)
            {
                this.message = "لطفا نوع دوره از کلیه پودمان ها را انتخاب نمائید.";
                this.isSaving = false;
                return;
            }
        }

        this.currentUserFullName = this.currentPerson.fullName;

        if (this.requestNiazsanjiFardi.id !== undefined) {
            if(this.requestNiazsanjiFardi.approvedCourseTypeId)
                this.requestNiazsanjiFardi.courseTypeId = this.requestNiazsanjiFardi.approvedCourseTypeId;
            if(this.requestNiazsanjiFardi.allCourseTypeId)
                this.requestNiazsanjiFardi.courseTypeId = this.requestNiazsanjiFardi.allCourseTypeId;
            this.subscribeToSaveResponse(this.requestNiazsanjiFardiService.update(this.requestNiazsanjiFardi),true);
        } else {
            if(this.currentPerson.organizationChartId) {

                let org = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                if(org.parentId > 0)
                    this.requestNiazsanjiFardi.status = org.parentId;
                else
                    this.requestNiazsanjiFardi.status = 0;
                this.requestNiazsanjiFardi.requestStatus = RequestStatus.NEW;
                this.requestNiazsanjiFardi.changeStatusUserLogin = this.currentAccount.login;
                this.requestNiazsanjiFardi.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
                if (this.requestNiazsanjiFardi.description) {
                    this.requestNiazsanjiFardi.conversation += "\n";
                    this.requestNiazsanjiFardi.conversation += this.currentUserFullName + ": " + this.requestNiazsanjiFardi.description;
                }
                if (this.requestNiazsanjiFardi.approvedEducationalModuleId && this.requestNiazsanjiFardi.allEducationalModuleId) {
                    //let approvedEducationalModule : IRequestNiazsanjiFardiMarineSuffix = new RequestNiazsanjiFardiMarineSuffix();
                    let approvedEducationalModule = this.convertObjectDatesService.goClone(this.requestNiazsanjiFardi);
                    //let allEducationalModule : IRequestNiazsanjiFardiMarineSuffix = new RequestNiazsanjiFardiMarineSuffix();
                    let allEducationalModule = this.convertObjectDatesService.goClone(this.requestNiazsanjiFardi);

                    approvedEducationalModule.allEducationalModuleId = null;
                    approvedEducationalModule.costAllEducationalModule = 0;
                    approvedEducationalModule.courseTypeId = this.requestNiazsanjiFardi.approvedCourseTypeId;
                    this.subscribeToSaveResponse(this.requestNiazsanjiFardiService.create(approvedEducationalModule));

                    allEducationalModule.approvedEducationalModuleId = null;
                    allEducationalModule.costApprovedEducationalModule = 0;
                    allEducationalModule.courseTypeId = this.requestNiazsanjiFardi.allCourseTypeId;
                    this.subscribeToSaveResponse(this.requestNiazsanjiFardiService.create(allEducationalModule));
                }
                else {
                    this.subscribeToSaveResponse(this.requestNiazsanjiFardiService.create(this.requestNiazsanjiFardi));
                }
            }
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestNiazsanjiFardiMarineSuffix>>, isEdit:boolean = false) {
        result.subscribe(
            (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(res.body, isEdit),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess(res: IRequestNiazsanjiFardiMarineSuffix, isEdit:boolean = false) {

        if(!isEdit) {
            if(res.status == 0){
                res.conversation += "\n ------------------------------------- \n";
                res.conversation += " تایید درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.requestNiazsanjiFardiService.finalize(res).subscribe(
                    (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => res,
                    (res: HttpErrorResponse) => res
                );
            }
        }
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
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
}
