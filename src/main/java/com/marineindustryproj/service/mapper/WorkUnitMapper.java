package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.WorkUnitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WorkUnit and its DTO WorkUnitDTO.
 */
@Mapper(componentModel = "spring", uses = {WorkIndustryMapper.class, WorkGroupMapper.class})
public interface WorkUnitMapper extends EntityMapper<WorkUnitDTO, WorkUnit> {

    @Mapping(source = "workIndustry.id", target = "workIndustryId")
    @Mapping(source = "workIndustry.title", target = "workIndustryTitle")
    @Mapping(source = "workGroup.id", target = "workGroupId")
    @Mapping(source = "workGroup.title", target = "workGroupTitle")
    WorkUnitDTO toDto(WorkUnit workUnit);

    @Mapping(source = "workIndustryId", target = "workIndustry")
    @Mapping(source = "workGroupId", target = "workGroup")
    WorkUnit toEntity(WorkUnitDTO workUnitDTO);

    default WorkUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkUnit workUnit = new WorkUnit();
        workUnit.setId(id);
        return workUnit;
    }
}
