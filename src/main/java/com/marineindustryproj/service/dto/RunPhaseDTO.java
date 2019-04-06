package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the RunPhase entity.
 */
public class RunPhaseDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String description;

    @NotNull
    private Integer finalizeCost;

    @NotNull
    private Integer stepNumber;

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

    @NotNull
    private Boolean archived;

    @Size(max = 50)
    private String archivedUserLogin;

    private ZonedDateTime archivedDate;

    @NotNull
    private Integer status;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<PersonDTO> people = new HashSet<>();

    private Long finalNiazsanjiReportId;

    private String finalNiazsanjiReportDescription;

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

    public Integer getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
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

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Set<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonDTO> people) {
        this.people = people;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public String getFinalNiazsanjiReportDescription() {
        return finalNiazsanjiReportDescription;
    }

    public void setFinalNiazsanjiReportDescription(String finalNiazsanjiReportDescription) {
        this.finalNiazsanjiReportDescription = finalNiazsanjiReportDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RunPhaseDTO runPhaseDTO = (RunPhaseDTO) o;
        if (runPhaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runPhaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunPhaseDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", finalizeCost=" + getFinalizeCost() +
            ", stepNumber=" + getStepNumber() +
            ", done='" + isDone() + "'" +
            ", doneUserLogin='" + getDoneUserLogin() + "'" +
            ", doneDate='" + getDoneDate() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", finalNiazsanjiReport=" + getFinalNiazsanjiReportId() +
            ", finalNiazsanjiReport='" + getFinalNiazsanjiReportDescription() + "'" +
            "}";
    }
}
