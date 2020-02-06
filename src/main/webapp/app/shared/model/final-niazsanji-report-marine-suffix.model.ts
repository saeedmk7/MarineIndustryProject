import { Moment } from 'moment';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model//final-niazsanji-report-person-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IPollMarineSuffix } from 'app/shared/model//poll-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {NiazSanjiSource} from 'app/shared/model/enums/NiazSanjiSource';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';

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
    runMonth?: number;
    planningRunMonth?: number;
    finalizeCost?: number;
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    priority?: number;
    finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    runPhases?: IRunPhaseMarineSuffix[];
    polls?: IPollMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    niazsanjiIntegrationNiazsanjiYear?: string;
    niazsanjiIntegrationId?: number;
    teacherFamily?: string;
    teacherId?: number;
    niazsanjiInputTitle?: string;
    niazsanjiInputId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
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
        public runMonth?: number,
        public planningRunMonth?: number,
        public finalizeCost?: number,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public priority?: number,
        public finalNiazsanjiReportPeople?: IFinalNiazsanjiReportPersonMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public runPhases?: IRunPhaseMarineSuffix[],
        public polls?: IPollMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public niazsanjiIntegrationNiazsanjiYear?: string,
        public niazsanjiIntegrationId?: number,
        public teacherFamily?: string,
        public teacherId?: number,
        public niazsanjiInputTitle?: string,
        public niazsanjiInputId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
