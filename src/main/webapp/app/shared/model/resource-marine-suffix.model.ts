import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';

export interface IResourceMarineSuffix {
    id?: number;
    title?: string;
    description?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    documents?: IDocumentMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
}

export class ResourceMarineSuffix implements IResourceMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public documents?: IDocumentMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[]
    ) {}
}
