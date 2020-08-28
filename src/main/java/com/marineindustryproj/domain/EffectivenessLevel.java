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
 * A EffectivenessLevel.
 */
@Entity
@Table(name = "effectiveness_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EffectivenessLevel implements Serializable {

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

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
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

    @OneToMany(mappedBy = "effectivenessLevel")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();
    @ManyToMany(mappedBy = "effectivenessLevels")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "effectivenessLevels")
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

    public EffectivenessLevel title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public EffectivenessLevel code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public EffectivenessLevel description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EffectivenessLevel createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EffectivenessLevel createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EffectivenessLevel modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EffectivenessLevel modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public EffectivenessLevel designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public EffectivenessLevel addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setEffectivenessLevel(this);
        return this;
    }

    public EffectivenessLevel removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setEffectivenessLevel(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public EffectivenessLevel educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public EffectivenessLevel addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getEffectivenessLevels().add(this);
        return this;
    }

    public EffectivenessLevel removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getEffectivenessLevels().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public EffectivenessLevel requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public EffectivenessLevel addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getEffectivenessLevels().add(this);
        return this;
    }

    public EffectivenessLevel removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getEffectivenessLevels().remove(this);
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
        EffectivenessLevel effectivenessLevel = (EffectivenessLevel) o;
        if (effectivenessLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), effectivenessLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EffectivenessLevel{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
