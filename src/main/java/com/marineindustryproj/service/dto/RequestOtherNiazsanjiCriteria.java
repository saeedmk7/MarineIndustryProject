package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the RequestOtherNiazsanji entity. This class is used in RequestOtherNiazsanjiResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /request-other-niazsanjis?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RequestOtherNiazsanjiCriteria implements Serializable {
    public StringFilter getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(StringFilter educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public StringFilter getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(StringFilter educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }

    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LongFilter costEducationalModule;

    private StringFilter restrictionDescription;

    private StringFilter goalsText;

    private StringFilter prerequisite;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private RequestStatusFilter requestStatus;

    private StringFilter changeStatusUserLogin;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private LongFilter niazsanjiOtherId;

    private LongFilter prioritizeRequestNiazsanjiId;

    private LongFilter documentId;

    private LongFilter restrictionId;

    private LongFilter niazsanjiInputId;

    private LongFilter courseTypeId;

    private LongFilter educationalModuleId;

    private StringFilter educationalModuleTitle;

    private StringFilter educationalModuleCode;

    private LongFilter personId;

    private LongFilter organizationChartId;

    private LongFilter teachingApproachId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public LongFilter getCostEducationalModule() {
        return costEducationalModule;
    }

    public void setCostEducationalModule(LongFilter costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
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

    public RequestStatusFilter getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusFilter requestStatus) {
        this.requestStatus = requestStatus;
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

    public LongFilter getNiazsanjiOtherId() {
        return niazsanjiOtherId;
    }

    public void setNiazsanjiOtherId(LongFilter niazsanjiOtherId) {
        this.niazsanjiOtherId = niazsanjiOtherId;
    }

    public LongFilter getPrioritizeRequestNiazsanjiId() {
        return prioritizeRequestNiazsanjiId;
    }

    public void setPrioritizeRequestNiazsanjiId(LongFilter prioritizeRequestNiazsanjiId) {
        this.prioritizeRequestNiazsanjiId = prioritizeRequestNiazsanjiId;
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
        final RequestOtherNiazsanjiCriteria that = (RequestOtherNiazsanjiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(costEducationalModule, that.costEducationalModule) &&
            Objects.equals(restrictionDescription, that.restrictionDescription) &&
            Objects.equals(goalsText, that.goalsText) &&
            Objects.equals(prerequisite, that.prerequisite) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(requestStatus, that.requestStatus) &&
            Objects.equals(changeStatusUserLogin, that.changeStatusUserLogin) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(niazsanjiOtherId, that.niazsanjiOtherId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(restrictionId, that.restrictionId) &&
            Objects.equals(niazsanjiInputId, that.niazsanjiInputId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(educationalModuleCode, that.educationalModuleCode) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(teachingApproachId, that.teachingApproachId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        costEducationalModule,
        restrictionDescription,
        goalsText,
        prerequisite,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        requestStatus,
        changeStatusUserLogin,
        guid,
        hasImportantMessage,
        niazsanjiOtherId,
        prioritizeRequestNiazsanjiId,
        documentId,
        restrictionId,
        niazsanjiInputId,
        courseTypeId,
        educationalModuleId,
        educationalModuleTitle,
        educationalModuleCode,
        personId,
        organizationChartId,
        teachingApproachId
        );
    }

    @Override
    public String toString() {
        return "RequestOtherNiazsanjiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (costEducationalModule != null ? "costEducationalModule=" + costEducationalModule + ", " : "") +
                (restrictionDescription != null ? "restrictionDescription=" + restrictionDescription + ", " : "") +
                (goalsText != null ? "goalsText=" + goalsText + ", " : "") +
                (prerequisite != null ? "prerequisite=" + prerequisite + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (requestStatus != null ? "requestStatus=" + requestStatus + ", " : "") +
                (changeStatusUserLogin != null ? "changeStatusUserLogin=" + changeStatusUserLogin + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (niazsanjiOtherId != null ? "niazsanjiOtherId=" + niazsanjiOtherId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (restrictionId != null ? "restrictionId=" + restrictionId + ", " : "") +
                (niazsanjiInputId != null ? "niazsanjiInputId=" + niazsanjiInputId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (educationalModuleCode != null ? "educationalModuleCode=" + educationalModuleCode + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
            "}";
    }

}
