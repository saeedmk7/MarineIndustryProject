import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {IEffectivenessPhaseLevelMarineSuffix} from "app/shared/model/effectiveness-phase-level-marine-suffix.model";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";

export interface IEffectivenessPhaseMarineSuffix {
    id?: number;
    finishPhaseDate?: Moment;
    startPhaseDate?: Moment;
    firstScore?: number;
    secondScore?: number;
    finalScore?: number;
    weightedPoints?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    status?: number;
    startPhaseUserLogin?: string;
    finishPhaseUserLogin?: string;
    documents?: IDocumentMarineSuffix[];
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
    finalNiazsanjiReport?: IFinalNiazsanjiReportMarineSuffix;
    effectivenessPhaseLevelTitle?: string;
    effectivenessPhaseLevelId?: number;
    effectivenessPhaseLevel?: IEffectivenessPhaseLevelMarineSuffix;
    peopleFinishCount?: number;
    peopleCount?: number;
}

export class EffectivenessPhaseMarineSuffix implements IEffectivenessPhaseMarineSuffix {
    constructor(
        public id?: number,
        public finishPhaseDate?: Moment,
        public startPhaseDate?: Moment,
        public firstScore?: number,
        public secondScore?: number,
        public finalScore?: number,
        public weightedPoints?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public status?: number,
        public startPhaseUserLogin?: string,
        public finishPhaseUserLogin?: string,
        public documents?: IDocumentMarineSuffix[],
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number,
        public finalNiazsanjiReport?: IFinalNiazsanjiReportMarineSuffix,
        public effectivenessPhaseLevelTitle?: string,
        public effectivenessPhaseLevelId?: number,
        public effectivenessPhaseLevel?: IEffectivenessPhaseLevelMarineSuffix,
        public peopleFinishCount?: number,
        public peopleCount?: number
    ) {}
}
