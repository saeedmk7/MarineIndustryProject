package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.loader.tools.LibraryScope;

public class HomePageReportOrgDetail implements Serializable {

    public HomePageReportOrgDetail(){}
    public HomePageReportOrgDetail(long courseTypeId,
                                   float total,
                                   float totalPercent,
                                   float passed,
                                   float passedPercent,
                                   float remaining,
                                   float remainingPercent) {
        this.courseTypeId = courseTypeId;
        this.total = total;
        this.totalPercent = totalPercent;
        this.passed = passed;
        this.passedPercent = passedPercent;
        this.remaining = remaining;
        this.remainingPercent = remainingPercent;
    }

    private long courseTypeId;

    private float total;

    private float totalPercent;

    private float passed;

    private float passedPercent;

    private float remaining;

    private float remainingPercent;

    public long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(long courseTypeId) {
        this.courseTypeId = courseTypeId;
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
