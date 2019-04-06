package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CriterionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Criterion and its DTO CriterionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CriterionMapper extends EntityMapper<CriterionDTO, Criterion> {


    @Mapping(target = "pollItems", ignore = true)
    Criterion toEntity(CriterionDTO criterionDTO);

    default Criterion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Criterion criterion = new Criterion();
        criterion.setId(id);
        return criterion;
    }
}
