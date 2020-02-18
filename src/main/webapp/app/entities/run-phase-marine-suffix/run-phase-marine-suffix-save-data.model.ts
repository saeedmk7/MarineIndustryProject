import {IRunPhaseSaveDataItemModel} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data-item.model";

export interface IRunPhaseSaveDataModel {
    finalNiazsanjiReportId?: number;
    runPhaseId?: number;
    runMonth?: number;
    description?: string;
    conversion?: string;
    stepNumber?: number;
    done?: boolean;
    status?: number;
    finalizeCost?: number;
    finishDate?: string;
    runPhaseSaveDataItemModels?: IRunPhaseSaveDataItemModel[]
}

export class RunPhaseSaveDataModel implements IRunPhaseSaveDataModel {
    constructor(
        public finalNiazsanjiReportId?: number,
        public runPhaseId?: number,
        public runMonth?: number,
        public description?: string,
        public conversion?: string,
        public stepNumber?: number,
        public done?: boolean,
        public status?: number,
        public finalizeCost?: number,
        public finishDate?: string,
        public runPhaseSaveDataItemModels?: IRunPhaseSaveDataItemModel[]
    ) {}
}
