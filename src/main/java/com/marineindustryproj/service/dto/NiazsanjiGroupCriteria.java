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
 * Criteria class for the NiazsanjiGroup entity. This class is used in NiazsanjiGroupResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /niazsanji-groups?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NiazsanjiGroupCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter editorPerson;

    private ZonedDateTimeFilter reviewDate;

    private ZonedDateTimeFilter scheduleDate;

    private StringFilter firstThreeJobCode;

    private IntegerFilter priceCost;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter jobId;

    private LongFilter educationalModuleId;

    private LongFilter scientificWorkGroupId;

    public NiazsanjiGroupCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEditorPerson() {
        return editorPerson;
    }

    public void setEditorPerson(StringFilter editorPerson) {
        this.editorPerson = editorPerson;
    }

    public ZonedDateTimeFilter getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(ZonedDateTimeFilter reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ZonedDateTimeFilter getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(ZonedDateTimeFilter scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public StringFilter getFirstThreeJobCode() {
        return firstThreeJobCode;
    }

    public void setFirstThreeJobCode(StringFilter firstThreeJobCode) {
        this.firstThreeJobCode = firstThreeJobCode;
    }

    public IntegerFilter getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(IntegerFilter priceCost) {
        this.priceCost = priceCost;
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

    public BooleanFilter getArchived() {
        return archived;
    }

    public void setArchived(BooleanFilter archived) {
        this.archived = archived;
    }

    public StringFilter getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(StringFilter archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTimeFilter getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTimeFilter archivedDate) {
        this.archivedDate = archivedDate;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getJobId() {
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getScientificWorkGroupId() {
        return scientificWorkGroupId;
    }

    public void setScientificWorkGroupId(LongFilter scientificWorkGroupId) {
        this.scientificWorkGroupId = scientificWorkGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NiazsanjiGroupCriteria that = (NiazsanjiGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(editorPerson, that.editorPerson) &&
            Objects.equals(reviewDate, that.reviewDate) &&
            Objects.equals(scheduleDate, that.scheduleDate) &&
            Objects.equals(firstThreeJobCode, that.firstThreeJobCode) &&
            Objects.equals(priceCost, that.priceCost) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(scientificWorkGroupId, that.scientificWorkGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        editorPerson,
        reviewDate,
        scheduleDate,
        firstThreeJobCode,
        priceCost,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        jobId,
        educationalModuleId,
        scientificWorkGroupId
        );
    }

    @Override
    public String toString() {
        return "NiazsanjiGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (editorPerson != null ? "editorPerson=" + editorPerson + ", " : "") +
                (reviewDate != null ? "reviewDate=" + reviewDate + ", " : "") +
                (scheduleDate != null ? "scheduleDate=" + scheduleDate + ", " : "") +
                (firstThreeJobCode != null ? "firstThreeJobCode=" + firstThreeJobCode + ", " : "") +
                (priceCost != null ? "priceCost=" + priceCost + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (scientificWorkGroupId != null ? "scientificWorkGroupId=" + scientificWorkGroupId + ", " : "") +
            "}";
    }

}
