package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NavBarItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NavBarItem and its DTO NavBarItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NavBarItemMapper extends EntityMapper<NavBarItemDTO, NavBarItem> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.title", target = "parentTitle")
    NavBarItemDTO toDto(NavBarItem navBarItem);

    @Mapping(target = "navBarItems", ignore = true)
    @Mapping(target = "navBarItemAuthorities", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    NavBarItem toEntity(NavBarItemDTO navBarItemDTO);

    default NavBarItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        NavBarItem navBarItem = new NavBarItem();
        navBarItem.setId(id);
        return navBarItem;
    }
}
