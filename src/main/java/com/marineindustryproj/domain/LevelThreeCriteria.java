package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LevelThreeCriteria.
 */
@Entity
@Table(name = "level_three_criteria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LevelThreeCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 1024)
    @Column(name = "title", length = 1024, nullable = false)
    private String title;

    @NotNull
    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "second_weight")
    private Integer secondWeight;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
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

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "color_text")
    private String colorText;

    @OneToMany(mappedBy = "levelThreeCriteria")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LevelThreeScore> levelThreeScores = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("levelThreeCriteria")
    private MahiatCourse mahiatCourse;

    @ManyToOne
    @JsonIgnoreProperties("levelThreeCriteria")
    private LevelThreeCriteriaGroup levelThreeCriteriaGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public LevelThreeCriteria title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public LevelThreeCriteria displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getWeight() {
        return weight;
    }

    public LevelThreeCriteria weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSecondWeight() {
        return secondWeight;
    }

    public LevelThreeCriteria secondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
        return this;
    }

    public void setSecondWeight(Integer secondWeight) {
        this.secondWeight = secondWeight;
    }

    public String getDescription() {
        return description;
    }

    public LevelThreeCriteria description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public LevelThreeCriteria createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public LevelThreeCriteria createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public LevelThreeCriteria modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public LevelThreeCriteria modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public LevelThreeCriteria backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getColorText() {
        return colorText;
    }

    public LevelThreeCriteria colorText(String colorText) {
        this.colorText = colorText;
        return this;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public Set<LevelThreeScore> getLevelThreeScores() {
        return levelThreeScores;
    }

    public LevelThreeCriteria levelThreeScores(Set<LevelThreeScore> levelThreeScores) {
        this.levelThreeScores = levelThreeScores;
        return this;
    }

    public LevelThreeCriteria addLevelThreeScore(LevelThreeScore levelThreeScore) {
        this.levelThreeScores.add(levelThreeScore);
        levelThreeScore.setLevelThreeCriteria(this);
        return this;
    }

    public LevelThreeCriteria removeLevelThreeScore(LevelThreeScore levelThreeScore) {
        this.levelThreeScores.remove(levelThreeScore);
        levelThreeScore.setLevelThreeCriteria(null);
        return this;
    }

    public void setLevelThreeScores(Set<LevelThreeScore> levelThreeScores) {
        this.levelThreeScores = levelThreeScores;
    }

    public MahiatCourse getMahiatCourse() {
        return mahiatCourse;
    }

    public LevelThreeCriteria mahiatCourse(MahiatCourse mahiatCourse) {
        this.mahiatCourse = mahiatCourse;
        return this;
    }

    public void setMahiatCourse(MahiatCourse mahiatCourse) {
        this.mahiatCourse = mahiatCourse;
    }

    public LevelThreeCriteriaGroup getLevelThreeCriteriaGroup() {
        return levelThreeCriteriaGroup;
    }

    public LevelThreeCriteria levelThreeCriteriaGroup(LevelThreeCriteriaGroup levelThreeCriteriaGroup) {
        this.levelThreeCriteriaGroup = levelThreeCriteriaGroup;
        return this;
    }

    public void setLevelThreeCriteriaGroup(LevelThreeCriteriaGroup levelThreeCriteriaGroup) {
        this.levelThreeCriteriaGroup = levelThreeCriteriaGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LevelThreeCriteria levelThreeCriteria = (LevelThreeCriteria) o;
        if (levelThreeCriteria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelThreeCriteria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelThreeCriteria{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", weight=" + getWeight() +
            ", secondWeight=" + getSecondWeight() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", colorText='" + getColorText() + "'" +
            "}";
    }
}
