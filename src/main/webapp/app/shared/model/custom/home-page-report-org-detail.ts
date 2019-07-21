
export interface IHomePageReportOrgDetail {
    courseTypeId?: number;
    total?: number;
    totalPercent?: number;
    passed?: number;
    passedPercent?: number;
    remaining?: number;
    remainingPercent?: number;
}

export class HomePageReportOrgDetail implements IHomePageReportOrgDetail {
    constructor(
        public courseTypeId?: number,
        public total?: number,
        public totalPercent?: number,
        public passed?: number,
        public passedPercent?: number,
        public remaining?: number,
        public remainingPercent?: number
    ) {
    }
}
