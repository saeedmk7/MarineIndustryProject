package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import com.marineindustryproj.domain.LevelFourEffectiveness;
import com.marineindustryproj.domain.LevelThreeEffectiveness;
import com.marineindustryproj.domain.NiazsanjiPersonGrade;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;

/**
 * A DTO for the FinalNiazsanjiReportPerson entity.
 */
public class FinalNiazsanjiReportPersonDTO implements Serializable {

    private Long id;

    @NotNull
    private NiazSanjiSource niazSanjiSource;

    private Integer priceCost;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @NotNull
    private Boolean archived;

    @Size(max = 50)
    private String archivedUserLogin;

    private ZonedDateTime archivedDate;

    @NotNull
    private Integer status;

    @NotNull
    private Long sourceId;

    private Float scoreBeforeTest;

    private Float scoreAfterTest;

    private Float averageScore;

    private Float levelOneScore;

    private Float levelThreeScore;

    private Float levelFourScore;

    private Boolean absented;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long personId;

    private String personName;

    private String personFamily;

    private Long finalNiazsanjiReportId;

    private String finalNiazsanjiReportDescription;

    private Set<NiazsanjiPersonGradeDTO> niazsanjiPersonGrades = new HashSet<>();

    private Set<LevelThreeEffectivenessDTO> levelThreeEffectivenesses = new HashSet<>();

    private Set<LevelFourEffectivenessDTO> levelFourEffectivenesses = new HashSet<>();

    private FinalNiazsanjiReportDTO finalNiazsanjiReport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NiazSanjiSource getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public void setNiazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
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

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Float getScoreBeforeTest() {
        return scoreBeforeTest;
    }

    public void setScoreBeforeTest(Float scoreBeforeTest) {
        this.scoreBeforeTest = scoreBeforeTest;
    }

    public Float getScoreAfterTest() {
        return scoreAfterTest;
    }

    public void setScoreAfterTest(Float scoreAfterTest) {
        this.scoreAfterTest = scoreAfterTest;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    public Float getLevelOneScore() {
        return levelOneScore;
    }

    public void setLevelOneScore(Float levelOneScore) {
        this.levelOneScore = levelOneScore;
    }

    public Float getLevelThreeScore() {
        return levelThreeScore;
    }

    public void setLevelThreeScore(Float levelThreeScore) {
        this.levelThreeScore = levelThreeScore;
    }

    public Float getLevelFourScore() {
        return levelFourScore;
    }

    public void setLevelFourScore(Float levelFourScore) {
        this.levelFourScore = levelFourScore;
    }

    public Boolean isAbsented() {
        return absented;
    }

    public void setAbsented(Boolean absented) {
        this.absented = absented;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(String personFamily) {
        this.personFamily = personFamily;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public String getFinalNiazsanjiReportDescription() {
        return finalNiazsanjiReportDescription;
    }

    public void setFinalNiazsanjiReportDescription(String finalNiazsanjiReportDescription) {
        this.finalNiazsanjiReportDescription = finalNiazsanjiReportDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO = (FinalNiazsanjiReportPersonDTO) o;
        if (finalNiazsanjiReportPersonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportPersonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportPersonDTO{" +
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
            ", person=" + getPersonId() +
            ", personFamily='" + getPersonFamily() + "'" +
            ", personName='" + getPersonFamily() + "'" +
            ", finalNiazsanjiReport=" + getFinalNiazsanjiReportId() +
            ", finalNiazsanjiReport='" + getFinalNiazsanjiReportDescription() + "'" +
            "}";
    }

    public Set<NiazsanjiPersonGradeDTO> getNiazsanjiPersonGrades() {
        return niazsanjiPersonGrades;
    }

    public void setNiazsanjiPersonGrades(Set<NiazsanjiPersonGradeDTO> niazsanjiPersonGrades) {
        this.niazsanjiPersonGrades = niazsanjiPersonGrades;
    }

    public Set<LevelThreeEffectivenessDTO> getLevelThreeEffectivenesses() {
        return levelThreeEffectivenesses;
    }

    public void setLevelThreeEffectivenesses(Set<LevelThreeEffectivenessDTO> levelThreeEffectivenesses) {
        this.levelThreeEffectivenesses = levelThreeEffectivenesses;
    }

    public Set<LevelFourEffectivenessDTO> getLevelFourEffectivenesses() {
        return levelFourEffectivenesses;
    }

    public void setLevelFourEffectivenesses(Set<LevelFourEffectivenessDTO> levelFourEffectivenesses) {
        this.levelFourEffectivenesses = levelFourEffectivenesses;
    }

    public FinalNiazsanjiReportDTO getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReportDTO finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
    }
}
