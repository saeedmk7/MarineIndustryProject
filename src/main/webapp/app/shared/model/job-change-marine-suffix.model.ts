import { Moment } from 'moment';

export interface IJobChangeMarineSuffix {
    id?: number;
    title?: string;
    status?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    personName?: string;
    personFamily?: string;
    personFullName?: string;
    personId?: number;
    oldJobTitle?: string;
    oldJobId?: number;
    newJobTitle?: string;
    newJobId?: number;
}

export class JobChangeMarineSuffix implements IJobChangeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public status?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public personName?: string,
        public personFamily?: string,
        public personFullName?: string,
        public personId?: number,
        public oldJobTitle?: string,
        public oldJobId?: number,
        public newJobTitle?: string,
        public newJobId?: number
    ) {}
}
