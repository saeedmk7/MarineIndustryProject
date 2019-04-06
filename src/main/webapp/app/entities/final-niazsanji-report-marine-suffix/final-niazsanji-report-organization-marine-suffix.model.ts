import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

export interface IFinalNiazsanjiReportOrganizationMarineSuffix {
    id?: number;
    organizationChartTitle?: string;
    educationalModuleTitle?: string;
    educationalModuleId?: number;
    educationalModuleLevel?: string;
    educationalModuleTotalLearningTime?: number;
    priceCost?: number;
    niazsanjiYear?: number;
    people?: IPersonMarineSuffix[]
}

export class FinalNiazsanjiReportOrganizationMarineSuffix implements IFinalNiazsanjiReportOrganizationMarineSuffix  {
    constructor(
        public educationalModuleId?: number,
        public educationalModuleLevel?: string,
        public educationalModuleTitle?: string,
        public educationalModuleTotalLearningTime?: number,
        public id?: number,
        public niazsanjiYear?: number,
        public organizationChartTitle?: string,
        public people?: IPersonMarineSuffix[],
        public priceCost?: number) {

    }
}
