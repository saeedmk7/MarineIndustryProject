import { Moment } from 'moment';
import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model//media-awareness-report-marine-suffix.model';

export interface IMediaProductTypeMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[];
}

export class MediaProductTypeMarineSuffix implements IMediaProductTypeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public mediaAwarenessReports?: IMediaAwarenessReportMarineSuffix[]
    ) {}
}
