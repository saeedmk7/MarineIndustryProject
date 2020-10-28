import { Moment } from 'moment';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model//educational-center-grade-marine-suffix.model';
import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model//educational-center-criteria-marine-suffix.model';

export interface IEducationalCenterGroupMarineSuffix {
    id?: number;
    title?: string;
    displayOrder?: number;
    weight?: number;
    description?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalCenterGrades?: IEducationalCenterGradeMarineSuffix[];
    educationalCenterCriteria?: IEducationalCenterCriteriaMarineSuffix[];
}

export class EducationalCenterGroupMarineSuffix implements IEducationalCenterGroupMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public displayOrder?: number,
        public weight?: number,
        public description?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenterGrades?: IEducationalCenterGradeMarineSuffix[],
        public educationalCenterCriteria?: IEducationalCenterCriteriaMarineSuffix[]
    ) {}
}
