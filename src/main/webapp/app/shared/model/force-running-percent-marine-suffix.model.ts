import { Moment } from 'moment';
import { IOrganizationChartMarineSuffix } from 'app/shared/model//organization-chart-marine-suffix.model';

export interface IForceRunningPercentMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    percentAmount?: number;
    year?: number;
    runMonth?: number;
    runMonthName?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    organizationCharts?: IOrganizationChartMarineSuffix[];
}

export class ForceRunningPercentMarineSuffix implements IForceRunningPercentMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public percentAmount?: number,
        public year?: number,
        public runMonth?: number,
        public runMonthName?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public organizationCharts?: IOrganizationChartMarineSuffix[]
    ) {}
}
