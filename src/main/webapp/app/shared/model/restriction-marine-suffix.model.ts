import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model//design-niazsanji-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';

export interface IRestrictionMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    designNiazsanjis?: IDesignNiazsanjiMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
}

export class RestrictionMarineSuffix implements IRestrictionMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public guid?: string,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[],
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public designNiazsanjis?: IDesignNiazsanjiMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[]
    ) {}
}
