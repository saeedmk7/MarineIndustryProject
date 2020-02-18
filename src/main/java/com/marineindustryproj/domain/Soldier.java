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
 * A Soldier.
 */
@Entity
@Table(name = "soldier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Soldier implements Serializable {

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

    @NotNull
    @Size(max = 100)
    @Column(name = "father_name", length = 100, nullable = false)
    private String fatherName;

    @Size(max = 12)
    @Column(name = "certificate_number", length = 12)
    private String certificateNumber;

    @NotNull
    @Size(max = 20)
    @Column(name = "national_id", length = 20, nullable = false)
    private String nationalId;

    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    @Column(name = "release_date")
    private ZonedDateTime releaseDate;

    @Size(max = 50)
    @Column(name = "personel_code", length = 50)
    private String personelCode;

    @Column(name = "employment_date")
    private ZonedDateTime employmentDate;

    @Size(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Size(max = 50)
    @Column(name = "mobile", length = 50)
    private String mobile;

    @Size(max = 4096)
    @Column(name = "address", length = 4096)
    private String address;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

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

    @OneToMany(mappedBy = "soldier")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SoldierTrainingReport> soldierTrainingReports = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "soldier_document",
               joinColumns = @JoinColumn(name = "soldiers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("soldiers")
    private Qualification lastQualification;

    @ManyToOne
    @JsonIgnoreProperties("soldiers")
    private FieldOfStudy lastFieldOfStudy;

    @ManyToOne
    @JsonIgnoreProperties("jobSoldiers")
    private Job job;

    @ManyToOne
    @JsonIgnoreProperties("soldiers")
    private OrganizationChart organizationChart;

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

    public Soldier name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public Soldier family(String family) {
        this.family = family;
        return this;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Soldier fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public Soldier certificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public Soldier nationalId(String nationalId) {
        this.nationalId = nationalId;
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public Soldier birthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public Soldier releaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPersonelCode() {
        return personelCode;
    }

    public Soldier personelCode(String personelCode) {
        this.personelCode = personelCode;
        return this;
    }

    public void setPersonelCode(String personelCode) {
        this.personelCode = personelCode;
    }

    public ZonedDateTime getEmploymentDate() {
        return employmentDate;
    }

    public Soldier employmentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
        return this;
    }

    public void setEmploymentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Soldier phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public Soldier mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public Soldier address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public Soldier description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Soldier createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Soldier createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Soldier modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Soldier modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public Soldier archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public Soldier archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public Soldier archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Soldier status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public Soldier guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<SoldierTrainingReport> getSoldierTrainingReports() {
        return soldierTrainingReports;
    }

    public Soldier soldierTrainingReports(Set<SoldierTrainingReport> soldierTrainingReports) {
        this.soldierTrainingReports = soldierTrainingReports;
        return this;
    }

    public Soldier addSoldierTrainingReport(SoldierTrainingReport soldierTrainingReport) {
        this.soldierTrainingReports.add(soldierTrainingReport);
        soldierTrainingReport.setSoldier(this);
        return this;
    }

    public Soldier removeSoldierTrainingReport(SoldierTrainingReport soldierTrainingReport) {
        this.soldierTrainingReports.remove(soldierTrainingReport);
        soldierTrainingReport.setSoldier(null);
        return this;
    }

    public void setSoldierTrainingReports(Set<SoldierTrainingReport> soldierTrainingReports) {
        this.soldierTrainingReports = soldierTrainingReports;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Soldier documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Soldier addDocument(Document document) {
        this.documents.add(document);
        document.getSoldiers().add(this);
        return this;
    }

    public Soldier removeDocument(Document document) {
        this.documents.remove(document);
        document.getSoldiers().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Qualification getLastQualification() {
        return lastQualification;
    }

    public Soldier lastQualification(Qualification qualification) {
        this.lastQualification = qualification;
        return this;
    }

    public void setLastQualification(Qualification qualification) {
        this.lastQualification = qualification;
    }

    public FieldOfStudy getLastFieldOfStudy() {
        return lastFieldOfStudy;
    }

    public Soldier lastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
        return this;
    }

    public void setLastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
    }

    public Job getJob() {
        return job;
    }

    public Soldier job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public Soldier organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
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
        Soldier soldier = (Soldier) o;
        if (soldier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soldier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Soldier{" +
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
            "}";
    }
}
