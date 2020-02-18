package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Soldier entity.
 */
public class SoldierDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String family;

    @NotNull
    @Size(max = 100)
    private String fatherName;

    @Size(max = 12)
    private String certificateNumber;

    @NotNull
    @Size(max = 20)
    private String nationalId;

    private ZonedDateTime birthDate;

    private ZonedDateTime releaseDate;

    @Size(max = 50)
    private String personelCode;

    private ZonedDateTime employmentDate;

    @Size(max = 50)
    private String phoneNumber;

    @Size(max = 50)
    private String mobile;

    @Size(max = 4096)
    private String address;

    @Size(max = 4096)
    private String description;

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

    @Size(max = 50)
    private String guid;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long lastQualificationId;

    private String lastQualificationTitle;

    private Long lastFieldOfStudyId;

    private String lastFieldOfStudyTitle;

    private Long jobId;

    private String jobTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

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

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPersonelCode() {
        return personelCode;
    }

    public void setPersonelCode(String personelCode) {
        this.personelCode = personelCode;
    }

    public ZonedDateTime getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SoldierDTO soldierDTO = (SoldierDTO) o;
        if (soldierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soldierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SoldierDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", personelCode='" + getPersonelCode() + "'" +
            ", employmentDate='" + getEmploymentDate() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", address='" + getAddress() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", guid='" + getGuid() + "'" +
            ", lastQualification=" + getLastQualificationId() +
            ", lastQualification='" + getLastQualificationTitle() + "'" +
            ", lastFieldOfStudy=" + getLastFieldOfStudyId() +
            ", lastFieldOfStudy='" + getLastFieldOfStudyTitle() + "'" +
            ", job=" + getJobId() +
            ", job='" + getJobTitle() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
