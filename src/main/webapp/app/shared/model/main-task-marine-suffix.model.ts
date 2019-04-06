import { Moment } from 'moment';
import { ISubTaskMarineSuffix } from 'app/shared/model//sub-task-marine-suffix.model';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';

export interface IMainTaskMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    code?: string;
    solution?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    subTasks?: ISubTaskMarineSuffix[];
    jobs?: IJobMarineSuffix[];
    people?: IPersonMarineSuffix[];
}

export class MainTaskMarineSuffix implements IMainTaskMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public code?: string,
        public solution?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public subTasks?: ISubTaskMarineSuffix[],
        public jobs?: IJobMarineSuffix[],
        public people?: IPersonMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
