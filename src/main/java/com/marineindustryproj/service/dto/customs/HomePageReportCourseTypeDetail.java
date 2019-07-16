package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

public class HomePageReportCourseTypeDetail implements Serializable {

    public HomePageReportCourseTypeDetail(Long courseTypeId,
                                          String courseTypeTitle,
                                          float total,
                                          float totalPercent,
                                          float totalManagers,
                                          float totalManagersPercent,
                                          float totalStuffs,
                                          float totalStuffsPercent) {
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

    private float total;

    private float totalPercent;

    private float totalManagers;

    private float totalManagersPercent;

    private float totalStuffs;

    private float totalStuffsPercent;

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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(float totalPercent) {
        this.totalPercent = totalPercent;
    }

    public float getTotalManagers() {
        return totalManagers;
    }

    public void setTotalManagers(float totalManagers) {
        this.totalManagers = totalManagers;
    }

    public float getTotalManagersPercent() {
        return totalManagersPercent;
    }

    public void setTotalManagersPercent(float totalManagersPercent) {
        this.totalManagersPercent = totalManagersPercent;
    }

    public float getTotalStuffs() {
        return totalStuffs;
    }

    public void setTotalStuffs(float totalStuffs) {
        this.totalStuffs = totalStuffs;
    }

    public float getTotalStuffsPercent() {
        return totalStuffsPercent;
    }

    public void setTotalStuffsPercent(float totalStuffsPercent) {
        this.totalStuffsPercent = totalStuffsPercent;
    }
}
