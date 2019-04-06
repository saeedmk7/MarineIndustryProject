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

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")*/
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotNull
    @Size(min = 6, max = 6)
    @Column(name = "job_key", length = 6, nullable = false, unique = true)
    private String jobKey;

    @Size(min = 12, max = 12)
    @Column(name = "job_code", length = 12)
    private String jobCode;

    @Size(max = 100)
    @Column(name = "first_3_job_code", length = 100)
    private String first3JobCode;

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

    @OneToMany(mappedBy = "job")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> jobPeople = new HashSet<>();
    @OneToMany(mappedBy = "practicaljob")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> practicaljobPeople = new HashSet<>();
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Job> jobs = new HashSet<>();
    @OneToMany(mappedBy = "job")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalModuleJob> educationalModuleJobs = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "job_document",
               joinColumns = @JoinColumn(name = "jobs_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("jobs")
    private Raste raste;

    @ManyToOne
    @JsonIgnoreProperties("jobs")
    private Radeh radeh;

    @ManyToOne
    @JsonIgnoreProperties("jobs")
    private JobType jobType;

    @ManyToOne
    @JsonIgnoreProperties("jobs")
    private ScientificWorkGroup scientificWorkGroup;

    @ManyToOne
    @JsonIgnoreProperties("jobs")
    private Job parent;

    @ManyToMany(mappedBy = "jobs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<MainTask> mainTasks = new HashSet<>();

    @ManyToMany(mappedBy = "jobs")
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

    public String getTitle() {
        return title;
    }

    public Job title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobKey() {
        return jobKey;
    }

    public Job jobKey(String jobKey) {
        this.jobKey = jobKey;
        return this;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobCode() {
        return jobCode;
    }

    public Job jobCode(String jobCode) {
        this.jobCode = jobCode;
        return this;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getFirst3JobCode() {
        return first3JobCode;
    }

    public Job first3JobCode(String first3JobCode) {
        this.first3JobCode = first3JobCode;
        return this;
    }

    public void setFirst3JobCode(String first3JobCode) {
        this.first3JobCode = first3JobCode;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Job createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Job createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Job modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Job modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public Job archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public Job archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public Job archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Job status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Person> getJobPeople() {
        return jobPeople;
    }

    public Job jobPeople(Set<Person> people) {
        this.jobPeople = people;
        return this;
    }

    public Job addJobPerson(Person person) {
        this.jobPeople.add(person);
        person.setJob(this);
        return this;
    }

    public Job removeJobPerson(Person person) {
        this.jobPeople.remove(person);
        person.setJob(null);
        return this;
    }

    public void setJobPeople(Set<Person> people) {
        this.jobPeople = people;
    }

    public Set<Person> getPracticaljobPeople() {
        return practicaljobPeople;
    }

    public Job practicaljobPeople(Set<Person> people) {
        this.practicaljobPeople = people;
        return this;
    }

    public Job addPracticaljobPerson(Person person) {
        this.practicaljobPeople.add(person);
        person.setPracticaljob(this);
        return this;
    }

    public Job removePracticaljobPerson(Person person) {
        this.practicaljobPeople.remove(person);
        person.setPracticaljob(null);
        return this;
    }

    public void setPracticaljobPeople(Set<Person> people) {
        this.practicaljobPeople = people;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public Job jobs(Set<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Job addJob(Job job) {
        this.jobs.add(job);
        job.setParent(this);
        return this;
    }

    public Job removeJob(Job job) {
        this.jobs.remove(job);
        job.setParent(null);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<EducationalModuleJob> getEducationalModuleJobs() {
        return educationalModuleJobs;
    }

    public Job educationalModuleJobs(Set<EducationalModuleJob> educationalModuleJobs) {
        this.educationalModuleJobs = educationalModuleJobs;
        return this;
    }

    public Job addEducationalModuleJob(EducationalModuleJob educationalModuleJob) {
        this.educationalModuleJobs.add(educationalModuleJob);
        educationalModuleJob.setJob(this);
        return this;
    }

    public Job removeEducationalModuleJob(EducationalModuleJob educationalModuleJob) {
        this.educationalModuleJobs.remove(educationalModuleJob);
        educationalModuleJob.setJob(null);
        return this;
    }

    public void setEducationalModuleJobs(Set<EducationalModuleJob> educationalModuleJobs) {
        this.educationalModuleJobs = educationalModuleJobs;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Job documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Job addDocument(Document document) {
        this.documents.add(document);
        document.getJobs().add(this);
        return this;
    }

    public Job removeDocument(Document document) {
        this.documents.remove(document);
        document.getJobs().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Raste getRaste() {
        return raste;
    }

    public Job raste(Raste raste) {
        this.raste = raste;
        return this;
    }

    public void setRaste(Raste raste) {
        this.raste = raste;
    }

    public Radeh getRadeh() {
        return radeh;
    }

    public Job radeh(Radeh radeh) {
        this.radeh = radeh;
        return this;
    }

    public void setRadeh(Radeh radeh) {
        this.radeh = radeh;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Job jobType(JobType jobType) {
        this.jobType = jobType;
        return this;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public ScientificWorkGroup getScientificWorkGroup() {
        return scientificWorkGroup;
    }

    public Job scientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroup = scientificWorkGroup;
        return this;
    }

    public void setScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroup = scientificWorkGroup;
    }

    public Job getParent() {
        return parent;
    }

    public Job parent(Job job) {
        this.parent = job;
        return this;
    }

    public void setParent(Job job) {
        this.parent = job;
    }

    public Set<MainTask> getMainTasks() {
        return mainTasks;
    }

    public Job mainTasks(Set<MainTask> mainTasks) {
        this.mainTasks = mainTasks;
        return this;
    }

    public Job addMainTask(MainTask mainTask) {
        this.mainTasks.add(mainTask);
        mainTask.getJobs().add(this);
        return this;
    }

    public Job removeMainTask(MainTask mainTask) {
        this.mainTasks.remove(mainTask);
        mainTask.getJobs().remove(this);
        return this;
    }

    public void setMainTasks(Set<MainTask> mainTasks) {
        this.mainTasks = mainTasks;
    }

    public Set<NiazsanjiGroup> getNiazsanjiGroups() {
        return niazsanjiGroups;
    }

    public Job niazsanjiGroups(Set<NiazsanjiGroup> niazsanjiGroups) {
        this.niazsanjiGroups = niazsanjiGroups;
        return this;
    }

    public Job addNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.add(niazsanjiGroup);
        niazsanjiGroup.getJobs().add(this);
        return this;
    }

    public Job removeNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.remove(niazsanjiGroup);
        niazsanjiGroup.getJobs().remove(this);
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
        Job job = (Job) o;
        if (job.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), job.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", jobKey='" + getJobKey() + "'" +
            ", jobCode='" + getJobCode() + "'" +
            ", first3JobCode='" + getFirst3JobCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
