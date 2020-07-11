export interface ICountListModel {
    count?: number;
    entityId?: number;
}

export class CountListModel implements ICountListModel {
    constructor(public entityId?: number, public count?: number) {}
}
