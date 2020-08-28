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
 * A NiazsanjiPersonGradeScore.
 */
@Entity
@Table(name = "niazsanji_person_grade_score")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NiazsanjiPersonGradeScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

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

    @ManyToOne
    @JsonIgnoreProperties("niazsanjiPersonGradeScores")
    private NiazsanjiPersonCriteria niazsanjiPersonCriteria;

    @ManyToOne
    @JsonIgnoreProperties("niazsanjiPersonGradeScores")
    private NiazsanjiPersonGrade niazsanjiPersonGrade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public NiazsanjiPersonGradeScore score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public NiazsanjiPersonGradeScore description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public NiazsanjiPersonGradeScore createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NiazsanjiPersonGradeScore createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public NiazsanjiPersonGradeScore modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public NiazsanjiPersonGradeScore modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public NiazsanjiPersonCriteria getNiazsanjiPersonCriteria() {
        return niazsanjiPersonCriteria;
    }

    public NiazsanjiPersonGradeScore niazsanjiPersonCriteria(NiazsanjiPersonCriteria niazsanjiPersonCriteria) {
        this.niazsanjiPersonCriteria = niazsanjiPersonCriteria;
        return this;
    }

    public void setNiazsanjiPersonCriteria(NiazsanjiPersonCriteria niazsanjiPersonCriteria) {
        this.niazsanjiPersonCriteria = niazsanjiPersonCriteria;
    }

    public NiazsanjiPersonGrade getNiazsanjiPersonGrade() {
        return niazsanjiPersonGrade;
    }

    public NiazsanjiPersonGradeScore niazsanjiPersonGrade(NiazsanjiPersonGrade niazsanjiPersonGrade) {
        this.niazsanjiPersonGrade = niazsanjiPersonGrade;
        return this;
    }

    public void setNiazsanjiPersonGrade(NiazsanjiPersonGrade niazsanjiPersonGrade) {
        this.niazsanjiPersonGrade = niazsanjiPersonGrade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NiazsanjiPersonGradeScore niazsanjiPersonGradeScore = (NiazsanjiPersonGradeScore) o;
        if (niazsanjiPersonGradeScore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiPersonGradeScore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiPersonGradeScore{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
