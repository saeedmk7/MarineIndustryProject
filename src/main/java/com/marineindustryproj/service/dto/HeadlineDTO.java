package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.HeadlineLevel;
import com.marineindustryproj.domain.enumeration.HeadlineScope;

/**
 * A DTO for the Headline entity.
 */
public class HeadlineDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4096)
    private String title;

    @NotNull
    private HeadlineLevel headlineLevel;

    @NotNull
    private HeadlineScope headlineScope;

    @NotNull
    private Float totalHour;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long requestEducationalModuleId;

    private String requestEducationalModuleTitle;

    private Long educationalModuleId;

    private String educationalModuleTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeadlineLevel getHeadlineLevel() {
        return headlineLevel;
    }

    public void setHeadlineLevel(HeadlineLevel headlineLevel) {
        this.headlineLevel = headlineLevel;
    }

    public HeadlineScope getHeadlineScope() {
        return headlineScope;
    }

    public void setHeadlineScope(HeadlineScope headlineScope) {
        this.headlineScope = headlineScope;
    }

    public Float getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Float totalHour) {
        this.totalHour = totalHour;
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

    public Long getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(Long requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }

    public String getRequestEducationalModuleTitle() {
        return requestEducationalModuleTitle;
    }

    public void setRequestEducationalModuleTitle(String requestEducationalModuleTitle) {
        this.requestEducationalModuleTitle = requestEducationalModuleTitle;
    }

    public Long getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(Long educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HeadlineDTO headlineDTO = (HeadlineDTO) o;
        if (headlineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), headlineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HeadlineDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", headlineLevel='" + getHeadlineLevel() + "'" +
            ", headlineScope='" + getHeadlineScope() + "'" +
            ", totalHour=" + getTotalHour() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", requestEducationalModule=" + getRequestEducationalModuleId() +
            ", requestEducationalModule='" + getRequestEducationalModuleTitle() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            "}";
    }
}
