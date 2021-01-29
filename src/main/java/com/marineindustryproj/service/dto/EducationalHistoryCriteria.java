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
 * Criteria class for the EducationalHistory entity. This class is used in EducationalHistoryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /educational-histories?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationalHistoryCriteria implements Serializable {
    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter personName;

    private StringFilter personFamily;

    private StringFilter educationalModuleName;

    private StringFilter educationalModuleCode;

    private StringFilter educationalModuleTitle;

    private IntegerFilter learningTimeTheorical;

    private IntegerFilter learningTimePractical;

    private IntegerFilter totalTime;

    private StringFilter educationalCenter;

    private StringFilter dateOfStart;

    private StringFilter dateOfEnd;

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

    private LongFilter courseTypeId;

    private LongFilter personId;

    private LongFilter educationalModuleId;

    private LongFilter organizationChartId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEducationalModuleName() {
        return educationalModuleName;
    }

    public void setEducationalModuleName(StringFilter educationalModuleName) {
        this.educationalModuleName = educationalModuleName;
    }

    public StringFilter getPersonName() {
        return personName;
    }

    public void setPersonName(StringFilter personName) {
        this.personName = personName;
    }

    public StringFilter getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(StringFilter personFamily) {
        this.personFamily = personFamily;
    }

    public IntegerFilter getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public void setLearningTimeTheorical(IntegerFilter learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public IntegerFilter getLearningTimePractical() {
        return learningTimePractical;
    }

    public void setLearningTimePractical(IntegerFilter learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    public IntegerFilter getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(IntegerFilter totalTime) {
        this.totalTime = totalTime;
    }

    public StringFilter getEducationalCenter() {
        return educationalCenter;
    }

    public void setEducationalCenter(StringFilter educationalCenter) {
        this.educationalCenter = educationalCenter;
    }

    public StringFilter getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(StringFilter dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public StringFilter getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(StringFilter dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
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

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
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

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }
    public StringFilter getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(StringFilter educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
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
        final EducationalHistoryCriteria that = (EducationalHistoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(educationalModuleName, that.educationalModuleName) &&
            Objects.equals(personName, that.personName) &&
            Objects.equals(personFamily, that.personFamily) &&
            Objects.equals(educationalModuleCode, that.educationalModuleCode) &&
            Objects.equals(learningTimeTheorical, that.learningTimeTheorical) &&
            Objects.equals(learningTimePractical, that.learningTimePractical) &&
            Objects.equals(totalTime, that.totalTime) &&
            Objects.equals(educationalCenter, that.educationalCenter) &&
            Objects.equals(dateOfStart, that.dateOfStart) &&
            Objects.equals(dateOfEnd, that.dateOfEnd) &&
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
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        educationalModuleName,
        personName,
        personFamily,
        educationalModuleCode,
        learningTimeTheorical,
        learningTimePractical,
        totalTime,
        educationalCenter,
        dateOfStart,
        dateOfEnd,
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
        courseTypeId,
        personId,
        educationalModuleId,
        educationalModuleTitle,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "EducationalHistoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (educationalModuleName != null ? "educationalModuleName=" + educationalModuleName + ", " : "") +
                (personName != null ? "personName=" + personName + ", " : "") +
                (personFamily != null ? "personFamily=" + personFamily + ", " : "") +
                (educationalModuleCode != null ? "educationalModuleCode=" + educationalModuleCode + ", " : "") +
                (learningTimeTheorical != null ? "learningTimeTheorical=" + learningTimeTheorical + ", " : "") +
                (learningTimePractical != null ? "learningTimePractical=" + learningTimePractical + ", " : "") +
                (totalTime != null ? "totalTime=" + totalTime + ", " : "") +
                (educationalCenter != null ? "educationalCenter=" + educationalCenter + ", " : "") +
                (dateOfStart != null ? "dateOfStart=" + dateOfStart + ", " : "") +
                (dateOfEnd != null ? "dateOfEnd=" + dateOfEnd + ", " : "") +
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
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

}
