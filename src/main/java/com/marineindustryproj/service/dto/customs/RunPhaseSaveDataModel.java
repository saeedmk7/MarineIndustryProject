package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RunPhaseSaveDataModel implements Serializable {

    private Long runPhaseId;

    private String description;

    private Integer finalizeCost;

    private Integer stepNumber;

    private Boolean done;

    private Integer status;

    private Integer runMonth;

    private Long finalNiazsanjiReportId;

    private Set<RunPhaseSaveDataItemModel> runPhaseSaveDataItemModels = new HashSet<>();

    public Long getRunPhaseId() {
        return runPhaseId;
    }

    public void setRunPhaseId(Long runPhaseId) {
        this.runPhaseId = runPhaseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public Set<RunPhaseSaveDataItemModel> getRunPhaseSaveDataItemModels() {
        return runPhaseSaveDataItemModels;
    }

    public void setRunPhaseSaveDataItemModels(Set<RunPhaseSaveDataItemModel> runPhaseSaveDataItemModels) {
        this.runPhaseSaveDataItemModels = runPhaseSaveDataItemModels;
    }

    public Integer getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(Integer runMonth) {
        this.runMonth = runMonth;
    }
}
