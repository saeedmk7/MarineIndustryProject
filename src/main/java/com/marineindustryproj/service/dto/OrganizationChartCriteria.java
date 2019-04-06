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
 * Criteria class for the OrganizationChart entity. This class is used in OrganizationChartResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /organization-charts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrganizationChartCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter personId;

    private LongFilter organizationChartId;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter organizationChartAuthorityId;

    private LongFilter niazsanjiFardiId;

    private LongFilter requestNiazsanjiFardiId;

    private LongFilter educationalHistoryId;

    private LongFilter parentId;

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

    public LongFilter getOrganizationChartAuthorityId() {
        return organizationChartAuthorityId;
    }

    public void setOrganizationChartAuthorityId(LongFilter organizationChartAuthorityId) {
        this.organizationChartAuthorityId = organizationChartAuthorityId;
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

    public LongFilter getEducationalHistoryId() {
        return educationalHistoryId;
    }

    public void setEducationalHistoryId(LongFilter educationalHistoryId) {
        this.educationalHistoryId = educationalHistoryId;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrganizationChartCriteria that = (OrganizationChartCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(organizationChartAuthorityId, that.organizationChartAuthorityId) &&
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(requestNiazsanjiFardiId, that.requestNiazsanjiFardiId) &&
            Objects.equals(educationalHistoryId, that.educationalHistoryId) &&
            Objects.equals(parentId, that.parentId);
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
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        personId,
        organizationChartId,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        finalNiazsanjiReportId,
        organizationChartAuthorityId,
        niazsanjiFardiId,
        requestNiazsanjiFardiId,
        educationalHistoryId,
        parentId
        );
    }

    @Override
    public String toString() {
        return "OrganizationChartCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (organizationChartAuthorityId != null ? "organizationChartAuthorityId=" + organizationChartAuthorityId + ", " : "") +
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (requestNiazsanjiFardiId != null ? "requestNiazsanjiFardiId=" + requestNiazsanjiFardiId + ", " : "") +
                (educationalHistoryId != null ? "educationalHistoryId=" + educationalHistoryId + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
            "}";
    }

}
