
export interface IPlanningAndRunMonthReport {
    month?: number;
    totalHour?:number;
    totalPriceCost?:number;
    persianMonth?: string;
    personHour?: number;
    personCost?: number;
    reportType?: number;
}

export class PlanningAndRunMonthReport implements IPlanningAndRunMonthReport {
    constructor(
        month?: number,
        totalHour?: number,
        totalPriceCost?: number,
        persianMonth?: string,
        personHour?: number,
        personCost?: number,
        reportType?: number
    ) {
    }
}
