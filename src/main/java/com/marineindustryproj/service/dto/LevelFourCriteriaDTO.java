package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LevelFourCriteria entity.
 */
public class LevelFourCriteriaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 1024)
    private String title;

    @NotNull
    private Integer displayOrder;

    private Integer weight;

    private Integer secondWeight;

    @Size(max = 1024)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private String backgroundColor;

    private String colorText;

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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSecondWeight() {
        return secondWeight;
    }

    public void setSecondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LevelFourCriteriaDTO levelFourCriteriaDTO = (LevelFourCriteriaDTO) o;
        if (levelFourCriteriaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelFourCriteriaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelFourCriteriaDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", secondWeight=" + getSecondWeight() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", colorText='" + getColorText() + "'" +
            "}";
    }
}
