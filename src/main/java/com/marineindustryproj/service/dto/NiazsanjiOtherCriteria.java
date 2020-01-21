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
 * Criteria class for the NiazsanjiOther entity. This class is used in NiazsanjiOtherResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /niazsanji-others?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NiazsanjiOtherCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter niazsanjiYear;

    private StringFilter code;

    private LongFilter priceCost;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private StringFilter changeStatusUserLogin;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private StringFilter restrictionDescription;

    private StringFilter goalsText;

    private StringFilter prerequisite;

    private LongFilter documentId;

    private LongFilter restrictionId;

    private LongFilter niazsanjiInputId;

    private LongFilter courseTypeId;

    private LongFilter requestOtherNiazsanjiId;

    private LongFilter educationalModuleId;

    private LongFilter personId;

    private LongFilter organizationChartId;

    private LongFilter teachingApproachId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(IntegerFilter niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public LongFilter getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(LongFilter priceCost) {
        this.priceCost = priceCost;
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

    public StringFilter getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(StringFilter changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public BooleanFilter getHasImportantMessage() {
        return hasImportantMessage;
    }

    public void setHasImportantMessage(BooleanFilter hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public StringFilter getRestrictionDescription() {
        return restrictionDescription;
    }

    public void setRestrictionDescription(StringFilter restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public StringFilter getGoalsText() {
        return goalsText;
    }

    public void setGoalsText(StringFilter goalsText) {
        this.goalsText = goalsText;
    }

    public StringFilter getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(StringFilter prerequisite) {
        this.prerequisite = prerequisite;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(LongFilter restrictionId) {
        this.restrictionId = restrictionId;
    }

    public LongFilter getNiazsanjiInputId() {
        return niazsanjiInputId;
    }

    public void setNiazsanjiInputId(LongFilter niazsanjiInputId) {
        this.niazsanjiInputId = niazsanjiInputId;
    }

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public LongFilter getRequestOtherNiazsanjiId() {
        return requestOtherNiazsanjiId;
    }

    public void setRequestOtherNiazsanjiId(LongFilter requestOtherNiazsanjiId) {
        this.requestOtherNiazsanjiId = requestOtherNiazsanjiId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
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

    public LongFilter getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(LongFilter teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NiazsanjiOtherCriteria that = (NiazsanjiOtherCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
            Objects.equals(code, that.code) &&
            Objects.equals(priceCost, that.priceCost) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(changeStatusUserLogin, that.changeStatusUserLogin) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(restrictionDescription, that.restrictionDescription) &&
            Objects.equals(goalsText, that.goalsText) &&
            Objects.equals(prerequisite, that.prerequisite) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(restrictionId, that.restrictionId) &&
            Objects.equals(niazsanjiInputId, that.niazsanjiInputId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(requestOtherNiazsanjiId, that.requestOtherNiazsanjiId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(teachingApproachId, that.teachingApproachId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        niazsanjiYear,
        code,
        priceCost,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        changeStatusUserLogin,
        guid,
        hasImportantMessage,
        restrictionDescription,
        goalsText,
        prerequisite,
        documentId,
        restrictionId,
        niazsanjiInputId,
        courseTypeId,
        requestOtherNiazsanjiId,
        educationalModuleId,
        personId,
        organizationChartId,
        teachingApproachId
        );
    }

    @Override
    public String toString() {
        return "NiazsanjiOtherCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (priceCost != null ? "priceCost=" + priceCost + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (changeStatusUserLogin != null ? "changeStatusUserLogin=" + changeStatusUserLogin + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (restrictionDescription != null ? "restrictionDescription=" + restrictionDescription + ", " : "") +
                (goalsText != null ? "goalsText=" + goalsText + ", " : "") +
                (prerequisite != null ? "prerequisite=" + prerequisite + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (restrictionId != null ? "restrictionId=" + restrictionId + ", " : "") +
                (niazsanjiInputId != null ? "niazsanjiInputId=" + niazsanjiInputId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (requestOtherNiazsanjiId != null ? "requestOtherNiazsanjiId=" + requestOtherNiazsanjiId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
            "}";
    }

}
