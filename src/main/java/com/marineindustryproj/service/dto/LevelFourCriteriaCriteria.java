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
 * Criteria class for the LevelFourCriteria entity. This class is used in LevelFourCriteriaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /level-four-criteria?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LevelFourCriteriaCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter displayOrder;

    private IntegerFilter weight;

    private IntegerFilter secondWeight;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter backgroundColor;

    private StringFilter colorText;

    private LongFilter levelFourScoreId;

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

    public StringFilter getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(StringFilter backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public StringFilter getColorText() {
        return colorText;
    }

    public void setColorText(StringFilter colorText) {
        this.colorText = colorText;
    }

    public LongFilter getLevelFourScoreId() {
        return levelFourScoreId;
    }

    public void setLevelFourScoreId(LongFilter levelFourScoreId) {
        this.levelFourScoreId = levelFourScoreId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LevelFourCriteriaCriteria that = (LevelFourCriteriaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(displayOrder, that.displayOrder) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(secondWeight, that.secondWeight) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(backgroundColor, that.backgroundColor) &&
            Objects.equals(colorText, that.colorText) &&
            Objects.equals(levelFourScoreId, that.levelFourScoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        displayOrder,
        weight,
        secondWeight,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        backgroundColor,
        colorText,
        levelFourScoreId
        );
    }

    @Override
    public String toString() {
        return "LevelFourCriteriaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (displayOrder != null ? "displayOrder=" + displayOrder + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (secondWeight != null ? "secondWeight=" + secondWeight + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (backgroundColor != null ? "backgroundColor=" + backgroundColor + ", " : "") +
                (colorText != null ? "colorText=" + colorText + ", " : "") +
                (levelFourScoreId != null ? "levelFourScoreId=" + levelFourScoreId + ", " : "") +
            "}";
    }

}
