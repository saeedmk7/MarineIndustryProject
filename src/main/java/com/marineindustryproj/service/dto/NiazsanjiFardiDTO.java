package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.marineindustryproj.domain.enumeration.EducationalModuleType;

/**
 * A DTO for the NiazsanjiFardi entity.
 */
public class NiazsanjiFardiDTO implements Serializable {

    private Long id;

    private Integer niazsanjiYear;

    @Size(max = 100)
    private String code;

    @NotNull
    private EducationalModuleType educationalModuleType;

    private Long priceCost;

    @Lob
    private String description;

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

    @Lob
    private String conversation;

    @Size(max = 50)
    private String changeStatusUserLogin;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long requestNiazsanjiFardiId;

    private String requestNiazsanjiFardiCode;

    private Long educationalModuleId;

    private String educationalModuleTitle;

    private Long personId;

    private String personName;

    private String personFamily;

    private Long organizationChartId;

    private String organizationChartTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EducationalModuleType getEducationalModuleType() {
        return educationalModuleType;
    }

    public void setEducationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
    }

    public Long getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Long priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public Long getRequestNiazsanjiFardiId() {
        return requestNiazsanjiFardiId;
    }

    public void setRequestNiazsanjiFardiId(Long requestNiazsanjiFardiId) {
        this.requestNiazsanjiFardiId = requestNiazsanjiFardiId;
    }

    public String getRequestNiazsanjiFardiCode() {
        return requestNiazsanjiFardiCode;
    }

    public void setRequestNiazsanjiFardiCode(String requestNiazsanjiFardiCode) {
        this.requestNiazsanjiFardiCode = requestNiazsanjiFardiCode;
    }

    public Long getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(Long educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonFamily() {
        return personFamily;
    }

    public void setPersonFamily(String personFamily) {
        this.personFamily = personFamily;
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

        NiazsanjiFardiDTO niazsanjiFardiDTO = (NiazsanjiFardiDTO) o;
        if (niazsanjiFardiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiFardiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiFardiDTO{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", code='" + getCode() + "'" +
            ", educationalModuleType='" + getEducationalModuleType() + "'" +
            ", priceCost=" + getPriceCost() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", conversation='" + getConversation() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", requestNiazsanjiFardi=" + getRequestNiazsanjiFardiId() +
            ", requestNiazsanjiFardi='" + getRequestNiazsanjiFardiCode() + "'" +
            ", educationalModule=" + getEducationalModuleId() +
            ", educationalModule='" + getEducationalModuleTitle() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
