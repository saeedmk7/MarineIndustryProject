package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A RequestEducationalModule.
 */
@Entity
@Table(name = "request_educational_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestEducationalModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotNull
    @Column(name = "learning_time_theorical", nullable = false)
    private Integer learningTimeTheorical;

    @NotNull
    @Column(name = "learning_time_practical", nullable = false)
    private Integer learningTimePractical;

    @Size(max = 50)
    @Column(name = "version", length = 50)
    private String version;

    @Size(max = 50)
    @Column(name = "inner_code", length = 50)
    private String innerCode;

    @Size(max = 50)
    @Column(name = "centralized_code", length = 50)
    private String centralizedCode;

    @Size(max = 4096)
    @Column(name = "more_description", length = 4096)
    private String moreDescription;

    @Size(max = 4096)
    @Column(name = "recommended_by", length = 4096)
    private String recommendedBy;

    @Size(max = 4096)
    @Column(name = "educational_module_headlines", length = 4096)
    private String educationalModuleHeadlines;

    @Size(max = 4096)
    @Column(name = "prerequisite", length = 4096)
    private String prerequisite;

    @Size(max = 100)
    @Column(name = "drafters", length = 100)
    private String drafters;

    @Max(value = 9)
    @Column(name = "educational_module_level")
    private Integer educationalModuleLevel;

    @Max(value = 99)
    @Column(name = "educational_module_group")
    private Integer educationalModuleGroup;

    @Max(value = 99)
    @Column(name = "educational_module_hour")
    private Integer educationalModuleHour;

    @Column(name = "time_passed")
    private ZonedDateTime timePassed;

    @Column(name = "credit")
    private ZonedDateTime credit;

    @Size(max = 50)
    @Column(name = "create_user_login", length = 50)
    private String createUserLogin;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Size(max = 50)
    @Column(name = "modify_user_login", length = 50)
    private String modifyUserLogin;

    @Column(name = "modify_date")
    private ZonedDateTime modifyDate;

    @NotNull
    @Column(name = "archived", nullable = false)
    private Boolean archived;

    @Size(max = 50)
    @Column(name = "archived_user_login", length = 50)
    private String archivedUserLogin;

    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Lob
    @Column(name = "conversation")
    private String conversation;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Size(max = 50)
    @Column(name = "change_status_user_login", length = 50)
    private String changeStatusUserLogin;

    @Size(max = 4096)
    @Column(name = "goals_text", length = 4096)
    private String goalsText;

    @Size(max = 4096)
    @Column(name = "teachers_text", length = 4096)
    private String teachersText;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @Column(name = "has_important_message")
    private Boolean hasImportantMessage;

    @Size(max = 4096)
    @Column(name = "restriction_description", length = 4096)
    private String restrictionDescription;

    @Column(name = "recommend_date")
    private ZonedDateTime recommendDate;

    @Size(max = 4096)
    @Column(name = "goals_behavioral_text", length = 4096)
    private String goalsBehavioralText;

    @Size(max = 4096)
    @Column(name = "needed_softwares", length = 4096)
    private String neededSoftwares;

    @Size(max = 4096)
    @Column(name = "needed_hardware", length = 4096)
    private String neededHardware;

    @Size(max = 4096)
    @Column(name = "course_contacts_terms", length = 4096)
    private String courseContactsTerms;

    @OneToMany(mappedBy = "requestEducationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalModule> educationalModules = new HashSet<>();
    @OneToMany(mappedBy = "requestEducationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Headline> headlines = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_scientific_work_group",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "scientific_work_groups_id", referencedColumnName = "id"))
    private Set<ScientificWorkGroup> scientificWorkGroups = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_document",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_educational_center",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "educational_centers_id", referencedColumnName = "id"))
    private Set<EducationalCenter> educationalCenters = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_goal",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "goals_id", referencedColumnName = "id"))
    private Set<Goal> goals = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_resource",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "resources_id", referencedColumnName = "id"))
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_teacher",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "teachers_id", referencedColumnName = "id"))
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "restriction_request_educational_module",
        joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "restrictions_id", referencedColumnName = "id"))
    private Set<Restriction> restrictions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_people_under_training",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_under_trainings_id", referencedColumnName = "id"))
    private Set<PeopleUnderTraining> peopleUnderTrainings = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_teaching_approach",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "teaching_approaches_id", referencedColumnName = "id"))
    private Set<TeachingApproach> teachingApproaches = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_effectiveness_level",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "effectiveness_levels_id", referencedColumnName = "id"))
    private Set<EffectivenessLevel> effectivenessLevels = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_effectiveness_index",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "effectiveness_indices_id", referencedColumnName = "id"))
    private Set<EffectivenessIndex> effectivenessIndices = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_educational_module_assessment_method",
               joinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "assessment_methods_id", referencedColumnName = "id"))
    private Set<AssessmentMethod> assessmentMethods = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("requestEducationalModules")
    private SecurityLevel securityLevel;

    @ManyToOne
    @JsonIgnoreProperties("requestEducationalModules")
    private SkillableLevelOfSkill skillableLevelOfSkill;

    @ManyToOne
    @JsonIgnoreProperties("requestEducationalModules")
    private EvaluationMethod evaluationMethod;

    @ManyToOne
    @JsonIgnoreProperties("requestEducationalModules")
    private Organization organization;

    @ManyToOne
    @JsonIgnoreProperties("requestEducationalModules")
    private Competency competency;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public RequestEducationalModule code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public RequestEducationalModule title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public RequestEducationalModule learningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
        return this;
    }

    public void setLearningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public Integer getLearningTimePractical() {
        return learningTimePractical;
    }

    public RequestEducationalModule learningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
        return this;
    }

    public void setLearningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    public String getVersion() {
        return version;
    }

    public RequestEducationalModule version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public RequestEducationalModule innerCode(String innerCode) {
        this.innerCode = innerCode;
        return this;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getCentralizedCode() {
        return centralizedCode;
    }

    public RequestEducationalModule centralizedCode(String centralizedCode) {
        this.centralizedCode = centralizedCode;
        return this;
    }

    public void setCentralizedCode(String centralizedCode) {
        this.centralizedCode = centralizedCode;
    }

    public String getMoreDescription() {
        return moreDescription;
    }

    public RequestEducationalModule moreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
        return this;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public RequestEducationalModule recommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
        return this;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getEducationalModuleHeadlines() {
        return educationalModuleHeadlines;
    }

    public RequestEducationalModule educationalModuleHeadlines(String educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
        return this;
    }

    public void setEducationalModuleHeadlines(String educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public RequestEducationalModule prerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
        return this;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getDrafters() {
        return drafters;
    }

    public RequestEducationalModule drafters(String drafters) {
        this.drafters = drafters;
        return this;
    }

    public void setDrafters(String drafters) {
        this.drafters = drafters;
    }

    public Integer getEducationalModuleLevel() {
        return educationalModuleLevel;
    }

    public RequestEducationalModule educationalModuleLevel(Integer educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
        return this;
    }

    public void setEducationalModuleLevel(Integer educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
    }

    public Integer getEducationalModuleGroup() {
        return educationalModuleGroup;
    }

    public RequestEducationalModule educationalModuleGroup(Integer educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
        return this;
    }

    public void setEducationalModuleGroup(Integer educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
    }

    public Integer getEducationalModuleHour() {
        return educationalModuleHour;
    }

    public RequestEducationalModule educationalModuleHour(Integer educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
        return this;
    }

    public void setEducationalModuleHour(Integer educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
    }

    public ZonedDateTime getTimePassed() {
        return timePassed;
    }

    public RequestEducationalModule timePassed(ZonedDateTime timePassed) {
        this.timePassed = timePassed;
        return this;
    }

    public void setTimePassed(ZonedDateTime timePassed) {
        this.timePassed = timePassed;
    }

    public ZonedDateTime getCredit() {
        return credit;
    }

    public RequestEducationalModule credit(ZonedDateTime credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(ZonedDateTime credit) {
        this.credit = credit;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RequestEducationalModule createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RequestEducationalModule createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RequestEducationalModule modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RequestEducationalModule modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RequestEducationalModule archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RequestEducationalModule archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RequestEducationalModule archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RequestEducationalModule status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public RequestEducationalModule conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public RequestEducationalModule requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public RequestEducationalModule changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public RequestEducationalModule goalsText(String goalsText) {
        this.goalsText = goalsText;
        return this;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getTeachersText() {
        return teachersText;
    }

    public RequestEducationalModule teachersText(String teachersText) {
        this.teachersText = teachersText;
        return this;
    }

    public void setTeachersText(String teachersText) {
        this.teachersText = teachersText;
    }

    public String getGuid() {
        return guid;
    }

    public RequestEducationalModule guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public RequestEducationalModule hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public RequestEducationalModule restrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
        return this;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public ZonedDateTime getRecommendDate() {
        return recommendDate;
    }

    public RequestEducationalModule recommendDate(ZonedDateTime recommendDate) {
        this.recommendDate = recommendDate;
        return this;
    }

    public void setRecommendDate(ZonedDateTime recommendDate) {
        this.recommendDate = recommendDate;
    }

    public String getGoalsBehavioralText() {
        return goalsBehavioralText;
    }

    public RequestEducationalModule goalsBehavioralText(String goalsBehavioralText) {
        this.goalsBehavioralText = goalsBehavioralText;
        return this;
    }

    public void setGoalsBehavioralText(String goalsBehavioralText) {
        this.goalsBehavioralText = goalsBehavioralText;
    }

    public String getNeededSoftwares() {
        return neededSoftwares;
    }

    public RequestEducationalModule neededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
        return this;
    }

    public void setNeededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public String getNeededHardware() {
        return neededHardware;
    }

    public RequestEducationalModule neededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
        return this;
    }

    public void setNeededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
    }

    public String getCourseContactsTerms() {
        return courseContactsTerms;
    }

    public RequestEducationalModule courseContactsTerms(String courseContactsTerms) {
        this.courseContactsTerms = courseContactsTerms;
        return this;
    }

    public void setCourseContactsTerms(String courseContactsTerms) {
        this.courseContactsTerms = courseContactsTerms;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public RequestEducationalModule educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public RequestEducationalModule addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.setRequestEducationalModule(this);
        return this;
    }

    public RequestEducationalModule removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.setRequestEducationalModule(null);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<Headline> getHeadlines() {
        return headlines;
    }

    public RequestEducationalModule headlines(Set<Headline> headlines) {
        this.headlines = headlines;
        return this;
    }

    public RequestEducationalModule addHeadline(Headline headline) {
        this.headlines.add(headline);
        headline.setRequestEducationalModule(this);
        return this;
    }

    public RequestEducationalModule removeHeadline(Headline headline) {
        this.headlines.remove(headline);
        headline.setRequestEducationalModule(null);
        return this;
    }

    public void setHeadlines(Set<Headline> headlines) {
        this.headlines = headlines;
    }

    public Set<ScientificWorkGroup> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public RequestEducationalModule scientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
        return this;
    }

    public RequestEducationalModule addScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.add(scientificWorkGroup);
        scientificWorkGroup.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.remove(scientificWorkGroup);
        scientificWorkGroup.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public RequestEducationalModule documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RequestEducationalModule addDocument(Document document) {
        this.documents.add(document);
        document.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeDocument(Document document) {
        this.documents.remove(document);
        document.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<EducationalCenter> getEducationalCenters() {
        return educationalCenters;
    }

    public RequestEducationalModule educationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
        return this;
    }

    public RequestEducationalModule addEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.add(educationalCenter);
        educationalCenter.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.remove(educationalCenter);
        educationalCenter.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setEducationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public RequestEducationalModule goals(Set<Goal> goals) {
        this.goals = goals;
        return this;
    }

    public RequestEducationalModule addGoal(Goal goal) {
        this.goals.add(goal);
        goal.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeGoal(Goal goal) {
        this.goals.remove(goal);
        goal.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public RequestEducationalModule resources(Set<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public RequestEducationalModule addResource(Resource resource) {
        this.resources.add(resource);
        resource.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeResource(Resource resource) {
        this.resources.remove(resource);
        resource.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public RequestEducationalModule teachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public RequestEducationalModule addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Restriction> getRestrictions() {
        return restrictions;
    }

    public RequestEducationalModule restrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public RequestEducationalModule addRestriction(Restriction restriction) {
        this.restrictions.add(restriction);
        restriction.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeRestriction(Restriction restriction) {
        this.restrictions.remove(restriction);
        restriction.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setRestrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public Set<PeopleUnderTraining> getPeopleUnderTrainings() {
        return peopleUnderTrainings;
    }

    public RequestEducationalModule peopleUnderTrainings(Set<PeopleUnderTraining> peopleUnderTrainings) {
        this.peopleUnderTrainings = peopleUnderTrainings;
        return this;
    }

    public RequestEducationalModule addPeopleUnderTraining(PeopleUnderTraining peopleUnderTraining) {
        this.peopleUnderTrainings.add(peopleUnderTraining);
        peopleUnderTraining.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removePeopleUnderTraining(PeopleUnderTraining peopleUnderTraining) {
        this.peopleUnderTrainings.remove(peopleUnderTraining);
        peopleUnderTraining.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setPeopleUnderTrainings(Set<PeopleUnderTraining> peopleUnderTrainings) {
        this.peopleUnderTrainings = peopleUnderTrainings;
    }

    public Set<TeachingApproach> getTeachingApproaches() {
        return teachingApproaches;
    }

    public RequestEducationalModule teachingApproaches(Set<TeachingApproach> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
        return this;
    }

    public RequestEducationalModule addTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproaches.add(teachingApproach);
        teachingApproach.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproaches.remove(teachingApproach);
        teachingApproach.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setTeachingApproaches(Set<TeachingApproach> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
    }

    public Set<EffectivenessLevel> getEffectivenessLevels() {
        return effectivenessLevels;
    }

    public RequestEducationalModule effectivenessLevels(Set<EffectivenessLevel> effectivenessLevels) {
        this.effectivenessLevels = effectivenessLevels;
        return this;
    }

    public RequestEducationalModule addEffectivenessLevel(EffectivenessLevel effectivenessLevel) {
        this.effectivenessLevels.add(effectivenessLevel);
        effectivenessLevel.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeEffectivenessLevel(EffectivenessLevel effectivenessLevel) {
        this.effectivenessLevels.remove(effectivenessLevel);
        effectivenessLevel.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setEffectivenessLevels(Set<EffectivenessLevel> effectivenessLevels) {
        this.effectivenessLevels = effectivenessLevels;
    }

    public Set<EffectivenessIndex> getEffectivenessIndices() {
        return effectivenessIndices;
    }

    public RequestEducationalModule effectivenessIndices(Set<EffectivenessIndex> effectivenessIndices) {
        this.effectivenessIndices = effectivenessIndices;
        return this;
    }

    public RequestEducationalModule addEffectivenessIndex(EffectivenessIndex effectivenessIndex) {
        this.effectivenessIndices.add(effectivenessIndex);
        effectivenessIndex.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeEffectivenessIndex(EffectivenessIndex effectivenessIndex) {
        this.effectivenessIndices.remove(effectivenessIndex);
        effectivenessIndex.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setEffectivenessIndices(Set<EffectivenessIndex> effectivenessIndices) {
        this.effectivenessIndices = effectivenessIndices;
    }

    public Set<AssessmentMethod> getAssessmentMethods() {
        return assessmentMethods;
    }

    public RequestEducationalModule assessmentMethods(Set<AssessmentMethod> assessmentMethods) {
        this.assessmentMethods = assessmentMethods;
        return this;
    }

    public RequestEducationalModule addAssessmentMethod(AssessmentMethod assessmentMethod) {
        this.assessmentMethods.add(assessmentMethod);
        assessmentMethod.getRequestEducationalModules().add(this);
        return this;
    }

    public RequestEducationalModule removeAssessmentMethod(AssessmentMethod assessmentMethod) {
        this.assessmentMethods.remove(assessmentMethod);
        assessmentMethod.getRequestEducationalModules().remove(this);
        return this;
    }

    public void setAssessmentMethods(Set<AssessmentMethod> assessmentMethods) {
        this.assessmentMethods = assessmentMethods;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public RequestEducationalModule securityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
        return this;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public SkillableLevelOfSkill getSkillableLevelOfSkill() {
        return skillableLevelOfSkill;
    }

    public RequestEducationalModule skillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkill = skillableLevelOfSkill;
        return this;
    }

    public void setSkillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkill = skillableLevelOfSkill;
    }

    public EvaluationMethod getEvaluationMethod() {
        return evaluationMethod;
    }

    public RequestEducationalModule evaluationMethod(EvaluationMethod evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
        return this;
    }

    public void setEvaluationMethod(EvaluationMethod evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public Organization getOrganization() {
        return organization;
    }

    public RequestEducationalModule organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Competency getCompetency() {
        return competency;
    }

    public RequestEducationalModule competency(Competency competency) {
        this.competency = competency;
        return this;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestEducationalModule requestEducationalModule = (RequestEducationalModule) o;
        if (requestEducationalModule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestEducationalModule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestEducationalModule{" +
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
            "}";
    }
}
