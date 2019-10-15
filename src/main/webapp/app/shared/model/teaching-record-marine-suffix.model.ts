import { Moment } from 'moment';

export interface ITeachingRecordMarineSuffix {
    id?: number;
    title?: string;
    record?: string;
    teachLocation?: string;
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

export class TeachingRecordMarineSuffix implements ITeachingRecordMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public record?: string,
        public teachLocation?: string,
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
