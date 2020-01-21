import { Moment } from 'moment';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';

export const enum RequestStatus {
    NEW = 'NEW',
    READ = 'READ',
    IGNORE = 'IGNORE',
    ACCEPT = 'ACCEPT',
    RETURNED = 'RETURNED'
}

export const enum RequestNiazsanjiType {
    FARDI = 'FARDI',
    JOB = 'JOB',
    OTHER = 'OTHER'
}

export interface INiazsanjiIntegrationMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    requestNiazsanjiType?: RequestNiazsanjiType;
    priority?: number;
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    prioritizeRequestNiazsanjiCode?: string;
    prioritizeRequestNiazsanjiId?: number;
}

export class NiazsanjiIntegrationMarineSuffix implements INiazsanjiIntegrationMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public requestNiazsanjiType?: RequestNiazsanjiType,
        public priority?: number,
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public prioritizeRequestNiazsanjiCode?: string,
        public prioritizeRequestNiazsanjiId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
