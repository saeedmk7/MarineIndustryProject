package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MatchingRunningStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MatchingRunningStep and its DTO MatchingRunningStepDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MatchingRunningStepMapper extends EntityMapper<MatchingRunningStepDTO, MatchingRunningStep> {


    @Mapping(target = "matchingRunRunningSteps", ignore = true)
    MatchingRunningStep toEntity(MatchingRunningStepDTO matchingRunningStepDTO);

    default MatchingRunningStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        MatchingRunningStep matchingRunningStep = new MatchingRunningStep();
        matchingRunningStep.setId(id);
        return matchingRunningStep;
    }
}
