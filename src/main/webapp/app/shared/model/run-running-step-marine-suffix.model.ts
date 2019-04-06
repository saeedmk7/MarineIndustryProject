import { Moment } from 'moment';

export interface IRunRunningStepMarineSuffix {
    id?: number;
    description?: string;
    done?: boolean;
    doneUserLogin?: string;
    doneDate?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    runPhaseDescription?: string;
    runPhaseId?: number;
    runningStepTitle?: string;
    runningStepId?: number;
}

export class RunRunningStepMarineSuffix implements IRunRunningStepMarineSuffix {
    constructor(
        public id?: number,
        public description?: string,
        public done?: boolean,
        public doneUserLogin?: string,
        public doneDate?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public runPhaseDescription?: string,
        public runPhaseId?: number,
        public runningStepTitle?: string,
        public runningStepId?: number
    ) {
        this.done = this.done || false;
    }
}
