package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalModuleJobDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalModuleJob and its DTO EducationalModuleJobDTO.
 */
@Mapper(componentModel = "spring", uses = {EducationalModuleMapper.class, JobMapper.class})
public interface EducationalModuleJobMapper extends EntityMapper<EducationalModuleJobDTO, EducationalModuleJob> {

    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "job.jobCode", target = "jobCode")
    EducationalModuleJobDTO toDto(EducationalModuleJob educationalModuleJob);

    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "jobId", target = "job")
    EducationalModuleJob toEntity(EducationalModuleJobDTO educationalModuleJobDTO);

    default EducationalModuleJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalModuleJob educationalModuleJob = new EducationalModuleJob();
        educationalModuleJob.setId(id);
        return educationalModuleJob;
    }
}
