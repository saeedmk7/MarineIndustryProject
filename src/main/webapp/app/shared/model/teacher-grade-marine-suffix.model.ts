import { Moment } from 'moment';
import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model//teacher-grade-score-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { Grade } from 'app/shared/model/enums/Grade';

export interface ITeacherGradeMarineSuffix {
    id?: number;
    title?: string;
    version?: string;
    totalScore?: number;
    totalScorePercent?: number;
    grade?: Grade;
    evaluateDate?: string;
    year?: number;
    month?: number;
    monthPersian?: string;
    fileDoc?: any;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    teacherGradeScores?: ITeacherGradeScoreMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    teacherName?: string;
    teacherFamily?: string;
    teacherId?: number;
    teacherCriteriaGroupTitle?: string;
    teacherCriteriaGroupId?: number;
}

export class TeacherGradeMarineSuffix implements ITeacherGradeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public version?: string,
        public totalScore?: number,
        public totalScorePercent?: number,
        public grade?: Grade,
        public evaluateDate?: string,
        public year?: number,
        public month?: number,
        public monthPersian?: string,
        public fileDoc?: any,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public teacherGradeScores?: ITeacherGradeScoreMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public teacherName?: string,
        public teacherFamily?: string,
        public teacherId?: number,
        public teacherCriteriaGroupTitle?: string,
        public teacherCriteriaGroupId?: number
    ) {}
}
