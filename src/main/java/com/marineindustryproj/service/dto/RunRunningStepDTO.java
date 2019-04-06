package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RunRunningStep entity.
 */
public class RunRunningStepDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String description;

    @NotNull
    private Boolean done;

    @Size(max = 50)
    private String doneUserLogin;

    private ZonedDateTime doneDate;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long runPhaseId;

    private String runPhaseDescription;

    private Long runningStepId;

    private String runningStepTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDoneUserLogin() {
        return doneUserLogin;
    }

    public void setDoneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
    }

    public ZonedDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getRunPhaseId() {
        return runPhaseId;
    }

    public void setRunPhaseId(Long runPhaseId) {
        this.runPhaseId = runPhaseId;
    }

    public String getRunPhaseDescription() {
        return runPhaseDescription;
    }

    public void setRunPhaseDescription(String runPhaseDescription) {
        this.runPhaseDescription = runPhaseDescription;
    }

    public Long getRunningStepId() {
        return runningStepId;
    }

    public void setRunningStepId(Long runningStepId) {
        this.runningStepId = runningStepId;
    }

    public String getRunningStepTitle() {
        return runningStepTitle;
    }

    public void setRunningStepTitle(String runningStepTitle) {
        this.runningStepTitle = runningStepTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RunRunningStepDTO runRunningStepDTO = (RunRunningStepDTO) o;
        if (runRunningStepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runRunningStepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunRunningStepDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", done='" + isDone() + "'" +
            ", doneUserLogin='" + getDoneUserLogin() + "'" +
            ", doneDate='" + getDoneDate() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", runPhase=" + getRunPhaseId() +
            ", runPhase='" + getRunPhaseDescription() + "'" +
            ", runningStep=" + getRunningStepId() +
            ", runningStep='" + getRunningStepTitle() + "'" +
            "}";
    }
}
