package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JamHelpAuthority entity.
 */
public class JamHelpAuthorityDTO implements Serializable {

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

    private Long jamHelpId;

    private String jamHelpTitle;

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

    public Long getJamHelpId() {
        return jamHelpId;
    }

    public void setJamHelpId(Long jamHelpId) {
        this.jamHelpId = jamHelpId;
    }

    public String getJamHelpTitle() {
        return jamHelpTitle;
    }

    public void setJamHelpTitle(String jamHelpTitle) {
        this.jamHelpTitle = jamHelpTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JamHelpAuthorityDTO jamHelpAuthorityDTO = (JamHelpAuthorityDTO) o;
        if (jamHelpAuthorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jamHelpAuthorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JamHelpAuthorityDTO{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", hasEditPermission='" + isHasEditPermission() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", jamHelp=" + getJamHelpId() +
            ", jamHelp='" + getJamHelpTitle() + "'" +
            "}";
    }
}
