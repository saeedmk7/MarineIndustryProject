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
 * Criteria class for the EducationalCenterGradeScore entity. This class is used in EducationalCenterGradeScoreResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /educational-center-grade-scores?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationalCenterGradeScoreCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter score;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter educationalCenterCriteriaId;

    private LongFilter educationalCenterGradeId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getScore() {
        return score;
    }

    public void setScore(IntegerFilter score) {
        this.score = score;
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

    public LongFilter getEducationalCenterCriteriaId() {
        return educationalCenterCriteriaId;
    }

    public void setEducationalCenterCriteriaId(LongFilter educationalCenterCriteriaId) {
        this.educationalCenterCriteriaId = educationalCenterCriteriaId;
    }

    public LongFilter getEducationalCenterGradeId() {
        return educationalCenterGradeId;
    }

    public void setEducationalCenterGradeId(LongFilter educationalCenterGradeId) {
        this.educationalCenterGradeId = educationalCenterGradeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EducationalCenterGradeScoreCriteria that = (EducationalCenterGradeScoreCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(score, that.score) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(educationalCenterCriteriaId, that.educationalCenterCriteriaId) &&
            Objects.equals(educationalCenterGradeId, that.educationalCenterGradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        score,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        educationalCenterCriteriaId,
        educationalCenterGradeId
        );
    }

    @Override
    public String toString() {
        return "EducationalCenterGradeScoreCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (score != null ? "score=" + score + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (educationalCenterCriteriaId != null ? "educationalCenterCriteriaId=" + educationalCenterCriteriaId + ", " : "") +
                (educationalCenterGradeId != null ? "educationalCenterGradeId=" + educationalCenterGradeId + ", " : "") +
            "}";
    }

}
