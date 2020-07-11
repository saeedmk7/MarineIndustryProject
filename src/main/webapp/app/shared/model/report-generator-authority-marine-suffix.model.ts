import { Moment } from 'moment';

export interface IReportGeneratorAuthorityMarineSuffix {
    id?: number;
    authorityName?: string;
    hasEditPermission?: boolean;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    reportGeneratorTitle?: string;
    reportGeneratorId?: number;
}

export class ReportGeneratorAuthorityMarineSuffix implements IReportGeneratorAuthorityMarineSuffix {
    constructor(
        public id?: number,
        public authorityName?: string,
        public hasEditPermission?: boolean,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public reportGeneratorTitle?: string,
        public reportGeneratorId?: number
    ) {
        this.hasEditPermission = this.hasEditPermission || false;
    }
}
