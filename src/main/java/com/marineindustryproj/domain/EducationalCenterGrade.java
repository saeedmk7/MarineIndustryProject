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
 * A EducationalCenterGrade.
 */
@Entity
@Table(name = "educational_center_grade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationalCenterGrade implements Serializable {

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

    @OneToMany(mappedBy = "educationalCenterGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalCenterGradeScore> educationalCenterGradeScores = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_center_grade_evaluator_opinion",
               joinColumns = @JoinColumn(name = "educational_center_grades_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "evaluator_opinions_id", referencedColumnName = "id"))
    private Set<EvaluatorOpinion> evaluatorOpinions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_center_grade_document",
               joinColumns = @JoinColumn(name = "educational_center_grades_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("educationalCenterGrades")
    private EducationalCenterService educationalCenterService;

    @ManyToOne
    @JsonIgnoreProperties("educationalCenterGrades")
    private EducationalCenter educationalCenter;

    @ManyToOne
    @JsonIgnoreProperties("educationalCenterGrades")
    private EducationalCenterGroup educationalCenterGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public EducationalCenterGrade title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public EducationalCenterGrade totalScore(Float totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getTotalScorePercent() {
        return totalScorePercent;
    }

    public EducationalCenterGrade totalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
        return this;
    }

    public void setTotalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public Grade getGrade() {
        return grade;
    }

    public EducationalCenterGrade grade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public EducationalCenterGrade evaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
        return this;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getYear() {
        return year;
    }

    public EducationalCenterGrade year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public EducationalCenterGrade month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public EducationalCenterGrade fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public EducationalCenterGrade description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EducationalCenterGrade createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EducationalCenterGrade createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EducationalCenterGrade modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EducationalCenterGrade modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<EducationalCenterGradeScore> getEducationalCenterGradeScores() {
        return educationalCenterGradeScores;
    }

    public EducationalCenterGrade educationalCenterGradeScores(Set<EducationalCenterGradeScore> educationalCenterGradeScores) {
        this.educationalCenterGradeScores = educationalCenterGradeScores;
        return this;
    }

    public EducationalCenterGrade addEducationalCenterGradeScore(EducationalCenterGradeScore educationalCenterGradeScore) {
        this.educationalCenterGradeScores.add(educationalCenterGradeScore);
        educationalCenterGradeScore.setEducationalCenterGrade(this);
        return this;
    }

    public EducationalCenterGrade removeEducationalCenterGradeScore(EducationalCenterGradeScore educationalCenterGradeScore) {
        this.educationalCenterGradeScores.remove(educationalCenterGradeScore);
        educationalCenterGradeScore.setEducationalCenterGrade(null);
        return this;
    }

    public void setEducationalCenterGradeScores(Set<EducationalCenterGradeScore> educationalCenterGradeScores) {
        this.educationalCenterGradeScores = educationalCenterGradeScores;
    }

    public Set<EvaluatorOpinion> getEvaluatorOpinions() {
        return evaluatorOpinions;
    }

    public EducationalCenterGrade evaluatorOpinions(Set<EvaluatorOpinion> evaluatorOpinions) {
        this.evaluatorOpinions = evaluatorOpinions;
        return this;
    }

    public EducationalCenterGrade addEvaluatorOpinion(EvaluatorOpinion evaluatorOpinion) {
        this.evaluatorOpinions.add(evaluatorOpinion);
        evaluatorOpinion.getEducationalCenterGrades().add(this);
        return this;
    }

    public EducationalCenterGrade removeEvaluatorOpinion(EvaluatorOpinion evaluatorOpinion) {
        this.evaluatorOpinions.remove(evaluatorOpinion);
        evaluatorOpinion.getEducationalCenterGrades().remove(this);
        return this;
    }

    public void setEvaluatorOpinions(Set<EvaluatorOpinion> evaluatorOpinions) {
        this.evaluatorOpinions = evaluatorOpinions;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EducationalCenterGrade documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EducationalCenterGrade addDocument(Document document) {
        this.documents.add(document);
        document.getEducationalCenterGrades().add(this);
        return this;
    }

    public EducationalCenterGrade removeDocument(Document document) {
        this.documents.remove(document);
        document.getEducationalCenterGrades().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public EducationalCenterService getEducationalCenterService() {
        return educationalCenterService;
    }

    public EducationalCenterGrade educationalCenterService(EducationalCenterService educationalCenterService) {
        this.educationalCenterService = educationalCenterService;
        return this;
    }

    public void setEducationalCenterService(EducationalCenterService educationalCenterService) {
        this.educationalCenterService = educationalCenterService;
    }

    public EducationalCenter getEducationalCenter() {
        return educationalCenter;
    }

    public EducationalCenterGrade educationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenter = educationalCenter;
        return this;
    }

    public void setEducationalCenter(EducationalCenter educationalCenter) {
        this.educationalCenter = educationalCenter;
    }

    public EducationalCenterGroup getEducationalCenterGroup() {
        return educationalCenterGroup;
    }

    public EducationalCenterGrade educationalCenterGroup(EducationalCenterGroup educationalCenterGroup) {
        this.educationalCenterGroup = educationalCenterGroup;
        return this;
    }

    public void setEducationalCenterGroup(EducationalCenterGroup educationalCenterGroup) {
        this.educationalCenterGroup = educationalCenterGroup;
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
        EducationalCenterGrade educationalCenterGrade = (EducationalCenterGrade) o;
        if (educationalCenterGrade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterGrade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterGrade{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", totalScore=" + getTotalScore() +
            ", totalScorePercent=" + getTotalScorePercent() +
            ", grade='" + getGrade() + "'" +
            ", evaluateDate='" + getEvaluateDate() + "'" +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
