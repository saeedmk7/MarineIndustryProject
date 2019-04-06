package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeachTechniqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TeachTechnique and its DTO TeachTechniqueDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeachTechniqueMapper extends EntityMapper<TeachTechniqueDTO, TeachTechnique> {


    @Mapping(target = "designAndPlannings", ignore = true)
    TeachTechnique toEntity(TeachTechniqueDTO teachTechniqueDTO);

    default TeachTechnique fromId(Long id) {
        if (id == null) {
            return null;
        }
        TeachTechnique teachTechnique = new TeachTechnique();
        teachTechnique.setId(id);
        return teachTechnique;
    }
}
