import { Moment } from 'moment';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model//final-niazsanji-report-person-marine-suffix.model';
import { IPollScoreMarineSuffix } from 'app/shared/model//poll-score-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model//educational-history-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model//scientific-work-group-marine-suffix.model';
import { IMainTaskMarineSuffix } from 'app/shared/model//main-task-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';

export interface IPersonMarineSuffix {
    id?: number;
    name?: string;
    family?: string;
    fullName?: string;
    fatherName?: string;
    certificateNumber?: string;
    nationalId?: string;
    birthDate?: Moment;
    personelCode?: string;
    employmentDate?: Moment;
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
    finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[];
    pollScores?: IPollScoreMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    educationalHistories?: IEducationalHistoryMarineSuffix[];
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
        public finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[],
        public pollScores?: IPollScoreMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public educationalHistories?: IEducationalHistoryMarineSuffix[],
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
        public runPhases?: IRunPhaseMarineSuffix[]
    ) {

        this.archived = this.archived || false;
    }
}
