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
 * A RunningStep.
 */
@Entity
@Table(name = "running_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RunningStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @NotNull
    @Column(name = "step_required", nullable = false)
    private Boolean stepRequired;

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

    @NotNull
    @Column(name = "archived", nullable = false)
    private Boolean archived;

    @Size(max = 50)
    @Column(name = "archived_user_login", length = 50)
    private String archivedUserLogin;

    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "runningStep")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RunRunningStep> runRunningSteps = new HashSet<>();
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

    public RunningStep title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public RunningStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public RunningStep stepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
        return this;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Boolean isStepRequired() {
        return stepRequired;
    }

    public RunningStep stepRequired(Boolean stepRequired) {
        this.stepRequired = stepRequired;
        return this;
    }

    public void setStepRequired(Boolean stepRequired) {
        this.stepRequired = stepRequired;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RunningStep createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RunningStep createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RunningStep modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RunningStep modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RunningStep archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RunningStep archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RunningStep archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RunningStep status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<RunRunningStep> getRunRunningSteps() {
        return runRunningSteps;
    }

    public RunningStep runRunningSteps(Set<RunRunningStep> runRunningSteps) {
        this.runRunningSteps = runRunningSteps;
        return this;
    }

    public RunningStep addRunRunningStep(RunRunningStep runRunningStep) {
        this.runRunningSteps.add(runRunningStep);
        runRunningStep.setRunningStep(this);
        return this;
    }

    public RunningStep removeRunRunningStep(RunRunningStep runRunningStep) {
        this.runRunningSteps.remove(runRunningStep);
        runRunningStep.setRunningStep(null);
        return this;
    }

    public void setRunRunningSteps(Set<RunRunningStep> runRunningSteps) {
        this.runRunningSteps = runRunningSteps;
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
        RunningStep runningStep = (RunningStep) o;
        if (runningStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runningStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunningStep{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", stepNumber=" + getStepNumber() +
            ", stepRequired='" + isStepRequired() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
