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
 * A MonitorLearningProcessLevel.
 */
@Entity
@Table(name = "monitor_learning_process_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonitorLearningProcessLevel implements Serializable {

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
    @Column(name = "code", length = 4096)
    private String code;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "goal")
    private Integer goal;

    @Size(max = 4096)
    @Column(name = "formula", length = 4096)
    private String formula;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "monitorLearningProcessLevel")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MonitorLearningProcessGrade> monitorLearningProcessGrades = new HashSet<>();
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

    public MonitorLearningProcessLevel title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public MonitorLearningProcessLevel code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public MonitorLearningProcessLevel description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGoal() {
        return goal;
    }

    public MonitorLearningProcessLevel goal(Integer goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public String getFormula() {
        return formula;
    }

    public MonitorLearningProcessLevel formula(String formula) {
        this.formula = formula;
        return this;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MonitorLearningProcessLevel createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MonitorLearningProcessLevel createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MonitorLearningProcessLevel modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MonitorLearningProcessLevel modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public MonitorLearningProcessLevel guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<MonitorLearningProcessGrade> getMonitorLearningProcessGrades() {
        return monitorLearningProcessGrades;
    }

    public MonitorLearningProcessLevel monitorLearningProcessGrades(Set<MonitorLearningProcessGrade> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
        return this;
    }

    public MonitorLearningProcessLevel addMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.add(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorLearningProcessLevel(this);
        return this;
    }

    public MonitorLearningProcessLevel removeMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.remove(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorLearningProcessLevel(null);
        return this;
    }

    public void setMonitorLearningProcessGrades(Set<MonitorLearningProcessGrade> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
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
        MonitorLearningProcessLevel monitorLearningProcessLevel = (MonitorLearningProcessLevel) o;
        if (monitorLearningProcessLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorLearningProcessLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessLevel{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", goal=" + getGoal() +
            ", formula='" + getFormula() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
