import { Moment } from 'moment';

export interface ICapitationMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    employeeMaximumAllowablePersonHours?: number;
    bossMaximumAllowablePersonHours?: number;
    employeeMaximumAllowablePersonCosts?: number;
    bossMaximumAllowablePersonCosts?: number;
    capitationYear?: number;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
}

export class CapitationMarineSuffix implements ICapitationMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public employeeMaximumAllowablePersonHours?: number,
        public bossMaximumAllowablePersonHours?: number,
        public employeeMaximumAllowablePersonCosts?: number,
        public bossMaximumAllowablePersonCosts?: number,
        public capitationYear?: number,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment
    ) {}
}
