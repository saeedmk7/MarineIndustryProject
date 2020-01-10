package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.JobNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobNiazsanji and its DTO JobNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RestrictionMapper.class, CourseTypeMapper.class, PreJobNiazsanjiMapper.class, EducationalModuleMapper.class, PersonMapper.class, OrganizationChartMapper.class, TeachingApproachMapper.class})
public interface JobNiazsanjiMapper extends EntityMapper<JobNiazsanjiDTO, JobNiazsanji> {

    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "preJobNiazsanji.id", target = "preJobNiazsanjiId")
    @Mapping(source = "preJobNiazsanji.code", target = "preJobNiazsanjiCode")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    JobNiazsanjiDTO toDto(JobNiazsanji jobNiazsanji);

    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "preJobNiazsanjiId", target = "preJobNiazsanji")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    JobNiazsanji toEntity(JobNiazsanjiDTO jobNiazsanjiDTO);

    default JobNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobNiazsanji jobNiazsanji = new JobNiazsanji();
        jobNiazsanji.setId(id);
        return jobNiazsanji;
    }
}
