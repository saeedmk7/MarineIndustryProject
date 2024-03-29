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
 * A PeopleUnderTraining.
 */
@Entity
@Table(name = "people_under_training")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PeopleUnderTraining implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 1024)
    @Column(name = "title", length = 1024, nullable = false)
    private String title;

    @NotNull
    @Size(max = 1024)
    @Column(name = "description", length = 1024, nullable = false)
    private String description;

    @Column(name = "people_count")
    private Integer peopleCount;

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

    @ManyToMany(mappedBy = "peopleUnderTrainings")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "peopleUnderTrainings")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public PeopleUnderTraining title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public PeopleUnderTraining description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public PeopleUnderTraining peopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getCode() {
        return code;
    }

    public PeopleUnderTraining code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PeopleUnderTraining createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PeopleUnderTraining createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PeopleUnderTraining modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PeopleUnderTraining modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public PeopleUnderTraining educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public PeopleUnderTraining addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getPeopleUnderTrainings().add(this);
        return this;
    }

    public PeopleUnderTraining removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getPeopleUnderTrainings().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public PeopleUnderTraining requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public PeopleUnderTraining addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getPeopleUnderTrainings().add(this);
        return this;
    }

    public PeopleUnderTraining removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getPeopleUnderTrainings().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PeopleUnderTraining peopleUnderTraining = (PeopleUnderTraining) o;
        if (peopleUnderTraining.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), peopleUnderTraining.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeopleUnderTraining{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", peopleCount=" + getPeopleCount() +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
