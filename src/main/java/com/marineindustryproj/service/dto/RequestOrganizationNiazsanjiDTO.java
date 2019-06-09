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
 * A DTO for the RequestOrganizationNiazsanji entity.
 */
public class RequestOrganizationNiazsanjiDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String code;

    @Size(max = 4096)
    private String recommendedByOrgchart;

    @Size(max = 100)
    private String neededSoftwares;

    @Size(max = 100)
    private String neededHardware;

    @Size(max = 100)
    private String studentsType;

    private Boolean teacherNotFound;

    @Size(max = 100)
    private String teacherName;

    @Size(max = 100)
    private String teacherMobile;

    @NotNull
    private RequestStatus requestStatus;

    @Size(max = 50)
    private String changeStatusUserLogin;

    @Size(max = 1024)
    private String trainingGoals;

    @Size(max = 4096)
    private String description;

    private Integer priceCost;

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

    @Size(max = 50)
    private String guid;

    private Set<PersonDTO> people = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long teacherId;

    private String teacherFamily;

    private Long educationalModuleId;

    private String educationalModuleTitle;

    private Long teachApproachId;

    private String teachApproachTitle;

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

    public String getRecommendedByOrgchart() {
        return recommendedByOrgchart;
    }

    public void setRecommendedByOrgchart(String recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
    }

    public String getNeededSoftwares() {
        return neededSoftwares;
    }

    public void setNeededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public String getNeededHardware() {
        return neededHardware;
    }

    public void setNeededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
    }

    public String getStudentsType() {
        return studentsType;
    }

    public void setStudentsType(String studentsType) {
        this.studentsType = studentsType;
    }

    public Boolean isTeacherNotFound() {
        return teacherNotFound;
    }

    public void setTeacherNotFound(Boolean teacherNotFound) {
        this.teacherNotFound = teacherNotFound;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
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

    public String getTrainingGoals() {
        return trainingGoals;
    }

    public void setTrainingGoals(String trainingGoals) {
        this.trainingGoals = trainingGoals;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonDTO> people) {
        this.people = people;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFamily() {
        return teacherFamily;
    }

    public void setTeacherFamily(String teacherFamily) {
        this.teacherFamily = teacherFamily;
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

    public Long getTeachApproachId() {
        return teachApproachId;
    }

    public void setTeachApproachId(Long teachApproachId) {
        this.teachApproachId = teachApproachId;
    }

    public String getTeachApproachTitle() {
        return teachApproachTitle;
    }

    public void setTeachApproachTitle(String teachApproachTitle) {
        this.teachApproachTitle = teachApproachTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO = (RequestOrganizationNiazsanjiDTO) o;
        if (requestOrganizationNiazsanjiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestOrganizationNiazsanjiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestOrganizationNiazsanjiDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", recommendedByOrgchart='" + getRecommendedByOrgchart() + "'" +
            ", neededSoftwares='" + getNeededSoftwares() + "'" +
            ", neededHardware='" + getNeededHardware() + "'" +
            ", studentsType='" + getStudentsType() + "'" +
            ", teacherNotFound='" + isTeacherNotFound() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherMobile='" + getTeacherMobile() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", trainingGoals='" + getTrainingGoals() + "'" +
            ", description='" + getDescription() + "'" +
            ", priceCost=" + getPriceCost() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", conversation='" + getConversation() + "'" +
            ", guid='" + getGuid() + "'" +
            ", courseType=" + getCourseTypeId() +
            ", courseType='" + getCourseTypeTitle() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", teacher=" + getTeacherId() +
            ", teacher='" + getTeacherFamily() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", teachApproach=" + getTeachApproachId() +
            ", teachApproach='" + getTeachApproachTitle() + "'" +
            "}";
    }
}
