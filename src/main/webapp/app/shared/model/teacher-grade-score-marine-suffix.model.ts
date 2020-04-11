import { Moment } from 'moment';

export interface ITeacherGradeScoreMarineSuffix {
    id?: number;
    score?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    teacherCriteriaTitle?: string;
    teacherCriteriaDescription?: string;
    teacherCriteriaWeight?: number;
    teacherCriteriaId?: number;
    teacherGradeTitle?: string;
    teacherGradeId?: number;
    isNew?: boolean;
}

export class TeacherGradeScoreMarineSuffix implements ITeacherGradeScoreMarineSuffix {
    constructor(
        public id?: number,
        public score?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public teacherCriteriaTitle?: string,
        public teacherCriteriaDescription?: string,
        public teacherCriteriaWeight?: number,
        public teacherCriteriaId?: number,
        public teacherGradeTitle?: string,
        public teacherGradeId?: number,
        public isNew?: boolean
    ) {}
}
