package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

public class HomePageReportOrganizationAndCourseTypeDetail implements Serializable {

    public HomePageReportOrganizationAndCourseTypeDetail(){}

    public HomePageReportOrganizationAndCourseTypeDetail(long courseTypeId,
                                                         String courseTypeTitle,
                                                         List<HomePageReportChartOrganizationDetail> homePageReportChartOrganizationDetails) {
        this.courseTypeId = courseTypeId;
        this.courseTypeTitle = courseTypeTitle;
        this.homePageReportChartOrganizationDetails = homePageReportChartOrganizationDetails;
    }

    private long courseTypeId;

    private String courseTypeTitle;

    private List<HomePageReportChartOrganizationDetail> homePageReportChartOrganizationDetails;

    public long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public List<HomePageReportChartOrganizationDetail> getHomePageReportChartOrganizationDetails() {
        return homePageReportChartOrganizationDetails;
    }

    public void setHomePageReportChartOrganizationDetails(List<HomePageReportChartOrganizationDetail> homePageReportChartOrganizationDetails) {
        this.homePageReportChartOrganizationDetails = homePageReportChartOrganizationDetails;
    }
}
