package com.marineindustryproj.service.dto.customs.CapitationReportModels;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.domain.enumeration.RequestStatus;

import java.util.List;

public class NiazsanjiFardiSummaryDTO {

    public NiazsanjiFardiSummaryDTO(Integer niazsanjiYear, RequestStatus requestStatus, PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.niazsanjiYear = niazsanjiYear;
        this.costEducationalModule = prioritizeRequestNiazsanji.getCostEducationalModule();
        this.status = prioritizeRequestNiazsanji.getStatus();
        this.requestStatus = requestStatus;
        this.courseType = prioritizeRequestNiazsanji.getCourseType();
        this.person = prioritizeRequestNiazsanji.getPerson();
        this.organizationChart = prioritizeRequestNiazsanji.getOrganizationChart();
        this.educationalModule = prioritizeRequestNiazsanji.getEducationalModule();
        if(this.educationalModule != null)
        {
            this.personHour =
                (this.educationalModule.getLearningTimePractical() != null ? this.educationalModule.getLearningTimePractical() : 0)
                +
                (this.educationalModule.getLearningTimePractical() != null ? this.educationalModule.getLearningTimePractical() : 0);
        }
    }

    private Integer niazsanjiYear;
    private Long costEducationalModule;
    private Integer status;
    private RequestStatus requestStatus;
    private CourseType courseType;
    private Integer personHour;
    private EducationalModule educationalModule;
    private Person person;
    private OrganizationChart organizationChart;

    public Integer getPersonHour() {
        return personHour;
    }

    public void setPersonHour(Integer personHour) {
        this.personHour = personHour;
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public Long getCostEducationalModule() {
        return costEducationalModule;
    }

    public void setCostEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }
}
