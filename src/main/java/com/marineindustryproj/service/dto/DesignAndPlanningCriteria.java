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
 * Criteria class for the DesignAndPlanning entity. This class is used in DesignAndPlanningResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /design-and-plannings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DesignAndPlanningCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter directCost;

    private StringFilter directCostDescription;

    private IntegerFilter undirectCost;

    private StringFilter undirectCostDescription;

    private IntegerFilter step;

    private BooleanFilter finished;

    private StringFilter finishedUserLogin;

    private ZonedDateTimeFilter finishedDate;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter personId;

    private LongFilter documentId;

    private LongFilter finalNiazsanjiReportId;

    private LongFilter mahiatCourseId;

    private LongFilter courseTypeId;

    private LongFilter teachTypeId;

    private LongFilter courseLocationId;

    private LongFilter conditionsOfParticipantId;

    private LongFilter effectivenessLevelId;

    private LongFilter toolsAndFacilityId;

    private LongFilter teachingApproachId;

    private LongFilter teachTechniqueId;

    private LongFilter effectivenessIndexId;

    public DesignAndPlanningCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDirectCost() {
        return directCost;
    }

    public void setDirectCost(IntegerFilter directCost) {
        this.directCost = directCost;
    }

    public StringFilter getDirectCostDescription() {
        return directCostDescription;
    }

    public void setDirectCostDescription(StringFilter directCostDescription) {
        this.directCostDescription = directCostDescription;
    }

    public IntegerFilter getUndirectCost() {
        return undirectCost;
    }

    public void setUndirectCost(IntegerFilter undirectCost) {
        this.undirectCost = undirectCost;
    }

    public StringFilter getUndirectCostDescription() {
        return undirectCostDescription;
    }

    public void setUndirectCostDescription(StringFilter undirectCostDescription) {
        this.undirectCostDescription = undirectCostDescription;
    }

    public IntegerFilter getStep() {
        return step;
    }

    public void setStep(IntegerFilter step) {
        this.step = step;
    }

    public BooleanFilter getFinished() {
        return finished;
    }

    public void setFinished(BooleanFilter finished) {
        this.finished = finished;
    }

    public StringFilter getFinishedUserLogin() {
        return finishedUserLogin;
    }

    public void setFinishedUserLogin(StringFilter finishedUserLogin) {
        this.finishedUserLogin = finishedUserLogin;
    }

    public ZonedDateTimeFilter getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(ZonedDateTimeFilter finishedDate) {
        this.finishedDate = finishedDate;
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

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public LongFilter getMahiatCourseId() {
        return mahiatCourseId;
    }

    public void setMahiatCourseId(LongFilter mahiatCourseId) {
        this.mahiatCourseId = mahiatCourseId;
    }

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public LongFilter getTeachTypeId() {
        return teachTypeId;
    }

    public void setTeachTypeId(LongFilter teachTypeId) {
        this.teachTypeId = teachTypeId;
    }

    public LongFilter getCourseLocationId() {
        return courseLocationId;
    }

    public void setCourseLocationId(LongFilter courseLocationId) {
        this.courseLocationId = courseLocationId;
    }

    public LongFilter getConditionsOfParticipantId() {
        return conditionsOfParticipantId;
    }

    public void setConditionsOfParticipantId(LongFilter conditionsOfParticipantId) {
        this.conditionsOfParticipantId = conditionsOfParticipantId;
    }

    public LongFilter getEffectivenessLevelId() {
        return effectivenessLevelId;
    }

    public void setEffectivenessLevelId(LongFilter effectivenessLevelId) {
        this.effectivenessLevelId = effectivenessLevelId;
    }

    public LongFilter getToolsAndFacilityId() {
        return toolsAndFacilityId;
    }

    public void setToolsAndFacilityId(LongFilter toolsAndFacilityId) {
        this.toolsAndFacilityId = toolsAndFacilityId;
    }

    public LongFilter getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(LongFilter teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }

    public LongFilter getTeachTechniqueId() {
        return teachTechniqueId;
    }

    public void setTeachTechniqueId(LongFilter teachTechniqueId) {
        this.teachTechniqueId = teachTechniqueId;
    }

    public LongFilter getEffectivenessIndexId() {
        return effectivenessIndexId;
    }

    public void setEffectivenessIndexId(LongFilter effectivenessIndexId) {
        this.effectivenessIndexId = effectivenessIndexId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DesignAndPlanningCriteria that = (DesignAndPlanningCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(directCost, that.directCost) &&
            Objects.equals(directCostDescription, that.directCostDescription) &&
            Objects.equals(undirectCost, that.undirectCost) &&
            Objects.equals(undirectCostDescription, that.undirectCostDescription) &&
            Objects.equals(step, that.step) &&
            Objects.equals(finished, that.finished) &&
            Objects.equals(finishedUserLogin, that.finishedUserLogin) &&
            Objects.equals(finishedDate, that.finishedDate) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId) &&
            Objects.equals(mahiatCourseId, that.mahiatCourseId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(teachTypeId, that.teachTypeId) &&
            Objects.equals(courseLocationId, that.courseLocationId) &&
            Objects.equals(conditionsOfParticipantId, that.conditionsOfParticipantId) &&
            Objects.equals(effectivenessLevelId, that.effectivenessLevelId) &&
            Objects.equals(toolsAndFacilityId, that.toolsAndFacilityId) &&
            Objects.equals(teachingApproachId, that.teachingApproachId) &&
            Objects.equals(teachTechniqueId, that.teachTechniqueId) &&
            Objects.equals(effectivenessIndexId, that.effectivenessIndexId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        directCost,
        directCostDescription,
        undirectCost,
        undirectCostDescription,
        step,
        finished,
        finishedUserLogin,
        finishedDate,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        personId,
        documentId,
        finalNiazsanjiReportId,
        mahiatCourseId,
        courseTypeId,
        teachTypeId,
        courseLocationId,
        conditionsOfParticipantId,
        effectivenessLevelId,
        toolsAndFacilityId,
        teachingApproachId,
        teachTechniqueId,
        effectivenessIndexId
        );
    }

    @Override
    public String toString() {
        return "DesignAndPlanningCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (directCost != null ? "directCost=" + directCost + ", " : "") +
                (directCostDescription != null ? "directCostDescription=" + directCostDescription + ", " : "") +
                (undirectCost != null ? "undirectCost=" + undirectCost + ", " : "") +
                (undirectCostDescription != null ? "undirectCostDescription=" + undirectCostDescription + ", " : "") +
                (step != null ? "step=" + step + ", " : "") +
                (finished != null ? "finished=" + finished + ", " : "") +
                (finishedUserLogin != null ? "finishedUserLogin=" + finishedUserLogin + ", " : "") +
                (finishedDate != null ? "finishedDate=" + finishedDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
                (mahiatCourseId != null ? "mahiatCourseId=" + mahiatCourseId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (teachTypeId != null ? "teachTypeId=" + teachTypeId + ", " : "") +
                (courseLocationId != null ? "courseLocationId=" + courseLocationId + ", " : "") +
                (conditionsOfParticipantId != null ? "conditionsOfParticipantId=" + conditionsOfParticipantId + ", " : "") +
                (effectivenessLevelId != null ? "effectivenessLevelId=" + effectivenessLevelId + ", " : "") +
                (toolsAndFacilityId != null ? "toolsAndFacilityId=" + toolsAndFacilityId + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
                (teachTechniqueId != null ? "teachTechniqueId=" + teachTechniqueId + ", " : "") +
                (effectivenessIndexId != null ? "effectivenessIndexId=" + effectivenessIndexId + ", " : "") +
            "}";
    }

}
