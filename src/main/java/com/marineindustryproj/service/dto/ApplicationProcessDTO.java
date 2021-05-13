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
 * A DTO for the ApplicationProcess entity.
 */
public class ApplicationProcessDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String code;

    @Lob
    private String description;

    @Size(max = 4096)
    private String jobAfterProcess;

    @Size(max = 4096)
    private String acceptedUniversityAndDegree;

    @Size(max = 50)
    private String startStudyDate;

    @Size(max = 4096)
    private String graduateDateOfNewCourse;

    @Size(max = 50)
    private String averagePointOfNewCourse;

    @Size(max = 4096)
    private String acceptedMajorAndField;

    private Boolean haveUsedEducationalFacilities;

    @Size(max = 4096)
    private String haveUsedEducationalFacilitiesDescription;

    @Size(max = 50)
    private String dateOfAcceptanceOfDegree;

    @Size(max = 4096)
    private String typeAndAmountOfFacilities;

    @Size(max = 4096)
    private String academicCommitmentsFulfilled;

    @Size(max = 4096)
    private String remainingAcademicCommitments;

    @Size(max = 4096)
    private String requestedFacilitiesText;

    @Size(max = 4096)
    private String requestedFacilitiesDescription;

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
    private Integer chartStatus;

    @NotNull
    private Integer bossStatus;

    @Lob
    private String conversation;

    private RequestStatus requestStatus;

    @Size(max = 50)
    private String changeStatusUserLogin;

    @Size(max = 50)
    private String guid;

    private Boolean hasImportantMessage;

    @Size(max = 4096)
    private String selectedModuleIds;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long educationalRecordId;

    private String educationalRecordQualificationText;

    private Long personId;

    private String personFamily;

    private String personName;

    private String personJobTitle;

    private String personEmploymentTypeTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long qualificationId;

    private String qualificationTitle;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobAfterProcess() {
        return jobAfterProcess;
    }

    public void setJobAfterProcess(String jobAfterProcess) {
        this.jobAfterProcess = jobAfterProcess;
    }

    public String getAcceptedUniversityAndDegree() {
        return acceptedUniversityAndDegree;
    }

    public void setAcceptedUniversityAndDegree(String acceptedUniversityAndDegree) {
        this.acceptedUniversityAndDegree = acceptedUniversityAndDegree;
    }

    public String getStartStudyDate() {
        return startStudyDate;
    }

    public void setStartStudyDate(String startStudyDate) {
        this.startStudyDate = startStudyDate;
    }

    public String getGraduateDateOfNewCourse() {
        return graduateDateOfNewCourse;
    }

    public void setGraduateDateOfNewCourse(String graduateDateOfNewCourse) {
        this.graduateDateOfNewCourse = graduateDateOfNewCourse;
    }

    public String getAveragePointOfNewCourse() {
        return averagePointOfNewCourse;
    }

    public void setAveragePointOfNewCourse(String averagePointOfNewCourse) {
        this.averagePointOfNewCourse = averagePointOfNewCourse;
    }

    public String getAcceptedMajorAndField() {
        return acceptedMajorAndField;
    }

    public void setAcceptedMajorAndField(String acceptedMajorAndField) {
        this.acceptedMajorAndField = acceptedMajorAndField;
    }

    public Boolean isHaveUsedEducationalFacilities() {
        return haveUsedEducationalFacilities;
    }

    public void setHaveUsedEducationalFacilities(Boolean haveUsedEducationalFacilities) {
        this.haveUsedEducationalFacilities = haveUsedEducationalFacilities;
    }

    public String getHaveUsedEducationalFacilitiesDescription() {
        return haveUsedEducationalFacilitiesDescription;
    }

    public void setHaveUsedEducationalFacilitiesDescription(String haveUsedEducationalFacilitiesDescription) {
        this.haveUsedEducationalFacilitiesDescription = haveUsedEducationalFacilitiesDescription;
    }

    public String getDateOfAcceptanceOfDegree() {
        return dateOfAcceptanceOfDegree;
    }

    public void setDateOfAcceptanceOfDegree(String dateOfAcceptanceOfDegree) {
        this.dateOfAcceptanceOfDegree = dateOfAcceptanceOfDegree;
    }

    public String getTypeAndAmountOfFacilities() {
        return typeAndAmountOfFacilities;
    }

    public void setTypeAndAmountOfFacilities(String typeAndAmountOfFacilities) {
        this.typeAndAmountOfFacilities = typeAndAmountOfFacilities;
    }

    public String getAcademicCommitmentsFulfilled() {
        return academicCommitmentsFulfilled;
    }

    public void setAcademicCommitmentsFulfilled(String academicCommitmentsFulfilled) {
        this.academicCommitmentsFulfilled = academicCommitmentsFulfilled;
    }

    public String getRemainingAcademicCommitments() {
        return remainingAcademicCommitments;
    }

    public void setRemainingAcademicCommitments(String remainingAcademicCommitments) {
        this.remainingAcademicCommitments = remainingAcademicCommitments;
    }

    public String getRequestedFacilitiesText() {
        return requestedFacilitiesText;
    }

    public void setRequestedFacilitiesText(String requestedFacilitiesText) {
        this.requestedFacilitiesText = requestedFacilitiesText;
    }

    public String getRequestedFacilitiesDescription() {
        return requestedFacilitiesDescription;
    }

    public void setRequestedFacilitiesDescription(String requestedFacilitiesDescription) {
        this.requestedFacilitiesDescription = requestedFacilitiesDescription;
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

    public Integer getChartStatus() {
        return chartStatus;
    }

    public void setChartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
    }

    public Integer getBossStatus() {
        return bossStatus;
    }

    public void setBossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
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

    public String getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public void setSelectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getEducationalRecordId() {
        return educationalRecordId;
    }

    public void setEducationalRecordId(Long educationalRecordId) {
        this.educationalRecordId = educationalRecordId;
    }

    public String getEducationalRecordQualificationText() {
        return educationalRecordQualificationText;
    }

    public void setEducationalRecordQualificationText(String educationalRecordQualificationText) {
        this.educationalRecordQualificationText = educationalRecordQualificationText;
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

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationTitle() {
        return qualificationTitle;
    }

    public void setQualificationTitle(String qualificationTitle) {
        this.qualificationTitle = qualificationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApplicationProcessDTO applicationProcessDTO = (ApplicationProcessDTO) o;
        if (applicationProcessDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationProcessDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationProcessDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobAfterProcess='" + getJobAfterProcess() + "'" +
            ", acceptedUniversityAndDegree='" + getAcceptedUniversityAndDegree() + "'" +
            ", startStudyDate='" + getStartStudyDate() + "'" +
            ", graduateDateOfNewCourse='" + getGraduateDateOfNewCourse() + "'" +
            ", averagePointOfNewCourse='" + getAveragePointOfNewCourse() + "'" +
            ", acceptedMajorAndField='" + getAcceptedMajorAndField() + "'" +
            ", haveUsedEducationalFacilities='" + isHaveUsedEducationalFacilities() + "'" +
            ", haveUsedEducationalFacilitiesDescription='" + getHaveUsedEducationalFacilitiesDescription() + "'" +
            ", dateOfAcceptanceOfDegree='" + getDateOfAcceptanceOfDegree() + "'" +
            ", typeAndAmountOfFacilities='" + getTypeAndAmountOfFacilities() + "'" +
            ", academicCommitmentsFulfilled='" + getAcademicCommitmentsFulfilled() + "'" +
            ", remainingAcademicCommitments='" + getRemainingAcademicCommitments() + "'" +
            ", requestedFacilitiesText='" + getRequestedFacilitiesText() + "'" +
            ", requestedFacilitiesDescription='" + getRequestedFacilitiesDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", chartStatus=" + getChartStatus() +
            ", bossStatus=" + getBossStatus() +
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", selectedModuleIds='" + getSelectedModuleIds() + "'" +
            ", educationalRecord=" + getEducationalRecordId() +
            ", educationalRecord='" + getEducationalRecordQualificationText() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", qualification=" + getQualificationId() +
            ", qualification='" + getQualificationTitle() + "'" +
            "}";
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonJobTitle() {
        return personJobTitle;
    }

    public void setPersonJobTitle(String personJobTitle) {
        this.personJobTitle = personJobTitle;
    }

    public String getPersonEmploymentTypeTitle() {
        return personEmploymentTypeTitle;
    }

    public void setPersonEmploymentTypeTitle(String personEmploymentTypeTitle) {
        this.personEmploymentTypeTitle = personEmploymentTypeTitle;
    }
}
