package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.domain.enumeration.Grade;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the FinalNiazsanjiReport entity. This class is used in FinalNiazsanjiReportResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /final-niazsanji-reports?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinalNiazsanjiReportCriteria implements Serializable {

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public StringFilter getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(StringFilter educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }

    /**
     * Class for filtering NiazSanjiSource
     */
    public static class NiazSanjiSourceFilter extends Filter<NiazSanjiSource> {
    }
    /**
     * Class for filtering Grade
     */
    public static class GradeFilter extends Filter<Grade> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter niazsanjiYear;

    private NiazSanjiSourceFilter niazSanjiSource;

    private IntegerFilter priceCost;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private IntegerFilter runMonth;

    private IntegerFilter planningRunMonth;

    private IntegerFilter finalizeCost;

    private StringFilter guid;

    private BooleanFilter hasImportantMessage;

    private StringFilter restrictionDescription;

    private StringFilter goalsText;

    private StringFilter prerequisite;

    private IntegerFilter priority;

    private FloatFilter effectivenessPhaseAverage;

    private GradeFilter effectivenessPhaseGrade;

    private IntegerFilter selectedEffectivenessPhaseLevel;

    private IntegerFilter currentEffectivenessPhaseLevel;

    private ZonedDateTimeFilter lastEffectivenessPhaseFinish;

    private LongFilter finalNiazsanjiReportPersonId;

    private LongFilter personId;

    private LongFilter designAndPlanningId;

    private LongFilter runPhaseId;

    private LongFilter pollId;

    private LongFilter effectivenessPhaseId;

    private LongFilter documentId;

    private LongFilter restrictionId;

    private LongFilter niazsanjiIntegrationId;

    private LongFilter teacherId;

    private LongFilter niazsanjiInputId;

    private LongFilter courseTypeId;

    private LongFilter organizationChartId;

    private LongFilter educationalModuleId;

    private LongFilter mahiatCourseId;

    private StringFilter educationalModuleCode;

    private StringFilter educationalModuleTitle;

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

    public NiazSanjiSourceFilter getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public void setNiazSanjiSource(NiazSanjiSourceFilter niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public IntegerFilter getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(IntegerFilter priceCost) {
        this.priceCost = priceCost;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public IntegerFilter getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(IntegerFilter runMonth) {
        this.runMonth = runMonth;
    }

    public IntegerFilter getPlanningRunMonth() {
        return planningRunMonth;
    }

    public void setPlanningRunMonth(IntegerFilter planningRunMonth) {
        this.planningRunMonth = planningRunMonth;
    }

    public IntegerFilter getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(IntegerFilter finalizeCost) {
        this.finalizeCost = finalizeCost;
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

    public IntegerFilter getPriority() {
        return priority;
    }

    public void setPriority(IntegerFilter priority) {
        this.priority = priority;
    }

    public FloatFilter getEffectivenessPhaseAverage() {
        return effectivenessPhaseAverage;
    }

    public void setEffectivenessPhaseAverage(FloatFilter effectivenessPhaseAverage) {
        this.effectivenessPhaseAverage = effectivenessPhaseAverage;
    }

    public GradeFilter getEffectivenessPhaseGrade() {
        return effectivenessPhaseGrade;
    }

    public void setEffectivenessPhaseGrade(GradeFilter effectivenessPhaseGrade) {
        this.effectivenessPhaseGrade = effectivenessPhaseGrade;
    }

    public IntegerFilter getSelectedEffectivenessPhaseLevel() {
        return selectedEffectivenessPhaseLevel;
    }

    public void setSelectedEffectivenessPhaseLevel(IntegerFilter selectedEffectivenessPhaseLevel) {
        this.selectedEffectivenessPhaseLevel = selectedEffectivenessPhaseLevel;
    }

    public IntegerFilter getCurrentEffectivenessPhaseLevel() {
        return currentEffectivenessPhaseLevel;
    }

    public void setCurrentEffectivenessPhaseLevel(IntegerFilter currentEffectivenessPhaseLevel) {
        this.currentEffectivenessPhaseLevel = currentEffectivenessPhaseLevel;
    }

    public ZonedDateTimeFilter getLastEffectivenessPhaseFinish() {
        return lastEffectivenessPhaseFinish;
    }

    public void setLastEffectivenessPhaseFinish(ZonedDateTimeFilter lastEffectivenessPhaseFinish) {
        this.lastEffectivenessPhaseFinish = lastEffectivenessPhaseFinish;
    }

    public LongFilter getFinalNiazsanjiReportPersonId() {
        return finalNiazsanjiReportPersonId;
    }

    public void setFinalNiazsanjiReportPersonId(LongFilter finalNiazsanjiReportPersonId) {
        this.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
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

    public LongFilter getPollId() {
        return pollId;
    }

    public void setPollId(LongFilter pollId) {
        this.pollId = pollId;
    }

    public LongFilter getEffectivenessPhaseId() {
        return effectivenessPhaseId;
    }

    public void setEffectivenessPhaseId(LongFilter effectivenessPhaseId) {
        this.effectivenessPhaseId = effectivenessPhaseId;
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

    public LongFilter getNiazsanjiIntegrationId() {
        return niazsanjiIntegrationId;
    }

    public void setNiazsanjiIntegrationId(LongFilter niazsanjiIntegrationId) {
        this.niazsanjiIntegrationId = niazsanjiIntegrationId;
    }

    public LongFilter getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(LongFilter teacherId) {
        this.teacherId = teacherId;
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

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }
    public StringFilter getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(StringFilter educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }


    public LongFilter getMahiatCourseId() {
        return mahiatCourseId;
    }

    public void setMahiatCourseId(LongFilter mahiatCourseId) {
        this.mahiatCourseId = mahiatCourseId;
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
        final FinalNiazsanjiReportCriteria that = (FinalNiazsanjiReportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
            Objects.equals(niazSanjiSource, that.niazSanjiSource) &&
            Objects.equals(priceCost, that.priceCost) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(runMonth, that.runMonth) &&
            Objects.equals(planningRunMonth, that.planningRunMonth) &&
            Objects.equals(finalizeCost, that.finalizeCost) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(hasImportantMessage, that.hasImportantMessage) &&
            Objects.equals(restrictionDescription, that.restrictionDescription) &&
            Objects.equals(goalsText, that.goalsText) &&
            Objects.equals(prerequisite, that.prerequisite) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(effectivenessPhaseAverage, that.effectivenessPhaseAverage) &&
            Objects.equals(effectivenessPhaseGrade, that.effectivenessPhaseGrade) &&
            Objects.equals(selectedEffectivenessPhaseLevel, that.selectedEffectivenessPhaseLevel) &&
            Objects.equals(currentEffectivenessPhaseLevel, that.currentEffectivenessPhaseLevel) &&
            Objects.equals(lastEffectivenessPhaseFinish, that.lastEffectivenessPhaseFinish) &&
            Objects.equals(finalNiazsanjiReportPersonId, that.finalNiazsanjiReportPersonId) &&
            Objects.equals(designAndPlanningId, that.designAndPlanningId) &&
            Objects.equals(runPhaseId, that.runPhaseId) &&
            Objects.equals(pollId, that.pollId) &&
            Objects.equals(effectivenessPhaseId, that.effectivenessPhaseId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(restrictionId, that.restrictionId) &&
            Objects.equals(niazsanjiIntegrationId, that.niazsanjiIntegrationId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(niazsanjiInputId, that.niazsanjiInputId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
                        Objects.equals(mahiatCourseId, that.mahiatCourseId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(educationalModuleCode, that.educationalModuleCode) &&
                        Objects.equals(teachingApproachId, that.teachingApproachId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        niazsanjiYear,
        niazSanjiSource,
        priceCost,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        runMonth,
        planningRunMonth,
        finalizeCost,
        guid,
        hasImportantMessage,
        restrictionDescription,
        goalsText,
        prerequisite,
        priority,
        effectivenessPhaseAverage,
        effectivenessPhaseGrade,
        selectedEffectivenessPhaseLevel,
        currentEffectivenessPhaseLevel,
        lastEffectivenessPhaseFinish,
        finalNiazsanjiReportPersonId,
        designAndPlanningId,
        runPhaseId,
        pollId,
        effectivenessPhaseId,
        documentId,
        restrictionId,
        niazsanjiIntegrationId,
        teacherId,
        niazsanjiInputId,
        courseTypeId,
        organizationChartId,
        educationalModuleId,
        mahiatCourseId,
        personId,
        educationalModuleTitle,
        educationalModuleCode,
        teachingApproachId
        );
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
                (niazSanjiSource != null ? "niazSanjiSource=" + niazSanjiSource + ", " : "") +
                (priceCost != null ? "priceCost=" + priceCost + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (runMonth != null ? "runMonth=" + runMonth + ", " : "") +
                (planningRunMonth != null ? "planningRunMonth=" + planningRunMonth + ", " : "") +
                (finalizeCost != null ? "finalizeCost=" + finalizeCost + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (hasImportantMessage != null ? "hasImportantMessage=" + hasImportantMessage + ", " : "") +
                (restrictionDescription != null ? "restrictionDescription=" + restrictionDescription + ", " : "") +
                (goalsText != null ? "goalsText=" + goalsText + ", " : "") +
                (prerequisite != null ? "prerequisite=" + prerequisite + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (effectivenessPhaseAverage != null ? "effectivenessPhaseAverage=" + effectivenessPhaseAverage + ", " : "") +
                (effectivenessPhaseGrade != null ? "effectivenessPhaseGrade=" + effectivenessPhaseGrade + ", " : "") +
                (selectedEffectivenessPhaseLevel != null ? "selectedEffectivenessPhaseLevel=" + selectedEffectivenessPhaseLevel + ", " : "") +
                (currentEffectivenessPhaseLevel != null ? "currentEffectivenessPhaseLevel=" + currentEffectivenessPhaseLevel + ", " : "") +
                (lastEffectivenessPhaseFinish != null ? "lastEffectivenessPhaseFinish=" + lastEffectivenessPhaseFinish + ", " : "") +
                (finalNiazsanjiReportPersonId != null ? "finalNiazsanjiReportPersonId=" + finalNiazsanjiReportPersonId + ", " : "") +
                (designAndPlanningId != null ? "designAndPlanningId=" + designAndPlanningId + ", " : "") +
                (runPhaseId != null ? "runPhaseId=" + runPhaseId + ", " : "") +
                (pollId != null ? "pollId=" + pollId + ", " : "") +
                (effectivenessPhaseId != null ? "effectivenessPhaseId=" + effectivenessPhaseId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (restrictionId != null ? "restrictionId=" + restrictionId + ", " : "") +
                (niazsanjiIntegrationId != null ? "niazsanjiIntegrationId=" + niazsanjiIntegrationId + ", " : "") +
                (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
                (niazsanjiInputId != null ? "niazsanjiInputId=" + niazsanjiInputId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (mahiatCourseId != null ? "mahiatCourseId=" + mahiatCourseId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (educationalModuleCode != null ? "educationalModuleCode=" + educationalModuleCode + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
            "}";
    }

}
