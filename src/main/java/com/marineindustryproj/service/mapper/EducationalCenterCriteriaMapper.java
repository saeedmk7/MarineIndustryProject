package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenterCriteria and its DTO EducationalCenterCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {EducationalCenterGroupMapper.class})
public interface EducationalCenterCriteriaMapper extends EntityMapper<EducationalCenterCriteriaDTO, EducationalCenterCriteria> {

    @Mapping(source = "educationalCenterGroup.id", target = "educationalCenterGroupId")
    @Mapping(source = "educationalCenterGroup.title", target = "educationalCenterGroupTitle")
    EducationalCenterCriteriaDTO toDto(EducationalCenterCriteria educationalCenterCriteria);

    @Mapping(target = "educationalCenterGradeScores", ignore = true)
    @Mapping(source = "educationalCenterGroupId", target = "educationalCenterGroup")
    EducationalCenterCriteria toEntity(EducationalCenterCriteriaDTO educationalCenterCriteriaDTO);

    default EducationalCenterCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenterCriteria educationalCenterCriteria = new EducationalCenterCriteria();
        educationalCenterCriteria.setId(id);
        return educationalCenterCriteria;
    }
}
