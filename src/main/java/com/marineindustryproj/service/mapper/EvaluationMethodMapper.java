package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EvaluationMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EvaluationMethod and its DTO EvaluationMethodDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EvaluationMethodMapper extends EntityMapper<EvaluationMethodDTO, EvaluationMethod> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    EvaluationMethod toEntity(EvaluationMethodDTO evaluationMethodDTO);

    default EvaluationMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluationMethod evaluationMethod = new EvaluationMethod();
        evaluationMethod.setId(id);
        return evaluationMethod;
    }
}
