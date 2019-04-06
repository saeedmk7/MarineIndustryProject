package com.marineindustryproj.domain;

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
 * A NiazsanjiGroup.
 */
@Entity
@Table(name = "niazsanji_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NiazsanjiGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "editor_person", length = 100, nullable = false)
    private String editorPerson;

    @Column(name = "review_date")
    private ZonedDateTime reviewDate;

    @Column(name = "schedule_date")
    private ZonedDateTime scheduleDate;

    @Size(max = 50)
    @Column(name = "first_three_job_code", length = 50)
    private String firstThreeJobCode;

    @Column(name = "price_cost")
    private Integer priceCost;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "niazsanji_group_job",
               joinColumns = @JoinColumn(name = "niazsanji_groups_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "jobs_id", referencedColumnName = "id"))
    private Set<Job> jobs = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "niazsanji_group_educational_module",
               joinColumns = @JoinColumn(name = "niazsanji_groups_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"))
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("niazsanjiGroups")
    private ScientificWorkGroup scientificWorkGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEditorPerson() {
        return editorPerson;
    }

    public NiazsanjiGroup editorPerson(String editorPerson) {
        this.editorPerson = editorPerson;
        return this;
    }

    public void setEditorPerson(String editorPerson) {
        this.editorPerson = editorPerson;
    }

    public ZonedDateTime getReviewDate() {
        return reviewDate;
    }

    public NiazsanjiGroup reviewDate(ZonedDateTime reviewDate) {
        this.reviewDate = reviewDate;
        return this;
    }

    public void setReviewDate(ZonedDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ZonedDateTime getScheduleDate() {
        return scheduleDate;
    }

    public NiazsanjiGroup scheduleDate(ZonedDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
        return this;
    }

    public void setScheduleDate(ZonedDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getFirstThreeJobCode() {
        return firstThreeJobCode;
    }

    public NiazsanjiGroup firstThreeJobCode(String firstThreeJobCode) {
        this.firstThreeJobCode = firstThreeJobCode;
        return this;
    }

    public void setFirstThreeJobCode(String firstThreeJobCode) {
        this.firstThreeJobCode = firstThreeJobCode;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public NiazsanjiGroup priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public NiazsanjiGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public NiazsanjiGroup createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NiazsanjiGroup createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public NiazsanjiGroup modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public NiazsanjiGroup modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public NiazsanjiGroup archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public NiazsanjiGroup archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public NiazsanjiGroup archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public NiazsanjiGroup status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public NiazsanjiGroup jobs(Set<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public NiazsanjiGroup addJob(Job job) {
        this.jobs.add(job);
        job.getNiazsanjiGroups().add(this);
        return this;
    }

    public NiazsanjiGroup removeJob(Job job) {
        this.jobs.remove(job);
        job.getNiazsanjiGroups().remove(this);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public NiazsanjiGroup educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public NiazsanjiGroup addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getNiazsanjiGroups().add(this);
        return this;
    }

    public NiazsanjiGroup removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getNiazsanjiGroups().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public ScientificWorkGroup getScientificWorkGroup() {
        return scientificWorkGroup;
    }

    public NiazsanjiGroup scientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroup = scientificWorkGroup;
        return this;
    }

    public void setScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroup = scientificWorkGroup;
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
        NiazsanjiGroup niazsanjiGroup = (NiazsanjiGroup) o;
        if (niazsanjiGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiGroup{" +
            "id=" + getId() +
            ", editorPerson='" + getEditorPerson() + "'" +
            ", reviewDate='" + getReviewDate() + "'" +
            ", scheduleDate='" + getScheduleDate() + "'" +
            ", firstThreeJobCode='" + getFirstThreeJobCode() + "'" +
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
            "}";
    }
}
