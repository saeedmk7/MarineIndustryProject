package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class PlanningAndRunMonthReport implements Serializable {

    public PlanningAndRunMonthReport(Integer month,
                                     Long totalHour,
                                     Long totalPriceCost,
                                     Long personHour,
                                     Long personCost,
                                     Integer reportType) {
        this.month = month;
        this.totalHour = totalHour;
        this.totalPriceCost = totalPriceCost;
        this.personHour = personHour;
        this.personCost = personCost;
        this.reportType = reportType;
    }

    private Integer month;

    private Long totalHour;

    private Long totalPriceCost;

    private Long personHour;

    private Long personCost;

    private Integer reportType;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getPersonHour() {
        return personHour;
    }

    public void setPersonHour(Long personHour) {
        this.personHour = personHour;
    }

    public Long getPersonCost() {
        return personCost;
    }

    public void setPersonCost(Long personCost) {
        this.personCost = personCost;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Long getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Long totalHour) {
        this.totalHour = totalHour;
    }

    public Long getTotalPriceCost() {
        return totalPriceCost;
    }

    public void setTotalPriceCost(Long totalPriceCost) {
        this.totalPriceCost = totalPriceCost;
    }
}
