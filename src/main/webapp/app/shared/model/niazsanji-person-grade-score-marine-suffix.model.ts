import { Moment } from 'moment';
import {CriterionType} from "app/shared/model/enums/CriterionType";

export interface INiazsanjiPersonGradeScoreMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    niazsanjiPersonCriteriaTitle?: string;
    niazsanjiPersonCriteriaId?: number;
    niazsanjiPersonGradeTitle?: string;
    niazsanjiPersonGradeId?: number;
    niazsanjiPersonCriteriaDescription?: string;
    niazsanjiPersonBackgroundColor?: string;
    niazsanjiPersonColorText?: string;
    niazsanjiPersonCriteriaWeight?: number;
    niazsanjiPersonCriterionType?: CriterionType;
    isNew?: boolean;
}

export class NiazsanjiPersonGradeScoreMarineSuffix implements INiazsanjiPersonGradeScoreMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public niazsanjiPersonCriteriaTitle?: string,
        public niazsanjiPersonCriteriaId?: number,
        public niazsanjiPersonGradeTitle?: string,
        public niazsanjiPersonGradeId?: number,
        public niazsanjiPersonCriteriaDescription?: string,
        public niazsanjiPersonBackgroundColor?: string,
        public niazsanjiPersonColorText?: string,
        public niazsanjiPersonCriteriaWeight?: number,
        public niazsanjiPersonCriterionType?: CriterionType,
        public isNew?: boolean
    ) {}
}
