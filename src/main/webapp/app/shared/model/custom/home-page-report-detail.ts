
export interface IHomePageReportDetail {
    organizationChartId?: number;
    organizationChartTitle?: string;
    total?: number;
    totalPercent?: number;
    totalManagers?: number;
    totalManagersPercent?: number;
    totalStuffs?: number;
    totalStuffsPercent?: number;
    totalPassed?: number;
    totalPassedPercent?: number;
    totalPassedManagers?: number;
    totalPassedManagersPercent?: number;
    totalPassedStuffs?: number;
    totalPassedStuffsPercent?: number;
    remaining?: number;
    remainingPercent?: number;
    remainingManagers?: number;
    remainingManagersPercent?: number;
    remainingStuffs?: number;
    remainingStuffsPercent?: number;
}

export class HomePageReportDetail implements IHomePageReportDetail {
    constructor(
        public organizationChartId?: number,
        public organizationChartTitle?: string,
        public total?: number,
        public totalPercent?: number,
        public totalManagers?: number,
        public totalManagersPercent?: number,
        public totalStuffs?: number,
        public totalStuffsPercent?: number,
        public totalPassed?: number,
        public totalPassedPercent?: number,
        public totalPassedManagers?: number,
        public totalPassedManagersPercent?: number,
        public totalPassedStuffs?: number,
        public totalPassedStuffsPercent?: number,
        public remaining?: number,
        public remainingPercent?: number,
        public remainingManagers?: number,
        public remainingManagersPercent?: number,
        public remainingStuffs?: number,
        public remainingStuffsPercent?: number,
    ) {
    }
}
