import { Moment } from 'moment';
import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model//report-generator-authority-marine-suffix.model';
import { IOrganizationChartMarineSuffix } from 'app/shared/model//organization-chart-marine-suffix.model';

export interface IReportGeneratorMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    searchParams?: string;
    columnNames?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    reportGeneratorAuthorities?: IReportGeneratorAuthorityMarineSuffix[];
    organizationCharts?: IOrganizationChartMarineSuffix[];
    authorityNames?: string;
}

export class ReportGeneratorMarineSuffix implements IReportGeneratorMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public searchParams?: string,
        public columnNames?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public reportGeneratorAuthorities?: IReportGeneratorAuthorityMarineSuffix[],
        public organizationCharts?: IOrganizationChartMarineSuffix[],
        public authorityNames?: string
    ) {}
}
