package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelThreeCriteriaGroup and its DTO LevelThreeCriteriaGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LevelThreeCriteriaGroupMapper extends EntityMapper<LevelThreeCriteriaGroupDTO, LevelThreeCriteriaGroup> {


    @Mapping(target = "levelThreeCriteria", ignore = true)
    LevelThreeCriteriaGroup toEntity(LevelThreeCriteriaGroupDTO levelThreeCriteriaGroupDTO);

    default LevelThreeCriteriaGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelThreeCriteriaGroup levelThreeCriteriaGroup = new LevelThreeCriteriaGroup();
        levelThreeCriteriaGroup.setId(id);
        return levelThreeCriteriaGroup;
    }
}
