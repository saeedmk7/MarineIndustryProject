package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PollScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PollScore and its DTO PollScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {PollItemMapper.class, ScoreItemMapper.class, PollMapper.class, PersonMapper.class})
public interface PollScoreMapper extends EntityMapper<PollScoreDTO, PollScore> {

    @Mapping(source = "pollItem.id", target = "pollItemId")
    @Mapping(source = "pollItem.title", target = "pollItemTitle")
    @Mapping(source = "scoreItem.id", target = "scoreItemId")
    @Mapping(source = "scoreItem.title", target = "scoreItemTitle")
    @Mapping(source = "poll.id", target = "pollId")
    @Mapping(source = "poll.moreRecommendation", target = "pollMoreRecommendation")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    PollScoreDTO toDto(PollScore pollScore);

    @Mapping(source = "pollItemId", target = "pollItem")
    @Mapping(source = "scoreItemId", target = "scoreItem")
    @Mapping(source = "pollId", target = "poll")
    @Mapping(source = "personId", target = "person")
    PollScore toEntity(PollScoreDTO pollScoreDTO);

    default PollScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        PollScore pollScore = new PollScore();
        pollScore.setId(id);
        return pollScore;
    }
}
