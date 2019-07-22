package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

public class HomePageReportSecondLevelDetail implements Serializable {

    public HomePageReportSecondLevelDetail(){}
    public HomePageReportSecondLevelDetail(long courseTypeId,
                                           String courseTypeTitle,
                                           List<HomePageReportThirdLevelDetail>  homePageReportThirdLevelDetails
                                         ) {
        this.courseTypeTitle = courseTypeTitle;
        this.courseTypeId = courseTypeId;
        this.homePageReportThirdLevelDetails = homePageReportThirdLevelDetails;
    }

    private long courseTypeId;

    private String courseTypeTitle;

    private List<HomePageReportThirdLevelDetail>  homePageReportThirdLevelDetails;

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


    public List<HomePageReportThirdLevelDetail> getHomePageReportThirdLevelDetails() {
        return homePageReportThirdLevelDetails;
    }

    public void setHomePageReportThirdLevelDetails(List<HomePageReportThirdLevelDetail> homePageReportThirdLevelDetails) {
        this.homePageReportThirdLevelDetails = homePageReportThirdLevelDetails;
    }
}
