export interface IChartResultDetail {
    groupId?: number;
    industryTitle?: string;
    totalPersonHour?: number;
    totalPriceCost?: number;
    priceCostNew?: number;
    educationalModuleTotalHourNew?: number;
    priceCostFinished?: number;
    educationalModuleTotalHourFinished?: number;
}

export class ChartResultDetail implements IChartResultDetail {
    constructor(
        public groupId?: number,
        public industryTitle?: string,
        public totalPersonHour?: number,
        public totalPriceCost?: number,
        public priceCostNew?: number,
        public educationalModuleTotalHourNew?: number,
        public priceCostFinished?: number,
        public educationalModuleTotalHourFinished?: number
    ) {}
}
