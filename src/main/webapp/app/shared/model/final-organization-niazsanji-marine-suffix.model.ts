import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IFinalOrganizationNiazsanjiMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    code?: string;
    recommendedByOrgchart?: string;
    neededSoftwares?: string;
    neededHardware?: string;
    studentsType?: string;
    teacherName?: string;
    teacherMobile?: string;
    conversation?: any;
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
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    people?: IPersonMarineSuffix[];
    peopleCount?: number;
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    courseTypeTitle?: string;
    courseTypeId?: number;
    organizationChartTitle?: string;
    organizationChartRootTitle?: string;
    organizationChartId?: number;
    teacherFamily?: string;
    teacherId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    teachApproachTitle?: string;
    teachApproachId?: number;
    requestOrganizationNiazsanjiRecommendedByOrgchart?: string;
    requestOrganizationNiazsanjiId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
}

export class FinalOrganizationNiazsanjiMarineSuffix implements IFinalOrganizationNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public code?: string,
        public recommendedByOrgchart?: string,
        public neededSoftwares?: string,
        public neededHardware?: string,
        public studentsType?: string,
        public teacherName?: string,
        public teacherMobile?: string,
        public conversation?: any,
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
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public people?: IPersonMarineSuffix[],
        public peopleCount?: number,
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
        public teachApproachTitle?: string,
        public teachApproachId?: number,
        public requestOrganizationNiazsanjiRecommendedByOrgchart?: string,
        public requestOrganizationNiazsanjiId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
