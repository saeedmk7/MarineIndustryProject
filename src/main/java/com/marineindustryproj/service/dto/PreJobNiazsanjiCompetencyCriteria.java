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
 * Criteria class for the PreJobNiazsanjiCompetency entity. This class is used in PreJobNiazsanjiCompetencyResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pre-job-niazsanji-competencies?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PreJobNiazsanjiCompetencyCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter needToImprove;

    private StringFilter needToImproveDescription;

    private StringFilter fixCompetencyDeficiencyDescription;

    private StringFilter educationalModuleText;

    private IntegerFilter sumScore;

    private IntegerFilter priority;

    private BooleanFilter selected;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter priorityCriteriaValueId;

    private LongFilter teachingApproachId;

    private LongFilter fixCompetencyDeficiencyId;

    private LongFilter educationalModuleId;

    private LongFilter preJobNiazsanjiId;

    private LongFilter competencyId;

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

    public IntegerFilter getNeedToImprove() {
        return needToImprove;
    }

    public void setNeedToImprove(IntegerFilter needToImprove) {
        this.needToImprove = needToImprove;
    }

    public StringFilter getNeedToImproveDescription() {
        return needToImproveDescription;
    }

    public void setNeedToImproveDescription(StringFilter needToImproveDescription) {
        this.needToImproveDescription = needToImproveDescription;
    }

    public StringFilter getFixCompetencyDeficiencyDescription() {
        return fixCompetencyDeficiencyDescription;
    }

    public void setFixCompetencyDeficiencyDescription(StringFilter fixCompetencyDeficiencyDescription) {
        this.fixCompetencyDeficiencyDescription = fixCompetencyDeficiencyDescription;
    }

    public StringFilter getEducationalModuleText() {
        return educationalModuleText;
    }

    public void setEducationalModuleText(StringFilter educationalModuleText) {
        this.educationalModuleText = educationalModuleText;
    }

    public IntegerFilter getSumScore() {
        return sumScore;
    }

    public void setSumScore(IntegerFilter sumScore) {
        this.sumScore = sumScore;
    }

    public IntegerFilter getPriority() {
        return priority;
    }

    public void setPriority(IntegerFilter priority) {
        this.priority = priority;
    }

    public BooleanFilter getSelected() {
        return selected;
    }

    public void setSelected(BooleanFilter selected) {
        this.selected = selected;
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

    public LongFilter getPriorityCriteriaValueId() {
        return priorityCriteriaValueId;
    }

    public void setPriorityCriteriaValueId(LongFilter priorityCriteriaValueId) {
        this.priorityCriteriaValueId = priorityCriteriaValueId;
    }

    public LongFilter getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(LongFilter teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }

    public LongFilter getFixCompetencyDeficiencyId() {
        return fixCompetencyDeficiencyId;
    }

    public void setFixCompetencyDeficiencyId(LongFilter fixCompetencyDeficiencyId) {
        this.fixCompetencyDeficiencyId = fixCompetencyDeficiencyId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getPreJobNiazsanjiId() {
        return preJobNiazsanjiId;
    }

    public void setPreJobNiazsanjiId(LongFilter preJobNiazsanjiId) {
        this.preJobNiazsanjiId = preJobNiazsanjiId;
    }

    public LongFilter getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(LongFilter competencyId) {
        this.competencyId = competencyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PreJobNiazsanjiCompetencyCriteria that = (PreJobNiazsanjiCompetencyCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(needToImprove, that.needToImprove) &&
            Objects.equals(needToImproveDescription, that.needToImproveDescription) &&
            Objects.equals(fixCompetencyDeficiencyDescription, that.fixCompetencyDeficiencyDescription) &&
            Objects.equals(educationalModuleText, that.educationalModuleText) &&
            Objects.equals(sumScore, that.sumScore) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(selected, that.selected) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(priorityCriteriaValueId, that.priorityCriteriaValueId) &&
            Objects.equals(teachingApproachId, that.teachingApproachId) &&
            Objects.equals(fixCompetencyDeficiencyId, that.fixCompetencyDeficiencyId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(preJobNiazsanjiId, that.preJobNiazsanjiId) &&
            Objects.equals(competencyId, that.competencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        needToImprove,
        needToImproveDescription,
        fixCompetencyDeficiencyDescription,
        educationalModuleText,
        sumScore,
        priority,
        selected,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        priorityCriteriaValueId,
        teachingApproachId,
        fixCompetencyDeficiencyId,
        educationalModuleId,
        preJobNiazsanjiId,
        competencyId
        );
    }

    @Override
    public String toString() {
        return "PreJobNiazsanjiCompetencyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (needToImprove != null ? "needToImprove=" + needToImprove + ", " : "") +
                (needToImproveDescription != null ? "needToImproveDescription=" + needToImproveDescription + ", " : "") +
                (fixCompetencyDeficiencyDescription != null ? "fixCompetencyDeficiencyDescription=" + fixCompetencyDeficiencyDescription + ", " : "") +
                (educationalModuleText != null ? "educationalModuleText=" + educationalModuleText + ", " : "") +
                (sumScore != null ? "sumScore=" + sumScore + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (selected != null ? "selected=" + selected + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (priorityCriteriaValueId != null ? "priorityCriteriaValueId=" + priorityCriteriaValueId + ", " : "") +
                (teachingApproachId != null ? "teachingApproachId=" + teachingApproachId + ", " : "") +
                (fixCompetencyDeficiencyId != null ? "fixCompetencyDeficiencyId=" + fixCompetencyDeficiencyId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (preJobNiazsanjiId != null ? "preJobNiazsanjiId=" + preJobNiazsanjiId + ", " : "") +
                (competencyId != null ? "competencyId=" + competencyId + ", " : "") +
            "}";
    }

}
