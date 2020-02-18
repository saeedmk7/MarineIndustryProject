import { Moment } from 'moment';
import { IRunRunningStepMarineSuffix } from 'app/shared/model//run-running-step-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import {NiazSanjiSource} from "app/shared/model/enums/NiazSanjiSource";

export interface IRunPhaseMarineSuffix {
    id?: number;
    description?: string;
    finalizeCost?: number;
    priceCost?: number;
    stepNumber?: number;
    done?: boolean;
    doneUserLogin?: string;
    doneDate?: Moment;
    runMonth?: number;
    runMonthPersian?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    guid?: string;
    finishDate?: string;
    hasImportantMessage?: boolean;
    conversation?: any;
    comment?: string;
    runRunningSteps?: IRunRunningStepMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    people?: IPersonMarineSuffix[];
    organizationChartTitle?: string;
    organizationChartId?: number;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
    niazsanjiYear?: number;
    niazSanjiSource?: NiazSanjiSource;
    courseTypeId?: number;
    courseTypeTitle?: string;
}

export class RunPhaseMarineSuffix implements IRunPhaseMarineSuffix {
    constructor(
        public id?: number,
        public description?: string,
        public finalizeCost?: number,
        public priceCost?: number,
        public stepNumber?: number,
        public done?: boolean,
        public doneUserLogin?: string,
        public doneDate?: Moment,
        public runMonth?: number,
        public runMonthPersian?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public guid?: string,
        public finishDate?: string,
        public hasImportantMessage?: boolean,
        public conversation?: any,
        public comment?: string,
        public runRunningSteps?: IRunRunningStepMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public people?: IPersonMarineSuffix[],
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleId?: number,
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number,
        public niazsanjiYear?: number,
        public niazSanjiSource?: NiazSanjiSource,
        public courseTypeId?: number,
        public courseTypeTitle?: string
    ) {
        this.done = this.done || false;
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
