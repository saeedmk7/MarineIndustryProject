package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EvaluateCriteriaTraining entity.
 */
public class EvaluateCriteriaTrainingDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String processTitle;

    @NotNull
    private Integer processWeight;

    @Size(max = 4096)
    private String activityDescription;

    private Integer criteriaWeight;

    @Size(max = 4096)
    private String measureDescription;

    private Integer firstNumber;

    private Integer secondNumber;

    private Integer thirdNumber;

    private Integer measureCalc;

    private Integer measureCalcPercent;

    private Integer result;

    private Integer resultPercent;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer reportTime;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    @Size(max = 4096)
    private String qualityGoal;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long organizationChartId;

    private String organizationChartTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(String processTitle) {
        this.processTitle = processTitle;
    }

    public Integer getProcessWeight() {
        return processWeight;
    }

    public void setProcessWeight(Integer processWeight) {
        this.processWeight = processWeight;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Integer getCriteriaWeight() {
        return criteriaWeight;
    }

    public void setCriteriaWeight(Integer criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
    }

    public String getMeasureDescription() {
        return measureDescription;
    }

    public void setMeasureDescription(String measureDescription) {
        this.measureDescription = measureDescription;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Integer getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(Integer thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Integer getMeasureCalc() {
        return measureCalc;
    }

    public void setMeasureCalc(Integer measureCalc) {
        this.measureCalc = measureCalc;
    }

    public Integer getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public void setMeasureCalcPercent(Integer measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(Integer resultPercent) {
        this.resultPercent = resultPercent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO = (EvaluateCriteriaTrainingDTO) o;
        if (evaluateCriteriaTrainingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateCriteriaTrainingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaTrainingDTO{" +
            "id=" + getId() +
            ", processTitle='" + getProcessTitle() + "'" +
            ", processWeight=" + getProcessWeight() +
            ", activityDescription='" + getActivityDescription() + "'" +
            ", criteriaWeight=" + getCriteriaWeight() +
            ", measureDescription='" + getMeasureDescription() + "'" +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", measureCalc=" + getMeasureCalc() +
            ", measureCalcPercent=" + getMeasureCalcPercent() +
            ", result=" + getResult() +
            ", resultPercent=" + getResultPercent() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", reportTime=" + getReportTime() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }

    public String getQualityGoal() {
        return qualityGoal;
    }

    public void setQualityGoal(String qualityGoal) {
        this.qualityGoal = qualityGoal;
    }
}
