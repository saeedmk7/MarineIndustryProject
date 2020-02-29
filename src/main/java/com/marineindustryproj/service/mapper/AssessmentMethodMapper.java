package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.AssessmentMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AssessmentMethod and its DTO AssessmentMethodDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AssessmentMethodMapper extends EntityMapper<AssessmentMethodDTO, AssessmentMethod> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    AssessmentMethod toEntity(AssessmentMethodDTO assessmentMethodDTO);

    default AssessmentMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        AssessmentMethod assessmentMethod = new AssessmentMethod();
        assessmentMethod.setId(id);
        return assessmentMethod;
    }
}
