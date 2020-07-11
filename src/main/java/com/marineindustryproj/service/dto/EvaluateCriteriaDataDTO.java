package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the EvaluateCriteriaData entity.
 */
public class EvaluateCriteriaDataDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    private Float firstNumber;

    private Float secondNumber;

    private Float thirdNumber;

    private Float measureCalc;

    private Float measureCalcPercent;

    private Float result;

    private Float resultPercent;

    @NotNull
    private Float year;

    @NotNull
    private Float month;

    @Lob
    private String fileDoc;

    @Size(max = 50)
    private String reportTime;

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

    private Long evaluateCriteriaTrainingId;

    private String evaluateCriteriaTrainingProcessTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Float getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Float getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Float getMeasureCalc() {
        return measureCalc;
    }

    public void setMeasureCalc(Float measureCalc) {
        this.measureCalc = measureCalc;
    }

    public Float getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public void setMeasureCalcPercent(Float measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public Float getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(Float resultPercent) {
        this.resultPercent = resultPercent;
    }

    public Float getYear() {
        return year;
    }

    public void setYear(Float year) {
        this.year = year;
    }

    public Float getMonth() {
        return month;
    }

    public void setMonth(Float month) {
        this.month = month;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
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

    public Long getEvaluateCriteriaTrainingId() {
        return evaluateCriteriaTrainingId;
    }

    public void setEvaluateCriteriaTrainingId(Long evaluateCriteriaTrainingId) {
        this.evaluateCriteriaTrainingId = evaluateCriteriaTrainingId;
    }

    public String getEvaluateCriteriaTrainingProcessTitle() {
        return evaluateCriteriaTrainingProcessTitle;
    }

    public void setEvaluateCriteriaTrainingProcessTitle(String evaluateCriteriaTrainingProcessTitle) {
        this.evaluateCriteriaTrainingProcessTitle = evaluateCriteriaTrainingProcessTitle;
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

        EvaluateCriteriaDataDTO evaluateCriteriaDataDTO = (EvaluateCriteriaDataDTO) o;
        if (evaluateCriteriaDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateCriteriaDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaDataDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", measureCalc=" + getMeasureCalc() +
            ", measureCalcPercent=" + getMeasureCalcPercent() +
            ", result=" + getResult() +
            ", resultPercent=" + getResultPercent() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", evaluateCriteriaTraining=" + getEvaluateCriteriaTrainingId() +
            ", evaluateCriteriaTraining='" + getEvaluateCriteriaTrainingProcessTitle() + "'" +
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
