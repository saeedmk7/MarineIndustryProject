import { Moment } from 'moment';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model//pre-job-niazsanji-competency-marine-suffix.model';

export interface ICompetencyMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    displayOrder?: number;
    competencyType?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    selectedItems?: any[];
    preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[];
}

export class CompetencyMarineSuffix implements ICompetencyMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public displayOrder?: number,
        public competencyType?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[],
        public selectedItems?: any[]
    ) {}
}
