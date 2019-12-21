package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ForceRunningPercent entity.
 */
public class ForceRunningPercentDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    @Size(max = 4096)
    private String description;

    private Integer percentAmount;

    private Integer runMonth;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Integer year;

    private Set<OrganizationChartDTO> organizationCharts = new HashSet<>();

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

    public Integer getPercentAmount() {
        return percentAmount;
    }

    public void setPercentAmount(Integer percentAmount) {
        this.percentAmount = percentAmount;
    }

    public Integer getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(Integer runMonth) {
        this.runMonth = runMonth;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

        ForceRunningPercentDTO forceRunningPercentDTO = (ForceRunningPercentDTO) o;
        if (forceRunningPercentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), forceRunningPercentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ForceRunningPercentDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", percentAmount=" + getPercentAmount() +
            ", runMonth=" + getRunMonth() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", year=" + getYear() +
            "}";
    }
}
