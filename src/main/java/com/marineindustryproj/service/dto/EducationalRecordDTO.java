package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EducationalRecord entity.
 */
public class EducationalRecordDTO implements Serializable {

    private Long id;

    @Size(max = 1000)
    private String qualificationText;

    @Size(max = 1000)
    private String fieldOfStudyText;

    @Size(max = 1000)
    private String educationalCenterText;

    @Size(max = 50)
    private String totalAverage;

    private Integer totalHour;

    @Size(max = 50)
    private String startDate;

    @Size(max = 50)
    private String endDate;

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

    private Long qualificationId;

    private String qualificationTitle;

    private Long fieldOfStudyId;

    private String fieldOfStudyTitle;

    private Long personId;

    private String personFamily;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualificationText() {
        return qualificationText;
    }

    public void setQualificationText(String qualificationText) {
        this.qualificationText = qualificationText;
    }

    public String getFieldOfStudyText() {
        return fieldOfStudyText;
    }

    public void setFieldOfStudyText(String fieldOfStudyText) {
        this.fieldOfStudyText = fieldOfStudyText;
    }

    public String getEducationalCenterText() {
        return educationalCenterText;
    }

    public void setEducationalCenterText(String educationalCenterText) {
        this.educationalCenterText = educationalCenterText;
    }

    public String getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(String totalAverage) {
        this.totalAverage = totalAverage;
    }

    public Integer getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Integer totalHour) {
        this.totalHour = totalHour;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationTitle() {
        return qualificationTitle;
    }

    public void setQualificationTitle(String qualificationTitle) {
        this.qualificationTitle = qualificationTitle;
    }

    public Long getFieldOfStudyId() {
        return fieldOfStudyId;
    }

    public void setFieldOfStudyId(Long fieldOfStudyId) {
        this.fieldOfStudyId = fieldOfStudyId;
    }

    public String getFieldOfStudyTitle() {
        return fieldOfStudyTitle;
    }

    public void setFieldOfStudyTitle(String fieldOfStudyTitle) {
        this.fieldOfStudyTitle = fieldOfStudyTitle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalRecordDTO educationalRecordDTO = (EducationalRecordDTO) o;
        if (educationalRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalRecordDTO{" +
            "id=" + getId() +
            ", qualificationText='" + getQualificationText() + "'" +
            ", fieldOfStudyText='" + getFieldOfStudyText() + "'" +
            ", educationalCenterText='" + getEducationalCenterText() + "'" +
            ", totalAverage='" + getTotalAverage() + "'" +
            ", totalHour=" + getTotalHour() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            ", qualification=" + getQualificationId() +
            ", qualification='" + getQualificationTitle() + "'" +
            ", fieldOfStudy=" + getFieldOfStudyId() +
            ", fieldOfStudy='" + getFieldOfStudyTitle() + "'" +
            ", person=" + getPersonId() +
            ", person='" + getPersonFamily() + "'" +
            "}";
    }
}
