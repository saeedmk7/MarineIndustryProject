export interface IChartResult {
    groupId?: number;
    totalPersonHour?: number;
    totalPriceCost?: number;
    priceCostNew?: number;
    educationalModuleTotalHourNew?: number;
    priceCostFinished?: number;
    educationalModuleTotalHourFinished?: number;
}

export class ChartResult implements IChartResult {
    constructor(
        public groupId?: number,
        public totalPersonHour?: number,
        public totalPriceCost?: number,
        public priceCostNew?: number,
        public educationalModuleTotalHourNew?: number,
        public priceCostFinished?: number,
        public educationalModuleTotalHourFinished?: number
    ) {
    }
}
