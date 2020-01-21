import { Moment } from 'moment';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

export interface IRequestOtherNiazsanjiMarineSuffix {
    id?: number;
    code?: string;
    costEducationalModule?: number;
    description?: any;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    createUserLogin?: string;
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
    guid?: string;
    hasImportantMessage?: boolean;
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    niazsanjiInputTitle?: string;
    niazsanjiInputId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    skillLevelOfSkillTitle?: string;
    totalLearningTime?: number;
    personFamily?: string;
    personName?: string;
    personFullName?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
}

export class RequestOtherNiazsanjiMarineSuffix implements IRequestOtherNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public costEducationalModule?: number,
        public description?: any,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public createUserLogin?: string,
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
        public guid?: string,
        public hasImportantMessage?: boolean,
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public niazsanjiInputTitle?: string,
        public niazsanjiInputId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public skillLevelOfSkillTitle?: string,
        public totalLearningTime?: number,
        public personFamily?: string,
        public personName?: string,
        public personFullName?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
