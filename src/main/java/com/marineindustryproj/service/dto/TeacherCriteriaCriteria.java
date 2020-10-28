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
 * Criteria class for the TeacherCriteria entity. This class is used in TeacherCriteriaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /teacher-criteria?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TeacherCriteriaCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter group;

    private StringFilter title;

    private IntegerFilter displayOrder;

    private IntegerFilter weight;

    private IntegerFilter secondWeight;

    private StringFilter description;

    private IntegerFilter peopleCount;

    private StringFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter teacherGradeScoreId;

    private LongFilter teacherCriteriaGroupId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getGroup() {
        return group;
    }

    public void setGroup(StringFilter group) {
        this.group = group;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public IntegerFilter getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(IntegerFilter displayOrder) {
        this.displayOrder = displayOrder;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getSecondWeight() {
        return secondWeight;
    }

    public void setSecondWeight(IntegerFilter secondWeight) {
        this.secondWeight = secondWeight;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(IntegerFilter peopleCount) {
        this.peopleCount = peopleCount;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public LongFilter getTeacherGradeScoreId() {
        return teacherGradeScoreId;
    }

    public void setTeacherGradeScoreId(LongFilter teacherGradeScoreId) {
        this.teacherGradeScoreId = teacherGradeScoreId;
    }

    public LongFilter getTeacherCriteriaGroupId() {
        return teacherCriteriaGroupId;
    }

    public void setTeacherCriteriaGroupId(LongFilter teacherCriteriaGroupId) {
        this.teacherCriteriaGroupId = teacherCriteriaGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TeacherCriteriaCriteria that = (TeacherCriteriaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(group, that.group) &&
            Objects.equals(title, that.title) &&
            Objects.equals(displayOrder, that.displayOrder) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(secondWeight, that.secondWeight) &&
            Objects.equals(description, that.description) &&
            Objects.equals(peopleCount, that.peopleCount) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(teacherGradeScoreId, that.teacherGradeScoreId) &&
            Objects.equals(teacherCriteriaGroupId, that.teacherCriteriaGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        group,
        title,
        displayOrder,
        weight,
        secondWeight,
        description,
        peopleCount,
        code,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        teacherGradeScoreId,
        teacherCriteriaGroupId
        );
    }

    @Override
    public String toString() {
        return "TeacherCriteriaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (group != null ? "group=" + group + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (displayOrder != null ? "displayOrder=" + displayOrder + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (secondWeight != null ? "secondWeight=" + secondWeight + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (peopleCount != null ? "peopleCount=" + peopleCount + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (teacherGradeScoreId != null ? "teacherGradeScoreId=" + teacherGradeScoreId + ", " : "") +
                (teacherCriteriaGroupId != null ? "teacherCriteriaGroupId=" + teacherCriteriaGroupId + ", " : "") +
            "}";
    }

}
