import { Moment } from 'moment';
import { IPollScoreMarineSuffix } from 'app/shared/model//poll-score-marine-suffix.model';

export interface IScoreItemMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    score?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    pollScores?: IPollScoreMarineSuffix[];
}

export class ScoreItemMarineSuffix implements IScoreItemMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public score?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public pollScores?: IPollScoreMarineSuffix[]
    ) {}
}
