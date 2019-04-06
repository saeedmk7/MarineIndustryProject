package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PollDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Poll and its DTO PollDTO.
 */
@Mapper(componentModel = "spring", uses = {FinalNiazsanjiReportMapper.class})
public interface PollMapper extends EntityMapper<PollDTO, Poll> {

    @Mapping(source = "finalNiazsanjiReport.id", target = "finalNiazsanjiReportId")
    @Mapping(source = "finalNiazsanjiReport.description", target = "finalNiazsanjiReportDescription")
    PollDTO toDto(Poll poll);

    @Mapping(target = "pollScores", ignore = true)
    @Mapping(source = "finalNiazsanjiReportId", target = "finalNiazsanjiReport")
    Poll toEntity(PollDTO pollDTO);

    default Poll fromId(Long id) {
        if (id == null) {
            return null;
        }
        Poll poll = new Poll();
        poll.setId(id);
        return poll;
    }
}
