package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NavBarItem entity.
 */
public class NavBarItemDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String code;

    @Size(max = 1024)
    private String description;

    @NotNull
    @Size(max = 1024)
    private String url;

    @Size(max = 50)
    private String faicon;

    @NotNull
    private Boolean isActive;

    private Integer displayOrder;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long parentId;

    private String parentTitle;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFaicon() {
        return faicon;
    }

    public void setFaicon(String faicon) {
        this.faicon = faicon;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long navBarItemId) {
        this.parentId = navBarItemId;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String navBarItemTitle) {
        this.parentTitle = navBarItemTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NavBarItemDTO navBarItemDTO = (NavBarItemDTO) o;
        if (navBarItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), navBarItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NavBarItemDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", url='" + getUrl() + "'" +
            ", faicon='" + getFaicon() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", parent=" + getParentId() +
            ", parent='" + getParentTitle() + "'" +
            "}";
    }
}
