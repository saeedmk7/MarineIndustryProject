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

import com.marineindustryproj.domain.enumeration.Grade;

/**
 * A LevelFourEffectiveness.
 */
@Entity
@Table(name = "level_four_effectiveness")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LevelFourEffectiveness implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1024)
    @Column(name = "title", length = 1024)
    private String title;

    @NotNull
    @Column(name = "total_score", nullable = false)
    private Float totalScore;

    @NotNull
    @Column(name = "total_score_percent", nullable = false)
    private Float totalScorePercent;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Grade grade;

    @Size(max = 50)
    @Column(name = "evaluate_date", length = 50)
    private String evaluateDate;

    @NotNull
    @Column(name = "mi_year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Integer month;

    @Size(max = 1024)
    @Column(name = "strength", length = 1024)
    private String strength;

    @Size(max = 1024)
    @Column(name = "improvement", length = 1024)
    private String improvement;

    @Size(max = 1024)
    @Column(name = "what_do_you_do", length = 1024)
    private String whatDoYouDo;

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

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

    @OneToMany(mappedBy = "levelFourEffectiveness", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelFourScore> levelFourScores = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "level_four_effectiveness_document",
               joinColumns = @JoinColumn(name = "level_four_effectivenesses_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("levelFourEffectivenesses")
    private FinalNiazsanjiReportPerson finalNiazsanjiReportPerson;

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

    public LevelFourEffectiveness title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public LevelFourEffectiveness totalScore(Float totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getTotalScorePercent() {
        return totalScorePercent;
    }

    public LevelFourEffectiveness totalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
        return this;
    }

    public void setTotalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public Grade getGrade() {
        return grade;
    }

    public LevelFourEffectiveness grade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public LevelFourEffectiveness evaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
        return this;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getYear() {
        return year;
    }

    public LevelFourEffectiveness year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public LevelFourEffectiveness month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getStrength() {
        return strength;
    }

    public LevelFourEffectiveness strength(String strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getImprovement() {
        return improvement;
    }

    public LevelFourEffectiveness improvement(String improvement) {
        this.improvement = improvement;
        return this;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public String getWhatDoYouDo() {
        return whatDoYouDo;
    }

    public LevelFourEffectiveness whatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
        return this;
    }

    public void setWhatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public LevelFourEffectiveness fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public LevelFourEffectiveness description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public LevelFourEffectiveness createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public LevelFourEffectiveness createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public LevelFourEffectiveness modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public LevelFourEffectiveness modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<LevelFourScore> getLevelFourScores() {
        return levelFourScores;
    }

    public LevelFourEffectiveness levelFourScores(Set<LevelFourScore> levelFourScores) {
        this.levelFourScores = levelFourScores;
        return this;
    }

    public LevelFourEffectiveness addLevelFourScore(LevelFourScore levelFourScore) {
        this.levelFourScores.add(levelFourScore);
        levelFourScore.setLevelFourEffectiveness(this);
        return this;
    }

    public LevelFourEffectiveness removeLevelFourScore(LevelFourScore levelFourScore) {
        this.levelFourScores.remove(levelFourScore);
        levelFourScore.setLevelFourEffectiveness(null);
        return this;
    }

    public void setLevelFourScores(Set<LevelFourScore> levelFourScores) {
        this.levelFourScores = levelFourScores;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public LevelFourEffectiveness documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public LevelFourEffectiveness addDocument(Document document) {
        this.documents.add(document);
        document.getLevelFourEffectivenesses().add(this);
        return this;
    }

    public LevelFourEffectiveness removeDocument(Document document) {
        this.documents.remove(document);
        document.getLevelFourEffectivenesses().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public FinalNiazsanjiReportPerson getFinalNiazsanjiReportPerson() {
        return finalNiazsanjiReportPerson;
    }

    public LevelFourEffectiveness finalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPerson = finalNiazsanjiReportPerson;
        return this;
    }

    public void setFinalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPerson = finalNiazsanjiReportPerson;
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
        LevelFourEffectiveness levelFourEffectiveness = (LevelFourEffectiveness) o;
        if (levelFourEffectiveness.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelFourEffectiveness.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelFourEffectiveness{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", totalScore=" + getTotalScore() +
            ", totalScorePercent=" + getTotalScorePercent() +
            ", grade='" + getGrade() + "'" +
            ", evaluateDate='" + getEvaluateDate() + "'" +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", strength='" + getStrength() + "'" +
            ", improvement='" + getImprovement() + "'" +
            ", whatDoYouDo='" + getWhatDoYouDo() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
