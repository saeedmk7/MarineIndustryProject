package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Lob;

import com.marineindustryproj.domain.Authority;

/**
 * A DTO for the JamHelp entity.
 */
public class JamHelpDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 1024)
    private String description;

    @Size(max = 100)
    private String code;

    
    @Lob
    private String fileDoc;

    @Size(max = 4096)
    private String pageUrl;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private String authorityNames;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
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

    public String getAuthorityNames() {
        return authorityNames;
    }

    public void setAuthorityNames(String authorityNames) {
        this.authorityNames = authorityNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JamHelpDTO jamHelpDTO = (JamHelpDTO) o;
        if (jamHelpDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jamHelpDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JamHelpDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", code='" + getCode() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            ", authorityNames='" + getAuthorityNames() + "'" +
            ", pageUrl='" + getPageUrl() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }


}
