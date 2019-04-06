import { Moment } from 'moment';
import { ITeacherMarineSuffix } from 'app/shared/model//teacher-marine-suffix.model';

export interface IServiceUnitMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    teachers?: ITeacherMarineSuffix[];
}

export class ServiceUnitMarineSuffix implements IServiceUnitMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public teachers?: ITeacherMarineSuffix[]
    ) {}
}
