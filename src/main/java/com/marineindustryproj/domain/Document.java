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
