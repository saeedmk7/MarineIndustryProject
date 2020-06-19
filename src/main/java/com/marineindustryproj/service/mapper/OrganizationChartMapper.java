package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.OrganizationChartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrganizationChart and its DTO OrganizationChartDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationChartMapper extends EntityMapper<OrganizationChartDTO, OrganizationChart> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.title", target = "parentTitle")
    OrganizationChartDTO toDto(OrganizationChart organizationChart);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "organizationCharts", ignore = true)
    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "runPhases", ignore = true)
    @Mapping(target = "organizationChartAuthorities", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "requestNiazsanjiFardis", ignore = true)
    @Mapping(target = "educationalHistories", ignore = true)
    @Mapping(target = "investToGroupTransactions", ignore = true)
    @Mapping(target = "mediaAwarenessReports", ignore = true)
    @Mapping(target = "preJobNiazsanjis", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(target = "soldiers", ignore = true)
    @Mapping(target = "evaluateCriteriaTrainings", ignore = true)
    @Mapping(target = "evaluateCriteriaData", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "forceRunningPercents", ignore = true)
    @Mapping(target = "reportGenerators", ignore = true)
    OrganizationChart toEntity(OrganizationChartDTO organizationChartDTO);

    default OrganizationChart fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganizationChart organizationChart = new OrganizationChart();
        organizationChart.setId(id);
        return organizationChart;
    }
}
