package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachType and its DTO TeachTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeachTypeMapper extends EntityMapper<TeachTypeDTO, TeachType> {


    @Mapping(target = "designAndPlannings", ignore = true)
    TeachType toEntity(TeachTypeDTO teachTypeDTO);

    default TeachType fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachType teachType = new TeachType();
        teachType.setId(id);
        return teachType;
    }
}
