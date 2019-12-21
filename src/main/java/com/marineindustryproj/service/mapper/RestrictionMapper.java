package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RestrictionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Restriction and its DTO RestrictionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RestrictionMapper extends EntityMapper<RestrictionDTO, Restriction> {

    @Mapping(target = "requestEducationalModules", ignore = true)
    @Mapping(target = "educationalModules", ignore = true)
    Restriction toEntity(RestrictionDTO restrictionDTO);

    default Restriction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Restriction restriction = new Restriction();
        restriction.setId(id);
        return restriction;
    }
}
