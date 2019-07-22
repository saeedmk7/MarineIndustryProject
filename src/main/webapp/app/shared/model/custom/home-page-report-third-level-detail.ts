
export interface IHomePageReportThirdLevelDetail {
    total?: number;
    totalPercent?: number;
    passed?: number;
    passedPercent?: number;
    remaining?: number;
    remainingPercent?: number;
}

export class HomePageReportThirdLevelDetail implements IHomePageReportThirdLevelDetail {
    constructor(
        public total?: number,
        public totalPercent?: number,
        public passed?: number,
        public passedPercent?: number,
        public remaining?: number,
        public remainingPercent?: number
    ) {
    }
}
