import { Moment } from 'moment';

export interface IInvestToGroupTransactionMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    investDate?: string;
    investYear?: number;
    investAmount?: number;
    checkNumber?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    organizationChartTitle?: string;
    organizationChartId?: number;
}

export class InvestToGroupTransactionMarineSuffix implements IInvestToGroupTransactionMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public investDate?: string,
        public investYear?: number,
        public investAmount?: number,
        public checkNumber?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public organizationChartTitle?: string,
        public organizationChartId?: number
    ) {}
}
