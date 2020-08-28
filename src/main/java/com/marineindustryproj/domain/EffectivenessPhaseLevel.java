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

import com.marineindustryproj.domain.enumeration.UnitOfMeasurement;

/**
 * A EffectivenessPhaseLevel.
 */
@Entity
@Table(name = "effectiveness_phase_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EffectivenessPhaseLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @NotNull
    @Column(name = "effectiveness_level", nullable = false)
    private Integer effectivenessLevel;

    @NotNull
    @Column(name = "effectiveness_level_use", nullable = false)
    private Integer effectivenessLevelUse;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "goal")
    private Float goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measurement")
    private UnitOfMeasurement unitOfMeasurement;

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

    @OneToMany(mappedBy = "effectivenessPhaseLevel")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EffectivenessPhase> effectivenessPhases = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public EffectivenessPhaseLevel title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEffectivenessLevel() {
        return effectivenessLevel;
    }

    public EffectivenessPhaseLevel effectivenessLevel(Integer effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
        return this;
    }

    public void setEffectivenessLevel(Integer effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
    }

    public Integer getEffectivenessLevelUse() {
        return effectivenessLevelUse;
    }

    public EffectivenessPhaseLevel effectivenessLevelUse(Integer effectivenessLevelUse) {
        this.effectivenessLevelUse = effectivenessLevelUse;
        return this;
    }

    public void setEffectivenessLevelUse(Integer effectivenessLevelUse) {
        this.effectivenessLevelUse = effectivenessLevelUse;
    }

    public Float getWeight() {
        return weight;
    }

    public EffectivenessPhaseLevel weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getGoal() {
        return goal;
    }

    public EffectivenessPhaseLevel goal(Float goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(Float goal) {
        this.goal = goal;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public EffectivenessPhaseLevel unitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
        return this;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getDescription() {
        return description;
    }

    public EffectivenessPhaseLevel description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EffectivenessPhaseLevel createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EffectivenessPhaseLevel createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EffectivenessPhaseLevel modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EffectivenessPhaseLevel modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<EffectivenessPhase> getEffectivenessPhases() {
        return effectivenessPhases;
    }

    public EffectivenessPhaseLevel effectivenessPhases(Set<EffectivenessPhase> effectivenessPhases) {
        this.effectivenessPhases = effectivenessPhases;
        return this;
    }

    public EffectivenessPhaseLevel addEffectivenessPhase(EffectivenessPhase effectivenessPhase) {
        this.effectivenessPhases.add(effectivenessPhase);
        effectivenessPhase.setEffectivenessPhaseLevel(this);
        return this;
    }

    public EffectivenessPhaseLevel removeEffectivenessPhase(EffectivenessPhase effectivenessPhase) {
        this.effectivenessPhases.remove(effectivenessPhase);
        effectivenessPhase.setEffectivenessPhaseLevel(null);
        return this;
    }

    public void setEffectivenessPhases(Set<EffectivenessPhase> effectivenessPhases) {
        this.effectivenessPhases = effectivenessPhases;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EffectivenessPhaseLevel effectivenessPhaseLevel = (EffectivenessPhaseLevel) o;
        if (effectivenessPhaseLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), effectivenessPhaseLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EffectivenessPhaseLevel{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", effectivenessLevel=" + getEffectivenessLevel() +
            ", effectivenessLevelUse=" + getEffectivenessLevelUse() +
            ", weight=" + getWeight() +
            ", goal=" + getGoal() +
            ", unitOfMeasurement='" + getUnitOfMeasurement() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
