package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the FinalNiazsanjiReportPerson entity. This class is used in FinalNiazsanjiReportPersonResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /final-niazsanji-report-people?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinalNiazsanjiReportPersonCriteria implements Serializable {
    /**
     * Class for filtering NiazSanjiSource
     */
    public static class NiazSanjiSourceFilter extends Filter<NiazSanjiSource> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private NiazSanjiSourceFilter niazSanjiSource;

    private IntegerFilter priceCost;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter sourceId;

    private FloatFilter scoreBeforeTest;

    private FloatFilter scoreAfterTest;

    private FloatFilter averageScore;

    private FloatFilter levelOneScore;

    private FloatFilter levelThreeScore;

    private FloatFilter levelFourScore;

    private BooleanFilter absented;

    private LongFilter niazsanjiPersonGradeId;

    private LongFilter levelThreeEffectivenessId;

    private LongFilter levelFourEffectivenessId;

    private LongFilter documentId;

    private LongFilter personId;

    private LongFilter finalNiazsanjiReportId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public NiazSanjiSourceFilter getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public void setNiazSanjiSource(NiazSanjiSourceFilter niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public IntegerFilter getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(IntegerFilter priceCost) {
        this.priceCost = priceCost;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(StringFilter createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTimeFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTimeFilter createDate) {
        this.createDate = createDate;
    }

    public StringFilter getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(StringFilter modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTimeFilter getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTimeFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BooleanFilter getArchived() {
        return archived;
    }

    public void setArchived(BooleanFilter archived) {
        this.archived = archived;
    }

    public StringFilter getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(StringFilter archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTimeFilter getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTimeFilter archivedDate) {
        this.archivedDate = archivedDate;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getSourceId() {
        return sourceId;
    }

    public void setSourceId(LongFilter sourceId) {
        this.sourceId = sourceId;
    }

    public FloatFilter getScoreBeforeTest() {
        return scoreBeforeTest;
    }

    public void setScoreBeforeTest(FloatFilter scoreBeforeTest) {
        this.scoreBeforeTest = scoreBeforeTest;
    }

    public FloatFilter getScoreAfterTest() {
        return scoreAfterTest;
    }

    public void setScoreAfterTest(FloatFilter scoreAfterTest) {
        this.scoreAfterTest = scoreAfterTest;
    }

    public FloatFilter getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(FloatFilter averageScore) {
        this.averageScore = averageScore;
    }

    public FloatFilter getLevelOneScore() {
        return levelOneScore;
    }

    public void setLevelOneScore(FloatFilter levelOneScore) {
        this.levelOneScore = levelOneScore;
    }

    public FloatFilter getLevelThreeScore() {
        return levelThreeScore;
    }

    public void setLevelThreeScore(FloatFilter levelThreeScore) {
        this.levelThreeScore = levelThreeScore;
    }

    public FloatFilter getLevelFourScore() {
        return levelFourScore;
    }

    public void setLevelFourScore(FloatFilter levelFourScore) {
        this.levelFourScore = levelFourScore;
    }

    public BooleanFilter getAbsented() {
        return absented;
    }

    public void setAbsented(BooleanFilter absented) {
        this.absented = absented;
    }

    public LongFilter getNiazsanjiPersonGradeId() {
        return niazsanjiPersonGradeId;
    }

    public void setNiazsanjiPersonGradeId(LongFilter niazsanjiPersonGradeId) {
        this.niazsanjiPersonGradeId = niazsanjiPersonGradeId;
    }

    public LongFilter getLevelThreeEffectivenessId() {
        return levelThreeEffectivenessId;
    }

    public void setLevelThreeEffectivenessId(LongFilter levelThreeEffectivenessId) {
        this.levelThreeEffectivenessId = levelThreeEffectivenessId;
    }

    public LongFilter getLevelFourEffectivenessId() {
        return levelFourEffectivenessId;
    }

    public void setLevelFourEffectivenessId(LongFilter levelFourEffectivenessId) {
        this.levelFourEffectivenessId = levelFourEffectivenessId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(LongFilter finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FinalNiazsanjiReportPersonCriteria that = (FinalNiazsanjiReportPersonCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(niazSanjiSource, that.niazSanjiSource) &&
            Objects.equals(priceCost, that.priceCost) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(sourceId, that.sourceId) &&
            Objects.equals(scoreBeforeTest, that.scoreBeforeTest) &&
            Objects.equals(scoreAfterTest, that.scoreAfterTest) &&
            Objects.equals(averageScore, that.averageScore) &&
            Objects.equals(levelOneScore, that.levelOneScore) &&
            Objects.equals(levelThreeScore, that.levelThreeScore) &&
            Objects.equals(levelFourScore, that.levelFourScore) &&
            Objects.equals(absented, that.absented) &&
            Objects.equals(niazsanjiPersonGradeId, that.niazsanjiPersonGradeId) &&
            Objects.equals(levelThreeEffectivenessId, that.levelThreeEffectivenessId) &&
            Objects.equals(levelFourEffectivenessId, that.levelFourEffectivenessId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(finalNiazsanjiReportId, that.finalNiazsanjiReportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        niazSanjiSource,
        priceCost,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        sourceId,
        scoreBeforeTest,
        scoreAfterTest,
        averageScore,
        levelOneScore,
        levelThreeScore,
        levelFourScore,
        absented,
        niazsanjiPersonGradeId,
        levelThreeEffectivenessId,
        levelFourEffectivenessId,
        documentId,
        personId,
        finalNiazsanjiReportId
        );
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportPersonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (niazSanjiSource != null ? "niazSanjiSource=" + niazSanjiSource + ", " : "") +
                (priceCost != null ? "priceCost=" + priceCost + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (sourceId != null ? "sourceId=" + sourceId + ", " : "") +
                (scoreBeforeTest != null ? "scoreBeforeTest=" + scoreBeforeTest + ", " : "") +
                (scoreAfterTest != null ? "scoreAfterTest=" + scoreAfterTest + ", " : "") +
                (averageScore != null ? "averageScore=" + averageScore + ", " : "") +
                (levelOneScore != null ? "levelOneScore=" + levelOneScore + ", " : "") +
                (levelThreeScore != null ? "levelThreeScore=" + levelThreeScore + ", " : "") +
                (levelFourScore != null ? "levelFourScore=" + levelFourScore + ", " : "") +
                (absented != null ? "absented=" + absented + ", " : "") +
                (niazsanjiPersonGradeId != null ? "niazsanjiPersonGradeId=" + niazsanjiPersonGradeId + ", " : "") +
                (levelThreeEffectivenessId != null ? "levelThreeEffectivenessId=" + levelThreeEffectivenessId + ", " : "") +
                (levelFourEffectivenessId != null ? "levelFourEffectivenessId=" + levelFourEffectivenessId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (finalNiazsanjiReportId != null ? "finalNiazsanjiReportId=" + finalNiazsanjiReportId + ", " : "") +
            "}";
    }

}
