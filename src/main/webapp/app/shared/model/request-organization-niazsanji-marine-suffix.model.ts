import { Moment } from 'moment';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import {RequestStatus} from 'app/shared/model/enums/RequestStatus';

export interface IRequestOrganizationNiazsanjiMarineSuffix {
    id?: number;
    code?: string;
    fullTitle?: string;
    recommendedByOrgchart?: string;
    neededSoftwares?: string;
    neededHardware?: string;
    studentsType?: string;
    teacherNotFound?: boolean;
    teacherName?: string;
    teacherMobile?: string;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    trainingGoals?: string;
    description?: string;
    priceCost?: number;
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
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    people?: IPersonMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    courseTypeTitle?: string;
    courseTypeId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    teacherFamily?: string;
    teacherId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
    teachApproachTitle?: string;
    teachApproachId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
}

export class RequestOrganizationNiazsanjiMarineSuffix implements IRequestOrganizationNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public fullTitle?: string,
        public recommendedByOrgchart?: string,
        public neededSoftwares?: string,
        public neededHardware?: string,
        public studentsType?: string,
        public teacherNotFound?: boolean,
        public teacherName?: string,
        public teacherMobile?: string,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public trainingGoals?: string,
        public description?: string,
        public priceCost?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public people?: IPersonMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public teacherFamily?: string,
        public teacherId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleId?: number,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string,
        public teachApproachTitle?: string,
        public teachApproachId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.teacherNotFound = this.teacherNotFound || false;
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
