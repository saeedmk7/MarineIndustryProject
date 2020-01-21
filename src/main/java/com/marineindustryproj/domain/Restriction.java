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
 * A Restriction.
 */
@Entity
@Table(name = "restriction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Restriction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DesignNiazsanji> designNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<JobNiazsanji> jobNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<NiazsanjiOther> niazsanjiOthers = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestOtherNiazsanji> requestOtherNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();

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

    public Restriction title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Restriction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Restriction createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Restriction createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Restriction modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Restriction modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public Restriction guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public Restriction educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public Restriction addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getRestrictions().add(this);
        return this;
    }

    public Restriction removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getRestrictions().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public Restriction requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public Restriction addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getRestrictions().add(this);
        return this;
    }

    public Restriction removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getRestrictions().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public Restriction requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public Restriction addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public Restriction finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public Restriction addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public Restriction finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public Restriction addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.getRestrictions().add(this);
        return this;
    }

    public Restriction removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.getRestrictions().remove(this);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public Restriction niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public Restriction addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.getRestrictions().add(this);
        return this;
    }

    public Restriction removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.getRestrictions().remove(this);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<DesignNiazsanji> getDesignNiazsanjis() {
        return designNiazsanjis;
    }

    public Restriction designNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
        return this;
    }

    public Restriction addDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.add(designNiazsanji);
        designNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removeDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.remove(designNiazsanji);
        designNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setDesignNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
    }

    public Set<JobNiazsanji> getJobNiazsanjis() {
        return jobNiazsanjis;
    }

    public Restriction jobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
        return this;
    }

    public Restriction addJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.add(jobNiazsanji);
        jobNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removeJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.remove(jobNiazsanji);
        jobNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setJobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
    }

    public Set<NiazsanjiOther> getNiazsanjiOthers() {
        return niazsanjiOthers;
    }

    public Restriction niazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
        return this;
    }

    public Restriction addNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.add(niazsanjiOther);
        niazsanjiOther.getRestrictions().add(this);
        return this;
    }

    public Restriction removeNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.remove(niazsanjiOther);
        niazsanjiOther.getRestrictions().remove(this);
        return this;
    }

    public void setNiazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
    }

    public Set<RequestOtherNiazsanji> getRequestOtherNiazsanjis() {
        return requestOtherNiazsanjis;
    }

    public Restriction requestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
        return this;
    }

    public Restriction addRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.add(requestOtherNiazsanji);
        requestOtherNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removeRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.remove(requestOtherNiazsanji);
        requestOtherNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setRequestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public Restriction prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public Restriction addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.getRestrictions().add(this);
        return this;
    }

    public Restriction removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.getRestrictions().remove(this);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
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
        Restriction restriction = (Restriction) o;
        if (restriction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), restriction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Restriction{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
