import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';

export interface INiazsanjiOtherMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    code?: string;
    priceCost?: number;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    conversation?: any;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    documents?: IDocumentMarineSuffix[];
    restrictions?: IRestrictionMarineSuffix[];
    niazsanjiInputTitle?: string;
    niazsanjiInputId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    requestOtherNiazsanjiCode?: string;
    requestOtherNiazsanjiId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    personFamily?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
}

export class NiazsanjiOtherMarineSuffix implements INiazsanjiOtherMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public code?: string,
        public priceCost?: number,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public documents?: IDocumentMarineSuffix[],
        public restrictions?: IRestrictionMarineSuffix[],
        public niazsanjiInputTitle?: string,
        public niazsanjiInputId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public requestOtherNiazsanjiCode?: string,
        public requestOtherNiazsanjiId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public personFamily?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
