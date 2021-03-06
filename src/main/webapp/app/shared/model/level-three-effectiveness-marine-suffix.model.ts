import { Moment } from 'moment';
import { ILevelThreeScoreMarineSuffix } from 'app/shared/model//level-three-score-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {Grade} from "app/shared/model/enums/Grade";

export interface ILevelThreeEffectivenessMarineSuffix {
    id?: number;
    title?: string;
    totalScore?: number;
    totalScorePercent?: number;
    grade?: Grade;
    evaluateDate?: string;
    year?: number;
    month?: number;
    strength?: string;
    improvement?: string;
    whatDoYouDo?: string;
    fileDoc?: any;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    levelThreeScores?: ILevelThreeScoreMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    finalNiazsanjiReportPersonDescription?: string;
    finalNiazsanjiReportPersonId?: number;
}

export class LevelThreeEffectivenessMarineSuffix implements ILevelThreeEffectivenessMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public totalScore?: number,
        public totalScorePercent?: number,
        public grade?: Grade,
        public evaluateDate?: string,
        public year?: number,
        public month?: number,
        public strength?: string,
        public improvement?: string,
        public whatDoYouDo?: string,
        public fileDoc?: any,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public levelThreeScores?: ILevelThreeScoreMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public finalNiazsanjiReportPersonDescription?: string,
        public finalNiazsanjiReportPersonId?: number
    ) {}
}
