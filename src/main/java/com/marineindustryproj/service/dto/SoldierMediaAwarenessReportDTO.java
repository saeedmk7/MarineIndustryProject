package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SoldierMediaAwarenessReport entity.
 */
public class SoldierMediaAwarenessReportDTO implements Serializable {

    private Long id;

    @Size(max = 4096)
    private String title;

    @NotNull
    private Integer personHour;

    @Size(max = 4096)
    private String executiveTrainingCompany;

    @Size(max = 4096)
    private String certificateStatus;

    @Size(max = 4096)
    private String certificateNumber;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer reportTime;

    @Lob
    private String fileDoc;

    @Size(max = 4096)
    private String description;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Set<DocumentDTO> documents = new HashSet<>();

    private Long soldierId;

    private String soldierFamily;

    private String soldierName;

    private ZonedDateTime soldierEmploymentDate;

    private String soldierNationalId;

    private Long soldierOrganizationChartId;

    private String soldierOrganizationChartTitle;

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

    public Integer getPersonHour() {
        return personHour;
    }

    public void setPersonHour(Integer personHour) {
        this.personHour = personHour;
    }

    public String getExecutiveTrainingCompany() {
        return executiveTrainingCompany;
    }

    public void setExecutiveTrainingCompany(String executiveTrainingCompany) {
        this.executiveTrainingCompany = executiveTrainingCompany;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
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

    public Long getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(Long soldierId) {
        this.soldierId = soldierId;
    }

    public String getSoldierFamily() {
        return soldierFamily;
    }

    public void setSoldierFamily(String soldierFamily) {
        this.soldierFamily = soldierFamily;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public void setSoldierName(String soldierName) {
        this.soldierName = soldierName;
    }

    public ZonedDateTime getSoldierEmploymentDate() {
        return soldierEmploymentDate;
    }

    public void setSoldierEmploymentDate(ZonedDateTime soldierEmploymentDate) {
        this.soldierEmploymentDate = soldierEmploymentDate;
    }

    public String getSoldierNationalId() {
        return soldierNationalId;
    }

    public void setSoldierNationalId(String soldierNationalId) {
        this.soldierNationalId = soldierNationalId;
    }

    public Long getSoldierOrganizationChartId() {
        return soldierOrganizationChartId;
    }

    public void setSoldierOrganizationChartId(Long soldierOrganizationChartId) {
        this.soldierOrganizationChartId = soldierOrganizationChartId;
    }

    public String getSoldierOrganizationChartTitle() {
        return soldierOrganizationChartTitle;
    }

    public void setSoldierOrganizationChartTitle(String soldierOrganizationChartTitle) {
        this.soldierOrganizationChartTitle = soldierOrganizationChartTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO = (SoldierMediaAwarenessReportDTO) o;
        if (soldierMediaAwarenessReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soldierMediaAwarenessReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SoldierMediaAwarenessReportDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", personHour=" + getPersonHour() +
            ", executiveTrainingCompany='" + getExecutiveTrainingCompany() + "'" +
            ", certificateStatus='" + getCertificateStatus() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", reportTime=" + getReportTime() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", soldier=" + getSoldierId() +
            ", soldier='" + getSoldierFamily() + "'" +
            ", soldier='" + getSoldierName() + "'" +
            ", soldier='" + getSoldierNationalId() + "'" +
            ", soldier='" + getSoldierEmploymentDate() + "'" +
            "}";
    }
}
