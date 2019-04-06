import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model//scientific-work-group-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';
import { IGoalMarineSuffix } from 'app/shared/model//goal-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model//resource-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

export interface IRequestEducationalModuleMarineSuffix {
    id?: number;
    code?: number;
    title?: string;
    fullTitle?: string;
    learningTimeTheorical?: number;
    learningTimePractical?: number;
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
    credit?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    educationalModules?: IEducationalModuleMarineSuffix[];
    scientificWorkGroups?: IScientificWorkGroupMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    educationalCenters?: IEducationalCenterMarineSuffix[];
    goals?: IGoalMarineSuffix[];
    resources?: IResourceMarineSuffix[];
    teachers?: ITeacherMarineSuffix[];
    securityLevelTitle?: string;
    securityLevelId?: number;
    skillableLevelOfSkillTitle?: string;
    skillableLevelOfSkillId?: number;
    evaluationMethodTitle?: string;
    evaluationMethodId?: number;
    organizationTitle?: string;
    organizationId?: number;
}

export class RequestEducationalModuleMarineSuffix implements IRequestEducationalModuleMarineSuffix {
    constructor(
        public id?: number,
        public code?: number,
        public title?: string,
        public fullTitle?: string,
        public learningTimeTheorical?: number,
        public learningTimePractical?: number,
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
        public credit?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public scientificWorkGroups?: IScientificWorkGroupMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public educationalCenters?: IEducationalCenterMarineSuffix[],
        public goals?: IGoalMarineSuffix[],
        public resources?: IResourceMarineSuffix[],
        public teachers?: ITeacherMarineSuffix[],
        public securityLevelTitle?: string,
        public securityLevelId?: number,
        public skillableLevelOfSkillTitle?: string,
        public skillableLevelOfSkillId?: number,
        public evaluationMethodTitle?: string,
        public evaluationMethodId?: number,
        public organizationTitle?: string,
        public organizationId?: number
    ) {
        this.archived = this.archived || false;
        this.fullTitle =  this.code + " - " + this.title;
    }
}
