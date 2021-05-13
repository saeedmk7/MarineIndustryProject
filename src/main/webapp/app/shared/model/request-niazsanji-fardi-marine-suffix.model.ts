import { Moment } from 'moment';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model//niazsanji-fardi-marine-suffix.model';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model//prioritize-request-niazsanji-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model//document-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';

export interface IRequestNiazsanjiFardiMarineSuffix {
    id?: number;
    code?: string;
    costApprovedEducationalModule?: number;
    costAllEducationalModule?: number;
    description?: any;
    createUserLogin?: string;
    createDate?: Moment;
    modifyUserLogin?: string;
    modifyDate?: Moment;
    archived?: boolean;
    archivedUserLogin?: string;
    archivedDate?: Moment;
    status?: number;
    statusMeaning?: string;
    conversation?: any;
    requestStatus?: RequestStatus;
    changeStatusUserLogin?: string;
    guid?: string;
    hasImportantMessage?: boolean;
    niazsanjiFardis?: INiazsanjiFardiMarineSuffix[];
    prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[];
    documents?: IDocumentMarineSuffix[];
    courseTypeTitle?: string;
    courseTypeId?: number;
    allCourseTypeId?: number;
    approvedCourseTypeId?: number;
    approvedEducationalModuleTitle?: string;
    approvedEducationalModuleCode?: string;
    approvedEducationalModuleId?: number;
    allEducationalModuleTitle?: string;
    allEducationalModuleCode?: string;
    allEducationalModuleId?: number;
    personFullName?: string;
    personName?: string;
    personFamily?: string;
    personJobTitle?: string;
    personId?: number;
    organizationChartTitle?: string;
    organizationChartId?: number;
    approvedLearningTimePractical?: number;
    allLearningTimePractical?: number;
    approvedLearningTimeTheorical?: number;
    allLearningTimeTheorical?: number;
    totalLearningTime?: number;
    skillLevelOfSkillTitle?: string;
    allSkillLevelOfSkillTitle?: string;
    approvedSkillLevelOfSkillTitle?: string;
}

export class RequestNiazsanjiFardiMarineSuffix implements IRequestNiazsanjiFardiMarineSuffix {
    constructor(
        public id?: number,
        public code?: string,
        public costApprovedEducationalModule?: number,
        public costAllEducationalModule?: number,
        public description?: any,
        public createUserLogin?: string,
        public createDate?: Moment,
        public modifyUserLogin?: string,
        public modifyDate?: Moment,
        public archived?: boolean,
        public archivedUserLogin?: string,
        public archivedDate?: Moment,
        public status?: number,
        public statusMeaning?: string,
        public conversation?: any,
        public requestStatus?: RequestStatus,
        public changeStatusUserLogin?: string,
        public guid?: string,
        public hasImportantMessage?: boolean,
        public niazsanjiFardis?: INiazsanjiFardiMarineSuffix[],
        public prioritizeRequestNiazsanjis?: IPrioritizeRequestNiazsanjiMarineSuffix[],
        public documents?: IDocumentMarineSuffix[],
        public courseTypeTitle?: string,
        public courseTypeId?: number,
        public approvedEducationalModuleTitle?: string,
        public approvedEducationalModuleCode?: string,
        public approvedEducationalModuleId?: number,
        public allEducationalModuleTitle?: string,
        public allEducationalModuleCode?: string,
        public allEducationalModuleId?: number,
        public personFullName?: string,
        public personName?: string,
        public personFamily?: string,
        public personJobTitle?: string,
        public personId?: number,
        public organizationChartTitle?: string,
        public organizationChartId?: number,
        public approvedLearningTimeTheorical?: number,
        public allLearningTimeTheorical?: number,
        public approvedLearningTimePractical?: number,
        public allLearningTimePractical?: number,
        public totalLearningTime?: number,
        public skillLevelOfSkillTitle?: string,
        public approvedSkillLevelOfSkillTitle?: string,
        public allSkillLevelOfSkillTitle?: string
    ) {
        this.archived = this.archived || false;
        this.hasImportantMessage = this.hasImportantMessage || false;
    }
}
