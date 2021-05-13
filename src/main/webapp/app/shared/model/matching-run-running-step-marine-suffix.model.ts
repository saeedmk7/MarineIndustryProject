import { Moment } from 'moment';

export interface IMatchingRunRunningStepMarineSuffix {
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
    matchingEducationalRecordCode?: string;
    matchingEducationalRecordId?: number;
    matchingRunningStepTitle?: string;
    matchingRunningStepId?: number;
}

export class MatchingRunRunningStepMarineSuffix implements IMatchingRunRunningStepMarineSuffix {
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
        public matchingEducationalRecordCode?: string,
        public matchingEducationalRecordId?: number,
        public matchingRunningStepTitle?: string,
        public matchingRunningStepId?: number
    ) {
        this.done = this.done || false;
    }
}
