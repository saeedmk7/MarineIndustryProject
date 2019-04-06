import { Moment } from 'moment';
import { IJobMarineSuffix } from 'app/shared/model//job-marine-suffix.model';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model//niazsanji-group-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model//person-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';

export interface IScientificWorkGroupMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    jobs?: IJobMarineSuffix[];
    niazsanjiGroups?: INiazsanjiGroupMarineSuffix[];
    people?: IPersonMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
}

export class ScientificWorkGroupMarineSuffix implements IScientificWorkGroupMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public jobs?: IJobMarineSuffix[],
        public niazsanjiGroups?: INiazsanjiGroupMarineSuffix[],
        public people?: IPersonMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[]
    ) {}
}
