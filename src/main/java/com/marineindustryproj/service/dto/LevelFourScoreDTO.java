package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LevelFourScore entity.
 */
public class LevelFourScoreDTO implements Serializable {

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

    private Long levelFourCriteriaId;

    private String levelFourCriteriaTitle;

    private Long levelFourEffectivenessId;

    private String levelFourEffectivenessTitle;

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

    public Long getLevelFourCriteriaId() {
        return levelFourCriteriaId;
    }

    public void setLevelFourCriteriaId(Long levelFourCriteriaId) {
        this.levelFourCriteriaId = levelFourCriteriaId;
    }

    public String getLevelFourCriteriaTitle() {
        return levelFourCriteriaTitle;
    }

    public void setLevelFourCriteriaTitle(String levelFourCriteriaTitle) {
        this.levelFourCriteriaTitle = levelFourCriteriaTitle;
    }

    public Long getLevelFourEffectivenessId() {
        return levelFourEffectivenessId;
    }

    public void setLevelFourEffectivenessId(Long levelFourEffectivenessId) {
        this.levelFourEffectivenessId = levelFourEffectivenessId;
    }

    public String getLevelFourEffectivenessTitle() {
        return levelFourEffectivenessTitle;
    }

    public void setLevelFourEffectivenessTitle(String levelFourEffectivenessTitle) {
        this.levelFourEffectivenessTitle = levelFourEffectivenessTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LevelFourScoreDTO levelFourScoreDTO = (LevelFourScoreDTO) o;
        if (levelFourScoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelFourScoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelFourScoreDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", levelFourCriteria=" + getLevelFourCriteriaId() +
            ", levelFourCriteria='" + getLevelFourCriteriaTitle() + "'" +
            ", levelFourEffectiveness=" + getLevelFourEffectivenessId() +
            ", levelFourEffectiveness='" + getLevelFourEffectivenessTitle() + "'" +
            "}";
    }
}
