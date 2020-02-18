package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A DTO for the RequestEducationalModule entity.
 */
public class RequestEducationalModuleDTO implements Serializable {

    private Long id;

    private String code;

    @NotNull
    @Size(max = 100)
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

    @Size(max = 4096)
    private String recommendedBy;

    @Size(max = 4096)
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

    @Lob
    private String conversation;

    private RequestStatus requestStatus;

    @Size(max = 50)
    private String changeStatusUserLogin;

    @Size(max = 4096)
    private String goalsText;

    @Size(max = 4096)
    private String teachersText;

    @Size(max = 50)
    private String guid;

    private Boolean hasImportantMessage;

    @Size(max = 4096)
    private String restrictionDescription;

    private ZonedDateTime recommendDate;

    @Size(max = 4096)
    private String goalsBehavioralText;

    @Size(max = 4096)
    private String neededSoftwares;

    @Size(max = 4096)
    private String neededHardware;

    @Size(max = 4096)
    private String courseContactsTerms;

    private Set<ScientificWorkGroupDTO> scientificWorkGroups = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<EducationalCenterDTO> educationalCenters = new HashSet<>();

    private Set<GoalDTO> goals = new HashSet<>();

    private Set<ResourceDTO> resources = new HashSet<>();

    private Set<TeacherDTO> teachers = new HashSet<>();

    private Set<RestrictionDTO> restrictions = new HashSet<>();

    private Set<PeopleUnderTrainingDTO> peopleUnderTrainings = new HashSet<>();

    private Set<TeachingApproachDTO> teachingApproaches = new HashSet<>();

    private Set<EffectivenessLevelDTO> effectivenessLevels = new HashSet<>();

    private Set<EffectivenessIndexDTO> effectivenessIndices = new HashSet<>();

    private Set<HeadlineDTO> headlines = new HashSet<>();

    private Long securityLevelId;

    private String securityLevelTitle;

    private Long skillableLevelOfSkillId;

    private String skillableLevelOfSkillTitle;

    private Long evaluationMethodId;

    private String evaluationMethodTitle;

    private Long organizationId;

    private String organizationTitle;

    private Long competencyId;

    private String competencyTitle;

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

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getTeachersText() {
        return teachersText;
    }

    public void setTeachersText(String teachersText) {
        this.teachersText = teachersText;
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

    public ZonedDateTime getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(ZonedDateTime recommendDate) {
        this.recommendDate = recommendDate;
    }

    public String getGoalsBehavioralText() {
        return goalsBehavioralText;
    }

    public void setGoalsBehavioralText(String goalsBehavioralText) {
        this.goalsBehavioralText = goalsBehavioralText;
    }

    public String getNeededSoftwares() {
        return neededSoftwares;
    }

    public void setNeededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public String getNeededHardware() {
        return neededHardware;
    }

    public void setNeededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
    }

    public String getCourseContactsTerms() {
        return courseContactsTerms;
    }

    public void setCourseContactsTerms(String courseContactsTerms) {
        this.courseContactsTerms = courseContactsTerms;
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

    public Set<RestrictionDTO> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Set<RestrictionDTO> restrictions) {
        this.restrictions = restrictions;
    }

    public Set<PeopleUnderTrainingDTO> getPeopleUnderTrainings() {
        return peopleUnderTrainings;
    }

    public void setPeopleUnderTrainings(Set<PeopleUnderTrainingDTO> peopleUnderTrainings) {
        this.peopleUnderTrainings = peopleUnderTrainings;
    }

    public Set<TeachingApproachDTO> getTeachingApproaches() {
        return teachingApproaches;
    }

    public void setTeachingApproaches(Set<TeachingApproachDTO> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
    }

    public Set<EffectivenessLevelDTO> getEffectivenessLevels() {
        return effectivenessLevels;
    }

    public void setEffectivenessLevels(Set<EffectivenessLevelDTO> effectivenessLevels) {
        this.effectivenessLevels = effectivenessLevels;
    }

    public Set<EffectivenessIndexDTO> getEffectivenessIndices() {
        return effectivenessIndices;
    }

    public void setEffectivenessIndices(Set<EffectivenessIndexDTO> effectivenessIndices) {
        this.effectivenessIndices = effectivenessIndices;
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

    public Long getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(Long competencyId) {
        this.competencyId = competencyId;
    }

    public String getCompetencyTitle() {
        return competencyTitle;
    }

    public void setCompetencyTitle(String competencyTitle) {
        this.competencyTitle = competencyTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestEducationalModuleDTO requestEducationalModuleDTO = (RequestEducationalModuleDTO) o;
        if (requestEducationalModuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestEducationalModuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestEducationalModuleDTO{" +
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
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", goalsText='" + getGoalsText() + "'" +
            ", teachersText='" + getTeachersText() + "'" +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", restrictionDescription='" + getRestrictionDescription() + "'" +
            ", recommendDate='" + getRecommendDate() + "'" +
            ", goalsBehavioralText='" + getGoalsBehavioralText() + "'" +
            ", neededSoftwares='" + getNeededSoftwares() + "'" +
            ", neededHardware='" + getNeededHardware() + "'" +
            ", courseContactsTerms='" + getCourseContactsTerms() + "'" +
            ", securityLevel=" + getSecurityLevelId() +
            ", securityLevel='" + getSecurityLevelTitle() + "'" +
            ", skillableLevelOfSkill=" + getSkillableLevelOfSkillId() +
            ", skillableLevelOfSkill='" + getSkillableLevelOfSkillTitle() + "'" +
            ", evaluationMethod=" + getEvaluationMethodId() +
            ", evaluationMethod='" + getEvaluationMethodTitle() + "'" +
            ", organization=" + getOrganizationId() +
            ", organization='" + getOrganizationTitle() + "'" +
            ", competency=" + getCompetencyId() +
            ", competency='" + getCompetencyTitle() + "'" +
            "}";
    }

    public Set<HeadlineDTO> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(Set<HeadlineDTO> headlines) {
        this.headlines = headlines;
    }
}
