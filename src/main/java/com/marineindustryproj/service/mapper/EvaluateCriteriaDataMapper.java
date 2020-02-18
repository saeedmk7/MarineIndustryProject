package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EvaluateCriteriaData and its DTO EvaluateCriteriaDataDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, EvaluateCriteriaTrainingMapper.class, OrganizationChartMapper.class})
public interface EvaluateCriteriaDataMapper extends EntityMapper<EvaluateCriteriaDataDTO, EvaluateCriteriaData> {

    @Mapping(source = "evaluateCriteriaTraining.id", target = "evaluateCriteriaTrainingId")
    @Mapping(source = "evaluateCriteriaTraining.processTitle", target = "evaluateCriteriaTrainingProcessTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    EvaluateCriteriaDataDTO toDto(EvaluateCriteriaData evaluateCriteriaData);

    @Mapping(source = "evaluateCriteriaTrainingId", target = "evaluateCriteriaTraining")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    EvaluateCriteriaData toEntity(EvaluateCriteriaDataDTO evaluateCriteriaDataDTO);

    default EvaluateCriteriaData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateCriteriaData evaluateCriteriaData = new EvaluateCriteriaData();
        evaluateCriteriaData.setId(id);
        return evaluateCriteriaData;
    }
}
