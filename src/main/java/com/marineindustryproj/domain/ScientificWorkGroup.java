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
 * A ScientificWorkGroup.
 */
@Entity
@Table(name = "scientific_work_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ScientificWorkGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

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

    @OneToMany(mappedBy = "scientificWorkGroup")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Job> jobs = new HashSet<>();
    @OneToMany(mappedBy = "scientificWorkGroup")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiGroup> niazsanjiGroups = new HashSet<>();
    @ManyToMany(mappedBy = "scientificWorkGroups")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Person> people = new HashSet<>();

    @ManyToMany(mappedBy = "scientificWorkGroups")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "scientificWorkGroups")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();

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

    public ScientificWorkGroup title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public ScientificWorkGroup code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public ScientificWorkGroup createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ScientificWorkGroup createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public ScientificWorkGroup modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public ScientificWorkGroup modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public ScientificWorkGroup jobs(Set<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public ScientificWorkGroup addJob(Job job) {
        this.jobs.add(job);
        job.setScientificWorkGroup(this);
        return this;
    }

    public ScientificWorkGroup removeJob(Job job) {
        this.jobs.remove(job);
        job.setScientificWorkGroup(null);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<NiazsanjiGroup> getNiazsanjiGroups() {
        return niazsanjiGroups;
    }

    public ScientificWorkGroup niazsanjiGroups(Set<NiazsanjiGroup> niazsanjiGroups) {
        this.niazsanjiGroups = niazsanjiGroups;
        return this;
    }

    public ScientificWorkGroup addNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.add(niazsanjiGroup);
        niazsanjiGroup.setScientificWorkGroup(this);
        return this;
    }

    public ScientificWorkGroup removeNiazsanjiGroup(NiazsanjiGroup niazsanjiGroup) {
        this.niazsanjiGroups.remove(niazsanjiGroup);
        niazsanjiGroup.setScientificWorkGroup(null);
        return this;
    }

    public void setNiazsanjiGroups(Set<NiazsanjiGroup> niazsanjiGroups) {
        this.niazsanjiGroups = niazsanjiGroups;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public ScientificWorkGroup people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public ScientificWorkGroup addPerson(Person person) {
        this.people.add(person);
        person.getScientificWorkGroups().add(this);
        return this;
    }

    public ScientificWorkGroup removePerson(Person person) {
        this.people.remove(person);
        person.getScientificWorkGroups().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public ScientificWorkGroup educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public ScientificWorkGroup addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getScientificWorkGroups().add(this);
        return this;
    }

    public ScientificWorkGroup removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getScientificWorkGroups().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public ScientificWorkGroup requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public ScientificWorkGroup addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getScientificWorkGroups().add(this);
        return this;
    }

    public ScientificWorkGroup removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getScientificWorkGroups().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
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
        ScientificWorkGroup scientificWorkGroup = (ScientificWorkGroup) o;
        if (scientificWorkGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scientificWorkGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScientificWorkGroup{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
