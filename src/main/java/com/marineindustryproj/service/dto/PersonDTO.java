package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Person entity.
 */
public class PersonDTO implements Serializable {

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

    @NotNull
    @Size(max = 12)
    private String certificateNumber;

    @NotNull
    @Size(max = 20)
    private String nationalId;

    @NotNull
    private ZonedDateTime birthDate;

    @Size(max = 50)
    private String personelCode;

    private ZonedDateTime employmentDate;

    private Integer yearOfService;

    @Size(max = 100)
    private String code;

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

    @Size(max = 50)
    private String phoneNumber;

    @Size(max = 50)
    private String mobile;

    @Size(max = 4096)
    private String address;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<ScientificWorkGroupDTO> scientificWorkGroups = new HashSet<>();

    private Long lastQualificationId;

    private String lastQualificationTitle;

    private Long lastFieldOfStudyId;

    private String lastFieldOfStudyTitle;

    private Long employmentTypeId;

    private String employmentTypeTitle;

    private Long workGroupId;

    private String workGroupTitle;

    private Long workIndustryId;

    private String workIndustryTitle;

    private Long jobId;

    private String jobTitle;

    private String jobCode;

    private Long practicaljobId;

    private String practicaljobTitle;

    private String practicaljobCode;

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

    public Integer getYearOfService() {
        return yearOfService;
    }

    public void setYearOfService(Integer yearOfService) {
        this.yearOfService = yearOfService;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Set<ScientificWorkGroupDTO> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroupDTO> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
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

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getEmploymentTypeTitle() {
        return employmentTypeTitle;
    }

    public void setEmploymentTypeTitle(String employmentTypeTitle) {
        this.employmentTypeTitle = employmentTypeTitle;
    }

    public Long getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(Long workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getWorkGroupTitle() {
        return workGroupTitle;
    }

    public void setWorkGroupTitle(String workGroupTitle) {
        this.workGroupTitle = workGroupTitle;
    }

    public Long getWorkIndustryId() {
        return workIndustryId;
    }

    public void setWorkIndustryId(Long workIndustryId) {
        this.workIndustryId = workIndustryId;
    }

    public String getWorkIndustryTitle() {
        return workIndustryTitle;
    }

    public void setWorkIndustryTitle(String workIndustryTitle) {
        this.workIndustryTitle = workIndustryTitle;
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

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Long getPracticaljobId() {
        return practicaljobId;
    }

    public void setPracticaljobId(Long jobId) {
        this.practicaljobId = jobId;
    }

    public String getPracticaljobTitle() {
        return practicaljobTitle;
    }

    public void setPracticaljobTitle(String jobTitle) {
        this.practicaljobTitle = jobTitle;
    }

    public String getPracticaljobCode() {
        return practicaljobCode;
    }

    public void setPracticaljobCode(String jobCode) {
        this.practicaljobCode = jobCode;
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

        PersonDTO personDTO = (PersonDTO) o;
        if (personDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", personelCode='" + getPersonelCode() + "'" +
            ", employmentDate='" + getEmploymentDate() + "'" +
            ", yearOfService=" + getYearOfService() +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", guid='" + getGuid() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", address='" + getAddress() + "'" +
            ", lastQualification=" + getLastQualificationId() +
            ", lastQualification='" + getLastQualificationTitle() + "'" +
            ", lastFieldOfStudy=" + getLastFieldOfStudyId() +
            ", lastFieldOfStudy='" + getLastFieldOfStudyTitle() + "'" +
            ", employmentType=" + getEmploymentTypeId() +
            ", employmentType='" + getEmploymentTypeTitle() + "'" +
            ", workGroup=" + getWorkGroupId() +
            ", workGroup='" + getWorkGroupTitle() + "'" +
            ", workIndustry=" + getWorkIndustryId() +
            ", workIndustry='" + getWorkIndustryTitle() + "'" +
            ", job=" + getJobId() +
            ", job='" + getJobTitle() + "'" +
            ", practicaljob=" + getPracticaljobId() +
            ", practicaljob='" + getPracticaljobTitle() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
