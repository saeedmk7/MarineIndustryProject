package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

import com.marineindustryproj.domain.EducationalModule;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class ChartResult implements Serializable {



    private Long groupId;

    private Long priceCostNew;

    private Long educationalModuleTotalHourNew;

    private Long priceCostFinished;

    private Long educationalModuleTotalHourFinished;

    @Override
    public String toString() {
        return "ChartResult{" +
            "priceCostNew=" + getPriceCostNew() +
            ", groupId=" + getGroupId() +
            ", educationalModuleTotalHourNew=" + getEducationalModuleTotalHourNew() +
            ", priceCostFinished=" + getPriceCostFinished() +
            ", educationalModuleTotalHourFinished=" + getEducationalModuleTotalHourFinished() +
            "}";
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPriceCostNew() {
        return priceCostNew;
    }

    public void setPriceCostNew(Long priceCostNew) {
        this.priceCostNew = priceCostNew;
    }

    public Long getEducationalModuleTotalHourNew() {
        return educationalModuleTotalHourNew;
    }

    public void setEducationalModuleTotalHourNew(Long educationalModuleTotalHourNew) {
        this.educationalModuleTotalHourNew = educationalModuleTotalHourNew;
    }

    public Long getPriceCostFinished() {
        return priceCostFinished;
    }

    public void setPriceCostFinished(Long priceCostFinished) {
        this.priceCostFinished = priceCostFinished;
    }

    public Long getEducationalModuleTotalHourFinished() {
        return educationalModuleTotalHourFinished;
    }

    public void setEducationalModuleTotalHourFinished(Long educationalModuleTotalHourFinished) {
        this.educationalModuleTotalHourFinished = educationalModuleTotalHourFinished;
    }
}
