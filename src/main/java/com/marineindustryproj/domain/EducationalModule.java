package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EducationalModule.
 */
@Entity
@Table(name = "educational_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationalModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")*/
    /*@SequenceGenerator(name = "sequenceGenerator")*/
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Size(max = 1000)
    @Column(name = "title", length = 1000, nullable = false)
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

    @Size(max = 100)
    @Column(name = "recommended_by", length = 100)
    private String recommendedBy;

    @Lob
    @Column(name = "educational_module_headlines")
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

    @Size(max = 4096)
    @Column(name = "goals_text", length = 4096)
    private String goalsText;

    @Size(max = 4096)
    @Column(name = "teachers_text", length = 4096)
    private String teachersText;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalModuleJob> educationalModuleJobs = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RunPhase> runPhases = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "approvedEducationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestNiazsanjiFardi> approvedRequestNiazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "allEducationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestNiazsanjiFardi> allRequestNiazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "educationalModule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalHistory> educationalHistories = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_scientific_work_group",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "scientific_work_groups_id", referencedColumnName = "id"))
    private Set<ScientificWorkGroup> scientificWorkGroups = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_document",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_educational_center",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "educational_centers_id", referencedColumnName = "id"))
    private Set<EducationalCenter> educationalCenters = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_goal",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "goals_id", referencedColumnName = "id"))
    private Set<Goal> goals = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_resource",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "resources_id", referencedColumnName = "id"))
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_module_teacher",
               joinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "teachers_id", referencedColumnName = "id"))
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("educationalModules")
    private RequestEducationalModule requestEducationalModule;

    @ManyToOne
    @JsonIgnoreProperties("educationalModules")
    private SecurityLevel securityLevel;

    @ManyToOne
    @JsonIgnoreProperties("educationalModules")
    private SkillableLevelOfSkill skillableLevelOfSkill;

    @ManyToOne
    @JsonIgnoreProperties("educationalModules")
    private EvaluationMethod evaluationMethod;

    @ManyToOne
    @JsonIgnoreProperties("educationalModules")
    private Organization organization;

    @ManyToMany(mappedBy = "educationalModules")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<NiazsanjiGroup> niazsanjiGroups = new HashSet<>();

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

    public EducationalModule code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public EducationalModule title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public EducationalModule learningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
        return this;
    }

    public void setLearningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public Integer getLearningTimePractical() {
        return learningTimePractical;
    }

    public EducationalModule learningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
        return this;
    }

    public void setLearningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    public String getVersion() {
        return version;
    }

    public EducationalModule version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public EducationalModule innerCode(String innerCode) {
        this.innerCode = innerCode;
        return this;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getCentralizedCode() {
        return centralizedCode;
    }

    public EducationalModule centralizedCode(String centralizedCode) {
        this.centralizedCode = centralizedCode;
        return this;
    }

    public void setCentralizedCode(String centralizedCode) {
        this.centralizedCode = centralizedCode;
    }

    public String getMoreDescription() {
        return moreDescription;
    }

    public EducationalModule moreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
        return this;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public EducationalModule recommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
        return this;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getEducationalModuleHeadlines() {
        return educationalModuleHeadlines;
    }

    public EducationalModule educationalModuleHeadlines(String educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
        return this;
    }

    public void setEducationalModuleHeadlines(String educationalModuleHeadlines) {
        this.educationalModuleHeadlines = educationalModuleHeadlines;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public EducationalModule prerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
        return this;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getDrafters() {
        return drafters;
    }

    public EducationalModule drafters(String drafters) {
        this.drafters = drafters;
        return this;
    }

    public void setDrafters(String drafters) {
        this.drafters = drafters;
    }

    public Integer getEducationalModuleLevel() {
        return educationalModuleLevel;
    }

    public EducationalModule educationalModuleLevel(Integer educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
        return this;
    }

    public void setEducationalModuleLevel(Integer educationalModuleLevel) {
        this.educationalModuleLevel = educationalModuleLevel;
    }

    public Integer getEducationalModuleGroup() {
        return educationalModuleGroup;
    }

    public EducationalModule educationalModuleGroup(Integer educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
        return this;
    }

    public void setEducationalModuleGroup(Integer educationalModuleGroup) {
        this.educationalModuleGroup = educationalModuleGroup;
    }

    public Integer getEducationalModuleHour() {
        return educationalModuleHour;
    }

    public EducationalModule educationalModuleHour(Integer educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
        return this;
    }

    public void setEducationalModuleHour(Integer educationalModuleHour) {
        this.educationalModuleHour = educationalModuleHour;
    }

    public ZonedDateTime getTimePassed() {
        return timePassed;
    }

    public EducationalModule timePassed(ZonedDateTime timePassed) {
        this.timePassed = timePassed;
        return this;
    }

    public void setTimePassed(ZonedDateTime timePassed) {
        this.timePassed = timePassed;
    }

    public ZonedDateTime getCredit() {
        return credit;
    }

    public EducationalModule credit(ZonedDateTime credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(ZonedDateTime credit) {
        this.credit = credit;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EducationalModule createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EducationalModule createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EducationalModule modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EducationalModule modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public EducationalModule archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public EducationalModule archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public EducationalModule archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public EducationalModule status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public EducationalModule goalsText(String goalsText) {
        this.goalsText = goalsText;
        return this;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getTeachersText() {
        return teachersText;
    }

    public EducationalModule teachersText(String teachersText) {
        this.teachersText = teachersText;
        return this;
    }

    public void setTeachersText(String teachersText) {
        this.teachersText = teachersText;
    }

    public String getGuid() {
        return guid;
    }

    public EducationalModule guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<EducationalModuleJob> getEducationalModuleJobs() {
        return educationalModuleJobs;
    }

    public EducationalModule educationalModuleJobs(Set<EducationalModuleJob> educationalModuleJobs) {
        this.educationalModuleJobs = educationalModuleJobs;
        return this;
    }

    public EducationalModule addEducationalModuleJob(EducationalModuleJob educationalModuleJob) {
        this.educationalModuleJobs.add(educationalModuleJob);
        educationalModuleJob.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeEducationalModuleJob(EducationalModuleJob educationalModuleJob) {
        this.educationalModuleJobs.remove(educationalModuleJob);
        educationalModuleJob.setEducationalModule(null);
        return this;
    }

    public void setEducationalModuleJobs(Set<EducationalModuleJob> educationalModuleJobs) {
        this.educationalModuleJobs = educationalModuleJobs;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public EducationalModule requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public EducationalModule addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setEducationalModule(null);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public EducationalModule finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public EducationalModule addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setEducationalModule(null);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public EducationalModule finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public EducationalModule addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.setEducationalModule(null);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public EducationalModule designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public EducationalModule addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setEducationalModule(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<RunPhase> getRunPhases() {
        return runPhases;
    }

    public EducationalModule runPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
        return this;
    }

    public EducationalModule addRunPhase(RunPhase runPhase) {
        this.runPhases.add(runPhase);
        runPhase.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeRunPhase(RunPhase runPhase) {
        this.runPhases.remove(runPhase);
        runPhase.setEducationalModule(null);
        return this;
    }

    public void setRunPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public EducationalModule niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public EducationalModule addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setEducationalModule(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getApprovedRequestNiazsanjiFardis() {
        return approvedRequestNiazsanjiFardis;
    }

    public EducationalModule approvedRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.approvedRequestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public EducationalModule addApprovedRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.approvedRequestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setApprovedEducationalModule(this);
        return this;
    }

    public EducationalModule removeApprovedRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.approvedRequestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setApprovedEducationalModule(null);
        return this;
    }

    public void setApprovedRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.approvedRequestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getAllRequestNiazsanjiFardis() {
        return allRequestNiazsanjiFardis;
    }

    public EducationalModule allRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.allRequestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public EducationalModule addAllRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.allRequestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setAllEducationalModule(this);
        return this;
    }

    public EducationalModule removeAllRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.allRequestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setAllEducationalModule(null);
        return this;
    }

    public void setAllRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.allRequestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<EducationalHistory> getEducationalHistories() {
        return educationalHistories;
    }

    public EducationalModule educationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
        return this;
    }

    public EducationalModule addEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.add(educationalHistory);
        educationalHistory.setEducationalModule(this);
        return this;
    }

    public EducationalModule removeEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.remove(educationalHistory);
        educationalHistory.setEducationalModule(null);
        return this;
    }

    public void setEducationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
    }

    public Set<ScientificWorkGroup> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public EducationalModule scientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
        return this;
    }

    public EducationalModule addScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.add(scientificWorkGroup);
        scientificWorkGroup.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.remove(scientificWorkGroup);
        scientificWorkGroup.getEducationalModules().remove(this);
        return this;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EducationalModule documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EducationalModule addDocument(Document document) {
        this.documents.add(document);
        document.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeDocument(Document document) {
        this.documents.remove(document);
        document.getEducationalModules().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<EducationalCenter> getEducationalCenters() {
        return educationalCenters;
    }

    public EducationalModule educationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
        return this;
    }

    public EducationalModule addEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.add(educationalCenter);
        educationalCenter.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.remove(educationalCenter);
        educationalCenter.getEducationalModules().remove(this);
        return this;
    }

    public void setEducationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public EducationalModule goals(Set<Goal> goals) {
        this.goals = goals;
        return this;
    }

    public EducationalModule addGoal(Goal goal) {
        this.goals.add(goal);
        goal.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeGoal(Goal goal) {
        this.goals.remove(goal);
        goal.getEducationalModules().remove(this);
        return this;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public EducationalModule resources(Set<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public EducationalModule addResource(Resource resource) {
        this.resources.add(resource);
        resource.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeResource(Resource resource) {
        this.resources.remove(resource);
        resource.getEducationalModules().remove(this);
        return this;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public EducationalModule teachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public EducationalModule addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getEducationalModules().remove(this);
        return this;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public RequestEducationalModule getRequestEducationalModule() {
        return requestEducationalModule;
    }

    public EducationalModule requestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModule = requestEducationalModule;
        return this;
    }

    public void setRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModule = requestEducationalModule;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public EducationalModule securityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
        return this;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public SkillableLevelOfSkill getSkillableLevelOfSkill() {
        return skillableLevelOfSkill;
    }

    public EducationalModule skillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkill = skillableLevelOfSkill;
        return this;
    }

    public void setSkillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkill = skillableLevelOfSkill;
    }

    public EvaluationMethod getEvaluationMethod() {
        return evaluationMethod;
    }

    public EducationalModule evaluationMethod(EvaluationMethod evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
        return this;
    }

    public void setEvaluationMethod(EvaluationMethod evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public Organization getOrganization() {
        return organization;
    }

    public EducationalModule organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<NiazsanjiGroup> getNiazsanjiGroups() {
        return niazsanjiGroups;
    }

    public EducationalModule niazsanjiGroups(Set<NiazsanjiGroup> niazsanjiGroups) {
        this.niazsanjiGroups = niazsanjiGroups;
        return this;
    }

    public EducationalModule addNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.add(niazsanjiGroup);
        niazsanjiGroup.getEducationalModules().add(this);
        return this;
    }

    public EducationalModule removeNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.remove(niazsanjiGroup);
        niazsanjiGroup.getEducationalModules().remove(this);
        return this;
    }

    public void setNiazsanjiGroups(Set<NiazsanjiGroup> niazsanjiGroups) {
        this.niazsanjiGroups = niazsanjiGroups;
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
        EducationalModule educationalModule = (EducationalModule) o;
        if (educationalModule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalModule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalModule{" +
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
            ", goalsText='" + getGoalsText() + "'" +
            ", teachersText='" + getTeachersText() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
