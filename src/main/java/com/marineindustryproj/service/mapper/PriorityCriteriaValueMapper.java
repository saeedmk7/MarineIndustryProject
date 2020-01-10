package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PriorityCriteriaValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PriorityCriteriaValue and its DTO PriorityCriteriaValueDTO.
 */
@Mapper(componentModel = "spring", uses = {PriorityCriteriaMapper.class, PreJobNiazsanjiCompetencyMapper.class})
public interface PriorityCriteriaValueMapper extends EntityMapper<PriorityCriteriaValueDTO, PriorityCriteriaValue> {

    @Mapping(source = "priorityCriteria.id", target = "priorityCriteriaId")
    @Mapping(source = "priorityCriteria.title", target = "priorityCriteriaTitle")
    @Mapping(source = "preJobNiazsanjiCompetency.id", target = "preJobNiazsanjiCompetencyId")
    @Mapping(source = "preJobNiazsanjiCompetency.title", target = "preJobNiazsanjiCompetencyTitle")
    PriorityCriteriaValueDTO toDto(PriorityCriteriaValue priorityCriteriaValue);

    @Mapping(source = "priorityCriteriaId", target = "priorityCriteria")
    @Mapping(source = "preJobNiazsanjiCompetencyId", target = "preJobNiazsanjiCompetency")
    PriorityCriteriaValue toEntity(PriorityCriteriaValueDTO priorityCriteriaValueDTO);

    default PriorityCriteriaValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        PriorityCriteriaValue priorityCriteriaValue = new PriorityCriteriaValue();
        priorityCriteriaValue.setId(id);
        return priorityCriteriaValue;
    }
}
