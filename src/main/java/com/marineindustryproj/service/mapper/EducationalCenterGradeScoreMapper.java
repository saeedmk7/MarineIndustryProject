package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenterGradeScore and its DTO EducationalCenterGradeScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {EducationalCenterCriteriaMapper.class, EducationalCenterGradeMapper.class})
public interface EducationalCenterGradeScoreMapper extends EntityMapper<EducationalCenterGradeScoreDTO, EducationalCenterGradeScore> {

    @Mapping(source = "educationalCenterCriteria.id", target = "educationalCenterCriteriaId")
    @Mapping(source = "educationalCenterCriteria.title", target = "educationalCenterCriteriaTitle")
    @Mapping(source = "educationalCenterGrade.id", target = "educationalCenterGradeId")
    @Mapping(source = "educationalCenterGrade.title", target = "educationalCenterGradeTitle")
    EducationalCenterGradeScoreDTO toDto(EducationalCenterGradeScore educationalCenterGradeScore);

    @Mapping(source = "educationalCenterCriteriaId", target = "educationalCenterCriteria")
    @Mapping(source = "educationalCenterGradeId", target = "educationalCenterGrade")
    EducationalCenterGradeScore toEntity(EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO);

    default EducationalCenterGradeScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenterGradeScore educationalCenterGradeScore = new EducationalCenterGradeScore();
        educationalCenterGradeScore.setId(id);
        return educationalCenterGradeScore;
    }
}
