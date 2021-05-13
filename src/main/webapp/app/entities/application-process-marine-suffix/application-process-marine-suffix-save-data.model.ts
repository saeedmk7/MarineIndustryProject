import { IRunPhaseSaveDataItemModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data-item.model';
import { IApplicationProcessSaveDataItemModel } from 'app/entities/application-process-marine-suffix/application-process-marine-suffix-save-data-item.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';

export interface IApplicationProcessSaveDataModel {
    applicationProcessId?: number;
    description?: string;
    conversion?: string;
    stepNumber?: number;
    chartStatus?: number;
    bossStatus?: number;
    requestStatus?: RequestStatus;
    personId?: number;
    organizationChartId?: number;
    selectedModuleIds?: string;
    qualificationId?: number;
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

    applicationProcessSaveDataItemModels?: IApplicationProcessSaveDataItemModel[];
}

export class ApplicationProcessSaveDataModel implements IApplicationProcessSaveDataModel {
    constructor(
        public applicationProcessId?: number,
        public description?: string,
        public conversion?: string,
        public stepNumber?: number,
        public chartStatus?: number,
        public bossStatus?: number,
        public requestStatus?: RequestStatus,
        public personId?: number,
        public organizationChartId?: number,
        public selectedModuleIds?: string,
        public qualificationId?: number,
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
        public applicationProcessSaveDataItemModels?: IApplicationProcessSaveDataItemModel[]
    ) {}
}
