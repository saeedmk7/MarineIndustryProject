package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the MatchingRunRunningStep entity.
 */
public class MatchingRunRunningStepDTO implements Serializable {

    private Long id;

    @Lob
    private String fileDoc;

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

    private Long matchingEducationalRecordId;

    private String matchingEducationalRecordCode;

    private Long matchingRunningStepId;

    private String matchingRunningStepTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
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

    public Long getMatchingEducationalRecordId() {
        return matchingEducationalRecordId;
    }

    public void setMatchingEducationalRecordId(Long matchingEducationalRecordId) {
        this.matchingEducationalRecordId = matchingEducationalRecordId;
    }

    public String getMatchingEducationalRecordCode() {
        return matchingEducationalRecordCode;
    }

    public void setMatchingEducationalRecordCode(String matchingEducationalRecordCode) {
        this.matchingEducationalRecordCode = matchingEducationalRecordCode;
    }

    public Long getMatchingRunningStepId() {
        return matchingRunningStepId;
    }

    public void setMatchingRunningStepId(Long matchingRunningStepId) {
        this.matchingRunningStepId = matchingRunningStepId;
    }

    public String getMatchingRunningStepTitle() {
        return matchingRunningStepTitle;
    }

    public void setMatchingRunningStepTitle(String matchingRunningStepTitle) {
        this.matchingRunningStepTitle = matchingRunningStepTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchingRunRunningStepDTO matchingRunRunningStepDTO = (MatchingRunRunningStepDTO) o;
        if (matchingRunRunningStepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchingRunRunningStepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchingRunRunningStepDTO{" +
            "id=" + getId() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", description='" + getDescription() + "'" +
            ", done='" + isDone() + "'" +
            ", doneUserLogin='" + getDoneUserLogin() + "'" +
            ", doneDate='" + getDoneDate() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", matchingEducationalRecord=" + getMatchingEducationalRecordId() +
            ", matchingEducationalRecord='" + getMatchingEducationalRecordCode() + "'" +
            ", matchingRunningStep=" + getMatchingRunningStepId() +
            ", matchingRunningStep='" + getMatchingRunningStepTitle() + "'" +
            "}";
    }
}
