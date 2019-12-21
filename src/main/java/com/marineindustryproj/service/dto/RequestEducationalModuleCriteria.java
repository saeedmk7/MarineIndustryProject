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
 * Criteria class for the RequestEducationalModule entity. This class is used in RequestEducationalModuleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /request-educational-modules?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RequestEducationalModuleCriteria implements Serializable {
    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter title;

    private IntegerFilter learningTimeTheorical;

    private IntegerFilter learningTimePractical;

    private StringFilter version;

    private StringFilter innerCode;

    private StringFilter centralizedCode;

    private StringFilter moreDescription;

    private StringFilter recommendedBy;

    private StringFilter educationalModuleHeadlines;

    private StringFilter prerequisite;

    private StringFilter drafters;

    private IntegerFilter educationalModuleLevel;

    private IntegerFilter educationalModuleGroup;

    private IntegerFilter educationalModuleHour;

    private ZonedDateTimeFilter timePassed;

    private ZonedDateTimeFilter credit;

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

    private StringFilter goalsText;

    private StringFilter teachersText;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private StringFilter restrictionDescription;

    private LongFilter educationalModuleId;

    private LongFilter scientificWorkGroupId;

    private LongFilter documentId;

    private LongFilter educationalCenterId;

    private LongFilter goalId;

    private LongFilter resourceId;

    private LongFilter teacherId;

    private LongFilter securityLevelId;

    private LongFilter skillableLevelOfSkillId;

    private LongFilter evaluationMethodId;

    private LongFilter organizationId;

    private LongFilter restrictionId;

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

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
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

    public StringFilter getVersion() {
        return version;
    }

    public void setVersion(StringFilter version) {
        this.version = version;
    }

    public StringFilter getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(StringFilter innerCode) {
        this.innerCode = innerCode;
    }

    public StringFilter getCentralizedCode() {
        return centralizedCode;
    }

    public void setCentralizedCode(StringFilter centralizedCode) {
        this.centralizedCode = centralizedCode;
    }

    public StringFilter getMoreDescription() {
        return moreDescription;
    }

    public void setMoreDescription(StringFilter moreDescription) {
        this.moreDescription = moreDescription;
    }

    public StringFilter getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(StringFilter recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public StringFilter getEducationalModuleHeadlines() {
        return educationalModuleHeadlines;
    }

    public void setEducationalModuleHeadlines(StringFilter educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
    }

    public StringFilter getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(StringFilter prerequisite) {
        this.prerequisite = prerequisite;
    }

    public StringFilter getDrafters() {
        return drafters;
    }

    public void setDrafters(StringFilter drafters) {
        this.drafters = drafters;
    }

    public IntegerFilter getEducationalModuleLevel() {
        return educationalModuleLevel;
    }

    public void setEducationalModuleLevel(IntegerFilter educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
    }

    public IntegerFilter getEducationalModuleGroup() {
        return educationalModuleGroup;
    }

    public void setEducationalModuleGroup(IntegerFilter educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
    }

    public IntegerFilter getEducationalModuleHour() {
        return educationalModuleHour;
    }

    public void setEducationalModuleHour(IntegerFilter educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
    }

    public ZonedDateTimeFilter getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(ZonedDateTimeFilter timePassed) {
        this.timePassed = timePassed;
    }

    public ZonedDateTimeFilter getCredit() {
        return credit;
    }

    public void setCredit(ZonedDateTimeFilter credit) {
        this.credit = credit;
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

    public StringFilter getGoalsText() {
        return goalsText;
    }

    public void setGoalsText(StringFilter goalsText) {
        this.goalsText = goalsText;
    }

    public StringFilter getTeachersText() {
        return teachersText;
    }

    public void setTeachersText(StringFilter teachersText) {
        this.teachersText = teachersText;
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

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getScientificWorkGroupId() {
        return scientificWorkGroupId;
    }

    public void setScientificWorkGroupId(LongFilter scientificWorkGroupId) {
        this.scientificWorkGroupId = scientificWorkGroupId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getEducationalCenterId() {
        return educationalCenterId;
    }

    public void setEducationalCenterId(LongFilter educationalCenterId) {
        this.educationalCenterId = educationalCenterId;
    }

    public LongFilter getGoalId() {
        return goalId;
    }

    public void setGoalId(LongFilter goalId) {
        this.goalId = goalId;
    }

    public LongFilter getResourceId() {
        return resourceId;
    }

    public void setResourceId(LongFilter resourceId) {
        this.resourceId = resourceId;
    }

    public LongFilter getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(LongFilter teacherId) {
        this.teacherId = teacherId;
    }

    public LongFilter getSecurityLevelId() {
        return securityLevelId;
    }

    public void setSecurityLevelId(LongFilter securityLevelId) {
        this.securityLevelId = securityLevelId;
    }

    public LongFilter getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(LongFilter skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
    }

    public LongFilter getEvaluationMethodId() {
        return evaluationMethodId;
    }

    public void setEvaluationMethodId(LongFilter evaluationMethodId) {
        this.evaluationMethodId = evaluationMethodId;
    }

    public LongFilter getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(LongFilter organizationId) {
        this.organizationId = organizationId;
    }

    public LongFilter getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(LongFilter restrictionId) {
        this.restrictionId = restrictionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RequestEducationalModuleCriteria that = (RequestEducationalModuleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(title, that.title) &&
            Objects.equals(learningTimeTheorical, that.learningTimeTheorical) &&
            Objects.equals(learningTimePractical, that.learningTimePractical) &&
            Objects.equals(version, that.version) &&
            Objects.equals(innerCode, that.innerCode) &&
            Objects.equals(centralizedCode, that.centralizedCode) &&
            Objects.equals(moreDescription, that.moreDescription) &&
            Objects.equals(recommendedBy, that.recommendedBy) &&
            Objects.equals(educationalModuleHeadlines, that.educationalModuleHeadlines) &&
            Objects.equals(prerequisite, that.prerequisite) &&
            Objects.equals(drafters, that.drafters) &&
            Objects.equals(educationalModuleLevel, that.educationalModuleLevel) &&
            Objects.equals(educationalModuleGroup, that.educationalModuleGroup) &&
            Objects.equals(educationalModuleHour, that.educationalModuleHour) &&
            Objects.equals(timePassed, that.timePassed) &&
            Objects.equals(credit, that.credit) &&
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
            Objects.equals(goalsText, that.goalsText) &&
            Objects.equals(teachersText, that.teachersText) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(restrictionDescription, that.restrictionDescription) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(scientificWorkGroupId, that.scientificWorkGroupId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(educationalCenterId, that.educationalCenterId) &&
            Objects.equals(goalId, that.goalId) &&
            Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(securityLevelId, that.securityLevelId) &&
            Objects.equals(skillableLevelOfSkillId, that.skillableLevelOfSkillId) &&
            Objects.equals(evaluationMethodId, that.evaluationMethodId) &&
            Objects.equals(organizationId, that.organizationId) &&
            Objects.equals(restrictionId, that.restrictionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        title,
        learningTimeTheorical,
        learningTimePractical,
        version,
        innerCode,
        centralizedCode,
        moreDescription,
        recommendedBy,
        educationalModuleHeadlines,
        prerequisite,
        drafters,
        educationalModuleLevel,
        educationalModuleGroup,
        educationalModuleHour,
        timePassed,
        credit,
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
        goalsText,
        teachersText,
        guid,
        hasImportantMessage,
        restrictionDescription,
        educationalModuleId,
        scientificWorkGroupId,
        documentId,
        educationalCenterId,
        goalId,
        resourceId,
        teacherId,
        securityLevelId,
        skillableLevelOfSkillId,
        evaluationMethodId,
        organizationId,
        restrictionId
        );
    }

    @Override
    public String toString() {
        return "RequestEducationalModuleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (learningTimeTheorical != null ? "learningTimeTheorical=" + learningTimeTheorical + ", " : "") +
                (learningTimePractical != null ? "learningTimePractical=" + learningTimePractical + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (innerCode != null ? "innerCode=" + innerCode + ", " : "") +
                (centralizedCode != null ? "centralizedCode=" + centralizedCode + ", " : "") +
                (moreDescription != null ? "moreDescription=" + moreDescription + ", " : "") +
                (recommendedBy != null ? "recommendedBy=" + recommendedBy + ", " : "") +
                (educationalModuleHeadlines != null ? "educationalModuleHeadlines=" + educationalModuleHeadlines + ", " : "") +
                (prerequisite != null ? "prerequisite=" + prerequisite + ", " : "") +
                (drafters != null ? "drafters=" + drafters + ", " : "") +
                (educationalModuleLevel != null ? "educationalModuleLevel=" + educationalModuleLevel + ", " : "") +
                (educationalModuleGroup != null ? "educationalModuleGroup=" + educationalModuleGroup + ", " : "") +
                (educationalModuleHour != null ? "educationalModuleHour=" + educationalModuleHour + ", " : "") +
                (timePassed != null ? "timePassed=" + timePassed + ", " : "") +
                (credit != null ? "credit=" + credit + ", " : "") +
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
                (goalsText != null ? "goalsText=" + goalsText + ", " : "") +
                (teachersText != null ? "teachersText=" + teachersText + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (restrictionDescription != null ? "restrictionDescription=" + restrictionDescription + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (scientificWorkGroupId != null ? "scientificWorkGroupId=" + scientificWorkGroupId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (educationalCenterId != null ? "educationalCenterId=" + educationalCenterId + ", " : "") +
                (goalId != null ? "goalId=" + goalId + ", " : "") +
                (resourceId != null ? "resourceId=" + resourceId + ", " : "") +
                (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
                (securityLevelId != null ? "securityLevelId=" + securityLevelId + ", " : "") +
                (skillableLevelOfSkillId != null ? "skillableLevelOfSkillId=" + skillableLevelOfSkillId + ", " : "") +
                (evaluationMethodId != null ? "evaluationMethodId=" + evaluationMethodId + ", " : "") +
                (organizationId != null ? "organizationId=" + organizationId + ", " : "") +
                (restrictionId != null ? "restrictionId=" + restrictionId + ", " : "") +
            "}";
    }

}
