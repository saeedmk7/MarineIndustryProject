package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JobDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Job and its DTO JobDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RasteMapper.class, RadehMapper.class, JobTypeMapper.class, ScientificWorkGroupMapper.class})
public interface JobMapper extends EntityMapper<JobDTO, Job> {

    @Mapping(source = "raste.id", target = "rasteId")
    @Mapping(source = "raste.title", target = "rasteTitle")
    @Mapping(source = "radeh.id", target = "radehId")
    @Mapping(source = "radeh.title", target = "radehTitle")
    @Mapping(source = "jobType.id", target = "jobTypeId")
    @Mapping(source = "jobType.title", target = "jobTypeTitle")
    @Mapping(source = "scientificWorkGroup.id", target = "scientificWorkGroupId")
    @Mapping(source = "scientificWorkGroup.title", target = "scientificWorkGroupTitle")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.title", target = "parentTitle")
    JobDTO toDto(Job job);

    @Mapping(target = "jobPeople", ignore = true)
    @Mapping(target = "practicaljobPeople", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "educationalModuleJobs", ignore = true)
    @Mapping(source = "rasteId", target = "raste")
    @Mapping(source = "radehId", target = "radeh")
    @Mapping(source = "jobTypeId", target = "jobType")
    @Mapping(source = "scientificWorkGroupId", target = "scientificWorkGroup")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "mainTasks", ignore = true)
    @Mapping(target = "niazsanjiGroups", ignore = true)
    Job toEntity(JobDTO jobDTO);

    default Job fromId(Long id) {
        if (id == null) {
            return null;
        }
        Job job = new Job();
        job.setId(id);
        return job;
    }
}
