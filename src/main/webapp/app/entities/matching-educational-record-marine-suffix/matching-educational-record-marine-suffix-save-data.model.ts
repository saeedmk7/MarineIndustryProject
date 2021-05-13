import { IRunPhaseSaveDataItemModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data-item.model';
import { IMatchingEducationalRecordSaveDataItemModel } from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix-save-data-item.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';

export interface IMatchingEducationalRecordSaveDataModel {
    matchingEducationalRecordId?: number;
    description?: string;
    conversion?: string;
    stepNumber?: number;
    chartStatus?: number;
    bossStatus?: number;
    requestStatus?: RequestStatus;
    personId?: number;
    organizationChartId?: number;
    selectedModuleIds?: string;
    skillableLevelOfSkills?: ISkillableLevelOfSkillMarineSuffix[];
    matchingEducationalRecordSaveDataItemModels?: IMatchingEducationalRecordSaveDataItemModel[];
}

export class MatchingEducationalRecordSaveDataModel implements IMatchingEducationalRecordSaveDataModel {
    constructor(
        public matchingEducationalRecordId?: number,
        public description?: string,
        public conversion?: string,
        public stepNumber?: number,
        public chartStatus?: number,
        public bossStatus?: number,
        public requestStatus?: RequestStatus,
        public personId?: number,
        public organizationChartId?: number,
        public selectedModuleIds?: string,
        public skillableLevelOfSkills?: ISkillableLevelOfSkillMarineSuffix[],
        public matchingEducationalRecordSaveDataItemModels?: IMatchingEducationalRecordSaveDataItemModel[]
    ) {}
}
