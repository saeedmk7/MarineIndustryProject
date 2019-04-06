import { Moment } from 'moment';
import {NiazSanjiSource} from "app/shared/model/enums/NiazSanjiSource";

export interface IFinalNiazsanjiReportPersonMarineSuffix {
    id?: number;
    niazSanjiSource?: NiazSanjiSource;
    priceCost?: number;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    sourceId?: number;
    personFamily?: string;
    personId?: number;
    finalNiazsanjiReportDescription?: string;
    finalNiazsanjiReportId?: number;
}

export class FinalNiazsanjiReportPersonMarineSuffix implements IFinalNiazsanjiReportPersonMarineSuffix {
    constructor(
        public id?: number,
        public niazSanjiSource?: NiazSanjiSource,
        public priceCost?: number,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public sourceId?: number,
        public personFamily?: string,
        public personId?: number,
        public finalNiazsanjiReportDescription?: string,
        public finalNiazsanjiReportId?: number
    ) {
        this.archived = this.archived || false;
    }
}
