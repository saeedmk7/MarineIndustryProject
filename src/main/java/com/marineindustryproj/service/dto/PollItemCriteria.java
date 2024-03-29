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
 * Criteria class for the PollItem entity. This class is used in PollItemResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /poll-items?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PollItemCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter displayOrder;

    private IntegerFilter coefficient;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter pollScoreId;

    private LongFilter criterionId;

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

    public IntegerFilter getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(IntegerFilter displayOrder) {
        this.displayOrder = displayOrder;
    }

    public IntegerFilter getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(IntegerFilter coefficient) {
        this.coefficient = coefficient;
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

    public LongFilter getPollScoreId() {
        return pollScoreId;
    }

    public void setPollScoreId(LongFilter pollScoreId) {
        this.pollScoreId = pollScoreId;
    }

    public LongFilter getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(LongFilter criterionId) {
        this.criterionId = criterionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PollItemCriteria that = (PollItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(displayOrder, that.displayOrder) &&
            Objects.equals(coefficient, that.coefficient) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(pollScoreId, that.pollScoreId) &&
            Objects.equals(criterionId, that.criterionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        displayOrder,
        coefficient,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        pollScoreId,
        criterionId
        );
    }

    @Override
    public String toString() {
        return "PollItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (displayOrder != null ? "displayOrder=" + displayOrder + ", " : "") +
                (coefficient != null ? "coefficient=" + coefficient + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (pollScoreId != null ? "pollScoreId=" + pollScoreId + ", " : "") +
                (criterionId != null ? "criterionId=" + criterionId + ", " : "") +
            "}";
    }

}
