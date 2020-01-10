package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PriorityCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PriorityCriteria and its DTO PriorityCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PriorityCriteriaMapper extends EntityMapper<PriorityCriteriaDTO, PriorityCriteria> {


    @Mapping(target = "priorityCriteriaValues", ignore = true)
    PriorityCriteria toEntity(PriorityCriteriaDTO priorityCriteriaDTO);

    default PriorityCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        PriorityCriteria priorityCriteria = new PriorityCriteria();
        priorityCriteria.setId(id);
        return priorityCriteria;
    }
}
