package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;

/**
 * A DTO for the NiazsanjiIntegration entity.
 */
public class NiazsanjiIntegrationDTO implements Serializable {

    private Long id;

    private Integer niazsanjiYear;

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

    @Lob
    private String conversation;

    private RequestStatus requestStatus;

    @Size(max = 50)
    private String changeStatusUserLogin;

    @Size(max = 50)
    private String guid;

    private Boolean hasImportantMessage;

    @NotNull
    private RequestNiazsanjiType requestNiazsanjiType;

    private Integer priority;

    private Long prioritizeRequestNiazsanjiId;

    private String prioritizeRequestNiazsanjiCode;

    private Long costEducationalModule;

    private EducationalModuleType educationalModuleType;

    private String niazsanjiInputTitle;

    private String courseTypeTitle;

    private String educationalModuleId;

    private String educationalModuleCode;

    private String educationalModuleTitle;

    private String personFamily;

    private String personName;

    private Long organizationChartId;

    private String organizationChartTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
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

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public RequestNiazsanjiType getRequestNiazsanjiType() {
        return requestNiazsanjiType;
    }

    public void setRequestNiazsanjiType(RequestNiazsanjiType requestNiazsanjiType) {
        this.requestNiazsanjiType = requestNiazsanjiType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getPrioritizeRequestNiazsanjiId() {
        return prioritizeRequestNiazsanjiId;
    }

    public void setPrioritizeRequestNiazsanjiId(Long prioritizeRequestNiazsanjiId) {
        this.prioritizeRequestNiazsanjiId = prioritizeRequestNiazsanjiId;
    }

    public String getPrioritizeRequestNiazsanjiCode() {
        return prioritizeRequestNiazsanjiCode;
    }

    public void setPrioritizeRequestNiazsanjiCode(String prioritizeRequestNiazsanjiCode) {
        this.prioritizeRequestNiazsanjiCode = prioritizeRequestNiazsanjiCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NiazsanjiIntegrationDTO niazsanjiIntegrationDTO = (NiazsanjiIntegrationDTO) o;
        if (niazsanjiIntegrationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiIntegrationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiIntegrationDTO{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", requestNiazsanjiType='" + getRequestNiazsanjiType() + "'" +
            ", priority=" + getPriority() +
            ", prioritizeRequestNiazsanji=" + getPrioritizeRequestNiazsanjiId() +
            ", prioritizeRequestNiazsanji='" + getPrioritizeRequestNiazsanjiCode() + "'" +
            "}";
    }

    public Long getCostEducationalModule() {
        return costEducationalModule;
    }

    public void setCostEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
    }

    public EducationalModuleType getEducationalModuleType() {
        return educationalModuleType;
    }

    public void setEducationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
    }

    public String getNiazsanjiInputTitle() {
        return niazsanjiInputTitle;
    }

    public void setNiazsanjiInputTitle(String niazsanjiInputTitle) {
        this.niazsanjiInputTitle = niazsanjiInputTitle;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public String getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(String educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public String getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(String personFamily) {
        this.personFamily = personFamily;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    public String getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(String educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }
}
