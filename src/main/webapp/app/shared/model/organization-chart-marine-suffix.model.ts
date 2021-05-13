import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IOrganizationChartMarineSuffix } from 'app/shared/model//organization-chart-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model//organization-chart-authority-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model//educational-history-marine-suffix.model';
import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model//invest-to-group-transaction-marine-suffix.model';
import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model//media-awareness-report-marine-suffix.model';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model//pre-job-niazsanji-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { ISoldierMarineSuffix } from 'app/shared/model//soldier-marine-suffix.model';
import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model//evaluate-criteria-training-marine-suffix.model';
import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model//evaluate-criteria-data-marine-suffix.model';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model//matching-educational-record-marine-suffix.model';
import { IApplicationProcessMarineSuffix } from 'app/shared/model//application-process-marine-suffix.model';
import { IForceRunningPercentMarineSuffix } from 'app/shared/model//force-running-percent-marine-suffix.model';
import { IReportGeneratorMarineSuffix } from 'app/shared/model//report-generator-marine-suffix.model';

export interface IOrganizationChartMarineSuffix {
    id?: number;
    title?: string;
    fullTitle?: string;
    rootTitle?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    people?: IPersonMarineSuffix[];
    organizationCharts?: IOrganizationChartMarineSuffix[];
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    organizationChartAuthorities?: IOrganizationChartAuthorityMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    educationalHistories?: IEducationalHistoryMarineSuffix[];
    investToGroupTransactions?: IInvestToGroupTransactionMarineSuffix[];
    mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[];
    preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    soldiers?: ISoldierMarineSuffix[];
    evaluateCriteriaTrainings?: IEvaluateCriteriaTrainingMarineSuffix[];
    evaluateCriteriaData?: IEvaluateCriteriaDataMarineSuffix[];
    matchingEducationalRecords?: IMatchingEducationalRecordMarineSuffix[];
    applicationProcesses?: IApplicationProcessMarineSuffix[];
    parentTitle?: string;
    parentId?: number;
    forceRunningPercents?: IForceRunningPercentMarineSuffix[];
    reportGenerators?: IReportGeneratorMarineSuffix[];
}

export class OrganizationChartMarineSuffix implements IOrganizationChartMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public fullTitle?: string,
        public rootTitle?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public people?: IPersonMarineSuffix[],
        public organizationCharts?: IOrganizationChartMarineSuffix[],
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public organizationChartAuthorities?: IOrganizationChartAuthorityMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public educationalHistories?: IEducationalHistoryMarineSuffix[],
        public investToGroupTransactions?: IInvestToGroupTransactionMarineSuffix[],
        public mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[],
        public preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public soldiers?: ISoldierMarineSuffix[],
        public evaluateCriteriaTrainings?: IEvaluateCriteriaTrainingMarineSuffix[],
        public evaluateCriteriaData?: IEvaluateCriteriaDataMarineSuffix[],
        public matchingEducationalRecords?: IMatchingEducationalRecordMarineSuffix[],
        public applicationProcesses?: IApplicationProcessMarineSuffix[],
        public parentTitle?: string,
        public parentId?: number,
        public forceRunningPercents?: IForceRunningPercentMarineSuffix[],
        public reportGenerators?: IReportGeneratorMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
