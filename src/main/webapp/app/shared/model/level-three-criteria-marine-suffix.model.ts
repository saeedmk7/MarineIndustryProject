import { Moment } from 'moment';
import { ILevelThreeScoreMarineSuffix } from 'app/shared/model//level-three-score-marine-suffix.model';

export interface ILevelThreeCriteriaMarineSuffix {
    id?: number;
    title?: string;
    displayOrder?: number;
    weight?: number;
    secondWeight?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    backgroundColor?: string;
    colorText?: string;
    levelThreeScores?: ILevelThreeScoreMarineSuffix[];
    mahiatCourseTitle?: string;
    mahiatCourseId?: number;
    levelThreeCriteriaGroupTitle?: string;
    levelThreeCriteriaGroupId?: number;
}

export class LevelThreeCriteriaMarineSuffix implements ILevelThreeCriteriaMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public displayOrder?: number,
        public weight?: number,
        public secondWeight?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public backgroundColor?: string,
        public colorText?: string,
        public levelThreeScores?: ILevelThreeScoreMarineSuffix[],
        public mahiatCourseTitle?: string,
        public mahiatCourseId?: number,
        public levelThreeCriteriaGroupTitle?: string,
        public levelThreeCriteriaGroupId?: number
    ) {}
}
