package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PollScore.
 */
@Entity
@Table(name = "poll_score")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PollScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1024)
    @Column(name = "recommendation", length = 1024)
    private String recommendation;

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

    @ManyToOne
    @JsonIgnoreProperties("pollScores")
    private PollItem pollItem;

    @ManyToOne
    @JsonIgnoreProperties("pollScores")
    private ScoreItem scoreItem;

    @ManyToOne
    @JsonIgnoreProperties("pollScores")
    private Poll poll;

    @ManyToOne
    @JsonIgnoreProperties("pollScores")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public PollScore recommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PollScore createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PollScore createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PollScore modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PollScore modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public PollItem getPollItem() {
        return pollItem;
    }

    public PollScore pollItem(PollItem pollItem) {
        this.pollItem = pollItem;
        return this;
    }

    public void setPollItem(PollItem pollItem) {
        this.pollItem = pollItem;
    }

    public ScoreItem getScoreItem() {
        return scoreItem;
    }

    public PollScore scoreItem(ScoreItem scoreItem) {
        this.scoreItem = scoreItem;
        return this;
    }

    public void setScoreItem(ScoreItem scoreItem) {
        this.scoreItem = scoreItem;
    }

    public Poll getPoll() {
        return poll;
    }

    public PollScore poll(Poll poll) {
        this.poll = poll;
        return this;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Person getPerson() {
        return person;
    }

    public PollScore person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        PollScore pollScore = (PollScore) o;
        if (pollScore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pollScore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PollScore{" +
            "id=" + getId() +
            ", recommendation='" + getRecommendation() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
