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
 * Criteria class for the Job entity. This class is used in JobResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /jobs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class JobCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter jobKey;

    private StringFilter jobCode;

    private StringFilter first3JobCode;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter jobPersonId;

    private LongFilter practicaljobPersonId;

    private LongFilter jobId;

    private LongFilter educationalModuleJobId;

    private LongFilter documentId;

    private LongFilter rasteId;

    private LongFilter radehId;

    private LongFilter jobTypeId;

    private LongFilter scientificWorkGroupId;

    private LongFilter parentId;

    private LongFilter mainTaskId;

    private LongFilter niazsanjiGroupId;

    public JobCriteria() {
    }

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

    public StringFilter getJobKey() {
        return jobKey;
    }

    public void setJobKey(StringFilter jobKey) {
        this.jobKey = jobKey;
    }

    public StringFilter getJobCode() {
        return jobCode;
    }

    public void setJobCode(StringFilter jobCode) {
        this.jobCode = jobCode;
    }

    public StringFilter getFirst3JobCode() {
        return first3JobCode;
    }

    public void setFirst3JobCode(StringFilter first3JobCode) {
        this.first3JobCode = first3JobCode;
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

    public LongFilter getJobPersonId() {
        return jobPersonId;
    }

    public void setJobPersonId(LongFilter jobPersonId) {
        this.jobPersonId = jobPersonId;
    }

    public LongFilter getPracticaljobPersonId() {
        return practicaljobPersonId;
    }

    public void setPracticaljobPersonId(LongFilter practicaljobPersonId) {
        this.practicaljobPersonId = practicaljobPersonId;
    }

    public LongFilter getJobId() {
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getEducationalModuleJobId() {
        return educationalModuleJobId;
    }

    public void setEducationalModuleJobId(LongFilter educationalModuleJobId) {
        this.educationalModuleJobId = educationalModuleJobId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getRasteId() {
        return rasteId;
    }

    public void setRasteId(LongFilter rasteId) {
        this.rasteId = rasteId;
    }

    public LongFilter getRadehId() {
        return radehId;
    }

    public void setRadehId(LongFilter radehId) {
        this.radehId = radehId;
    }

    public LongFilter getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(LongFilter jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public LongFilter getScientificWorkGroupId() {
        return scientificWorkGroupId;
    }

    public void setScientificWorkGroupId(LongFilter scientificWorkGroupId) {
        this.scientificWorkGroupId = scientificWorkGroupId;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }

    public LongFilter getMainTaskId() {
        return mainTaskId;
    }

    public void setMainTaskId(LongFilter mainTaskId) {
        this.mainTaskId = mainTaskId;
    }

    public LongFilter getNiazsanjiGroupId() {
        return niazsanjiGroupId;
    }

    public void setNiazsanjiGroupId(LongFilter niazsanjiGroupId) {
        this.niazsanjiGroupId = niazsanjiGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JobCriteria that = (JobCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(jobKey, that.jobKey) &&
            Objects.equals(jobCode, that.jobCode) &&
            Objects.equals(first3JobCode, that.first3JobCode) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(jobPersonId, that.jobPersonId) &&
            Objects.equals(practicaljobPersonId, that.practicaljobPersonId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(educationalModuleJobId, that.educationalModuleJobId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(rasteId, that.rasteId) &&
            Objects.equals(radehId, that.radehId) &&
            Objects.equals(jobTypeId, that.jobTypeId) &&
            Objects.equals(scientificWorkGroupId, that.scientificWorkGroupId) &&
            Objects.equals(parentId, that.parentId) &&
            Objects.equals(mainTaskId, that.mainTaskId) &&
            Objects.equals(niazsanjiGroupId, that.niazsanjiGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        jobKey,
        jobCode,
        first3JobCode,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        jobPersonId,
        practicaljobPersonId,
        jobId,
        educationalModuleJobId,
        documentId,
        rasteId,
        radehId,
        jobTypeId,
        scientificWorkGroupId,
        parentId,
        mainTaskId,
        niazsanjiGroupId
        );
    }

    @Override
    public String toString() {
        return "JobCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (jobKey != null ? "jobKey=" + jobKey + ", " : "") +
                (jobCode != null ? "jobCode=" + jobCode + ", " : "") +
                (first3JobCode != null ? "first3JobCode=" + first3JobCode + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (jobPersonId != null ? "jobPersonId=" + jobPersonId + ", " : "") +
                (practicaljobPersonId != null ? "practicaljobPersonId=" + practicaljobPersonId + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (educationalModuleJobId != null ? "educationalModuleJobId=" + educationalModuleJobId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (rasteId != null ? "rasteId=" + rasteId + ", " : "") +
                (radehId != null ? "radehId=" + radehId + ", " : "") +
                (jobTypeId != null ? "jobTypeId=" + jobTypeId + ", " : "") +
                (scientificWorkGroupId != null ? "scientificWorkGroupId=" + scientificWorkGroupId + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
                (mainTaskId != null ? "mainTaskId=" + mainTaskId + ", " : "") +
                (niazsanjiGroupId != null ? "niazsanjiGroupId=" + niazsanjiGroupId + ", " : "") +
            "}";
    }

}
