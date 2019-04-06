package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EffectivenessIndexDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EffectivenessIndex and its DTO EffectivenessIndexDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EffectivenessIndexMapper extends EntityMapper<EffectivenessIndexDTO, EffectivenessIndex> {


    @Mapping(target = "designAndPlannings", ignore = true)
    EffectivenessIndex toEntity(EffectivenessIndexDTO effectivenessIndexDTO);

    default EffectivenessIndex fromId(Long id) {
        if (id == null) {
            return null;
        }
        EffectivenessIndex effectivenessIndex = new EffectivenessIndex();
        effectivenessIndex.setId(id);
        return effectivenessIndex;
    }
}
