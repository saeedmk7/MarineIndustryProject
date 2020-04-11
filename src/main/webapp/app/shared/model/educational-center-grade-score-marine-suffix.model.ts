import { Moment } from 'moment';

export interface IEducationalCenterGradeScoreMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalCenterCriteriaTitle?: string;
    educationalCenterCriteriaDescription?: string;
    educationalCenterCriteriaId?: number;
    educationalCenterGradeTitle?: string;
    educationalCenterGradeId?: number;
    isNew?: boolean;
}

export class EducationalCenterGradeScoreMarineSuffix implements IEducationalCenterGradeScoreMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenterCriteriaTitle?: string,
        public educationalCenterCriteriaDescription?: string,
        public educationalCenterCriteriaId?: number,
        public educationalCenterGradeTitle?: string,
        public educationalCenterGradeId?: number,
        public isNew?: boolean
    ) {}
}
