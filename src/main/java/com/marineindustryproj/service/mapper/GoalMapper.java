package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.GoalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Goal and its DTO GoalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GoalMapper extends EntityMapper<GoalDTO, Goal> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    Goal toEntity(GoalDTO goalDTO);

    default Goal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Goal goal = new Goal();
        goal.setId(id);
        return goal;
    }
}
