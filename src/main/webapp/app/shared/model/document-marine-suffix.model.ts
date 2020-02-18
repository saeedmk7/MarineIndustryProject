import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';
import { IEducationalCenterMarineSuffix } from 'app/shared/model//educational-center-marine-suffix.model';
import { IResourceMarineSuffix } from 'app/shared/model//resource-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IAnnouncementMarineSuffix } from 'app/shared/model//announcement-marine-suffix.model';
import { IUsersRequestMarineSuffix } from 'app/shared/model//users-request-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IInstructionMarineSuffix } from 'app/shared/model//instruction-marine-suffix.model';
import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model//invest-to-group-transaction-marine-suffix.model';
import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model//media-awareness-report-marine-suffix.model';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model//pre-job-niazsanji-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { ISoldierMarineSuffix } from 'app/shared/model//soldier-marine-suffix.model';
import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model//soldier-training-report-marine-suffix.model';
import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model//evaluate-criteria-training-marine-suffix.model';

export interface IDocumentMarineSuffix {
    id?: number;
    title?: string;
    fileDocContentType?: string;
    fileDoc?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    entityName?: any,
    entityId?: any,
    people?: IPersonMarineSuffix[];
    teachers?: ITeacherMarineSuffix[];
    jobs?: IJobMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
    educationalCenters?: IEducationalCenterMarineSuffix[];
    resources?: IResourceMarineSuffix[];
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    announcements?: IAnnouncementMarineSuffix[];
    usersRequests?: IUsersRequestMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    instructions?: IInstructionMarineSuffix[];
    investToGroupTransactions?: IInvestToGroupTransactionMarineSuffix[];
    mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[];
    preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    soldiers?: ISoldierMarineSuffix[];
    soldierTrainingReports?: ISoldierTrainingReportMarineSuffix[];
    evaluateCriteriaTrainings?: IEvaluateCriteriaTrainingMarineSuffix[];
}

export class DocumentMarineSuffix implements IDocumentMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public fileDocContentType?: string,
        public fileDoc?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public entityName?: any,
        public entityId?: any,
        public people?: IPersonMarineSuffix[],
        public teachers?: ITeacherMarineSuffix[],
        public jobs?: IJobMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[],
        public educationalCenters?: IEducationalCenterMarineSuffix[],
        public resources?: IResourceMarineSuffix[],
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public announcements?: IAnnouncementMarineSuffix[],
        public usersRequests?: IUsersRequestMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public instructions?: IInstructionMarineSuffix[],
        public investToGroupTransactions?: IInvestToGroupTransactionMarineSuffix[],
        public mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[],
        public preJobNiazsanjis?: IPreJobNiazsanjiMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public soldiers?: ISoldierMarineSuffix[],
        public soldierTrainingReports?: ISoldierTrainingReportMarineSuffix[],
        public evaluateCriteriaTrainings?: IEvaluateCriteriaTrainingMarineSuffix[]
    ) {}
}
