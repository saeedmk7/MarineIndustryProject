import { Moment } from 'moment';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';
import { IEducationalModuleJobMarineSuffix } from 'app/shared/model//educational-module-job-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IMainTaskMarineSuffix } from 'app/shared/model//main-task-marine-suffix.model';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model//niazsanji-group-marine-suffix.model';

export interface IJobMarineSuffix {
    id?: number;
    title?: string;
    fullTitle?: string;
    jobKey?: string;
    jobCode?: string;
    first3JobCode?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    jobPeople?: IPersonMarineSuffix[];
    practicaljobPeople?: IPersonMarineSuffix[];
    jobs?: IJobMarineSuffix[];
    educationalModuleJobs?: IEducationalModuleJobMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    rasteTitle?: string;
    rasteId?: number;
    radehTitle?: string;
    radehId?: number;
    jobTypeTitle?: string;
    jobTypeId?: number;
    scientificWorkGroupTitle?: string;
    scientificWorkGroupId?: number;
    parentTitle?: string;
    parentId?: number;
    mainTasks?: IMainTaskMarineSuffix[];
    niazsanjiGroups?: INiazsanjiGroupMarineSuffix[];
}

export class JobMarineSuffix implements IJobMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public fullTitle?: string,
        public jobKey?: string,
        public jobCode?: string,
        public first3JobCode?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public jobPeople?: IPersonMarineSuffix[],
        public practicaljobPeople?: IPersonMarineSuffix[],
        public jobs?: IJobMarineSuffix[],
        public educationalModuleJobs?: IEducationalModuleJobMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public rasteTitle?: string,
        public rasteId?: number,
        public radehTitle?: string,
        public radehId?: number,
        public jobTypeTitle?: string,
        public jobTypeId?: number,
        public scientificWorkGroupTitle?: string,
        public scientificWorkGroupId?: number,
        public parentTitle?: string,
        public parentId?: number,
        public mainTasks?: IMainTaskMarineSuffix[],
        public niazsanjiGroups?: INiazsanjiGroupMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
