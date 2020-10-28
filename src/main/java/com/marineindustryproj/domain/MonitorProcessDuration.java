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
 * A MonitorProcessDuration.
 */
@Entity
@Table(name = "monitor_process_duration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonitorProcessDuration implements Serializable {

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

    @OneToMany(mappedBy = "monitorProcessDuration")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MonitorLearningProcess> monitorLearningProcesses = new HashSet<>();
    @OneToMany(mappedBy = "monitorProcessDuration")
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

    public MonitorProcessDuration title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public MonitorProcessDuration code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public MonitorProcessDuration description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MonitorProcessDuration createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MonitorProcessDuration createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MonitorProcessDuration modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MonitorProcessDuration modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public MonitorProcessDuration guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<MonitorLearningProcess> getMonitorLearningProcesses() {
        return monitorLearningProcesses;
    }

    public MonitorProcessDuration monitorLearningProcesses(Set<MonitorLearningProcess> monitorLearningProcesses) {
        this.monitorLearningProcesses = monitorLearningProcesses;
        return this;
    }

    public MonitorProcessDuration addMonitorLearningProcess(MonitorLearningProcess monitorLearningProcess) {
        this.monitorLearningProcesses.add(monitorLearningProcess);
        monitorLearningProcess.setMonitorProcessDuration(this);
        return this;
    }

    public MonitorProcessDuration removeMonitorLearningProcess(MonitorLearningProcess monitorLearningProcess) {
        this.monitorLearningProcesses.remove(monitorLearningProcess);
        monitorLearningProcess.setMonitorProcessDuration(null);
        return this;
    }

    public void setMonitorLearningProcesses(Set<MonitorLearningProcess> monitorLearningProcesses) {
        this.monitorLearningProcesses = monitorLearningProcesses;
    }

    public Set<MonitorLearningProcessGrade> getMonitorLearningProcessGrades() {
        return monitorLearningProcessGrades;
    }

    public MonitorProcessDuration monitorLearningProcessGrades(Set<MonitorLearningProcessGrade> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
        return this;
    }

    public MonitorProcessDuration addMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.add(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorProcessDuration(this);
        return this;
    }

    public MonitorProcessDuration removeMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.remove(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorProcessDuration(null);
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
        MonitorProcessDuration monitorProcessDuration = (MonitorProcessDuration) o;
        if (monitorProcessDuration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorProcessDuration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorProcessDuration{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
