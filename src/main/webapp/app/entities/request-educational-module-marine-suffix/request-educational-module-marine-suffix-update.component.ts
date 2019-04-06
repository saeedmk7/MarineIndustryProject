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

@Component({
    selector: 'mi-request-educational-module-marine-suffix-update',
    templateUrl: './request-educational-module-marine-suffix-update.component.html'
})
export class RequestEducationalModuleMarineSuffixUpdateComponent implements OnInit {
    private _requestEducationalModule: IRequestEducationalModuleMarineSuffix;
    isSaving: boolean;

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
    timePassed: string;
    credit: string;
    createDate: string;
    modifyDate: string;
    archivedDate: string;
    answer: string;
    currentAccount: any;
    currentUserFullName: string;
    isAdmin:boolean;
    currentRequestStatus: RequestStatus;
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
        private principal : Principal
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            this.currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {

        this.isSaving = true;
        this.requestEducationalModule.status = 0;
        this.requestEducationalModule.code = 0;
        this.requestEducationalModule.timePassed = moment("", DATE_TIME_FORMAT);
        this.requestEducationalModule.credit = moment("", DATE_TIME_FORMAT);

        if(!this.currentUserFullName)
            this.currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;

        if (this.requestEducationalModule.id !== undefined) {
            if(this.requestEducationalModule.requestStatus != this.currentRequestStatus)
                this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;


            if(this.requestEducationalModule.conversation)
                this.requestEducationalModule.conversation += "\r\n";
            else
                this.requestEducationalModule.conversation = " ";

            if(this.answer)
                this.requestEducationalModule.conversation += this.currentUserFullName + ": " + this.answer;
            //this.requestEducationalModule.conversation += "\r\n" + this.currentUserFullName + ": " + this.answer;
            this.subscribeToSaveResponse(this.requestEducationalModuleService.update(this.requestEducationalModule));
        } else {
            this.requestEducationalModule.requestStatus = RequestStatus.NEW;
            this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;
            //this.requestEducationalModule.conversation = this.currentUserFullName + ": " + this.requestEducationalModule.description;
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
