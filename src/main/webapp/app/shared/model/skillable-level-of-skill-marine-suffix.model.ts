import { Moment } from 'moment';
import { IEducationalModuleMarineSuffix } from 'app/shared/model//educational-module-marine-suffix.model';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model//request-educational-module-marine-suffix.model';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model//matching-educational-record-marine-suffix.model';

export interface ISkillableLevelOfSkillMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    educationalModules?: IEducationalModuleMarineSuffix[];
    requestEducationalModules?: IRequestEducationalModuleMarineSuffix[];
    matchingEducationalRecords?: IMatchingEducationalRecordMarineSuffix[];
}

export class SkillableLevelOfSkillMarineSuffix implements ISkillableLevelOfSkillMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public educationalModules?: IEducationalModuleMarineSuffix[],
        public requestEducationalModules?: IRequestEducationalModuleMarineSuffix[],
        public matchingEducationalRecords?: IMatchingEducationalRecordMarineSuffix[]
    ) {}
}
