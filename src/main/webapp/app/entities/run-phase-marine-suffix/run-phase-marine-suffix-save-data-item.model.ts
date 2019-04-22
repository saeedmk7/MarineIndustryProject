export interface IRunPhaseSaveDataItemModel {
    runningStepId?: number;
    done?: boolean;
    description?: string;
}

export class RunPhaseSaveDataItemModel implements IRunPhaseSaveDataItemModel {
    constructor(
        public runningStepId?: number,
        public done?: boolean,
        public description?: string
    ) {}
}
