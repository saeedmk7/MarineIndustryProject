package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import com.marineindustryproj.domain.CourseType;
import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.OrganizationChart;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class FinalNiazsanjiReportHomePageDTO implements Serializable {

    public FinalNiazsanjiReportHomePageDTO(Long id,
                                           HomePageReportType homePageReportType,
                                           Integer priceCost,
                                           Integer finalizeCost,
                                           EducationalModule educationalModule,
                                           Integer status,
                                           Integer niazsanjiYear,
                                           CourseType courseType,
                                           OrganizationChart organizationChart,
                                           Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople,
                                           Boolean isManager) {
        this.id = id;

        this.isManager = isManager;
        this.priceCost = priceCost != null ? priceCost.longValue() : 0;
        this.finalizeCost = finalizeCost != null ? finalizeCost.longValue() : 0;
        this.status = status;
        this.niazsanjiYear = niazsanjiYear;
        this.totalLearningTime = (educationalModule.getLearningTimePractical() > 0 ? educationalModule.getLearningTimePractical().longValue() : 0) + (educationalModule.getLearningTimeTheorical() > 0 ? educationalModule.getLearningTimeTheorical().longValue() : 0);
        this.courseTypeId = courseType.getId();
        this.courseTypeTitle = courseType.getTitle();
        this.organizationChartId = organizationChart.getId();
        this.organizationChartTitle = organizationChart.getTitle();
        this.jobIds = finalNiazsanjiReportPeople.stream().mapToLong(a -> a.getPerson().getJob().getId()).distinct().toArray();
        this.peopleCount = finalNiazsanjiReportPeople.size();

        if(homePageReportType == HomePageReportType.PersonHour)
            this.value = this.totalLearningTime * this.peopleCount;
        else
            this.value = this.priceCost;
    }

    private Long id;

    private Long value;

    private Long priceCost;

    private Long finalizeCost;

    private Integer status;

    private Long totalLearningTime;

    private Long courseTypeId;

    private String courseTypeTitle;

    private Long organizationChartId;

    private String organizationChartTitle;

    private Integer niazsanjiYear;

    private long[] jobIds;

    private long peopleCount;

    private boolean isManager;

    public Long getTotalLearningTime() {
        return totalLearningTime;
    }

    public void setTotalLearningTime(Long totalLearningTime) {
        this.totalLearningTime = totalLearningTime;
    }

    public Long getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Long priceCost) {
        this.priceCost = priceCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinalizeCost() {
        return finalizeCost;
    }

    public void setFinalizeCost(Long finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }

    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    public long[] getJobIds() {
        return jobIds;
    }

    public void setJobIds(long[] jobIds) {
        this.jobIds = jobIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalNiazsanjiReportHomePageDTO finalNiazsanjiReportDTO = (FinalNiazsanjiReportHomePageDTO) o;
        if (finalNiazsanjiReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportDTO.getId());
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public long getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(long peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportDTO{" +
            "id=" + getId() +
            ", priceCost=" + getPriceCost() +
            ", finalizeCost=" + getFinalizeCost() +
            ", status=" + getStatus() +
            ", totalLearningTime=" + getTotalLearningTime() +
            "}";
    }


    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
