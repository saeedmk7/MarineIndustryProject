import { Moment } from 'moment';

export interface IWorkUnitMarineSuffix {
    id?: number;
    code?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    workIndustryTitle?: string;
    workIndustryId?: number;
    workGroupTitle?: string;
    workGroupId?: number;
}

export class WorkUnitMarineSuffix implements IWorkUnitMarineSuffix {
    constructor(
        public id?: number,
        public code?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public workIndustryTitle?: string,
        public workIndustryId?: number,
        public workGroupTitle?: string,
        public workGroupId?: number
    ) {}
}
