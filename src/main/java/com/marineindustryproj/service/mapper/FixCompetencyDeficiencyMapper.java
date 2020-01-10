package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FixCompetencyDeficiency and its DTO FixCompetencyDeficiencyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FixCompetencyDeficiencyMapper extends EntityMapper<FixCompetencyDeficiencyDTO, FixCompetencyDeficiency> {


    @Mapping(target = "preJobNiazsanjiCompetencies", ignore = true)
    FixCompetencyDeficiency toEntity(FixCompetencyDeficiencyDTO fixCompetencyDeficiencyDTO);

    default FixCompetencyDeficiency fromId(Long id) {
        if (id == null) {
            return null;
        }
        FixCompetencyDeficiency fixCompetencyDeficiency = new FixCompetencyDeficiency();
        fixCompetencyDeficiency.setId(id);
        return fixCompetencyDeficiency;
    }
}
