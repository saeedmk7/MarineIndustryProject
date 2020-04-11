package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenterGrade and its DTO EducationalCenterGradeDTO.
 */
@Mapper(componentModel = "spring", uses = {EvaluatorOpinionMapper.class, DocumentMapper.class, EducationalCenterServiceMapper.class, EducationalCenterMapper.class})
public interface EducationalCenterGradeMapper extends EntityMapper<EducationalCenterGradeDTO, EducationalCenterGrade> {

    @Mapping(source = "educationalCenterService.id", target = "educationalCenterServiceId")
    @Mapping(source = "educationalCenterService.title", target = "educationalCenterServiceTitle")
    @Mapping(source = "educationalCenter.id", target = "educationalCenterId")
    @Mapping(source = "educationalCenter.name", target = "educationalCenterName")
    EducationalCenterGradeDTO toDto(EducationalCenterGrade educationalCenterGrade);

    @Mapping(target = "educationalCenterGradeScores", ignore = true)
    @Mapping(source = "educationalCenterServiceId", target = "educationalCenterService")
    @Mapping(source = "educationalCenterId", target = "educationalCenter")
    EducationalCenterGrade toEntity(EducationalCenterGradeDTO educationalCenterGradeDTO);

    default EducationalCenterGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenterGrade educationalCenterGrade = new EducationalCenterGrade();
        educationalCenterGrade.setId(id);
        return educationalCenterGrade;
    }
}
