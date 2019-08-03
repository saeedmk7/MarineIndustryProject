package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

public class HomePageReportChartOrganizationDetail implements Serializable {

    public HomePageReportChartOrganizationDetail(){}

    public HomePageReportChartOrganizationDetail(long organizationChartId,
                                                 String organizationChartTitle,
                                                 float total,
                                                 float totalPercent,
                                                 float passed,
                                                 float passedPercent,
                                                 float remaining,
                                                 float remainingPercent) {
        this.organizationChartId = organizationChartId;
        this.organizationChartTitle = organizationChartTitle;
        this.total = total;
        this.totalPercent = totalPercent;
        this.passed = passed;
        this.passedPercent = passedPercent;
        this.remaining = remaining;
        this.remainingPercent = remainingPercent;
    }

    private long organizationChartId;

    private String organizationChartTitle;

    private float total;

    private float totalPercent;

    private float passed;

    private float passedPercent;

    private float remaining;

    private float remainingPercent;

    public long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
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

    public float getPassed() {
        return passed;
    }

    public void setPassed(float passed) {
        this.passed = passed;
    }

    public float getPassedPercent() {
        return passedPercent;
    }

    public void setPassedPercent(float passedPercent) {
        this.passedPercent = passedPercent;
    }

    public float getRemaining() {
        return remaining;
    }

    public void setRemaining(float remaining) {
        this.remaining = remaining;
    }

    public float getRemainingPercent() {
        return remainingPercent;
    }

    public void setRemainingPercent(float remainingPercent) {
        this.remainingPercent = remainingPercent;
    }
}
