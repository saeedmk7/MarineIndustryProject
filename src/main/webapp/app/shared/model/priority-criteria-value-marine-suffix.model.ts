import { Moment } from 'moment';

export interface IPriorityCriteriaValueMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    priorityCriteriaTitle?: string;
    priorityCriteriaId?: number;
    preJobNiazsanjiCompetencyTitle?: string;
    preJobNiazsanjiCompetencyId?: number;
}

export class PriorityCriteriaValueMarineSuffix implements IPriorityCriteriaValueMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public priorityCriteriaTitle?: string,
        public priorityCriteriaId?: number,
        public preJobNiazsanjiCompetencyTitle?: string,
        public preJobNiazsanjiCompetencyId?: number
    ) {}
}
