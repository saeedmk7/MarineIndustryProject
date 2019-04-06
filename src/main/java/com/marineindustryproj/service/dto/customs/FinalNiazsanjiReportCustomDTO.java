package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.OrganizationChart;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class FinalNiazsanjiReportCustomDTO implements Serializable {


    public FinalNiazsanjiReportCustomDTO(Long id,
                                         Integer priceCost,
                                         EducationalModule educationalModule,
                                         Integer status) {
        this.id = id;
        this.priceCost = priceCost.longValue();
        this.status = status;
        this.totalLearningTime = (educationalModule.getLearningTimePractical() > 0 ? educationalModule.getLearningTimePractical().longValue() : 0) + (educationalModule.getLearningTimeTheorical() > 0 ? educationalModule.getLearningTimeTheorical().longValue() : 0);
    }

    private Long id;

    private Long priceCost;

    private Integer status;

    private Long totalLearningTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalNiazsanjiReportCustomDTO finalNiazsanjiReportDTO = (FinalNiazsanjiReportCustomDTO) o;
        if (finalNiazsanjiReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportDTO.getId());
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
            ", status=" + getStatus() +
            ", totalLearningTime=" + getTotalLearningTime() +
            "}";
    }
}
