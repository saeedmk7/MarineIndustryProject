import { Moment } from 'moment';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { RequestNiazsanjiType } from 'app/shared/model/enums/RequestNiazsanjiType';
import { EducationalModuleType } from 'app/shared/model/enums/EducationalModuleType';

export interface INiazsanjiIntegrationMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    requestNiazsanjiType?: RequestNiazsanjiType;
    priority?: number;
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    prioritizeRequestNiazsanjiCode?: string;
    prioritizeRequestNiazsanjiId?: number;
    costEducationalModule?: number;
    educationalModuleType?: EducationalModuleType;
    niazsanjiInputTitle?: string;
    courseTypeTitle?: string;
    educationalModuleId?: number;
    educationalModuleCode?: string;
    educationalModuleTitle?: string;
    personFullName?: string;
    personFamily?: string;
    personName?: string;
    personJobTitle?: string;
    organizationChartId?: number;
    organizationChartTitle?: string;
    organizationChartFullTitle?: string;
    organizationChartRootTitle?: string;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
}

export class NiazsanjiIntegrationMarineSuffix implements INiazsanjiIntegrationMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public requestNiazsanjiType?: RequestNiazsanjiType,
        public priority?: number,
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public prioritizeRequestNiazsanjiCode?: string,
        public prioritizeRequestNiazsanjiId?: number,
        public costEducationalModule?: number,
        public educationalModuleType?: EducationalModuleType,
        public niazsanjiInputTitle?: string,
        public courseTypeTitle?: string,
        public educationalModuleId?: number,
        public educationalModuleCode?: string,
        public educationalModuleTitle?: string,
        public personFullName?: string,
        public personFamily?: string,
        public personName?: string,
        public personJobTitle?: string,
        public organizationChartId?: number,
        public organizationChartTitle?: string,
        public organizationChartFullTitle?: string,
        public organizationChartRootTitle?: string,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
