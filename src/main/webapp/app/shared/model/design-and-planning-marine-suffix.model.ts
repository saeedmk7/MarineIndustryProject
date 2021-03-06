import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface IDesignAndPlanningMarineSuffix {
    id?: number;
    directCost?: number;
    directCostDescription?: string;
    undirectCost?: number;
    undirectCostDescription?: string;
    step?: number;
    finished?: boolean;
    finishedUserLogin?: string;
    finishedDate?: Moment;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    runMonth?: number;
    guid?: string;
    runMonthName?: string;
    hasImportantMessage?: boolean;
    people?: IPersonMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    organizationChartTitle?: string;
    organizationChartId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    finalNiazsanjiReportNiazsanjiYear?: number;
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
    mahiatCourseTitle?: string;
    mahiatCourseId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    teachTypeTitle?: string;
    teachTypeId?: number;
    courseLocationTitle?: string;
    courseLocationId?: number;
    conditionsOfParticipantTitle?: string;
    conditionsOfParticipantId?: number;
    effectivenessLevelTitle?: string;
    effectivenessLevelId?: number;
    toolsAndFacilityTitle?: string;
    toolsAndFacilityId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
    teachTechniqueTitle?: string;
    teachTechniqueId?: number;
    effectivenessIndexTitle?: string;
    effectivenessIndexId?: number;
    learningTimePractical?: number;
    learningTimeTheorical?: number;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
}

export class DesignAndPlanningMarineSuffix implements IDesignAndPlanningMarineSuffix {
    constructor(
        public id?: number,
        public directCost?: number,
        public directCostDescription?: string,
        public undirectCost?: number,
        public undirectCostDescription?: string,
        public step?: number,
        public finished?: boolean,
        public finishedUserLogin?: string,
        public finishedDate?: Moment,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public runMonth?: number,
        public runMonthName?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public people?: IPersonMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleId?: number,
        public finalNiazsanjiReportNiazsanjiYear?: number,
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number,
        public mahiatCourseTitle?: string,
        public mahiatCourseId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public teachTypeTitle?: string,
        public teachTypeId?: number,
        public courseLocationTitle?: string,
        public courseLocationId?: number,
        public conditionsOfParticipantTitle?: string,
        public conditionsOfParticipantId?: number,
        public effectivenessLevelTitle?: string,
        public effectivenessLevelId?: number,
        public toolsAndFacilityTitle?: string,
        public toolsAndFacilityId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number,
        public teachTechniqueTitle?: string,
        public teachTechniqueId?: number,
        public effectivenessIndexTitle?: string,
        public effectivenessIndexId?: number,
        public learningTimeTheorical?: number,
        public learningTimePractical?: number,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string
    ) {
        this.finished = this.finished || false;
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
