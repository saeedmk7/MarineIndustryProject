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
 * A MahiatCourse.
 */
@Entity
@Table(name = "mahiat_course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MahiatCourse implements Serializable {

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

    @OneToMany(mappedBy = "mahiatCourse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();
    @OneToMany(mappedBy = "mahiatCourse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelThreeCriteria> levelThreeCriteria = new HashSet<>();
    @OneToMany(mappedBy = "mahiatCourse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public MahiatCourse title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public MahiatCourse code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public MahiatCourse description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MahiatCourse createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MahiatCourse createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MahiatCourse modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MahiatCourse modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public MahiatCourse finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public MahiatCourse addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.setMahiatCourse(this);
        return this;
    }

    public MahiatCourse removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.setMahiatCourse(null);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<LevelThreeCriteria> getLevelThreeCriteria() {
        return levelThreeCriteria;
    }

    public MahiatCourse levelThreeCriteria(Set<LevelThreeCriteria> levelThreeCriteria) {
        this.levelThreeCriteria = levelThreeCriteria;
        return this;
    }

    public MahiatCourse addLevelThreeCriteria(LevelThreeCriteria levelThreeCriteria) {
        this.levelThreeCriteria.add(levelThreeCriteria);
        levelThreeCriteria.setMahiatCourse(this);
        return this;
    }

    public MahiatCourse removeLevelThreeCriteria(LevelThreeCriteria levelThreeCriteria) {
        this.levelThreeCriteria.remove(levelThreeCriteria);
        levelThreeCriteria.setMahiatCourse(null);
        return this;
    }

    public void setLevelThreeCriteria(Set<LevelThreeCriteria> levelThreeCriteria) {
        this.levelThreeCriteria = levelThreeCriteria;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public MahiatCourse designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public MahiatCourse addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setMahiatCourse(this);
        return this;
    }

    public MahiatCourse removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setMahiatCourse(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MahiatCourse mahiatCourse = (MahiatCourse) o;
        if (mahiatCourse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mahiatCourse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MahiatCourse{" +
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
