package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PollItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PollItem and its DTO PollItemDTO.
 */
@Mapper(componentModel = "spring", uses = {CriterionMapper.class})
public interface PollItemMapper extends EntityMapper<PollItemDTO, PollItem> {

    @Mapping(source = "criterion.id", target = "criterionId")
    @Mapping(source = "criterion.title", target = "criterionTitle")
    PollItemDTO toDto(PollItem pollItem);

    @Mapping(target = "pollScores", ignore = true)
    @Mapping(source = "criterionId", target = "criterion")
    PollItem toEntity(PollItemDTO pollItemDTO);

    default PollItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        PollItem pollItem = new PollItem();
        pollItem.setId(id);
        return pollItem;
    }
}
