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

    /*@ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "restriction_request_educational_module",
               joinColumns = @JoinColumn(name = "restrictions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "request_educational_modules_id", referencedColumnName = "id"))
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();*/

    /*@ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "restriction_educational_module",
               joinColumns = @JoinColumn(name = "restrictions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "educational_modules_id", referencedColumnName = "id"))
    private Set<EducationalModule> educationalModules = new HashSet<>();*/

    @ManyToMany(mappedBy = "restrictions")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "restrictions")
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
