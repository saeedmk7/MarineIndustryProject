import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

export interface IFinalNiazsanjiReportOrganizationMarineSuffix {
    id?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
    educationalModuleCode?: string;
    educationalModuleId?: number;
    educationalModuleLevel?: string;
    educationalModuleTotalLearningTime?: number;
    courseTypeId?: number;
    courseTypeTitle?: string;
    priceCost?: number;
    niazsanjiYear?: number;
    finalizeCost?: number;
    people?: IPersonMarineSuffix[];
    status?: number;
    runMonthPersian?: string;
    planningRunMonthPersian?: string;
    modifyPerson?:string;
    modifyDate?:string;
    peopleCount?: number;
    peopleFullNames?: string;
}

export class FinalNiazsanjiReportOrganizationMarineSuffix implements IFinalNiazsanjiReportOrganizationMarineSuffix  {
    constructor(
        public educationalModuleId?: number,
        public educationalModuleLevel?: string,
        public educationalModuleTitle?: string,
        public educationalModuleCode?: string,
        public educationalModuleTotalLearningTime?: number,
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public id?: number,
        public niazsanjiYear?: number,
        public organizationChartTitle?: string,
        public finalizeCost?: number,
        public people?: IPersonMarineSuffix[],
        public priceCost?: number,
        public runMonthPersian?: string,
        public planningRunMonthPersian?: string,
        public modifyPerson?:string,
        public modifyDate?:string,
        public status?: number,
        public peopleCount?: number,
        public peopleFullNames?: string) {

    }
}
