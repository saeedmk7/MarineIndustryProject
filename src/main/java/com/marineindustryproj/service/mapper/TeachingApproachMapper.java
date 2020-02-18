package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachingApproachDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachingApproach and its DTO TeachingApproachDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeachingApproachMapper extends EntityMapper<TeachingApproachDTO, TeachingApproach> {


    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "designNiazsanjis", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    @Mapping(target = "preJobNiazsanjiCompetencies", ignore = true)
    TeachingApproach toEntity(TeachingApproachDTO teachingApproachDTO);

    default TeachingApproach fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachingApproach teachingApproach = new TeachingApproach();
        teachingApproach.setId(id);
        return teachingApproach;
    }
}
