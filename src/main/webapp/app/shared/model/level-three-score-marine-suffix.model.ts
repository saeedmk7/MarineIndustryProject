import { Moment } from 'moment';
import {CriterionType} from "app/shared/model/enums/CriterionType";

export interface ILevelThreeScoreMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    levelThreeCriteriaTitle?: string;
    levelThreeCriteriaId?: number;
    levelThreeEffectivenessTitle?: string;
    levelThreeEffectivenessId?: number;
    levelThreeGradeTitle?: string;
    levelThreeGradeId?: number;
    levelThreeCriteriaDescription?: string;
    levelThreeBackgroundColor?: string;
    levelThreeColorText?: string;
    levelThreeCriteriaWeight?: number;
    levelThreeCriteriaGroupId?: number;
    levelThreeCriteriaGroupTitle?: string;
    isNew?: boolean;
}

export class LevelThreeScoreMarineSuffix implements ILevelThreeScoreMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public levelThreeCriteriaTitle?: string,
        public levelThreeCriteriaId?: number,
        public levelThreeEffectivenessTitle?: string,
        public levelThreeEffectivenessId?: number,
        public levelThreeGradeTitle?: string,
        public levelThreeGradeId?: number,
        public levelThreeCriteriaDescription?: string,
        public levelThreeBackgroundColor?: string,
        public levelThreeColorText?: string,
        public levelThreeCriteriaWeight?: number,
        public levelThreeCriteriaGroupId?: number,
        public levelThreeCriteriaGroupTitle?: string,
        public isNew?: boolean
    ) {}
}
