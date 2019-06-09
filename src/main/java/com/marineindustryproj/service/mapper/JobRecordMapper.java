package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JobRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobRecord and its DTO JobRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface JobRecordMapper extends EntityMapper<JobRecordDTO, JobRecord> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    JobRecordDTO toDto(JobRecord jobRecord);

    @Mapping(source = "personId", target = "person")
    JobRecord toEntity(JobRecordDTO jobRecordDTO);

    default JobRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobRecord jobRecord = new JobRecord();
        jobRecord.setId(id);
        return jobRecord;
    }
}
