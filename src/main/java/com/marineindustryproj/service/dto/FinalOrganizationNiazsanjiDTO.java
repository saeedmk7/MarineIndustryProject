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
 * A DTO for the FinalOrganizationNiazsanji entity.
 */
public class FinalOrganizationNiazsanjiDTO implements Serializable {

    private Long id;

    private Integer niazsanjiYear;

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

    @Size(max = 100)
    private String teacherName;

    @Size(max = 100)
    private String teacherMobile;

    @Lob
    private String conversation;

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

    private Set<PersonDTO> people = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long teacherId;

    private String teacherFamily;

    private Long educationalModuleId;

    private String educationalModuleTitle;

    private Long teachApproachId;

    private String teachApproachTitle;

    private Long requestOrganizationNiazsanjiId;

    private String requestOrganizationNiazsanjiRecommendedByOrgchart;

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

    public Long getRequestOrganizationNiazsanjiId() {
        return requestOrganizationNiazsanjiId;
    }

    public void setRequestOrganizationNiazsanjiId(Long requestOrganizationNiazsanjiId) {
        this.requestOrganizationNiazsanjiId = requestOrganizationNiazsanjiId;
    }

    public String getRequestOrganizationNiazsanjiRecommendedByOrgchart() {
        return requestOrganizationNiazsanjiRecommendedByOrgchart;
    }

    public void setRequestOrganizationNiazsanjiRecommendedByOrgchart(String requestOrganizationNiazsanjiRecommendedByOrgchart) {
        this.requestOrganizationNiazsanjiRecommendedByOrgchart = requestOrganizationNiazsanjiRecommendedByOrgchart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO = (FinalOrganizationNiazsanjiDTO) o;
        if (finalOrganizationNiazsanjiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalOrganizationNiazsanjiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalOrganizationNiazsanjiDTO{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", code='" + getCode() + "'" +
            ", recommendedByOrgchart='" + getRecommendedByOrgchart() + "'" +
            ", neededSoftwares='" + getNeededSoftwares() + "'" +
            ", neededHardware='" + getNeededHardware() + "'" +
            ", studentsType='" + getStudentsType() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherMobile='" + getTeacherMobile() + "'" +
            ", conversation='" + getConversation() + "'" +
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
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", teacher=" + getTeacherId() +
            ", teacher='" + getTeacherFamily() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", teachApproach=" + getTeachApproachId() +
            ", teachApproach='" + getTeachApproachTitle() + "'" +
            ", requestOrganizationNiazsanji=" + getRequestOrganizationNiazsanjiId() +
            ", requestOrganizationNiazsanji='" + getRequestOrganizationNiazsanjiRecommendedByOrgchart() + "'" +
            "}";
    }
}
