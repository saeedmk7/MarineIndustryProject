package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EvaluatorOpinionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EvaluatorOpinion and its DTO EvaluatorOpinionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EvaluatorOpinionMapper extends EntityMapper<EvaluatorOpinionDTO, EvaluatorOpinion> {


    @Mapping(target = "educationalCenterGrades", ignore = true)
    EvaluatorOpinion toEntity(EvaluatorOpinionDTO evaluatorOpinionDTO);

    default EvaluatorOpinion fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluatorOpinion evaluatorOpinion = new EvaluatorOpinion();
        evaluatorOpinion.setId(id);
        return evaluatorOpinion;
    }
}
