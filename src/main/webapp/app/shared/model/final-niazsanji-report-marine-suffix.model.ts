import { Moment } from 'moment';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model//final-niazsanji-report-person-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IPollMarineSuffix } from 'app/shared/model//poll-marine-suffix.model';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model//effectiveness-phase-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { NiazSanjiSource } from 'app/shared/model/enums/NiazSanjiSource';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';
import { Grade } from 'app/shared/model/enums/Grade';

export interface IFinalNiazsanjiReportMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    niazSanjiSource?: NiazSanjiSource;
    priceCost?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    statusMeaning?: string;
    runMonth?: number;
    runMonthPersian?: string;
    planningRunMonth?: number;
    planningRunMonthPersian?: string;
    finalizeCost?: number;
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    priority?: number;
    effectivenessPhaseAverage?: number;
    effectivenessPhaseGrade?: Grade;
    selectedEffectivenessPhaseLevel?: number;
    selectedEffectivenessPhaseLevelTitle?: string;
    currentEffectivenessPhaseLevel?: number;
    currentEffectivenessPhaseLevelTitle?: string;
    lastEffectivenessPhaseFinish?: Moment;
    lastEffectivenessPhaseFinishPersian?: string;
    finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    polls?: IPollMarineSuffix[];
    effectivenessPhases?: IEffectivenessPhaseMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    niazsanjiIntegrationNiazsanjiYear?: string;
    niazsanjiIntegrationId?: number;
    teacherFamily?: string;
    teacherName?: string;
    teacherFullName?: string;
    teacherId?: number;
    niazsanjiInputTitle?: string;
    niazsanjiInputId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    organizationChartTitle?: string;
    organizationChartFullTitle?: string;
    organizationChartRootTitle?: string;
    organizationChartId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleLearningTimeTheorical?: number;
    educationalModuleLearningTimePractical?: number;
    educationalModuleTotalTime?: number;
    educationalModuleLevelTitle?: string;
    skillLevelOfSkillTitle?: string;
    educationalModuleId?: number;
    mahiatCourseTitle?: string;
    mahiatCourseId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
}

export class FinalNiazsanjiReportMarineSuffix implements IFinalNiazsanjiReportMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public niazSanjiSource?: NiazSanjiSource,
        public priceCost?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public statusMeaning?: string,
        public runMonth?: number,
        public runMonthPersian?: string,
        public planningRunMonth?: number,
        public planningRunMonthPersian?: string,
        public finalizeCost?: number,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public priority?: number,
        public effectivenessPhaseAverage?: number,
        public effectivenessPhaseGrade?: Grade,
        public selectedEffectivenessPhaseLevel?: number,
        public selectedEffectivenessPhaseLevelTitle?: string,
        public currentEffectivenessPhaseLevel?: number,
        public currentEffectivenessPhaseLevelTitle?: string,
        public lastEffectivenessPhaseFinish?: Moment,
        public lastEffectivenessPhaseFinishPersian?: string,
        public finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public polls?: IPollMarineSuffix[],
        public effectivenessPhases?: IEffectivenessPhaseMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public niazsanjiIntegrationNiazsanjiYear?: string,
        public niazsanjiIntegrationId?: number,
        public teacherFamily?: string,
        public teacherName?: string,
        public teacherFullName?: string,
        public teacherId?: number,
        public niazsanjiInputTitle?: string,
        public niazsanjiInputId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public organizationChartTitle?: string,
        public organizationChartFullTitle?: string,
        public organizationChartRootTitle?: string,
        public organizationChartId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleLearningTimeTheorical?: number,
        public educationalModuleLearningTimePractical?: number,
        public educationalModuleTotalTime?: number,
        public educationalModuleLevelTitle?: string,
        public skillLevelOfSkillTitle?: string,
        public educationalModuleId?: number,
        public mahiatCourseTitle?: string,
        public mahiatCourseId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
