package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CompetencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Competency and its DTO CompetencyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompetencyMapper extends EntityMapper<CompetencyDTO, Competency> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    @Mapping(target = "preJobNiazsanjiCompetencies", ignore = true)
    Competency toEntity(CompetencyDTO competencyDTO);

    default Competency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Competency competency = new Competency();
        competency.setId(id);
        return competency;
    }
}
