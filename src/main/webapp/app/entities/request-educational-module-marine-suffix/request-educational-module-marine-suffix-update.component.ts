import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
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
import {IRestrictionMarineSuffix} from "app/shared/model/restriction-marine-suffix.model";
import {RestrictionMarineSuffixService} from "app/entities/restriction-marine-suffix";
import {IPeopleUnderTrainingMarineSuffix} from "app/shared/model/people-under-training-marine-suffix.model";
import {PeopleUnderTrainingMarineSuffixService} from "app/entities/people-under-training-marine-suffix";
import {ICompetencyMarineSuffix} from "app/shared/model/competency-marine-suffix.model";
import {CompetencyMarineSuffixService} from "app/entities/competency-marine-suffix";
import {HeadlineLevel} from "app/shared/model/enums/HeadlineLevel";
import {HeadlineScope} from "app/shared/model/enums/HeadlineScope";
import {HeadlineMarineSuffixService} from "app/entities/headline-marine-suffix";
import {HeadlineMarineSuffix, IHeadlineMarineSuffix} from "app/shared/model/headline-marine-suffix.model";
import {
    ITeachApproachMarineSuffix,
    TeachApproachMarineSuffix
} from "app/shared/model/teach-approach-marine-suffix.model";
import {TeachApproachMarineSuffixService} from "app/entities/teach-approach-marine-suffix";
import {IEffectivenessLevelMarineSuffix} from "app/shared/model/effectiveness-level-marine-suffix.model";
import {EffectivenessLevelMarineSuffixService} from "app/entities/effectiveness-level-marine-suffix";
import {IEffectivenessIndexMarineSuffix} from "app/shared/model/effectiveness-index-marine-suffix.model";
import {EffectivenessIndexMarineSuffixService} from "app/entities/effectiveness-index-marine-suffix";
import * as moment from 'moment';
import * as persianMoment from 'jalali-moment';
import {TeachingRecordMarineSuffixService} from "app/entities/teaching-record-marine-suffix";
import {TeachingApproachMarineSuffix} from "app/shared/model/teaching-approach-marine-suffix.model";
import {TeachingApproachMarineSuffixService} from "app/entities/teaching-approach-marine-suffix";
import * as $ from 'jquery';
import {IAssessmentMethodMarineSuffix} from "app/shared/model/assessment-method-marine-suffix.model";
import {AssessmentMethodMarineSuffixService} from "app/entities/assessment-method-marine-suffix";

