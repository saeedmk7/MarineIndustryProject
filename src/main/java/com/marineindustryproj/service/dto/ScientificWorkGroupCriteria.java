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
 * Criteria class for the ScientificWorkGroup entity. This class is used in ScientificWorkGroupResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /scientific-work-groups?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ScientificWorkGroupCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter jobId;

    private LongFilter niazsanjiGroupId;

    private LongFilter personId;

    private LongFilter educationalModuleId;

    private LongFilter requestEducationalModuleId;

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

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public LongFilter getJobId() {
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getNiazsanjiGroupId() {
        return niazsanjiGroupId;
    }

    public void setNiazsanjiGroupId(LongFilter niazsanjiGroupId) {
        this.niazsanjiGroupId = niazsanjiGroupId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ScientificWorkGroupCriteria that = (ScientificWorkGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(niazsanjiGroupId, that.niazsanjiGroupId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        code,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        jobId,
        niazsanjiGroupId,
        personId,
        educationalModuleId,
        requestEducationalModuleId
        );
    }

    @Override
    public String toString() {
        return "ScientificWorkGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (niazsanjiGroupId != null ? "niazsanjiGroupId=" + niazsanjiGroupId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
            "}";
    }

}
