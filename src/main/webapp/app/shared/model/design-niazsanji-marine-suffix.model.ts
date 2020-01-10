import { Moment } from 'moment';
import { IRestrictionMarineSuffix } from 'app/shared/model//restriction-marine-suffix.model';

export interface IDesignNiazsanjiMarineSuffix {
    id?: number;
    title?: string;
    code?: string;
    costEducationalModule?: number;
    description?: any;
    restrictionDescription?: string;
    goalsText?: string;
    prerequisite?: string;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    restrictions?: IRestrictionMarineSuffix[];
    preJobNiazsanjiTitle?: string;
    preJobNiazsanjiId?: number;
    courseTypeTitle?: string;
    courseTypeId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    teachingApproachTitle?: string;
    teachingApproachId?: number;
}

export class DesignNiazsanjiMarineSuffix implements IDesignNiazsanjiMarineSuffix {
    constructor(
        public id?: number,
        public title?: string,
        public code?: string,
        public costEducationalModule?: number,
        public description?: any,
        public restrictionDescription?: string,
        public goalsText?: string,
        public prerequisite?: string,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public restrictions?: IRestrictionMarineSuffix[],
        public preJobNiazsanjiTitle?: string,
        public preJobNiazsanjiId?: number,
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public teachingApproachTitle?: string,
        public teachingApproachId?: number
    ) {
        this.archived = this.archived || false;
    }
}