@Component({
    selector: 'mi-request-educational-module-marine-suffix-update',
    templateUrl: './request-educational-module-marine-suffix-update.component.html',
    styleUrls: ['./request-educational-module-marine-suffix.scss']
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
    restrictions: IRestrictionMarineSuffix[];

    goals: IGoalMarineSuffix[];

    resources: IResourceMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    securitylevels: ISecurityLevelMarineSuffix[];

    skillablelevelofskills: ISkillableLevelOfSkillMarineSuffix[];

    peopleUnderTrainings: IPeopleUnderTrainingMarineSuffix[];

    teachingApproachs: ITeachApproachMarineSuffix[];

    effectivenessLevels: IEffectivenessLevelMarineSuffix[];

    assessmentMethods: IAssessmentMethodMarineSuffix[];

    effectivenessIndeces: IEffectivenessIndexMarineSuffix[];

    competencies: ICompetencyMarineSuffix[];

    headlines: IHeadlineMarineSuffix[] = [];

    evaluationmethods: IEvaluationMethodMarineSuffix[];

    organizations: IOrganizationMarineSuffix[];

    message: string;
    peopleUnderTrainingDescs: string = "";

    headlineLevelKeys: string[] = [];
    headlineScopeKeys: string[] = [];
    headlineRows: number = 0;

    dateRecommendDateValid: number;
    dateTimePassedValid: number;
    dateCreditValid: number;
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
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        private router: Router,
        private peopleUnderTrainingService: PeopleUnderTrainingMarineSuffixService,
        private competencyService: CompetencyMarineSuffixService,
        private headlineService: HeadlineMarineSuffixService,
        private teachingApproachService: TeachingApproachMarineSuffixService,
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
        private assessmentMethodService: AssessmentMethodMarineSuffixService,
        private effectivenessIndexService: EffectivenessIndexMarineSuffixService
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
    change(i){
        this.router.navigateByUrl(i);
    }
    ngOnInit() {
        this.isSaving = false;
        this.headlineLevelKeys = Object.keys(HeadlineLevel);
        this.headlineScopeKeys = Object.keys(HeadlineScope);
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

            if(this.requestEducationalModule.id === undefined)
            {
                this.requestEducationalModule.headlines = [];
                this.addNewHeadline();
            }
            else{
                this.headlineRows = this.requestEducationalModule.headlines.length;


                if (this.requestEducationalModule.credit && this.requestEducationalModule.credit.isValid()) {
                    this.requestEducationalModule.creditPersian = this.convertObjectDatesService.miladi2ShamsiMoment(this.requestEducationalModule.credit);
                }

                if (this.requestEducationalModule.timePassed && this.requestEducationalModule.timePassed.isValid()) {
                    this.requestEducationalModule.timePassedPersian = this.convertObjectDatesService.miladi2ShamsiMoment(this.requestEducationalModule.timePassed);
                }

                if (this.requestEducationalModule.recommendDate && this.requestEducationalModule.recommendDate) {
                    this.requestEducationalModule.recommendDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(this.requestEducationalModule.recommendDate);
                }
            }


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
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.organizationService.query().subscribe(
            (res: HttpResponse<IOrganizationMarineSuffix[]>) => {
                this.organizations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.competencyService.query().subscribe(
            (res: HttpResponse<ICompetencyMarineSuffix[]>) => {
                this.competencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachApproachMarineSuffix[]>) => {
                this.teachingApproachs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessLevelService.query().subscribe(
            (res: HttpResponse<IEffectivenessLevelMarineSuffix[]>) => {
                this.effectivenessLevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.assessmentMethodService.query().subscribe(
            (res: HttpResponse<IAssessmentMethodMarineSuffix[]>) => {
                this.assessmentMethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessIndexService.query().subscribe(
            (res: HttpResponse<IEffectivenessIndexMarineSuffix[]>) => {
                this.effectivenessIndeces = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.peopleUnderTrainingService.query().subscribe(
            (res: HttpResponse<IPeopleUnderTrainingMarineSuffix[]>) => {
                this.peopleUnderTrainings = res.body;

                if(this.requestEducationalModule.id !== undefined){
                    this.requestEducationalModule.peopleUnderTrainings.forEach(w => {
                        const person = this.peopleUnderTrainings.find(e => e.id == w.id);
                        w.fullTitle = (person.title ? person.title : "") + " " +
                            (person.description ? "(" + person.description + ")" : "");
                    })
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    addNewHeadline(){
        this.headlineRows++;
        let newHeadline: HeadlineMarineSuffix = {
            headlineLevel: HeadlineLevel.FAMILIARITY,
            headlineScope: HeadlineScope.KNOWLEDGE,
            totalHour: 0,
            isNew: true,
            id:this.headlineRows
        };
        this.requestEducationalModule.headlines.push(newHeadline);
    }
    showHeadlines(id){
        let criteria = [{
            key: 'requestEducationalModuleId.equals',
            value: id
        }];
        this.headlineService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp: HttpResponse<IHeadlineMarineSuffix[]>) => {
                this.headlines = resp.body;
            },
            (error) => this.onError("موردی یافت نشد."));
    }
    checkDateValidation(event, dateType) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                if (dateType == 1) {
                    this.dateRecommendDateValid = 1;
                } else if(dateType == 2) {
                    this.dateCreditValid = 1;
                } else if(dateType == 3) {
                    this.dateTimePassedValid = 1;
                }

            }
            else {
                if (dateType == 1) {
                    this.dateRecommendDateValid = 2;
                } else if(dateType == 2) {
                    this.dateCreditValid = 2;
                } else if(dateType == 3) {
                    this.dateTimePassedValid = 2;
                }
            }
        }
        catch (e) {
            if (dateType == 1) {
                this.dateRecommendDateValid = 2;
            } else if(dateType == 2) {
                this.dateCreditValid = 2;
            } else if(dateType == 3) {
                this.dateTimePassedValid = 2;
            }
        }
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
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست طراحی شما پس از ثبت برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
        }
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        if (this.requestEducationalModule.recommendDatePersian) {
            this.requestEducationalModule.recommendDate = this.convertObjectDatesService.shamsi2miladiMoment(this.requestEducationalModule.recommendDatePersian);
        }
        if (this.requestEducationalModule.timePassedPersian) {
            this.requestEducationalModule.timePassed = this.convertObjectDatesService.shamsi2miladiMoment(this.requestEducationalModule.timePassedPersian);
        }
        if (this.requestEducationalModule.creditPersian) {
            this.requestEducationalModule.credit = this.convertObjectDatesService.shamsi2miladiMoment(this.requestEducationalModule.creditPersian);
        }

        this.requestEducationalModule.headlines.forEach(a => {
            let name = 'headlineLevel_' + a.id;
            let radio = "input[name=" + name + "]:checked";
            let headlineValue = $(radio).val();
            if(headlineValue)
                a.headlineLevel = headlineValue;

            name = 'headlineScope_' + a.id;
            radio = "input[name=" + name + "]:checked";
            headlineValue = $(radio).val();
            if(headlineValue)
                a.headlineScope = headlineValue;

            if(a.isNew){
                a.id = undefined;
            }
        });

        this.currentUserFullName = this.currentPerson.fullName;
        this.requestEducationalModule.code = this.requestEducationalModule.code ? this.requestEducationalModule.code : "";
        this.requestEducationalModule.title = this.requestEducationalModule.title ? this.requestEducationalModule.title : "";
        this.requestEducationalModule.learningTimePractical = this.requestEducationalModule.learningTimePractical ? this.requestEducationalModule.learningTimePractical : 0;
        this.requestEducationalModule.learningTimeTheorical = this.requestEducationalModule.learningTimeTheorical ? this.requestEducationalModule.learningTimeTheorical : 0;
        this.message = "";

        if(!this.currentPerson.organizationChartId) {
            this.message = "موقعیت در چارت سازمانی برای شما تنظیم نشده است، لطفا مراتب را با مدیریت سامانه در میان بگذارید.";
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
    get headlineRowSpan() {
        return this.headlineRows + 4;
    }
}
