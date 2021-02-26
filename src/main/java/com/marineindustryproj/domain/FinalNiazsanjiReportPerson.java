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
import java.util.Set;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;

/**
 * A FinalNiazsanjiReportPerson.
 */
@Entity
@Table(name = "final_niazsanji_report_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FinalNiazsanjiReportPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "niaz_sanji_source", nullable = false)
    private NiazSanjiSource niazSanjiSource;

    @Column(name = "price_cost")
    private Integer priceCost;

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

    @NotNull
    @Column(name = "source_id", nullable = false)
    private Long sourceId;

    @Column(name = "score_before_test")
    private Float scoreBeforeTest;

    @Column(name = "score_after_test")
    private Float scoreAfterTest;

    @Column(name = "average_score")
    private Float averageScore;

    @Column(name = "level_one_score")
    private Float levelOneScore;

    @Column(name = "level_three_score")
    private Float levelThreeScore;

    @Column(name = "level_four_score")
    private Float levelFourScore;

    @Column(name = "absented")
    private Boolean absented;

    @OneToMany(mappedBy = "finalNiazsanjiReportPerson", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiPersonGrade> niazsanjiPersonGrades = new HashSet<>();
    @OneToMany(mappedBy = "finalNiazsanjiReportPerson", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelThreeEffectiveness> levelThreeEffectivenesses = new HashSet<>();
    @OneToMany(mappedBy = "finalNiazsanjiReportPerson", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelFourEffectiveness> levelFourEffectivenesses = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "final_niazsanji_report_person_document",
               joinColumns = @JoinColumn(name = "final_niazsanji_report_people_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("finalNiazsanjiReportPeople")
    private Person person;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("finalNiazsanjiReportPeople")
    private FinalNiazsanjiReport finalNiazsanjiReport;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NiazSanjiSource getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public FinalNiazsanjiReportPerson niazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
        return this;
    }

    public void setNiazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public FinalNiazsanjiReportPerson priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public FinalNiazsanjiReportPerson description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FinalNiazsanjiReportPerson createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FinalNiazsanjiReportPerson createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FinalNiazsanjiReportPerson modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FinalNiazsanjiReportPerson modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public FinalNiazsanjiReportPerson archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public FinalNiazsanjiReportPerson archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public FinalNiazsanjiReportPerson archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public FinalNiazsanjiReportPerson status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public FinalNiazsanjiReportPerson sourceId(Long sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Float getScoreBeforeTest() {
        return scoreBeforeTest;
    }

    public FinalNiazsanjiReportPerson scoreBeforeTest(Float scoreBeforeTest) {
        this.scoreBeforeTest = scoreBeforeTest;
        return this;
    }

    public void setScoreBeforeTest(Float scoreBeforeTest) {
        this.scoreBeforeTest = scoreBeforeTest;
    }

    public Float getScoreAfterTest() {
        return scoreAfterTest;
    }

    public FinalNiazsanjiReportPerson scoreAfterTest(Float scoreAfterTest) {
        this.scoreAfterTest = scoreAfterTest;
        return this;
    }

    public void setScoreAfterTest(Float scoreAfterTest) {
        this.scoreAfterTest = scoreAfterTest;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public FinalNiazsanjiReportPerson averageScore(Float averageScore) {
        this.averageScore = averageScore;
        return this;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    public Float getLevelOneScore() {
        return levelOneScore;
    }

    public FinalNiazsanjiReportPerson levelOneScore(Float levelOneScore) {
        this.levelOneScore = levelOneScore;
        return this;
    }

    public void setLevelOneScore(Float levelOneScore) {
        this.levelOneScore = levelOneScore;
    }

    public Float getLevelThreeScore() {
        return levelThreeScore;
    }

    public FinalNiazsanjiReportPerson levelThreeScore(Float levelThreeScore) {
        this.levelThreeScore = levelThreeScore;
        return this;
    }

    public void setLevelThreeScore(Float levelThreeScore) {
        this.levelThreeScore = levelThreeScore;
    }

    public Float getLevelFourScore() {
        return levelFourScore;
    }

    public FinalNiazsanjiReportPerson levelFourScore(Float levelFourScore) {
        this.levelFourScore = levelFourScore;
        return this;
    }

    public void setLevelFourScore(Float levelFourScore) {
        this.levelFourScore = levelFourScore;
    }

    public Boolean isAbsented() {
        return absented;
    }

    public FinalNiazsanjiReportPerson absented(Boolean absented) {
        this.absented = absented;
        return this;
    }

    public void setAbsented(Boolean absented) {
        this.absented = absented;
    }

    public Set<NiazsanjiPersonGrade> getNiazsanjiPersonGrades() {
        return niazsanjiPersonGrades;
    }

    public FinalNiazsanjiReportPerson niazsanjiPersonGrades(Set<NiazsanjiPersonGrade> niazsanjiPersonGrades) {
        this.niazsanjiPersonGrades = niazsanjiPersonGrades;
        return this;
    }

    public FinalNiazsanjiReportPerson addNiazsanjiPersonGrade(NiazsanjiPersonGrade niazsanjiPersonGrade) {
        this.niazsanjiPersonGrades.add(niazsanjiPersonGrade);
        niazsanjiPersonGrade.setFinalNiazsanjiReportPerson(this);
        return this;
    }

    public FinalNiazsanjiReportPerson removeNiazsanjiPersonGrade(NiazsanjiPersonGrade niazsanjiPersonGrade) {
        this.niazsanjiPersonGrades.remove(niazsanjiPersonGrade);
        niazsanjiPersonGrade.setFinalNiazsanjiReportPerson(null);
        return this;
    }

    public void setNiazsanjiPersonGrades(Set<NiazsanjiPersonGrade> niazsanjiPersonGrades) {
        this.niazsanjiPersonGrades = niazsanjiPersonGrades;
    }

    public Set<LevelThreeEffectiveness> getLevelThreeEffectivenesses() {
        return levelThreeEffectivenesses;
    }

    public FinalNiazsanjiReportPerson levelThreeEffectivenesses(Set<LevelThreeEffectiveness> levelThreeEffectivenesses) {
        this.levelThreeEffectivenesses = levelThreeEffectivenesses;
        return this;
    }

    public FinalNiazsanjiReportPerson addLevelThreeEffectiveness(LevelThreeEffectiveness levelThreeEffectiveness) {
        this.levelThreeEffectivenesses.add(levelThreeEffectiveness);
        levelThreeEffectiveness.setFinalNiazsanjiReportPerson(this);
        return this;
    }

    public FinalNiazsanjiReportPerson removeLevelThreeEffectiveness(LevelThreeEffectiveness levelThreeEffectiveness) {
        this.levelThreeEffectivenesses.remove(levelThreeEffectiveness);
        levelThreeEffectiveness.setFinalNiazsanjiReportPerson(null);
        return this;
    }

    public void setLevelThreeEffectivenesses(Set<LevelThreeEffectiveness> levelThreeEffectivenesses) {
        this.levelThreeEffectivenesses = levelThreeEffectivenesses;
    }

    public Set<LevelFourEffectiveness> getLevelFourEffectivenesses() {
        return levelFourEffectivenesses;
    }

    public FinalNiazsanjiReportPerson levelFourEffectivenesses(Set<LevelFourEffectiveness> levelFourEffectivenesses) {
        this.levelFourEffectivenesses = levelFourEffectivenesses;
        return this;
    }

    public FinalNiazsanjiReportPerson addLevelFourEffectiveness(LevelFourEffectiveness levelFourEffectiveness) {
        this.levelFourEffectivenesses.add(levelFourEffectiveness);
        levelFourEffectiveness.setFinalNiazsanjiReportPerson(this);
        return this;
    }

    public FinalNiazsanjiReportPerson removeLevelFourEffectiveness(LevelFourEffectiveness levelFourEffectiveness) {
        this.levelFourEffectivenesses.remove(levelFourEffectiveness);
        levelFourEffectiveness.setFinalNiazsanjiReportPerson(null);
        return this;
    }

    public void setLevelFourEffectivenesses(Set<LevelFourEffectiveness> levelFourEffectivenesses) {
        this.levelFourEffectivenesses = levelFourEffectivenesses;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public FinalNiazsanjiReportPerson documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public FinalNiazsanjiReportPerson addDocument(Document document) {
        this.documents.add(document);
        document.getFinalNiazsanjiReportPeople().add(this);
        return this;
    }

    public FinalNiazsanjiReportPerson removeDocument(Document document) {
        this.documents.remove(document);
        document.getFinalNiazsanjiReportPeople().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Person getPerson() {
        return person;
    }

    public FinalNiazsanjiReportPerson person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public FinalNiazsanjiReport getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public FinalNiazsanjiReportPerson finalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
        return this;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = (FinalNiazsanjiReportPerson) o;
        if (finalNiazsanjiReportPerson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportPerson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportPerson{" +
            "id=" + getId() +
            ", niazSanjiSource='" + getNiazSanjiSource() + "'" +
            ", priceCost=" + getPriceCost() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", sourceId=" + getSourceId() +
            ", scoreBeforeTest=" + getScoreBeforeTest() +
            ", scoreAfterTest=" + getScoreAfterTest() +
            ", averageScore=" + getAverageScore() +
            ", levelOneScore=" + getLevelOneScore() +
            ", levelThreeScore=" + getLevelThreeScore() +
            ", levelFourScore=" + getLevelFourScore() +
            ", absented='" + isAbsented() + "'" +
            "}";
    }
}
