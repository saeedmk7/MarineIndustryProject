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
 * A MatchingRunRunningStep.
 */
@Entity
@Table(name = "matching_run_running_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchingRunRunningStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

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
    @JsonIgnoreProperties("matchingRunRunningSteps")
    private MatchingEducationalRecord matchingEducationalRecord;

    @ManyToOne
    @JsonIgnoreProperties("matchingRunRunningSteps")
    private MatchingRunningStep matchingRunningStep;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public MatchingRunRunningStep fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public MatchingRunRunningStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDone() {
        return done;
    }

    public MatchingRunRunningStep done(Boolean done) {
        this.done = done;
        return this;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDoneUserLogin() {
        return doneUserLogin;
    }

    public MatchingRunRunningStep doneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
        return this;
    }

    public void setDoneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
    }

    public ZonedDateTime getDoneDate() {
        return doneDate;
    }

    public MatchingRunRunningStep doneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
        return this;
    }

    public void setDoneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MatchingRunRunningStep createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MatchingRunRunningStep createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MatchingRunRunningStep modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MatchingRunRunningStep modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public MatchingEducationalRecord getMatchingEducationalRecord() {
        return matchingEducationalRecord;
    }

    public MatchingRunRunningStep matchingEducationalRecord(MatchingEducationalRecord matchingEducationalRecord) {
        this.matchingEducationalRecord = matchingEducationalRecord;
        return this;
    }

    public void setMatchingEducationalRecord(MatchingEducationalRecord matchingEducationalRecord) {
        this.matchingEducationalRecord = matchingEducationalRecord;
    }

    public MatchingRunningStep getMatchingRunningStep() {
        return matchingRunningStep;
    }

    public MatchingRunRunningStep matchingRunningStep(MatchingRunningStep matchingRunningStep) {
        this.matchingRunningStep = matchingRunningStep;
        return this;
    }

    public void setMatchingRunningStep(MatchingRunningStep matchingRunningStep) {
        this.matchingRunningStep = matchingRunningStep;
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
        MatchingRunRunningStep matchingRunRunningStep = (MatchingRunRunningStep) o;
        if (matchingRunRunningStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchingRunRunningStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchingRunRunningStep{" +
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
            "}";
    }
}
