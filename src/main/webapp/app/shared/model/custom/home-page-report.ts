import {IHomePageReportDetail} from "app/shared/model/custom/home-page-report-detail";
import {IHomePageReportCourseTypeDetail} from "app/shared/model/custom/home-page-report-course-type-detail";
import {IHomePageReportOrganizationAndCourseTypeDetail} from 'app/shared/model/custom/home-page-report-organization-and-course-type-detail';

export interface IHomePageReport {
    total?: number;
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
    homePageReportDetails?: IHomePageReportDetail[];
    homePageReportCourseTypeDetails?: IHomePageReportCourseTypeDetail[];
    homePageReportOrganizationAndCourseTypeDetails?: IHomePageReportOrganizationAndCourseTypeDetail[];
}

export class HomePageReport implements IHomePageReport {
    constructor(
        public total?: number,
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
        public homePageReportDetails?: IHomePageReportDetail[],
        public homePageReportCourseTypeDetails?: IHomePageReportCourseTypeDetail[],
        public homePageReportOrganizationAndCourseTypeDetails?: IHomePageReportOrganizationAndCourseTypeDetail[]
    ) {
    }
}
