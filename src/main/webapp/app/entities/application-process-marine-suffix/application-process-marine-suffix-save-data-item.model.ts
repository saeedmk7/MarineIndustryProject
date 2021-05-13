export interface IApplicationProcessSaveDataItemModel {
    runningStepId?: number;
    fileDoc?: string;
    done?: boolean;
    description?: string;
}

export class ApplicationProcessSaveDataItemModel implements IApplicationProcessSaveDataItemModel {
    constructor(public runningStepId?: number, public fileDoc?: string, public done?: boolean, public description?: string) {}
}
