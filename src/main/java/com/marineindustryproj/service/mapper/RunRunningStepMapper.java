package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RunRunningStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RunRunningStep and its DTO RunRunningStepDTO.
 */
@Mapper(componentModel = "spring", uses = {RunPhaseMapper.class, RunningStepMapper.class})
public interface RunRunningStepMapper extends EntityMapper<RunRunningStepDTO, RunRunningStep> {

    @Mapping(source = "runPhase.id", target = "runPhaseId")
    @Mapping(source = "runPhase.description", target = "runPhaseDescription")
    @Mapping(source = "runningStep.id", target = "runningStepId")
    @Mapping(source = "runningStep.title", target = "runningStepTitle")
    RunRunningStepDTO toDto(RunRunningStep runRunningStep);

    @Mapping(source = "runPhaseId", target = "runPhase")
    @Mapping(source = "runningStepId", target = "runningStep")
    RunRunningStep toEntity(RunRunningStepDTO runRunningStepDTO);

    default RunRunningStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        RunRunningStep runRunningStep = new RunRunningStep();
        runRunningStep.setId(id);
        return runRunningStep;
    }
}
