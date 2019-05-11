export interface IRunPhaseItemModel {
    id?: number;
    title?: string;
    description?: string;
    fileDoc?: string;
    required?: boolean;
    fileDocRequired?: boolean;
    stepNumber?: number;
    checked?: boolean;
    descMessage?: string;
}

export class RunPhaseItemModel implements IRunPhaseItemModel {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public fileDoc?: string,
        public required?: boolean,
        public fileDocRequired?: boolean,
        public stepNumber?: number,
        public checked?: boolean,
        public descMessage?: string
    ) {}
}
