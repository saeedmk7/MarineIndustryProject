import { Moment } from 'moment';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model//level-three-criteria-marine-suffix.model';

export interface ILevelThreeCriteriaGroupMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    levelThreeCriteria?: ILevelThreeCriteriaMarineSuffix[];
}

export class LevelThreeCriteriaGroupMarineSuffix implements ILevelThreeCriteriaGroupMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public levelThreeCriteria?: ILevelThreeCriteriaMarineSuffix[]
    ) {}
}
