import { Moment } from 'moment';
import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model//niazsanji-person-grade-score-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {Grade} from 'app/shared/model/enums/Grade';

export interface INiazsanjiPersonGradeMarineSuffix {
    id?: number;
    title?: string;
    totalScore?: number;
    totalScorePercent?: number;
    grade?: Grade;
    evaluateDate?: string;
    year?: number;
    month?: number;
    monthPersian?:string;
    strength?: string;
    improvement?: string;
    whatDoYouDo?: string;
    fileDoc?: any;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    niazsanjiPersonGradeScores?: INiazsanjiPersonGradeScoreMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    finalNiazsanjiReportPersonPerson?: IPersonMarineSuffix;
    finalNiazsanjiReportPersonId?: number;
}

export class NiazsanjiPersonGradeMarineSuffix implements INiazsanjiPersonGradeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public totalScore?: number,
        public totalScorePercent?: number,
        public grade?: Grade,
        public evaluateDate?: string,
        public year?: number,
        public month?: number,
        public monthPersian?: string,
        public strength?: string,
        public improvement?: string,
        public whatDoYouDo?: string,
        public fileDoc?: any,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public niazsanjiPersonGradeScores?: INiazsanjiPersonGradeScoreMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public finalNiazsanjiReportPersonPerson?: IPersonMarineSuffix,
        public finalNiazsanjiReportPersonId?: number
    ) {}
}
