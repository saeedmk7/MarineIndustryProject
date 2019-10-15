import { Moment } from 'moment';

export interface IResearchRecordMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    personFamily?: string;
    personId?: number;
    personGuid?: string;
}

export class ResearchRecordMarineSuffix implements IResearchRecordMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public personFamily?: string,
        public personId?: number,
        public personGuid?: string
    ) {}
}
