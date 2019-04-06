package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ToolsAndFacilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ToolsAndFacility and its DTO ToolsAndFacilityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ToolsAndFacilityMapper extends EntityMapper<ToolsAndFacilityDTO, ToolsAndFacility> {


    @Mapping(target = "designAndPlannings", ignore = true)
    ToolsAndFacility toEntity(ToolsAndFacilityDTO toolsAndFacilityDTO);

    default ToolsAndFacility fromId(Long id) {
        if (id == null) {
            return null;
        }
        ToolsAndFacility toolsAndFacility = new ToolsAndFacility();
        toolsAndFacility.setId(id);
        return toolsAndFacility;
    }
}
