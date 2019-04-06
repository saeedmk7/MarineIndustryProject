package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachApproachDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachApproach and its DTO TeachApproachDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeachApproachMapper extends EntityMapper<TeachApproachDTO, TeachApproach> {


    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    TeachApproach toEntity(TeachApproachDTO teachApproachDTO);

    default TeachApproach fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachApproach teachApproach = new TeachApproach();
        teachApproach.setId(id);
        return teachApproach;
    }
}
