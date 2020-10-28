package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the MonitorLearningProcess entity.
 */
public class MonitorLearningProcessDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4096)
    private String title;

    @Size(max = 4096)
    private String code;

    @Size(max = 50)
    private String processDate;

    @Size(max = 4096)
    private String classification;

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

    private Long monitorProcessDurationId;

    private String monitorProcessDurationTitle;

    private Set<MonitorLearningProcessGradeDTO> monitorLearningProcessGrades = new HashSet<>();

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

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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

    public Long getMonitorProcessDurationId() {
        return monitorProcessDurationId;
    }

    public void setMonitorProcessDurationId(Long monitorProcessDurationId) {
        this.monitorProcessDurationId = monitorProcessDurationId;
    }

    public String getMonitorProcessDurationTitle() {
        return monitorProcessDurationTitle;
    }

    public void setMonitorProcessDurationTitle(String monitorProcessDurationTitle) {
        this.monitorProcessDurationTitle = monitorProcessDurationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MonitorLearningProcessDTO monitorLearningProcessDTO = (MonitorLearningProcessDTO) o;
        if (monitorLearningProcessDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorLearningProcessDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", processDate='" + getProcessDate() + "'" +
            ", classification='" + getClassification() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", monitorProcessDuration=" + getMonitorProcessDurationId() +
            ", monitorProcessDuration='" + getMonitorProcessDurationTitle() + "'" +
            "}";
    }

    public Set<MonitorLearningProcessGradeDTO> getMonitorLearningProcessGrades() {
        return monitorLearningProcessGrades;
    }

    public void setMonitorLearningProcessGrades(Set<MonitorLearningProcessGradeDTO> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
    }
}
