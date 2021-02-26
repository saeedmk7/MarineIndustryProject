import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { JhiAlertService, JhiLanguageService } from 'ng-jhipster';
import { PeopleUnderTrainingMarineSuffixService } from 'app/entities/people-under-training-marine-suffix';
import { EffectivenessLevelMarineSuffixService } from 'app/entities/effectiveness-level-marine-suffix';
import { CompetencyMarineSuffixService } from 'app/entities/competency-marine-suffix';
import { EffectivenessIndexMarineSuffixService } from 'app/entities/effectiveness-index-marine-suffix';
import { AssessmentMethodMarineSuffixService } from 'app/entities/assessment-method-marine-suffix';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix/educational-module-marine-suffix.service';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';
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
import { NiazsanjiGroupMarineSuffixService } from 'app/entities/niazsanji-group-marine-suffix';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import * as $ from 'jquery';

@Component({
    selector: 'mi-educational-module-marine-suffix-detail',
    templateUrl: './educational-module-marine-suffix-detail.component.html'
})
export class EducationalModuleMarineSuffixDetailComponent implements OnInit {
    educationalModule: IEducationalModuleMarineSuffix;

    scientificworkgroups: IScientificWorkGroupMarineSuffix[];

    educationalcenters: IEducationalCenterMarineSuffix[];

    goals: IGoalMarineSuffix[];

    resources: IResourceMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    securitylevels: ISecurityLevelMarineSuffix[];

    skillablelevelofskills: ISkillableLevelOfSkillMarineSuffix[];

    evaluationmethods: IEvaluationMethodMarineSuffix[];

    organizations: IOrganizationMarineSuffix[];

    niazsanjigroups: INiazsanjiGroupMarineSuffix[];

    requestEducationalModules: IRequestEducationalModuleMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    teachingApproachs: ITeachApproachMarineSuffix[];

    peopleUnderTrainings: IPeopleUnderTrainingMarineSuffix[];

    competencies: ICompetencyMarineSuffix[];

    effectivenessIndeces: IEffectivenessIndexMarineSuffix[];

    effectivenessLevels: IEffectivenessLevelMarineSuffix[];

    assessmentMethods: IAssessmentMethodMarineSuffix[];

    constructor(
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private peopleUnderTrainingService: PeopleUnderTrainingMarineSuffixService,
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
        private competencyService: CompetencyMarineSuffixService,
        private effectivenessIndexService: EffectivenessIndexMarineSuffixService,
        private assessmentMethodService: AssessmentMethodMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private teachingApproachService: TeachingApproachMarineSuffixService,
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
        private niazsanjiGroupService: NiazsanjiGroupMarineSuffixService,
        private languageManager: JhiLanguageService,
        protected restrictionService: RestrictionMarineSuffixService,
        private router: Router,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalModule }) => {
            this.educationalModule = educationalModule;
            if (this.educationalModule.teachers) this.educationalModule.teachers.forEach(a => (a.fullName = a.name + ' ' + a.family));
            this.educationalModule = this.convertObjectDatesService.changeDate(educationalModule);
            if (this.educationalModule.code && this.educationalModule.code.includes('(')) {
                this.educationalModule.code = this.educationalModule.code.substring(0, this.educationalModule.code.indexOf('('));
            }
            $('#target:input').attr('disabled', 'disabled');
        });
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
        this.competencyService.query().subscribe(
            (res: HttpResponse<ICompetencyMarineSuffix[]>) => {
                this.competencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.peopleUnderTrainingService.query().subscribe(
            (res: HttpResponse<IPeopleUnderTrainingMarineSuffix[]>) => {
                this.peopleUnderTrainings = res.body;

                if (this.educationalModule.id !== undefined) {
                    this.educationalModule.peopleUnderTrainings.forEach(w => {
                        const person = this.peopleUnderTrainings.find(e => e.id == w.id);
                        w.fullTitle = (person.title ? person.title : '') + ' ' + (person.description ? '(' + person.description + ')' : '');
                    });
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachApproachMarineSuffix[]>) => {
                this.teachingApproachs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestEducationalModuleService.query().subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) => {
                this.requestEducationalModules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }
}
