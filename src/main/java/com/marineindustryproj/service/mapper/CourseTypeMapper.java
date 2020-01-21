package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CourseTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseType and its DTO CourseTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseTypeMapper extends EntityMapper<CourseTypeDTO, CourseType> {


    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "requestNiazsanjiFardis", ignore = true)
    @Mapping(target = "educationalHistories", ignore = true)
    @Mapping(target = "designNiazsanjis", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    CourseType toEntity(CourseTypeDTO courseTypeDTO);

    default CourseType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseType courseType = new CourseType();
        courseType.setId(id);
        return courseType;
    }
}
