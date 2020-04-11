package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EffectivenessPhaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EffectivenessPhase and its DTO EffectivenessPhaseDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, FinalNiazsanjiReportMapper.class, EffectivenessPhaseLevelMapper.class})
public interface EffectivenessPhaseMapper extends EntityMapper<EffectivenessPhaseDTO, EffectivenessPhase> {

    @Mapping(source = "finalNiazsanjiReport", target = "finalNiazsanjiReport")
    @Mapping(source = "finalNiazsanjiReport.id", target = "finalNiazsanjiReportId")
    @Mapping(source = "finalNiazsanjiReport.description", target = "finalNiazsanjiReportDescription")
    @Mapping(source = "effectivenessPhaseLevel.id", target = "effectivenessPhaseLevelId")
    @Mapping(source = "effectivenessPhaseLevel.title", target = "effectivenessPhaseLevelTitle")
    @Mapping(source = "effectivenessPhaseLevel", target = "effectivenessPhaseLevel")

    EffectivenessPhaseDTO toDto(EffectivenessPhase effectivenessPhase);

    @Mapping(source = "finalNiazsanjiReportId", target = "finalNiazsanjiReport")
    @Mapping(source = "effectivenessPhaseLevelId", target = "effectivenessPhaseLevel")
    EffectivenessPhase toEntity(EffectivenessPhaseDTO effectivenessPhaseDTO);

    default EffectivenessPhase fromId(Long id) {
        if (id == null) {
            return null;
        }
        EffectivenessPhase effectivenessPhase = new EffectivenessPhase();
        effectivenessPhase.setId(id);
        return effectivenessPhase;
    }
}
