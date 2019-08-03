
import {IHomePageReportChartOrganizationDetail} from "app/shared/model/custom/home-page-report-chart-organization-detail";

export interface IHomePageReportOrganizationAndCourseTypeDetail {
    courseTypeId?: number;
    courseTypeTitle?: string;
    homePageReportChartOrganizationDetails?: IHomePageReportChartOrganizationDetail[];
}

export class HomePageReportOrganizationAndCourseTypeDetail implements IHomePageReportOrganizationAndCourseTypeDetail {
    constructor(
        public courseTypeId?: number,
        public courseTypeTitle?: string,
        public homePageReportChartOrganizationDetails?: IHomePageReportChartOrganizationDetail[]
    ) {
    }
}
