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
 * Criteria class for the EducationalModule entity. This class is used in EducationalModuleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /educational-modules?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationalModuleCriteria implements Serializable {

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

    private StringFilter goalsText;

    private StringFilter teachersText;

    private StringFilter guid;

    private StringFilter restrictionDescription;

    private ZonedDateTimeFilter recommendDate;

    private StringFilter goalsBehavioralText;

    private StringFilter neededSoftwares;

    private StringFilter neededHardware;

    private StringFilter courseContactsTerms;

    private LongFilter headlineId;

    private LongFilter educationalModuleJobId;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter designAndPlanningId;

    private LongFilter runPhaseId;

    private LongFilter niazsanjiFardiId;

    private LongFilter approvedRequestNiazsanjiFardiId;

    private LongFilter allRequestNiazsanjiFardiId;

    private LongFilter educationalHistoryId;

    private LongFilter designNiazsanjiId;

    private LongFilter preJobNiazsanjiCompetencyId;

    private LongFilter jobNiazsanjiId;

    private LongFilter niazsanjiOtherId;

    private LongFilter requestOtherNiazsanjiId;

    private LongFilter prioritizeRequestNiazsanjiId;

    private LongFilter scientificWorkGroupId;

    private LongFilter documentId;

    private LongFilter educationalCenterId;

    private LongFilter goalId;

    private LongFilter resourceId;

    private LongFilter teacherId;

    private LongFilter restrictionId;

    private LongFilter peopleUnderTrainingId;

    private LongFilter teachingApproachId;

    private LongFilter effectivenessLevelId;

    private LongFilter effectivenessIndexId;

    private LongFilter assessmentMethodId;

    private LongFilter requestEducationalModuleId;

    private LongFilter securityLevelId;

    private LongFilter skillableLevelOfSkillId;

    private LongFilter evaluationMethodId;

    private LongFilter organizationId;

    private LongFilter competencyId;

    private LongFilter niazsanjiGroupId;

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

    public StringFilter getRestrictionDescription() {
        return restrictionDescription;
    }

    public void setRestrictionDescription(StringFilter restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public ZonedDateTimeFilter getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(ZonedDateTimeFilter recommendDate) {
        this.recommendDate = recommendDate;
    }

    public StringFilter getGoalsBehavioralText() {
        return goalsBehavioralText;
    }

    public void setGoalsBehavioralText(StringFilter goalsBehavioralText) {
        this.goalsBehavioralText = goalsBehavioralText;
    }

    public StringFilter getNeededSoftwares() {
        return neededSoftwares;
    }

    public void setNeededSoftwares(StringFilter neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public StringFilter getNeededHardware() {
        return neededHardware;
    }

    public void setNeededHardware(StringFilter neededHardware) {
        this.neededHardware = neededHardware;
    }

    public StringFilter getCourseContactsTerms() {
        return courseContactsTerms;
    }

    public void setCourseContactsTerms(StringFilter courseContactsTerms) {
        this.courseContactsTerms = courseContactsTerms;
    }

    public LongFilter getHeadlineId() {
        return headlineId;
    }

    public void setHeadlineId(LongFilter headlineId) {
        this.headlineId = headlineId;
    }

    public LongFilter getEducationalModuleJobId() {
        return educationalModuleJobId;
    }

    public void setEducationalModuleJobId(LongFilter educationalModuleJobId) {
        this.educationalModuleJobId = educationalModuleJobId;
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

    public LongFilter getNiazsanjiFardiId() {
        return niazsanjiFardiId;
    }

    public void setNiazsanjiFardiId(LongFilter niazsanjiFardiId) {
        this.niazsanjiFardiId = niazsanjiFardiId;
    }

    public LongFilter getApprovedRequestNiazsanjiFardiId() {
        return approvedRequestNiazsanjiFardiId;
    }

    public void setApprovedRequestNiazsanjiFardiId(LongFilter approvedRequestNiazsanjiFardiId) {
        this.approvedRequestNiazsanjiFardiId = approvedRequestNiazsanjiFardiId;
    }

    public LongFilter getAllRequestNiazsanjiFardiId() {
        return allRequestNiazsanjiFardiId;
    }

    public void setAllRequestNiazsanjiFardiId(LongFilter allRequestNiazsanjiFardiId) {
        this.allRequestNiazsanjiFardiId = allRequestNiazsanjiFardiId;
    }

    public LongFilter getEducationalHistoryId() {
        return educationalHistoryId;
    }

    public void setEducationalHistoryId(LongFilter educationalHistoryId) {
        this.educationalHistoryId = educationalHistoryId;
    }

    public LongFilter getDesignNiazsanjiId() {
        return designNiazsanjiId;
    }

    public void setDesignNiazsanjiId(LongFilter designNiazsanjiId) {
        this.designNiazsanjiId = designNiazsanjiId;
    }

    public LongFilter getPreJobNiazsanjiCompetencyId() {
        return preJobNiazsanjiCompetencyId;
    }

    public void setPreJobNiazsanjiCompetencyId(LongFilter preJobNiazsanjiCompetencyId) {
        this.preJobNiazsanjiCompetencyId = preJobNiazsanjiCompetencyId;
    }

    public LongFilter getJobNiazsanjiId() {
        return jobNiazsanjiId;
    }

    public void setJobNiazsanjiId(LongFilter jobNiazsanjiId) {
        this.jobNiazsanjiId = jobNiazsanjiId;
    }

    public LongFilter getNiazsanjiOtherId() {
        return niazsanjiOtherId;
    }

    public void setNiazsanjiOtherId(LongFilter niazsanjiOtherId) {
        this.niazsanjiOtherId = niazsanjiOtherId;
    }

    public LongFilter getRequestOtherNiazsanjiId() {
        return requestOtherNiazsanjiId;
    }

    public void setRequestOtherNiazsanjiId(LongFilter requestOtherNiazsanjiId) {
        this.requestOtherNiazsanjiId = requestOtherNiazsanjiId;
    }

    public LongFilter getPrioritizeRequestNiazsanjiId() {
        return prioritizeRequestNiazsanjiId;
    }

    public void setPrioritizeRequestNiazsanjiId(LongFilter prioritizeRequestNiazsanjiId) {
        this.prioritizeRequestNiazsanjiId = prioritizeRequestNiazsanjiId;
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

    public LongFilter getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(LongFilter restrictionId) {
        this.restrictionId = restrictionId;
    }

    public LongFilter getPeopleUnderTrainingId() {
        return peopleUnderTrainingId;
    }

    public void setPeopleUnderTrainingId(LongFilter peopleUnderTrainingId) {
        this.peopleUnderTrainingId = peopleUnderTrainingId;
    }

    public LongFilter getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(LongFilter teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }

    public LongFilter getEffectivenessLevelId() {
        return effectivenessLevelId;
    }

    public void setEffectivenessLevelId(LongFilter effectivenessLevelId) {
        this.effectivenessLevelId = effectivenessLevelId;
    }

    public LongFilter getEffectivenessIndexId() {
        return effectivenessIndexId;
    }

    public void setEffectivenessIndexId(LongFilter effectivenessIndexId) {
        this.effectivenessIndexId = effectivenessIndexId;
    }

    public LongFilter getAssessmentMethodId() {
        return assessmentMethodId;
    }

    public void setAssessmentMethodId(LongFilter assessmentMethodId) {
        this.assessmentMethodId = assessmentMethodId;
    }

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
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

    public LongFilter getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(LongFilter competencyId) {
        this.competencyId = competencyId;
    }

    public LongFilter getNiazsanjiGroupId() {
        return niazsanjiGroupId;
    }

    public void setNiazsanjiGroupId(LongFilter niazsanjiGroupId) {
        this.niazsanjiGroupId = niazsanjiGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EducationalModuleCriteria that = (EducationalModuleCriteria) o;
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
            Objects.equals(goalsText, that.goalsText) &&
            Objects.equals(teachersText, that.teachersText) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(restrictionDescription, that.restrictionDescription) &&
            Objects.equals(recommendDate, that.recommendDate) &&
            Objects.equals(goalsBehavioralText, that.goalsBehavioralText) &&
            Objects.equals(neededSoftwares, that.neededSoftwares) &&
            Objects.equals(neededHardware, that.neededHardware) &&
            Objects.equals(courseContactsTerms, that.courseContactsTerms) &&
            Objects.equals(headlineId, that.headlineId) &&
            Objects.equals(educationalModuleJobId, that.educationalModuleJobId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(designAndPlanningId, that.designAndPlanningId) &&
            Objects.equals(runPhaseId, that.runPhaseId) &&
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(approvedRequestNiazsanjiFardiId, that.approvedRequestNiazsanjiFardiId) &&
            Objects.equals(allRequestNiazsanjiFardiId, that.allRequestNiazsanjiFardiId) &&
            Objects.equals(educationalHistoryId, that.educationalHistoryId) &&
            Objects.equals(designNiazsanjiId, that.designNiazsanjiId) &&
            Objects.equals(preJobNiazsanjiCompetencyId, that.preJobNiazsanjiCompetencyId) &&
            Objects.equals(jobNiazsanjiId, that.jobNiazsanjiId) &&
            Objects.equals(niazsanjiOtherId, that.niazsanjiOtherId) &&
            Objects.equals(requestOtherNiazsanjiId, that.requestOtherNiazsanjiId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId) &&
            Objects.equals(scientificWorkGroupId, that.scientificWorkGroupId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(educationalCenterId, that.educationalCenterId) &&
            Objects.equals(goalId, that.goalId) &&
            Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(restrictionId, that.restrictionId) &&
            Objects.equals(peopleUnderTrainingId, that.peopleUnderTrainingId) &&
            Objects.equals(teachingApproachId, that.teachingApproachId) &&
            Objects.equals(effectivenessLevelId, that.effectivenessLevelId) &&
            Objects.equals(effectivenessIndexId, that.effectivenessIndexId) &&
            Objects.equals(assessmentMethodId, that.assessmentMethodId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId) &&
            Objects.equals(securityLevelId, that.securityLevelId) &&
            Objects.equals(skillableLevelOfSkillId, that.skillableLevelOfSkillId) &&
            Objects.equals(evaluationMethodId, that.evaluationMethodId) &&
            Objects.equals(organizationId, that.organizationId) &&
            Objects.equals(competencyId, that.competencyId) &&
            Objects.equals(niazsanjiGroupId, that.niazsanjiGroupId);
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
        goalsText,
        teachersText,
        guid,
        restrictionDescription,
        recommendDate,
        goalsBehavioralText,
        neededSoftwares,
        neededHardware,
        courseContactsTerms,
        headlineId,
        educationalModuleJobId,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        finalNiazsanjiReportId,
        designAndPlanningId,
        runPhaseId,
        niazsanjiFardiId,
        approvedRequestNiazsanjiFardiId,
        allRequestNiazsanjiFardiId,
        educationalHistoryId,
        designNiazsanjiId,
        preJobNiazsanjiCompetencyId,
        jobNiazsanjiId,
        niazsanjiOtherId,
        requestOtherNiazsanjiId,
        prioritizeRequestNiazsanjiId,
        scientificWorkGroupId,
        documentId,
        educationalCenterId,
        goalId,
        resourceId,
        teacherId,
        restrictionId,
        peopleUnderTrainingId,
        teachingApproachId,
        effectivenessLevelId,
        effectivenessIndexId,
        assessmentMethodId,
        requestEducationalModuleId,
        securityLevelId,
        skillableLevelOfSkillId,
        evaluationMethodId,
        organizationId,
        competencyId,
        niazsanjiGroupId
        );
    }

    @Override
    public String toString() {
        return "EducationalModuleCriteria{" +
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
                (goalsText != null ? "goalsText=" + goalsText + ", " : "") +
                (teachersText != null ? "teachersText=" + teachersText + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (restrictionDescription != null ? "restrictionDescription=" + restrictionDescription + ", " : "") +
                (recommendDate != null ? "recommendDate=" + recommendDate + ", " : "") +
                (goalsBehavioralText != null ? "goalsBehavioralText=" + goalsBehavioralText + ", " : "") +
                (neededSoftwares != null ? "neededSoftwares=" + neededSoftwares + ", " : "") +
                (neededHardware != null ? "neededHardware=" + neededHardware + ", " : "") +
                (courseContactsTerms != null ? "courseContactsTerms=" + courseContactsTerms + ", " : "") +
                (headlineId != null ? "headlineId=" + headlineId + ", " : "") +
                (educationalModuleJobId != null ? "educationalModuleJobId=" + educationalModuleJobId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (designAndPlanningId != null ? "designAndPlanningId=" + designAndPlanningId + ", " : "") +
                (runPhaseId != null ? "runPhaseId=" + runPhaseId + ", " : "") +
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (approvedRequestNiazsanjiFardiId != null ? "approvedRequestNiazsanjiFardiId=" + approvedRequestNiazsanjiFardiId + ", " : "") +
                (allRequestNiazsanjiFardiId != null ? "allRequestNiazsanjiFardiId=" + allRequestNiazsanjiFardiId + ", " : "") +
                (educationalHistoryId != null ? "educationalHistoryId=" + educationalHistoryId + ", " : "") +
                (designNiazsanjiId != null ? "designNiazsanjiId=" + designNiazsanjiId + ", " : "") +
                (preJobNiazsanjiCompetencyId != null ? "preJobNiazsanjiCompetencyId=" + preJobNiazsanjiCompetencyId + ", " : "") +
                (jobNiazsanjiId != null ? "jobNiazsanjiId=" + jobNiazsanjiId + ", " : "") +
                (niazsanjiOtherId != null ? "niazsanjiOtherId=" + niazsanjiOtherId + ", " : "") +
                (requestOtherNiazsanjiId != null ? "requestOtherNiazsanjiId=" + requestOtherNiazsanjiId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
                (scientificWorkGroupId != null ? "scientificWorkGroupId=" + scientificWorkGroupId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (educationalCenterId != null ? "educationalCenterId=" + educationalCenterId + ", " : "") +
                (goalId != null ? "goalId=" + goalId + ", " : "") +
                (resourceId != null ? "resourceId=" + resourceId + ", " : "") +
                (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
                (restrictionId != null ? "restrictionId=" + restrictionId + ", " : "") +
                (peopleUnderTrainingId != null ? "peopleUnderTrainingId=" + peopleUnderTrainingId + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
                (effectivenessLevelId != null ? "effectivenessLevelId=" + effectivenessLevelId + ", " : "") +
                (effectivenessIndexId != null ? "effectivenessIndexId=" + effectivenessIndexId + ", " : "") +
                (assessmentMethodId != null ? "assessmentMethodId=" + assessmentMethodId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
                (securityLevelId != null ? "securityLevelId=" + securityLevelId + ", " : "") +
                (skillableLevelOfSkillId != null ? "skillableLevelOfSkillId=" + skillableLevelOfSkillId + ", " : "") +
                (evaluationMethodId != null ? "evaluationMethodId=" + evaluationMethodId + ", " : "") +
                (organizationId != null ? "organizationId=" + organizationId + ", " : "") +
                (competencyId != null ? "competencyId=" + competencyId + ", " : "") +
                (niazsanjiGroupId != null ? "niazsanjiGroupId=" + niazsanjiGroupId + ", " : "") +
            "}";
    }

}
