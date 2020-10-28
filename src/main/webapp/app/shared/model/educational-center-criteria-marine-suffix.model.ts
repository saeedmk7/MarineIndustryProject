import { Moment } from 'moment';
import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model//educational-center-grade-score-marine-suffix.model';
import { IKeyValue } from 'app/shared/model/custom/key-value';

export interface IEducationalCenterCriteriaMarineSuffix {
    id?: number;
    group?: string;
    title?: string;
    displayOrder?: number;
    weight?: number;
    description?: string;
    peopleCount?: number;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalCenterGradeScores?: IEducationalCenterGradeScoreMarineSuffix[];
    score?: IKeyValue;
    educationalCenterGroupTitle?: string;
    educationalCenterGroupId?: number;
}

export class EducationalCenterCriteriaMarineSuffix implements IEducationalCenterCriteriaMarineSuffix {
    constructor(
        public id?: number,
        public group?: string,
        public title?: string,
        public displayOrder?: number,
        public weight?: number,
        public description?: string,
        public peopleCount?: number,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenterGradeScores?: IEducationalCenterGradeScoreMarineSuffix[],
        public score?: IKeyValue,
        public educationalCenterGroupTitle?: string,
        public educationalCenterGroupId?: number
    ) {}
}
