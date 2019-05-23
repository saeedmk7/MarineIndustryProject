package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class PlanningAndRunMonthReport implements Serializable {

    public PlanningAndRunMonthReport(Integer month,
                                     Long personHour,
                                     Long personCost,
                                     Integer reportType) {
        this.month = month;
        this.personHour = personHour;
        this.personCost = personCost;
        this.reportType = reportType;
    }

    private Integer month;

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
}
