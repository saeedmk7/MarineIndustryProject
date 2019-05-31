package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the RunPhase entity. This class is used in RunPhaseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /run-phases?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RunPhaseCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private IntegerFilter finalizeCost;

    private IntegerFilter stepNumber;

    private BooleanFilter done;

    private StringFilter doneUserLogin;

    private ZonedDateTimeFilter doneDate;

    private IntegerFilter runMonth;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter runRunningStepId;

    private LongFilter documentId;

    private LongFilter personId;

    private LongFilter organizationChartId;

    private LongFilter educationalModuleId;

    private StringFilter educationalModuleTitle;

    private LongFilter finalNiazsanjiReportId;

    private IntegerFilter niazsanjiYear;

    private FinalNiazsanjiReportCriteria.NiazSanjiSourceFilter niazSanjiSource;

    private LongFilter courseTypeId;

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

    public IntegerFilter getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(IntegerFilter finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public IntegerFilter getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(IntegerFilter stepNumber) {
        this.stepNumber = stepNumber;
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

    public IntegerFilter getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(IntegerFilter runMonth) {
        this.runMonth = runMonth;
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

    public LongFilter getRunRunningStepId() {
        return runRunningStepId;
    }

    public void setRunRunningStepId(LongFilter runRunningStepId) {
        this.runRunningStepId = runRunningStepId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public IntegerFilter getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(IntegerFilter niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }
    public FinalNiazsanjiReportCriteria.NiazSanjiSourceFilter getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public void setNiazSanjiSource(FinalNiazsanjiReportCriteria.NiazSanjiSourceFilter niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }
    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
    public StringFilter getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(StringFilter educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RunPhaseCriteria that = (RunPhaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(finalizeCost, that.finalizeCost) &&
            Objects.equals(stepNumber, that.stepNumber) &&
            Objects.equals(done, that.done) &&
            Objects.equals(doneUserLogin, that.doneUserLogin) &&
            Objects.equals(doneDate, that.doneDate) &&
            Objects.equals(runMonth, that.runMonth) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(runRunningStepId, that.runRunningStepId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
            Objects.equals(niazSanjiSource, that.niazSanjiSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        description,
        finalizeCost,
        stepNumber,
        done,
        doneUserLogin,
        doneDate,
        runMonth,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        runRunningStepId,
        documentId,
        personId,
        courseTypeId,
        organizationChartId,
        educationalModuleId,
        educationalModuleTitle,
        finalNiazsanjiReportId,
        niazsanjiYear,
        niazSanjiSource
        );
    }

    @Override
    public String toString() {
        return "RunPhaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (finalizeCost != null ? "finalizeCost=" + finalizeCost + ", " : "") +
                (stepNumber != null ? "stepNumber=" + stepNumber + ", " : "") +
                (done != null ? "done=" + done + ", " : "") +
                (doneUserLogin != null ? "doneUserLogin=" + doneUserLogin + ", " : "") +
                (doneDate != null ? "doneDate=" + doneDate + ", " : "") +
                (runMonth != null ? "runMonth=" + runMonth + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (runRunningStepId != null ? "runRunningStepId=" + runRunningStepId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
                (niazSanjiSource != null ? "niazSanjiSource=" + niazSanjiSource + ", " : "") +
            "}";
    }



}
