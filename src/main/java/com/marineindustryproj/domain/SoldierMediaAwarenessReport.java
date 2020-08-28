package com.marineindustryproj.domain;

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
 * A SoldierMediaAwarenessReport.
 */
@Entity
@Table(name = "soldier_media_awareness_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SoldierMediaAwarenessReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @NotNull
    @Column(name = "person_hour", nullable = false)
    private Integer personHour;

    @Size(max = 4096)
    @Column(name = "executive_training_company", length = 4096)
    private String executiveTrainingCompany;

    @Size(max = 4096)
    @Column(name = "certificate_status", length = 4096)
    private String certificateStatus;

    @Size(max = 4096)
    @Column(name = "certificate_number", length = 4096)
    private String certificateNumber;

    @NotNull
    @Column(name = "mi_year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Integer month;

    @NotNull
    @Column(name = "report_time", nullable = false)
    private Integer reportTime;

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "soldier_media_awareness_report_document",
               joinColumns = @JoinColumn(name = "soldier_media_awareness_reports_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("soldierMediaAwarenessReports")
    private Soldier soldier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public SoldierMediaAwarenessReport title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPersonHour() {
        return personHour;
    }

    public SoldierMediaAwarenessReport personHour(Integer personHour) {
        this.personHour = personHour;
        return this;
    }

    public void setPersonHour(Integer personHour) {
        this.personHour = personHour;
    }

    public String getExecutiveTrainingCompany() {
        return executiveTrainingCompany;
    }

    public SoldierMediaAwarenessReport executiveTrainingCompany(String executiveTrainingCompany) {
        this.executiveTrainingCompany = executiveTrainingCompany;
        return this;
    }

    public void setExecutiveTrainingCompany(String executiveTrainingCompany) {
        this.executiveTrainingCompany = executiveTrainingCompany;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public SoldierMediaAwarenessReport certificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
        return this;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public SoldierMediaAwarenessReport certificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getYear() {
        return year;
    }

    public SoldierMediaAwarenessReport year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public SoldierMediaAwarenessReport month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public SoldierMediaAwarenessReport reportTime(Integer reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public SoldierMediaAwarenessReport fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public SoldierMediaAwarenessReport description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public SoldierMediaAwarenessReport createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public SoldierMediaAwarenessReport createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public SoldierMediaAwarenessReport modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public SoldierMediaAwarenessReport modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public SoldierMediaAwarenessReport guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public SoldierMediaAwarenessReport documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public SoldierMediaAwarenessReport addDocument(Document document) {
        this.documents.add(document);
        document.getSoldierMediaAwarenessReports().add(this);
        return this;
    }

    public SoldierMediaAwarenessReport removeDocument(Document document) {
        this.documents.remove(document);
        document.getSoldierMediaAwarenessReports().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public SoldierMediaAwarenessReport soldier(Soldier soldier) {
        this.soldier = soldier;
        return this;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
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
        SoldierMediaAwarenessReport soldierMediaAwarenessReport = (SoldierMediaAwarenessReport) o;
        if (soldierMediaAwarenessReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soldierMediaAwarenessReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SoldierMediaAwarenessReport{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", personHour=" + getPersonHour() +
            ", executiveTrainingCompany='" + getExecutiveTrainingCompany() + "'" +
            ", certificateStatus='" + getCertificateStatus() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", reportTime=" + getReportTime() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
