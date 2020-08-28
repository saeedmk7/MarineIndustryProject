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
 * Criteria class for the SoldierMediaAwarenessReport entity. This class is used in SoldierMediaAwarenessReportResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /soldier-media-awareness-reports?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SoldierMediaAwarenessReportCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter personHour;

    private StringFilter executiveTrainingCompany;

    private StringFilter certificateStatus;

    private StringFilter certificateNumber;

    private IntegerFilter year;

    private IntegerFilter month;

    private IntegerFilter reportTime;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter documentId;

    private LongFilter soldierId;

    private LongFilter soldierOrganizationChartId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public IntegerFilter getPersonHour() {
        return personHour;
    }

    public void setPersonHour(IntegerFilter personHour) {
        this.personHour = personHour;
    }

    public StringFilter getExecutiveTrainingCompany() {
        return executiveTrainingCompany;
    }

    public void setExecutiveTrainingCompany(StringFilter executiveTrainingCompany) {
        this.executiveTrainingCompany = executiveTrainingCompany;
    }

    public StringFilter getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(StringFilter certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public StringFilter getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(StringFilter certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public IntegerFilter getYear() {
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public IntegerFilter getMonth() {
        return month;
    }

    public void setMonth(IntegerFilter month) {
        this.month = month;
    }

    public IntegerFilter getReportTime() {
        return reportTime;
    }

    public void setReportTime(IntegerFilter reportTime) {
        this.reportTime = reportTime;
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

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(LongFilter soldierId) {
        this.soldierId = soldierId;
    }

    public LongFilter getSoldierOrganizationChartId() {
        return soldierOrganizationChartId;
    }

    public void setSoldierOrganizationChartId(LongFilter soldierOrganizationChartId) {
        this.soldierOrganizationChartId = soldierOrganizationChartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SoldierMediaAwarenessReportCriteria that = (SoldierMediaAwarenessReportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(personHour, that.personHour) &&
            Objects.equals(executiveTrainingCompany, that.executiveTrainingCompany) &&
            Objects.equals(certificateStatus, that.certificateStatus) &&
            Objects.equals(certificateNumber, that.certificateNumber) &&
            Objects.equals(year, that.year) &&
            Objects.equals(month, that.month) &&
            Objects.equals(reportTime, that.reportTime) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(soldierOrganizationChartId, that.soldierOrganizationChartId) &&
            Objects.equals(soldierId, that.soldierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        personHour,
        executiveTrainingCompany,
        certificateStatus,
        certificateNumber,
        year,
        month,
        reportTime,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        documentId,
        soldierOrganizationChartId,
        soldierId
        );
    }

    @Override
    public String toString() {
        return "SoldierMediaAwarenessReportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (personHour != null ? "personHour=" + personHour + ", " : "") +
                (executiveTrainingCompany != null ? "executiveTrainingCompany=" + executiveTrainingCompany + ", " : "") +
                (certificateStatus != null ? "certificateStatus=" + certificateStatus + ", " : "") +
                (certificateNumber != null ? "certificateNumber=" + certificateNumber + ", " : "") +
                (year != null ? "year=" + year + ", " : "") +
                (month != null ? "month=" + month + ", " : "") +
                (reportTime != null ? "reportTime=" + reportTime + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (soldierId != null ? "soldierId=" + soldierId + ", " : "") +
                (soldierOrganizationChartId != null ? "soldierOrganizationChartId=" + soldierOrganizationChartId + ", " : "") +
            "}";
    }

}
