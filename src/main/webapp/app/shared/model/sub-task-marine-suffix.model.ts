import { Moment } from 'moment';
import { IMainTaskMarineSuffix } from 'app/shared/model//main-task-marine-suffix.model';

export interface ISubTaskMarineSuffix {
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
    mainTasks?: IMainTaskMarineSuffix[];
}

export class SubTaskMarineSuffix implements ISubTaskMarineSuffix {
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
        public mainTasks?: IMainTaskMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
