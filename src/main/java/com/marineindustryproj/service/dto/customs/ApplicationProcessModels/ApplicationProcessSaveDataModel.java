package com.marineindustryproj.service.dto.customs.ApplicationProcessModels;

import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;
import com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels.MatchingEducationalRecordSaveDataItemModel;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ApplicationProcessSaveDataModel implements Serializable {

    private Long applicationProcessId;

    private String description;

    private String conversion;

    private Integer stepNumber;

    private Integer chartStatus;

    private Integer bossStatus;

    private RequestStatus requestStatus;

    private Long personId;

    private Long organizationChartId;

    private String selectedModuleIds;

    private Long educationalRecordId;

    private Long qualificationId;

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

    private Set<ApplicationProcessSaveDataItemModel> applicationProcessSaveDataItemModels = new HashSet<>();

    public Long getApplicationProcessId() {
        return applicationProcessId;
    }

    public void setApplicationProcessId(Long applicationProcessId) {
        this.applicationProcessId = applicationProcessId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public void setSelectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public Long getEducationalRecordId() {
        return educationalRecordId;
    }

    public void setEducationalRecordId(Long educationalRecordId) {
        this.educationalRecordId = educationalRecordId;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
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

    public Boolean getHaveUsedEducationalFacilities() {
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

    public Set<ApplicationProcessSaveDataItemModel> getApplicationProcessSaveDataItemModels() {
        return applicationProcessSaveDataItemModels;
    }

    public void setApplicationProcessSaveDataItemModels(Set<ApplicationProcessSaveDataItemModel> applicationProcessSaveDataItemModels) {
        this.applicationProcessSaveDataItemModels = applicationProcessSaveDataItemModels;
    }
}
