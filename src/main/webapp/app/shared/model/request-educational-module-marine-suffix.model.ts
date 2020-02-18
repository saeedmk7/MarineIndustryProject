import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IHeadlineMarineSuffix } from 'app/shared/model//headline-marine-suffix.model';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model//scientific-work-group-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';
import { IGoalMarineSuffix } from 'app/shared/model//goal-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model//resource-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';
import {RequestStatus} from 'app/shared/model/enums/RequestStatus';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model//people-under-training-marine-suffix.model';
import { ITeachingApproachMarineSuffix } from 'app/shared/model//teaching-approach-marine-suffix.model';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model//effectiveness-level-marine-suffix.model';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model//effectiveness-index-marine-suffix.model';

export interface IRequestEducationalModuleMarineSuffix {
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
    educationalModuleHeadlines?: string;
    prerequisite?: string;
    drafters?: string;
    educationalModuleLevel?: number;
    educationalModuleGroup?: number;
    educationalModuleHour?: number;
    timePassed?: Moment;
    timePassedPersian?: string;
    credit?: Moment;
    creditPersian?: string;
    createUserLogin?: string;
    createUserLoginName?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    statusMeaning?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    goalsText?: string;
    teachersText?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    recommendDate?: Moment;
    recommendDatePersian?: string;
    goalsBehavioralText?: string;
    neededSoftwares?: string;
    neededHardware?: string;
    courseContactsTerms?: string;
    educationalModules?: IEducationalModuleMarineSuffix[];
    headlines?: IHeadlineMarineSuffix[];
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
}

export class RequestEducationalModuleMarineSuffix implements IRequestEducationalModuleMarineSuffix {
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
        public educationalModuleHeadlines?: string,
        public prerequisite?: string,
        public drafters?: string,
        public educationalModuleLevel?: number,
        public educationalModuleGroup?: number,
        public educationalModuleHour?: number,
        public timePassed?: Moment,
        public timePassedPersian?: string,
        public credit?: Moment,
        public creditPersian?: string,
        public createUserLogin?: string,
        public createUserLoginName?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public statusMeaning?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public goalsText?: string,
        public teachersText?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public recommendDate?: Moment,
        public recommendDatePersian?: string,
        public goalsBehavioralText?: string,
        public neededSoftwares?: string,
        public neededHardware?: string,
        public courseContactsTerms?: string,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public headlines?: IHeadlineMarineSuffix[],
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
        public securityLevelTitle?: string,
        public securityLevelId?: number,
        public skillableLevelOfSkillTitle?: string,
        public skillableLevelOfSkillId?: number,
        public evaluationMethodTitle?: string,
        public evaluationMethodId?: number,
        public organizationTitle?: string,
        public organizationId?: number,
        public competencyTitle?: string,
        public competencyId?: number
    ) {
        this.archived = this.archived || false;
        this.fullTitle =  this.code + ' - ' + this.title;
    }
}
