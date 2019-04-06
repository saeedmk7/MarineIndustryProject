package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RasteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Raste and its DTO RasteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RasteMapper extends EntityMapper<RasteDTO, Raste> {


    @Mapping(target = "jobs", ignore = true)
    Raste toEntity(RasteDTO rasteDTO);

    default Raste fromId(Long id) {
        if (id == null) {
            return null;
        }
        Raste raste = new Raste();
        raste.setId(id);
        return raste;
    }
}
