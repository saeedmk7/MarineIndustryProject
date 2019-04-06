package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ActivityAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ActivityArea and its DTO ActivityAreaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActivityAreaMapper extends EntityMapper<ActivityAreaDTO, ActivityArea> {


    @Mapping(target = "educationalCenters", ignore = true)
    ActivityArea toEntity(ActivityAreaDTO activityAreaDTO);

    default ActivityArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActivityArea activityArea = new ActivityArea();
        activityArea.setId(id);
        return activityArea;
    }
}
