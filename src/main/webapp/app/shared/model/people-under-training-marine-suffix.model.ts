import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';

export interface IPeopleUnderTrainingMarineSuffix {
    id?: number;
    title?: string;
    fullTitle?: string;
    description?: string;
    peopleCount?: number;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
}

export class PeopleUnderTrainingMarineSuffix implements IPeopleUnderTrainingMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public fullTitle?: string,
        public description?: string,
        public peopleCount?: number,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[]
    ) {}
}
