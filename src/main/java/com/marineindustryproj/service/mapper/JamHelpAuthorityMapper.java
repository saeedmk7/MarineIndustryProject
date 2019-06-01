package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JamHelpAuthority and its DTO JamHelpAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {JamHelpMapper.class})
public interface JamHelpAuthorityMapper extends EntityMapper<JamHelpAuthorityDTO, JamHelpAuthority> {

    @Mapping(source = "jamHelp.id", target = "jamHelpId")
    @Mapping(source = "jamHelp.title", target = "jamHelpTitle")
    JamHelpAuthorityDTO toDto(JamHelpAuthority jamHelpAuthority);

    @Mapping(source = "jamHelpId", target = "jamHelp")
    JamHelpAuthority toEntity(JamHelpAuthorityDTO jamHelpAuthorityDTO);

    default JamHelpAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        JamHelpAuthority jamHelpAuthority = new JamHelpAuthority();
        jamHelpAuthority.setId(id);
        return jamHelpAuthority;
    }
}
