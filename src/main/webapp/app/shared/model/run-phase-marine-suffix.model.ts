import { Moment } from 'moment';
import { IRunRunningStepMarineSuffix } from 'app/shared/model//run-running-step-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';

export interface IRunPhaseMarineSuffix {
    id?: number;
    description?: string;
    finalizeCost?: number;
    stepNumber?: number;
    done?: boolean;
    doneUserLogin?: string;
    doneDate?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    runRunningSteps?: IRunRunningStepMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    people?: IPersonMarineSuffix[];
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
}

export class RunPhaseMarineSuffix implements IRunPhaseMarineSuffix {
    constructor(
        public id?: number,
        public description?: string,
        public finalizeCost?: number,
        public stepNumber?: number,
        public done?: boolean,
        public doneUserLogin?: string,
        public doneDate?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public runRunningSteps?: IRunRunningStepMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public people?: IPersonMarineSuffix[],
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number
    ) {
        this.done = this.done || false;
        this.archived = this.archived || false;
    }
}
