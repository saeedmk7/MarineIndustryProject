package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeacherGradeScore and its DTO TeacherGradeScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {TeacherCriteriaMapper.class, TeacherGradeMapper.class})
public interface TeacherGradeScoreMapper extends EntityMapper<TeacherGradeScoreDTO, TeacherGradeScore> {

    @Mapping(source = "teacherCriteria.id", target = "teacherCriteriaId")
    @Mapping(source = "teacherCriteria.title", target = "teacherCriteriaTitle")
    @Mapping(source = "teacherGrade.id", target = "teacherGradeId")
    @Mapping(source = "teacherGrade.title", target = "teacherGradeTitle")
    TeacherGradeScoreDTO toDto(TeacherGradeScore teacherGradeScore);

    @Mapping(source = "teacherCriteriaId", target = "teacherCriteria")
    @Mapping(source = "teacherGradeId", target = "teacherGrade")
    TeacherGradeScore toEntity(TeacherGradeScoreDTO teacherGradeScoreDTO);

    default TeacherGradeScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeacherGradeScore teacherGradeScore = new TeacherGradeScore();
        teacherGradeScore.setId(id);
        return teacherGradeScore;
    }
}
