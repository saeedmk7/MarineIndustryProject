import { Moment } from 'moment';

export interface IEducationalModuleJobMarineSuffix {
    id?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    jobTitle?: string;
    jobCode?: string;
    first3JobCode?: string;
    jobId?: number;
}

export class EducationalModuleJobMarineSuffix implements IEducationalModuleJobMarineSuffix {
    constructor(
        public id?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public jobTitle?: string,
        public jobCode?: string,
        public first3JobCode?: string,
        public jobId?: number
    ) {}
}
