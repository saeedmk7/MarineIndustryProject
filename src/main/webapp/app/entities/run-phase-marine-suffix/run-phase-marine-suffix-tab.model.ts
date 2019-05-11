import {IRunPhaseItemModel} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-item.model";

export interface IRunPhaseTabModel {
    id?: string;
    title?: string;
    href?: string;
    active?: boolean;
    colorText?: string;
    runPhaseItems?: IRunPhaseItemModel[]
}

export class RunPhaseTabModel implements IRunPhaseTabModel {
    constructor(
        public id?: string,
        public title?: string,
        public href?: string,
        public active?: boolean,
        public colorText?: string,
        public runPhaseItems?: IRunPhaseItemModel[]
    ) {}
}
