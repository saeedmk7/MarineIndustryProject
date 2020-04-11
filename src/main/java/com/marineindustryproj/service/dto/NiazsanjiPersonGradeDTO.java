package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

import com.marineindustryproj.domain.NiazsanjiPersonGrade;
import com.marineindustryproj.domain.NiazsanjiPersonGradeScore;
import com.marineindustryproj.domain.enumeration.Grade;

/**
 * A DTO for the NiazsanjiPersonGrade entity.
 */
public class NiazsanjiPersonGradeDTO implements Serializable {

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

    @Size(max = 1024)
    private String strength;

    @Size(max = 1024)
    private String improvement;

    @Size(max = 1024)
    private String whatDoYouDo;

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

    private Set<NiazsanjiPersonGradeScoreDTO> niazsanjiPersonGradeScores = new HashSet<>();

    private Long finalNiazsanjiReportPersonId;

    /*private PersonDTO finalNiazsanjiReportPersonPerson;*/

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

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public String getWhatDoYouDo() {
        return whatDoYouDo;
    }

    public void setWhatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
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

    public Long getFinalNiazsanjiReportPersonId() {
        return finalNiazsanjiReportPersonId;
    }

    public void setFinalNiazsanjiReportPersonId(Long finalNiazsanjiReportPersonId) {
        this.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO = (NiazsanjiPersonGradeDTO) o;
        if (niazsanjiPersonGradeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiPersonGradeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiPersonGradeDTO{" +
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
            ", finalNiazsanjiReportPerson=" + getFinalNiazsanjiReportPersonId() +
            /*", finalNiazsanjiReportPerson='" + getFinalNiazsanjiReportPersonPerson().getFamily() + "'" +*/
            "}";
    }

    public Set<NiazsanjiPersonGradeScoreDTO> getNiazsanjiPersonGradeScores() {
        return niazsanjiPersonGradeScores;
    }

    public void setNiazsanjiPersonGradeScores(Set<NiazsanjiPersonGradeScoreDTO> niazsanjiPersonGradeScores) {
        this.niazsanjiPersonGradeScores = niazsanjiPersonGradeScores;
    }

    /*public PersonDTO getFinalNiazsanjiReportPersonPerson() {
        return finalNiazsanjiReportPersonPerson;
    }

    public void setFinalNiazsanjiReportPersonPerson(PersonDTO finalNiazsanjiReportPersonPerson) {
        this.finalNiazsanjiReportPersonPerson = finalNiazsanjiReportPersonPerson;
    }*/
}
