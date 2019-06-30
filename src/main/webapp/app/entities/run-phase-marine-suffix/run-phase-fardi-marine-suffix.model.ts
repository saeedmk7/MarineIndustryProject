export interface IRunPhaseFardiMarineSuffix {
    id?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
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
    finishDate?: string;
    runMonthPersian?: string;
}

export class RunPhaseFardiMarineSuffix implements IRunPhaseFardiMarineSuffix  {
    constructor(
        public educationalModuleId?: number,
        public educationalModuleLevel?: string,
        public educationalModuleTitle?: string,
        public educationalModuleTotalLearningTime?: number,
        public id?: number,
        public niazsanjiYear?: number,
        public organizationChartTitle?: string,
        public personFullName?: string,
        public personJobTitle?: string,
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public status?: number,
        public finalizeCost?: number,
        public finishDate?: string,
        public runMonthPersian?: string) {

    }
}
