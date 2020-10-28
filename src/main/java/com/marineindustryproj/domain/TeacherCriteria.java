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
 * A TeacherCriteria.
 */
@Entity
@Table(name = "teacher_criteria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TeacherCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1024)
    @Column(name = "mi_group", length = 1024)
    private String group;

    @NotNull
    @Size(max = 1024)
    @Column(name = "title", length = 1024, nullable = false)
    private String title;

    @NotNull
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "second_weight")
    private Integer secondWeight;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
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

    @OneToMany(mappedBy = "teacherCriteria")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeacherGradeScore> teacherGradeScores = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("teacherCriteria")
    private TeacherCriteriaGroup teacherCriteriaGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public TeacherCriteria group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public TeacherCriteria title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public TeacherCriteria displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public TeacherCriteria weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSecondWeight() {
        return secondWeight;
    }

    public TeacherCriteria secondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
        return this;
    }

    public void setSecondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
    }

    public String getDescription() {
        return description;
    }

    public TeacherCriteria description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public TeacherCriteria peopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getCode() {
        return code;
    }

    public TeacherCriteria code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public TeacherCriteria createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public TeacherCriteria createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public TeacherCriteria modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public TeacherCriteria modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<TeacherGradeScore> getTeacherGradeScores() {
        return teacherGradeScores;
    }

    public TeacherCriteria teacherGradeScores(Set<TeacherGradeScore> teacherGradeScores) {
        this.teacherGradeScores = teacherGradeScores;
        return this;
    }

    public TeacherCriteria addTeacherGradeScore(TeacherGradeScore teacherGradeScore) {
        this.teacherGradeScores.add(teacherGradeScore);
        teacherGradeScore.setTeacherCriteria(this);
        return this;
    }

    public TeacherCriteria removeTeacherGradeScore(TeacherGradeScore teacherGradeScore) {
        this.teacherGradeScores.remove(teacherGradeScore);
        teacherGradeScore.setTeacherCriteria(null);
        return this;
    }

    public void setTeacherGradeScores(Set<TeacherGradeScore> teacherGradeScores) {
        this.teacherGradeScores = teacherGradeScores;
    }

    public TeacherCriteriaGroup getTeacherCriteriaGroup() {
        return teacherCriteriaGroup;
    }

    public TeacherCriteria teacherCriteriaGroup(TeacherCriteriaGroup teacherCriteriaGroup) {
        this.teacherCriteriaGroup = teacherCriteriaGroup;
        return this;
    }

    public void setTeacherCriteriaGroup(TeacherCriteriaGroup teacherCriteriaGroup) {
        this.teacherCriteriaGroup = teacherCriteriaGroup;
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
        TeacherCriteria teacherCriteria = (TeacherCriteria) o;
        if (teacherCriteria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherCriteria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherCriteria{" +
            "id=" + getId() +
            ", group='" + getGroup() + "'" +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", secondWeight=" + getSecondWeight() +
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
