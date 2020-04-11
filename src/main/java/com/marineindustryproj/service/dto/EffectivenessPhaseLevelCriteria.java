package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.UnitOfMeasurement;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the EffectivenessPhaseLevel entity. This class is used in EffectivenessPhaseLevelResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /effectiveness-phase-levels?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EffectivenessPhaseLevelCriteria implements Serializable {
    /**
     * Class for filtering UnitOfMeasurement
     */
    public static class UnitOfMeasurementFilter extends Filter<UnitOfMeasurement> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter effectivenessLevel;

    private IntegerFilter effectivenessLevelUse;

    private FloatFilter weight;

    private FloatFilter goal;

    private UnitOfMeasurementFilter unitOfMeasurement;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter effectivenessPhaseId;

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

    public IntegerFilter getEffectivenessLevel() {
        return effectivenessLevel;
    }

    public void setEffectivenessLevel(IntegerFilter effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
    }

    public IntegerFilter getEffectivenessLevelUse() {
        return effectivenessLevelUse;
    }

    public void setEffectivenessLevelUse(IntegerFilter effectivenessLevelUse) {
        this.effectivenessLevelUse = effectivenessLevelUse;
    }

    public FloatFilter getWeight() {
        return weight;
    }

    public void setWeight(FloatFilter weight) {
        this.weight = weight;
    }

    public FloatFilter getGoal() {
        return goal;
    }

    public void setGoal(FloatFilter goal) {
        this.goal = goal;
    }

    public UnitOfMeasurementFilter getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurementFilter unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
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

    public LongFilter getEffectivenessPhaseId() {
        return effectivenessPhaseId;
    }

    public void setEffectivenessPhaseId(LongFilter effectivenessPhaseId) {
        this.effectivenessPhaseId = effectivenessPhaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EffectivenessPhaseLevelCriteria that = (EffectivenessPhaseLevelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(effectivenessLevel, that.effectivenessLevel) &&
            Objects.equals(effectivenessLevelUse, that.effectivenessLevelUse) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(goal, that.goal) &&
            Objects.equals(unitOfMeasurement, that.unitOfMeasurement) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(effectivenessPhaseId, that.effectivenessPhaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        effectivenessLevel,
        effectivenessLevelUse,
        weight,
        goal,
        unitOfMeasurement,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        effectivenessPhaseId
        );
    }

    @Override
    public String toString() {
        return "EffectivenessPhaseLevelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (effectivenessLevel != null ? "effectivenessLevel=" + effectivenessLevel + ", " : "") +
                (effectivenessLevelUse != null ? "effectivenessLevelUse=" + effectivenessLevelUse + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (goal != null ? "goal=" + goal + ", " : "") +
                (unitOfMeasurement != null ? "unitOfMeasurement=" + unitOfMeasurement + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (effectivenessPhaseId != null ? "effectivenessPhaseId=" + effectivenessPhaseId + ", " : "") +
            "}";
    }

}
