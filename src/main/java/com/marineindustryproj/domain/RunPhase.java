package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A RunPhase.
 */
@Entity
@Table(name = "run_phase")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RunPhase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "finalize_cost", nullable = false)
    private Integer finalizeCost;

    @NotNull
    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

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

    @OneToMany(mappedBy = "runPhase")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RunRunningStep> runRunningSteps = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "run_phase_document",
               joinColumns = @JoinColumn(name = "run_phases_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "run_phase_person",
               joinColumns = @JoinColumn(name = "run_phases_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"))
    private Set<Person> people = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("runPhases")
    private FinalNiazsanjiReport finalNiazsanjiReport;

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

    public RunPhase description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFinalizeCost() {
        return finalizeCost;
    }

    public RunPhase finalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
        return this;
    }

    public void setFinalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public RunPhase stepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
        return this;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Boolean isDone() {
        return done;
    }

    public RunPhase done(Boolean done) {
        this.done = done;
        return this;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDoneUserLogin() {
        return doneUserLogin;
    }

    public RunPhase doneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
        return this;
    }

    public void setDoneUserLogin(String doneUserLogin) {
        this.doneUserLogin = doneUserLogin;
    }

    public ZonedDateTime getDoneDate() {
        return doneDate;
    }

    public RunPhase doneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
        return this;
    }

    public void setDoneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RunPhase createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RunPhase createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RunPhase modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RunPhase modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RunPhase archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RunPhase archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RunPhase archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RunPhase status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<RunRunningStep> getRunRunningSteps() {
        return runRunningSteps;
    }

    public RunPhase runRunningSteps(Set<RunRunningStep> runRunningSteps) {
        this.runRunningSteps = runRunningSteps;
        return this;
    }

    public RunPhase addRunRunningStep(RunRunningStep runRunningStep) {
        this.runRunningSteps.add(runRunningStep);
        runRunningStep.setRunPhase(this);
        return this;
    }

    public RunPhase removeRunRunningStep(RunRunningStep runRunningStep) {
        this.runRunningSteps.remove(runRunningStep);
        runRunningStep.setRunPhase(null);
        return this;
    }

    public void setRunRunningSteps(Set<RunRunningStep> runRunningSteps) {
        this.runRunningSteps = runRunningSteps;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public RunPhase documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RunPhase addDocument(Document document) {
        this.documents.add(document);
        document.getRunPhases().add(this);
        return this;
    }

    public RunPhase removeDocument(Document document) {
        this.documents.remove(document);
        document.getRunPhases().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public RunPhase people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public RunPhase addPerson(Person person) {
        this.people.add(person);
        person.getRunPhases().add(this);
        return this;
    }

    public RunPhase removePerson(Person person) {
        this.people.remove(person);
        person.getRunPhases().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public FinalNiazsanjiReport getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public RunPhase finalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
        return this;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
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
        RunPhase runPhase = (RunPhase) o;
        if (runPhase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runPhase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunPhase{" +
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
            "}";
    }
}
