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
 * Criteria class for the RequestNiazsanjiFardi entity. This class is used in RequestNiazsanjiFardiResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /request-niazsanji-fardis?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RequestNiazsanjiFardiCriteria implements Serializable {

    public LongFilter getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(LongFilter skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
    }

    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LongFilter costApprovedEducationalModule;

    private LongFilter costAllEducationalModule;

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

    private LongFilter niazsanjiFardiId;

    private LongFilter prioritizeRequestNiazsanjiId;

    private LongFilter documentId;

    private LongFilter courseTypeId;

    private LongFilter approvedEducationalModuleId;

    private LongFilter allEducationalModuleId;

    private StringFilter educationalModuleTitle;

    private LongFilter skillableLevelOfSkillId;

    private LongFilter personId;

    private LongFilter organizationChartId;

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

    public LongFilter getCostApprovedEducationalModule() {
        return costApprovedEducationalModule;
    }

    public void setCostApprovedEducationalModule(LongFilter costApprovedEducationalModule) {
        this.costApprovedEducationalModule = costApprovedEducationalModule;
    }

    public LongFilter getCostAllEducationalModule() {
        return costAllEducationalModule;
    }

    public void setCostAllEducationalModule(LongFilter costAllEducationalModule) {
        this.costAllEducationalModule = costAllEducationalModule;
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

    public LongFilter getNiazsanjiFardiId() {
        return niazsanjiFardiId;
    }

    public void setNiazsanjiFardiId(LongFilter niazsanjiFardiId) {
        this.niazsanjiFardiId = niazsanjiFardiId;
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

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public LongFilter getApprovedEducationalModuleId() {
        return approvedEducationalModuleId;
    }

    public void setApprovedEducationalModuleId(LongFilter approvedEducationalModuleId) {
        this.approvedEducationalModuleId = approvedEducationalModuleId;
    }

    public LongFilter getAllEducationalModuleId() {
        return allEducationalModuleId;
    }

    public void setAllEducationalModuleId(LongFilter allEducationalModuleId) {
        this.allEducationalModuleId = allEducationalModuleId;
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
        final RequestNiazsanjiFardiCriteria that = (RequestNiazsanjiFardiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(costApprovedEducationalModule, that.costApprovedEducationalModule) &&
            Objects.equals(costAllEducationalModule, that.costAllEducationalModule) &&
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
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(approvedEducationalModuleId, that.approvedEducationalModuleId) &&
            Objects.equals(allEducationalModuleId, that.allEducationalModuleId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(skillableLevelOfSkillId, that.skillableLevelOfSkillId) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        costApprovedEducationalModule,
        costAllEducationalModule,
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
        niazsanjiFardiId,
        prioritizeRequestNiazsanjiId,
        documentId,
        courseTypeId,
        approvedEducationalModuleId,
        allEducationalModuleId,
        educationalModuleTitle,
        personId,
        skillableLevelOfSkillId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "RequestNiazsanjiFardiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (costApprovedEducationalModule != null ? "costApprovedEducationalModule=" + costApprovedEducationalModule + ", " : "") +
                (costAllEducationalModule != null ? "costAllEducationalModule=" + costAllEducationalModule + ", " : "") +
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
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (approvedEducationalModuleId != null ? "approvedEducationalModuleId=" + approvedEducationalModuleId + ", " : "") +
                (allEducationalModuleId != null ? "allEducationalModuleId=" + allEducationalModuleId + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (skillableLevelOfSkillId != null ? "skillableLevelOfSkillId=" + skillableLevelOfSkillId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

}
