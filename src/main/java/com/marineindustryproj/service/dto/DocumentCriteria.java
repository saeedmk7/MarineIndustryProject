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

    private LongFilter teacherGradeId;

    private LongFilter jobId;

    private LongFilter educationalModuleId;

    private LongFilter requestEducationalModuleId;

    private LongFilter educationalCenterId;

    private LongFilter educationalCenterGradeId;

    private LongFilter resourceId;

    private LongFilter requestOrganizationNiazsanjiId;

    private LongFilter finalOrganizationNiazsanjiId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter finalNiazsanjiReportPersonId;

    private LongFilter niazsanjiPersonGradeId;

    private LongFilter levelThreeEffectivenessId;

    private LongFilter levelFourEffectivenessId;

    private LongFilter designAndPlanningId;

    private LongFilter runPhaseId;

    private LongFilter announcementId;

    private LongFilter usersRequestId;

    private LongFilter niazsanjiFardiId;

    private LongFilter requestNiazsanjiFardiId;

    private LongFilter instructionId;

    private LongFilter investToGroupTransactionId;

    private LongFilter mediaAwarenessReportId;

    private LongFilter preJobNiazsanjiId;

    private LongFilter jobNiazsanjiId;

    private LongFilter niazsanjiOtherId;

    private LongFilter requestOtherNiazsanjiId;

    private LongFilter prioritizeRequestNiazsanjiId;

    private LongFilter soldierId;

    private LongFilter soldierTrainingReportId;

    private LongFilter soldierMediaAwarenessReportId;

    private LongFilter evaluateCriteriaTrainingId;

    private LongFilter evaluateCriteriaDataId;

    private LongFilter effectivenessPhaseId;

    private LongFilter monitorLearningProcessId;

    private LongFilter matchingEducationalRecordId;

    private LongFilter applicationProcessId;

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

    public LongFilter getTeacherGradeId() {
        return teacherGradeId;
    }

    public void setTeacherGradeId(LongFilter teacherGradeId) {
        this.teacherGradeId = teacherGradeId;
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

    public LongFilter getEducationalCenterGradeId() {
        return educationalCenterGradeId;
    }

    public void setEducationalCenterGradeId(LongFilter educationalCenterGradeId) {
        this.educationalCenterGradeId = educationalCenterGradeId;
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

    public LongFilter getFinalNiazsanjiReportPersonId() {
        return finalNiazsanjiReportPersonId;
    }

    public void setFinalNiazsanjiReportPersonId(LongFilter finalNiazsanjiReportPersonId) {
        this.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
    }

    public LongFilter getNiazsanjiPersonGradeId() {
        return niazsanjiPersonGradeId;
    }

    public void setNiazsanjiPersonGradeId(LongFilter niazsanjiPersonGradeId) {
        this.niazsanjiPersonGradeId = niazsanjiPersonGradeId;
    }

    public LongFilter getLevelThreeEffectivenessId() {
        return levelThreeEffectivenessId;
    }

    public void setLevelThreeEffectivenessId(LongFilter levelThreeEffectivenessId) {
        this.levelThreeEffectivenessId = levelThreeEffectivenessId;
    }

    public LongFilter getLevelFourEffectivenessId() {
        return levelFourEffectivenessId;
    }

    public void setLevelFourEffectivenessId(LongFilter levelFourEffectivenessId) {
        this.levelFourEffectivenessId = levelFourEffectivenessId;
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

    public LongFilter getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(LongFilter instructionId) {
        this.instructionId = instructionId;
    }

    public LongFilter getInvestToGroupTransactionId() {
        return investToGroupTransactionId;
    }

    public void setInvestToGroupTransactionId(LongFilter investToGroupTransactionId) {
        this.investToGroupTransactionId = investToGroupTransactionId;
    }

    public LongFilter getMediaAwarenessReportId() {
        return mediaAwarenessReportId;
    }

    public void setMediaAwarenessReportId(LongFilter mediaAwarenessReportId) {
        this.mediaAwarenessReportId = mediaAwarenessReportId;
    }

    public LongFilter getPreJobNiazsanjiId() {
        return preJobNiazsanjiId;
    }

    public void setPreJobNiazsanjiId(LongFilter preJobNiazsanjiId) {
        this.preJobNiazsanjiId = preJobNiazsanjiId;
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

    public LongFilter getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(LongFilter soldierId) {
        this.soldierId = soldierId;
    }

    public LongFilter getSoldierTrainingReportId() {
        return soldierTrainingReportId;
    }

    public void setSoldierTrainingReportId(LongFilter soldierTrainingReportId) {
        this.soldierTrainingReportId = soldierTrainingReportId;
    }

    public LongFilter getSoldierMediaAwarenessReportId() {
        return soldierMediaAwarenessReportId;
    }

    public void setSoldierMediaAwarenessReportId(LongFilter soldierMediaAwarenessReportId) {
        this.soldierMediaAwarenessReportId = soldierMediaAwarenessReportId;
    }

    public LongFilter getEvaluateCriteriaTrainingId() {
        return evaluateCriteriaTrainingId;
    }

    public void setEvaluateCriteriaTrainingId(LongFilter evaluateCriteriaTrainingId) {
        this.evaluateCriteriaTrainingId = evaluateCriteriaTrainingId;
    }

    public LongFilter getEvaluateCriteriaDataId() {
        return evaluateCriteriaDataId;
    }

    public void setEvaluateCriteriaDataId(LongFilter evaluateCriteriaDataId) {
        this.evaluateCriteriaDataId = evaluateCriteriaDataId;
    }

    public LongFilter getEffectivenessPhaseId() {
        return effectivenessPhaseId;
    }

    public void setEffectivenessPhaseId(LongFilter effectivenessPhaseId) {
        this.effectivenessPhaseId = effectivenessPhaseId;
    }

    public LongFilter getMonitorLearningProcessId() {
        return monitorLearningProcessId;
    }

    public void setMonitorLearningProcessId(LongFilter monitorLearningProcessId) {
        this.monitorLearningProcessId = monitorLearningProcessId;
    }

    public LongFilter getMatchingEducationalRecordId() {
        return matchingEducationalRecordId;
    }

    public void setMatchingEducationalRecordId(LongFilter matchingEducationalRecordId) {
        this.matchingEducationalRecordId = matchingEducationalRecordId;
    }

    public LongFilter getApplicationProcessId() {
        return applicationProcessId;
    }

    public void setApplicationProcessId(LongFilter applicationProcessId) {
        this.applicationProcessId = applicationProcessId;
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
            Objects.equals(teacherGradeId, that.teacherGradeId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId) &&
            Objects.equals(educationalCenterId, that.educationalCenterId) &&
            Objects.equals(educationalCenterGradeId, that.educationalCenterGradeId) &&
            Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId) &&
            Objects.equals(finalOrganizationNiazsanjiId, that.finalOrganizationNiazsanjiId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(finalNiazsanjiReportPersonId, that.finalNiazsanjiReportPersonId) &&
            Objects.equals(niazsanjiPersonGradeId, that.niazsanjiPersonGradeId) &&
            Objects.equals(levelThreeEffectivenessId, that.levelThreeEffectivenessId) &&
            Objects.equals(levelFourEffectivenessId, that.levelFourEffectivenessId) &&
            Objects.equals(designAndPlanningId, that.designAndPlanningId) &&
            Objects.equals(runPhaseId, that.runPhaseId) &&
            Objects.equals(announcementId, that.announcementId) &&
            Objects.equals(usersRequestId, that.usersRequestId) &&
            Objects.equals(niazsanjiFardiId, that.niazsanjiFardiId) &&
            Objects.equals(requestNiazsanjiFardiId, that.requestNiazsanjiFardiId) &&
            Objects.equals(instructionId, that.instructionId) &&
            Objects.equals(investToGroupTransactionId, that.investToGroupTransactionId) &&
            Objects.equals(mediaAwarenessReportId, that.mediaAwarenessReportId) &&
            Objects.equals(preJobNiazsanjiId, that.preJobNiazsanjiId) &&
            Objects.equals(jobNiazsanjiId, that.jobNiazsanjiId) &&
            Objects.equals(niazsanjiOtherId, that.niazsanjiOtherId) &&
            Objects.equals(requestOtherNiazsanjiId, that.requestOtherNiazsanjiId) &&
            Objects.equals(prioritizeRequestNiazsanjiId, that.prioritizeRequestNiazsanjiId) &&
            Objects.equals(soldierId, that.soldierId) &&
            Objects.equals(soldierTrainingReportId, that.soldierTrainingReportId) &&
            Objects.equals(soldierMediaAwarenessReportId, that.soldierMediaAwarenessReportId) &&
            Objects.equals(evaluateCriteriaTrainingId, that.evaluateCriteriaTrainingId) &&
            Objects.equals(evaluateCriteriaDataId, that.evaluateCriteriaDataId) &&
            Objects.equals(effectivenessPhaseId, that.effectivenessPhaseId) &&
            Objects.equals(monitorLearningProcessId, that.monitorLearningProcessId) &&
            Objects.equals(matchingEducationalRecordId, that.matchingEducationalRecordId) &&
            Objects.equals(applicationProcessId, that.applicationProcessId);
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
        teacherGradeId,
        jobId,
        educationalModuleId,
        requestEducationalModuleId,
        educationalCenterId,
        educationalCenterGradeId,
        resourceId,
        requestOrganizationNiazsanjiId,
        finalOrganizationNiazsanjiId,
        finalNiazsanjiReportId,
        finalNiazsanjiReportPersonId,
        niazsanjiPersonGradeId,
        levelThreeEffectivenessId,
        levelFourEffectivenessId,
        designAndPlanningId,
        runPhaseId,
        announcementId,
        usersRequestId,
        niazsanjiFardiId,
        requestNiazsanjiFardiId,
        instructionId,
        investToGroupTransactionId,
        mediaAwarenessReportId,
        preJobNiazsanjiId,
        jobNiazsanjiId,
        niazsanjiOtherId,
        requestOtherNiazsanjiId,
        prioritizeRequestNiazsanjiId,
        soldierId,
        soldierTrainingReportId,
        soldierMediaAwarenessReportId,
        evaluateCriteriaTrainingId,
        evaluateCriteriaDataId,
        effectivenessPhaseId,
        monitorLearningProcessId,
        matchingEducationalRecordId,
        applicationProcessId
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
                (teacherGradeId != null ? "teacherGradeId=" + teacherGradeId + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
                (educationalCenterId != null ? "educationalCenterId=" + educationalCenterId + ", " : "") +
                (educationalCenterGradeId != null ? "educationalCenterGradeId=" + educationalCenterGradeId + ", " : "") +
                (resourceId != null ? "resourceId=" + resourceId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
                (finalOrganizationNiazsanjiId != null ? "finalOrganizationNiazsanjiId=" + finalOrganizationNiazsanjiId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (finalNiazsanjiReportPersonId != null ? "finalNiazsanjiReportPersonId=" + finalNiazsanjiReportPersonId + ", " : "") +
                (niazsanjiPersonGradeId != null ? "niazsanjiPersonGradeId=" + niazsanjiPersonGradeId + ", " : "") +
                (levelThreeEffectivenessId != null ? "levelThreeEffectivenessId=" + levelThreeEffectivenessId + ", " : "") +
                (levelFourEffectivenessId != null ? "levelFourEffectivenessId=" + levelFourEffectivenessId + ", " : "") +
                (designAndPlanningId != null ? "designAndPlanningId=" + designAndPlanningId + ", " : "") +
                (runPhaseId != null ? "runPhaseId=" + runPhaseId + ", " : "") +
                (announcementId != null ? "announcementId=" + announcementId + ", " : "") +
                (usersRequestId != null ? "usersRequestId=" + usersRequestId + ", " : "") +
                (niazsanjiFardiId != null ? "niazsanjiFardiId=" + niazsanjiFardiId + ", " : "") +
                (requestNiazsanjiFardiId != null ? "requestNiazsanjiFardiId=" + requestNiazsanjiFardiId + ", " : "") +
                (instructionId != null ? "instructionId=" + instructionId + ", " : "") +
                (investToGroupTransactionId != null ? "investToGroupTransactionId=" + investToGroupTransactionId + ", " : "") +
                (mediaAwarenessReportId != null ? "mediaAwarenessReportId=" + mediaAwarenessReportId + ", " : "") +
                (preJobNiazsanjiId != null ? "preJobNiazsanjiId=" + preJobNiazsanjiId + ", " : "") +
                (jobNiazsanjiId != null ? "jobNiazsanjiId=" + jobNiazsanjiId + ", " : "") +
                (niazsanjiOtherId != null ? "niazsanjiOtherId=" + niazsanjiOtherId + ", " : "") +
                (requestOtherNiazsanjiId != null ? "requestOtherNiazsanjiId=" + requestOtherNiazsanjiId + ", " : "") +
                (prioritizeRequestNiazsanjiId != null ? "prioritizeRequestNiazsanjiId=" + prioritizeRequestNiazsanjiId + ", " : "") +
                (soldierId != null ? "soldierId=" + soldierId + ", " : "") +
                (soldierTrainingReportId != null ? "soldierTrainingReportId=" + soldierTrainingReportId + ", " : "") +
                (soldierMediaAwarenessReportId != null ? "soldierMediaAwarenessReportId=" + soldierMediaAwarenessReportId + ", " : "") +
                (evaluateCriteriaTrainingId != null ? "evaluateCriteriaTrainingId=" + evaluateCriteriaTrainingId + ", " : "") +
                (evaluateCriteriaDataId != null ? "evaluateCriteriaDataId=" + evaluateCriteriaDataId + ", " : "") +
                (effectivenessPhaseId != null ? "effectivenessPhaseId=" + effectivenessPhaseId + ", " : "") +
                (monitorLearningProcessId != null ? "monitorLearningProcessId=" + monitorLearningProcessId + ", " : "") +
                (matchingEducationalRecordId != null ? "matchingEducationalRecordId=" + matchingEducationalRecordId + ", " : "") +
                (applicationProcessId != null ? "applicationProcessId=" + applicationProcessId + ", " : "") +
            "}";
    }

}
