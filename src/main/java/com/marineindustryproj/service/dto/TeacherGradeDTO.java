package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

import com.marineindustryproj.domain.TeacherGrade;
import com.marineindustryproj.domain.enumeration.Grade;

/**
 * A DTO for the TeacherGrade entity.
 */
public class TeacherGradeDTO implements Serializable {

    private Long id;

    @Size(max = 1024)
    private String title;

    @Size(max = 1024)
    private String version;

    @NotNull
    private Float totalScore;

    @NotNull
    private Float totalScorePercent;

    @NotNull
    private Grade grade;

    @Size(max = 50)
    private String evaluateDate;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @Lob
    private String fileDoc;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Set<TeacherGradeScoreDTO> teacherGradeScores = new HashSet<>();

    private Long teacherId;

    private String teacherFamily;

    private String teacherName;

    private Long teacherCriteriaGroupId;

    private String teacherCriteriaGroupTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getTotalScorePercent() {
        return totalScorePercent;
    }

    public void setTotalScorePercent(Float totalScorePercent) {
        this.totalScorePercent = totalScorePercent;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFamily() {
        return teacherFamily;
    }

    public void setTeacherFamily(String teacherFamily) {
        this.teacherFamily = teacherFamily;
    }

    public Long getTeacherCriteriaGroupId() {
        return teacherCriteriaGroupId;
    }

    public void setTeacherCriteriaGroupId(Long teacherCriteriaGroupId) {
        this.teacherCriteriaGroupId = teacherCriteriaGroupId;
    }

    public String getTeacherCriteriaGroupTitle() {
        return teacherCriteriaGroupTitle;
    }

    public void setTeacherCriteriaGroupTitle(String teacherCriteriaGroupTitle) {
        this.teacherCriteriaGroupTitle = teacherCriteriaGroupTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherGradeDTO teacherGradeDTO = (TeacherGradeDTO) o;
        if (teacherGradeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherGradeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherGradeDTO{" +
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
            ", teacher=" + getTeacherId() +
            ", teacher='" + getTeacherFamily() + "'" +
            ", teacher='" + getTeacherName() + "'" +
            ", teacherCriteriaGroup=" + getTeacherCriteriaGroupId() +
            ", teacherCriteriaGroup='" + getTeacherCriteriaGroupTitle() + "'" +
            "}";
    }

    public Set<TeacherGradeScoreDTO> getTeacherGradeScores() {
        return teacherGradeScores;
    }

    public void setTeacherGradeScores(Set<TeacherGradeScoreDTO> teacherGradeScores) {
        this.teacherGradeScores = teacherGradeScores;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
