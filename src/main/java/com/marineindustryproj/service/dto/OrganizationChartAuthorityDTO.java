package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrganizationChartAuthority entity.
 */
public class OrganizationChartAuthorityDTO implements Serializable {

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

    @NotNull
    private Boolean archived;

    @Size(max = 50)
    private String archivedUserLogin;

    private ZonedDateTime archivedDate;

    @NotNull
    private Integer status;

    private Long organizationChartId;

    private String organizationChartTitle;

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

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationChartAuthorityDTO organizationChartAuthorityDTO = (OrganizationChartAuthorityDTO) o;
        if (organizationChartAuthorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationChartAuthorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationChartAuthorityDTO{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", hasEditPermission='" + isHasEditPermission() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
