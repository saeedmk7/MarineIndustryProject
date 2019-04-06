package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RunningStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RunningStep and its DTO RunningStepDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RunningStepMapper extends EntityMapper<RunningStepDTO, RunningStep> {


    @Mapping(target = "runRunningSteps", ignore = true)
    RunningStep toEntity(RunningStepDTO runningStepDTO);

    default RunningStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        RunningStep runningStep = new RunningStep();
        runningStep.setId(id);
        return runningStep;
    }
}
