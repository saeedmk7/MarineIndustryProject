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
 * Criteria class for the EducationalRecord entity. This class is used in EducationalRecordResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /educational-records?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationalRecordCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter qualificationText;

    private StringFilter fieldOfStudyText;

    private StringFilter educationalCenterText;

    private StringFilter totalAverage;

    private IntegerFilter totalHour;

    private StringFilter startDate;

    private StringFilter endDate;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter applicationProcessId;

    private LongFilter qualificationId;

    private LongFilter fieldOfStudyId;

    private LongFilter personId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getQualificationText() {
        return qualificationText;
    }

    public void setQualificationText(StringFilter qualificationText) {
        this.qualificationText = qualificationText;
    }

    public StringFilter getFieldOfStudyText() {
        return fieldOfStudyText;
    }

    public void setFieldOfStudyText(StringFilter fieldOfStudyText) {
        this.fieldOfStudyText = fieldOfStudyText;
    }

    public StringFilter getEducationalCenterText() {
        return educationalCenterText;
    }

    public void setEducationalCenterText(StringFilter educationalCenterText) {
        this.educationalCenterText = educationalCenterText;
    }

    public StringFilter getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(StringFilter totalAverage) {
        this.totalAverage = totalAverage;
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

    public LongFilter getApplicationProcessId() {
        return applicationProcessId;
    }

    public void setApplicationProcessId(LongFilter applicationProcessId) {
        this.applicationProcessId = applicationProcessId;
    }

    public LongFilter getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(LongFilter qualificationId) {
        this.qualificationId = qualificationId;
    }

    public LongFilter getFieldOfStudyId() {
        return fieldOfStudyId;
    }

    public void setFieldOfStudyId(LongFilter fieldOfStudyId) {
        this.fieldOfStudyId = fieldOfStudyId;
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
        final EducationalRecordCriteria that = (EducationalRecordCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(qualificationText, that.qualificationText) &&
            Objects.equals(fieldOfStudyText, that.fieldOfStudyText) &&
            Objects.equals(educationalCenterText, that.educationalCenterText) &&
            Objects.equals(totalAverage, that.totalAverage) &&
            Objects.equals(totalHour, that.totalHour) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(applicationProcessId, that.applicationProcessId) &&
            Objects.equals(qualificationId, that.qualificationId) &&
            Objects.equals(fieldOfStudyId, that.fieldOfStudyId) &&
            Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        qualificationText,
        fieldOfStudyText,
        educationalCenterText,
        totalAverage,
        totalHour,
        startDate,
        endDate,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        applicationProcessId,
        qualificationId,
        fieldOfStudyId,
        personId
        );
    }

    @Override
    public String toString() {
        return "EducationalRecordCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (qualificationText != null ? "qualificationText=" + qualificationText + ", " : "") +
                (fieldOfStudyText != null ? "fieldOfStudyText=" + fieldOfStudyText + ", " : "") +
                (educationalCenterText != null ? "educationalCenterText=" + educationalCenterText + ", " : "") +
                (totalAverage != null ? "totalAverage=" + totalAverage + ", " : "") +
                (totalHour != null ? "totalHour=" + totalHour + ", " : "") +
                (startDate != null ? "startDate=" + startDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (applicationProcessId != null ? "applicationProcessId=" + applicationProcessId + ", " : "") +
                (qualificationId != null ? "qualificationId=" + qualificationId + ", " : "") +
                (fieldOfStudyId != null ? "fieldOfStudyId=" + fieldOfStudyId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
            "}";
    }

}
