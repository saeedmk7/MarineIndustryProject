package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the PollScore entity. This class is used in PollScoreResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /poll-scores?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PollScoreCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter recommendation;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter pollItemId;

    private LongFilter scoreItemId;

    private LongFilter pollId;

    private LongFilter personId;

    public PollScoreCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(StringFilter recommendation) {
        this.recommendation = recommendation;
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

    public LongFilter getPollItemId() {
        return pollItemId;
    }

    public void setPollItemId(LongFilter pollItemId) {
        this.pollItemId = pollItemId;
    }

    public LongFilter getScoreItemId() {
        return scoreItemId;
    }

    public void setScoreItemId(LongFilter scoreItemId) {
        this.scoreItemId = scoreItemId;
    }

    public LongFilter getPollId() {
        return pollId;
    }

    public void setPollId(LongFilter pollId) {
        this.pollId = pollId;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PollScoreCriteria that = (PollScoreCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(recommendation, that.recommendation) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(pollItemId, that.pollItemId) &&
            Objects.equals(scoreItemId, that.scoreItemId) &&
            Objects.equals(pollId, that.pollId) &&
            Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        recommendation,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        pollItemId,
        scoreItemId,
        pollId,
        personId
        );
    }

    @Override
    public String toString() {
        return "PollScoreCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (recommendation != null ? "recommendation=" + recommendation + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (pollItemId != null ? "pollItemId=" + pollItemId + ", " : "") +
                (scoreItemId != null ? "scoreItemId=" + scoreItemId + ", " : "") +
                (pollId != null ? "pollId=" + pollId + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
            "}";
    }

}
