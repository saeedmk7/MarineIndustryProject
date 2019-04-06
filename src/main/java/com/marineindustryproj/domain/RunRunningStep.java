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
 * A RunRunningStep.
 */
@Entity
@Table(name = "run_running_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RunRunningStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "done", nullable = false)
    private Boolean done;

    @Size(max = 50)
    @Column(name = "done_user_login", length = 50)
    private String doneUserLogin;

    @Column(name = "done_date")
    private ZonedDateTime doneDate;

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
    @JsonIgnoreProperties("runRunningSteps")
    private RunPhase runPhase;

    @ManyToOne
    @JsonIgnoreProperties("runRunningSteps")
    private RunningStep runningStep;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public RunRunningStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDone() {
        return done;
    }

    public RunRunningStep done(Boolean done) {
        this.done = done;
        return this;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDoneUserLogin() {
        return doneUserLogin;
    }

    public RunRunningStep doneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
        return this;
    }

    public void setDoneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
    }

    public ZonedDateTime getDoneDate() {
        return doneDate;
    }

    public RunRunningStep doneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
        return this;
    }

    public void setDoneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RunRunningStep createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RunRunningStep createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RunRunningStep modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RunRunningStep modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public RunPhase getRunPhase() {
        return runPhase;
    }

    public RunRunningStep runPhase(RunPhase runPhase) {
        this.runPhase = runPhase;
        return this;
    }

    public void setRunPhase(RunPhase runPhase) {
        this.runPhase = runPhase;
    }

    public RunningStep getRunningStep() {
        return runningStep;
    }

    public RunRunningStep runningStep(RunningStep runningStep) {
        this.runningStep = runningStep;
        return this;
    }

    public void setRunningStep(RunningStep runningStep) {
        this.runningStep = runningStep;
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
        RunRunningStep runRunningStep = (RunRunningStep) o;
        if (runRunningStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runRunningStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunRunningStep{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", done='" + isDone() + "'" +
            ", doneUserLogin='" + getDoneUserLogin() + "'" +
            ", doneDate='" + getDoneDate() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
