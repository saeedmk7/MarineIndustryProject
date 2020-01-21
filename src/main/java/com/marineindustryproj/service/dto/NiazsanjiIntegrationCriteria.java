package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the NiazsanjiIntegration entity. This class is used in NiazsanjiIntegrationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /niazsanji-integrations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NiazsanjiIntegrationCriteria implements Serializable {
    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }
    /**
     * Class for filtering RequestNiazsanjiType
     */
    public static class RequestNiazsanjiTypeFilter extends Filter<RequestNiazsanjiType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter niazsanjiYear;

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

    private RequestNiazsanjiTypeFilter requestNiazsanjiType;

    private IntegerFilter priority;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter prioritizeRequestNiazsanjiId;

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

    public RequestNiazsanjiTypeFilter getRequestNiazsanjiType() {
        return requestNiazsanjiType;
    }

    public void setRequestNiazsanjiType(RequestNiazsanjiTypeFilter requestNiazsanjiType) {
        this.requestNiazsanjiType = requestNiazsanjiType;
    }

    public IntegerFilter getPriority() {
        return priority;
    }

    public void setPriority(IntegerFilter priority) {
        this.priority = priority;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public LongFilter getPrioritizeRequestNiazsanjiId() {
        return prioritizeRequestNiazsanjiId;
    }

    public void setPrioritizeRequestNiazsanjiId(LongFilter prioritizeRequestNiazsanjiId) {
        this.prioritizeRequestNiazsanjiId = prioritizeRequestNiazsanjiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NiazsanjiIntegrationCriteria that = (NiazsanjiIntegrationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
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
            Objects.equals(requestNiazsanjiType, that.requestNiazsanjiType) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        niazsanjiYear,
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
        requestNiazsanjiType,
        priority,
        finalNiazsanjiReportId,
        prioritizeRequestNiazsanjiId
        );
    }

    @Override
    public String toString() {
        return "NiazsanjiIntegrationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
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
                (requestNiazsanjiType != null ? "requestNiazsanjiType=" + requestNiazsanjiType + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
            "}";
    }

}
