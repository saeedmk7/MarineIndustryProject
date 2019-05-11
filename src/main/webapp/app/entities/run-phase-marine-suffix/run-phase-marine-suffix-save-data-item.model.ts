export interface IRunPhaseSaveDataItemModel {
    runningStepId?: number;
    fileDoc?: string;
    done?: boolean;
    description?: string;
}

export class RunPhaseSaveDataItemModel implements IRunPhaseSaveDataItemModel {
    constructor(
        public runningStepId?: number,
        public fileDoc?: string,
        public done?: boolean,
        public description?: string
    ) {}
}
