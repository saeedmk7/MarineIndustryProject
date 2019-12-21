import { Moment } from 'moment';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';

export interface IRestrictionMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    guid?: string;
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
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
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[]
    ) {}
}
