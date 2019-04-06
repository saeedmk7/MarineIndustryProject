import { Moment } from 'moment';
import { IRunRunningStepMarineSuffix } from 'app/shared/model//run-running-step-marine-suffix.model';

export interface IRunningStepMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    stepNumber?: number;
    stepRequired?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    runRunningSteps?: IRunRunningStepMarineSuffix[];
}

export class RunningStepMarineSuffix implements IRunningStepMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public stepNumber?: number,
        public stepRequired?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public runRunningSteps?: IRunRunningStepMarineSuffix[]
    ) {
        this.stepRequired = this.stepRequired || false;
        this.archived = this.archived || false;
    }
}
