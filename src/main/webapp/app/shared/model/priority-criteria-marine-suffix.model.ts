import { Moment } from 'moment';
import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model//priority-criteria-value-marine-suffix.model';

export interface IPriorityCriteriaMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    displayOrder?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    priorityCriteriaValues?: IPriorityCriteriaValueMarineSuffix[];
}

export class PriorityCriteriaMarineSuffix implements IPriorityCriteriaMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public displayOrder?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public priorityCriteriaValues?: IPriorityCriteriaValueMarineSuffix[]
    ) {}
}
