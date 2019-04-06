package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CourseTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseType and its DTO CourseTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseTypeMapper extends EntityMapper<CourseTypeDTO, CourseType> {


    @Mapping(target = "designAndPlannings", ignore = true)
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
