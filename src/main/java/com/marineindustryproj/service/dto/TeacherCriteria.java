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
 * Criteria class for the Teacher entity. This class is used in TeacherResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /teachers?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TeacherCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter family;

    private StringFilter fatherName;

    private IntegerFilter scientificBasis;

    private BooleanFilter inquiry;

    private BooleanFilter schoolConfirmation;

    private BooleanFilter protectiveApproval;

    private StringFilter teachingSubject;

    private ZonedDateTimeFilter issueDate;

    private ZonedDateTimeFilter expirationDate;

    private IntegerFilter licenseNumber;

    private IntegerFilter sessionNumber;

    private StringFilter phoneNumber;

    private ZonedDateTimeFilter licenseRenewalDate;

    private LongFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter documentId;

    private LongFilter lastQualificationId;

    private LongFilter lastFieldOfStudyId;

    private LongFilter serviceUnitId;

    private LongFilter academicRankId;

    private LongFilter educationalModuleId;

    private LongFilter requestEducationalModuleId;

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

    public IntegerFilter getScientificBasis() {
        return scientificBasis;
    }

    public void setScientificBasis(IntegerFilter scientificBasis) {
        this.scientificBasis = scientificBasis;
    }

    public BooleanFilter getInquiry() {
        return inquiry;
    }

    public void setInquiry(BooleanFilter inquiry) {
        this.inquiry = inquiry;
    }

    public BooleanFilter getSchoolConfirmation() {
        return schoolConfirmation;
    }

    public void setSchoolConfirmation(BooleanFilter schoolConfirmation) {
        this.schoolConfirmation = schoolConfirmation;
    }

    public BooleanFilter getProtectiveApproval() {
        return protectiveApproval;
    }

    public void setProtectiveApproval(BooleanFilter protectiveApproval) {
        this.protectiveApproval = protectiveApproval;
    }

    public StringFilter getTeachingSubject() {
        return teachingSubject;
    }

    public void setTeachingSubject(StringFilter teachingSubject) {
        this.teachingSubject = teachingSubject;
    }

    public ZonedDateTimeFilter getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(ZonedDateTimeFilter issueDate) {
        this.issueDate = issueDate;
    }

    public ZonedDateTimeFilter getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ZonedDateTimeFilter expirationDate) {
        this.expirationDate = expirationDate;
    }

    public IntegerFilter getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(IntegerFilter licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public IntegerFilter getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(IntegerFilter sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ZonedDateTimeFilter getLicenseRenewalDate() {
        return licenseRenewalDate;
    }

    public void setLicenseRenewalDate(ZonedDateTimeFilter licenseRenewalDate) {
        this.licenseRenewalDate = licenseRenewalDate;
    }

    public LongFilter getCode() {
        return code;
    }

    public void setCode(LongFilter code) {
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

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
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

    public LongFilter getServiceUnitId() {
        return serviceUnitId;
    }

    public void setServiceUnitId(LongFilter serviceUnitId) {
        this.serviceUnitId = serviceUnitId;
    }

    public LongFilter getAcademicRankId() {
        return academicRankId;
    }

    public void setAcademicRankId(LongFilter academicRankId) {
        this.academicRankId = academicRankId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TeacherCriteria that = (TeacherCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(family, that.family) &&
            Objects.equals(fatherName, that.fatherName) &&
            Objects.equals(scientificBasis, that.scientificBasis) &&
            Objects.equals(inquiry, that.inquiry) &&
            Objects.equals(schoolConfirmation, that.schoolConfirmation) &&
            Objects.equals(protectiveApproval, that.protectiveApproval) &&
            Objects.equals(teachingSubject, that.teachingSubject) &&
            Objects.equals(issueDate, that.issueDate) &&
            Objects.equals(expirationDate, that.expirationDate) &&
            Objects.equals(licenseNumber, that.licenseNumber) &&
            Objects.equals(sessionNumber, that.sessionNumber) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(licenseRenewalDate, that.licenseRenewalDate) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(lastQualificationId, that.lastQualificationId) &&
            Objects.equals(lastFieldOfStudyId, that.lastFieldOfStudyId) &&
            Objects.equals(serviceUnitId, that.serviceUnitId) &&
            Objects.equals(academicRankId, that.academicRankId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        family,
        fatherName,
        scientificBasis,
        inquiry,
        schoolConfirmation,
        protectiveApproval,
        teachingSubject,
        issueDate,
        expirationDate,
        licenseNumber,
        sessionNumber,
        phoneNumber,
        licenseRenewalDate,
        code,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        documentId,
        lastQualificationId,
        lastFieldOfStudyId,
        serviceUnitId,
        academicRankId,
        educationalModuleId,
        requestEducationalModuleId
        );
    }

    @Override
    public String toString() {
        return "TeacherCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (family != null ? "family=" + family + ", " : "") +
                (fatherName != null ? "fatherName=" + fatherName + ", " : "") +
                (scientificBasis != null ? "scientificBasis=" + scientificBasis + ", " : "") +
                (inquiry != null ? "inquiry=" + inquiry + ", " : "") +
                (schoolConfirmation != null ? "schoolConfirmation=" + schoolConfirmation + ", " : "") +
                (protectiveApproval != null ? "protectiveApproval=" + protectiveApproval + ", " : "") +
                (teachingSubject != null ? "teachingSubject=" + teachingSubject + ", " : "") +
                (issueDate != null ? "issueDate=" + issueDate + ", " : "") +
                (expirationDate != null ? "expirationDate=" + expirationDate + ", " : "") +
                (licenseNumber != null ? "licenseNumber=" + licenseNumber + ", " : "") +
                (sessionNumber != null ? "sessionNumber=" + sessionNumber + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (licenseRenewalDate != null ? "licenseRenewalDate=" + licenseRenewalDate + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (lastQualificationId != null ? "lastQualificationId=" + lastQualificationId + ", " : "") +
                (lastFieldOfStudyId != null ? "lastFieldOfStudyId=" + lastFieldOfStudyId + ", " : "") +
                (serviceUnitId != null ? "serviceUnitId=" + serviceUnitId + ", " : "") +
                (academicRankId != null ? "academicRankId=" + academicRankId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
            "}";
    }

}
