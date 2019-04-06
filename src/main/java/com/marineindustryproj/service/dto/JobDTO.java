package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Job entity.
 */
public class JobDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(min = 6, max = 6)
    private String jobKey;

    @Size(min = 12, max = 12)
    private String jobCode;

    @Size(max = 100)
    private String first3JobCode;

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

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long rasteId;

    private String rasteTitle;

    private Long radehId;

    private String radehTitle;

    private Long jobTypeId;

    private String jobTypeTitle;

    private Long scientificWorkGroupId;

    private String scientificWorkGroupTitle;

    private Long parentId;

    private String parentTitle;

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

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getFirst3JobCode() {
        return first3JobCode;
    }

    public void setFirst3JobCode(String first3JobCode) {
        this.first3JobCode = first3JobCode;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getRasteId() {
        return rasteId;
    }

    public void setRasteId(Long rasteId) {
        this.rasteId = rasteId;
    }

    public String getRasteTitle() {
        return rasteTitle;
    }

    public void setRasteTitle(String rasteTitle) {
        this.rasteTitle = rasteTitle;
    }

    public Long getRadehId() {
        return radehId;
    }

    public void setRadehId(Long radehId) {
        this.radehId = radehId;
    }

    public String getRadehTitle() {
        return radehTitle;
    }

    public void setRadehTitle(String radehTitle) {
        this.radehTitle = radehTitle;
    }

    public Long getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Long jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getJobTypeTitle() {
        return jobTypeTitle;
    }

    public void setJobTypeTitle(String jobTypeTitle) {
        this.jobTypeTitle = jobTypeTitle;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long jobId) {
        this.parentId = jobId;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String jobTitle) {
        this.parentTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobDTO jobDTO = (JobDTO) o;
        if (jobDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", jobKey='" + getJobKey() + "'" +
            ", jobCode='" + getJobCode() + "'" +
            ", first3JobCode='" + getFirst3JobCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", raste=" + getRasteId() +
            ", raste='" + getRasteTitle() + "'" +
            ", radeh=" + getRadehId() +
            ", radeh='" + getRadehTitle() + "'" +
            ", jobType=" + getJobTypeId() +
            ", jobType='" + getJobTypeTitle() + "'" +
            ", scientificWorkGroup=" + getScientificWorkGroupId() +
            ", scientificWorkGroup='" + getScientificWorkGroupTitle() + "'" +
            ", parent=" + getParentId() +
            ", parent='" + getParentTitle() + "'" +
            "}";
    }
}
