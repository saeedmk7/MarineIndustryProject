import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';

export interface IEmploymentTypeMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    people?: IPersonMarineSuffix[];
}

export class EmploymentTypeMarineSuffix implements IEmploymentTypeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public people?: IPersonMarineSuffix[]
    ) {}
}
