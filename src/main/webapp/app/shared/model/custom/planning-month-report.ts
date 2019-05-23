
export interface IPlanningAndRunMonthReport {
    month?: number;
    persianMonth?: string;
    personHour?: number;
    personCost?: number;
    reportType?: number;
}

export class PlanningAndRunMonthReport implements IPlanningAndRunMonthReport {
    constructor(
        month?: number,
        persianMonth?: string,
        personHour?: number,
        personCost?: number,
        reportType?: number
    ) {
    }
}
