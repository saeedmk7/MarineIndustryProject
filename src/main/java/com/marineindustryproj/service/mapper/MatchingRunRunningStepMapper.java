package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MatchingRunRunningStep and its DTO MatchingRunRunningStepDTO.
 */
@Mapper(componentModel = "spring", uses = {MatchingEducationalRecordMapper.class, MatchingRunningStepMapper.class})
public interface MatchingRunRunningStepMapper extends EntityMapper<MatchingRunRunningStepDTO, MatchingRunRunningStep> {

    @Mapping(source = "matchingEducationalRecord.id", target = "matchingEducationalRecordId")
    @Mapping(source = "matchingEducationalRecord.code", target = "matchingEducationalRecordCode")
    @Mapping(source = "matchingRunningStep.id", target = "matchingRunningStepId")
    @Mapping(source = "matchingRunningStep.title", target = "matchingRunningStepTitle")
    MatchingRunRunningStepDTO toDto(MatchingRunRunningStep matchingRunRunningStep);

    @Mapping(source = "matchingEducationalRecordId", target = "matchingEducationalRecord")
    @Mapping(source = "matchingRunningStepId", target = "matchingRunningStep")
    MatchingRunRunningStep toEntity(MatchingRunRunningStepDTO matchingRunRunningStepDTO);

    default MatchingRunRunningStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        MatchingRunRunningStep matchingRunRunningStep = new MatchingRunRunningStep();
        matchingRunRunningStep.setId(id);
        return matchingRunRunningStep;
    }
}
