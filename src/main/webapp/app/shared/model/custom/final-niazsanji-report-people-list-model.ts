export interface IFinalNiazsanjiPeopleListModel {
    peopleFullNames?: string[];
    entityId?: number;
}

export class FinalNiazsanjiPeopleListModel implements IFinalNiazsanjiPeopleListModel {
    constructor(public entityId?: number, public peopleFullNames?: string[]) {}
}
