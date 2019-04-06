package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the EducationalModule entity.
 */
public class EducationalModuleDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    @Size(max = 1000)
    private String title;

    @NotNull
    private Integer learningTimeTheorical;

    @NotNull
    private Integer learningTimePractical;

    @Size(max = 50)
    private String version;

    @Size(max = 50)
    private String innerCode;

    @Size(max = 50)
    private String centralizedCode;

    @Size(max = 4096)
    private String moreDescription;

    @Size(max = 100)
    private String recommendedBy;

    @Lob
    private String educationalModuleHeadlines;

    @Size(max = 4096)
    private String prerequisite;

    @Size(max = 100)
    private String drafters;

    @Max(value = 9)
    private Integer educationalModuleLevel;

    @Max(value = 99)
    private Integer educationalModuleGroup;

    @Max(value = 99)
    private Integer educationalModuleHour;

    private ZonedDateTime timePassed;

    private ZonedDateTime credit;

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

    private Set<ScientificWorkGroupDTO> scientificWorkGroups = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<EducationalCenterDTO> educationalCenters = new HashSet<>();

    private Set<GoalDTO> goals = new HashSet<>();

    private Set<ResourceDTO> resources = new HashSet<>();

    private Set<TeacherDTO> teachers = new HashSet<>();

    private Long requestEducationalModuleId;

    private String requestEducationalModuleTitle;

    private Long securityLevelId;

    private String securityLevelTitle;

    private Long skillableLevelOfSkillId;

    private String skillableLevelOfSkillTitle;

    private Long evaluationMethodId;

    private String evaluationMethodTitle;

    private Long organizationId;

    private String organizationTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public void setLearningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public Integer getLearningTimePractical() {
        return learningTimePractical;
    }

    public void setLearningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getCentralizedCode() {
        return centralizedCode;
    }

    public void setCentralizedCode(String centralizedCode) {
        this.centralizedCode = centralizedCode;
    }

    public String getMoreDescription() {
        return moreDescription;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getEducationalModuleHeadlines() {
        return educationalModuleHeadlines;
    }

    public void setEducationalModuleHeadlines(String educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getDrafters() {
        return drafters;
    }

    public void setDrafters(String drafters) {
        this.drafters = drafters;
    }

    public Integer getEducationalModuleLevel() {
        return educationalModuleLevel;
    }

    public void setEducationalModuleLevel(Integer educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
    }

    public Integer getEducationalModuleGroup() {
        return educationalModuleGroup;
    }

    public void setEducationalModuleGroup(Integer educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
    }

    public Integer getEducationalModuleHour() {
        return educationalModuleHour;
    }

    public void setEducationalModuleHour(Integer educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
    }

    public ZonedDateTime getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(ZonedDateTime timePassed) {
        this.timePassed = timePassed;
    }

    public ZonedDateTime getCredit() {
        return credit;
    }

    public void setCredit(ZonedDateTime credit) {
        this.credit = credit;
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

    public Set<ScientificWorkGroupDTO> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroupDTO> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Set<EducationalCenterDTO> getEducationalCenters() {
        return educationalCenters;
    }

    public void setEducationalCenters(Set<EducationalCenterDTO> educationalCenters) {
        this.educationalCenters = educationalCenters;
    }

    public Set<GoalDTO> getGoals() {
        return goals;
    }

    public void setGoals(Set<GoalDTO> goals) {
        this.goals = goals;
    }

    public Set<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceDTO> resources) {
        this.resources = resources;
    }

    public Set<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherDTO> teachers) {
        this.teachers = teachers;
    }

    public Long getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(Long requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }

    public String getRequestEducationalModuleTitle() {
        return requestEducationalModuleTitle;
    }

    public void setRequestEducationalModuleTitle(String requestEducationalModuleTitle) {
        this.requestEducationalModuleTitle = requestEducationalModuleTitle;
    }

    public Long getSecurityLevelId() {
        return securityLevelId;
    }

    public void setSecurityLevelId(Long securityLevelId) {
        this.securityLevelId = securityLevelId;
    }

    public String getSecurityLevelTitle() {
        return securityLevelTitle;
    }

    public void setSecurityLevelTitle(String securityLevelTitle) {
        this.securityLevelTitle = securityLevelTitle;
    }

    public Long getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(Long skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
    }

    public String getSkillableLevelOfSkillTitle() {
        return skillableLevelOfSkillTitle;
    }

    public void setSkillableLevelOfSkillTitle(String skillableLevelOfSkillTitle) {
        this.skillableLevelOfSkillTitle = skillableLevelOfSkillTitle;
    }

    public Long getEvaluationMethodId() {
        return evaluationMethodId;
    }

    public void setEvaluationMethodId(Long evaluationMethodId) {
        this.evaluationMethodId = evaluationMethodId;
    }

    public String getEvaluationMethodTitle() {
        return evaluationMethodTitle;
    }

    public void setEvaluationMethodTitle(String evaluationMethodTitle) {
        this.evaluationMethodTitle = evaluationMethodTitle;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalModuleDTO educationalModuleDTO = (EducationalModuleDTO) o;
        if (educationalModuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalModuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalModuleDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", learningTimeTheorical=" + getLearningTimeTheorical() +
            ", learningTimePractical=" + getLearningTimePractical() +
            ", version='" + getVersion() + "'" +
            ", innerCode='" + getInnerCode() + "'" +
            ", centralizedCode='" + getCentralizedCode() + "'" +
            ", moreDescription='" + getMoreDescription() + "'" +
            ", recommendedBy='" + getRecommendedBy() + "'" +
            ", educationalModuleHeadlines='" + getEducationalModuleHeadlines() + "'" +
            ", prerequisite='" + getPrerequisite() + "'" +
            ", drafters='" + getDrafters() + "'" +
            ", educationalModuleLevel=" + getEducationalModuleLevel() +
            ", educationalModuleGroup=" + getEducationalModuleGroup() +
            ", educationalModuleHour=" + getEducationalModuleHour() +
            ", timePassed='" + getTimePassed() + "'" +
            ", credit='" + getCredit() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", requestEducationalModule=" + getRequestEducationalModuleId() +
            ", requestEducationalModule='" + getRequestEducationalModuleTitle() + "'" +
            ", securityLevel=" + getSecurityLevelId() +
            ", securityLevel='" + getSecurityLevelTitle() + "'" +
            ", skillableLevelOfSkill=" + getSkillableLevelOfSkillId() +
            ", skillableLevelOfSkill='" + getSkillableLevelOfSkillTitle() + "'" +
            ", evaluationMethod=" + getEvaluationMethodId() +
            ", evaluationMethod='" + getEvaluationMethodTitle() + "'" +
            ", organization=" + getOrganizationId() +
            ", organization='" + getOrganizationTitle() + "'" +
            "}";
    }
}
