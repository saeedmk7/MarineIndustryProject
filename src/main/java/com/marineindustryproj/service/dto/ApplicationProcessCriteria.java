package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the ApplicationProcess entity. This class is used in ApplicationProcessResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /application-processes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApplicationProcessCriteria implements Serializable {
    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter jobAfterProcess;

    private StringFilter acceptedUniversityAndDegree;

    private StringFilter startStudyDate;

    private StringFilter graduateDateOfNewCourse;

    private StringFilter averagePointOfNewCourse;

    private StringFilter acceptedMajorAndField;

    private BooleanFilter haveUsedEducationalFacilities;

    private StringFilter haveUsedEducationalFacilitiesDescription;

    private StringFilter dateOfAcceptanceOfDegree;

    private StringFilter typeAndAmountOfFacilities;

    private StringFilter academicCommitmentsFulfilled;

    private StringFilter remainingAcademicCommitments;

    private StringFilter requestedFacilitiesText;

    private StringFilter requestedFacilitiesDescription;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter chartStatus;

    private IntegerFilter bossStatus;

    private RequestStatusFilter requestStatus;

    private StringFilter changeStatusUserLogin;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private StringFilter selectedModuleIds;

    private LongFilter applicationProcessRunStepId;

    private LongFilter documentId;

    private LongFilter educationalRecordId;

    private LongFilter personId;

    private LongFilter personEmploymentTypeId;

    private LongFilter organizationChartId;

    private LongFilter qualificationId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getJobAfterProcess() {
        return jobAfterProcess;
    }

    public void setJobAfterProcess(StringFilter jobAfterProcess) {
        this.jobAfterProcess = jobAfterProcess;
    }

    public StringFilter getAcceptedUniversityAndDegree() {
        return acceptedUniversityAndDegree;
    }

    public void setAcceptedUniversityAndDegree(StringFilter acceptedUniversityAndDegree) {
        this.acceptedUniversityAndDegree = acceptedUniversityAndDegree;
    }

    public StringFilter getStartStudyDate() {
        return startStudyDate;
    }

    public void setStartStudyDate(StringFilter startStudyDate) {
        this.startStudyDate = startStudyDate;
    }

    public StringFilter getGraduateDateOfNewCourse() {
        return graduateDateOfNewCourse;
    }

    public void setGraduateDateOfNewCourse(StringFilter graduateDateOfNewCourse) {
        this.graduateDateOfNewCourse = graduateDateOfNewCourse;
    }

    public StringFilter getAveragePointOfNewCourse() {
        return averagePointOfNewCourse;
    }

    public void setAveragePointOfNewCourse(StringFilter averagePointOfNewCourse) {
        this.averagePointOfNewCourse = averagePointOfNewCourse;
    }

    public StringFilter getAcceptedMajorAndField() {
        return acceptedMajorAndField;
    }

    public void setAcceptedMajorAndField(StringFilter acceptedMajorAndField) {
        this.acceptedMajorAndField = acceptedMajorAndField;
    }

    public BooleanFilter getHaveUsedEducationalFacilities() {
        return haveUsedEducationalFacilities;
    }

    public void setHaveUsedEducationalFacilities(BooleanFilter haveUsedEducationalFacilities) {
        this.haveUsedEducationalFacilities = haveUsedEducationalFacilities;
    }

    public StringFilter getHaveUsedEducationalFacilitiesDescription() {
        return haveUsedEducationalFacilitiesDescription;
    }

    public void setHaveUsedEducationalFacilitiesDescription(StringFilter haveUsedEducationalFacilitiesDescription) {
        this.haveUsedEducationalFacilitiesDescription = haveUsedEducationalFacilitiesDescription;
    }

    public StringFilter getDateOfAcceptanceOfDegree() {
        return dateOfAcceptanceOfDegree;
    }

    public void setDateOfAcceptanceOfDegree(StringFilter dateOfAcceptanceOfDegree) {
        this.dateOfAcceptanceOfDegree = dateOfAcceptanceOfDegree;
    }

    public StringFilter getTypeAndAmountOfFacilities() {
        return typeAndAmountOfFacilities;
    }

    public void setTypeAndAmountOfFacilities(StringFilter typeAndAmountOfFacilities) {
        this.typeAndAmountOfFacilities = typeAndAmountOfFacilities;
    }

    public StringFilter getAcademicCommitmentsFulfilled() {
        return academicCommitmentsFulfilled;
    }

    public void setAcademicCommitmentsFulfilled(StringFilter academicCommitmentsFulfilled) {
        this.academicCommitmentsFulfilled = academicCommitmentsFulfilled;
    }

    public StringFilter getRemainingAcademicCommitments() {
        return remainingAcademicCommitments;
    }

    public void setRemainingAcademicCommitments(StringFilter remainingAcademicCommitments) {
        this.remainingAcademicCommitments = remainingAcademicCommitments;
    }

    public StringFilter getRequestedFacilitiesText() {
        return requestedFacilitiesText;
    }

    public void setRequestedFacilitiesText(StringFilter requestedFacilitiesText) {
        this.requestedFacilitiesText = requestedFacilitiesText;
    }

    public StringFilter getRequestedFacilitiesDescription() {
        return requestedFacilitiesDescription;
    }

    public void setRequestedFacilitiesDescription(StringFilter requestedFacilitiesDescription) {
        this.requestedFacilitiesDescription = requestedFacilitiesDescription;
    }

    public StringFilter getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(StringFilter createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTimeFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTimeFilter createDate) {
        this.createDate = createDate;
    }

    public StringFilter getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(StringFilter modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTimeFilter getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTimeFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BooleanFilter getArchived() {
        return archived;
    }

    public void setArchived(BooleanFilter archived) {
        this.archived = archived;
    }

    public StringFilter getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(StringFilter archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTimeFilter getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTimeFilter archivedDate) {
        this.archivedDate = archivedDate;
    }

    public IntegerFilter getChartStatus() {
        return chartStatus;
    }

    public void setChartStatus(IntegerFilter chartStatus) {
        this.chartStatus = chartStatus;
    }

    public IntegerFilter getBossStatus() {
        return bossStatus;
    }

    public void setBossStatus(IntegerFilter bossStatus) {
        this.bossStatus = bossStatus;
    }

    public RequestStatusFilter getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusFilter requestStatus) {
        this.requestStatus = requestStatus;
    }

    public StringFilter getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(StringFilter changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public BooleanFilter getHasImportantMessage() {
        return hasImportantMessage;
    }

    public void setHasImportantMessage(BooleanFilter hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public StringFilter getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public void setSelectedModuleIds(StringFilter selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public LongFilter getApplicationProcessRunStepId() {
        return applicationProcessRunStepId;
    }

    public void setApplicationProcessRunStepId(LongFilter applicationProcessRunStepId) {
        this.applicationProcessRunStepId = applicationProcessRunStepId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getEducationalRecordId() {
        return educationalRecordId;
    }

    public void setEducationalRecordId(LongFilter educationalRecordId) {
        this.educationalRecordId = educationalRecordId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(LongFilter qualificationId) {
        this.qualificationId = qualificationId;
    }

    public LongFilter getPersonEmploymentTypeId() {
        return personEmploymentTypeId;
    }

    public void setPersonEmploymentTypeId(LongFilter personEmploymentTypeId) {
        this.personEmploymentTypeId = personEmploymentTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ApplicationProcessCriteria that = (ApplicationProcessCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(jobAfterProcess, that.jobAfterProcess) &&
            Objects.equals(acceptedUniversityAndDegree, that.acceptedUniversityAndDegree) &&
            Objects.equals(startStudyDate, that.startStudyDate) &&
            Objects.equals(graduateDateOfNewCourse, that.graduateDateOfNewCourse) &&
            Objects.equals(averagePointOfNewCourse, that.averagePointOfNewCourse) &&
            Objects.equals(acceptedMajorAndField, that.acceptedMajorAndField) &&
            Objects.equals(haveUsedEducationalFacilities, that.haveUsedEducationalFacilities) &&
            Objects.equals(haveUsedEducationalFacilitiesDescription, that.haveUsedEducationalFacilitiesDescription) &&
            Objects.equals(dateOfAcceptanceOfDegree, that.dateOfAcceptanceOfDegree) &&
            Objects.equals(typeAndAmountOfFacilities, that.typeAndAmountOfFacilities) &&
            Objects.equals(academicCommitmentsFulfilled, that.academicCommitmentsFulfilled) &&
            Objects.equals(remainingAcademicCommitments, that.remainingAcademicCommitments) &&
            Objects.equals(requestedFacilitiesText, that.requestedFacilitiesText) &&
            Objects.equals(requestedFacilitiesDescription, that.requestedFacilitiesDescription) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(chartStatus, that.chartStatus) &&
            Objects.equals(bossStatus, that.bossStatus) &&
            Objects.equals(requestStatus, that.requestStatus) &&
            Objects.equals(changeStatusUserLogin, that.changeStatusUserLogin) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(selectedModuleIds, that.selectedModuleIds) &&
            Objects.equals(applicationProcessRunStepId, that.applicationProcessRunStepId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(educationalRecordId, that.educationalRecordId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(personEmploymentTypeId, that.personEmploymentTypeId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(qualificationId, that.qualificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        jobAfterProcess,
        acceptedUniversityAndDegree,
        startStudyDate,
        graduateDateOfNewCourse,
        averagePointOfNewCourse,
        acceptedMajorAndField,
        haveUsedEducationalFacilities,
        haveUsedEducationalFacilitiesDescription,
        dateOfAcceptanceOfDegree,
        typeAndAmountOfFacilities,
        academicCommitmentsFulfilled,
        remainingAcademicCommitments,
        requestedFacilitiesText,
        requestedFacilitiesDescription,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        chartStatus,
        bossStatus,
        requestStatus,
        changeStatusUserLogin,
        guid,
        hasImportantMessage,
        selectedModuleIds,
        applicationProcessRunStepId,
        documentId,
        educationalRecordId,
        personId,
        personEmploymentTypeId,
        organizationChartId,
        qualificationId
        );
    }

    @Override
    public String toString() {
        return "ApplicationProcessCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (jobAfterProcess != null ? "jobAfterProcess=" + jobAfterProcess + ", " : "") +
                (acceptedUniversityAndDegree != null ? "acceptedUniversityAndDegree=" + acceptedUniversityAndDegree + ", " : "") +
                (startStudyDate != null ? "startStudyDate=" + startStudyDate + ", " : "") +
                (graduateDateOfNewCourse != null ? "graduateDateOfNewCourse=" + graduateDateOfNewCourse + ", " : "") +
                (averagePointOfNewCourse != null ? "averagePointOfNewCourse=" + averagePointOfNewCourse + ", " : "") +
                (acceptedMajorAndField != null ? "acceptedMajorAndField=" + acceptedMajorAndField + ", " : "") +
                (haveUsedEducationalFacilities != null ? "haveUsedEducationalFacilities=" + haveUsedEducationalFacilities + ", " : "") +
                (haveUsedEducationalFacilitiesDescription != null ? "haveUsedEducationalFacilitiesDescription=" + haveUsedEducationalFacilitiesDescription + ", " : "") +
                (dateOfAcceptanceOfDegree != null ? "dateOfAcceptanceOfDegree=" + dateOfAcceptanceOfDegree + ", " : "") +
                (typeAndAmountOfFacilities != null ? "typeAndAmountOfFacilities=" + typeAndAmountOfFacilities + ", " : "") +
                (academicCommitmentsFulfilled != null ? "academicCommitmentsFulfilled=" + academicCommitmentsFulfilled + ", " : "") +
                (remainingAcademicCommitments != null ? "remainingAcademicCommitments=" + remainingAcademicCommitments + ", " : "") +
                (requestedFacilitiesText != null ? "requestedFacilitiesText=" + requestedFacilitiesText + ", " : "") +
                (requestedFacilitiesDescription != null ? "requestedFacilitiesDescription=" + requestedFacilitiesDescription + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (chartStatus != null ? "chartStatus=" + chartStatus + ", " : "") +
                (bossStatus != null ? "bossStatus=" + bossStatus + ", " : "") +
                (requestStatus != null ? "requestStatus=" + requestStatus + ", " : "") +
                (changeStatusUserLogin != null ? "changeStatusUserLogin=" + changeStatusUserLogin + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (selectedModuleIds != null ? "selectedModuleIds=" + selectedModuleIds + ", " : "") +
                (applicationProcessRunStepId != null ? "applicationProcessRunStepId=" + applicationProcessRunStepId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (educationalRecordId != null ? "educationalRecordId=" + educationalRecordId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (personEmploymentTypeId != null ? "personEmploymentTypeId=" + personEmploymentTypeId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (qualificationId != null ? "qualificationId=" + qualificationId + ", " : "") +
            "}";
    }

}
