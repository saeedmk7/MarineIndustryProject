package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Teacher entity.
 */
public class TeacherDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String family;

    @Size(max = 100)
    private String fatherName;

    @Min(value = 1)
    @Max(value = 30)
    private Integer scientificBasis;

    @NotNull
    private Boolean inquiry;

    @NotNull
    private Boolean schoolConfirmation;

    @NotNull
    private Boolean protectiveApproval;

    @Size(max = 1024)
    private String teachingSubject;

    private ZonedDateTime issueDate;

    private ZonedDateTime expirationDate;

    private Integer licenseNumber;

    private Integer sessionNumber;

    @Size(max = 50)
    private String phoneNumber;

    private ZonedDateTime licenseRenewalDate;

    @NotNull
    private Long code;

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

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long lastQualificationId;

    private String lastQualificationTitle;

    private Long lastFieldOfStudyId;

    private String lastFieldOfStudyTitle;

    private Long serviceUnitId;

    private String serviceUnitTitle;

    private Long academicRankId;

    private String academicRankTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getScientificBasis() {
        return scientificBasis;
    }

    public void setScientificBasis(Integer scientificBasis) {
        this.scientificBasis = scientificBasis;
    }

    public Boolean isInquiry() {
        return inquiry;
    }

    public void setInquiry(Boolean inquiry) {
        this.inquiry = inquiry;
    }

    public Boolean isSchoolConfirmation() {
        return schoolConfirmation;
    }

    public void setSchoolConfirmation(Boolean schoolConfirmation) {
        this.schoolConfirmation = schoolConfirmation;
    }

    public Boolean isProtectiveApproval() {
        return protectiveApproval;
    }

    public void setProtectiveApproval(Boolean protectiveApproval) {
        this.protectiveApproval = protectiveApproval;
    }

    public String getTeachingSubject() {
        return teachingSubject;
    }

    public void setTeachingSubject(String teachingSubject) {
        this.teachingSubject = teachingSubject;
    }

    public ZonedDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(ZonedDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ZonedDateTime getLicenseRenewalDate() {
        return licenseRenewalDate;
    }

    public void setLicenseRenewalDate(ZonedDateTime licenseRenewalDate) {
        this.licenseRenewalDate = licenseRenewalDate;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getLastQualificationId() {
        return lastQualificationId;
    }

    public void setLastQualificationId(Long qualificationId) {
        this.lastQualificationId = qualificationId;
    }

    public String getLastQualificationTitle() {
        return lastQualificationTitle;
    }

    public void setLastQualificationTitle(String qualificationTitle) {
        this.lastQualificationTitle = qualificationTitle;
    }

    public Long getLastFieldOfStudyId() {
        return lastFieldOfStudyId;
    }

    public void setLastFieldOfStudyId(Long fieldOfStudyId) {
        this.lastFieldOfStudyId = fieldOfStudyId;
    }

    public String getLastFieldOfStudyTitle() {
        return lastFieldOfStudyTitle;
    }

    public void setLastFieldOfStudyTitle(String fieldOfStudyTitle) {
        this.lastFieldOfStudyTitle = fieldOfStudyTitle;
    }

    public Long getServiceUnitId() {
        return serviceUnitId;
    }

    public void setServiceUnitId(Long serviceUnitId) {
        this.serviceUnitId = serviceUnitId;
    }

    public String getServiceUnitTitle() {
        return serviceUnitTitle;
    }

    public void setServiceUnitTitle(String serviceUnitTitle) {
        this.serviceUnitTitle = serviceUnitTitle;
    }

    public Long getAcademicRankId() {
        return academicRankId;
    }

    public void setAcademicRankId(Long academicRankId) {
        this.academicRankId = academicRankId;
    }

    public String getAcademicRankTitle() {
        return academicRankTitle;
    }

    public void setAcademicRankTitle(String academicRankTitle) {
        this.academicRankTitle = academicRankTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherDTO teacherDTO = (TeacherDTO) o;
        if (teacherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", scientificBasis=" + getScientificBasis() +
            ", inquiry='" + isInquiry() + "'" +
            ", schoolConfirmation='" + isSchoolConfirmation() + "'" +
            ", protectiveApproval='" + isProtectiveApproval() + "'" +
            ", teachingSubject='" + getTeachingSubject() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", licenseNumber=" + getLicenseNumber() +
            ", sessionNumber=" + getSessionNumber() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", licenseRenewalDate='" + getLicenseRenewalDate() + "'" +
            ", code=" + getCode() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", lastQualification=" + getLastQualificationId() +
            ", lastQualification='" + getLastQualificationTitle() + "'" +
            ", lastFieldOfStudy=" + getLastFieldOfStudyId() +
            ", lastFieldOfStudy='" + getLastFieldOfStudyTitle() + "'" +
            ", serviceUnit=" + getServiceUnitId() +
            ", serviceUnit='" + getServiceUnitTitle() + "'" +
            ", academicRank=" + getAcademicRankId() +
            ", academicRank='" + getAcademicRankTitle() + "'" +
            "}";
    }
}
