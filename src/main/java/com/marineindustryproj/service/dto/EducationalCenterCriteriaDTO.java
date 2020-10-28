package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EducationalCenterCriteria entity.
 */
public class EducationalCenterCriteriaDTO implements Serializable {

    private Long id;

    @Size(max = 1024)
    private String group;

    @NotNull
    @Size(max = 1024)
    private String title;

    @NotNull
    private Integer displayOrder;

    private Integer weight;

    @Size(max = 1024)
    private String description;

    private Integer peopleCount;

    @Size(max = 100)
    private String code;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long educationalCenterGroupId;

    private String educationalCenterGroupTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getEducationalCenterGroupId() {
        return educationalCenterGroupId;
    }

    public void setEducationalCenterGroupId(Long educationalCenterGroupId) {
        this.educationalCenterGroupId = educationalCenterGroupId;
    }

    public String getEducationalCenterGroupTitle() {
        return educationalCenterGroupTitle;
    }

    public void setEducationalCenterGroupTitle(String educationalCenterGroupTitle) {
        this.educationalCenterGroupTitle = educationalCenterGroupTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalCenterCriteriaDTO educationalCenterCriteriaDTO = (EducationalCenterCriteriaDTO) o;
        if (educationalCenterCriteriaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterCriteriaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterCriteriaDTO{" +
            "id=" + getId() +
            ", group='" + getGroup() + "'" +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", description='" + getDescription() + "'" +
            ", peopleCount=" + getPeopleCount() +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", educationalCenterGroup=" + getEducationalCenterGroupId() +
            ", educationalCenterGroup='" + getEducationalCenterGroupTitle() + "'" +
            "}";
    }
}
