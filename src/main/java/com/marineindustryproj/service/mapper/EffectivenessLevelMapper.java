package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EffectivenessLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EffectivenessLevel and its DTO EffectivenessLevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EffectivenessLevelMapper extends EntityMapper<EffectivenessLevelDTO, EffectivenessLevel> {


    @Mapping(target = "designAndPlannings", ignore = true)
    EffectivenessLevel toEntity(EffectivenessLevelDTO effectivenessLevelDTO);

    default EffectivenessLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        EffectivenessLevel effectivenessLevel = new EffectivenessLevel();
        effectivenessLevel.setId(id);
        return effectivenessLevel;
    }
}
