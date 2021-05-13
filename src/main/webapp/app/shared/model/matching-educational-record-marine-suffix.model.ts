import { Moment } from 'moment';
import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model//matching-run-running-step-marine-suffix.model';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model//skillable-level-of-skill-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IMatchingEducationalRecordMarineSuffix {
    id?: number;
    code?: string;
    description?: any;
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
    matchingRunRunningSteps?: IMatchingRunRunningStepMarineSuffix[];
    skillableLevelOfSkills?: ISkillableLevelOfSkillMarineSuffix[];
    skillableLevelOfSkill?: ISkillableLevelOfSkillMarineSuffix;
    documents?: IDocumentMarineSuffix[];
    personName?: string;
    personFamily?: string;
    personFullName?: string;
    personJobTitle?: string;
    personEmploymentTypeTitle?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    selectedModuleIds?: string;
}

export class MatchingEducationalRecordMarineSuffix implements IMatchingEducationalRecordMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public chartStatus?: number,
        public bossStatus?: number,
        public statusMeaning?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public matchingRunRunningSteps?: IMatchingRunRunningStepMarineSuffix[],
        public skillableLevelOfSkills?: ISkillableLevelOfSkillMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public personName?: string,
        public personFamily?: string,
        public personFullName?: string,
        public personJobTitle?: string,
        public personEmploymentTypeTitle?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public selectedModuleIds?: string
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
