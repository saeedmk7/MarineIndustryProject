export interface IHomePagePersonEducationalModule {
    id?: number;
    code?: string;
    title?: string;
    fullTitle?: string;
    learningTimeTheorical?: number;
    learningTimePractical?: number;
    totalLearningTime?: number;
    status?: number;
    statusMeaning?: string;
    educationalModuleType?: string;
    courseType?: string;
    organizationTitle?: string;
    organizationId?: number;
    skillableLevelOfSkillTitle?: string;
    skillableLevelOfSkillId?: number;
    runDate?: string;
    endDate?: string;
    educationalHistoryId?: number;
}

export class HomePagePersonEducationalModule implements IHomePagePersonEducationalModule {
    constructor(
        public id?: number,
        public code?: string,
        public title?: string,
        public fullTitle?: string,
        public learningTimeTheorical?: number,
        public learningTimePractical?: number,
        public totalLearningTime?: number,
        public status?: number,
        public statusMeaning?: string,
        public educationalModuleType?: string,
        public courseType?: string,
        public skillableLevelOfSkillTitle?: string,
        public skillableLevelOfSkillId?: number,
        public organizationTitle?: string,
        public organizationId?: number,
        public runDate?: string,
        public endDate?: string,
        public educationalHistoryId?: number
    ) {}
}
