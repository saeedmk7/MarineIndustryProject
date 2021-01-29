import { Moment } from 'moment';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model//final-niazsanji-report-person-marine-suffix.model';
import { IPollScoreMarineSuffix } from 'app/shared/model//poll-score-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model//educational-history-marine-suffix.model';
import { IEducationalRecordMarineSuffix } from 'app/shared/model//educational-record-marine-suffix.model';
import { IJobRecordMarineSuffix } from 'app/shared/model//job-record-marine-suffix.model';
import { IResearchRecordMarineSuffix } from 'app/shared/model//research-record-marine-suffix.model';
import { ITeachingRecordMarineSuffix } from 'app/shared/model//teaching-record-marine-suffix.model';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model//pre-job-niazsanji-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { IJobChangeMarineSuffix } from 'app/shared/model//job-change-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model//scientific-work-group-marine-suffix.model';
import { IMainTaskMarineSuffix } from 'app/shared/model//main-task-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IUsersRequestMarineSuffix } from 'app/shared/model//users-request-marine-suffix.model';

export interface IPersonMarineSuffix {
    id?: number;
    name?: string;
    family?: string;
    fullName?: string;
    fatherName?: string;
    certificateNumber?: string;
    nationalId?: string;
    birthDate?: Moment;
    birthDatePersian?: string;
    personelCode?: string;
    employmentDate?: Moment;
    employmentDatePersian?: string;
    yearOfService?: number;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    guid?: string;
    phoneNumber?: string;
    mobile?: string;
    address?: string;
    finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[];
    pollScores?: IPollScoreMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    educationalHistories?: IEducationalHistoryMarineSuffix[];
    educationalRecords?: IEducationalRecordMarineSuffix[];
    jobRecords?: IJobRecordMarineSuffix[];
    researchRecords?: IResearchRecordMarineSuffix[];
    teachingRecords?: ITeachingRecordMarineSuffix[];
    preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    jobChanges?: IJobChangeMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    scientificWorkGroups?: IScientificWorkGroupMarineSuffix[];
    lastQualificationTitle?: string;
    lastQualificationId?: number;
    lastFieldOfStudyTitle?: string;
    lastFieldOfStudyId?: number;
    employmentTypeTitle?: string;
    employmentTypeId?: number;
    workGroupTitle?: string;
    workGroupId?: number;
    workIndustryTitle?: string;
    workIndustryId?: number;
    jobTitle?: string;
    jobCode?: string;
    jobId?: number;
    practicaljobTitle?: string;
    practicaljobCode?: string;
    practicaljobId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    mainTasks?: IMainTaskMarineSuffix[];
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    usersRequests?: IUsersRequestMarineSuffix[];
}

export class PersonMarineSuffix implements IPersonMarineSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public family?: string,
        public fullName?: string,
        public fatherName?: string,
        public certificateNumber?: string,
        public nationalId?: string,
        public birthDate?: Moment,
        public personelCode?: string,
        public employmentDate?: Moment,
        public yearOfService?: number,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public guid?: string,
        public phoneNumber?: string,
        public mobile?: string,
        public address?: string,
        public finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[],
        public pollScores?: IPollScoreMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public educationalHistories?: IEducationalHistoryMarineSuffix[],
        public educationalRecords?: IEducationalRecordMarineSuffix[],
        public jobRecords?: IJobRecordMarineSuffix[],
        public researchRecords?: IResearchRecordMarineSuffix[],
        public teachingRecords?: ITeachingRecordMarineSuffix[],
        public preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public jobChanges?: IJobChangeMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public scientificWorkGroups?: IScientificWorkGroupMarineSuffix[],
        public lastQualificationTitle?: string,
        public lastQualificationId?: number,
        public lastFieldOfStudyTitle?: string,
        public lastFieldOfStudyId?: number,
        public employmentTypeTitle?: string,
        public employmentTypeId?: number,
        public workGroupTitle?: string,
        public workGroupId?: number,
        public workIndustryTitle?: string,
        public workIndustryId?: number,
        public jobTitle?: string,
        public jobId?: number,
        public practicaljobTitle?: string,
        public practicaljobId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public mainTasks?: IMainTaskMarineSuffix[],
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public usersRequests?: IUsersRequestMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
