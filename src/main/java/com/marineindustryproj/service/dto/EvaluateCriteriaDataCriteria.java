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
 * Criteria class for the EvaluateCriteriaData entity. This class is used in EvaluateCriteriaDataResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /evaluate-criteria-data?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EvaluateCriteriaDataCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private FloatFilter firstNumber;

    private FloatFilter secondNumber;

    private FloatFilter thirdNumber;

    private FloatFilter measureCalc;

    private FloatFilter measureCalcPercent;

    private FloatFilter result;

    private FloatFilter resultPercent;

    private FloatFilter year;

    private FloatFilter month;

    private StringFilter reportTime;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private StringFilter qualityGoal;

    private LongFilter documentId;

    private LongFilter evaluateCriteriaTrainingId;

    private LongFilter organizationChartId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public FloatFilter getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(FloatFilter firstNumber) {
        this.firstNumber = firstNumber;
    }

    public FloatFilter getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(FloatFilter secondNumber) {
        this.secondNumber = secondNumber;
    }

    public FloatFilter getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(FloatFilter thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public FloatFilter getMeasureCalc() {
        return measureCalc;
    }

    public void setMeasureCalc(FloatFilter measureCalc) {
        this.measureCalc = measureCalc;
    }

    public FloatFilter getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public void setMeasureCalcPercent(FloatFilter measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public FloatFilter getResult() {
        return result;
    }

    public void setResult(FloatFilter result) {
        this.result = result;
    }

    public FloatFilter getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(FloatFilter resultPercent) {
        this.resultPercent = resultPercent;
    }

    public FloatFilter getYear() {
        return year;
    }

    public void setYear(FloatFilter year) {
        this.year = year;
    }

    public FloatFilter getMonth() {
        return month;
    }

    public void setMonth(FloatFilter month) {
        this.month = month;
    }

    public StringFilter getReportTime() {
        return reportTime;
    }

    public void setReportTime(StringFilter reportTime) {
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

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getEvaluateCriteriaTrainingId() {
        return evaluateCriteriaTrainingId;
    }

    public void setEvaluateCriteriaTrainingId(LongFilter evaluateCriteriaTrainingId) {
        this.evaluateCriteriaTrainingId = evaluateCriteriaTrainingId;
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
        final EvaluateCriteriaDataCriteria that = (EvaluateCriteriaDataCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
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
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(evaluateCriteriaTrainingId, that.evaluateCriteriaTrainingId) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
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
        documentId,
        evaluateCriteriaTrainingId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaDataCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
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
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (evaluateCriteriaTrainingId != null ? "evaluateCriteriaTrainingId=" + evaluateCriteriaTrainingId + ", " : "") +
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
