package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CourseLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLocation and its DTO CourseLocationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseLocationMapper extends EntityMapper<CourseLocationDTO, CourseLocation> {


    @Mapping(target = "designAndPlannings", ignore = true)
    CourseLocation toEntity(CourseLocationDTO courseLocationDTO);

    default CourseLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLocation courseLocation = new CourseLocation();
        courseLocation.setId(id);
        return courseLocation;
    }
}
