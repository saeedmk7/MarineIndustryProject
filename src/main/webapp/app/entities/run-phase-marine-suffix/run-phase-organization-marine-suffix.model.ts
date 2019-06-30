import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

export interface IRunPhaseOrganizationMarineSuffix {
    id?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    educationalModuleLevel?: string;
    educationalModuleTotalLearningTime?: number;
    courseTypeId?: number;
    courseTypeTitle?: string;
    priceCost?: number;
    niazsanjiYear?: number;
    finalizeCost?: number;
    finishDate?: string;
    people?: IPersonMarineSuffix[];
    status?: number;
    runMonthPersian?: string;
}

export class RunPhaseOrganizationMarineSuffix implements IRunPhaseOrganizationMarineSuffix  {
    constructor(
        public educationalModuleId?: number,
        public educationalModuleLevel?: string,
        public educationalModuleTitle?: string,
        public educationalModuleTotalLearningTime?: number,
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public id?: number,
        public niazsanjiYear?: number,
        public organizationChartTitle?: string,
        public finalizeCost?: number,
        public finishDate?: string,
        public people?: IPersonMarineSuffix[],
        public priceCost?: number,
        public runMonthPersian?: string,
        public status?: number) {

    }
}
