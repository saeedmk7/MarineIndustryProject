export interface IMatchingEducationalRecordSaveDataItemModel {
    runningStepId?: number;
    fileDoc?: string;
    done?: boolean;
    description?: string;
}

export class MatchingEducationalRecordSaveDataItemModel implements IMatchingEducationalRecordSaveDataItemModel {
    constructor(public runningStepId?: number, public fileDoc?: string, public done?: boolean, public description?: string) {}
}
