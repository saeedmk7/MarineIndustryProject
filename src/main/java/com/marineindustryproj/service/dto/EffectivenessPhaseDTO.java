package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EffectivenessPhase entity.
 */
public class EffectivenessPhaseDTO implements Serializable {

    private Long id;

    private ZonedDateTime finishPhaseDate;

    private ZonedDateTime startPhaseDate;

    private Float firstScore;

    private Float secondScore;

    private Float finalScore;

    private Float weightedPoints;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Integer status;

    @Size(max = 50)
    private String startPhaseUserLogin;

    @Size(max = 50)
    private String finishPhaseUserLogin;

    private Set<DocumentDTO> documents = new HashSet<>();

    private FinalNiazsanjiReportDTO finalNiazsanjiReport;

    private Long finalNiazsanjiReportId;

    private String finalNiazsanjiReportDescription;

    private EffectivenessPhaseLevelDTO effectivenessPhaseLevel;

    private Long effectivenessPhaseLevelId;

    private String effectivenessPhaseLevelTitle;

    private Long peopleFinishCount;

    private Long peopleCount;

    private Float currentWeightedPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getFinishPhaseDate() {
        return finishPhaseDate;
    }

    public void setFinishPhaseDate(ZonedDateTime finishPhaseDate) {
        this.finishPhaseDate = finishPhaseDate;
    }

    public ZonedDateTime getStartPhaseDate() {
        return startPhaseDate;
    }

    public void setStartPhaseDate(ZonedDateTime startPhaseDate) {
        this.startPhaseDate = startPhaseDate;
    }

    public Float getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(Float firstScore) {
        this.firstScore = firstScore;
    }

    public Float getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(Float secondScore) {
        this.secondScore = secondScore;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public Float getWeightedPoints() {
        return weightedPoints;
    }

    public void setWeightedPoints(Float weightedPoints) {
        this.weightedPoints = weightedPoints;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartPhaseUserLogin() {
        return startPhaseUserLogin;
    }

    public void setStartPhaseUserLogin(String startPhaseUserLogin) {
        this.startPhaseUserLogin = startPhaseUserLogin;
    }

    public String getFinishPhaseUserLogin() {
        return finishPhaseUserLogin;
    }

    public void setFinishPhaseUserLogin(String finishPhaseUserLogin) {
        this.finishPhaseUserLogin = finishPhaseUserLogin;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
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

    public Long getEffectivenessPhaseLevelId() {
        return effectivenessPhaseLevelId;
    }

    public void setEffectivenessPhaseLevelId(Long effectivenessPhaseLevelId) {
        this.effectivenessPhaseLevelId = effectivenessPhaseLevelId;
    }

    public String getEffectivenessPhaseLevelTitle() {
        return effectivenessPhaseLevelTitle;
    }

    public void setEffectivenessPhaseLevelTitle(String effectivenessPhaseLevelTitle) {
        this.effectivenessPhaseLevelTitle = effectivenessPhaseLevelTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EffectivenessPhaseDTO effectivenessPhaseDTO = (EffectivenessPhaseDTO) o;
        if (effectivenessPhaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), effectivenessPhaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EffectivenessPhaseDTO{" +
            "id=" + getId() +
            ", finishPhaseDate='" + getFinishPhaseDate() + "'" +
            ", startPhaseDate='" + getStartPhaseDate() + "'" +
            ", firstScore=" + getFirstScore() +
            ", secondScore=" + getSecondScore() +
            ", finalScore=" + getFinalScore() +
            ", weightedPoints=" + getWeightedPoints() +
            ", currentWeightedPoints=" + getCurrentWeightedPoints() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", status=" + getStatus() +
            ", startPhaseUserLogin='" + getStartPhaseUserLogin() + "'" +
            ", finishPhaseUserLogin='" + getFinishPhaseUserLogin() + "'" +
            ", finalNiazsanjiReport=" + getFinalNiazsanjiReportId() +
            ", finalNiazsanjiReport='" + getFinalNiazsanjiReportDescription() + "'" +
            ", effectivenessPhaseLevel=" + getEffectivenessPhaseLevelId() +
            ", effectivenessPhaseLevel='" + getEffectivenessPhaseLevelTitle() + "'" +
            "}";
    }

    public FinalNiazsanjiReportDTO getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReportDTO finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
    }

    public EffectivenessPhaseLevelDTO getEffectivenessPhaseLevel() {
        return effectivenessPhaseLevel;
    }

    public void setEffectivenessPhaseLevel(EffectivenessPhaseLevelDTO effectivenessPhaseLevel) {
        this.effectivenessPhaseLevel = effectivenessPhaseLevel;
    }

    public Long getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Long peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Long getPeopleFinishCount() {
        return peopleFinishCount;
    }

    public void setPeopleFinishCount(Long peopleFinishCount) {
        this.peopleFinishCount = peopleFinishCount;
    }

    public Float getCurrentWeightedPoints() {
        return currentWeightedPoints;
    }

    public void setCurrentWeightedPoints(Float currentWeightedPoints) {
        this.currentWeightedPoints = currentWeightedPoints;
    }
}
