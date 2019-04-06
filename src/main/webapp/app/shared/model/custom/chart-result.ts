import { Moment } from 'moment';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model//final-niazsanji-report-person-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { IRunPhaseMarineSuffix } from 'app/shared/model//run-phase-marine-suffix.model';
import { IPollMarineSuffix } from 'app/shared/model//poll-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {NiazSanjiSource} from "app/shared/model/enums/NiazSanjiSource";


export interface IChartResult {
    groupId?: number;
    priceCostNew?: number;
    educationalModuleTotalHourNew?: number;
    priceCostFinished?: number;
    educationalModuleTotalHourFinished?: number;
}

export class ChartResult implements IChartResult {
    constructor(
        public groupId?: number,
        public priceCostNew?: number,
        public educationalModuleTotalHourNew?: number,
        public priceCostFinished?: number,
        public educationalModuleTotalHourFinished?: number
    ) {
    }
}
