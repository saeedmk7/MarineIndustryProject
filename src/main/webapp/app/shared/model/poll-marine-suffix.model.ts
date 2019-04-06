import { Moment } from 'moment';
import { IPollScoreMarineSuffix } from 'app/shared/model//poll-score-marine-suffix.model';

export interface IPollMarineSuffix {
    id?: number;
    moreRecommendation?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    pollScores?: IPollScoreMarineSuffix[];
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
}

export class PollMarineSuffix implements IPollMarineSuffix {
    constructor(
        public id?: number,
        public moreRecommendation?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public pollScores?: IPollScoreMarineSuffix[],
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number
    ) {
        this.archived = this.archived || false;
    }
}
