import { Moment } from 'moment';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model//pre-job-niazsanji-competency-marine-suffix.model';

export interface IFixCompetencyDeficiencyMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    displayOrder?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[];
}

export class FixCompetencyDeficiencyMarineSuffix implements IFixCompetencyDeficiencyMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public displayOrder?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[]
    ) {}
}
