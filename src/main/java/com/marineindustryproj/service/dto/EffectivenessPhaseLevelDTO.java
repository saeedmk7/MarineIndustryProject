package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.UnitOfMeasurement;

/**
 * A DTO for the EffectivenessPhaseLevel entity.
 */
public class EffectivenessPhaseLevelDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    @NotNull
    private Integer effectivenessLevel;

    @NotNull
    private Integer effectivenessLevelUse;

    @NotNull
    private Float weight;

    private Float goal;

    private UnitOfMeasurement unitOfMeasurement;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

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

    public Integer getEffectivenessLevel() {
        return effectivenessLevel;
    }

    public void setEffectivenessLevel(Integer effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
    }

    public Integer getEffectivenessLevelUse() {
        return effectivenessLevelUse;
    }

    public void setEffectivenessLevelUse(Integer effectivenessLevelUse) {
        this.effectivenessLevelUse = effectivenessLevelUse;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getGoal() {
        return goal;
    }

    public void setGoal(Float goal) {
        this.goal = goal;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO = (EffectivenessPhaseLevelDTO) o;
        if (effectivenessPhaseLevelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), effectivenessPhaseLevelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EffectivenessPhaseLevelDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", effectivenessLevel=" + getEffectivenessLevel() +
            ", effectivenessLevelUse=" + getEffectivenessLevelUse() +
            ", weight=" + getWeight() +
            ", goal=" + getGoal() +
            ", unitOfMeasurement='" + getUnitOfMeasurement() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
