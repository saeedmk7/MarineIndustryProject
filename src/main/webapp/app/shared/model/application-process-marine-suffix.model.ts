import { Moment } from 'moment';
import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model//application-process-run-step-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IApplicationProcessMarineSuffix {
    id?: number;
    code?: string;
    description?: any;
    jobAfterProcess?: string;
    acceptedUniversityAndDegree?: string;
    startStudyDate?: string;
    graduateDateOfNewCourse?: string;
    averagePointOfNewCourse?: string;
    acceptedMajorAndField?: string;
    haveUsedEducationalFacilities?: boolean;
    haveUsedEducationalFacilitiesDescription?: string;
    dateOfAcceptanceOfDegree?: string;
    typeAndAmountOfFacilities?: string;
    academicCommitmentsFulfilled?: string;
    remainingAcademicCommitments?: string;
    requestedFacilitiesText?: string;
    requestedFacilitiesDescription?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    chartStatus?: number;
    bossStatus?: number;
    statusMeaning?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    selectedModuleIds?: string;
    applicationProcessRunSteps?: IApplicationProcessRunStepMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    educationalRecordQualificationText?: string;
    educationalRecordId?: number;
    personName?: string;
    personFamily?: string;
    personFullName?: string;
    personJobTitle?: string;
    personEmploymentTypeTitle?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    qualificationTitle?: string;
    qualificationId?: number;
}

export class ApplicationProcessMarineSuffix implements IApplicationProcessMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public description?: any,
        public jobAfterProcess?: string,
        public acceptedUniversityAndDegree?: string,
        public startStudyDate?: string,
        public graduateDateOfNewCourse?: string,
        public averagePointOfNewCourse?: string,
        public acceptedMajorAndField?: string,
        public haveUsedEducationalFacilities?: boolean,
        public haveUsedEducationalFacilitiesDescription?: string,
        public dateOfAcceptanceOfDegree?: string,
        public typeAndAmountOfFacilities?: string,
        public academicCommitmentsFulfilled?: string,
        public remainingAcademicCommitments?: string,
        public requestedFacilitiesText?: string,
        public requestedFacilitiesDescription?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public chartStatus?: number,
        public bossStatus?: number,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public selectedModuleIds?: string,
        public applicationProcessRunSteps?: IApplicationProcessRunStepMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public educationalRecordQualificationText?: string,
        public educationalRecordId?: number,
        public personName?: string,
        public personFamily?: string,
        public personFullName?: string,
        public personJobTitle?: string,
        public personEmploymentTypeTitle?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public qualificationTitle?: string,
        public qualificationId?: number
    ) {
        this.haveUsedEducationalFacilities = this.haveUsedEducationalFacilities || false;
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
