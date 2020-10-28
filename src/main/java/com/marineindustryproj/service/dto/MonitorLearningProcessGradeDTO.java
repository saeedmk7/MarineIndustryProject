package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MonitorLearningProcessGrade entity.
 */
public class MonitorLearningProcessGradeDTO implements Serializable {

    private Long id;

    private Float firstNumber;

    private Float secondNumber;

    private Float thirdNumber;

    @NotNull
    private Float result;

    private Integer goal;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    @Size(max = 50)
    private String modifyUserLogin;

    private ZonedDateTime modifyDate;

    @Size(max = 50)
    private String guid;

    private Long monitorLearningProcessLevelId;

    private String monitorLearningProcessLevelTitle;

    private Long monitorLearningProcessId;

    private String monitorLearningProcessTitle;

    private Long monitorProcessDurationId;

    private String monitorProcessDurationTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Float getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Float getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
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

    public Long getMonitorLearningProcessLevelId() {
        return monitorLearningProcessLevelId;
    }

    public void setMonitorLearningProcessLevelId(Long monitorLearningProcessLevelId) {
        this.monitorLearningProcessLevelId = monitorLearningProcessLevelId;
    }

    public String getMonitorLearningProcessLevelTitle() {
        return monitorLearningProcessLevelTitle;
    }

    public void setMonitorLearningProcessLevelTitle(String monitorLearningProcessLevelTitle) {
        this.monitorLearningProcessLevelTitle = monitorLearningProcessLevelTitle;
    }

    public Long getMonitorLearningProcessId() {
        return monitorLearningProcessId;
    }

    public void setMonitorLearningProcessId(Long monitorLearningProcessId) {
        this.monitorLearningProcessId = monitorLearningProcessId;
    }

    public String getMonitorLearningProcessTitle() {
        return monitorLearningProcessTitle;
    }

    public void setMonitorLearningProcessTitle(String monitorLearningProcessTitle) {
        this.monitorLearningProcessTitle = monitorLearningProcessTitle;
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

        MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO = (MonitorLearningProcessGradeDTO) o;
        if (monitorLearningProcessGradeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorLearningProcessGradeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessGradeDTO{" +
            "id=" + getId() +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", result=" + getResult() +
            ", goal=" + getGoal() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", monitorLearningProcessLevel=" + getMonitorLearningProcessLevelId() +
            ", monitorLearningProcessLevel='" + getMonitorLearningProcessLevelTitle() + "'" +
            ", monitorLearningProcess=" + getMonitorLearningProcessId() +
            ", monitorLearningProcess='" + getMonitorLearningProcessTitle() + "'" +
            ", monitorProcessDuration=" + getMonitorProcessDurationId() +
            ", monitorProcessDuration='" + getMonitorProcessDurationTitle() + "'" +
            "}";
    }
}
