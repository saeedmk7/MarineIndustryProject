
export interface IHomePageReportCourseTypeDetail {
    courseTypeId?: number;
    courseTypeTitle?: string;
    total?: number;
    totalPercent?: number;
    totalManagers?: number;
    totalManagersPercent?: number;
    totalStuffs?: number;
    totalStuffsPercent?: number;
}

export class HomePageReportCourseTypeDetail implements IHomePageReportCourseTypeDetail {
    constructor(
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public total?: number,
        public totalPercent?: number,
        public totalManagers?: number,
        public totalManagersPercent?: number,
        public totalStuffs?: number,
        public totalStuffsPercent?: number
    ) {
    }
}
