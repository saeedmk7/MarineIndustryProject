import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { Principal } from 'app/core';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix/request-educational-module-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { HeadlineLevel } from 'app/shared/model/enums/HeadlineLevel';
import { HeadlineScope } from 'app/shared/model/enums/HeadlineScope';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { PeopleUnderTrainingMarineSuffixService } from 'app/entities/people-under-training-marine-suffix';
import { CompetencyMarineSuffixService } from 'app/entities/competency-marine-suffix';
import { HeadlineMarineSuffixService } from 'app/entities/headline-marine-suffix';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { EffectivenessLevelMarineSuffixService } from 'app/entities/effectiveness-level-marine-suffix';
import { AssessmentMethodMarineSuffixService } from 'app/entities/assessment-method-marine-suffix';
import { EffectivenessIndexMarineSuffixService } from 'app/entities/effectiveness-index-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from 'app/entities/scientific-work-group-marine-suffix';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { EducationalCenterMarineSuffixService } from 'app/entities/educational-center-marine-suffix';
import { GoalMarineSuffixService } from 'app/entities/goal-marine-suffix';
import { ResourceMarineSuffixService } from 'app/entities/resource-marine-suffix';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';
import { SecurityLevelMarineSuffixService } from 'app/entities/security-level-marine-suffix';
import { SkillableLevelOfSkillMarineSuffixService } from 'app/entities/skillable-level-of-skill-marine-suffix';
import { EvaluationMethodMarineSuffixService } from 'app/entities/evaluation-method-marine-suffix';
import { OrganizationMarineSuffixService } from 'app/entities/organization-marine-suffix';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import * as $ from 'jquery';

@Component({
    selector: 'mi-request-educational-module-marine-suffix-detail',
    templateUrl: './request-educational-module-marine-suffix-detail.component.html',
    styleUrls: ['./request-educational-module-marine-suffix.scss']
})
export class RequestEducationalModuleMarineSuffixDetailComponent implements OnInit {
    requestEducationalModule: IRequestEducationalModuleMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    rows: number = 0;

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
    peopleUnderTrainingDescs: string = '';

    headlineLevelKeys: string[] = [];
    headlineScopeKeys: string[] = [];
    headlineRows: number = 0;

    dateRecommendDateValid: number;
    dateTimePassedValid: number;
    dateCreditValid: number;

    constructor(
        private dataUtils: JhiDataUtils,
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private personService: PersonMarineSuffixService,
        private peopleUnderTrainingService: PeopleUnderTrainingMarineSuffixService,
        private competencyService: CompetencyMarineSuffixService,
        private headlineService: HeadlineMarineSuffixService,
        private teachingApproachService: TeachingApproachMarineSuffixService,
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
        private assessmentMethodService: AssessmentMethodMarineSuffixService,
        private effectivenessIndexService: EffectivenessIndexMarineSuffixService,
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
        protected restrictionService: RestrictionMarineSuffixService,
        private router: Router,
        private requestEducationalModuleMarineSuffixService: RequestEducationalModuleMarineSuffixService
    ) {}
    private setRoles(account: any) {
        if (account) {
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    ngOnInit() {
        this.headlineLevelKeys = Object.keys(HeadlineLevel);
        this.headlineScopeKeys = Object.keys(HeadlineScope);
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    this.currentUserFullName = this.currentPerson.fullName;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            this.requestEducationalModule = requestEducationalModule;
            if (this.requestEducationalModule.teachers)
                this.requestEducationalModule.teachers.forEach(a => (a.fullName = a.name + ' ' + a.family));

            this.headlineRows = this.requestEducationalModule.headlines.length;

            if (this.requestEducationalModule.credit && this.requestEducationalModule.credit.isValid()) {
                this.requestEducationalModule.creditPersian = this.convertObjectDatesService.miladi2ShamsiMoment(
                    this.requestEducationalModule.credit
                );
            }

            if (this.requestEducationalModule.timePassed && this.requestEducationalModule.timePassed.isValid()) {
                this.requestEducationalModule.timePassedPersian = this.convertObjectDatesService.miladi2ShamsiMoment(
                    this.requestEducationalModule.timePassed
                );
            }

            if (this.requestEducationalModule.recommendDate && this.requestEducationalModule.recommendDate) {
                this.requestEducationalModule.recommendDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(
                    this.requestEducationalModule.recommendDate
                );
            }
            $('#target :input').prop('disabled', true);
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

                if (this.requestEducationalModule.id !== undefined) {
                    this.requestEducationalModule.peopleUnderTrainings.forEach(w => {
                        const person = this.peopleUnderTrainings.find(e => e.id == w.id);
                        w.fullTitle = (person.title ? person.title : '') + ' ' + (person.description ? '(' + person.description + ')' : '');
                    });
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestEducationalModuleMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    private onSaveSuccess() {}

    private onSaveError() {}
    previousState() {
        window.history.back();
    }

    get headlineRowSpan() {
        return this.headlineRows + 4;
    }
}
