package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the InvestToGroupTransaction entity.
 */
public class InvestToGroupTransactionDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String investDate;

    private Integer investYear;

    private Integer investAmount;

    @Size(max = 4096)
    private String checkNumber;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long organizationChartId;

    private String organizationChartTitle;

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

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate;
    }

    public Integer getInvestYear() {
        return investYear;
    }

    public void setInvestYear(Integer investYear) {
        this.investYear = investYear;
    }

    public Integer getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(Integer investAmount) {
        this.investAmount = investAmount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
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

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
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

        InvestToGroupTransactionDTO investToGroupTransactionDTO = (InvestToGroupTransactionDTO) o;
        if (investToGroupTransactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investToGroupTransactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestToGroupTransactionDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", investDate='" + getInvestDate() + "'" +
            ", investYear=" + getInvestYear() +
            ", investAmount=" + getInvestAmount() +
            ", checkNumber='" + getCheckNumber() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", organizationChart=" + getOrganizationChartId() +
            ", organizationChart='" + getOrganizationChartTitle() + "'" +
            "}";
    }
}
