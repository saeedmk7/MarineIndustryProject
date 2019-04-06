package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JobTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobType and its DTO JobTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobTypeMapper extends EntityMapper<JobTypeDTO, JobType> {


    @Mapping(target = "jobs", ignore = true)
    JobType toEntity(JobTypeDTO jobTypeDTO);

    default JobType fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobType jobType = new JobType();
        jobType.setId(id);
        return jobType;
    }
}
