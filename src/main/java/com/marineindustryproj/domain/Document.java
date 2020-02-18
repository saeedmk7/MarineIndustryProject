package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    
    @Lob
    @Column(name = "file_doc", nullable = false)
    private String fileDoc;

    @Column(name = "file_doc_content_type", nullable = false)
    private String fileDocContentType;

    @Size(max = 50)
    @Column(name = "create_user_login", length = 50)
    private String createUserLogin;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Person> people = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Job> jobs = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalCenter> educationalCenters = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RunPhase> runPhases = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Announcement> announcements = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<UsersRequest> usersRequests = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestNiazsanjiFardi> requestNiazsanjiFardis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Instruction> instructions = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<InvestToGroupTransaction> investToGroupTransactions = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<MediaAwarenessReport> mediaAwarenessReports = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<PreJobNiazsanji> preJobNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<JobNiazsanji> jobNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<NiazsanjiOther> niazsanjiOthers = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestOtherNiazsanji> requestOtherNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Soldier> soldiers = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<SoldierTrainingReport> soldierTrainingReports = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EvaluateCriteriaData> evaluateCriteriaData = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Document title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public Document fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getFileDocContentType() {
        return fileDocContentType;
    }

    public Document fileDocContentType(String fileDocContentType) {
        this.fileDocContentType = fileDocContentType;
        return this;
    }

    public void setFileDocContentType(String fileDocContentType) {
        this.fileDocContentType = fileDocContentType;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Document createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Document createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Document people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public Document addPerson(Person person) {
        this.people.add(person);
        person.getDocuments().add(this);
        return this;
    }

    public Document removePerson(Person person) {
        this.people.remove(person);
        person.getDocuments().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Document teachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Document addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getDocuments().add(this);
        return this;
    }

    public Document removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getDocuments().remove(this);
        return this;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public Document jobs(Set<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Document addJob(Job job) {
        this.jobs.add(job);
        job.getDocuments().add(this);
        return this;
    }

    public Document removeJob(Job job) {
        this.jobs.remove(job);
        job.getDocuments().remove(this);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public Document educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public Document addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getDocuments().add(this);
        return this;
    }

    public Document removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getDocuments().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public Document requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public Document addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getDocuments().add(this);
        return this;
    }

    public Document removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getDocuments().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
    }

    public Set<EducationalCenter> getEducationalCenters() {
        return educationalCenters;
    }

    public Document educationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
        return this;
    }

    public Document addEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.add(educationalCenter);
        educationalCenter.getDocuments().add(this);
        return this;
    }

    public Document removeEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenters.remove(educationalCenter);
        educationalCenter.getDocuments().remove(this);
        return this;
    }

    public void setEducationalCenters(Set<EducationalCenter> educationalCenters) {
        this.educationalCenters = educationalCenters;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Document resources(Set<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public Document addResource(Resource resource) {
        this.resources.add(resource);
        resource.getDocuments().add(this);
        return this;
    }

    public Document removeResource(Resource resource) {
        this.resources.remove(resource);
        resource.getDocuments().remove(this);
        return this;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public Document requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public Document addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public Document finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public Document addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public Document finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public Document addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.getDocuments().add(this);
        return this;
    }

    public Document removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.getDocuments().remove(this);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public Document designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public Document addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.getDocuments().add(this);
        return this;
    }

    public Document removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.getDocuments().remove(this);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<RunPhase> getRunPhases() {
        return runPhases;
    }

    public Document runPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
        return this;
    }

    public Document addRunPhase(RunPhase runPhase) {
        this.runPhases.add(runPhase);
        runPhase.getDocuments().add(this);
        return this;
    }

    public Document removeRunPhase(RunPhase runPhase) {
        this.runPhases.remove(runPhase);
        runPhase.getDocuments().remove(this);
        return this;
    }

    public void setRunPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public Document announcements(Set<Announcement> announcements) {
        this.announcements = announcements;
        return this;
    }

    public Document addAnnouncement(Announcement announcement) {
        this.announcements.add(announcement);
        announcement.getDocuments().add(this);
        return this;
    }

    public Document removeAnnouncement(Announcement announcement) {
        this.announcements.remove(announcement);
        announcement.getDocuments().remove(this);
        return this;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Set<UsersRequest> getUsersRequests() {
        return usersRequests;
    }

    public Document usersRequests(Set<UsersRequest> usersRequests) {
        this.usersRequests = usersRequests;
        return this;
    }

    public Document addUsersRequest(UsersRequest usersRequest) {
        this.usersRequests.add(usersRequest);
        usersRequest.getDocuments().add(this);
        return this;
    }

    public Document removeUsersRequest(UsersRequest usersRequest) {
        this.usersRequests.remove(usersRequest);
        usersRequest.getDocuments().remove(this);
        return this;
    }

    public void setUsersRequests(Set<UsersRequest> usersRequests) {
        this.usersRequests = usersRequests;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public Document niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public Document addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.getDocuments().add(this);
        return this;
    }

    public Document removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.getDocuments().remove(this);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getRequestNiazsanjiFardis() {
        return requestNiazsanjiFardis;
    }

    public Document requestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public Document addRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.getDocuments().add(this);
        return this;
    }

    public Document removeRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.getDocuments().remove(this);
        return this;
    }

    public void setRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<Instruction> getInstructions() {
        return instructions;
    }

    public Document instructions(Set<Instruction> instructions) {
        this.instructions = instructions;
        return this;
    }

    public Document addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
        instruction.getDocuments().add(this);
        return this;
    }

    public Document removeInstruction(Instruction instruction) {
        this.instructions.remove(instruction);
        instruction.getDocuments().remove(this);
        return this;
    }

    public void setInstructions(Set<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Set<InvestToGroupTransaction> getInvestToGroupTransactions() {
        return investToGroupTransactions;
    }

    public Document investToGroupTransactions(Set<InvestToGroupTransaction> investToGroupTransactions) {
        this.investToGroupTransactions = investToGroupTransactions;
        return this;
    }

    public Document addInvestToGroupTransaction(InvestToGroupTransaction investToGroupTransaction) {
        this.investToGroupTransactions.add(investToGroupTransaction);
        investToGroupTransaction.getDocuments().add(this);
        return this;
    }

    public Document removeInvestToGroupTransaction(InvestToGroupTransaction investToGroupTransaction) {
        this.investToGroupTransactions.remove(investToGroupTransaction);
        investToGroupTransaction.getDocuments().remove(this);
        return this;
    }

    public void setInvestToGroupTransactions(Set<InvestToGroupTransaction> investToGroupTransactions) {
        this.investToGroupTransactions = investToGroupTransactions;
    }

    public Set<MediaAwarenessReport> getMediaAwarenessReports() {
        return mediaAwarenessReports;
    }

    public Document mediaAwarenessReports(Set<MediaAwarenessReport> mediaAwarenessReports) {
        this.mediaAwarenessReports = mediaAwarenessReports;
        return this;
    }

    public Document addMediaAwarenessReport(MediaAwarenessReport mediaAwarenessReport) {
        this.mediaAwarenessReports.add(mediaAwarenessReport);
        mediaAwarenessReport.getDocuments().add(this);
        return this;
    }

    public Document removeMediaAwarenessReport(MediaAwarenessReport mediaAwarenessReport) {
        this.mediaAwarenessReports.remove(mediaAwarenessReport);
        mediaAwarenessReport.getDocuments().remove(this);
        return this;
    }

    public void setMediaAwarenessReports(Set<MediaAwarenessReport> mediaAwarenessReports) {
        this.mediaAwarenessReports = mediaAwarenessReports;
    }

    public Set<PreJobNiazsanji> getPreJobNiazsanjis() {
        return preJobNiazsanjis;
    }

    public Document preJobNiazsanjis(Set<PreJobNiazsanji> preJobNiazsanjis) {
        this.preJobNiazsanjis = preJobNiazsanjis;
        return this;
    }

    public Document addPreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanjis.add(preJobNiazsanji);
        preJobNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removePreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanjis.remove(preJobNiazsanji);
        preJobNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setPreJobNiazsanjis(Set<PreJobNiazsanji> preJobNiazsanjis) {
        this.preJobNiazsanjis = preJobNiazsanjis;
    }

    public Set<JobNiazsanji> getJobNiazsanjis() {
        return jobNiazsanjis;
    }

    public Document jobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
        return this;
    }

    public Document addJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.add(jobNiazsanji);
        jobNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removeJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.remove(jobNiazsanji);
        jobNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setJobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
    }

    public Set<NiazsanjiOther> getNiazsanjiOthers() {
        return niazsanjiOthers;
    }

    public Document niazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
        return this;
    }

    public Document addNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.add(niazsanjiOther);
        niazsanjiOther.getDocuments().add(this);
        return this;
    }

    public Document removeNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.remove(niazsanjiOther);
        niazsanjiOther.getDocuments().remove(this);
        return this;
    }

    public void setNiazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
    }

    public Set<RequestOtherNiazsanji> getRequestOtherNiazsanjis() {
        return requestOtherNiazsanjis;
    }

    public Document requestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
        return this;
    }

    public Document addRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.add(requestOtherNiazsanji);
        requestOtherNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removeRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.remove(requestOtherNiazsanji);
        requestOtherNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setRequestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public Document prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public Document addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.getDocuments().add(this);
        return this;
    }

    public Document removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.getDocuments().remove(this);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
    }

    public Set<Soldier> getSoldiers() {
        return soldiers;
    }

    public Document soldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
        return this;
    }

    public Document addSoldier(Soldier soldier) {
        this.soldiers.add(soldier);
        soldier.getDocuments().add(this);
        return this;
    }

    public Document removeSoldier(Soldier soldier) {
        this.soldiers.remove(soldier);
        soldier.getDocuments().remove(this);
        return this;
    }

    public void setSoldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public Set<SoldierTrainingReport> getSoldierTrainingReports() {
        return soldierTrainingReports;
    }

    public Document soldierTrainingReports(Set<SoldierTrainingReport> soldierTrainingReports) {
        this.soldierTrainingReports = soldierTrainingReports;
        return this;
    }

    public Document addSoldierTrainingReport(SoldierTrainingReport soldierTrainingReport) {
        this.soldierTrainingReports.add(soldierTrainingReport);
        soldierTrainingReport.getDocuments().add(this);
        return this;
    }

    public Document removeSoldierTrainingReport(SoldierTrainingReport soldierTrainingReport) {
        this.soldierTrainingReports.remove(soldierTrainingReport);
        soldierTrainingReport.getDocuments().remove(this);
        return this;
    }

    public void setSoldierTrainingReports(Set<SoldierTrainingReport> soldierTrainingReports) {
        this.soldierTrainingReports = soldierTrainingReports;
    }

    public Set<EvaluateCriteriaTraining> getEvaluateCriteriaTrainings() {
        return evaluateCriteriaTrainings;
    }

    public Document evaluateCriteriaTrainings(Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings) {
        this.evaluateCriteriaTrainings = evaluateCriteriaTrainings;
        return this;
    }

    public Document addEvaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTrainings.add(evaluateCriteriaTraining);
        evaluateCriteriaTraining.getDocuments().add(this);
        return this;
    }

    public Document removeEvaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTrainings.remove(evaluateCriteriaTraining);
        evaluateCriteriaTraining.getDocuments().remove(this);
        return this;
    }

    public void setEvaluateCriteriaTrainings(Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings) {
        this.evaluateCriteriaTrainings = evaluateCriteriaTrainings;
    }

    public Set<EvaluateCriteriaData> getEvaluateCriteriaData() {
        return evaluateCriteriaData;
    }

    public Document evaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
        return this;
    }

    public Document addEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.add(evaluateCriteriaData);
        evaluateCriteriaData.getDocuments().add(this);
        return this;
    }

    public Document removeEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.remove(evaluateCriteriaData);
        evaluateCriteriaData.getDocuments().remove(this);
        return this;
    }

    public void setEvaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
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
        Document document = (Document) o;
        if (document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            ", fileDocContentType='" + getFileDocContentType() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
