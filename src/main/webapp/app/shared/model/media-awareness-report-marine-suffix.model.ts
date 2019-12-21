import { Moment } from 'moment';

export interface IMediaAwarenessReportMarineSuffix {
    id?: number;
    designed?: boolean;
    designedNumber?: number;
    subject?: string;
    publishDate?: string;
    numberOfViewers?: number;
    durationOfOperation?: number;
    reportTime?: number;
    personHour?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    organizationChartTitle?: string;
    organizationChartId?: number;
    mediaProductTypeTitle?: string;
    mediaProductTypeId?: number;
}

export class MediaAwarenessReportMarineSuffix implements IMediaAwarenessReportMarineSuffix {
    constructor(
        public id?: number,
        public designed?: boolean,
        public designedNumber?: number,
        public subject?: string,
        public publishDate?: string,
        public numberOfViewers?: number,
        public durationOfOperation?: number,
        public reportTime?: number,
        public personHour?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public mediaProductTypeTitle?: string,
        public mediaProductTypeId?: number
    ) {
        this.designed = this.designed || false;
    }
}
