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
 * Criteria class for the Soldier entity. This class is used in SoldierResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /soldiers?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SoldierCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter family;

    private StringFilter fatherName;

    private StringFilter certificateNumber;

    private StringFilter nationalId;

    private ZonedDateTimeFilter birthDate;

    private ZonedDateTimeFilter releaseDate;

    private StringFilter personelCode;

    private ZonedDateTimeFilter employmentDate;

    private StringFilter phoneNumber;

    private StringFilter mobile;

    private StringFilter address;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private StringFilter guid;

    private LongFilter soldierTrainingReportId;

    private LongFilter documentId;

    private LongFilter lastQualificationId;

    private LongFilter lastFieldOfStudyId;

    private LongFilter jobId;

    private LongFilter organizationChartId;

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

    public ZonedDateTimeFilter getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTimeFilter releaseDate) {
        this.releaseDate = releaseDate;
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

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public LongFilter getSoldierTrainingReportId() {
        return soldierTrainingReportId;
    }

    public void setSoldierTrainingReportId(LongFilter soldierTrainingReportId) {
        this.soldierTrainingReportId = soldierTrainingReportId;
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

    public LongFilter getJobId() {
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SoldierCriteria that = (SoldierCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(family, that.family) &&
            Objects.equals(fatherName, that.fatherName) &&
            Objects.equals(certificateNumber, that.certificateNumber) &&
            Objects.equals(nationalId, that.nationalId) &&
            Objects.equals(birthDate, that.birthDate) &&
            Objects.equals(releaseDate, that.releaseDate) &&
            Objects.equals(personelCode, that.personelCode) &&
            Objects.equals(employmentDate, that.employmentDate) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(address, that.address) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(soldierTrainingReportId, that.soldierTrainingReportId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(lastQualificationId, that.lastQualificationId) &&
            Objects.equals(lastFieldOfStudyId, that.lastFieldOfStudyId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(organizationChartId, that.organizationChartId);
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
        releaseDate,
        personelCode,
        employmentDate,
        phoneNumber,
        mobile,
        address,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        guid,
        soldierTrainingReportId,
        documentId,
        lastQualificationId,
        lastFieldOfStudyId,
        jobId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "SoldierCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (family != null ? "family=" + family + ", " : "") +
                (fatherName != null ? "fatherName=" + fatherName + ", " : "") +
                (certificateNumber != null ? "certificateNumber=" + certificateNumber + ", " : "") +
                (nationalId != null ? "nationalId=" + nationalId + ", " : "") +
                (birthDate != null ? "birthDate=" + birthDate + ", " : "") +
                (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") +
                (personelCode != null ? "personelCode=" + personelCode + ", " : "") +
                (employmentDate != null ? "employmentDate=" + employmentDate + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (soldierTrainingReportId != null ? "soldierTrainingReportId=" + soldierTrainingReportId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (lastQualificationId != null ? "lastQualificationId=" + lastQualificationId + ", " : "") +
                (lastFieldOfStudyId != null ? "lastFieldOfStudyId=" + lastFieldOfStudyId + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

}
