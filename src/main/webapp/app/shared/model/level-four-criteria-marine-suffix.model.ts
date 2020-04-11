import { Moment } from 'moment';
import { ILevelFourScoreMarineSuffix } from 'app/shared/model//level-four-score-marine-suffix.model';

export interface ILevelFourCriteriaMarineSuffix {
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
    levelFourScores?: ILevelFourScoreMarineSuffix[];
}

export class LevelFourCriteriaMarineSuffix implements ILevelFourCriteriaMarineSuffix {
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
        public levelFourScores?: ILevelFourScoreMarineSuffix[]
    ) {}
}
