import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {IRequestEducationalModuleMarineSuffix} from 'app/shared/model/request-educational-module-marine-suffix.model';
import {RequestEducationalModuleMarineSuffixService} from './request-educational-module-marine-suffix.service';
import {IScientificWorkGroupMarineSuffix} from 'app/shared/model/scientific-work-group-marine-suffix.model';
import {ScientificWorkGroupMarineSuffixService} from 'app/entities/scientific-work-group-marine-suffix';
import {IDocumentMarineSuffix} from 'app/shared/model/document-marine-suffix.model';
import {DocumentMarineSuffixService} from 'app/entities/document-marine-suffix';
import {IEducationalCenterMarineSuffix} from 'app/shared/model/educational-center-marine-suffix.model';
import {EducationalCenterMarineSuffixService} from 'app/entities/educational-center-marine-suffix';
import {IGoalMarineSuffix} from 'app/shared/model/goal-marine-suffix.model';
import {GoalMarineSuffixService} from 'app/entities/goal-marine-suffix';
import {IResourceMarineSuffix} from 'app/shared/model/resource-marine-suffix.model';
import {ResourceMarineSuffixService} from 'app/entities/resource-marine-suffix';
import {ITeacherMarineSuffix} from 'app/shared/model/teacher-marine-suffix.model';
import {TeacherMarineSuffixService} from 'app/entities/teacher-marine-suffix';
import {ISecurityLevelMarineSuffix} from 'app/shared/model/security-level-marine-suffix.model';
import {SecurityLevelMarineSuffixService} from 'app/entities/security-level-marine-suffix';
import {ISkillableLevelOfSkillMarineSuffix} from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import {SkillableLevelOfSkillMarineSuffixService} from 'app/entities/skillable-level-of-skill-marine-suffix';
import {IEvaluationMethodMarineSuffix} from 'app/shared/model/evaluation-method-marine-suffix.model';
import {EvaluationMethodMarineSuffixService} from 'app/entities/evaluation-method-marine-suffix';
import {IOrganizationMarineSuffix} from 'app/shared/model/organization-marine-suffix.model';
import {OrganizationMarineSuffixService} from 'app/entities/organization-marine-suffix';
import {Principal} from "app/core";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";

