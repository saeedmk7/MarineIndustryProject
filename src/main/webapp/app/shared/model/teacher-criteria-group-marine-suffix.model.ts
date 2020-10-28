import { Moment } from 'moment';
import { ITeacherGradeMarineSuffix } from 'app/shared/model//teacher-grade-marine-suffix.model';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model//teacher-criteria-marine-suffix.model';

export interface ITeacherCriteriaGroupMarineSuffix {
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
    teacherGrades?: ITeacherGradeMarineSuffix[];
    teacherCriteria?: ITeacherCriteriaMarineSuffix[];
}

export class TeacherCriteriaGroupMarineSuffix implements ITeacherCriteriaGroupMarineSuffix {
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
        public teacherGrades?: ITeacherGradeMarineSuffix[],
        public teacherCriteria?: ITeacherCriteriaMarineSuffix[]
    ) {}
}
