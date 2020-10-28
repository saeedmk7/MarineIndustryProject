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
 * A EducationalCenterCriteria.
 */
@Entity
@Table(name = "educational_center_criteria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationalCenterCriteria implements Serializable {

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

    @OneToMany(mappedBy = "educationalCenterCriteria")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalCenterGradeScore> educationalCenterGradeScores = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("educationalCenterCriteria")
    private EducationalCenterGroup educationalCenterGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public EducationalCenterCriteria group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public EducationalCenterCriteria title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public EducationalCenterCriteria displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public EducationalCenterCriteria weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public EducationalCenterCriteria description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public EducationalCenterCriteria peopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getCode() {
        return code;
    }

    public EducationalCenterCriteria code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EducationalCenterCriteria createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EducationalCenterCriteria createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EducationalCenterCriteria modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EducationalCenterCriteria modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<EducationalCenterGradeScore> getEducationalCenterGradeScores() {
        return educationalCenterGradeScores;
    }

    public EducationalCenterCriteria educationalCenterGradeScores(Set<EducationalCenterGradeScore> educationalCenterGradeScores) {
        this.educationalCenterGradeScores = educationalCenterGradeScores;
        return this;
    }

    public EducationalCenterCriteria addEducationalCenterGradeScore(EducationalCenterGradeScore educationalCenterGradeScore) {
        this.educationalCenterGradeScores.add(educationalCenterGradeScore);
        educationalCenterGradeScore.setEducationalCenterCriteria(this);
        return this;
    }

    public EducationalCenterCriteria removeEducationalCenterGradeScore(EducationalCenterGradeScore educationalCenterGradeScore) {
        this.educationalCenterGradeScores.remove(educationalCenterGradeScore);
        educationalCenterGradeScore.setEducationalCenterCriteria(null);
        return this;
    }

    public void setEducationalCenterGradeScores(Set<EducationalCenterGradeScore> educationalCenterGradeScores) {
        this.educationalCenterGradeScores = educationalCenterGradeScores;
    }

    public EducationalCenterGroup getEducationalCenterGroup() {
        return educationalCenterGroup;
    }

    public EducationalCenterCriteria educationalCenterGroup(EducationalCenterGroup educationalCenterGroup) {
        this.educationalCenterGroup = educationalCenterGroup;
        return this;
    }

    public void setEducationalCenterGroup(EducationalCenterGroup educationalCenterGroup) {
        this.educationalCenterGroup = educationalCenterGroup;
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
        EducationalCenterCriteria educationalCenterCriteria = (EducationalCenterCriteria) o;
        if (educationalCenterCriteria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterCriteria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterCriteria{" +
            "id=" + getId() +
            ", group='" + getGroup() + "'" +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
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
