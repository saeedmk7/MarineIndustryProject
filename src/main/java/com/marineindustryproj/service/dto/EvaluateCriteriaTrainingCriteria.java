package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the EvaluateCriteriaTraining entity. This class is used in EvaluateCriteriaTrainingResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /evaluate-criteria-trainings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EvaluateCriteriaTrainingCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter processTitle;

    private IntegerFilter processWeight;

    private StringFilter activityDescription;

    private IntegerFilter criteriaWeight;

    private StringFilter measureDescription;

    private IntegerFilter firstNumber;

    private IntegerFilter secondNumber;

    private IntegerFilter thirdNumber;

    private IntegerFilter measureCalc;

    private IntegerFilter measureCalcPercent;

    private IntegerFilter result;

    private IntegerFilter resultPercent;

    private IntegerFilter year;

    private IntegerFilter month;

    private IntegerFilter reportTime;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private StringFilter qualityGoal;

    private LongFilter evaluateCriteriaDataId;

    private LongFilter documentId;

    private LongFilter organizationChartId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(StringFilter processTitle) {
        this.processTitle = processTitle;
    }

    public IntegerFilter getProcessWeight() {
        return processWeight;
    }

    public void setProcessWeight(IntegerFilter processWeight) {
        this.processWeight = processWeight;
    }

    public StringFilter getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(StringFilter activityDescription) {
        this.activityDescription = activityDescription;
    }

    public IntegerFilter getCriteriaWeight() {
        return criteriaWeight;
    }

    public void setCriteriaWeight(IntegerFilter criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
    }

    public StringFilter getMeasureDescription() {
        return measureDescription;
    }

    public void setMeasureDescription(StringFilter measureDescription) {
        this.measureDescription = measureDescription;
    }

    public IntegerFilter getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(IntegerFilter firstNumber) {
        this.firstNumber = firstNumber;
    }

    public IntegerFilter getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(IntegerFilter secondNumber) {
        this.secondNumber = secondNumber;
    }

    public IntegerFilter getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(IntegerFilter thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public IntegerFilter getMeasureCalc() {
        return measureCalc;
    }

    public void setMeasureCalc(IntegerFilter measureCalc) {
        this.measureCalc = measureCalc;
    }

    public IntegerFilter getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public void setMeasureCalcPercent(IntegerFilter measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public IntegerFilter getResult() {
        return result;
    }

    public void setResult(IntegerFilter result) {
        this.result = result;
    }

    public IntegerFilter getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(IntegerFilter resultPercent) {
        this.resultPercent = resultPercent;
    }

    public IntegerFilter getYear() {
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public IntegerFilter getMonth() {
        return month;
    }

    public void setMonth(IntegerFilter month) {
        this.month = month;
    }

    public IntegerFilter getReportTime() {
        return reportTime;
    }

    public void setReportTime(IntegerFilter reportTime) {
        this.reportTime = reportTime;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(StringFilter createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTimeFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTimeFilter createDate) {
        this.createDate = createDate;
    }

    public StringFilter getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(StringFilter modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTimeFilter getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTimeFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public LongFilter getEvaluateCriteriaDataId() {
        return evaluateCriteriaDataId;
    }

    public void setEvaluateCriteriaDataId(LongFilter evaluateCriteriaDataId) {
        this.evaluateCriteriaDataId = evaluateCriteriaDataId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EvaluateCriteriaTrainingCriteria that = (EvaluateCriteriaTrainingCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(processTitle, that.processTitle) &&
            Objects.equals(processWeight, that.processWeight) &&
            Objects.equals(activityDescription, that.activityDescription) &&
            Objects.equals(criteriaWeight, that.criteriaWeight) &&
            Objects.equals(measureDescription, that.measureDescription) &&
            Objects.equals(firstNumber, that.firstNumber) &&
            Objects.equals(secondNumber, that.secondNumber) &&
            Objects.equals(thirdNumber, that.thirdNumber) &&
            Objects.equals(measureCalc, that.measureCalc) &&
            Objects.equals(measureCalcPercent, that.measureCalcPercent) &&
            Objects.equals(result, that.result) &&
            Objects.equals(resultPercent, that.resultPercent) &&
            Objects.equals(year, that.year) &&
            Objects.equals(month, that.month) &&
            Objects.equals(reportTime, that.reportTime) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(evaluateCriteriaDataId, that.evaluateCriteriaDataId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(qualityGoal, that.qualityGoal) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        processTitle,
        processWeight,
        activityDescription,
        criteriaWeight,
        measureDescription,
        firstNumber,
        secondNumber,
        thirdNumber,
        measureCalc,
        measureCalcPercent,
        result,
        resultPercent,
        year,
        month,
        reportTime,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        evaluateCriteriaDataId,
        documentId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaTrainingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (processTitle != null ? "processTitle=" + processTitle + ", " : "") +
                (processWeight != null ? "processWeight=" + processWeight + ", " : "") +
                (activityDescription != null ? "activityDescription=" + activityDescription + ", " : "") +
                (criteriaWeight != null ? "criteriaWeight=" + criteriaWeight + ", " : "") +
                (measureDescription != null ? "measureDescription=" + measureDescription + ", " : "") +
                (firstNumber != null ? "firstNumber=" + firstNumber + ", " : "") +
                (secondNumber != null ? "secondNumber=" + secondNumber + ", " : "") +
                (thirdNumber != null ? "thirdNumber=" + thirdNumber + ", " : "") +
                (measureCalc != null ? "measureCalc=" + measureCalc + ", " : "") +
                (measureCalcPercent != null ? "measureCalcPercent=" + measureCalcPercent + ", " : "") +
                (result != null ? "result=" + result + ", " : "") +
                (resultPercent != null ? "resultPercent=" + resultPercent + ", " : "") +
                (year != null ? "year=" + year + ", " : "") +
                (month != null ? "month=" + month + ", " : "") +
                (reportTime != null ? "reportTime=" + reportTime + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (evaluateCriteriaDataId != null ? "evaluateCriteriaDataId=" + evaluateCriteriaDataId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

    public StringFilter getQualityGoal() {
        return qualityGoal;
    }

    public void setQualityGoal(StringFilter qualityGoal) {
        this.qualityGoal = qualityGoal;
    }
}
