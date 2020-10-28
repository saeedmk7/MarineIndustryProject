package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeacherCriteriaGroup and its DTO TeacherCriteriaGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeacherCriteriaGroupMapper extends EntityMapper<TeacherCriteriaGroupDTO, TeacherCriteriaGroup> {


    @Mapping(target = "teacherGrades", ignore = true)
    @Mapping(target = "teacherCriteria", ignore = true)
    TeacherCriteriaGroup toEntity(TeacherCriteriaGroupDTO teacherCriteriaGroupDTO);

    default TeacherCriteriaGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeacherCriteriaGroup teacherCriteriaGroup = new TeacherCriteriaGroup();
        teacherCriteriaGroup.setId(id);
        return teacherCriteriaGroup;
    }
}
