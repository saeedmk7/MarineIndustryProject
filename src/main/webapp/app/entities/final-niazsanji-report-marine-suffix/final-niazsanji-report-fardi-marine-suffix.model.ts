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
    courseTypeId?: number;
    courseTypeTitle?: string;
    niazsanjiYear?: number;
    status?: number;
    finalizeCost?: number;
    runMonthPersian?: string;
    planningRunMonthPersian?: string;
    modifyPerson?:string;
    modifyDate?:string;
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
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public priceCost?: number,
        public status?: number,
        public finalizeCost?: number,
        public planningRunMonthPersian?: string,
        public modifyPerson?:string,
        public modifyDate?:string,
        public runMonthPersian?: string) {

    }
}
