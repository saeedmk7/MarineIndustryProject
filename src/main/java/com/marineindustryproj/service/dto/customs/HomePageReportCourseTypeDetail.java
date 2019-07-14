package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

public class HomePageReportCourseTypeDetail implements Serializable {

    public HomePageReportCourseTypeDetail(Long courseTypeId,
                                          String courseTypeTitle,
                                          Float total,
                                          Float totalPercent,
                                          Float totalManagers,
                                          Float totalManagersPercent,
                                          Float totalStuffs,
                                          Float totalStuffsPercent) {
        this.courseTypeId = courseTypeId;
        this.courseTypeTitle = courseTypeTitle;
        this.total = total;
        this.totalPercent = totalPercent;
        this.totalManagers = totalManagers;
        this.totalManagersPercent = totalManagersPercent;
        this.totalStuffs = totalStuffs;
        this.totalStuffsPercent = totalStuffsPercent;
    }

    private Long courseTypeId;

    private String courseTypeTitle;

    private Float total;

    private Float totalPercent;

    private Float totalManagers;

    private Float totalManagersPercent;

    private Float totalStuffs;

    private Float totalStuffsPercent;

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(Float totalPercent) {
        this.totalPercent = totalPercent;
    }

    public Float getTotalManagers() {
        return totalManagers;
    }

    public void setTotalManagers(Float totalManagers) {
        this.totalManagers = totalManagers;
    }

    public Float getTotalManagersPercent() {
        return totalManagersPercent;
    }

    public void setTotalManagersPercent(Float totalManagersPercent) {
        this.totalManagersPercent = totalManagersPercent;
    }

    public Float getTotalStuffs() {
        return totalStuffs;
    }

    public void setTotalStuffs(Float totalStuffs) {
        this.totalStuffs = totalStuffs;
    }

    public Float getTotalStuffsPercent() {
        return totalStuffsPercent;
    }

    public void setTotalStuffsPercent(Float totalStuffsPercent) {
        this.totalStuffsPercent = totalStuffsPercent;
    }
}
