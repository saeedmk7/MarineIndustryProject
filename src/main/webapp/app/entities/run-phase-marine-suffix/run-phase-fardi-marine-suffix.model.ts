export interface IRunPhaseFardiMarineSuffix {
    id?: number;
    finalNiazsanjiReportId?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    educationalModuleLevel?: string;
    educationalModuleTotalLearningTime?: number;
    personFullName?: string;
    personJobTitle?: string;
    courseTypeId?: number;
    courseTypeTitle?: string;
    niazsanjiYear?: number;
    status?: number;
    finalizeCost?: number;
    priceCost?: number;
    finishDate?: string;
    runMonthPersian?: string;
}

export class RunPhaseFardiMarineSuffix implements IRunPhaseFardiMarineSuffix  {
    constructor(
        public educationalModuleId?: number,
        public educationalModuleLevel?: string,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleTotalLearningTime?: number,
        public id?: number,
        public finalNiazsanjiReportId?: number,
        public niazsanjiYear?: number,
        public organizationChartTitle?: string,
        public personFullName?: string,
        public personJobTitle?: string,
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public status?: number,
        public finalizeCost?: number,
        public priceCost?: number,
        public finishDate?: string,
        public runMonthPersian?: string) {

    }
}
