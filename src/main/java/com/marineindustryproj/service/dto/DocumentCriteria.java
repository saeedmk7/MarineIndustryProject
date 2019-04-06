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
 * Criteria class for the Document entity. This class is used in DocumentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /documents?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DocumentCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private LongFilter personId;

    private LongFilter teacherId;

    private LongFilter jobId;

    private LongFilter educationalModuleId;

    private LongFilter requestEducationalModuleId;

    private LongFilter educationalCenterId;

    private LongFilter resourceId;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter designAndPlanningId;

    private LongFilter runPhaseId;

    private LongFilter announcementId;

    private LongFilter usersRequestId;

    private LongFilter niazsanjiFardiId;

    private LongFilter requestNiazsanjiFardiId;

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

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(LongFilter teacherId) {
        this.teacherId = teacherId;
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

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }

    public LongFilter getEducationalCenterId() {
        return educationalCenterId;
    }

    public void setEducationalCenterId(LongFilter educationalCenterId) {
        this.educationalCenterId = educationalCenterId;
    }

    public LongFilter getResourceId() {
        return resourceId;
    }

    public void setResourceId(LongFilter resourceId) {
        this.resourceId = resourceId;
    }

    public LongFilter getRequestOrganizationNiazsanjiId() {
        return requestOrganizationNiazsanjiId;
    }

    public void setRequestOrganizationNiazsanjiId(LongFilter requestOrganizationNiazsanjiId) {
        this.requestOrganizationNiazsanjiId = requestOrganizationNiazsanjiId;
    }

    public LongFilter getFinalOrganizationNiazsanjiId() {
        return finalOrganizationNiazsanjiId;
    }

    public void setFinalOrganizationNiazsanjiId(LongFilter finalOrganizationNiazsanjiId) {
        this.finalOrganizationNiazsanjiId = finalOrganizationNiazsanjiId;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public LongFilter getDesignAndPlanningId() {
        return designAndPlanningId;
    }

    public void setDesignAndPlanningId(LongFilter designAndPlanningId) {
        this.designAndPlanningId = designAndPlanningId;
    }

    public LongFilter getRunPhaseId() {
        return runPhaseId;
    }

    public void setRunPhaseId(LongFilter runPhaseId) {
        this.runPhaseId = runPhaseId;
    }

    public LongFilter getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(LongFilter announcementId) {
        this.announcementId = announcementId;
    }

    public LongFilter getUsersRequestId() {
        return usersRequestId;
    }

    public void setUsersRequestId(LongFilter usersRequestId) {
        this.usersRequestId = usersRequestId;
    }

    public LongFilter getNiazsanjiFardiId() {
        return niazsanjiFardiId;
    }

    public void setNiazsanjiFardiId(LongFilter niazsanjiFardiId) {
        this.niazsanjiFardiId = niazsanjiFardiId;
    }

    public LongFilter getRequestNiazsanjiFardiId() {
        return requestNiazsanjiFardiId;
    }

    public void setRequestNiazsanjiFardiId(LongFilter requestNiazsanjiFardiId) {
        this.requestNiazsanjiFardiId = requestNiazsanjiFardiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DocumentCriteria that = (DocumentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId) &&
            Objects.equals(educationalCenterId, that.educationalCenterId) &&
            Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(designAndPlanningId, that.designAndPlanningId) &&
            Objects.equals(runPhaseId, that.runPhaseId) &&
            Objects.equals(announcementId, that.announcementId) &&
            Objects.equals(usersRequestId, that.usersRequestId) &&
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(requestNiazsanjiFardiId, that.requestNiazsanjiFardiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        createUserLogin,
        createDate,
        personId,
        teacherId,
        jobId,
        educationalModuleId,
        requestEducationalModuleId,
        educationalCenterId,
        resourceId,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        finalNiazsanjiReportId,
        designAndPlanningId,
        runPhaseId,
        announcementId,
        usersRequestId,
        niazsanjiFardiId,
        requestNiazsanjiFardiId
        );
    }

    @Override
    public String toString() {
        return "DocumentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
                (educationalCenterId != null ? "educationalCenterId=" + educationalCenterId + ", " : "") +
                (resourceId != null ? "resourceId=" + resourceId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (designAndPlanningId != null ? "designAndPlanningId=" + designAndPlanningId + ", " : "") +
                (runPhaseId != null ? "runPhaseId=" + runPhaseId + ", " : "") +
                (announcementId != null ? "announcementId=" + announcementId + ", " : "") +
                (usersRequestId != null ? "usersRequestId=" + usersRequestId + ", " : "") +
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (requestNiazsanjiFardiId != null ? "requestNiazsanjiFardiId=" + requestNiazsanjiFardiId + ", " : "") +
            "}";
    }

}
