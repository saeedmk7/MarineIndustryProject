package com.marineindustryproj.domain;

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
 * A EffectivenessPhase.
 */
@Entity
@Table(name = "effectiveness_phase")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EffectivenessPhase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "finish_phase_date")
    private ZonedDateTime finishPhaseDate;

    @Column(name = "start_phase_date")
    private ZonedDateTime startPhaseDate;

    @Column(name = "first_score")
    private Float firstScore;

    @Column(name = "second_score")
    private Float secondScore;

    @Column(name = "final_score")
    private Float finalScore;

    @Column(name = "weighted_points")
    private Float weightedPoints;

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

    @Column(name = "status")
    private Integer status;

    @Size(max = 50)
    @Column(name = "start_phase_user_login", length = 50)
    private String startPhaseUserLogin;

    @Size(max = 50)
    @Column(name = "finish_phase_user_login", length = 50)
    private String finishPhaseUserLogin;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "effectiveness_phase_document",
               joinColumns = @JoinColumn(name = "effectiveness_phases_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("effectivenessPhases")
    private FinalNiazsanjiReport finalNiazsanjiReport;

    @ManyToOne
    @JsonIgnoreProperties("effectivenessPhases")
    private EffectivenessPhaseLevel effectivenessPhaseLevel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getFinishPhaseDate() {
        return finishPhaseDate;
    }

    public EffectivenessPhase finishPhaseDate(ZonedDateTime finishPhaseDate) {
        this.finishPhaseDate = finishPhaseDate;
        return this;
    }

    public void setFinishPhaseDate(ZonedDateTime finishPhaseDate) {
        this.finishPhaseDate = finishPhaseDate;
    }

    public ZonedDateTime getStartPhaseDate() {
        return startPhaseDate;
    }

    public EffectivenessPhase startPhaseDate(ZonedDateTime startPhaseDate) {
        this.startPhaseDate = startPhaseDate;
        return this;
    }

    public void setStartPhaseDate(ZonedDateTime startPhaseDate) {
        this.startPhaseDate = startPhaseDate;
    }

    public Float getFirstScore() {
        return firstScore;
    }

    public EffectivenessPhase firstScore(Float firstScore) {
        this.firstScore = firstScore;
        return this;
    }

    public void setFirstScore(Float firstScore) {
        this.firstScore = firstScore;
    }

    public Float getSecondScore() {
        return secondScore;
    }

    public EffectivenessPhase secondScore(Float secondScore) {
        this.secondScore = secondScore;
        return this;
    }

    public void setSecondScore(Float secondScore) {
        this.secondScore = secondScore;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public EffectivenessPhase finalScore(Float finalScore) {
        this.finalScore = finalScore;
        return this;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public Float getWeightedPoints() {
        return weightedPoints;
    }

    public EffectivenessPhase weightedPoints(Float weightedPoints) {
        this.weightedPoints = weightedPoints;
        return this;
    }

    public void setWeightedPoints(Float weightedPoints) {
        this.weightedPoints = weightedPoints;
    }

    public String getDescription() {
        return description;
    }

    public EffectivenessPhase description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EffectivenessPhase createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EffectivenessPhase createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EffectivenessPhase modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EffectivenessPhase modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public EffectivenessPhase status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartPhaseUserLogin() {
        return startPhaseUserLogin;
    }

    public EffectivenessPhase startPhaseUserLogin(String startPhaseUserLogin) {
        this.startPhaseUserLogin = startPhaseUserLogin;
        return this;
    }

    public void setStartPhaseUserLogin(String startPhaseUserLogin) {
        this.startPhaseUserLogin = startPhaseUserLogin;
    }

    public String getFinishPhaseUserLogin() {
        return finishPhaseUserLogin;
    }

    public EffectivenessPhase finishPhaseUserLogin(String finishPhaseUserLogin) {
        this.finishPhaseUserLogin = finishPhaseUserLogin;
        return this;
    }

    public void setFinishPhaseUserLogin(String finishPhaseUserLogin) {
        this.finishPhaseUserLogin = finishPhaseUserLogin;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EffectivenessPhase documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EffectivenessPhase addDocument(Document document) {
        this.documents.add(document);
        document.getEffectivenessPhases().add(this);
        return this;
    }

    public EffectivenessPhase removeDocument(Document document) {
        this.documents.remove(document);
        document.getEffectivenessPhases().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public FinalNiazsanjiReport getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public EffectivenessPhase finalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
        return this;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
    }

    public EffectivenessPhaseLevel getEffectivenessPhaseLevel() {
        return effectivenessPhaseLevel;
    }

    public EffectivenessPhase effectivenessPhaseLevel(EffectivenessPhaseLevel effectivenessPhaseLevel) {
        this.effectivenessPhaseLevel = effectivenessPhaseLevel;
        return this;
    }

    public void setEffectivenessPhaseLevel(EffectivenessPhaseLevel effectivenessPhaseLevel) {
        this.effectivenessPhaseLevel = effectivenessPhaseLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EffectivenessPhase effectivenessPhase = (EffectivenessPhase) o;
        if (effectivenessPhase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), effectivenessPhase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EffectivenessPhase{" +
            "id=" + getId() +
            ", finishPhaseDate='" + getFinishPhaseDate() + "'" +
            ", startPhaseDate='" + getStartPhaseDate() + "'" +
            ", firstScore=" + getFirstScore() +
            ", secondScore=" + getSecondScore() +
            ", finalScore=" + getFinalScore() +
            ", weightedPoints=" + getWeightedPoints() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", status=" + getStatus() +
            ", startPhaseUserLogin='" + getStartPhaseUserLogin() + "'" +
            ", finishPhaseUserLogin='" + getFinishPhaseUserLogin() + "'" +
            "}";
    }
}
