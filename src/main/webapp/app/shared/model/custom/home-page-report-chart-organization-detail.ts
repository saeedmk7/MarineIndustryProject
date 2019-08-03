import {IHomePageReportThirdLevelDetail} from "app/shared/model/custom/home-page-report-third-level-detail";

export interface IHomePageReportChartOrganizationDetail {
    organizationChartId?: number;
    organizationChartTitle?: string;
    total?: number;
    totalPercent?: number;
    passed?: number;
    passedPercent?: number;
    remaining?: number;
    remainingPercent?: number;
}

export class HomePageReportChartOrganizationDetail implements IHomePageReportChartOrganizationDetail {
    constructor(
        public organizationChartId?: number,
        public organizationChartTitle?: string,
        public total?: number,
        public totalPercent?: number,
        public passed?: number,
        public passedPercent?: number,
        public remaining?: number,
        public remainingPercent?: number
    ) {
    }
}
