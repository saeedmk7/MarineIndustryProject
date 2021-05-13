import { Moment } from 'moment';
import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model//matching-run-running-step-marine-suffix.model';

export interface IMatchingRunningStepMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    stepNumber?: number;
    stepRequired?: boolean;
    fileDocRequired?: boolean;
    colorText?: string;
    isHeader?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    fileDoc?: any;
    matchingRunRunningSteps?: IMatchingRunRunningStepMarineSuffix[];
}

export class MatchingRunningStepMarineSuffix implements IMatchingRunningStepMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public stepNumber?: number,
        public stepRequired?: boolean,
        public fileDocRequired?: boolean,
        public colorText?: string,
        public isHeader?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public fileDoc?: any,
        public matchingRunRunningSteps?: IMatchingRunRunningStepMarineSuffix[]
    ) {
        this.stepRequired = this.stepRequired || false;
        this.fileDocRequired = this.fileDocRequired || false;
        this.isHeader = this.isHeader || false;
        this.archived = this.archived || false;
    }
}
