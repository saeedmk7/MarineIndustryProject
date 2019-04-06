package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NavBarItemAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NavBarItemAuthority and its DTO NavBarItemAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {NavBarItemMapper.class})
public interface NavBarItemAuthorityMapper extends EntityMapper<NavBarItemAuthorityDTO, NavBarItemAuthority> {

    @Mapping(source = "navBarItem.id", target = "navBarItemId")
    @Mapping(source = "navBarItem.title", target = "navBarItemTitle")
    NavBarItemAuthorityDTO toDto(NavBarItemAuthority navBarItemAuthority);

    @Mapping(source = "navBarItemId", target = "navBarItem")
    NavBarItemAuthority toEntity(NavBarItemAuthorityDTO navBarItemAuthorityDTO);

    default NavBarItemAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        NavBarItemAuthority navBarItemAuthority = new NavBarItemAuthority();
        navBarItemAuthority.setId(id);
        return navBarItemAuthority;
    }
}
