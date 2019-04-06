package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.WorkGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WorkGroup and its DTO WorkGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkGroupMapper extends EntityMapper<WorkGroupDTO, WorkGroup> {


    @Mapping(target = "people", ignore = true)
    @Mapping(target = "workUnits", ignore = true)
    WorkGroup toEntity(WorkGroupDTO workGroupDTO);

    default WorkGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkGroup workGroup = new WorkGroup();
        workGroup.setId(id);
        return workGroup;
    }
}
