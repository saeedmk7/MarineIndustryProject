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
 * A LevelThreeEffectiveness.
 */
@Entity
@Table(name = "level_three_effectiveness")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LevelThreeEffectiveness implements Serializable {

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

    @OneToMany(mappedBy = "levelThreeEffectiveness")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelThreeScore> levelThreeScores = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "level_three_effectiveness_document",
               joinColumns = @JoinColumn(name = "level_three_effectivenesses_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("levelThreeEffectivenesses")
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

    public LevelThreeEffectiveness title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public LevelThreeEffectiveness totalScore(Float totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getTotalScorePercent() {
        return totalScorePercent;
    }

    public LevelThreeEffectiveness totalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
        return this;
    }

    public void setTotalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public Grade getGrade() {
        return grade;
    }

    public LevelThreeEffectiveness grade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public LevelThreeEffectiveness evaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
        return this;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getYear() {
        return year;
    }

    public LevelThreeEffectiveness year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public LevelThreeEffectiveness month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getStrength() {
        return strength;
    }

    public LevelThreeEffectiveness strength(String strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getImprovement() {
        return improvement;
    }

    public LevelThreeEffectiveness improvement(String improvement) {
        this.improvement = improvement;
        return this;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public String getWhatDoYouDo() {
        return whatDoYouDo;
    }

    public LevelThreeEffectiveness whatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
        return this;
    }

    public void setWhatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public LevelThreeEffectiveness fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public LevelThreeEffectiveness description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public LevelThreeEffectiveness createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public LevelThreeEffectiveness createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public LevelThreeEffectiveness modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public LevelThreeEffectiveness modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<LevelThreeScore> getLevelThreeScores() {
        return levelThreeScores;
    }

    public LevelThreeEffectiveness levelThreeScores(Set<LevelThreeScore> levelThreeScores) {
        this.levelThreeScores = levelThreeScores;
        return this;
    }

    public LevelThreeEffectiveness addLevelThreeScore(LevelThreeScore levelThreeScore) {
        this.levelThreeScores.add(levelThreeScore);
        levelThreeScore.setLevelThreeEffectiveness(this);
        return this;
    }

    public LevelThreeEffectiveness removeLevelThreeScore(LevelThreeScore levelThreeScore) {
        this.levelThreeScores.remove(levelThreeScore);
        levelThreeScore.setLevelThreeEffectiveness(null);
        return this;
    }

    public void setLevelThreeScores(Set<LevelThreeScore> levelThreeScores) {
        this.levelThreeScores = levelThreeScores;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public LevelThreeEffectiveness documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public LevelThreeEffectiveness addDocument(Document document) {
        this.documents.add(document);
        document.getLevelThreeEffectivenesses().add(this);
        return this;
    }

    public LevelThreeEffectiveness removeDocument(Document document) {
        this.documents.remove(document);
        document.getLevelThreeEffectivenesses().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public FinalNiazsanjiReportPerson getFinalNiazsanjiReportPerson() {
        return finalNiazsanjiReportPerson;
    }

    public LevelThreeEffectiveness finalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
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
        LevelThreeEffectiveness levelThreeEffectiveness = (LevelThreeEffectiveness) o;
        if (levelThreeEffectiveness.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelThreeEffectiveness.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelThreeEffectiveness{" +
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
