package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachingRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachingRecord and its DTO TeachingRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface TeachingRecordMapper extends EntityMapper<TeachingRecordDTO, TeachingRecord> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    TeachingRecordDTO toDto(TeachingRecord teachingRecord);

    @Mapping(source = "personId", target = "person")
    TeachingRecord toEntity(TeachingRecordDTO teachingRecordDTO);

    default TeachingRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachingRecord teachingRecord = new TeachingRecord();
        teachingRecord.setId(id);
        return teachingRecord;
    }
}
