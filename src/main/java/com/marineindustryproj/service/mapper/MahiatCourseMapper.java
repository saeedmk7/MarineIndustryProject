package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MahiatCourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MahiatCourse and its DTO MahiatCourseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MahiatCourseMapper extends EntityMapper<MahiatCourseDTO, MahiatCourse> {


    @Mapping(target = "designAndPlannings", ignore = true)
    MahiatCourse toEntity(MahiatCourseDTO mahiatCourseDTO);

    default MahiatCourse fromId(Long id) {
        if (id == null) {
            return null;
        }
        MahiatCourse mahiatCourse = new MahiatCourse();
        mahiatCourse.setId(id);
        return mahiatCourse;
    }
}
