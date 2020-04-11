package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelFourCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelFourCriteria and its DTO LevelFourCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LevelFourCriteriaMapper extends EntityMapper<LevelFourCriteriaDTO, LevelFourCriteria> {


    @Mapping(target = "levelFourScores", ignore = true)
    LevelFourCriteria toEntity(LevelFourCriteriaDTO levelFourCriteriaDTO);

    default LevelFourCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelFourCriteria levelFourCriteria = new LevelFourCriteria();
        levelFourCriteria.setId(id);
        return levelFourCriteria;
    }
}
