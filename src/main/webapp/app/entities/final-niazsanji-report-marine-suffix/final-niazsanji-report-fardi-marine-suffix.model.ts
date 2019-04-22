export interface IFinalNiazsanjiReportFardiMarineSuffix {
    id?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    educationalModuleLevel?: string;
    educationalModuleTotalLearningTime?: number;
    personFullName?: string;
    personJobTitle?: string;
    priceCost?: number;
    niazsanjiYear?: number;
    status?: number;
}

export class FinalNiazsanjiReportFardiMarineSuffix implements IFinalNiazsanjiReportFardiMarineSuffix  {
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
        public priceCost?: number,
        public status?: number) {

    }
}
