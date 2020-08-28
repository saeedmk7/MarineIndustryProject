export interface IDetailFinalEffectivenessPhaseReportModel {
    effectivenessLevel?: number;
    finalNiazsanjiReportId?: number;
    educationalModuleTitle?: string;
    effectivenessPhaseFinalResultPercent?: number;
    peopleCount?: number;
    finalAverage?: number;
}

export class DetailFinalEffectivenessPhaseReportModel implements IDetailFinalEffectivenessPhaseReportModel {
    constructor(
        public effectivenessLevel?: number,
        public finalNiazsanjiReportId?: number,
        public educationalModuleTitle?: string,
        public effectivenessPhaseFinalResultPercent?: number,
        public peopleCount?: number,
        public finalAverage?: number
    ) {}
}
