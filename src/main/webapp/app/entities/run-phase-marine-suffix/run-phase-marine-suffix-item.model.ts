export interface IRunPhaseItemModel {
    id?: number;
    title?: string;
    description?: string;
    required?: boolean;
    stepNumber?: number;
    checked?: boolean;
    descMessage?: string;
}

export class RunPhaseItemModel implements IRunPhaseItemModel {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public required?: boolean,
        public stepNumber?: number,
        public checked?: boolean,
        public descMessage?: string
    ) {}
}
