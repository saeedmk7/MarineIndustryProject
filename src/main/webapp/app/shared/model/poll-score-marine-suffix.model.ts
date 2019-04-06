import { Moment } from 'moment';

export interface IPollScoreMarineSuffix {
    id?: number;
    recommendation?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    pollItemTitle?: string;
    pollItemId?: number;
    scoreItemTitle?: string;
    scoreItemId?: number;
    pollMoreRecommendation?: string;
    pollId?: number;
    personFamily?: string;
    personId?: number;
}

export class PollScoreMarineSuffix implements IPollScoreMarineSuffix {
    constructor(
        public id?: number,
        public recommendation?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public pollItemTitle?: string,
        public pollItemId?: number,
        public scoreItemTitle?: string,
        public scoreItemId?: number,
        public pollMoreRecommendation?: string,
        public pollId?: number,
        public personFamily?: string,
        public personId?: number
    ) {}
}
