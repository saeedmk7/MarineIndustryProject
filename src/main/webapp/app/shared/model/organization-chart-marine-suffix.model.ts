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
import { IForceRunningPercentMarineSuffix } from 'app/shared/model//force-running-percent-marine-suffix.model';

export interface IOrganizationChartMarineSuffix {
    id?: number;
    title?: string;
    fullTitle?: string;
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
    parentTitle?: string;
    parentId?: number;
    forceRunningPercents?: IForceRunningPercentMarineSuffix[];
}

export class OrganizationChartMarineSuffix implements IOrganizationChartMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public fullTitle?: string,
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
        public parentTitle?: string,
        public parentId?: number,
        public forceRunningPercents?: IForceRunningPercentMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
