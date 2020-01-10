package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PriorityCriteriaValue entity.
 */
public class PriorityCriteriaValueDTO implements Serializable {

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

    private Long priorityCriteriaId;

    private String priorityCriteriaTitle;

    private Long preJobNiazsanjiCompetencyId;

    private String preJobNiazsanjiCompetencyTitle;

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

    public Long getPriorityCriteriaId() {
        return priorityCriteriaId;
    }

    public void setPriorityCriteriaId(Long priorityCriteriaId) {
        this.priorityCriteriaId = priorityCriteriaId;
    }

    public String getPriorityCriteriaTitle() {
        return priorityCriteriaTitle;
    }

    public void setPriorityCriteriaTitle(String priorityCriteriaTitle) {
        this.priorityCriteriaTitle = priorityCriteriaTitle;
    }

    public Long getPreJobNiazsanjiCompetencyId() {
        return preJobNiazsanjiCompetencyId;
    }

    public void setPreJobNiazsanjiCompetencyId(Long preJobNiazsanjiCompetencyId) {
        this.preJobNiazsanjiCompetencyId = preJobNiazsanjiCompetencyId;
    }

    public String getPreJobNiazsanjiCompetencyTitle() {
        return preJobNiazsanjiCompetencyTitle;
    }

    public void setPreJobNiazsanjiCompetencyTitle(String preJobNiazsanjiCompetencyTitle) {
        this.preJobNiazsanjiCompetencyTitle = preJobNiazsanjiCompetencyTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriorityCriteriaValueDTO priorityCriteriaValueDTO = (PriorityCriteriaValueDTO) o;
        if (priorityCriteriaValueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), priorityCriteriaValueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PriorityCriteriaValueDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", priorityCriteria=" + getPriorityCriteriaId() +
            ", priorityCriteria='" + getPriorityCriteriaTitle() + "'" +
            ", preJobNiazsanjiCompetency=" + getPreJobNiazsanjiCompetencyId() +
            ", preJobNiazsanjiCompetency='" + getPreJobNiazsanjiCompetencyTitle() + "'" +
            "}";
    }
}
