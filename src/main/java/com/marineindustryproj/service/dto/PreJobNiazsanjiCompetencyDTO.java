package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PreJobNiazsanjiCompetency entity.
 */
public class PreJobNiazsanjiCompetencyDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4096)
    private String title;

    @NotNull
    private Integer needToImprove;

    @Size(max = 4096)
    private String needToImproveDescription;

    @Size(max = 4096)
    private String fixCompetencyDeficiencyDescription;

    @Size(max = 100)
    private String educationalModuleText;

    private Integer sumScore;

    private Integer priority;

    @NotNull
    private Boolean selected;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Set<TeachingApproachDTO> teachingApproaches = new HashSet<>();

    private Long fixCompetencyDeficiencyId;

    private String fixCompetencyDeficiencyTitle;

    private Long educationalModuleId;

    private String educationalModuleCode;

    private String educationalModuleTitle;

    private Long preJobNiazsanjiId;

    private String preJobNiazsanjiTitle;

    private Long competencyId;

    private String competencyTitle;

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

    public Integer getNeedToImprove() {
        return needToImprove;
    }

    public void setNeedToImprove(Integer needToImprove) {
        this.needToImprove = needToImprove;
    }

    public String getNeedToImproveDescription() {
        return needToImproveDescription;
    }

    public void setNeedToImproveDescription(String needToImproveDescription) {
        this.needToImproveDescription = needToImproveDescription;
    }

    public String getFixCompetencyDeficiencyDescription() {
        return fixCompetencyDeficiencyDescription;
    }

    public void setFixCompetencyDeficiencyDescription(String fixCompetencyDeficiencyDescription) {
        this.fixCompetencyDeficiencyDescription = fixCompetencyDeficiencyDescription;
    }

    public String getEducationalModuleText() {
        return educationalModuleText;
    }

    public void setEducationalModuleText(String educationalModuleText) {
        this.educationalModuleText = educationalModuleText;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    public Set<TeachingApproachDTO> getTeachingApproaches() {
        return teachingApproaches;
    }

    public void setTeachingApproaches(Set<TeachingApproachDTO> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
    }

    public Long getFixCompetencyDeficiencyId() {
        return fixCompetencyDeficiencyId;
    }

    public void setFixCompetencyDeficiencyId(Long fixCompetencyDeficiencyId) {
        this.fixCompetencyDeficiencyId = fixCompetencyDeficiencyId;
    }

    public String getFixCompetencyDeficiencyTitle() {
        return fixCompetencyDeficiencyTitle;
    }

    public void setFixCompetencyDeficiencyTitle(String fixCompetencyDeficiencyTitle) {
        this.fixCompetencyDeficiencyTitle = fixCompetencyDeficiencyTitle;
    }

    public Long getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(Long educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public Long getPreJobNiazsanjiId() {
        return preJobNiazsanjiId;
    }

    public void setPreJobNiazsanjiId(Long preJobNiazsanjiId) {
        this.preJobNiazsanjiId = preJobNiazsanjiId;
    }

    public String getPreJobNiazsanjiTitle() {
        return preJobNiazsanjiTitle;
    }

    public void setPreJobNiazsanjiTitle(String preJobNiazsanjiTitle) {
        this.preJobNiazsanjiTitle = preJobNiazsanjiTitle;
    }

    public Long getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(Long competencyId) {
        this.competencyId = competencyId;
    }

    public String getCompetencyTitle() {
        return competencyTitle;
    }

    public void setCompetencyTitle(String competencyTitle) {
        this.competencyTitle = competencyTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO = (PreJobNiazsanjiCompetencyDTO) o;
        if (preJobNiazsanjiCompetencyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preJobNiazsanjiCompetencyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreJobNiazsanjiCompetencyDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", needToImprove=" + getNeedToImprove() +
            ", needToImproveDescription='" + getNeedToImproveDescription() + "'" +
            ", fixCompetencyDeficiencyDescription='" + getFixCompetencyDeficiencyDescription() + "'" +
            ", educationalModuleText='" + getEducationalModuleText() + "'" +
            ", sumScore=" + getSumScore() +
            ", priority=" + getPriority() +
            ", selected='" + isSelected() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", fixCompetencyDeficiency=" + getFixCompetencyDeficiencyId() +
            ", fixCompetencyDeficiency='" + getFixCompetencyDeficiencyTitle() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleCode() + "'" +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", preJobNiazsanji=" + getPreJobNiazsanjiId() +
            ", preJobNiazsanji='" + getPreJobNiazsanjiTitle() + "'" +
            ", competency=" + getCompetencyId() +
            ", competency='" + getCompetencyTitle() + "'" +
            "}";
    }

    public String getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(String educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }
}
