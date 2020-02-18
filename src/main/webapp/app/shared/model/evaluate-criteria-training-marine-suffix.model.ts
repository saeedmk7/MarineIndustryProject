import { Moment } from 'moment';
import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model//evaluate-criteria-data-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';

export interface IEvaluateCriteriaTrainingMarineSuffix {
    id?: number;
    processTitle?: string;
    processWeight?: number;
    activityDescription?: string;
    criteriaWeight?: number;
    measureDescription?: string;
    firstNumber?: number;
    secondNumber?: number;
    thirdNumber?: number;
    measureCalc?: number;
    measureCalcPercent?: number;
    result?: number;
    resultPercent?: number;
    year?: number;
    month?: number;
    reportTime?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    evaluateCriteriaData?: IEvaluateCriteriaDataMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class EvaluateCriteriaTrainingMarineSuffix implements IEvaluateCriteriaTrainingMarineSuffix {
    constructor(
        public id?: number,
        public processTitle?: string,
        public processWeight?: number,
        public activityDescription?: string,
        public criteriaWeight?: number,
        public measureDescription?: string,
        public firstNumber?: number,
        public secondNumber?: number,
        public thirdNumber?: number,
        public measureCalc?: number,
        public measureCalcPercent?: number,
        public result?: number,
        public resultPercent?: number,
        public year?: number,
        public month?: number,
        public reportTime?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public evaluateCriteriaData?: IEvaluateCriteriaDataMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {}
}
