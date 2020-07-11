package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ReportGenerator entity.
 */
public class ReportGeneratorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4096)
    private String title;

    @Size(max = 4096)
    private String description;

    @NotNull
    @Size(max = 9999999)
    private String searchParams;

    @NotNull
    @Size(max = 9999999)
    private String columnNames;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Set<OrganizationChartDTO> organizationCharts = new HashSet<>();

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

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<OrganizationChartDTO> getOrganizationCharts() {
        return organizationCharts;
    }

    public void setOrganizationCharts(Set<OrganizationChartDTO> organizationCharts) {
        this.organizationCharts = organizationCharts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReportGeneratorDTO reportGeneratorDTO = (ReportGeneratorDTO) o;
        if (reportGeneratorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportGeneratorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportGeneratorDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", searchParams='" + getSearchParams() + "'" +
            ", columnNames='" + getColumnNames() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", authorityNames='" + getAuthorityNames() + "'" +
            "}";
    }

    public String getAuthorityNames() {
        return authorityNames;
    }

    public void setAuthorityNames(String authorityNames) {
        this.authorityNames = authorityNames;
    }
}
