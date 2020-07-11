import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface IEvaluateCriteriaDataMarineSuffix {
    id?: number;
    title?: string;
    firstNumber?: number;
    secondNumber?: number;
    thirdNumber?: number;
    measureCalc?: number;
    measureCalcPercent?: number;
    result?: number;
    resultPercent?: number;
    year?: number;
    month?: number;
    fileDoc?: any;
    reportTime?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    qualityGoal?: string;
    documents?: IDocumentMarineSuffix[];
    evaluateCriteriaTrainingProcessTitle?: string;
    evaluateCriteriaTrainingId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class EvaluateCriteriaDataMarineSuffix implements IEvaluateCriteriaDataMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public firstNumber?: number,
        public secondNumber?: number,
        public thirdNumber?: number,
        public measureCalc?: number,
        public measureCalcPercent?: number,
        public result?: number,
        public resultPercent?: number,
        public year?: number,
        public month?: number,
        public fileDoc?: any,
        public reportTime?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public qualityGoal?: string,
        public documents?: IDocumentMarineSuffix[],
        public evaluateCriteriaTrainingProcessTitle?: string,
        public evaluateCriteriaTrainingId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {}
}
