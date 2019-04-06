import { Moment } from 'moment';
import { IActivityAreaMarineSuffix } from 'app/shared/model//activity-area-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';

export interface IEducationalCenterMarineSuffix {
    id?: number;
    name?: string;
    ceo?: string;
    connectionPerson?: string;
    telephone?: string;
    fax?: string;
    address?: string;
    email?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    activityAreas?: IActivityAreaMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
}

export class EducationalCenterMarineSuffix implements IEducationalCenterMarineSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public ceo?: string,
        public connectionPerson?: string,
        public telephone?: string,
        public fax?: string,
        public address?: string,
        public email?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public activityAreas?: IActivityAreaMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[]
    ) {
        this.archived = this.archived || false;
    }
}
