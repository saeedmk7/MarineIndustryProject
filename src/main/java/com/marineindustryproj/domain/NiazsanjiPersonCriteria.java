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

import com.marineindustryproj.domain.enumeration.CriterionType;

/**
 * A NiazsanjiPersonCriteria.
 */
@Entity
@Table(name = "niazsanji_person_criteria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NiazsanjiPersonCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "criterion_type", nullable = false)
    private CriterionType criterionType;

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

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "color_text")
    private String colorText;

    @OneToMany(mappedBy = "niazsanjiPersonCriteria")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiPersonGradeScore> niazsanjiPersonGradeScores = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CriterionType getCriterionType() {
        return criterionType;
    }

    public NiazsanjiPersonCriteria criterionType(CriterionType criterionType) {
        this.criterionType = criterionType;
        return this;
    }

    public void setCriterionType(CriterionType criterionType) {
        this.criterionType = criterionType;
    }

    public String getTitle() {
        return title;
    }

    public NiazsanjiPersonCriteria title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public NiazsanjiPersonCriteria displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public NiazsanjiPersonCriteria weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSecondWeight() {
        return secondWeight;
    }

    public NiazsanjiPersonCriteria secondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
        return this;
    }

    public void setSecondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
    }

    public String getDescription() {
        return description;
    }

    public NiazsanjiPersonCriteria description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public NiazsanjiPersonCriteria createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NiazsanjiPersonCriteria createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public NiazsanjiPersonCriteria modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public NiazsanjiPersonCriteria modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public NiazsanjiPersonCriteria backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getColorText() {
        return colorText;
    }

    public NiazsanjiPersonCriteria colorText(String colorText) {
        this.colorText = colorText;
        return this;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public Set<NiazsanjiPersonGradeScore> getNiazsanjiPersonGradeScores() {
        return niazsanjiPersonGradeScores;
    }

    public NiazsanjiPersonCriteria niazsanjiPersonGradeScores(Set<NiazsanjiPersonGradeScore> niazsanjiPersonGradeScores) {
        this.niazsanjiPersonGradeScores = niazsanjiPersonGradeScores;
        return this;
    }

    public NiazsanjiPersonCriteria addNiazsanjiPersonGradeScore(NiazsanjiPersonGradeScore niazsanjiPersonGradeScore) {
        this.niazsanjiPersonGradeScores.add(niazsanjiPersonGradeScore);
        niazsanjiPersonGradeScore.setNiazsanjiPersonCriteria(this);
        return this;
    }

    public NiazsanjiPersonCriteria removeNiazsanjiPersonGradeScore(NiazsanjiPersonGradeScore niazsanjiPersonGradeScore) {
        this.niazsanjiPersonGradeScores.remove(niazsanjiPersonGradeScore);
        niazsanjiPersonGradeScore.setNiazsanjiPersonCriteria(null);
        return this;
    }

    public void setNiazsanjiPersonGradeScores(Set<NiazsanjiPersonGradeScore> niazsanjiPersonGradeScores) {
        this.niazsanjiPersonGradeScores = niazsanjiPersonGradeScores;
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
        NiazsanjiPersonCriteria niazsanjiPersonCriteria = (NiazsanjiPersonCriteria) o;
        if (niazsanjiPersonCriteria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiPersonCriteria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiPersonCriteria{" +
            "id=" + getId() +
            ", criterionType='" + getCriterionType() + "'" +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", secondWeight=" + getSecondWeight() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", colorText='" + getColorText() + "'" +
            "}";
    }
}
