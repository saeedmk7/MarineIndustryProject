package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DesignAndPlanning entity.
 */
public class DesignAndPlanningDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer directCost;

    @Size(max = 4096)
    private String directCostDescription;

    @NotNull
    private Integer undirectCost;

    @Size(max = 4096)
    private String undirectCostDescription;

    @NotNull
    private Integer step;

    @NotNull
    private Boolean finished;

    @Size(max = 50)
    private String finishedUserLogin;

    private ZonedDateTime finishedDate;

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

    private Set<PersonDTO> people = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long finalNiazsanjiReportId;

    private String finalNiazsanjiReportDescription;

    private Long mahiatCourseId;

    private String mahiatCourseTitle;

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long teachTypeId;

    private String teachTypeTitle;

    private Long courseLocationId;

    private String courseLocationTitle;

    private Long conditionsOfParticipantId;

    private String conditionsOfParticipantTitle;

    private Long effectivenessLevelId;

    private String effectivenessLevelTitle;

    private Long toolsAndFacilityId;

    private String toolsAndFacilityTitle;

    private Long teachingApproachId;

    private String teachingApproachTitle;

    private Long teachTechniqueId;

    private String teachTechniqueTitle;

    private Long effectivenessIndexId;

    private String effectivenessIndexTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDirectCost() {
        return directCost;
    }

    public void setDirectCost(Integer directCost) {
        this.directCost = directCost;
    }

    public String getDirectCostDescription() {
        return directCostDescription;
    }

    public void setDirectCostDescription(String directCostDescription) {
        this.directCostDescription = directCostDescription;
    }

    public Integer getUndirectCost() {
        return undirectCost;
    }

    public void setUndirectCost(Integer undirectCost) {
        this.undirectCost = undirectCost;
    }

    public String getUndirectCostDescription() {
        return undirectCostDescription;
    }

    public void setUndirectCostDescription(String undirectCostDescription) {
        this.undirectCostDescription = undirectCostDescription;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getFinishedUserLogin() {
        return finishedUserLogin;
    }

    public void setFinishedUserLogin(String finishedUserLogin) {
        this.finishedUserLogin = finishedUserLogin;
    }

    public ZonedDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(ZonedDateTime finishedDate) {
        this.finishedDate = finishedDate;
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

    public Set<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonDTO> people) {
        this.people = people;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public String getFinalNiazsanjiReportDescription() {
        return finalNiazsanjiReportDescription;
    }

    public void setFinalNiazsanjiReportDescription(String finalNiazsanjiReportDescription) {
        this.finalNiazsanjiReportDescription = finalNiazsanjiReportDescription;
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

    public Long getTeachTypeId() {
        return teachTypeId;
    }

    public void setTeachTypeId(Long teachTypeId) {
        this.teachTypeId = teachTypeId;
    }

    public String getTeachTypeTitle() {
        return teachTypeTitle;
    }

    public void setTeachTypeTitle(String teachTypeTitle) {
        this.teachTypeTitle = teachTypeTitle;
    }

    public Long getCourseLocationId() {
        return courseLocationId;
    }

    public void setCourseLocationId(Long courseLocationId) {
        this.courseLocationId = courseLocationId;
    }

    public String getCourseLocationTitle() {
        return courseLocationTitle;
    }

    public void setCourseLocationTitle(String courseLocationTitle) {
        this.courseLocationTitle = courseLocationTitle;
    }

    public Long getConditionsOfParticipantId() {
        return conditionsOfParticipantId;
    }

    public void setConditionsOfParticipantId(Long conditionsOfParticipantId) {
        this.conditionsOfParticipantId = conditionsOfParticipantId;
    }

    public String getConditionsOfParticipantTitle() {
        return conditionsOfParticipantTitle;
    }

    public void setConditionsOfParticipantTitle(String conditionsOfParticipantTitle) {
        this.conditionsOfParticipantTitle = conditionsOfParticipantTitle;
    }

    public Long getEffectivenessLevelId() {
        return effectivenessLevelId;
    }

    public void setEffectivenessLevelId(Long effectivenessLevelId) {
        this.effectivenessLevelId = effectivenessLevelId;
    }

    public String getEffectivenessLevelTitle() {
        return effectivenessLevelTitle;
    }

    public void setEffectivenessLevelTitle(String effectivenessLevelTitle) {
        this.effectivenessLevelTitle = effectivenessLevelTitle;
    }

    public Long getToolsAndFacilityId() {
        return toolsAndFacilityId;
    }

    public void setToolsAndFacilityId(Long toolsAndFacilityId) {
        this.toolsAndFacilityId = toolsAndFacilityId;
    }

    public String getToolsAndFacilityTitle() {
        return toolsAndFacilityTitle;
    }

    public void setToolsAndFacilityTitle(String toolsAndFacilityTitle) {
        this.toolsAndFacilityTitle = toolsAndFacilityTitle;
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

    public Long getTeachTechniqueId() {
        return teachTechniqueId;
    }

    public void setTeachTechniqueId(Long teachTechniqueId) {
        this.teachTechniqueId = teachTechniqueId;
    }

    public String getTeachTechniqueTitle() {
        return teachTechniqueTitle;
    }

    public void setTeachTechniqueTitle(String teachTechniqueTitle) {
        this.teachTechniqueTitle = teachTechniqueTitle;
    }

    public Long getEffectivenessIndexId() {
        return effectivenessIndexId;
    }

    public void setEffectivenessIndexId(Long effectivenessIndexId) {
        this.effectivenessIndexId = effectivenessIndexId;
    }

    public String getEffectivenessIndexTitle() {
        return effectivenessIndexTitle;
    }

    public void setEffectivenessIndexTitle(String effectivenessIndexTitle) {
        this.effectivenessIndexTitle = effectivenessIndexTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DesignAndPlanningDTO designAndPlanningDTO = (DesignAndPlanningDTO) o;
        if (designAndPlanningDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), designAndPlanningDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DesignAndPlanningDTO{" +
            "id=" + getId() +
            ", directCost=" + getDirectCost() +
            ", directCostDescription='" + getDirectCostDescription() + "'" +
            ", undirectCost=" + getUndirectCost() +
            ", undirectCostDescription='" + getUndirectCostDescription() + "'" +
            ", step=" + getStep() +
            ", finished='" + isFinished() + "'" +
            ", finishedUserLogin='" + getFinishedUserLogin() + "'" +
            ", finishedDate='" + getFinishedDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", finalNiazsanjiReport=" + getFinalNiazsanjiReportId() +
            ", finalNiazsanjiReport='" + getFinalNiazsanjiReportDescription() + "'" +
            ", mahiatCourse=" + getMahiatCourseId() +
            ", mahiatCourse='" + getMahiatCourseTitle() + "'" +
            ", courseType=" + getCourseTypeId() +
            ", courseType='" + getCourseTypeTitle() + "'" +
            ", teachType=" + getTeachTypeId() +
            ", teachType='" + getTeachTypeTitle() + "'" +
            ", courseLocation=" + getCourseLocationId() +
            ", courseLocation='" + getCourseLocationTitle() + "'" +
            ", conditionsOfParticipant=" + getConditionsOfParticipantId() +
            ", conditionsOfParticipant='" + getConditionsOfParticipantTitle() + "'" +
            ", effectivenessLevel=" + getEffectivenessLevelId() +
            ", effectivenessLevel='" + getEffectivenessLevelTitle() + "'" +
            ", toolsAndFacility=" + getToolsAndFacilityId() +
            ", toolsAndFacility='" + getToolsAndFacilityTitle() + "'" +
            ", teachingApproach=" + getTeachingApproachId() +
            ", teachingApproach='" + getTeachingApproachTitle() + "'" +
            ", teachTechnique=" + getTeachTechniqueId() +
            ", teachTechnique='" + getTeachTechniqueTitle() + "'" +
            ", effectivenessIndex=" + getEffectivenessIndexId() +
            ", effectivenessIndex='" + getEffectivenessIndexTitle() + "'" +
            "}";
    }
}
