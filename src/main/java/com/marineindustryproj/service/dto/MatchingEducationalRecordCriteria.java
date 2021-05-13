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
 * Criteria class for the MatchingEducationalRecord entity. This class is used in MatchingEducationalRecordResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /matching-educational-records?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MatchingEducationalRecordCriteria implements Serializable {
    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter chartStatus;

    private IntegerFilter bossStatus;

    private RequestStatusFilter requestStatus;

    private StringFilter changeStatusUserLogin;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private StringFilter selectedModuleIds;

    private LongFilter matchingRunRunningStepId;

    private LongFilter skillableLevelOfSkillId;

    private LongFilter documentId;

    private LongFilter personId;

    private LongFilter personEmploymentTypeId;

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

    public IntegerFilter getChartStatus() {
        return chartStatus;
    }

    public void setChartStatus(IntegerFilter chartStatus) {
        this.chartStatus = chartStatus;
    }

    public IntegerFilter getBossStatus() {
        return bossStatus;
    }

    public void setBossStatus(IntegerFilter bossStatus) {
        this.bossStatus = bossStatus;
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

    public StringFilter getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public void setSelectedModuleIds(StringFilter selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public LongFilter getMatchingRunRunningStepId() {
        return matchingRunRunningStepId;
    }

    public void setMatchingRunRunningStepId(LongFilter matchingRunRunningStepId) {
        this.matchingRunRunningStepId = matchingRunRunningStepId;
    }

    public LongFilter getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(LongFilter skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
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

    public LongFilter getPersonEmploymentTypeId() {
        return personEmploymentTypeId;
    }

    public void setPersonEmploymentTypeId(LongFilter personEmploymentTypeId) {
        this.personEmploymentTypeId = personEmploymentTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MatchingEducationalRecordCriteria that = (MatchingEducationalRecordCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(chartStatus, that.chartStatus) &&
            Objects.equals(bossStatus, that.bossStatus) &&
            Objects.equals(requestStatus, that.requestStatus) &&
            Objects.equals(changeStatusUserLogin, that.changeStatusUserLogin) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(selectedModuleIds, that.selectedModuleIds) &&
            Objects.equals(matchingRunRunningStepId, that.matchingRunRunningStepId) &&
            Objects.equals(skillableLevelOfSkillId, that.skillableLevelOfSkillId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(personEmploymentTypeId, that.personEmploymentTypeId) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        chartStatus,
        bossStatus,
        requestStatus,
        changeStatusUserLogin,
        guid,
        hasImportantMessage,
        selectedModuleIds,
        matchingRunRunningStepId,
        skillableLevelOfSkillId,
        documentId,
        personId,
        personEmploymentTypeId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "MatchingEducationalRecordCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (chartStatus != null ? "chartStatus=" + chartStatus + ", " : "") +
                (bossStatus != null ? "bossStatus=" + bossStatus + ", " : "") +
                (requestStatus != null ? "requestStatus=" + requestStatus + ", " : "") +
                (changeStatusUserLogin != null ? "changeStatusUserLogin=" + changeStatusUserLogin + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (selectedModuleIds != null ? "selectedModuleIds=" + selectedModuleIds + ", " : "") +
                (matchingRunRunningStepId != null ? "matchingRunRunningStepId=" + matchingRunRunningStepId + ", " : "") +
                (skillableLevelOfSkillId != null ? "skillableLevelOfSkillId=" + skillableLevelOfSkillId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (personEmploymentTypeId != null ? "personEmploymentTypeId=" + personEmploymentTypeId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

}
