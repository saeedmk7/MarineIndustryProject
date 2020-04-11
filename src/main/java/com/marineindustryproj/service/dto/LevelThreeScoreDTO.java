package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LevelThreeScore entity.
 */
public class LevelThreeScoreDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer score;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long levelThreeCriteriaId;

    private String levelThreeCriteriaTitle;

    private Long levelThreeEffectivenessId;

    private String levelThreeEffectivenessTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Long getLevelThreeCriteriaId() {
        return levelThreeCriteriaId;
    }

    public void setLevelThreeCriteriaId(Long levelThreeCriteriaId) {
        this.levelThreeCriteriaId = levelThreeCriteriaId;
    }

    public String getLevelThreeCriteriaTitle() {
        return levelThreeCriteriaTitle;
    }

    public void setLevelThreeCriteriaTitle(String levelThreeCriteriaTitle) {
        this.levelThreeCriteriaTitle = levelThreeCriteriaTitle;
    }

    public Long getLevelThreeEffectivenessId() {
        return levelThreeEffectivenessId;
    }

    public void setLevelThreeEffectivenessId(Long levelThreeEffectivenessId) {
        this.levelThreeEffectivenessId = levelThreeEffectivenessId;
    }

    public String getLevelThreeEffectivenessTitle() {
        return levelThreeEffectivenessTitle;
    }

    public void setLevelThreeEffectivenessTitle(String levelThreeEffectivenessTitle) {
        this.levelThreeEffectivenessTitle = levelThreeEffectivenessTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LevelThreeScoreDTO levelThreeScoreDTO = (LevelThreeScoreDTO) o;
        if (levelThreeScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelThreeScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelThreeScoreDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", levelThreeCriteria=" + getLevelThreeCriteriaId() +
            ", levelThreeCriteria='" + getLevelThreeCriteriaTitle() + "'" +
            ", levelThreeEffectiveness=" + getLevelThreeEffectivenessId() +
            ", levelThreeEffectiveness='" + getLevelThreeEffectivenessTitle() + "'" +
            "}";
    }
}
