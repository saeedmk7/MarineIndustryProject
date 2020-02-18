package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EvaluateCriteriaTraining and its DTO EvaluateCriteriaTrainingDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, OrganizationChartMapper.class})
public interface EvaluateCriteriaTrainingMapper extends EntityMapper<EvaluateCriteriaTrainingDTO, EvaluateCriteriaTraining> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    EvaluateCriteriaTrainingDTO toDto(EvaluateCriteriaTraining evaluateCriteriaTraining);

    @Mapping(target = "evaluateCriteriaData", ignore = true)
    @Mapping(source = "organizationChartId", target = "organizationChart")
    EvaluateCriteriaTraining toEntity(EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO);

    default EvaluateCriteriaTraining fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateCriteriaTraining evaluateCriteriaTraining = new EvaluateCriteriaTraining();
        evaluateCriteriaTraining.setId(id);
        return evaluateCriteriaTraining;
    }
}
