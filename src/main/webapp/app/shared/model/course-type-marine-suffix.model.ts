import { Moment } from 'moment';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//request-organization-niazsanji-marine-suffix.model';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model//final-organization-niazsanji-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model//final-niazsanji-report-marine-suffix.model';
import { IDesignAndPlanningMarineSuffix } from 'app/shared/model//design-and-planning-marine-suffix.model';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model//request-niazsanji-fardi-marine-suffix.model';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model//educational-history-marine-suffix.model';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model//design-niazsanji-marine-suffix.model';
import { IJobNiazsanjiMarineSuffix } from 'app/shared/model//job-niazsanji-marine-suffix.model';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model//niazsanji-other-marine-suffix.model';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model//request-other-niazsanji-marine-suffix.model';

export interface ICourseTypeMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[];
    finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[];
    finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[];
    designAndPlannings?: IDesignAndPlanningMarineSuffix[];
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[];
    educationalHistories?: IEducationalHistoryMarineSuffix[];
    designNiazsanjis?: IDesignNiazsanjiMarineSuffix[];
    jobNiazsanjis?: IJobNiazsanjiMarineSuffix[];
    niazsanjiOthers?: INiazsanjiOtherMarineSuffix[];
    requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[];
}

export class CourseTypeMarineSuffix implements ICourseTypeMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public description?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public requestOrganizationNiazsanjis?: IRequestOrganizationNiazsanjiMarineSuffix[],
        public finalOrganizationNiazsanjis?: IFinalOrganizationNiazsanjiMarineSuffix[],
        public finalNiazsanjiReports?: IFinalNiazsanjiReportMarineSuffix[],
        public designAndPlannings?: IDesignAndPlanningMarineSuffix[],
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public requestNiazsanjiFardis?: IRequestNiazsanjiFardiMarineSuffix[],
        public educationalHistories?: IEducationalHistoryMarineSuffix[],
        public designNiazsanjis?: IDesignNiazsanjiMarineSuffix[],
        public jobNiazsanjis?: IJobNiazsanjiMarineSuffix[],
        public niazsanjiOthers?: INiazsanjiOtherMarineSuffix[],
        public requestOtherNiazsanjis?: IRequestOtherNiazsanjiMarineSuffix[]
    ) {}
}
