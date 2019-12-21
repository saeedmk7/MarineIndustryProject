package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MediaAwarenessReport entity.
 */
public class MediaAwarenessReportDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean designed;

    private Integer designedNumber;

    @Size(max = 4096)
    private String subject;

    @Size(max = 50)
    private String publishDate;

    @NotNull
    private Integer numberOfViewers;

    @NotNull
    private Integer durationOfOperation;

    @NotNull
    private Integer reportTime;

    @NotNull
    private Integer personHour;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long mediaProductTypeId;

    private String mediaProductTypeTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDesigned() {
        return designed;
    }

    public void setDesigned(Boolean designed) {
        this.designed = designed;
    }

    public Integer getDesignedNumber() {
        return designedNumber;
    }

    public void setDesignedNumber(Integer designedNumber) {
        this.designedNumber = designedNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getNumberOfViewers() {
        return numberOfViewers;
    }

    public void setNumberOfViewers(Integer numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
    }

    public Integer getDurationOfOperation() {
        return durationOfOperation;
    }

    public void setDurationOfOperation(Integer durationOfOperation) {
        this.durationOfOperation = durationOfOperation;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getPersonHour() {
        return personHour;
    }

    public void setPersonHour(Integer personHour) {
        this.personHour = personHour;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public Long getMediaProductTypeId() {
        return mediaProductTypeId;
    }

    public void setMediaProductTypeId(Long mediaProductTypeId) {
        this.mediaProductTypeId = mediaProductTypeId;
    }

    public String getMediaProductTypeTitle() {
        return mediaProductTypeTitle;
    }

    public void setMediaProductTypeTitle(String mediaProductTypeTitle) {
        this.mediaProductTypeTitle = mediaProductTypeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediaAwarenessReportDTO mediaAwarenessReportDTO = (MediaAwarenessReportDTO) o;
        if (mediaAwarenessReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mediaAwarenessReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MediaAwarenessReportDTO{" +
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
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", mediaProductType=" + getMediaProductTypeId() +
            ", mediaProductType='" + getMediaProductTypeTitle() + "'" +
            "}";
    }
}
