import { Moment } from 'moment';

export interface IApplicationProcessRunStepMarineSuffix {
    id?: number;
    fileDoc?: any;
    description?: string;
    done?: boolean;
    doneUserLogin?: string;
    doneDate?: Moment;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    applicationProcessCode?: string;
    applicationProcessId?: number;
    applicationProcessStepTitle?: string;
    applicationProcessStepId?: number;
}

export class ApplicationProcessRunStepMarineSuffix implements IApplicationProcessRunStepMarineSuffix {
    constructor(
        public id?: number,
        public fileDoc?: any,
        public description?: string,
        public done?: boolean,
        public doneUserLogin?: string,
        public doneDate?: Moment,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public applicationProcessCode?: string,
        public applicationProcessId?: number,
        public applicationProcessStepTitle?: string,
        public applicationProcessStepId?: number
    ) {
        this.done = this.done || false;
    }
}
