import {IHomePageReportThirdLevelDetail} from "app/shared/model/custom/home-page-report-third-level-detail";

export interface IHomePageReportSecondLevelDetail {
    courseTypeId?: number;
    courseTypeTitle?: string;
    homePageReportThirdLevelDetails?: IHomePageReportThirdLevelDetail[];
}

export class HomePageReportSecondLevelDetail implements IHomePageReportSecondLevelDetail {
    constructor(
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public homePageReportThirdLevelDetails?: IHomePageReportThirdLevelDetail[]
    ) {
    }
}
