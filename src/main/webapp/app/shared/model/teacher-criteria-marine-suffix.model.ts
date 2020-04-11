import { Moment } from 'moment';
import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model//teacher-grade-score-marine-suffix.model';

export interface ITeacherCriteriaMarineSuffix {
    id?: number;
    group?: string;
    title?: string;
    displayOrder?: number;
    weight?: number;
    secondWeight?: number;
    description?: string;
    peopleCount?: number;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    teacherGradeScores?: ITeacherGradeScoreMarineSuffix[];
}

export class TeacherCriteriaMarineSuffix implements ITeacherCriteriaMarineSuffix {
    constructor(
        public id?: number,
        public group?: string,
        public title?: string,
        public displayOrder?: number,
        public weight?: number,
        public secondWeight?: number,
        public description?: string,
        public peopleCount?: number,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public teacherGradeScores?: ITeacherGradeScoreMarineSuffix[]
    ) {}
}
