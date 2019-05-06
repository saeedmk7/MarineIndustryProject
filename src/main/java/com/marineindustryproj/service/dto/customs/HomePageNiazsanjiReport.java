package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class HomePageNiazsanjiReport implements Serializable {

    private Long niazsanjiFardiCount;

    private Long niazsanjiFardiSucceedCount;

    private Long organizationNiazsanjiCount;

    private Long organizationNiazsanjiSucceedCount;

    private Long designAndPlanningStepCount;

    private Long runningStepCount;

    public HomePageNiazsanjiReport(){}
    public HomePageNiazsanjiReport(Long niazsanjiFardiCount,
                                   Long niazsanjiFardiSucceedCount,
                                   Long organizationNiazsanjiCount,
                                   Long organizationNiazsanjiSucceedCount,
                                   Long designAndPlanningStepCount,
                                   Long runningStepCount) {
        this.niazsanjiFardiCount = niazsanjiFardiCount;
        this.niazsanjiFardiSucceedCount = niazsanjiFardiSucceedCount;
        this.organizationNiazsanjiCount = organizationNiazsanjiCount;
        this.organizationNiazsanjiSucceedCount = organizationNiazsanjiSucceedCount;
        this.designAndPlanningStepCount = designAndPlanningStepCount;
        this.runningStepCount = runningStepCount;
    }

    public Long getNiazsanjiFardiCount() {
        return niazsanjiFardiCount;
    }

    public void setNiazsanjiFardiCount(Long niazsanjiFardiCount) {
        this.niazsanjiFardiCount = niazsanjiFardiCount;
    }

    public Long getNiazsanjiFardiSucceedCount() {
        return niazsanjiFardiSucceedCount;
    }

    public void setNiazsanjiFardiSucceedCount(Long niazsanjiFardiSucceedCount) {
        this.niazsanjiFardiSucceedCount = niazsanjiFardiSucceedCount;
    }

    public Long getOrganizationNiazsanjiCount() {
        return organizationNiazsanjiCount;
    }

    public void setOrganizationNiazsanjiCount(Long organizationNiazsanjiCount) {
        this.organizationNiazsanjiCount = organizationNiazsanjiCount;
    }

    public Long getOrganizationNiazsanjiSucceedCount() {
        return organizationNiazsanjiSucceedCount;
    }

    public void setOrganizationNiazsanjiSucceedCount(Long organizationNiazsanjiSucceedCount) {
        this.organizationNiazsanjiSucceedCount = organizationNiazsanjiSucceedCount;
    }

    public Long getDesignAndPlanningStepCount() {
        return designAndPlanningStepCount;
    }

    public void setDesignAndPlanningStepCount(Long designAndPlanningStepCount) {
        this.designAndPlanningStepCount = designAndPlanningStepCount;
    }

    public Long getRunningStepCount() {
        return runningStepCount;
    }

    public void setRunningStepCount(Long runningStepCount) {
        this.runningStepCount = runningStepCount;
    }
}
