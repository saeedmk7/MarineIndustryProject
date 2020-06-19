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
 * Criteria class for the EffectivenessPhase entity. This class is used in EffectivenessPhaseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /effectiveness-phases?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EffectivenessPhaseCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter finishPhaseDate;

    private ZonedDateTimeFilter startPhaseDate;

    private FloatFilter firstScore;

    private FloatFilter secondScore;

    private FloatFilter finalScore;

    private FloatFilter weightedPoints;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private IntegerFilter status;

    private StringFilter startPhaseUserLogin;

    private StringFilter finishPhaseUserLogin;

    private LongFilter documentId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter effectivenessPhaseLevelId;

    private LongFilter organizationChartId;

    private IntegerFilter niazsanjiYear;

    private LongFilter courseTypeId;

    private StringFilter educationalModuleCode;

    private StringFilter educationalModuleTitle;

    private IntegerFilter selectedEffectivenessPhaseLevel;

    private IntegerFilter currentEffectivenessPhaseLevel;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getFinishPhaseDate() {
        return finishPhaseDate;
    }

    public void setFinishPhaseDate(ZonedDateTimeFilter finishPhaseDate) {
        this.finishPhaseDate = finishPhaseDate;
    }

    public ZonedDateTimeFilter getStartPhaseDate() {
        return startPhaseDate;
    }

    public void setStartPhaseDate(ZonedDateTimeFilter startPhaseDate) {
        this.startPhaseDate = startPhaseDate;
    }

    public FloatFilter getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(FloatFilter firstScore) {
        this.firstScore = firstScore;
    }

    public FloatFilter getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(FloatFilter secondScore) {
        this.secondScore = secondScore;
    }

    public FloatFilter getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(FloatFilter finalScore) {
        this.finalScore = finalScore;
    }

    public FloatFilter getWeightedPoints() {
        return weightedPoints;
    }

    public void setWeightedPoints(FloatFilter weightedPoints) {
        this.weightedPoints = weightedPoints;
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

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public StringFilter getStartPhaseUserLogin() {
        return startPhaseUserLogin;
    }

    public void setStartPhaseUserLogin(StringFilter startPhaseUserLogin) {
        this.startPhaseUserLogin = startPhaseUserLogin;
    }

    public StringFilter getFinishPhaseUserLogin() {
        return finishPhaseUserLogin;
    }

    public void setFinishPhaseUserLogin(StringFilter finishPhaseUserLogin) {
        this.finishPhaseUserLogin = finishPhaseUserLogin;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public LongFilter getEffectivenessPhaseLevelId() {
        return effectivenessPhaseLevelId;
    }

    public void setEffectivenessPhaseLevelId(LongFilter effectivenessPhaseLevelId) {
        this.effectivenessPhaseLevelId = effectivenessPhaseLevelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EffectivenessPhaseCriteria that = (EffectivenessPhaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(finishPhaseDate, that.finishPhaseDate) &&
            Objects.equals(startPhaseDate, that.startPhaseDate) &&
            Objects.equals(firstScore, that.firstScore) &&
            Objects.equals(secondScore, that.secondScore) &&
            Objects.equals(finalScore, that.finalScore) &&
            Objects.equals(weightedPoints, that.weightedPoints) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(startPhaseUserLogin, that.startPhaseUserLogin) &&
            Objects.equals(finishPhaseUserLogin, that.finishPhaseUserLogin) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(educationalModuleCode, that.educationalModuleCode) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(selectedEffectivenessPhaseLevel, that.selectedEffectivenessPhaseLevel) &&
            Objects.equals(currentEffectivenessPhaseLevel, that.currentEffectivenessPhaseLevel) &&
            Objects.equals(effectivenessPhaseLevelId, that.effectivenessPhaseLevelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        finishPhaseDate,
        startPhaseDate,
        firstScore,
        secondScore,
        finalScore,
        weightedPoints,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        status,
        startPhaseUserLogin,
        finishPhaseUserLogin,
        documentId,
        finalNiazsanjiReportId,
        organizationChartId,
        niazsanjiYear,
        courseTypeId,
        educationalModuleCode,
        educationalModuleTitle,
        currentEffectivenessPhaseLevel,
        selectedEffectivenessPhaseLevel,
        effectivenessPhaseLevelId
        );
    }

    @Override
    public String toString() {
        return "EffectivenessPhaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (finishPhaseDate != null ? "finishPhaseDate=" + finishPhaseDate + ", " : "") +
                (startPhaseDate != null ? "startPhaseDate=" + startPhaseDate + ", " : "") +
                (firstScore != null ? "firstScore=" + firstScore + ", " : "") +
                (secondScore != null ? "secondScore=" + secondScore + ", " : "") +
                (finalScore != null ? "finalScore=" + finalScore + ", " : "") +
                (weightedPoints != null ? "weightedPoints=" + weightedPoints + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (startPhaseUserLogin != null ? "startPhaseUserLogin=" + startPhaseUserLogin + ", " : "") +
                (finishPhaseUserLogin != null ? "finishPhaseUserLogin=" + finishPhaseUserLogin + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (effectivenessPhaseLevelId != null ? "effectivenessPhaseLevelId=" + effectivenessPhaseLevelId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (educationalModuleCode != null ? "educationalModuleCode=" + educationalModuleCode + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (selectedEffectivenessPhaseLevel != null ? "selectedEffectivenessPhaseLevel=" + selectedEffectivenessPhaseLevel + ", " : "") +
                (currentEffectivenessPhaseLevel != null ? "currentEffectivenessPhaseLevel=" + currentEffectivenessPhaseLevel + ", " : "") +
            "}";
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public StringFilter getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(StringFilter educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }

    public StringFilter getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(StringFilter educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public IntegerFilter getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(IntegerFilter niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public IntegerFilter getSelectedEffectivenessPhaseLevel() {
        return selectedEffectivenessPhaseLevel;
    }

    public void setSelectedEffectivenessPhaseLevel(IntegerFilter selectedEffectivenessPhaseLevel) {
        this.selectedEffectivenessPhaseLevel = selectedEffectivenessPhaseLevel;
    }

    public IntegerFilter getCurrentEffectivenessPhaseLevel() {
        return currentEffectivenessPhaseLevel;
    }

    public void setCurrentEffectivenessPhaseLevel(IntegerFilter currentEffectivenessPhaseLevel) {
        this.currentEffectivenessPhaseLevel = currentEffectivenessPhaseLevel;
    }
}
