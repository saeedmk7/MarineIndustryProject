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
 * A MediaAwarenessReport.
 */
@Entity
@Table(name = "media_awareness_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MediaAwarenessReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "designed", nullable = false)
    private Boolean designed;

    @Column(name = "designed_number")
    private Integer designedNumber;

    @Size(max = 4096)
    @Column(name = "subject", length = 4096)
    private String subject;

    @Size(max = 50)
    @Column(name = "publish_date", length = 50)
    private String publishDate;

    @NotNull
    @Column(name = "number_of_viewers", nullable = false)
    private Integer numberOfViewers;

    @NotNull
    @Column(name = "duration_of_operation", nullable = false)
    private Integer durationOfOperation;

    @NotNull
    @Column(name = "report_time", nullable = false)
    private Integer reportTime;

    @NotNull
    @Column(name = "person_hour", nullable = false)
    private Integer personHour;

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

    @ManyToOne
    @JsonIgnoreProperties("mediaAwarenessReports")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("mediaAwarenessReports")
    private MediaProductType mediaProductType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public MediaAwarenessReport designed(Boolean designed) {
        this.designed = designed;
        return this;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public Integer getDesignedNumber() {
        return designedNumber;
    }

    public MediaAwarenessReport designedNumber(Integer designedNumber) {
        this.designedNumber = designedNumber;
        return this;
    }

    public void setDesignedNumber(Integer designedNumber) {
        this.designedNumber = designedNumber;
    }

    public String getSubject() {
        return subject;
    }

    public MediaAwarenessReport subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public MediaAwarenessReport publishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getNumberOfViewers() {
        return numberOfViewers;
    }

    public MediaAwarenessReport numberOfViewers(Integer numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
        return this;
    }

    public void setNumberOfViewers(Integer numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
    }

    public Integer getDurationOfOperation() {
        return durationOfOperation;
    }

    public MediaAwarenessReport durationOfOperation(Integer durationOfOperation) {
        this.durationOfOperation = durationOfOperation;
        return this;
    }

    public void setDurationOfOperation(Integer durationOfOperation) {
        this.durationOfOperation = durationOfOperation;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public MediaAwarenessReport reportTime(Integer reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getPersonHour() {
        return personHour;
    }

    public MediaAwarenessReport personHour(Integer personHour) {
        this.personHour = personHour;
        return this;
    }

    public void setPersonHour(Integer personHour) {
        this.personHour = personHour;
    }

    public String getDescription() {
        return description;
    }

    public MediaAwarenessReport description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MediaAwarenessReport createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MediaAwarenessReport createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MediaAwarenessReport modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MediaAwarenessReport modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public MediaAwarenessReport guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public MediaAwarenessReport organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public MediaProductType getMediaProductType() {
        return mediaProductType;
    }

    public MediaAwarenessReport mediaProductType(MediaProductType mediaProductType) {
        this.mediaProductType = mediaProductType;
        return this;
    }

    public void setMediaProductType(MediaProductType mediaProductType) {
        this.mediaProductType = mediaProductType;
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
        MediaAwarenessReport mediaAwarenessReport = (MediaAwarenessReport) o;
        if (mediaAwarenessReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mediaAwarenessReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MediaAwarenessReport{" +
            "id=" + getId() +
            ", designed='" + isDesigned() + "'" +
            ", designedNumber=" + getDesignedNumber() +
            ", subject='" + getSubject() + "'" +
            ", publishDate='" + getPublishDate() + "'" +
            ", numberOfViewers=" + getNumberOfViewers() +
            ", durationOfOperation=" + getDurationOfOperation() +
            ", reportTime=" + getReportTime() +
            ", personHour=" + getPersonHour() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
