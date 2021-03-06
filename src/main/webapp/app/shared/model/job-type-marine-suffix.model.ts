import { Moment } from 'moment';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';

export interface IJobTypeMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    jobs?: IJobMarineSuffix[];
}

export class JobTypeMarineSuffix implements IJobTypeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public jobs?: IJobMarineSuffix[]
    ) {}
}
