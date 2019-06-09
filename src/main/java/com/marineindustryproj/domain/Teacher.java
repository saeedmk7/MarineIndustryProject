package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")*/
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "family", length = 100, nullable = false)
    private String family;

    @Size(max = 100)
    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Min(value = 1)
    @Max(value = 30)
    @Column(name = "scientific_basis")
    private Integer scientificBasis;

    @NotNull
    @Column(name = "inquiry", nullable = false)
    private Boolean inquiry;

    @NotNull
    @Column(name = "school_confirmation", nullable = false)
    private Boolean schoolConfirmation;

    @NotNull
    @Column(name = "protective_approval", nullable = false)
    private Boolean protectiveApproval;

    @Size(max = 1024)
    @Column(name = "teaching_subject", length = 1024)
    private String teachingSubject;

    @Column(name = "issue_date")
    private ZonedDateTime issueDate;

    @Column(name = "expiration_date")
    private ZonedDateTime expirationDate;

    @Column(name = "license_number")
    private Integer licenseNumber;

    @Column(name = "session_number")
    private Integer sessionNumber;

    @Size(max = 50)
    @Column(name = "phone_number", length = 50, unique = true)
    private String phoneNumber;

    @Column(name = "license_renewal_date")
    private ZonedDateTime licenseRenewalDate;

    @NotNull
    @Column(name = "code", nullable = false)
    private Long code;

    @Size(max = 50)
    @Column(name = "create_user_login", length = 50)
    private String createUserLogin;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Size(max = 50)
    @Column(name = "modify_user_login", length = 50)
    private String modifyUserLogin;

    @Column(name = "modify_date")
    private ZonedDateTime modifyDate;

    @NotNull
    @Column(name = "archived", nullable = false)
    private Boolean archived;

    @Size(max = 50)
    @Column(name = "archived_user_login", length = 50)
    private String archivedUserLogin;

    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "teacher")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "teacher")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "teacher_document",
               joinColumns = @JoinColumn(name = "teachers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private Qualification lastQualification;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private FieldOfStudy lastFieldOfStudy;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private ServiceUnit serviceUnit;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private AcademicRank academicRank;

    @ManyToMany(mappedBy = "teachers")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "teachers")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Teacher name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public Teacher family(String family) {
        this.family = family;
        return this;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Teacher fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getScientificBasis() {
        return scientificBasis;
    }

    public Teacher scientificBasis(Integer scientificBasis) {
        this.scientificBasis = scientificBasis;
        return this;
    }

    public void setScientificBasis(Integer scientificBasis) {
        this.scientificBasis = scientificBasis;
    }

    public Boolean isInquiry() {
        return inquiry;
    }

    public Teacher inquiry(Boolean inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    public void setInquiry(Boolean inquiry) {
        this.inquiry = inquiry;
    }

    public Boolean isSchoolConfirmation() {
        return schoolConfirmation;
    }

    public Teacher schoolConfirmation(Boolean schoolConfirmation) {
        this.schoolConfirmation = schoolConfirmation;
        return this;
    }

    public void setSchoolConfirmation(Boolean schoolConfirmation) {
        this.schoolConfirmation = schoolConfirmation;
    }

    public Boolean isProtectiveApproval() {
        return protectiveApproval;
    }

    public Teacher protectiveApproval(Boolean protectiveApproval) {
        this.protectiveApproval = protectiveApproval;
        return this;
    }

    public void setProtectiveApproval(Boolean protectiveApproval) {
        this.protectiveApproval = protectiveApproval;
    }

    public String getTeachingSubject() {
        return teachingSubject;
    }

    public Teacher teachingSubject(String teachingSubject) {
        this.teachingSubject = teachingSubject;
        return this;
    }

    public void setTeachingSubject(String teachingSubject) {
        this.teachingSubject = teachingSubject;
    }

    public ZonedDateTime getIssueDate() {
        return issueDate;
    }

    public Teacher issueDate(ZonedDateTime issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(ZonedDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public Teacher expirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public void setExpirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public Teacher licenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public Teacher sessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
        return this;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Teacher phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ZonedDateTime getLicenseRenewalDate() {
        return licenseRenewalDate;
    }

    public Teacher licenseRenewalDate(ZonedDateTime licenseRenewalDate) {
        this.licenseRenewalDate = licenseRenewalDate;
        return this;
    }

    public void setLicenseRenewalDate(ZonedDateTime licenseRenewalDate) {
        this.licenseRenewalDate = licenseRenewalDate;
    }

    public Long getCode() {
        return code;
    }

    public Teacher code(Long code) {
        this.code = code;
        return this;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Teacher createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Teacher createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Teacher modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Teacher modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public Teacher archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public Teacher archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public Teacher archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Teacher status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public Teacher guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public Teacher requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public Teacher addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setTeacher(this);
        return this;
    }

    public Teacher removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setTeacher(null);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public Teacher finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public Teacher addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setTeacher(this);
        return this;
    }

    public Teacher removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setTeacher(null);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Teacher documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Teacher addDocument(Document document) {
        this.documents.add(document);
        document.getTeachers().add(this);
        return this;
    }

    public Teacher removeDocument(Document document) {
        this.documents.remove(document);
        document.getTeachers().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Qualification getLastQualification() {
        return lastQualification;
    }

    public Teacher lastQualification(Qualification qualification) {
        this.lastQualification = qualification;
        return this;
    }

    public void setLastQualification(Qualification qualification) {
        this.lastQualification = qualification;
    }

    public FieldOfStudy getLastFieldOfStudy() {
        return lastFieldOfStudy;
    }

    public Teacher lastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
        return this;
    }

    public void setLastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
    }

    public ServiceUnit getServiceUnit() {
        return serviceUnit;
    }

    public Teacher serviceUnit(ServiceUnit serviceUnit) {
        this.serviceUnit = serviceUnit;
        return this;
    }

    public void setServiceUnit(ServiceUnit serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public Teacher academicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
        return this;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public Teacher educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public Teacher addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getTeachers().add(this);
        return this;
    }

    public Teacher removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getTeachers().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public Teacher requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public Teacher addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getTeachers().add(this);
        return this;
    }

    public Teacher removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getTeachers().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        if (teacher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Teacher{" +
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
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
