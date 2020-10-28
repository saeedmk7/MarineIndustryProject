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
 * A TeacherGrade.
 */
@Entity
@Table(name = "teacher_grade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TeacherGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1024)
    @Column(name = "title", length = 1024)
    private String title;

    @Size(max = 1024)
    @Column(name = "version", length = 1024)
    private String version;

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

    @OneToMany(mappedBy = "teacherGrade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TeacherGradeScore> teacherGradeScores = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "teacher_grade_document",
               joinColumns = @JoinColumn(name = "teacher_grades_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("teacherGrades")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("teacherGrades")
    private TeacherCriteriaGroup teacherCriteriaGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public TeacherGrade title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public TeacherGrade version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public TeacherGrade totalScore(Float totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getTotalScorePercent() {
        return totalScorePercent;
    }

    public TeacherGrade totalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
        return this;
    }

    public void setTotalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public Grade getGrade() {
        return grade;
    }

    public TeacherGrade grade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public TeacherGrade evaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
        return this;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getYear() {
        return year;
    }

    public TeacherGrade year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public TeacherGrade month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public TeacherGrade fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getDescription() {
        return description;
    }

    public TeacherGrade description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public TeacherGrade createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public TeacherGrade createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public TeacherGrade modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public TeacherGrade modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<TeacherGradeScore> getTeacherGradeScores() {
        return teacherGradeScores;
    }

    public TeacherGrade teacherGradeScores(Set<TeacherGradeScore> teacherGradeScores) {
        this.teacherGradeScores = teacherGradeScores;
        return this;
    }

    public TeacherGrade addTeacherGradeScore(TeacherGradeScore teacherGradeScore) {
        this.teacherGradeScores.add(teacherGradeScore);
        teacherGradeScore.setTeacherGrade(this);
        return this;
    }

    public TeacherGrade removeTeacherGradeScore(TeacherGradeScore teacherGradeScore) {
        this.teacherGradeScores.remove(teacherGradeScore);
        teacherGradeScore.setTeacherGrade(null);
        return this;
    }

    public void setTeacherGradeScores(Set<TeacherGradeScore> teacherGradeScores) {
        this.teacherGradeScores = teacherGradeScores;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public TeacherGrade documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public TeacherGrade addDocument(Document document) {
        this.documents.add(document);
        document.getTeacherGrades().add(this);
        return this;
    }

    public TeacherGrade removeDocument(Document document) {
        this.documents.remove(document);
        document.getTeacherGrades().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public TeacherGrade teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherCriteriaGroup getTeacherCriteriaGroup() {
        return teacherCriteriaGroup;
    }

    public TeacherGrade teacherCriteriaGroup(TeacherCriteriaGroup teacherCriteriaGroup) {
        this.teacherCriteriaGroup = teacherCriteriaGroup;
        return this;
    }

    public void setTeacherCriteriaGroup(TeacherCriteriaGroup teacherCriteriaGroup) {
        this.teacherCriteriaGroup = teacherCriteriaGroup;
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
        TeacherGrade teacherGrade = (TeacherGrade) o;
        if (teacherGrade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherGrade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherGrade{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", version='" + getVersion() + "'" +
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
