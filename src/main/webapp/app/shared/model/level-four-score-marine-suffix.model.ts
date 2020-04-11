import { Moment } from 'moment';

export interface ILevelFourScoreMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    levelFourCriteriaTitle?: string;
    levelFourCriteriaId?: number;
    levelFourEffectivenessTitle?: string;
    levelFourEffectivenessId?: number;
    levelFourGradeTitle?: string;
    levelFourGradeId?: number;
    levelFourCriteriaDescription?: string;
    levelFourBackgroundColor?: string;
    levelFourColorText?: string;
    levelFourCriteriaWeight?: number;
    isNew?: boolean;
}

export class LevelFourScoreMarineSuffix implements ILevelFourScoreMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public levelFourCriteriaTitle?: string,
        public levelFourCriteriaId?: number,
        public levelFourEffectivenessTitle?: string,
        public levelFourEffectivenessId?: number,
        public levelFourGradeTitle?: string,
        public levelFourGradeId?: number,
        public levelFourCriteriaDescription?: string,
        public levelFourBackgroundColor?: string,
        public levelFourColorText?: string,
        public levelFourCriteriaWeight?: number,
        public levelFourCriteriaGroupId?: number,
        public levelFourCriteriaGroupTitle?: string,
        public isNew?: boolean
    ) {}
}
