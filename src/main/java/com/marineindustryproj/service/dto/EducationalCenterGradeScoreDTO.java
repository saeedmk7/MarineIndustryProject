package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EducationalCenterGradeScore entity.
 */
public class EducationalCenterGradeScoreDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer score;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long educationalCenterCriteriaId;

    private String educationalCenterCriteriaTitle;

    private Long educationalCenterGradeId;

    private String educationalCenterGradeTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Long getEducationalCenterCriteriaId() {
        return educationalCenterCriteriaId;
    }

    public void setEducationalCenterCriteriaId(Long educationalCenterCriteriaId) {
        this.educationalCenterCriteriaId = educationalCenterCriteriaId;
    }

    public String getEducationalCenterCriteriaTitle() {
        return educationalCenterCriteriaTitle;
    }

    public void setEducationalCenterCriteriaTitle(String educationalCenterCriteriaTitle) {
        this.educationalCenterCriteriaTitle = educationalCenterCriteriaTitle;
    }

    public Long getEducationalCenterGradeId() {
        return educationalCenterGradeId;
    }

    public void setEducationalCenterGradeId(Long educationalCenterGradeId) {
        this.educationalCenterGradeId = educationalCenterGradeId;
    }

    public String getEducationalCenterGradeTitle() {
        return educationalCenterGradeTitle;
    }

    public void setEducationalCenterGradeTitle(String educationalCenterGradeTitle) {
        this.educationalCenterGradeTitle = educationalCenterGradeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO = (EducationalCenterGradeScoreDTO) o;
        if (educationalCenterGradeScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterGradeScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterGradeScoreDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", educationalCenterCriteria=" + getEducationalCenterCriteriaId() +
            ", educationalCenterCriteria='" + getEducationalCenterCriteriaTitle() + "'" +
            ", educationalCenterGrade=" + getEducationalCenterGradeId() +
            ", educationalCenterGrade='" + getEducationalCenterGradeTitle() + "'" +
            "}";
    }
}
