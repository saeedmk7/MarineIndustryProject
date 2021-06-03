package com.marineindustryproj.service.dto.customs.CapitationReportModels;

import com.marineindustryproj.domain.CourseType;
import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.service.dto.CourseTypeDTO;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import com.marineindustryproj.service.dto.PersonDTO;

import java.util.List;
import java.util.Set;

public class NiazsanjiOrganizationSummaryDTO {

    public NiazsanjiOrganizationSummaryDTO(Integer niazsanjiYear, Integer priceCost, Integer status, RequestStatus requestStatus,
                                           CourseTypeDTO courseType, EducationalModuleDTO educationalModule,
                                           OrganizationChartDTO organizationChart, Set<PersonDTO> people) {
        this.niazsanjiYear = niazsanjiYear;
        this.priceCost = priceCost;
        this.status = status;
        this.requestStatus = requestStatus;
        this.courseType = courseType;
        this.educationalModule = educationalModule;
        this.organizationChart = organizationChart;
        this.people = people;
        if(this.educationalModule != null && (!this.people.isEmpty()))
        {
            this.personHour =
                (this.educationalModule.getLearningTimePractical() != null ? this.educationalModule.getLearningTimePractical() : 0)
                    +
                    (this.educationalModule.getLearningTimePractical() != null ? this.educationalModule.getLearningTimePractical() : 0);
            this.totalPersonHour = this.personHour * this.people.stream().count();
        }
    }

    private Integer niazsanjiYear;
    private Integer priceCost;
    private Integer status;
    private RequestStatus requestStatus;
    private CourseTypeDTO courseType;
    private EducationalModuleDTO educationalModule;
    private OrganizationChartDTO organizationChart;
    private Set<PersonDTO> people;
    private long personHour;
    private long totalPersonHour;

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
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

    public CourseTypeDTO getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeDTO courseType) {
        this.courseType = courseType;
    }

    public EducationalModuleDTO getEducationalModule() {
        return educationalModule;
    }

    public void setEducationalModule(EducationalModuleDTO educationalModule) {
        this.educationalModule = educationalModule;
    }

    public OrganizationChartDTO getOrganizationChart() {
        return organizationChart;
    }

    public void setOrganizationChart(OrganizationChartDTO organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Set<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonDTO> people) {
        this.people = people;
    }

    public long getPersonHour() {
        return personHour;
    }

    public void setPersonHour(long personHour) {
        this.personHour = personHour;
    }

    public long getTotalPersonHour() {
        return totalPersonHour;
    }

    public void setTotalPersonHour(long totalPersonHour) {
        this.totalPersonHour = totalPersonHour;
    }
}
