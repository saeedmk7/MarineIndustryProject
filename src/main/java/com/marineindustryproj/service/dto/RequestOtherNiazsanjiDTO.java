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
 * A DTO for the RequestOtherNiazsanji entity.
 */
public class RequestOtherNiazsanjiDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String code;

    private Long costEducationalModule;

    @Lob
    private String description;

    @Size(max = 4096)
    private String restrictionDescription;

    @Size(max = 4096)
    private String goalsText;

    @Size(max = 4096)
    private String prerequisite;

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

    private Boolean hasImportantMessage;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<RestrictionDTO> restrictions = new HashSet<>();

    private Long niazsanjiInputId;

    private String niazsanjiInputTitle;

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long educationalModuleId;

    private String educationalModuleTitle;

    private Long personId;

    private String personFamily;

    private String personName;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long teachingApproachId;

    private String teachingApproachTitle;

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

    public Long getCostEducationalModule() {
        return costEducationalModule;
    }

    public void setCostEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
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

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Set<RestrictionDTO> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Set<RestrictionDTO> restrictions) {
        this.restrictions = restrictions;
    }

    public Long getNiazsanjiInputId() {
        return niazsanjiInputId;
    }

    public void setNiazsanjiInputId(Long niazsanjiInputId) {
        this.niazsanjiInputId = niazsanjiInputId;
    }

    public String getNiazsanjiInputTitle() {
        return niazsanjiInputTitle;
    }

    public void setNiazsanjiInputTitle(String niazsanjiInputTitle) {
        this.niazsanjiInputTitle = niazsanjiInputTitle;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public Long getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(Long teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }

    public String getTeachingApproachTitle() {
        return teachingApproachTitle;
    }

    public void setTeachingApproachTitle(String teachingApproachTitle) {
        this.teachingApproachTitle = teachingApproachTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO = (RequestOtherNiazsanjiDTO) o;
        if (requestOtherNiazsanjiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestOtherNiazsanjiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestOtherNiazsanjiDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", costEducationalModule=" + getCostEducationalModule() +
            ", description='" + getDescription() + "'" +
            ", restrictionDescription='" + getRestrictionDescription() + "'" +
            ", goalsText='" + getGoalsText() + "'" +
            ", prerequisite='" + getPrerequisite() + "'" +
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
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", niazsanjiInput=" + getNiazsanjiInputId() +
            ", niazsanjiInput='" + getNiazsanjiInputTitle() + "'" +
            ", courseType=" + getCourseTypeId() +
            ", courseType='" + getCourseTypeTitle() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            ", person='" + getPersonName() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", teachingApproach=" + getTeachingApproachId() +
            ", teachingApproach='" + getTeachingApproachTitle() + "'" +
            "}";
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
