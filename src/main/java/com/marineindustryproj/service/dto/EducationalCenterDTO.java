package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EducationalCenter entity.
 */
public class EducationalCenterDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 50)
    private String ceo;

    @Size(max = 50)
    private String connectionPerson;

    @Size(max = 100)
    private String telephone;

    @Size(max = 50)
    private String fax;

    @Size(max = 1024)
    private String address;

    @Size(max = 50)
    private String email;

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

    private Set<ActivityAreaDTO> activityAreas = new HashSet<>();

    private Set<DocumentDTO> documents = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getConnectionPerson() {
        return connectionPerson;
    }

    public void setConnectionPerson(String connectionPerson) {
        this.connectionPerson = connectionPerson;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<ActivityAreaDTO> getActivityAreas() {
        return activityAreas;
    }

    public void setActivityAreas(Set<ActivityAreaDTO> activityAreas) {
        this.activityAreas = activityAreas;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalCenterDTO educationalCenterDTO = (EducationalCenterDTO) o;
        if (educationalCenterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ceo='" + getCeo() + "'" +
            ", connectionPerson='" + getConnectionPerson() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
