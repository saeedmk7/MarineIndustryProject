package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class ChartResultDetail implements Serializable {

    private Long groupId;

    private String industryTitle;

    private List<Long> orgChartIds;

    private Long totalPersonHour;

    private Long totalPriceCost;

    private Long priceCostNew;

    private Long educationalModuleTotalHourNew;

    private Long priceCostFinished;

    private Long educationalModuleTotalHourFinished;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getIndustryTitle() {
        return industryTitle;
    }

    public void setIndustryTitle(String industryTitle) {
        this.industryTitle = industryTitle;
    }

    public List<Long> getOrgChartIds() {
        return orgChartIds;
    }

    public void setOrgChartIds(List<Long> orgChartIds) {
        this.orgChartIds = orgChartIds;
    }

    public Long getTotalPersonHour() {
        return totalPersonHour;
    }

    public void setTotalPersonHour(Long totalPersonHour) {
        this.totalPersonHour = totalPersonHour;
    }

    public Long getTotalPriceCost() {
        return totalPriceCost;
    }

    public void setTotalPriceCost(Long totalPriceCost) {
        this.totalPriceCost = totalPriceCost;
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
