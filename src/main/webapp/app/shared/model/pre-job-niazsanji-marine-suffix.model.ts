import { Moment } from 'moment';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model//design-niazsanji-marine-suffix.model';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model//pre-job-niazsanji-competency-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IPreJobNiazsanjiMarineSuffix {
    id?: number;
    title?: string;
    reviewDate?: string;
    code?: string;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    statusMeaning?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    step?: number;
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    designNiazsanjis?: IDesignNiazsanjiMarineSuffix[];
    preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    people?: IPersonMarineSuffix[];
    organizationChartTitle?: string;
    organizationChartId?: number;
    personFamily?: string;
    personJobTitle?: string;
    personName?: string;
    personFullName?: string;
    personId?: number;
}

export class PreJobNiazsanjiMarineSuffix implements IPreJobNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public reviewDate?: string,
        public code?: string,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public statusMeaning?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public step?: number,
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public designNiazsanjis?: IDesignNiazsanjiMarineSuffix[],
        public preJobNiazsanjiCompetencies?: IPreJobNiazsanjiCompetencyMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public people?: IPersonMarineSuffix[],
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public personFamily?: string,
        public personJobTitle?: string,
        public personName?: string,
        public personFullName?: string,
        public personId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
