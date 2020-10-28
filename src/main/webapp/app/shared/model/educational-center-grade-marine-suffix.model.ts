import { Moment } from 'moment';
import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model//educational-center-grade-score-marine-suffix.model';
import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model//evaluator-opinion-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { Grade } from 'app/shared/model/enums/Grade';
import { IKeyValue } from 'app/shared/model/custom/key-value';

export interface IEducationalCenterGradeMarineSuffix {
    id?: number;
    title?: string;
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
    educationalCenterGradeScores?: IEducationalCenterGradeScoreMarineSuffix[];
    evaluatorOpinions?: IEvaluatorOpinionMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    educationalCenterServiceTitle?: string;
    educationalCenterServiceId?: number;
    educationalCenterName?: string;
    educationalCenterId?: number;
    keyValues?: IKeyValue[];
    educationalCenterGroupTitle?: string;
    educationalCenterGroupId?: number;
}

export class EducationalCenterGradeMarineSuffix implements IEducationalCenterGradeMarineSuffix {
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
        public fileDoc?: any,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalCenterGradeScores?: IEducationalCenterGradeScoreMarineSuffix[],
        public evaluatorOpinions?: IEvaluatorOpinionMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public educationalCenterServiceTitle?: string,
        public educationalCenterServiceId?: number,
        public educationalCenterName?: string,
        public educationalCenterId?: number,
        public keyValues?: IKeyValue[],
        public educationalCenterGroupTitle?: string,
        public educationalCenterGroupId?: number
    ) {}
}
