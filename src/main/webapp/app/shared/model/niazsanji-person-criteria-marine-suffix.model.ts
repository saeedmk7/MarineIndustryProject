import { Moment } from 'moment';
import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model//niazsanji-person-grade-score-marine-suffix.model';
import {CriterionType} from "app/shared/model/enums/CriterionType";

export interface INiazsanjiPersonCriteriaMarineSuffix {
    id?: number;
    criterionType?: CriterionType;
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
    niazsanjiPersonGradeScores?: INiazsanjiPersonGradeScoreMarineSuffix[];
}

export class NiazsanjiPersonCriteriaMarineSuffix implements INiazsanjiPersonCriteriaMarineSuffix {
    constructor(
        public id?: number,
        public criterionType?: CriterionType,
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
        public niazsanjiPersonGradeScores?: INiazsanjiPersonGradeScoreMarineSuffix[]
    ) {}
}
