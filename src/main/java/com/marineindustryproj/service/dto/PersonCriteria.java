package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the Person entity. This class is used in PersonResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /people?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter family;

    private StringFilter fatherName;

    private StringFilter certificateNumber;

    private StringFilter nationalId;

    private ZonedDateTimeFilter birthDate;

    private StringFilter personelCode;

    private ZonedDateTimeFilter employmentDate;

    private IntegerFilter yearOfService;

    private StringFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private StringFilter guid;

    private StringFilter phoneNumber;

    private StringFilter mobile;

    private StringFilter address;

    private LongFilter finalNiazsanjiReportPersonId;

    private LongFilter pollScoreId;

    private LongFilter niazsanjiFardiId;

    private LongFilter requestNiazsanjiFardiId;

    private LongFilter educationalHistoryId;

    private LongFilter educationalRecordId;

    private LongFilter jobRecordId;

    private LongFilter researchRecordId;

    private LongFilter teachingRecordId;

    private LongFilter preJobNiazsanjiId;

    private LongFilter jobNiazsanjiId;

    private LongFilter niazsanjiOtherId;

    private LongFilter requestOtherNiazsanjiId;

    private LongFilter prioritizeRequestNiazsanjiId;

    private LongFilter documentId;

    private LongFilter scientificWorkGroupId;

    private LongFilter lastQualificationId;

    private LongFilter lastFieldOfStudyId;

    private LongFilter employmentTypeId;

    private LongFilter workGroupId;

    private LongFilter workIndustryId;

    private LongFilter jobId;

    private LongFilter practicaljobId;

    private LongFilter organizationChartId;

    private LongFilter mainTaskId;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter designAndPlanningId;

    private LongFilter runPhaseId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getFamily() {
        return family;
    }

    public void setFamily(StringFilter family) {
        this.family = family;
    }

    public StringFilter getFatherName() {
        return fatherName;
    }

    public void setFatherName(StringFilter fatherName) {
        this.fatherName = fatherName;
    }

    public StringFilter getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(StringFilter certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public StringFilter getNationalId() {
        return nationalId;
    }

    public void setNationalId(StringFilter nationalId) {
        this.nationalId = nationalId;
    }

    public ZonedDateTimeFilter getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTimeFilter birthDate) {
        this.birthDate = birthDate;
    }

    public StringFilter getPersonelCode() {
        return personelCode;
    }

    public void setPersonelCode(StringFilter personelCode) {
        this.personelCode = personelCode;
    }

    public ZonedDateTimeFilter getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(ZonedDateTimeFilter employmentDate) {
        this.employmentDate = employmentDate;
    }

    public IntegerFilter getYearOfService() {
        return yearOfService;
    }

    public void setYearOfService(IntegerFilter yearOfService) {
        this.yearOfService = yearOfService;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter getMobile() {
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public LongFilter getFinalNiazsanjiReportPersonId() {
        return finalNiazsanjiReportPersonId;
    }

    public void setFinalNiazsanjiReportPersonId(LongFilter finalNiazsanjiReportPersonId) {
        this.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
    }

    public LongFilter getPollScoreId() {
        return pollScoreId;
    }

    public void setPollScoreId(LongFilter pollScoreId) {
        this.pollScoreId = pollScoreId;
    }

    public LongFilter getNiazsanjiFardiId() {
        return niazsanjiFardiId;
    }

    public void setNiazsanjiFardiId(LongFilter niazsanjiFardiId) {
        this.niazsanjiFardiId = niazsanjiFardiId;
    }

    public LongFilter getRequestNiazsanjiFardiId() {
        return requestNiazsanjiFardiId;
    }

    public void setRequestNiazsanjiFardiId(LongFilter requestNiazsanjiFardiId) {
        this.requestNiazsanjiFardiId = requestNiazsanjiFardiId;
    }

    public LongFilter getEducationalHistoryId() {
        return educationalHistoryId;
    }

    public void setEducationalHistoryId(LongFilter educationalHistoryId) {
        this.educationalHistoryId = educationalHistoryId;
    }

    public LongFilter getEducationalRecordId() {
        return educationalRecordId;
    }

    public void setEducationalRecordId(LongFilter educationalRecordId) {
        this.educationalRecordId = educationalRecordId;
    }

    public LongFilter getJobRecordId() {
        return jobRecordId;
    }

    public void setJobRecordId(LongFilter jobRecordId) {
        this.jobRecordId = jobRecordId;
    }

    public LongFilter getResearchRecordId() {
        return researchRecordId;
    }

    public void setResearchRecordId(LongFilter researchRecordId) {
        this.researchRecordId = researchRecordId;
    }

    public LongFilter getTeachingRecordId() {
        return teachingRecordId;
    }

    public void setTeachingRecordId(LongFilter teachingRecordId) {
        this.teachingRecordId = teachingRecordId;
    }

    public LongFilter getJobNiazsanjiId() {
        return jobNiazsanjiId;
    }

    public void setJobNiazsanjiId(LongFilter jobNiazsanjiId) {
        this.jobNiazsanjiId = jobNiazsanjiId;
    }

    public LongFilter getNiazsanjiOtherId() {
        return niazsanjiOtherId;
    }

    public void setNiazsanjiOtherId(LongFilter niazsanjiOtherId) {
        this.niazsanjiOtherId = niazsanjiOtherId;
    }

    public LongFilter getRequestOtherNiazsanjiId() {
        return requestOtherNiazsanjiId;
    }

    public void setRequestOtherNiazsanjiId(LongFilter requestOtherNiazsanjiId) {
        this.requestOtherNiazsanjiId = requestOtherNiazsanjiId;
    }

    public LongFilter getPrioritizeRequestNiazsanjiId() {
        return prioritizeRequestNiazsanjiId;
    }

    public void setPrioritizeRequestNiazsanjiId(LongFilter prioritizeRequestNiazsanjiId) {
        this.prioritizeRequestNiazsanjiId = prioritizeRequestNiazsanjiId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getScientificWorkGroupId() {
        return scientificWorkGroupId;
    }

    public void setScientificWorkGroupId(LongFilter scientificWorkGroupId) {
        this.scientificWorkGroupId = scientificWorkGroupId;
    }

    public LongFilter getLastQualificationId() {
        return lastQualificationId;
    }

    public void setLastQualificationId(LongFilter lastQualificationId) {
        this.lastQualificationId = lastQualificationId;
    }

    public LongFilter getLastFieldOfStudyId() {
        return lastFieldOfStudyId;
    }

    public void setLastFieldOfStudyId(LongFilter lastFieldOfStudyId) {
        this.lastFieldOfStudyId = lastFieldOfStudyId;
    }

    public LongFilter getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(LongFilter employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public LongFilter getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(LongFilter workGroupId) {
        this.workGroupId = workGroupId;
    }

    public LongFilter getWorkIndustryId() {
        return workIndustryId;
    }

    public void setWorkIndustryId(LongFilter workIndustryId) {
        this.workIndustryId = workIndustryId;
    }

    public LongFilter getJobId() {
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getPracticaljobId() {
        return practicaljobId;
    }

    public void setPracticaljobId(LongFilter practicaljobId) {
        this.practicaljobId = practicaljobId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getMainTaskId() {
        return mainTaskId;
    }

    public void setMainTaskId(LongFilter mainTaskId) {
        this.mainTaskId = mainTaskId;
    }

    public LongFilter getRequestOrganizationNiazsanjiId() {
        return requestOrganizationNiazsanjiId;
    }

    public void setRequestOrganizationNiazsanjiId(LongFilter requestOrganizationNiazsanjiId) {
        this.requestOrganizationNiazsanjiId = requestOrganizationNiazsanjiId;
    }

    public LongFilter getFinalOrganizationNiazsanjiId() {
        return finalOrganizationNiazsanjiId;
    }

    public void setFinalOrganizationNiazsanjiId(LongFilter finalOrganizationNiazsanjiId) {
        this.finalOrganizationNiazsanjiId = finalOrganizationNiazsanjiId;
    }

    public LongFilter getDesignAndPlanningId() {
        return designAndPlanningId;
    }

    public void setDesignAndPlanningId(LongFilter designAndPlanningId) {
        this.designAndPlanningId = designAndPlanningId;
    }

    public LongFilter getRunPhaseId() {
        return runPhaseId;
    }

    public void setRunPhaseId(LongFilter runPhaseId) {
        this.runPhaseId = runPhaseId;
    }

    public LongFilter getPreJobNiazsanjiId() {
        return preJobNiazsanjiId;
    }

    public void setPreJobNiazsanjiId(LongFilter preJobNiazsanjiId) {
        this.preJobNiazsanjiId = preJobNiazsanjiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonCriteria that = (PersonCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(family, that.family) &&
            Objects.equals(fatherName, that.fatherName) &&
            Objects.equals(certificateNumber, that.certificateNumber) &&
            Objects.equals(nationalId, that.nationalId) &&
            Objects.equals(birthDate, that.birthDate) &&
            Objects.equals(personelCode, that.personelCode) &&
            Objects.equals(employmentDate, that.employmentDate) &&
            Objects.equals(yearOfService, that.yearOfService) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(address, that.address) &&
            Objects.equals(finalNiazsanjiReportPersonId, that.finalNiazsanjiReportPersonId) &&
            Objects.equals(pollScoreId, that.pollScoreId) &&
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(requestNiazsanjiFardiId, that.requestNiazsanjiFardiId) &&
            Objects.equals(educationalHistoryId, that.educationalHistoryId) &&
            Objects.equals(educationalRecordId, that.educationalRecordId) &&
            Objects.equals(jobRecordId, that.jobRecordId) &&
            Objects.equals(researchRecordId, that.researchRecordId) &&
            Objects.equals(teachingRecordId, that.teachingRecordId) &&
            Objects.equals(preJobNiazsanjiId, that.preJobNiazsanjiId) &&
            Objects.equals(jobNiazsanjiId, that.jobNiazsanjiId) &&
            Objects.equals(niazsanjiOtherId, that.niazsanjiOtherId) &&
            Objects.equals(requestOtherNiazsanjiId, that.requestOtherNiazsanjiId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(scientificWorkGroupId, that.scientificWorkGroupId) &&
            Objects.equals(lastQualificationId, that.lastQualificationId) &&
            Objects.equals(lastFieldOfStudyId, that.lastFieldOfStudyId) &&
            Objects.equals(employmentTypeId, that.employmentTypeId) &&
            Objects.equals(workGroupId, that.workGroupId) &&
            Objects.equals(workIndustryId, that.workIndustryId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(practicaljobId, that.practicaljobId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(mainTaskId, that.mainTaskId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(designAndPlanningId, that.designAndPlanningId) &&
            Objects.equals(runPhaseId, that.runPhaseId) &&
            Objects.equals(preJobNiazsanjiId, that.preJobNiazsanjiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        family,
        fatherName,
        certificateNumber,
        nationalId,
        birthDate,
        personelCode,
        employmentDate,
        yearOfService,
        code,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        guid,
        phoneNumber,
        mobile,
        address,
        finalNiazsanjiReportPersonId,
        pollScoreId,
        niazsanjiFardiId,
        requestNiazsanjiFardiId,
        educationalHistoryId,
        educationalRecordId,
        jobRecordId,
        researchRecordId,
        teachingRecordId,
        preJobNiazsanjiId,
        jobNiazsanjiId,
        niazsanjiOtherId,
        requestOtherNiazsanjiId,
        prioritizeRequestNiazsanjiId,
        documentId,
        scientificWorkGroupId,
        lastQualificationId,
        lastFieldOfStudyId,
        employmentTypeId,
        workGroupId,
        workIndustryId,
        jobId,
        practicaljobId,
        organizationChartId,
        mainTaskId,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        designAndPlanningId,
        runPhaseId,
        preJobNiazsanjiId
        );
    }

    @Override
    public String toString() {
        return "PersonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (family != null ? "family=" + family + ", " : "") +
                (fatherName != null ? "fatherName=" + fatherName + ", " : "") +
                (certificateNumber != null ? "certificateNumber=" + certificateNumber + ", " : "") +
                (nationalId != null ? "nationalId=" + nationalId + ", " : "") +
                (birthDate != null ? "birthDate=" + birthDate + ", " : "") +
                (personelCode != null ? "personelCode=" + personelCode + ", " : "") +
                (employmentDate != null ? "employmentDate=" + employmentDate + ", " : "") +
                (yearOfService != null ? "yearOfService=" + yearOfService + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (finalNiazsanjiReportPersonId != null ? "finalNiazsanjiReportPersonId=" + finalNiazsanjiReportPersonId + ", " : "") +
                (pollScoreId != null ? "pollScoreId=" + pollScoreId + ", " : "") +
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (requestNiazsanjiFardiId != null ? "requestNiazsanjiFardiId=" + requestNiazsanjiFardiId + ", " : "") +
                (educationalHistoryId != null ? "educationalHistoryId=" + educationalHistoryId + ", " : "") +
                (educationalRecordId != null ? "educationalRecordId=" + educationalRecordId + ", " : "") +
                (jobRecordId != null ? "jobRecordId=" + jobRecordId + ", " : "") +
                (researchRecordId != null ? "researchRecordId=" + researchRecordId + ", " : "") +
                (teachingRecordId != null ? "teachingRecordId=" + teachingRecordId + ", " : "") +
                (preJobNiazsanjiId != null ? "preJobNiazsanjiId=" + preJobNiazsanjiId + ", " : "") +
                (jobNiazsanjiId != null ? "jobNiazsanjiId=" + jobNiazsanjiId + ", " : "") +
                (niazsanjiOtherId != null ? "niazsanjiOtherId=" + niazsanjiOtherId + ", " : "") +
                (requestOtherNiazsanjiId != null ? "requestOtherNiazsanjiId=" + requestOtherNiazsanjiId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (scientificWorkGroupId != null ? "scientificWorkGroupId=" + scientificWorkGroupId + ", " : "") +
                (lastQualificationId != null ? "lastQualificationId=" + lastQualificationId + ", " : "") +
                (lastFieldOfStudyId != null ? "lastFieldOfStudyId=" + lastFieldOfStudyId + ", " : "") +
                (employmentTypeId != null ? "employmentTypeId=" + employmentTypeId + ", " : "") +
                (workGroupId != null ? "workGroupId=" + workGroupId + ", " : "") +
                (workIndustryId != null ? "workIndustryId=" + workIndustryId + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (practicaljobId != null ? "practicaljobId=" + practicaljobId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (mainTaskId != null ? "mainTaskId=" + mainTaskId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (designAndPlanningId != null ? "designAndPlanningId=" + designAndPlanningId + ", " : "") +
                (runPhaseId != null ? "runPhaseId=" + runPhaseId + ", " : "") +
                (preJobNiazsanjiId != null ? "preJobNiazsanjiId=" + preJobNiazsanjiId + ", " : "") +
            "}";
    }

}
