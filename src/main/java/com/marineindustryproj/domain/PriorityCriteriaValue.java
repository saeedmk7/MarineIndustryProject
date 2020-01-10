package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PriorityCriteriaValue.
 */
@Entity
@Table(name = "priority_criteria_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PriorityCriteriaValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

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

    @ManyToOne
    @JsonIgnoreProperties("priorityCriteriaValues")
    private PriorityCriteria priorityCriteria;

    @ManyToOne
    @JsonIgnoreProperties("priorityCriteriaValues")
    private PreJobNiazsanjiCompetency preJobNiazsanjiCompetency;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public PriorityCriteriaValue score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public PriorityCriteriaValue description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PriorityCriteriaValue createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PriorityCriteriaValue createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PriorityCriteriaValue modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PriorityCriteriaValue modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public PriorityCriteria getPriorityCriteria() {
        return priorityCriteria;
    }

    public PriorityCriteriaValue priorityCriteria(PriorityCriteria priorityCriteria) {
        this.priorityCriteria = priorityCriteria;
        return this;
    }

    public void setPriorityCriteria(PriorityCriteria priorityCriteria) {
        this.priorityCriteria = priorityCriteria;
    }

    public PreJobNiazsanjiCompetency getPreJobNiazsanjiCompetency() {
        return preJobNiazsanjiCompetency;
    }

    public PriorityCriteriaValue preJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetency = preJobNiazsanjiCompetency;
        return this;
    }

    public void setPreJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetency = preJobNiazsanjiCompetency;
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
        PriorityCriteriaValue priorityCriteriaValue = (PriorityCriteriaValue) o;
        if (priorityCriteriaValue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), priorityCriteriaValue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PriorityCriteriaValue{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
