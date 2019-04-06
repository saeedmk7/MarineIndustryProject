import { Moment } from 'moment';
import { IPollScoreMarineSuffix } from 'app/shared/model//poll-score-marine-suffix.model';

export interface IPollItemMarineSuffix {
    id?: number;
    title?: string;
    displayOrder?: number;
    coefficient?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    pollScores?: IPollScoreMarineSuffix[];
    criterionTitle?: string;
    criterionId?: number;
}

export class PollItemMarineSuffix implements IPollItemMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public displayOrder?: number,
        public coefficient?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public pollScores?: IPollScoreMarineSuffix[],
        public criterionTitle?: string,
        public criterionId?: number
    ) {}
}
