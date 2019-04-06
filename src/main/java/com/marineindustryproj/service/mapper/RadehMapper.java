package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RadehDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Radeh and its DTO RadehDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RadehMapper extends EntityMapper<RadehDTO, Radeh> {


    @Mapping(target = "jobs", ignore = true)
    Radeh toEntity(RadehDTO radehDTO);

    default Radeh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Radeh radeh = new Radeh();
        radeh.setId(id);
        return radeh;
    }
}
