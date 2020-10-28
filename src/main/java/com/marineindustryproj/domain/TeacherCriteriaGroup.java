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
 * A TeacherCriteriaGroup.
 */
@Entity
@Table(name = "teacher_criteria_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TeacherCriteriaGroup implements Serializable {

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
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "weight")
    private Integer weight;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

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

    @OneToMany(mappedBy = "teacherCriteriaGroup")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeacherGrade> teacherGrades = new HashSet<>();
    @OneToMany(mappedBy = "teacherCriteriaGroup")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeacherCriteria> teacherCriteria = new HashSet<>();
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

    public TeacherCriteriaGroup title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public TeacherCriteriaGroup displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public TeacherCriteriaGroup weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public TeacherCriteriaGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public TeacherCriteriaGroup code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public TeacherCriteriaGroup createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public TeacherCriteriaGroup createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public TeacherCriteriaGroup modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public TeacherCriteriaGroup modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<TeacherGrade> getTeacherGrades() {
        return teacherGrades;
    }

    public TeacherCriteriaGroup teacherGrades(Set<TeacherGrade> teacherGrades) {
        this.teacherGrades = teacherGrades;
        return this;
    }

    public TeacherCriteriaGroup addTeacherGrade(TeacherGrade teacherGrade) {
        this.teacherGrades.add(teacherGrade);
        teacherGrade.setTeacherCriteriaGroup(this);
        return this;
    }

    public TeacherCriteriaGroup removeTeacherGrade(TeacherGrade teacherGrade) {
        this.teacherGrades.remove(teacherGrade);
        teacherGrade.setTeacherCriteriaGroup(null);
        return this;
    }

    public void setTeacherGrades(Set<TeacherGrade> teacherGrades) {
        this.teacherGrades = teacherGrades;
    }

    public Set<TeacherCriteria> getTeacherCriteria() {
        return teacherCriteria;
    }

    public TeacherCriteriaGroup teacherCriteria(Set<TeacherCriteria> teacherCriteria) {
        this.teacherCriteria = teacherCriteria;
        return this;
    }

    public TeacherCriteriaGroup addTeacherCriteria(TeacherCriteria teacherCriteria) {
        this.teacherCriteria.add(teacherCriteria);
        teacherCriteria.setTeacherCriteriaGroup(this);
        return this;
    }

    public TeacherCriteriaGroup removeTeacherCriteria(TeacherCriteria teacherCriteria) {
        this.teacherCriteria.remove(teacherCriteria);
        teacherCriteria.setTeacherCriteriaGroup(null);
        return this;
    }

    public void setTeacherCriteria(Set<TeacherCriteria> teacherCriteria) {
        this.teacherCriteria = teacherCriteria;
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
        TeacherCriteriaGroup teacherCriteriaGroup = (TeacherCriteriaGroup) o;
        if (teacherCriteriaGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherCriteriaGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherCriteriaGroup{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", description='" + getDescription() + "'" +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
