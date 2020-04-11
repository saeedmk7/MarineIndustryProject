package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeacherCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeacherCriteria and its DTO TeacherCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeacherCriteriaMapper extends EntityMapper<TeacherCriteriaDTO, TeacherCriteria> {


    @Mapping(target = "teacherGradeScores", ignore = true)
    TeacherCriteria toEntity(TeacherCriteriaDTO teacherCriteriaDTO);

    default TeacherCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeacherCriteria teacherCriteria = new TeacherCriteria();
        teacherCriteria.setId(id);
        return teacherCriteria;
    }
}
