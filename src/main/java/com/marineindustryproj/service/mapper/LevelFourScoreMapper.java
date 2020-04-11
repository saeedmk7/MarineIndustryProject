package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelFourScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelFourScore and its DTO LevelFourScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {LevelFourCriteriaMapper.class, LevelFourEffectivenessMapper.class})
public interface LevelFourScoreMapper extends EntityMapper<LevelFourScoreDTO, LevelFourScore> {

    @Mapping(source = "levelFourCriteria.id", target = "levelFourCriteriaId")
    @Mapping(source = "levelFourCriteria.title", target = "levelFourCriteriaTitle")
    @Mapping(source = "levelFourEffectiveness.id", target = "levelFourEffectivenessId")
    @Mapping(source = "levelFourEffectiveness.title", target = "levelFourEffectivenessTitle")
    LevelFourScoreDTO toDto(LevelFourScore levelFourScore);

    @Mapping(source = "levelFourCriteriaId", target = "levelFourCriteria")
    @Mapping(source = "levelFourEffectivenessId", target = "levelFourEffectiveness")
    LevelFourScore toEntity(LevelFourScoreDTO levelFourScoreDTO);

    default LevelFourScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelFourScore levelFourScore = new LevelFourScore();
        levelFourScore.setId(id);
        return levelFourScore;
    }
}
