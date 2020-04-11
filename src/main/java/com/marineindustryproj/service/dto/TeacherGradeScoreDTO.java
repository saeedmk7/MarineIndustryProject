package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TeacherGradeScore entity.
 */
public class TeacherGradeScoreDTO implements Serializable {

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

    private Long teacherCriteriaId;

    private String teacherCriteriaTitle;

    private Long teacherGradeId;

    private String teacherGradeTitle;

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

    public Long getTeacherCriteriaId() {
        return teacherCriteriaId;
    }

    public void setTeacherCriteriaId(Long teacherCriteriaId) {
        this.teacherCriteriaId = teacherCriteriaId;
    }

    public String getTeacherCriteriaTitle() {
        return teacherCriteriaTitle;
    }

    public void setTeacherCriteriaTitle(String teacherCriteriaTitle) {
        this.teacherCriteriaTitle = teacherCriteriaTitle;
    }

    public Long getTeacherGradeId() {
        return teacherGradeId;
    }

    public void setTeacherGradeId(Long teacherGradeId) {
        this.teacherGradeId = teacherGradeId;
    }

    public String getTeacherGradeTitle() {
        return teacherGradeTitle;
    }

    public void setTeacherGradeTitle(String teacherGradeTitle) {
        this.teacherGradeTitle = teacherGradeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherGradeScoreDTO teacherGradeScoreDTO = (TeacherGradeScoreDTO) o;
        if (teacherGradeScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherGradeScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherGradeScoreDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", teacherCriteria=" + getTeacherCriteriaId() +
            ", teacherCriteria='" + getTeacherCriteriaTitle() + "'" +
            ", teacherGrade=" + getTeacherGradeId() +
            ", teacherGrade='" + getTeacherGradeTitle() + "'" +
            "}";
    }
}
