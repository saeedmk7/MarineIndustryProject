import { Moment } from 'moment';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model//educational-center-grade-marine-suffix.model';

export interface IEducationalCenterServiceMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalCenterGrades?: IEducationalCenterGradeMarineSuffix[];
}

export class EducationalCenterServiceMarineSuffix implements IEducationalCenterServiceMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenterGrades?: IEducationalCenterGradeMarineSuffix[]
    ) {}
}
