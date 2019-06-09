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
 * Criteria class for the JobRecord entity. This class is used in JobRecordResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /job-records?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class JobRecordCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter workOrganization;

    private StringFilter workSection;

    private StringFilter jobResponsibility;

    private StringFilter jobPosition;

    private IntegerFilter totalHour;

    private StringFilter startDate;

    private StringFilter endDate;

    private StringFilter leaveReason;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter personId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getWorkOrganization() {
        return workOrganization;
    }

    public void setWorkOrganization(StringFilter workOrganization) {
        this.workOrganization = workOrganization;
    }

    public StringFilter getWorkSection() {
        return workSection;
    }

    public void setWorkSection(StringFilter workSection) {
        this.workSection = workSection;
    }

    public StringFilter getJobResponsibility() {
        return jobResponsibility;
    }

    public void setJobResponsibility(StringFilter jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
    }

    public StringFilter getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(StringFilter jobPosition) {
        this.jobPosition = jobPosition;
    }

    public IntegerFilter getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(IntegerFilter totalHour) {
        this.totalHour = totalHour;
    }

    public StringFilter getStartDate() {
        return startDate;
    }

    public void setStartDate(StringFilter startDate) {
        this.startDate = startDate;
    }

    public StringFilter getEndDate() {
        return endDate;
    }

    public void setEndDate(StringFilter endDate) {
        this.endDate = endDate;
    }

    public StringFilter getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(StringFilter leaveReason) {
        this.leaveReason = leaveReason;
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

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JobRecordCriteria that = (JobRecordCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(workOrganization, that.workOrganization) &&
            Objects.equals(workSection, that.workSection) &&
            Objects.equals(jobResponsibility, that.jobResponsibility) &&
            Objects.equals(jobPosition, that.jobPosition) &&
            Objects.equals(totalHour, that.totalHour) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(leaveReason, that.leaveReason) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        workOrganization,
        workSection,
        jobResponsibility,
        jobPosition,
        totalHour,
        startDate,
        endDate,
        leaveReason,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        personId
        );
    }

    @Override
    public String toString() {
        return "JobRecordCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (workOrganization != null ? "workOrganization=" + workOrganization + ", " : "") +
                (workSection != null ? "workSection=" + workSection + ", " : "") +
                (jobResponsibility != null ? "jobResponsibility=" + jobResponsibility + ", " : "") +
                (jobPosition != null ? "jobPosition=" + jobPosition + ", " : "") +
                (totalHour != null ? "totalHour=" + totalHour + ", " : "") +
                (startDate != null ? "startDate=" + startDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (leaveReason != null ? "leaveReason=" + leaveReason + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
            "}";
    }

}
