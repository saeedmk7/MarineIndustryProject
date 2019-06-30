package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BeautySpeechAuthority entity.
 */
public class BeautySpeechAuthorityDTO implements Serializable {

    private Long id;

    @NotNull
    private String authorityName;

    @NotNull
    private Boolean hasEditPermission;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long beautySpeechId;

    private String beautySpeechTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Boolean isHasEditPermission() {
        return hasEditPermission;
    }

    public void setHasEditPermission(Boolean hasEditPermission) {
        this.hasEditPermission = hasEditPermission;
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

    public Long getBeautySpeechId() {
        return beautySpeechId;
    }

    public void setBeautySpeechId(Long beautySpeechId) {
        this.beautySpeechId = beautySpeechId;
    }

    public String getBeautySpeechTitle() {
        return beautySpeechTitle;
    }

    public void setBeautySpeechTitle(String beautySpeechTitle) {
        this.beautySpeechTitle = beautySpeechTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BeautySpeechAuthorityDTO beautySpeechAuthorityDTO = (BeautySpeechAuthorityDTO) o;
        if (beautySpeechAuthorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beautySpeechAuthorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeautySpeechAuthorityDTO{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", hasEditPermission='" + isHasEditPermission() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", beautySpeech=" + getBeautySpeechId() +
            ", beautySpeech='" + getBeautySpeechTitle() + "'" +
            "}";
    }
}
