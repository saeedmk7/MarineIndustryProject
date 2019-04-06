package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.WorkIndustryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WorkIndustry and its DTO WorkIndustryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkIndustryMapper extends EntityMapper<WorkIndustryDTO, WorkIndustry> {


    @Mapping(target = "people", ignore = true)
    @Mapping(target = "workUnits", ignore = true)
    WorkIndustry toEntity(WorkIndustryDTO workIndustryDTO);

    default WorkIndustry fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkIndustry workIndustry = new WorkIndustry();
        workIndustry.setId(id);
        return workIndustry;
    }
}
