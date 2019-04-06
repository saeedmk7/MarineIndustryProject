package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PollScore entity.
 */
public class PollScoreDTO implements Serializable {

    private Long id;

    @Size(max = 1024)
    private String recommendation;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long pollItemId;

    private String pollItemTitle;

    private Long scoreItemId;

    private String scoreItemTitle;

    private Long pollId;

    private String pollMoreRecommendation;

    private Long personId;

    private String personFamily;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
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

    public Long getPollItemId() {
        return pollItemId;
    }

    public void setPollItemId(Long pollItemId) {
        this.pollItemId = pollItemId;
    }

    public String getPollItemTitle() {
        return pollItemTitle;
    }

    public void setPollItemTitle(String pollItemTitle) {
        this.pollItemTitle = pollItemTitle;
    }

    public Long getScoreItemId() {
        return scoreItemId;
    }

    public void setScoreItemId(Long scoreItemId) {
        this.scoreItemId = scoreItemId;
    }

    public String getScoreItemTitle() {
        return scoreItemTitle;
    }

    public void setScoreItemTitle(String scoreItemTitle) {
        this.scoreItemTitle = scoreItemTitle;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getPollMoreRecommendation() {
        return pollMoreRecommendation;
    }

    public void setPollMoreRecommendation(String pollMoreRecommendation) {
        this.pollMoreRecommendation = pollMoreRecommendation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PollScoreDTO pollScoreDTO = (PollScoreDTO) o;
        if (pollScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pollScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PollScoreDTO{" +
            "id=" + getId() +
            ", recommendation='" + getRecommendation() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", pollItem=" + getPollItemId() +
            ", pollItem='" + getPollItemTitle() + "'" +
            ", scoreItem=" + getScoreItemId() +
            ", scoreItem='" + getScoreItemTitle() + "'" +
            ", poll=" + getPollId() +
            ", poll='" + getPollMoreRecommendation() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            "}";
    }
}
