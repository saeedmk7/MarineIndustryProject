package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the WorkUnit entity.
 */
public class WorkUnitDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer code;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long workIndustryId;

    private String workIndustryTitle;

    private Long workGroupId;

    private String workGroupTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Long getWorkIndustryId() {
        return workIndustryId;
    }

    public void setWorkIndustryId(Long workIndustryId) {
        this.workIndustryId = workIndustryId;
    }

    public String getWorkIndustryTitle() {
        return workIndustryTitle;
    }

    public void setWorkIndustryTitle(String workIndustryTitle) {
        this.workIndustryTitle = workIndustryTitle;
    }

    public Long getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(Long workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getWorkGroupTitle() {
        return workGroupTitle;
    }

    public void setWorkGroupTitle(String workGroupTitle) {
        this.workGroupTitle = workGroupTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkUnitDTO workUnitDTO = (WorkUnitDTO) o;
        if (workUnitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workUnitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkUnitDTO{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", workIndustry=" + getWorkIndustryId() +
            ", workIndustry='" + getWorkIndustryTitle() + "'" +
            ", workGroup=" + getWorkGroupId() +
            ", workGroup='" + getWorkGroupTitle() + "'" +
            "}";
    }
}
