package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the NiazsanjiGroup entity.
 */
public class NiazsanjiGroupDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String editorPerson;

    private ZonedDateTime reviewDate;

    private ZonedDateTime scheduleDate;

    @Size(max = 50)
    private String firstThreeJobCode;

    private Integer priceCost;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @NotNull
    private Boolean archived;

    @Size(max = 50)
    private String archivedUserLogin;

    private ZonedDateTime archivedDate;

    @NotNull
    private Integer status;

    private Set<JobDTO> jobs = new HashSet<>();

    private Set<EducationalModuleDTO> educationalModules = new HashSet<>();

    private Long scientificWorkGroupId;

    private String scientificWorkGroupTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEditorPerson() {
        return editorPerson;
    }

    public void setEditorPerson(String editorPerson) {
        this.editorPerson = editorPerson;
    }

    public ZonedDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(ZonedDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ZonedDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(ZonedDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getFirstThreeJobCode() {
        return firstThreeJobCode;
    }

    public void setFirstThreeJobCode(String firstThreeJobCode) {
        this.firstThreeJobCode = firstThreeJobCode;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
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

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<JobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(Set<JobDTO> jobs) {
        this.jobs = jobs;
    }

    public Set<EducationalModuleDTO> getEducationalModules() {
        return educationalModules;
    }

    public void setEducationalModules(Set<EducationalModuleDTO> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Long getScientificWorkGroupId() {
        return scientificWorkGroupId;
    }

    public void setScientificWorkGroupId(Long scientificWorkGroupId) {
        this.scientificWorkGroupId = scientificWorkGroupId;
    }

    public String getScientificWorkGroupTitle() {
        return scientificWorkGroupTitle;
    }

    public void setScientificWorkGroupTitle(String scientificWorkGroupTitle) {
        this.scientificWorkGroupTitle = scientificWorkGroupTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NiazsanjiGroupDTO niazsanjiGroupDTO = (NiazsanjiGroupDTO) o;
        if (niazsanjiGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiGroupDTO{" +
            "id=" + getId() +
            ", editorPerson='" + getEditorPerson() + "'" +
            ", reviewDate='" + getReviewDate() + "'" +
            ", scheduleDate='" + getScheduleDate() + "'" +
            ", firstThreeJobCode='" + getFirstThreeJobCode() + "'" +
            ", priceCost=" + getPriceCost() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", scientificWorkGroup=" + getScientificWorkGroupId() +
            ", scientificWorkGroup='" + getScientificWorkGroupTitle() + "'" +
            "}";
    }
}
