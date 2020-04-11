package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelThreeScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelThreeScore and its DTO LevelThreeScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {LevelThreeCriteriaMapper.class, LevelThreeEffectivenessMapper.class})
public interface LevelThreeScoreMapper extends EntityMapper<LevelThreeScoreDTO, LevelThreeScore> {

    @Mapping(source = "levelThreeCriteria.id", target = "levelThreeCriteriaId")
    @Mapping(source = "levelThreeCriteria.title", target = "levelThreeCriteriaTitle")
    @Mapping(source = "levelThreeEffectiveness.id", target = "levelThreeEffectivenessId")
    @Mapping(source = "levelThreeEffectiveness.title", target = "levelThreeEffectivenessTitle")
    LevelThreeScoreDTO toDto(LevelThreeScore levelThreeScore);

    @Mapping(source = "levelThreeCriteriaId", target = "levelThreeCriteria")
    @Mapping(source = "levelThreeEffectivenessId", target = "levelThreeEffectiveness")
    LevelThreeScore toEntity(LevelThreeScoreDTO levelThreeScoreDTO);

    default LevelThreeScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelThreeScore levelThreeScore = new LevelThreeScore();
        levelThreeScore.setId(id);
        return levelThreeScore;
    }
}
