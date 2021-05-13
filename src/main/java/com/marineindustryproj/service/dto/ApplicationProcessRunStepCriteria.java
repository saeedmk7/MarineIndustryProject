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
 * Criteria class for the ApplicationProcessRunStep entity. This class is used in ApplicationProcessRunStepResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /application-process-run-steps?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApplicationProcessRunStepCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private BooleanFilter done;

    private StringFilter doneUserLogin;

    private ZonedDateTimeFilter doneDate;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter applicationProcessId;

    private LongFilter applicationProcessStepId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public BooleanFilter getDone() {
        return done;
    }

    public void setDone(BooleanFilter done) {
        this.done = done;
    }

    public StringFilter getDoneUserLogin() {
        return doneUserLogin;
    }

    public void setDoneUserLogin(StringFilter doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
    }

    public ZonedDateTimeFilter getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(ZonedDateTimeFilter doneDate) {
        this.doneDate = doneDate;
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

    public LongFilter getApplicationProcessId() {
        return applicationProcessId;
    }

    public void setApplicationProcessId(LongFilter applicationProcessId) {
        this.applicationProcessId = applicationProcessId;
    }

    public LongFilter getApplicationProcessStepId() {
        return applicationProcessStepId;
    }

    public void setApplicationProcessStepId(LongFilter applicationProcessStepId) {
        this.applicationProcessStepId = applicationProcessStepId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ApplicationProcessRunStepCriteria that = (ApplicationProcessRunStepCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(done, that.done) &&
            Objects.equals(doneUserLogin, that.doneUserLogin) &&
            Objects.equals(doneDate, that.doneDate) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(applicationProcessId, that.applicationProcessId) &&
            Objects.equals(applicationProcessStepId, that.applicationProcessStepId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        description,
        done,
        doneUserLogin,
        doneDate,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        applicationProcessId,
        applicationProcessStepId
        );
    }

    @Override
    public String toString() {
        return "ApplicationProcessRunStepCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (done != null ? "done=" + done + ", " : "") +
                (doneUserLogin != null ? "doneUserLogin=" + doneUserLogin + ", " : "") +
                (doneDate != null ? "doneDate=" + doneDate + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (applicationProcessId != null ? "applicationProcessId=" + applicationProcessId + ", " : "") +
                (applicationProcessStepId != null ? "applicationProcessStepId=" + applicationProcessStepId + ", " : "") +
            "}";
    }

}
