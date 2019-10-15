package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A EducationalRecord.
 */
@Entity
@Table(name = "educational_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1000)
    @Column(name = "qualification_text", length = 1000)
    private String qualificationText;

    @Size(max = 1000)
    @Column(name = "field_of_study_text", length = 1000)
    private String fieldOfStudyText;

    @Size(max = 1000)
    @Column(name = "educational_center_text", length = 1000)
    private String educationalCenterText;

    @Size(max = 50)
    @Column(name = "total_average", length = 50)
    private String totalAverage;

    @Column(name = "total_hour")
    private Integer totalHour;

    @Size(max = 50)
    @Column(name = "start_date", length = 50)
    private String startDate;

    @Size(max = 50)
    @Column(name = "end_date", length = 50)
    private String endDate;

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

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

    @ManyToOne
    @JsonIgnoreProperties("educationalRecords")
    private Qualification qualification;

    @ManyToOne
    @JsonIgnoreProperties("educationalRecords")
    private FieldOfStudy fieldOfStudy;

    @ManyToOne
    @JsonIgnoreProperties("educationalRecords")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualificationText() {
        return qualificationText;
    }

    public EducationalRecord qualificationText(String qualificationText) {
        this.qualificationText = qualificationText;
        return this;
    }

    public void setQualificationText(String qualificationText) {
        this.qualificationText = qualificationText;
    }

    public String getFieldOfStudyText() {
        return fieldOfStudyText;
    }

    public EducationalRecord fieldOfStudyText(String fieldOfStudyText) {
        this.fieldOfStudyText = fieldOfStudyText;
        return this;
    }

    public void setFieldOfStudyText(String fieldOfStudyText) {
        this.fieldOfStudyText = fieldOfStudyText;
    }

    public String getEducationalCenterText() {
        return educationalCenterText;
    }

    public EducationalRecord educationalCenterText(String educationalCenterText) {
        this.educationalCenterText = educationalCenterText;
        return this;
    }

    public void setEducationalCenterText(String educationalCenterText) {
        this.educationalCenterText = educationalCenterText;
    }

    public String getTotalAverage() {
        return totalAverage;
    }

    public EducationalRecord totalAverage(String totalAverage) {
        this.totalAverage = totalAverage;
        return this;
    }

    public void setTotalAverage(String totalAverage) {
        this.totalAverage = totalAverage;
    }

    public Integer getTotalHour() {
        return totalHour;
    }

    public EducationalRecord totalHour(Integer totalHour) {
        this.totalHour = totalHour;
        return this;
    }

    public void setTotalHour(Integer totalHour) {
        this.totalHour = totalHour;
    }

    public String getStartDate() {
        return startDate;
    }

    public EducationalRecord startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public EducationalRecord endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public EducationalRecord description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EducationalRecord createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EducationalRecord createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EducationalRecord modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EducationalRecord modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public EducationalRecord guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public EducationalRecord fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public EducationalRecord qualification(Qualification qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public EducationalRecord fieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        return this;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Person getPerson() {
        return person;
    }

    public EducationalRecord person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        EducationalRecord educationalRecord = (EducationalRecord) o;
        if (educationalRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalRecord{" +
            "id=" + getId() +
            ", qualificationText='" + getQualificationText() + "'" +
            ", fieldOfStudyText='" + getFieldOfStudyText() + "'" +
            ", educationalCenterText='" + getEducationalCenterText() + "'" +
            ", totalAverage='" + getTotalAverage() + "'" +
            ", totalHour=" + getTotalHour() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            "}";
    }
}
