package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.marineindustryproj.domain.enumeration.Grade;

/**
 * A DTO for the EducationalCenterGrade entity.
 */
public class EducationalCenterGradeDTO implements Serializable {

    private Long id;

    @Size(max = 1024)
    private String title;

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

    private Set<EvaluatorOpinionDTO> evaluatorOpinions = new HashSet<>();

    private Set<EducationalCenterGradeScoreDTO> educationalCenterGradeScores = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long educationalCenterServiceId;

    private String educationalCenterServiceTitle;

    private Long educationalCenterId;

    private String educationalCenterName;

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

    public Set<EvaluatorOpinionDTO> getEvaluatorOpinions() {
        return evaluatorOpinions;
    }

    public void setEvaluatorOpinions(Set<EvaluatorOpinionDTO> evaluatorOpinions) {
        this.evaluatorOpinions = evaluatorOpinions;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getEducationalCenterServiceId() {
        return educationalCenterServiceId;
    }

    public void setEducationalCenterServiceId(Long educationalCenterServiceId) {
        this.educationalCenterServiceId = educationalCenterServiceId;
    }

    public String getEducationalCenterServiceTitle() {
        return educationalCenterServiceTitle;
    }

    public void setEducationalCenterServiceTitle(String educationalCenterServiceTitle) {
        this.educationalCenterServiceTitle = educationalCenterServiceTitle;
    }

    public Long getEducationalCenterId() {
        return educationalCenterId;
    }

    public void setEducationalCenterId(Long educationalCenterId) {
        this.educationalCenterId = educationalCenterId;
    }

    public String getEducationalCenterName() {
        return educationalCenterName;
    }

    public void setEducationalCenterName(String educationalCenterName) {
        this.educationalCenterName = educationalCenterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalCenterGradeDTO educationalCenterGradeDTO = (EducationalCenterGradeDTO) o;
        if (educationalCenterGradeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterGradeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterGradeDTO{" +
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
            ", educationalCenterService=" + getEducationalCenterServiceId() +
            ", educationalCenterService='" + getEducationalCenterServiceTitle() + "'" +
            ", educationalCenter=" + getEducationalCenterId() +
            ", educationalCenter='" + getEducationalCenterName() + "'" +
            "}";
    }

    public Set<EducationalCenterGradeScoreDTO> getEducationalCenterGradeScores() {
        return educationalCenterGradeScores;
    }

    public void setEducationalCenterGradeScores(Set<EducationalCenterGradeScoreDTO> educationalCenterGradeScores) {
        this.educationalCenterGradeScores = educationalCenterGradeScores;
    }
}
