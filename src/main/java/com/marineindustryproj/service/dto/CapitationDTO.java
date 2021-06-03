package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Capitation entity.
 */
public class CapitationDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    @Size(max = 50)
    private String code;

    @Size(max = 4096)
    private String description;

    @NotNull
    private Integer employeeMaximumAllowablePersonHours;

    @NotNull
    private Integer bossMaximumAllowablePersonHours;

    @NotNull
    private Integer employeeMaximumAllowablePersonCosts;

    @NotNull
    private Integer bossMaximumAllowablePersonCosts;

    private Integer capitationYear;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

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

    public Integer getEmployeeMaximumAllowablePersonHours() {
        return employeeMaximumAllowablePersonHours;
    }

    public void setEmployeeMaximumAllowablePersonHours(Integer employeeMaximumAllowablePersonHours) {
        this.employeeMaximumAllowablePersonHours = employeeMaximumAllowablePersonHours;
    }

    public Integer getBossMaximumAllowablePersonHours() {
        return bossMaximumAllowablePersonHours;
    }

    public void setBossMaximumAllowablePersonHours(Integer bossMaximumAllowablePersonHours) {
        this.bossMaximumAllowablePersonHours = bossMaximumAllowablePersonHours;
    }

    public Integer getEmployeeMaximumAllowablePersonCosts() {
        return employeeMaximumAllowablePersonCosts;
    }

    public void setEmployeeMaximumAllowablePersonCosts(Integer employeeMaximumAllowablePersonCosts) {
        this.employeeMaximumAllowablePersonCosts = employeeMaximumAllowablePersonCosts;
    }

    public Integer getBossMaximumAllowablePersonCosts() {
        return bossMaximumAllowablePersonCosts;
    }

    public void setBossMaximumAllowablePersonCosts(Integer bossMaximumAllowablePersonCosts) {
        this.bossMaximumAllowablePersonCosts = bossMaximumAllowablePersonCosts;
    }

    public Integer getCapitationYear() {
        return capitationYear;
    }

    public void setCapitationYear(Integer capitationYear) {
        this.capitationYear = capitationYear;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CapitationDTO capitationDTO = (CapitationDTO) o;
        if (capitationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), capitationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CapitationDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", employeeMaximumAllowablePersonHours=" + getEmployeeMaximumAllowablePersonHours() +
            ", bossMaximumAllowablePersonHours=" + getBossMaximumAllowablePersonHours() +
            ", employeeMaximumAllowablePersonCosts=" + getEmployeeMaximumAllowablePersonCosts() +
            ", bossMaximumAllowablePersonCosts=" + getBossMaximumAllowablePersonCosts() +
            ", capitationYear=" + getCapitationYear() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
