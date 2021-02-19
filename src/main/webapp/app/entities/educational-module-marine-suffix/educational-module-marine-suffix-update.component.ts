import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiLanguageService } from 'ng-jhipster';

import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from './educational-module-marine-suffix.service';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from 'app/entities/scientific-work-group-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from 'app/entities/educational-center-marine-suffix';
import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { GoalMarineSuffixService } from 'app/entities/goal-marine-suffix';
import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ResourceMarineSuffixService } from 'app/entities/resource-marine-suffix';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';
import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { SecurityLevelMarineSuffixService } from 'app/entities/security-level-marine-suffix';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from 'app/entities/skillable-level-of-skill-marine-suffix';
import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { EvaluationMethodMarineSuffixService } from 'app/entities/evaluation-method-marine-suffix';
import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { OrganizationMarineSuffixService } from 'app/entities/organization-marine-suffix';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { NiazsanjiGroupMarineSuffixService } from 'app/entities/niazsanji-group-marine-suffix';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { PeopleUnderTrainingMarineSuffixService } from 'app/entities/people-under-training-marine-suffix';
import { CompetencyMarineSuffixService } from 'app/entities/competency-marine-suffix';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { EffectivenessIndexMarineSuffixService } from 'app/entities/effectiveness-index-marine-suffix';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { EffectivenessLevelMarineSuffixService } from 'app/entities/effectiveness-level-marine-suffix';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { AssessmentMethodMarineSuffixService } from 'app/entities/assessment-method-marine-suffix';

@Component({
    selector: 'mi-educational-module-marine-suffix-update',
    templateUrl: './educational-module-marine-suffix-update.component.html'
})
export class EducationalModuleMarineSuffixUpdateComponent implements OnInit {
    private _educationalModule: IEducationalModuleMarineSuffix;
    isSaving: boolean;

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

    isfa: boolean;

    constructor(
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
        private activatedRoute: ActivatedRoute,
        private languageManager: JhiLanguageService,
        protected restrictionService: RestrictionMarineSuffixService,
        private router: Router
    ) {
        this.isfa = languageManager.currentLang == 'fa';
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalModule }) => {
            this.educationalModule = educationalModule;

            if (this.educationalModule.code && this.educationalModule.code.includes('(')) {
                this.educationalModule.code = this.educationalModule.code.substring(0, this.educationalModule.code.indexOf('('));
            }
            if (this.educationalModule.teachers) this.educationalModule.teachers.forEach(a => (a.fullName = a.name + ' ' + a.family));
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

    previousState() {
        window.history.back();
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    save() {
        this.isSaving = true;

        this.educationalModule.status = 0;
        /*if(this.isfa)
        {
            let timePassedPersian: string = persianMoment.from(this.timePassed, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.educationalModule.timePassed = moment(timePassedPersian);
            let creditPersian: string = persianMoment.from(this.credit, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.educationalModule.credit = moment(creditPersian);
        }
        else {
            this.educationalModule.timePassed = moment(this.timePassed, DATE_TIME_FORMAT);
            this.educationalModule.credit = moment(this.credit, DATE_TIME_FORMAT);
        }*/

        if (this.educationalModule.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalModuleService.update(this.educationalModule));
        } else {
            this.subscribeToSaveResponse(this.educationalModuleService.create(this.educationalModule));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalModuleMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
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

    trackNiazsanjiGroupById(index: number, item: INiazsanjiGroupMarineSuffix) {
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
    get educationalModule() {
        return this._educationalModule;
    }

    set educationalModule(educationalModule: IEducationalModuleMarineSuffix) {
        this._educationalModule = educationalModule;
    }
}
