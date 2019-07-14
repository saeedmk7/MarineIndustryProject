package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

public class HomePageReportDetail implements Serializable {

    public HomePageReportDetail(Long courseTypeId, Float passed, Float remaining) {
        this.courseTypeId = courseTypeId;
        this.passed = passed;
        this.remaining = remaining;
    }

    private Long courseTypeId;

    private Float passed;

    private Float remaining;

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Float getPassed() {
        return passed;
    }

    public void setPassed(Float passed) {
        this.passed = passed;
    }

    public Float getRemaining() {
        return remaining;
    }

    public void setRemaining(Float remaining) {
        this.remaining = remaining;
    }


}
