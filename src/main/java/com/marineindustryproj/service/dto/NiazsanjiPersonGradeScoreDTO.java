package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NiazsanjiPersonGradeScore entity.
 */
public class NiazsanjiPersonGradeScoreDTO implements Serializable {

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

    private Long niazsanjiPersonCriteriaId;

    private String niazsanjiPersonCriteriaTitle;

    private Long niazsanjiPersonGradeId;

    private String niazsanjiPersonGradeTitle;

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

    public Long getNiazsanjiPersonCriteriaId() {
        return niazsanjiPersonCriteriaId;
    }

    public void setNiazsanjiPersonCriteriaId(Long niazsanjiPersonCriteriaId) {
        this.niazsanjiPersonCriteriaId = niazsanjiPersonCriteriaId;
    }

    public String getNiazsanjiPersonCriteriaTitle() {
        return niazsanjiPersonCriteriaTitle;
    }

    public void setNiazsanjiPersonCriteriaTitle(String niazsanjiPersonCriteriaTitle) {
        this.niazsanjiPersonCriteriaTitle = niazsanjiPersonCriteriaTitle;
    }

    public Long getNiazsanjiPersonGradeId() {
        return niazsanjiPersonGradeId;
    }

    public void setNiazsanjiPersonGradeId(Long niazsanjiPersonGradeId) {
        this.niazsanjiPersonGradeId = niazsanjiPersonGradeId;
    }

    public String getNiazsanjiPersonGradeTitle() {
        return niazsanjiPersonGradeTitle;
    }

    public void setNiazsanjiPersonGradeTitle(String niazsanjiPersonGradeTitle) {
        this.niazsanjiPersonGradeTitle = niazsanjiPersonGradeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO = (NiazsanjiPersonGradeScoreDTO) o;
        if (niazsanjiPersonGradeScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiPersonGradeScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiPersonGradeScoreDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", niazsanjiPersonCriteria=" + getNiazsanjiPersonCriteriaId() +
            ", niazsanjiPersonCriteria='" + getNiazsanjiPersonCriteriaTitle() + "'" +
            ", niazsanjiPersonGrade=" + getNiazsanjiPersonGradeId() +
            ", niazsanjiPersonGrade='" + getNiazsanjiPersonGradeTitle() + "'" +
            "}";
    }
}
