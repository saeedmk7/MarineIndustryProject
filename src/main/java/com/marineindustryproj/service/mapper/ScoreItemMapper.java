package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ScoreItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ScoreItem and its DTO ScoreItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScoreItemMapper extends EntityMapper<ScoreItemDTO, ScoreItem> {


    @Mapping(target = "pollScores", ignore = true)
    ScoreItem toEntity(ScoreItemDTO scoreItemDTO);

    default ScoreItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setId(id);
        return scoreItem;
    }
}
