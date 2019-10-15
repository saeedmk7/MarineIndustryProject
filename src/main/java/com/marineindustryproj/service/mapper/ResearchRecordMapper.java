package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ResearchRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResearchRecord and its DTO ResearchRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface ResearchRecordMapper extends EntityMapper<ResearchRecordDTO, ResearchRecord> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    ResearchRecordDTO toDto(ResearchRecord researchRecord);

    @Mapping(source = "personId", target = "person")
    ResearchRecord toEntity(ResearchRecordDTO researchRecordDTO);

    default ResearchRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResearchRecord researchRecord = new ResearchRecord();
        researchRecord.setId(id);
        return researchRecord;
    }
}
