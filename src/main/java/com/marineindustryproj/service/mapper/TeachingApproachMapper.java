package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachingApproachDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachingApproach and its DTO TeachingApproachDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeachingApproachMapper extends EntityMapper<TeachingApproachDTO, TeachingApproach> {


    @Mapping(target = "designAndPlannings", ignore = true)
    TeachingApproach toEntity(TeachingApproachDTO teachingApproachDTO);

    default TeachingApproach fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachingApproach teachingApproach = new TeachingApproach();
        teachingApproach.setId(id);
        return teachingApproach;
    }
}
