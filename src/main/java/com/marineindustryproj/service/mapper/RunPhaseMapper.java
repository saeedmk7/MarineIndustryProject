package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RunPhaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RunPhase and its DTO RunPhaseDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, PersonMapper.class, EducationalModuleMapper.class, FinalNiazsanjiReportMapper.class})
public interface RunPhaseMapper extends EntityMapper<RunPhaseDTO, RunPhase> {

    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "finalNiazsanjiReport.id", target = "finalNiazsanjiReportId")
    @Mapping(source = "finalNiazsanjiReport.description", target = "finalNiazsanjiReportDescription")
    RunPhaseDTO toDto(RunPhase runPhase);

    @Mapping(target = "runRunningSteps", ignore = true)
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "finalNiazsanjiReportId", target = "finalNiazsanjiReport")
    RunPhase toEntity(RunPhaseDTO runPhaseDTO);

    default RunPhase fromId(Long id) {
        if (id == null) {
            return null;
        }
        RunPhase runPhase = new RunPhase();
        runPhase.setId(id);
        return runPhase;
    }
}
