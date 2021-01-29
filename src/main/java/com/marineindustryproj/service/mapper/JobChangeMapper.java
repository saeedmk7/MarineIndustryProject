package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JobChangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobChange and its DTO JobChangeDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, JobMapper.class})
public interface JobChangeMapper extends EntityMapper<JobChangeDTO, JobChange> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "oldJob.id", target = "oldJobId")
    @Mapping(source = "oldJob.title", target = "oldJobTitle")
    @Mapping(source = "newJob.id", target = "newJobId")
    @Mapping(source = "newJob.title", target = "newJobTitle")
    JobChangeDTO toDto(JobChange jobChange);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "oldJobId", target = "oldJob")
    @Mapping(source = "newJobId", target = "newJob")
    JobChange toEntity(JobChangeDTO jobChangeDTO);

    default JobChange fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobChange jobChange = new JobChange();
        jobChange.setId(id);
        return jobChange;
    }
}
