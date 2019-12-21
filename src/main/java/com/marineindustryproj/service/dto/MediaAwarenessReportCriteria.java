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
 * Criteria class for the MediaAwarenessReport entity. This class is used in MediaAwarenessReportResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /media-awareness-reports?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MediaAwarenessReportCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BooleanFilter designed;

    private IntegerFilter designedNumber;

    private StringFilter subject;

    private StringFilter publishDate;

    private IntegerFilter numberOfViewers;

    private IntegerFilter durationOfOperation;

    private IntegerFilter reportTime;

    private IntegerFilter personHour;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter organizationChartId;

    private LongFilter mediaProductTypeId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BooleanFilter getDesigned() {
        return designed;
    }

    public void setDesigned(BooleanFilter designed) {
        this.designed = designed;
    }

    public IntegerFilter getDesignedNumber() {
        return designedNumber;
    }

    public void setDesignedNumber(IntegerFilter designedNumber) {
        this.designedNumber = designedNumber;
    }

    public StringFilter getSubject() {
        return subject;
    }

    public void setSubject(StringFilter subject) {
        this.subject = subject;
    }

    public StringFilter getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(StringFilter publishDate) {
        this.publishDate = publishDate;
    }

    public IntegerFilter getNumberOfViewers() {
        return numberOfViewers;
    }

    public void setNumberOfViewers(IntegerFilter numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
    }

    public IntegerFilter getDurationOfOperation() {
        return durationOfOperation;
    }

    public void setDurationOfOperation(IntegerFilter durationOfOperation) {
        this.durationOfOperation = durationOfOperation;
    }

    public IntegerFilter getReportTime() {
        return reportTime;
    }

    public void setReportTime(IntegerFilter reportTime) {
        this.reportTime = reportTime;
    }

    public IntegerFilter getPersonHour() {
        return personHour;
    }

    public void setPersonHour(IntegerFilter personHour) {
        this.personHour = personHour;
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

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getMediaProductTypeId() {
        return mediaProductTypeId;
    }

    public void setMediaProductTypeId(LongFilter mediaProductTypeId) {
        this.mediaProductTypeId = mediaProductTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MediaAwarenessReportCriteria that = (MediaAwarenessReportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(designed, that.designed) &&
            Objects.equals(designedNumber, that.designedNumber) &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(publishDate, that.publishDate) &&
            Objects.equals(numberOfViewers, that.numberOfViewers) &&
            Objects.equals(durationOfOperation, that.durationOfOperation) &&
            Objects.equals(reportTime, that.reportTime) &&
            Objects.equals(personHour, that.personHour) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(mediaProductTypeId, that.mediaProductTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        designed,
        designedNumber,
        subject,
        publishDate,
        numberOfViewers,
        durationOfOperation,
        reportTime,
        personHour,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        organizationChartId,
        mediaProductTypeId
        );
    }

    @Override
    public String toString() {
        return "MediaAwarenessReportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (designed != null ? "designed=" + designed + ", " : "") +
                (designedNumber != null ? "designedNumber=" + designedNumber + ", " : "") +
                (subject != null ? "subject=" + subject + ", " : "") +
                (publishDate != null ? "publishDate=" + publishDate + ", " : "") +
                (numberOfViewers != null ? "numberOfViewers=" + numberOfViewers + ", " : "") +
                (durationOfOperation != null ? "durationOfOperation=" + durationOfOperation + ", " : "") +
                (reportTime != null ? "reportTime=" + reportTime + ", " : "") +
                (personHour != null ? "personHour=" + personHour + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (mediaProductTypeId != null ? "mediaProductTypeId=" + mediaProductTypeId + ", " : "") +
            "}";
    }

}
