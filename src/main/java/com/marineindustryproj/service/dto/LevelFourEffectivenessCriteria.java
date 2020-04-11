package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.Grade;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the LevelFourEffectiveness entity. This class is used in LevelFourEffectivenessResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /level-four-effectivenesses?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LevelFourEffectivenessCriteria implements Serializable {
    /**
     * Class for filtering Grade
     */
    public static class GradeFilter extends Filter<Grade> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private FloatFilter totalScore;

    private FloatFilter totalScorePercent;

    private GradeFilter grade;

    private StringFilter evaluateDate;

    private IntegerFilter year;

    private IntegerFilter month;

    private StringFilter strength;

    private StringFilter improvement;

    private StringFilter whatDoYouDo;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter levelFourScoreId;

    private LongFilter documentId;

    private LongFilter finalNiazsanjiReportPersonId;

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

    public FloatFilter getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(FloatFilter totalScore) {
        this.totalScore = totalScore;
    }

    public FloatFilter getTotalScorePercent() {
        return totalScorePercent;
    }

    public void setTotalScorePercent(FloatFilter totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public GradeFilter getGrade() {
        return grade;
    }

    public void setGrade(GradeFilter grade) {
        this.grade = grade;
    }

    public StringFilter getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(StringFilter evaluateDate) {
        this.evaluateDate = evaluateDate;
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

    public StringFilter getStrength() {
        return strength;
    }

    public void setStrength(StringFilter strength) {
        this.strength = strength;
    }

    public StringFilter getImprovement() {
        return improvement;
    }

    public void setImprovement(StringFilter improvement) {
        this.improvement = improvement;
    }

    public StringFilter getWhatDoYouDo() {
        return whatDoYouDo;
    }

    public void setWhatDoYouDo(StringFilter whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
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

    public LongFilter getLevelFourScoreId() {
        return levelFourScoreId;
    }

    public void setLevelFourScoreId(LongFilter levelFourScoreId) {
        this.levelFourScoreId = levelFourScoreId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getFinalNiazsanjiReportPersonId() {
        return finalNiazsanjiReportPersonId;
    }

    public void setFinalNiazsanjiReportPersonId(LongFilter finalNiazsanjiReportPersonId) {
        this.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LevelFourEffectivenessCriteria that = (LevelFourEffectivenessCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(totalScore, that.totalScore) &&
            Objects.equals(totalScorePercent, that.totalScorePercent) &&
            Objects.equals(grade, that.grade) &&
            Objects.equals(evaluateDate, that.evaluateDate) &&
            Objects.equals(year, that.year) &&
            Objects.equals(month, that.month) &&
            Objects.equals(strength, that.strength) &&
            Objects.equals(improvement, that.improvement) &&
            Objects.equals(whatDoYouDo, that.whatDoYouDo) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(levelFourScoreId, that.levelFourScoreId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(finalNiazsanjiReportPersonId, that.finalNiazsanjiReportPersonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        totalScore,
        totalScorePercent,
        grade,
        evaluateDate,
        year,
        month,
        strength,
        improvement,
        whatDoYouDo,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        levelFourScoreId,
        documentId,
        finalNiazsanjiReportPersonId
        );
    }

    @Override
    public String toString() {
        return "LevelFourEffectivenessCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (totalScore != null ? "totalScore=" + totalScore + ", " : "") +
                (totalScorePercent != null ? "totalScorePercent=" + totalScorePercent + ", " : "") +
                (grade != null ? "grade=" + grade + ", " : "") +
                (evaluateDate != null ? "evaluateDate=" + evaluateDate + ", " : "") +
                (year != null ? "year=" + year + ", " : "") +
                (month != null ? "month=" + month + ", " : "") +
                (strength != null ? "strength=" + strength + ", " : "") +
                (improvement != null ? "improvement=" + improvement + ", " : "") +
                (whatDoYouDo != null ? "whatDoYouDo=" + whatDoYouDo + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (levelFourScoreId != null ? "levelFourScoreId=" + levelFourScoreId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (finalNiazsanjiReportPersonId != null ? "finalNiazsanjiReportPersonId=" + finalNiazsanjiReportPersonId + ", " : "") +
            "}";
    }

}
