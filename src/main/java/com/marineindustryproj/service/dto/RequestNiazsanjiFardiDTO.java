package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A DTO for the RequestNiazsanjiFardi entity.
 */
public class RequestNiazsanjiFardiDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String code;

    private Long costApprovedEducationalModule;

    private Long costAllEducationalModule;

    @Lob
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

    @Lob
    private String conversation;

    private RequestStatus requestStatus;

    @Size(max = 50)
    private String changeStatusUserLogin;

    @Size(max = 50)
    private String guid;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long approvedEducationalModuleId;

    private String approvedEducationalModuleTitle;

    private Long allEducationalModuleId;

    private String allEducationalModuleTitle;

    private Long personId;

    private String personName;

    private String personFamily;

    private Long organizationChartId;

    private String organizationChartTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCostApprovedEducationalModule() {
        return costApprovedEducationalModule;
    }

    public void setCostApprovedEducationalModule(Long costApprovedEducationalModule) {
        this.costApprovedEducationalModule = costApprovedEducationalModule;
    }

    public Long getCostAllEducationalModule() {
        return costAllEducationalModule;
    }

    public void setCostAllEducationalModule(Long costAllEducationalModule) {
        this.costAllEducationalModule = costAllEducationalModule;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public Long getApprovedEducationalModuleId() {
        return approvedEducationalModuleId;
    }

    public void setApprovedEducationalModuleId(Long educationalModuleId) {
        this.approvedEducationalModuleId = educationalModuleId;
    }

    public String getApprovedEducationalModuleTitle() {
        return approvedEducationalModuleTitle;
    }

    public void setApprovedEducationalModuleTitle(String educationalModuleTitle) {
        this.approvedEducationalModuleTitle = educationalModuleTitle;
    }

    public Long getAllEducationalModuleId() {
        return allEducationalModuleId;
    }

    public void setAllEducationalModuleId(Long educationalModuleId) {
        this.allEducationalModuleId = educationalModuleId;
    }

    public String getAllEducationalModuleTitle() {
        return allEducationalModuleTitle;
    }

    public void setAllEducationalModuleTitle(String educationalModuleTitle) {
        this.allEducationalModuleTitle = educationalModuleTitle;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(String personFamily) {
        this.personFamily = personFamily;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO = (RequestNiazsanjiFardiDTO) o;
        if (requestNiazsanjiFardiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestNiazsanjiFardiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestNiazsanjiFardiDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", costApprovedEducationalModule=" + getCostApprovedEducationalModule() +
            ", costAllEducationalModule=" + getCostAllEducationalModule() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
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
            ", courseType=" + getCourseTypeId() +
            ", courseType='" + getCourseTypeTitle() + "'" +
            ", approvedEducationalModule=" + getApprovedEducationalModuleId() +
            ", approvedEducationalModule='" + getApprovedEducationalModuleTitle() + "'" +
            ", allEducationalModule=" + getAllEducationalModuleId() +
            ", allEducationalModule='" + getAllEducationalModuleTitle() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonName() + "'" +
            ", person='" + getPersonFamily() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
