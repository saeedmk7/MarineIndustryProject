import { Moment } from 'moment';
import {NiazSanjiSource} from 'app/shared/model/enums/NiazSanjiSource';
import {INiazsanjiPersonGradeMarineSuffix} from "app/shared/model/niazsanji-person-grade-marine-suffix.model";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {IDocumentMarineSuffix} from "app/shared/model/document-marine-suffix.model";
import {ILevelFourEffectivenessMarineSuffix} from "app/shared/model/level-four-effectiveness-marine-suffix.model";
import {ILevelThreeEffectivenessMarineSuffix} from 'app/shared/model/level-three-effectiveness-marine-suffix.model';

export interface IFinalNiazsanjiReportPersonMarineSuffix {
    id?: number;
    niazSanjiSource?: NiazSanjiSource;
    priceCost?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    sourceId?: number;
    scoreBeforeTest?: number;
    scoreAfterTest?: number;
    averageScore?: number;
    levelOneScore?: number;
    levelThreeScore?: number;
    levelFourScore?: number;
    niazsanjiPersonGrades?: INiazsanjiPersonGradeMarineSuffix[];
    levelThreeEffectivenesses?: ILevelThreeEffectivenessMarineSuffix[];
    levelFourEffectivenesses?: ILevelFourEffectivenessMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    personFamily?: string;
    personName?: string;
    personFullName?: string;
    personId?: number;
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
    finalNiazsanjiReport?: IFinalNiazsanjiReportMarineSuffix;
}

export class FinalNiazsanjiReportPersonMarineSuffix implements IFinalNiazsanjiReportPersonMarineSuffix {
    constructor(
        public id?: number,
        public niazSanjiSource?: NiazSanjiSource,
        public priceCost?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public sourceId?: number,
        public scoreBeforeTest?: number,
        public scoreAfterTest?: number,
        public averageScore?: number,
        public levelOneScore?: number,
        public levelThreeScore?: number,
        public levelFourScore?: number,
        public niazsanjiPersonGrades?: INiazsanjiPersonGradeMarineSuffix[],
        public levelThreeEffectivenesses?: ILevelThreeEffectivenessMarineSuffix[],
        public levelFourEffectivenesses?: ILevelFourEffectivenessMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public personFamily?: string,
        public personName?: string,
        public personFullName?: string,
        public personId?: number,
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number,
        public finalNiazsanjiReport?: IFinalNiazsanjiReportMarineSuffix
    ) {
        this.archived = this.archived || false;
    }
}