@Component({
    selector: 'mi-request-educational-module-marine-suffix-update',
    templateUrl: './request-educational-module-marine-suffix-update.component.html'
})
export class RequestEducationalModuleMarineSuffixUpdateComponent implements OnInit {
    private _requestEducationalModule: IRequestEducationalModuleMarineSuffix;
    isSaving: boolean;
    organizationCharts: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];
    mustSendOrgChartId: number;
    currentAccount: any;
    currentUserFullName: string;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    scientificworkgroups: IScientificWorkGroupMarineSuffix[];

    documents: IDocumentMarineSuffix[];

    educationalcenters: IEducationalCenterMarineSuffix[];

    goals: IGoalMarineSuffix[];

    resources: IResourceMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    securitylevels: ISecurityLevelMarineSuffix[];

    skillablelevelofskills: ISkillableLevelOfSkillMarineSuffix[];

    evaluationmethods: IEvaluationMethodMarineSuffix[];

    organizations: IOrganizationMarineSuffix[];

    message: string;
    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private educationalCenterService: EducationalCenterMarineSuffixService,
        private goalService: GoalMarineSuffixService,
        private resourceService: ResourceMarineSuffixService,
        private teacherService: TeacherMarineSuffixService,
        private securityLevelService: SecurityLevelMarineSuffixService,
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        private evaluationMethodService: EvaluationMethodMarineSuffixService,
        private organizationService: OrganizationMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private principal : Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService
    ) {}
    private setRoles(account: any){
        if(account) {
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
                this.isModirAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
                this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
                this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }
    ngOnInit() {
        this.isSaving = false;
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
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            this.requestEducationalModule = requestEducationalModule;
            if(this.requestEducationalModule.teachers)
                this.requestEducationalModule.teachers.forEach(a => a.fullName = a.name + " " + a.family);
        });
        this.scientificWorkGroupService.query().subscribe(
            (res: HttpResponse<IScientificWorkGroupMarineSuffix[]>) => {
                this.scientificworkgroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalCenterService.query().subscribe(
            (res: HttpResponse<IEducationalCenterMarineSuffix[]>) => {
                this.educationalcenters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.goalService.query().subscribe(
            (res: HttpResponse<IGoalMarineSuffix[]>) => {
                this.goals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resourceService.query().subscribe(
            (res: HttpResponse<IResourceMarineSuffix[]>) => {
                this.resources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teacherService.query().subscribe(
            (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                this.teachers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.securityLevelService.query().subscribe(
            (res: HttpResponse<ISecurityLevelMarineSuffix[]>) => {
                this.securitylevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                this.skillablelevelofskills = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.evaluationMethodService.query().subscribe(
            (res: HttpResponse<IEvaluationMethodMarineSuffix[]>) => {
                this.evaluationmethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.organizationService.query().subscribe(
            (res: HttpResponse<IOrganizationMarineSuffix[]>) => {
                this.organizations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationCharts = this.organizationChartService.organizationchartsAll;
            this.findTargetPeople();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationCharts = res.body;
                    this.findTargetPeople();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    findTargetPeople(){
        let organization = this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId);
        if(organization.parentId > 0) {

            this.mustSendOrgChartId = this.treeUtilities.getRootId(this.organizationCharts ,this.currentPerson.organizationChartId); //organization.parentId;

            //this.mustSendChartId = neededOrgId;
            let criteria = [{
                key: 'organizationChartId.equals',
                value: this.mustSendOrgChartId
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
            this.mustSendOrgChartId = 0;
            this.targetPeople = [];
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'پرونده/سابقه آموزشی شما شما پس از ثبت برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
        }
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        debugger;
        this.currentUserFullName = this.currentPerson.fullName;
        this.requestEducationalModule.code = this.requestEducationalModule.code ? this.requestEducationalModule.code : 0;
        this.requestEducationalModule.title = this.requestEducationalModule.title ? this.requestEducationalModule.title : "";
        this.requestEducationalModule.learningTimePractical = this.requestEducationalModule.learningTimePractical ? this.requestEducationalModule.learningTimePractical : 0;
        this.requestEducationalModule.learningTimeTheorical = this.requestEducationalModule.learningTimeTheorical ? this.requestEducationalModule.learningTimeTheorical : 0;
        this.message = "";
        debugger;
        if(!this.currentPerson.organizationChartId) {
            this.message = "موقعیت در چارت سازمانی برای شما تنظیم نشده است، لطفا مراتب را با مدیریت سامانه در میان بگذارید."
            this.isSaving = false;
            return;
        }
        if (this.requestEducationalModule.id !== undefined) {
            this.subscribeToSaveResponse(this.requestEducationalModuleService.update(this.requestEducationalModule));
        } else {
            this.requestEducationalModule.status = this.mustSendOrgChartId;
            this.requestEducationalModule.requestStatus = RequestStatus.NEW;
            this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;
            this.requestEducationalModule.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
            /*if (this.requestEducationalModule.description) {
                this.requestEducationalModule.conversation += "\n";
                this.requestEducationalModule.conversation += this.currentUserFullName + ": " + this.requestEducationalModule.description;
            }*/
            this.subscribeToSaveResponse(this.requestEducationalModuleService.create(this.requestEducationalModule));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestEducationalModuleMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackScientificWorkGroupById(index: number, item: IScientificWorkGroupMarineSuffix) {
        return item.id;
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterById(index: number, item: IEducationalCenterMarineSuffix) {
        return item.id;
    }

    trackGoalById(index: number, item: IGoalMarineSuffix) {
        return item.id;
    }

    trackResourceById(index: number, item: IResourceMarineSuffix) {
        return item.id;
    }

    trackTeacherById(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    trackSecurityLevelById(index: number, item: ISecurityLevelMarineSuffix) {
        return item.id;
    }

    trackSkillableLevelOfSkillById(index: number, item: ISkillableLevelOfSkillMarineSuffix) {
        return item.id;
    }

    trackEvaluationMethodById(index: number, item: IEvaluationMethodMarineSuffix) {
        return item.id;
    }

    trackOrganizationById(index: number, item: IOrganizationMarineSuffix) {
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
    get requestEducationalModule() {
        return this._requestEducationalModule;
    }

    set requestEducationalModule(requestEducationalModule: IRequestEducationalModuleMarineSuffix) {
        this._requestEducationalModule = requestEducationalModule;
    }
}
