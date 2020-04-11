package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EffectivenessPhaseLevel and its DTO EffectivenessPhaseLevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EffectivenessPhaseLevelMapper extends EntityMapper<EffectivenessPhaseLevelDTO, EffectivenessPhaseLevel> {


    @Mapping(target = "effectivenessPhases", ignore = true)
    EffectivenessPhaseLevel toEntity(EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO);

    default EffectivenessPhaseLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        EffectivenessPhaseLevel effectivenessPhaseLevel = new EffectivenessPhaseLevel();
        effectivenessPhaseLevel.setId(id);
        return effectivenessPhaseLevel;
    }
}
