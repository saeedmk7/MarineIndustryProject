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
 * A MonitorLearningProcessGrade.
 */
@Entity
@Table(name = "monitor_learning_process_grade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonitorLearningProcessGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_number")
    private Float firstNumber;

    @Column(name = "second_number")
    private Float secondNumber;

    @Column(name = "third_number")
    private Float thirdNumber;

    @NotNull
    @Column(name = "result", nullable = false)
    private Float result;

    @Column(name = "goal")
    private Integer goal;

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

    @ManyToOne
    @JsonIgnoreProperties("monitorLearningProcessGrades")
    private MonitorLearningProcessLevel monitorLearningProcessLevel;

    @ManyToOne
    @JsonIgnoreProperties("monitorLearningProcessGrades")
    private MonitorLearningProcess monitorLearningProcess;

    @ManyToOne
    @JsonIgnoreProperties("monitorLearningProcessGrades")
    private MonitorProcessDuration monitorProcessDuration;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getFirstNumber() {
        return firstNumber;
    }

    public MonitorLearningProcessGrade firstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
        return this;
    }

    public void setFirstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Float getSecondNumber() {
        return secondNumber;
    }

    public MonitorLearningProcessGrade secondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
        return this;
    }

    public void setSecondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Float getThirdNumber() {
        return thirdNumber;
    }

    public MonitorLearningProcessGrade thirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
        return this;
    }

    public void setThirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Float getResult() {
        return result;
    }

    public MonitorLearningProcessGrade result(Float result) {
        this.result = result;
        return this;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public Integer getGoal() {
        return goal;
    }

    public MonitorLearningProcessGrade goal(Integer goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MonitorLearningProcessGrade createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MonitorLearningProcessGrade createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MonitorLearningProcessGrade modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MonitorLearningProcessGrade modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public MonitorLearningProcessGrade guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public MonitorLearningProcessLevel getMonitorLearningProcessLevel() {
        return monitorLearningProcessLevel;
    }

    public MonitorLearningProcessGrade monitorLearningProcessLevel(MonitorLearningProcessLevel monitorLearningProcessLevel) {
        this.monitorLearningProcessLevel = monitorLearningProcessLevel;
        return this;
    }

    public void setMonitorLearningProcessLevel(MonitorLearningProcessLevel monitorLearningProcessLevel) {
        this.monitorLearningProcessLevel = monitorLearningProcessLevel;
    }

    public MonitorLearningProcess getMonitorLearningProcess() {
        return monitorLearningProcess;
    }

    public MonitorLearningProcessGrade monitorLearningProcess(MonitorLearningProcess monitorLearningProcess) {
        this.monitorLearningProcess = monitorLearningProcess;
        return this;
    }

    public void setMonitorLearningProcess(MonitorLearningProcess monitorLearningProcess) {
        this.monitorLearningProcess = monitorLearningProcess;
    }

    public MonitorProcessDuration getMonitorProcessDuration() {
        return monitorProcessDuration;
    }

    public MonitorLearningProcessGrade monitorProcessDuration(MonitorProcessDuration monitorProcessDuration) {
        this.monitorProcessDuration = monitorProcessDuration;
        return this;
    }

    public void setMonitorProcessDuration(MonitorProcessDuration monitorProcessDuration) {
        this.monitorProcessDuration = monitorProcessDuration;
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
        MonitorLearningProcessGrade monitorLearningProcessGrade = (MonitorLearningProcessGrade) o;
        if (monitorLearningProcessGrade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorLearningProcessGrade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessGrade{" +
            "id=" + getId() +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", result=" + getResult() +
            ", goal=" + getGoal() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
