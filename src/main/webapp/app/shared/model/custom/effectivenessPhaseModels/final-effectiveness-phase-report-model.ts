import { IDetailFinalEffectivenessPhaseReportModel } from './detail-final-effectiveness-phase-report-model';

export interface IFinalEffectivenessPhaseReportModel {
    levelId?: number;
    levelTitle?: string;
    finishedCount?: number;
    averageEffectiveness?: number;
    detailFinalEffectivenessPhaseReportModels?: IDetailFinalEffectivenessPhaseReportModel[];
}

export class FinalEffectivenessPhaseReportModel implements IFinalEffectivenessPhaseReportModel {
    constructor(
        public levelId?: number,
        public levelTitle?: string,
        public finishedCount?: number,
        public averageEffectiveness?: number,
        public detailFinalEffectivenessPhaseReportModels?: IDetailFinalEffectivenessPhaseReportModel[]
    ) {}
}
