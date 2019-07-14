package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class HomePageReport implements Serializable {

    public HomePageReport(Long orgChartId, Float totalPassed, Float totalRemaining, List<HomePageReportDetail> homePageReportDetailList) {
        this.orgChartId = orgChartId;
        this.totalPassed = totalPassed;
        this.totalRemaining = totalRemaining;
        this.homePageReportDetailList = homePageReportDetailList;
    }

    private Long orgChartId;

    private Float totalPassed;

    private Float totalRemaining;

    private List<HomePageReportDetail> homePageReportDetailList;

    public Long getOrgChartId() {
        return orgChartId;
    }

    public void setOrgChartId(Long orgChartId) {
        this.orgChartId = orgChartId;
    }

    public Float getTotalPassed() {
        return totalPassed;
    }

    public void setTotalPassed(Float totalPassed) {
        this.totalPassed = totalPassed;
    }

    public Float getTotalRemaining() {
        return totalRemaining;
    }

    public void setTotalRemaining(Float totalRemaining) {
        this.totalRemaining = totalRemaining;
    }

    public List<HomePageReportDetail> getHomePageReportDetailList() {
        return homePageReportDetailList;
    }

    public void setHomePageReportDetailList(List<HomePageReportDetail> homePageReportDetailList) {
        this.homePageReportDetailList = homePageReportDetailList;
    }


}
