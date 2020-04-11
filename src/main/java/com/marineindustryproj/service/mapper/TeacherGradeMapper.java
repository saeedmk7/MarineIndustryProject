package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeacherGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeacherGrade and its DTO TeacherGradeDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, TeacherMapper.class})
public interface TeacherGradeMapper extends EntityMapper<TeacherGradeDTO, TeacherGrade> {

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "teacher.name", target = "teacherName")
    TeacherGradeDTO toDto(TeacherGrade teacherGrade);

    @Mapping(target = "teacherGradeScores", ignore = true)
    @Mapping(source = "teacherId", target = "teacher")
    TeacherGrade toEntity(TeacherGradeDTO teacherGradeDTO);

    default TeacherGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeacherGrade teacherGrade = new TeacherGrade();
        teacherGrade.setId(id);
        return teacherGrade;
    }
}
