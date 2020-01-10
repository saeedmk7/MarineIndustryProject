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
 * A FixCompetencyDeficiency.
 */
@Entity
@Table(name = "fix_competency_deficiency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FixCompetencyDeficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 4096)
    @Column(name = "title", length = 4096, nullable = false)
    private String title;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

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

    @OneToMany(mappedBy = "fixCompetencyDeficiency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies = new HashSet<>();
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

    public FixCompetencyDeficiency title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public FixCompetencyDeficiency description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public FixCompetencyDeficiency displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FixCompetencyDeficiency createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FixCompetencyDeficiency createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FixCompetencyDeficiency modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FixCompetencyDeficiency modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<PreJobNiazsanjiCompetency> getPreJobNiazsanjiCompetencies() {
        return preJobNiazsanjiCompetencies;
    }

    public FixCompetencyDeficiency preJobNiazsanjiCompetencies(Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies) {
        this.preJobNiazsanjiCompetencies = preJobNiazsanjiCompetencies;
        return this;
    }

    public FixCompetencyDeficiency addPreJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetencies.add(preJobNiazsanjiCompetency);
        preJobNiazsanjiCompetency.setFixCompetencyDeficiency(this);
        return this;
    }

    public FixCompetencyDeficiency removePreJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetencies.remove(preJobNiazsanjiCompetency);
        preJobNiazsanjiCompetency.setFixCompetencyDeficiency(null);
        return this;
    }

    public void setPreJobNiazsanjiCompetencies(Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies) {
        this.preJobNiazsanjiCompetencies = preJobNiazsanjiCompetencies;
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
        FixCompetencyDeficiency fixCompetencyDeficiency = (FixCompetencyDeficiency) o;
        if (fixCompetencyDeficiency.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fixCompetencyDeficiency.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FixCompetencyDeficiency{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
