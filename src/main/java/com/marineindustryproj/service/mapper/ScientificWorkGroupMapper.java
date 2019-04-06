package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ScientificWorkGroup and its DTO ScientificWorkGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScientificWorkGroupMapper extends EntityMapper<ScientificWorkGroupDTO, ScientificWorkGroup> {


    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "niazsanjiGroups", ignore = true)
    @Mapping(target = "people", ignore = true)
    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    ScientificWorkGroup toEntity(ScientificWorkGroupDTO scientificWorkGroupDTO);

    default ScientificWorkGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScientificWorkGroup scientificWorkGroup = new ScientificWorkGroup();
        scientificWorkGroup.setId(id);
        return scientificWorkGroup;
    }
}
