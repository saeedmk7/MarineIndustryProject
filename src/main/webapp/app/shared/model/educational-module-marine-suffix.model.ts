import { Moment } from 'moment';
import { IHeadlineMarineSuffix } from 'app/shared/model//headline-marine-suffix.model';
import { IEducationalModuleJobMarineSuffix } from 'app/shared/model//educational-module-job-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model//educational-history-marine-suffix.model';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model//design-niazsanji-marine-suffix.model';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model//pre-job-niazsanji-competency-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model//scientific-work-group-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';
import { IGoalMarineSuffix } from 'app/shared/model//goal-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model//resource-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model//people-under-training-marine-suffix.model';
import { ITeachingApproachMarineSuffix } from 'app/shared/model//teaching-approach-marine-suffix.model';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model//effectiveness-level-marine-suffix.model';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model//effectiveness-index-marine-suffix.model';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model//assessment-method-marine-suffix.model';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model//niazsanji-group-marine-suffix.model';

export interface IEducationalModuleMarineSuffix {
    id?: number;
    code?: string;
    title?: string;
    fullTitle?: string;
    learningTimeTheorical?: number;
    learningTimePractical?: number;
    totalLearningTime?: number;
    version?: string;
    innerCode?: string;
    centralizedCode?: string;
    moreDescription?: string;
    recommendedBy?: string;
    educationalModuleHeadlines?: any;
    prerequisite?: string;
    drafters?: string;
    educationalModuleLevel?: number;
    educationalModuleGroup?: number;
    educationalModuleHour?: number;
    timePassed?: Moment;
    credit?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    goalsText?: string;
    teachersText?: string;
    guid?: string;
    restrictionDescription?: string;
    recommendDate?: Moment;
    goalsBehavioralText?: string;
    neededSoftwares?: string;
    neededHardware?: string;
    courseContactsTerms?: string;
    headlines?: IHeadlineMarineSuffix[];
    educationalModuleJobs?: IEducationalModuleJobMarineSuffix[];
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    approvedRequestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    allRequestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    educationalHistories?: IEducationalHistoryMarineSuffix[];
    designNiazsanjis?: IDesignNiazsanjiMarineSuffix[];
    preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    scientificWorkGroups?: IScientificWorkGroupMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    educationalCenters?: IEducationalCenterMarineSuffix[];
    goals?: IGoalMarineSuffix[];
    resources?: IResourceMarineSuffix[];
    teachers?: ITeacherMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    peopleUnderTrainings?: IPeopleUnderTrainingMarineSuffix[];
    teachingApproaches?: ITeachingApproachMarineSuffix[];
    effectivenessLevels?: IEffectivenessLevelMarineSuffix[];
    effectivenessIndices?: IEffectivenessIndexMarineSuffix[];
    assessmentMethods?: IAssessmentMethodMarineSuffix[];
    requestEducationalModuleTitle?: string;
    requestEducationalModuleId?: number;
    securityLevelTitle?: string;
    securityLevelId?: number;
    skillableLevelOfSkillTitle?: string;
    skillableLevelOfSkillId?: number;
    evaluationMethodTitle?: string;
    evaluationMethodId?: number;
    organizationTitle?: string;
    organizationId?: number;
    competencyTitle?: string;
    competencyId?: number;
    niazsanjiGroups?: INiazsanjiGroupMarineSuffix[];
}

export class EducationalModuleMarineSuffix implements IEducationalModuleMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public title?: string,
        public fullTitle?: string,
        public learningTimeTheorical?: number,
        public learningTimePractical?: number,
        public totalLearningTime?: number,
        public version?: string,
        public innerCode?: string,
        public centralizedCode?: string,
        public moreDescription?: string,
        public recommendedBy?: string,
        public educationalModuleHeadlines?: any,
        public prerequisite?: string,
        public drafters?: string,
        public educationalModuleLevel?: number,
        public educationalModuleGroup?: number,
        public educationalModuleHour?: number,
        public timePassed?: Moment,
        public credit?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public goalsText?: string,
        public teachersText?: string,
        public guid?: string,
        public restrictionDescription?: string,
        public recommendDate?: Moment,
        public goalsBehavioralText?: string,
        public neededSoftwares?: string,
        public neededHardware?: string,
        public courseContactsTerms?: string,
        public headlines?: IHeadlineMarineSuffix[],
        public educationalModuleJobs?: IEducationalModuleJobMarineSuffix[],
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public approvedRequestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public allRequestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public educationalHistories?: IEducationalHistoryMarineSuffix[],
        public designNiazsanjis?: IDesignNiazsanjiMarineSuffix[],
        public preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public scientificWorkGroups?: IScientificWorkGroupMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public educationalCenters?: IEducationalCenterMarineSuffix[],
        public goals?: IGoalMarineSuffix[],
        public resources?: IResourceMarineSuffix[],
        public teachers?: ITeacherMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public peopleUnderTrainings?: IPeopleUnderTrainingMarineSuffix[],
        public teachingApproaches?: ITeachingApproachMarineSuffix[],
        public effectivenessLevels?: IEffectivenessLevelMarineSuffix[],
        public effectivenessIndices?: IEffectivenessIndexMarineSuffix[],
        public assessmentMethods?: IAssessmentMethodMarineSuffix[],
        public requestEducationalModuleTitle?: string,
        public requestEducationalModuleId?: number,
        public securityLevelTitle?: string,
        public securityLevelId?: number,
        public skillableLevelOfSkillTitle?: string,
        public skillableLevelOfSkillId?: number,
        public evaluationMethodTitle?: string,
        public evaluationMethodId?: number,
        public organizationTitle?: string,
        public organizationId?: number,
        public competencyTitle?: string,
        public competencyId?: number,
        public niazsanjiGroups?: INiazsanjiGroupMarineSuffix[]
    ) {
        this.archived = this.archived || false;
        this.fullTitle =  this.code + ' - ' + this.title;
    }
}
