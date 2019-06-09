import { Moment } from 'moment';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import {EducationalModuleType} from 'app/shared/model/enums/EducationalModuleType';

export interface INiazsanjiFardiMarineSuffix {
    id?: number;
    niazsanjiYear?: number;
    code?: string;
    educationalModuleType?: EducationalModuleType;
    priceCost?: number;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    conversation?: any;
    changeStatusUserLogin?: string;
    guid?: string;
    documents?: IDocumentMarineSuffix[];
    courseTypeTitle?: string;
    courseTypeId?: number;
    requestNiazsanjiFardiCode?: string;
    requestNiazsanjiFardiId?: number;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    personFullName?: string;
    personName?: string;
    personFamily?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartRootTitle?: string;
    organizationChartId?: number;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
}

export class NiazsanjiFardiMarineSuffix implements INiazsanjiFardiMarineSuffix {
    constructor(
        public id?: number,
        public niazsanjiYear?: number,
        public code?: string,
        public educationalModuleType?: EducationalModuleType,
        public priceCost?: number,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public conversation?: any,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public documents?: IDocumentMarineSuffix[],
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public requestNiazsanjiFardiCode?: string,
        public requestNiazsanjiFardiId?: number,
        public educationalModuleTitle?: string,
        public educationalModuleId?: number,
        public personFullName?: string,
        public personName?: string,
        public personFamily?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string
    ) {
        this.archived = this.archived || false;
    }
}
