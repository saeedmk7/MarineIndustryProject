import { Moment } from 'moment';
import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model//niazsanji-integration-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import {EducationalModuleType} from "app/shared/model/enums/EducationalModuleType";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {RequestNiazsanjiType} from "app/shared/model/enums/RequestNiazsanjiType";

export interface IPrioritizeRequestNiazsanjiMarineSuffix {
    id?: number;
    code?: string;
    costEducationalModule?: number;
    educationalModuleType?: EducationalModuleType;
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
    requestNiazsanjiType?: RequestNiazsanjiType;
    priority?: number;
    niazsanjiIntegrations?: INiazsanjiIntegrationMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    requestNiazsanjiFardiCode?: string;
    requestNiazsanjiFardiId?: number;
    preJobNiazsanjiTitle?: string;
    preJobNiazsanjiId?: number;
    requestOtherNiazsanjiCode?: string;
    requestOtherNiazsanjiId?: number;
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

export class PrioritizeRequestNiazsanjiMarineSuffix implements IPrioritizeRequestNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public costEducationalModule?: number,
        public educationalModuleType?: EducationalModuleType,
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
        public requestNiazsanjiType?: RequestNiazsanjiType,
        public priority?: number,
        public niazsanjiIntegrations?: INiazsanjiIntegrationMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public requestNiazsanjiFardiCode?: string,
        public requestNiazsanjiFardiId?: number,
        public preJobNiazsanjiTitle?: string,
        public preJobNiazsanjiId?: number,
        public requestOtherNiazsanjiCode?: string,
        public requestOtherNiazsanjiId?: number,
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
