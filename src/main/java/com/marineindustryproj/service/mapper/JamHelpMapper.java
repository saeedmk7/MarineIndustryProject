package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JamHelpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JamHelp and its DTO JamHelpDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JamHelpMapper extends EntityMapper<JamHelpDTO, JamHelp> {

    @Mapping(target = "jamHelpAuthorities", ignore = true)
    JamHelp toEntity(JamHelpDTO jamHelpDTO);

    default JamHelp fromId(Long id) {
        if (id == null) {
            return null;
        }
        JamHelp jamHelp = new JamHelp();
        jamHelp.setId(id);
        return jamHelp;
    }
}
