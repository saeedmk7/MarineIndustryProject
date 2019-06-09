import { Moment } from 'moment';

export interface IJobRecordMarineSuffix {
    id?: number;
    workOrganization?: string;
    workSection?: string;
    jobResponsibility?: string;
    jobPosition?: string;
    totalHour?: number;
    startDate?: string;
    endDate?: string;
    leaveReason?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    personFamily?: string;
    personId?: number;
}

export class JobRecordMarineSuffix implements IJobRecordMarineSuffix {
    constructor(
        public id?: number,
        public workOrganization?: string,
        public workSection?: string,
        public jobResponsibility?: string,
        public jobPosition?: string,
        public totalHour?: number,
        public startDate?: string,
        public endDate?: string,
        public leaveReason?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public personFamily?: string,
        public personId?: number
    ) {}
}
