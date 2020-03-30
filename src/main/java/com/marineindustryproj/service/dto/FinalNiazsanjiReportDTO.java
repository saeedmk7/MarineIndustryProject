package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.domain.enumeration.Grade;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class FinalNiazsanjiReportDTO implements Serializable {

    private Long id;

    private Integer niazsanjiYear;

    private NiazSanjiSource niazSanjiSource;

    private Integer priceCost;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @NotNull
    private Boolean archived;

    @Size(max = 50)
    private String archivedUserLogin;

    private ZonedDateTime archivedDate;

    @NotNull
    private Integer status;

    private Integer runMonth;

    private Integer planningRunMonth;

    private Integer finalizeCost;

    @Size(max = 50)
    private String guid;

    private Boolean hasImportantMessage;

    @Size(max = 4096)
    private String restrictionDescription;

    @Size(max = 4096)
    private String goalsText;

    @Size(max = 4096)
    private String prerequisite;

    private Integer priority;

    private Float effectivenessPhaseAverage;

    private Grade effectivenessPhaseGrade;

    private Integer selectedEffectivenessPhaseLevel;

    private Integer currentEffectivenessPhaseLevel;

    private ZonedDateTime lastEffectivenessPhaseFinish;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<RestrictionDTO> restrictions = new HashSet<>();

    private Long niazsanjiIntegrationId;

    private String niazsanjiIntegrationNiazsanjiYear;

    private Long teacherId;

    private String teacherFamily;

    private String teacherName;

    private Long niazsanjiInputId;

    private String niazsanjiInputTitle;

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Long educationalModuleId;

    private String educationalModuleCode;

    private String educationalModuleTitle;

    private Long mahiatCourseId;

    private String mahiatCourseTitle;

    private Long teachingApproachId;

    private String teachingApproachTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public NiazSanjiSource getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public void setNiazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(Integer runMonth) {
        this.runMonth = runMonth;
    }

    public Integer getPlanningRunMonth() {
        return planningRunMonth;
    }

    public void setPlanningRunMonth(Integer planningRunMonth) {
        this.planningRunMonth = planningRunMonth;
    }

    public Integer getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Float getEffectivenessPhaseAverage() {
        return effectivenessPhaseAverage;
    }

    public void setEffectivenessPhaseAverage(Float effectivenessPhaseAverage) {
        this.effectivenessPhaseAverage = effectivenessPhaseAverage;
    }

    public Grade getEffectivenessPhaseGrade() {
        return effectivenessPhaseGrade;
    }

    public void setEffectivenessPhaseGrade(Grade effectivenessPhaseGrade) {
        this.effectivenessPhaseGrade = effectivenessPhaseGrade;
    }

    public Integer getSelectedEffectivenessPhaseLevel() {
        return selectedEffectivenessPhaseLevel;
    }

    public void setSelectedEffectivenessPhaseLevel(Integer selectedEffectivenessPhaseLevel) {
        this.selectedEffectivenessPhaseLevel = selectedEffectivenessPhaseLevel;
    }

    public Integer getCurrentEffectivenessPhaseLevel() {
        return currentEffectivenessPhaseLevel;
    }

    public void setCurrentEffectivenessPhaseLevel(Integer currentEffectivenessPhaseLevel) {
        this.currentEffectivenessPhaseLevel = currentEffectivenessPhaseLevel;
    }

    public ZonedDateTime getLastEffectivenessPhaseFinish() {
        return lastEffectivenessPhaseFinish;
    }

    public void setLastEffectivenessPhaseFinish(ZonedDateTime lastEffectivenessPhaseFinish) {
        this.lastEffectivenessPhaseFinish = lastEffectivenessPhaseFinish;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Set<RestrictionDTO> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Set<RestrictionDTO> restrictions) {
        this.restrictions = restrictions;
    }

    public Long getNiazsanjiIntegrationId() {
        return niazsanjiIntegrationId;
    }

    public void setNiazsanjiIntegrationId(Long niazsanjiIntegrationId) {
        this.niazsanjiIntegrationId = niazsanjiIntegrationId;
    }

    public String getNiazsanjiIntegrationNiazsanjiYear() {
        return niazsanjiIntegrationNiazsanjiYear;
    }

    public void setNiazsanjiIntegrationNiazsanjiYear(String niazsanjiIntegrationNiazsanjiYear) {
        this.niazsanjiIntegrationNiazsanjiYear = niazsanjiIntegrationNiazsanjiYear;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFamily() {
        return teacherFamily;
    }

    public void setTeacherFamily(String teacherFamily) {
        this.teacherFamily = teacherFamily;
    }

    public Long getNiazsanjiInputId() {
        return niazsanjiInputId;
    }

    public void setNiazsanjiInputId(Long niazsanjiInputId) {
        this.niazsanjiInputId = niazsanjiInputId;
    }

    public String getNiazsanjiInputTitle() {
        return niazsanjiInputTitle;
    }

    public void setNiazsanjiInputTitle(String niazsanjiInputTitle) {
        this.niazsanjiInputTitle = niazsanjiInputTitle;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    public Long getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(Long educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public Long getMahiatCourseId() {
        return mahiatCourseId;
    }

    public void setMahiatCourseId(Long mahiatCourseId) {
        this.mahiatCourseId = mahiatCourseId;
    }

    public String getMahiatCourseTitle() {
        return mahiatCourseTitle;
    }

    public void setMahiatCourseTitle(String mahiatCourseTitle) {
        this.mahiatCourseTitle = mahiatCourseTitle;
    }

    public Long getTeachingApproachId() {
        return teachingApproachId;
    }

    public void setTeachingApproachId(Long teachingApproachId) {
        this.teachingApproachId = teachingApproachId;
    }

    public String getTeachingApproachTitle() {
        return teachingApproachTitle;
    }

    public void setTeachingApproachTitle(String teachingApproachTitle) {
        this.teachingApproachTitle = teachingApproachTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalNiazsanjiReportDTO finalNiazsanjiReportDTO = (FinalNiazsanjiReportDTO) o;
        if (finalNiazsanjiReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportDTO{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", niazSanjiSource='" + getNiazSanjiSource() + "'" +
            ", priceCost=" + getPriceCost() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", runMonth=" + getRunMonth() +
            ", planningRunMonth=" + getPlanningRunMonth() +
            ", finalizeCost=" + getFinalizeCost() +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", restrictionDescription='" + getRestrictionDescription() + "'" +
            ", goalsText='" + getGoalsText() + "'" +
            ", prerequisite='" + getPrerequisite() + "'" +
            ", priority=" + getPriority() +
            ", effectivenessPhaseAverage=" + getEffectivenessPhaseAverage() +
            ", effectivenessPhaseGrade='" + getEffectivenessPhaseGrade() + "'" +
            ", selectedEffectivenessPhaseLevel=" + getSelectedEffectivenessPhaseLevel() +
            ", currentEffectivenessPhaseLevel=" + getCurrentEffectivenessPhaseLevel() +
            ", lastEffectivenessPhaseFinish='" + getLastEffectivenessPhaseFinish() + "'" +
            ", niazsanjiIntegration=" + getNiazsanjiIntegrationId() +
            ", niazsanjiIntegration='" + getNiazsanjiIntegrationNiazsanjiYear() + "'" +
            ", teacher=" + getTeacherId() +
            ", teacher='" + getTeacherFamily() + "'" +
            ", teacher='" + getTeacherName() + "'" +
            ", niazsanjiInput=" + getNiazsanjiInputId() +
            ", niazsanjiInput='" + getNiazsanjiInputTitle() + "'" +
            ", courseType=" + getCourseTypeId() +
            ", courseType='" + getCourseTypeTitle() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleCode() + "'" +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", mahiatCourse=" + getMahiatCourseId() +
            ", mahiatCourse='" + getMahiatCourseTitle() + "'" +
            ", teachingApproach=" + getTeachingApproachId() +
            ", teachingApproach='" + getTeachingApproachTitle() + "'" +
            "}";
    }

    public String getEducationalModuleCode() {
        return educationalModuleCode;
    }

    public void setEducationalModuleCode(String educationalModuleCode) {
        this.educationalModuleCode = educationalModuleCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
