package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.loader.tools.LibraryScope;

public class HomePageReportOrgDetail implements Serializable {

    public HomePageReportOrgDetail(Long courseTypeId,
                                   Float total,
                                   Float totalPercent,
                                   Float passed,
                                   Float passedPercent,
                                   Float remaining,
                                   Float remainingPercent) {
        this.courseTypeId = courseTypeId;
        this.total = total;
        this.totalPercent = totalPercent;
        this.passed = passed;
        this.passedPercent = passedPercent;
        this.remaining = remaining;
        this.remainingPercent = remainingPercent;
    }

    private Long courseTypeId;

    private Float total;

    private Float totalPercent;

    private Float passed;

    private Float passedPercent;

    private Float remaining;

    private Float remainingPercent;

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
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

    public Float getPassed() {
        return passed;
    }

    public void setPassed(Float passed) {
        this.passed = passed;
    }

    public Float getPassedPercent() {
        return passedPercent;
    }

    public void setPassedPercent(Float passedPercent) {
        this.passedPercent = passedPercent;
    }

    public Float getRemaining() {
        return remaining;
    }

    public void setRemaining(Float remaining) {
        this.remaining = remaining;
    }

    public Float getRemainingPercent() {
        return remainingPercent;
    }

    public void setRemainingPercent(Float remainingPercent) {
        this.remainingPercent = remainingPercent;
    }
}
