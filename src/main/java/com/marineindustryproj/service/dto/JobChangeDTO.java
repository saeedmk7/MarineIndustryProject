package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JobChange entity.
 */
public class JobChangeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4096)
    private String title;

    @NotNull
    private Integer status;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    private Long personId;

    private String personFamily;

    private String personName;

    private Long oldJobId;

    private String oldJobTitle;

    private Long newJobId;

    private String newJobTitle;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(String personFamily) {
        this.personFamily = personFamily;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getOldJobId() {
        return oldJobId;
    }

    public void setOldJobId(Long jobId) {
        this.oldJobId = jobId;
    }

    public String getOldJobTitle() {
        return oldJobTitle;
    }

    public void setOldJobTitle(String jobTitle) {
        this.oldJobTitle = jobTitle;
    }

    public Long getNewJobId() {
        return newJobId;
    }

    public void setNewJobId(Long jobId) {
        this.newJobId = jobId;
    }

    public String getNewJobTitle() {
        return newJobTitle;
    }

    public void setNewJobTitle(String jobTitle) {
        this.newJobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobChangeDTO jobChangeDTO = (JobChangeDTO) o;
        if (jobChangeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobChangeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobChangeDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", status=" + getStatus() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            ", person='" + getPersonName() + "'" +
            ", oldJob=" + getOldJobId() +
            ", oldJob='" + getOldJobTitle() + "'" +
            ", newJob=" + getNewJobId() +
            ", newJob='" + getNewJobTitle() + "'" +
            "}";
    }
}
